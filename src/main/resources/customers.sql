/*
SQLyog Ultimate v12.08 (64 bit)
MySQL - 5.7.20-log 
*********************************************************************
*/
/*!40101 SET NAMES utf8 */;

create table `customers` (
	`id` int (11),
	`name` varchar (90),
	`address` varchar (90),
	`phone` varchar (90)
);
ALTER TABLE customers ADD CONSTRAINT name_uk UNIQUE(NAME);
insert into `customers` (`id`, `name`, `address`, `phone`) values('2','Jerry','Shanghai','13182467741');
