ALTER TABLE `dms`.`tnotice` ADD COLUMN `emergencyState` TINYINT(1) NOT NULL  AFTER `state` ;
insert into TDatabaseChange(majorVersion,minorVersion,fixVersion,fileName,opdate) 
	values(1,11,0,'1.11.sql',now());
