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
	fk bigint not null, 
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

ALTER TABLE `dms`.`tlike` 
ADD INDEX `fk` (`fk` ASC);

ALTER TABLE `dms`.`tlikerecord` 
ADD INDEX `likeid` (`likeId` ASC),
ADD INDEX `optionid` (`optionId` ASC),
ADD INDEX `userid` (`userId` ASC);

INSERT INTO `tlikeoption` (`createTime`,`creatorIp`,`creatorLoginName`,`creatorName`,`lastUpdate`,`lastUpdaterIp`,`lastUpdaterLoginName`,`lastUpdaterName`,`title`,`picture`) VALUES ('2015-05-31 11:21:26','0:0:0:0:0:0:0:1','admin','系统管理员','2015-05-31 11:21:26','0:0:0:0:0:0:0:1','admin','系统管理员','赞同','zt.png');
INSERT INTO `tlikeoption` (`createTime`,`creatorIp`,`creatorLoginName`,`creatorName`,`lastUpdate`,`lastUpdaterIp`,`lastUpdaterLoginName`,`lastUpdaterName`,`title`,`picture`) VALUES ('2015-05-31 11:21:33','0:0:0:0:0:0:0:1','admin','系统管理员','2015-05-31 11:21:33','0:0:0:0:0:0:0:1','admin','系统管理员','不赞同','bzt.png');
INSERT INTO `tlikeoption` (`createTime`,`creatorIp`,`creatorLoginName`,`creatorName`,`lastUpdate`,`lastUpdaterIp`,`lastUpdaterLoginName`,`lastUpdaterName`,`title`,`picture`) VALUES ('2015-05-31 14:09:08','0:0:0:0:0:0:0:1','admin','系统管理员','2015-05-31 14:09:08','0:0:0:0:0:0:0:1','admin','系统管理员','有创意','ycy.png');
INSERT INTO `tlikeoption` (`createTime`,`creatorIp`,`creatorLoginName`,`creatorName`,`lastUpdate`,`lastUpdaterIp`,`lastUpdaterLoginName`,`lastUpdaterName`,`title`,`picture`) VALUES ('2015-05-31 14:09:22','0:0:0:0:0:0:0:1','admin','系统管理员','2015-05-31 14:09:22','0:0:0:0:0:0:0:1','admin','系统管理员','震惊','zj.png');
INSERT INTO `tlikeoption` (`createTime`,`creatorIp`,`creatorLoginName`,`creatorName`,`lastUpdate`,`lastUpdaterIp`,`lastUpdaterLoginName`,`lastUpdaterName`,`title`,`picture`) VALUES ('2015-05-31 14:09:35','0:0:0:0:0:0:0:1','admin','系统管理员','2015-05-31 14:09:35','0:0:0:0:0:0:0:1','admin','系统管理员','搞笑','gx.png');
INSERT INTO `tlikeoption` (`createTime`,`creatorIp`,`creatorLoginName`,`creatorName`,`lastUpdate`,`lastUpdaterIp`,`lastUpdaterLoginName`,`lastUpdaterName`,`title`,`picture`) VALUES ('2015-05-31 14:09:46','0:0:0:0:0:0:0:1','admin','系统管理员','2015-05-31 14:09:46','0:0:0:0:0:0:0:1','admin','系统管理员','难过','ng.png');
INSERT INTO `tlikeoption` (`createTime`,`creatorIp`,`creatorLoginName`,`creatorName`,`lastUpdate`,`lastUpdaterIp`,`lastUpdaterLoginName`,`lastUpdaterName`,`title`,`picture`) VALUES ('2015-05-31 14:10:01','0:0:0:0:0:0:0:1','admin','系统管理员','2015-05-31 14:10:01','0:0:0:0:0:0:0:1','admin','系统管理员','新奇','xq.png');
INSERT INTO `tlikeoption` (`createTime`,`creatorIp`,`creatorLoginName`,`creatorName`,`lastUpdate`,`lastUpdaterIp`,`lastUpdaterLoginName`,`lastUpdaterName`,`title`,`picture`) VALUES ('2015-05-31 14:10:11','0:0:0:0:0:0:0:1','admin','系统管理员','2015-05-31 14:10:11','0:0:0:0:0:0:0:1','admin','系统管理员','愤怒','fn.png');


insert into TDatabaseChange(majorVersion,minorVersion,fixVersion,fileName,opdate) 
	values(1,12,0,'1.12.sql',now());