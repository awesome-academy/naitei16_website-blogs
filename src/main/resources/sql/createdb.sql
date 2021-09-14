INSERT INTO `blog`.`users` (`id`, `dateOfBirth`, `email`, `isConfirmed`, `name`, `password`, `role`, `sex`, `username`) VALUES ('1', '2021-09-07', 'thanhan@gmail.com', b'1', 'thanhan', '$2a$10$cIj4ZZL0BsLgQAk/1HHvtOJ/lXXerjnxJD.avm8LZmca9xJFhQ28O', 'USER', '1', 'thanhan');
INSERT INTO `blog`.`users` (`id`, `dateOfBirth`, `email`, `isConfirmed`, `name`, `password`, `role`, `sex`, `username`) VALUES ('2', '2021-09-07', 'a@gmail.com', b'1', 'user1', '$2a$10$cIj4ZZL0BsLgQAk/1HHvtOJ/lXXerjnxJD.avm8LZmca9xJFhQ28O', 'USER', '1', 'user1');
INSERT INTO `blog`.`users` (`id`, `dateOfBirth`, `email`, `isConfirmed`, `name`, `password`, `role`, `sex`, `username`) VALUES ('3', '2021-09-07', 'b@gmail.com', b'1', 'user2', '$2a$10$cIj4ZZL0BsLgQAk/1HHvtOJ/lXXerjnxJD.avm8LZmca9xJFhQ28O', 'USER', '1', 'user2');
INSERT INTO `blog`.`users` (`id`, `dateOfBirth`, `email`, `isConfirmed`, `name`, `password`, `role`, `sex`, `username`) VALUES ('4', '2021-09-07', 'c@gmail.com', b'1', 'user3', '$2a$10$cIj4ZZL0BsLgQAk/1HHvtOJ/lXXerjnxJD.avm8LZmca9xJFhQ28O', 'USER', '1', 'user3');

insert into relationship(follower_id, followed_id) values(1,2);
insert into relationship(follower_id, followed_id) values(1,3);
insert into relationship(follower_id, followed_id) values(1,4);
insert into relationship(follower_id, followed_id) values(2,1);
insert into relationship(follower_id, followed_id) values(3,1);

INSERT INTO `blog`.`posts` (`Id`, `createdAt`, `numberOfViews`, `status`, `title`, `updatedAt`, `author_id`) VALUES ('1', '1999-10-12 00:00:00', '1', '1', 'the first post', '1999-10-12 00:00:12', '1');
INSERT INTO `blog`.`posts` (`Id`, `createdAt`, `numberOfViews`, `status`, `title`, `updatedAt`, `author_id`) VALUES ('2', '1999-10-12 00:00:00', '1', '1', 'the second post', '1999-10-12 00:00:12', '2');
INSERT INTO `blog`.`posts` (`Id`, `createdAt`, `numberOfViews`, `status`, `title`, `updatedAt`, `author_id`) VALUES ('3', '1999-10-12 00:00:00', '1', '1', 'the third post', '1999-10-12 00:00:12', '2');
