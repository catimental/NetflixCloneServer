drop table if exists member CASCADE;
CREATE TABLE `member` (
    `id` int(11) NOT NULL AUTO_INCREMENT,
    `loginId` varchar(255) CHARACTER SET utf8mb3 DEFAULT NULL,
    `loginPassword` varchar(255) CHARACTER SET utf8mb3 DEFAULT NULL,
    PRIMARY KEY (`id`),
    UNIQUE KEY `id_UNIQUE` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4;

