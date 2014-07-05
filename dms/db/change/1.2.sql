create table TLoginAudit (
        id bigint not null auto_increment,
        userId bigint not null,
        loginName varchar(32) not null,
        userName varchar(20),
        longinTime datetime not null,
        ipAddress varchar(20),
        primary key (id)
    );
    
    -- Tfile
    alter table TFile add creatorLoginName varchar(32) ;
    alter table TFile add creatorName varchar(20) ;
    alter table TFile add creatorIp  varchar(20) ;
    
    alter table TFile add lastUpdate datetime ;
    alter table TFile add lastUpdaterLoginName varchar(32) ;
    alter table TFile add lastUpdaterName varchar(20) ;
    alter table TFile add lastUpdaterIp varchar(20) ;
    
    update TFile set creatorLoginName='sys_upgrade',creatorName='系统升级',creatorIp='0.0.0.0',lastUpdate='1900-01-01 01:01:01',lastUpdaterLoginName='sys_upgrade',lastUpdaterName='系统升级',lastUpdaterIp='0.0.0.0';
    
    -- TSoftware
    alter table TSoftware add creatorLoginName varchar(32) ;
    alter table TSoftware add creatorName varchar(20) ;
    alter table TSoftware add creatorIp  varchar(20) ;
    
    alter table TSoftware add lastUpdate datetime ;
    alter table TSoftware add lastUpdaterLoginName varchar(32) ;
    alter table TSoftware add lastUpdaterName varchar(20) ;
    alter table TSoftware add lastUpdaterIp varchar(20) ;
    
    update TSoftware set creatorLoginName='sys_upgrade',creatorName='系统升级',creatorIp='0.0.0.0',lastUpdate='1900-01-01 01:01:01',lastUpdaterLoginName='sys_upgrade',lastUpdaterName='系统升级',lastUpdaterIp='0.0.0.0';
    
	-- TFolder
    alter table TFolder add creatorLoginName varchar(32);
    alter table TFolder add creatorName varchar(20);
    alter table TFolder add creatorIp  varchar(20);
    
    alter table TFolder add lastUpdate datetime;
    alter table TFolder add lastUpdaterLoginName varchar(32);
    alter table TFolder add lastUpdaterName varchar(20);
    alter table TFolder add lastUpdaterIp varchar(20);
    
    update TFolder set creatorLoginName='sys_upgrade',creatorName='系统升级',creatorIp='0.0.0.0',lastUpdate='1900-01-01 01:01:01',lastUpdaterLoginName='sys_upgrade',lastUpdaterName='系统升级',lastUpdaterIp='0.0.0.0';
    
    -- TNotice
    alter table TNotice add creatorLoginName varchar(32);
    alter table TNotice add creatorName varchar(20);
    alter table TNotice add creatorIp  varchar(20);
    alter table TNotice add lastUpdate datetime;
    alter table TNotice add lastUpdaterLoginName varchar(32);
    alter table TNotice add lastUpdaterName varchar(20);
    alter table TNotice add lastUpdaterIp varchar(20);
    
    update TNotice set creatorLoginName='sys_upgrade',creatorName='系统升级',creatorIp='0.0.0.0',lastUpdate='1900-01-01 01:01:01',lastUpdaterLoginName='sys_upgrade',lastUpdaterName='系统升级',lastUpdaterIp='0.0.0.0';
    
    -- TNoticeAttachment
    alter table TNoticeAttachment add createTime datetime;
    alter table TNoticeAttachment add creatorLoginName varchar(32);
    alter table TNoticeAttachment add creatorName varchar(20);
    alter table TNoticeAttachment add creatorIp  varchar(20);
    alter table TNoticeAttachment add lastUpdate datetime;
    alter table TNoticeAttachment add lastUpdaterLoginName varchar(32);
    alter table TNoticeAttachment add lastUpdaterName varchar(20);
    alter table TNoticeAttachment add lastUpdaterIp varchar(20);

     update TNoticeAttachment set createTime='1900-01-01 01:01:01',creatorLoginName='sys_upgrade',creatorName='系统升级',creatorIp='0.0.0.0',lastUpdate='1900-01-01 01:01:01',lastUpdaterLoginName='sys_upgrade',lastUpdaterName='系统升级',lastUpdaterIp='0.0.0.0';
    
    -- TSuggestion
    alter table TSuggestion add createTime datetime;
    alter table TSuggestion add creatorLoginName varchar(32);
    alter table TSuggestion add creatorName varchar(20);
    alter table TSuggestion add creatorIp  varchar(20);
    alter table TSuggestion add lastUpdate datetime;
    alter table TSuggestion add lastUpdaterLoginName varchar(32);
    alter table TSuggestion add lastUpdaterName varchar(20);
    alter table TSuggestion add lastUpdaterIp varchar(20);
    
    update TSuggestion set createTime='1900-01-01 01:01:01',creatorLoginName='sys_upgrade',creatorName='系统升级',creatorIp='0.0.0.0',lastUpdate='1900-01-01 01:01:01',lastUpdaterLoginName='sys_upgrade',lastUpdaterName='系统升级',lastUpdaterIp='0.0.0.0';
    
    -- TReply
    alter table TReply add createTime datetime;
    alter table TReply add creatorLoginName varchar(32);
    alter table TReply add creatorName varchar(20);
    alter table TReply add creatorIp  varchar(20);
    alter table TReply add lastUpdate datetime;
    alter table TReply add lastUpdaterLoginName varchar(32);
    alter table TReply add lastUpdaterName varchar(20);
    alter table TReply add lastUpdaterIp varchar(20);
    
    update TReply set createTime='1900-01-01 01:01:01',creatorLoginName='sys_upgrade',creatorName='系统升级',creatorIp='0.0.0.0',lastUpdate='1900-01-01 01:01:01',lastUpdaterLoginName='sys_upgrade',lastUpdaterName='系统升级',lastUpdaterIp='0.0.0.0';
    
    -- Tuser
    alter table TUser add createTime datetime;
    alter table TUser add creatorLoginName varchar(32);
    alter table TUser add creatorName varchar(20);
    alter table TUser add creatorIp  varchar(20);
    alter table TUser add lastUpdate datetime;
    alter table TUser add lastUpdaterLoginName varchar(32);
    alter table TUser add lastUpdaterName varchar(20);
    alter table TUser add lastUpdaterIp varchar(20);
    
    update TUser set createTime='1900-01-01 01:01:01',creatorLoginName='sys_upgrade',creatorName='系统升级',creatorIp='0.0.0.0',lastUpdate='1900-01-01 01:01:01',lastUpdaterLoginName='sys_upgrade',lastUpdaterName='系统升级',lastUpdaterIp='0.0.0.0';
    
insert into TDatabaseChange(majorVersion,minorVersion,fixVersion,fileName) values(1,2,0,'1.2.sql');