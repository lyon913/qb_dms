set sql_safe_updates=0;	
ALTER TABLE `dms`.`tnotice` 
ADD COLUMN `state` TINYINT(1) NOT NULL ;

update TNotice set state="1";
set sql_safe_updates=1;	
insert into TDatabaseChange(majorVersion,minorVersion,fixVersion,fileName,opdate) values(1,8,0,'1.8.sql',now());