CREATE TABLE `dms`.`tuser_tdepartment` (
  `id` bigINT NOT NULL AUTO_INCREMENT,
  `userId` bigINT NULL,
  `departmentId` bigINT NULL,
  PRIMARY KEY (`id`));
insert into TDatabaseChange(majorVersion,minorVersion,fixVersion,fileName,opdate) 
	values(1,13,0,'1.13.sql',now());