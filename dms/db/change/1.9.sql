set sql_safe_updates=0;	
create table TVote (
	id bigint not null auto_increment, 
	createTime datetime, 
	creatorIp varchar(20), 
	creatorLoginName varchar(32), 
	creatorName varchar(20), 
	lastUpdate datetime, 
	lastUpdaterIp varchar(20), 
	lastUpdaterLoginName varchar(32), 
	lastUpdaterName varchar(20), 
	description varchar(500), 
	endDate date not null, 
	isMulti bit not null, 
	isOpen bit not null, 
	maxVotes integer not null, 
	title varchar(50) not null,
	authorId bigint not null,
	authorName varchar(20) not null,
	primary key (id)
);
create table TVoteOption (
	id bigint not null auto_increment, 
	createTime datetime, 
	creatorIp varchar(20), 
	creatorLoginName varchar(32), 
	creatorName varchar(20), 
	lastUpdate datetime, 
	lastUpdaterIp varchar(20), 
	lastUpdaterLoginName varchar(32), 
	lastUpdaterName varchar(20), 
	description varchar(200), 
	title varchar(50) not null, 
	voteId bigint not null, 
	primary key (id)
);
create table TVoteRecord (
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
	userId bigint not null, 
	voteId bigint not null, 
	primary key (id)
);
alter table TVoteOption add constraint FK_TVoteoption_TVote foreign key (voteId) references TVote (id);

ALTER TABLE `dms`.`tsuggestion` 
	ADD COLUMN `voteId` BIGINT(20) NULL AFTER `state`;

insert into TDatabaseChange(majorVersion,minorVersion,fixVersion,fileName,opdate) 
	values(1,9,0,'1.9.sql',now());