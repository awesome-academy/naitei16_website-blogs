package com.blogs.controller;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.ModelAndView;

import com.blogs.event.OnRegistrationCompleteEvent;
import com.blogs.model.User;
import com.blogs.model.VerificationToken;
import com.blogs.service.UserService;
import com.blogs.validator.UserValidator;

@Controller
@RequestMapping(value = "/register")
public class RegisterController {

	private static final Logger logger = Logger.getLogger(RegisterController.class);

	@Autowired
	private UserService userService;
	@Autowired
	private BCryptPasswordEncoder passwordEncoder;
	@Autowired
	ApplicationEventPublisher eventPublisher;

	@InitBinder("new_user")
	public void initBinder(WebDataBinder binder) {
		// validator
		binder.addValidators(new UserValidator());
		// editor
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		binder.registerCustomEditor(Date.class, null, new CustomDateEditor(dateFormat, true));
		;
	}

	@RequestMapping(method = RequestMethod.GET)
	public ModelAndView register() {
		if (ControllerUtils.isLogined()) {
			return new ModelAndView("redirect:/");
		}
		return new ModelAndView("auth/register", "new_user", new User());
	}

	@RequestMapping(method = RequestMethod.POST)
	public ModelAndView register_process(@Valid @ModelAttribute("new_user") User new_user, BindingResult result,
			ModelMap model, HttpServletRequest request) {
		// check validate
		if (result.hasErrors()) {
			logger.info("info user has error. ");
			return new ModelAndView("auth/register", "new_user", new_user);
		}

		// encode password
		String encodePassword = passwordEncoder.encode(new_user.getPassword());
		new_user.setPassword(encodePassword);

		// create verification token
		String token = UUID.randomUUID().toString();
		VerificationToken tokenObj = new VerificationToken(new_user, token);

		// save user + token
		if (userService.createNewUser(new_user, tokenObj)) {
			// if save success, trigger send email
			eventPublisher.publishEvent(new OnRegistrationCompleteEvent(new_user, request.getLocale(),
					ControllerUtils.baseUrlOf(request) + request.getContextPath(), tokenObj));
		}

		return new ModelAndView("auth/alertPage", "message", "go to email to active account.");
	}

	@GetMapping("/regitrationConfirm")
	public String confirmRegistration(WebRequest request, Model model, @RequestParam("token") String token) {

		VerificationToken verificationToken = userService.getVerificationToken(token);
		if (verificationToken == null) {
			model.addAttribute("message", "auth.message.token.not.true");
			return "auth/alertPage";
		}

		User user = verificationToken.getUser();
		Calendar cal = Calendar.getInstance();
		if ((verificationToken.getExpiryDate().getTime() - cal.getTime().getTime()) <= 0) {
			model.addAttribute("message", "auth.message.expired");
			return "auth/alertPage";
		}

		user.setConfirmed(true);
		if (userService.updateSuccessStateRegistationUser(user, verificationToken)) {
			logger.info("verify by email success. ");
			model.addAttribute("message", "verify by email success. ");
			return "auth/login";
		}
		
		model.addAttribute("message", "something wrong. ");
		return "auth/alertPage";
	}
}
