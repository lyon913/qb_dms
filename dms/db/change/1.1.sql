alter table treply add author varchar(100);

alter table tsuggestion add author varchar(100);

insert into TRole(name,displayName) values('ROLE_SUGGESTION_REPLY','意见簿回复');

insert into TDatabaseChange(majorVersion,minorVersion,fixVersion,fileName) values(1,1,0,'1.1.base.sql');