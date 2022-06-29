create table `MILEAGE`(
    `id` BINARY(16) NOT NULL,
    `type` varchar(30) NOT NULL,
    `point` int NOT NULL,
    `review_id` BINARY(16) NOT null,
    `create_at` datetime(6) NOT NULL,
    `update_at` datetime(6) NOT NULL,
    PRIMARY KEY (`id`),
    FOREIGN KEY (`review_id`) references `review` (`review_id`),
    INDEX `idx_mileage` (`id`, `review_id`)
)
