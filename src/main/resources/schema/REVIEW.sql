CREATE TABLE `REVIEW`
(
    `review_id` BINARY(16)   NOT NULL,
    `content`   varchar(255) NOT NULL,
    `place_id`  BINARY(16),
    `user_id`   BINARY(16),
    FOREIGN KEY (`place_id`) references `PLACE` (`place_id`),
    foreign key (`user_id`) references `USER` (`user_id`),
    PRIMARY KEY (`review_id`),
    INDEX `idx__review` (`review_id`, `place_id`, `user_id`)
)


