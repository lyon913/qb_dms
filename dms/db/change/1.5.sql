create table TClickCount(
	id bigint not null auto_increment primary key, 
	clickType varchar(100) not null, -- 存的是表名
	referenceId bigint not null, -- 存的是对应表的具体记录的id
	counts bigint not null -- 存的是点击次数，默认为0
);

insert into TDatabaseChange(majorVersion,minorVersion,fixVersion,fileName,opdate) values(1,5,0,'1.5.sql',now());