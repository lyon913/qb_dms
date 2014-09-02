-- 关闭安全更新模式（safe update mode）
set sql_safe_updates=0;	

-- 公共附件表
create table TAttachment (
    id bigint not null auto_increment,
    foreignTable varchar(20)  not null,
    foreignKey bigint not null,
    name varchar(260) not null,
    path varchar(260) not null,
    size bigint not null,
    createTime datetime,
   	creatorLoginName varchar(32),
    creatorName varchar(20),
    creatorIp  varchar(20),
  	lastUpdate datetime,
  	lastUpdaterLoginName varchar(32),
   	lastUpdaterName varchar(20),
    lastUpdaterIp varchar(20),
    primary key (id)
);

alter table TSuggestion add type varchar(10) not null;
alter table TSuggestion add state varchar(10) not null;

-- 更新历史记录
update TSuggestion set type='Suggestion';
update TSuggestion set state='Public';


alter table TDatabaseChange add opDate datetime;
insert into TDatabaseChange(majorVersion,minorVersion,fixVersion,fileName,opdate) values(1,3,0,'1.3.sql',now());

-- 恢复安全更新
set sql_safe_updates=1;