create table `ATTACHED_PHOTO`(
    `attached_id` BINARY(16) NOT NULL,
    `review_id` BINARY(16) NOT NULL,
    PRIMARY KEY `ATTACHED_PHOTO`(`attached_id`),
    INDEX `idx__photo`(`attached_id`, `review_id`)
)
