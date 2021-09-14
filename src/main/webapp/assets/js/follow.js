$(document).ready(function () {
	
	console.log(hasFollowed);
	
	$("#btn-follow").text(hasFollowed ? "Unfollow" : "Follow");
	
    $("#follow-form").submit(function (event) {

        //stop submit the form, we will post it manually.
        event.preventDefault();
       
        fire_ajax_submit();
		
    });

});

function fire_ajax_submit() {
	 
    var follower_id = $("#follower_id").val();
    var followed_id = $("#followed_id").val();
    var type = $('#btn-follow').text(); //Unfollow
    console.log("type ", type);
	var _type = type=="Follow" ? "Unfollow" : "Follow"; // Follow
	console.log("_type ", _type);
	
    $("#btn-follow").prop("disabled", true);

    $.ajax({
        type: "POST",
        contentType: "application/json",
        url: link,
        data: JSON.stringify({follower_id, followed_id, type}),
        dataType: 'json',
        cache: false,
        timeout: 600000,
        success: function (data) {
            console.log("SUCCESS ", _type);
            $("#btn-follow").prop("disabled", false);
            $("#btn-follow").text(_type);
            alert("success");

        },
        error: function (e) {
            console.log("ERROR ", type);
            $("#btn-follow").prop("disabled", false);
            alert("false");
        }
    });

}