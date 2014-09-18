set sql_safe_updates=0;	

ALTER TABLE `dms`.`tfile` 
ADD COLUMN `authorDepart` VARCHAR(50) NULL AFTER `author`;

update tfile f 
inner join tuser u on(f.authorid = u.id )
inner join tdepartment d on (u.departmentId = d.id)
set f.author = u.name ,f.authorDepart = d.name;

set sql_safe_updates=1;	
insert into TDatabaseChange(majorVersion,minorVersion,fixVersion,fileName,opdate) values(1,7,0,'1.7.sql',now());

