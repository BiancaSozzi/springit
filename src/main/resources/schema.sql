CREATE DATABASE  IF NOT EXISTS `springit`;
USE `springit`;

--
-- Table structure for table `comment`
--
DROP TABLE IF EXISTS `comment`;

CREATE TABLE `comment` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `created_by` varchar(255) DEFAULT NULL,
  `created_at` datetime DEFAULT NULL,
  `modified_by` varchar(255) DEFAULT NULL,
  `modified_at` datetime DEFAULT NULL,
  `body` varchar(255) DEFAULT NULL,
  `link_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKoutxw6g1ndh1t6282y0fwvami` (`link_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_general_ci;

--
-- Table structure for table `link`
--

DROP TABLE IF EXISTS `link`;
CREATE TABLE `link` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `created_by` varchar(255) DEFAULT NULL,
  `created_at` datetime DEFAULT NULL,
  `modified_by` varchar(255) DEFAULT NULL,
  `modified_at` datetime DEFAULT NULL,
  `title` varchar(255) DEFAULT NULL,
  `url` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_general_ci;