set sql_safe_updates=0;	

create table TLike (
	id bigint not null auto_increment, 
	createTime datetime, 
	creatorIp varchar(20), 
	creatorLoginName varchar(32), 
	creatorName varchar(20), 
	lastUpdate datetime, 
	lastUpdaterIp varchar(20), 
	lastUpdaterLoginName varchar(32), 
	lastUpdaterName varchar(20), 
	endDate date not null, 
	primary key (id)
);

create table TLikeOption (
	id bigint not null auto_increment, 
	createTime datetime, 
	creatorIp varchar(20), 
	creatorLoginName varchar(32), 
	creatorName varchar(20), 
	lastUpdate datetime, 
	lastUpdaterIp varchar(20), 
	lastUpdaterLoginName varchar(32), 
	lastUpdaterName varchar(20), 
	title varchar(20) not null, 
	picture varchar(200), 
	primary key (id)
);
create table TLikeRecord (
	id bigint not null auto_increment, 
	createTime datetime, 
	creatorIp varchar(20), 
	creatorLoginName varchar(32), 
	creatorName varchar(20), 
	lastUpdate datetime, 
	lastUpdaterIp varchar(20), 
	lastUpdaterLoginName varchar(32), 
	lastUpdaterName varchar(20),
	optionId bigint not null, 
	likeId bigint not null,
	userId bigint not null,
	userName varchar(20),
	primary key (id)
);

ALTER TABLE `dms`.`tsuggestion` 
	ADD COLUMN `likeId` BIGINT(20) NULL;

insert into TDatabaseChange(majorVersion,minorVersion,fixVersion,fileName,opdate) 
	values(1,12,0,'1.12.sql',now());