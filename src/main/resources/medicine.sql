CREATE TABLE `patient` (
`id` int(11) UNSIGNED NOT NULL AUTO_INCREMENT,
`name` varchar(30) NOT NULL,
`doctor_id` int(11) UNSIGNED NOT NULL,
`phone` char(11) NOT NULL,
`registry_date` date NOT NULL,
`trustAttr_id` int(11) UNSIGNED NOT NULL,
`sense_aware` boolean NOT NULL,
`illness_condition` varchar(255) NULL,
PRIMARY KEY (`id`) 
);

CREATE TABLE `doctor` (
`id` int(11) UNSIGNED NOT NULL AUTO_INCREMENT,
`name` varchar(30) NOT NULL,
`position` tinyint NOT NULL,
`office_location` varchar(0) NOT NULL,
`office_phone` char(11) NOT NULL,
`registry_date` date NOT NULL,
`department` tinyint NOT NULL,
`title` tinyint NOT NULL,
`workage` tinyint NOT NULL,
`degree` tinyint NOT NULL,
PRIMARY KEY (`id`) 
);

CREATE TABLE `trust_attr` (
`id` int(11) UNSIGNED NOT NULL AUTO_INCREMENT,
`department` tinyint NOT NULL,
`demand_title` tinyint NOT NULL,
`demand_workage` tinyint NOT NULL,
`demand_degree` tinyint NOT NULL,
PRIMARY KEY (`id`) 
);

CREATE TABLE `others` (
`id` int(11) NOT NULL AUTO_INCREMENT,
`name` varchar(30) NOT NULL,
`gender` boolean NOT NULL,
`age` int NOT NULL,
`job` tinyint NOT NULL,
`company` varchar(50) NOT NULL,
`address` varchar(50) NOT NULL,
`phone` char(11) NOT NULL,
`is_send_request` boolean NOT NULL,
PRIMARY KEY (`id`) 
);


ALTER TABLE `patient` ADD CONSTRAINT `fk_trust_attr` FOREIGN KEY (`trustAttr_id`) REFERENCES `trust_attr` (`id`) ON DELETE CASCADE ON UPDATE CASCADE;
ALTER TABLE `patient` ADD CONSTRAINT `fk_doctor` FOREIGN KEY (`doctor_id`) REFERENCES `doctor` (`id`) ON DELETE CASCADE ON UPDATE CASCADE;

