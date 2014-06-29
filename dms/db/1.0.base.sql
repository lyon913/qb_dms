    

create table TDepartment (
        id bigint not null auto_increment,
        head varchar(50),
        name varchar(50) not null,
        telephone varchar(50),
        primary key (id)
    );
    create table TDepartment_TRole (
        departmentId bigint not null,
        roleId bigint not null
    );
    create table TFile (
        id bigint not null auto_increment,
        author varchar(50),
        authorId bigint,
        createTime datetime not null,
        description varchar(500),
        filePath varchar(1000) not null,
        name varchar(255) not null,
        parentId bigint not null,
        size bigint not null,
        primary key (id)
    );
    create table TFile_TDepartment (
        id bigint not null auto_increment,
        departmentId bigint,
        fileId bigint,
        primary key (id)
    ); 
    create table TFolder (
        id bigint not null auto_increment,
        author varchar(50),
        createTime datetime not null,
        description varchar(500),
        name varchar(255) not null,
        parentId bigint not null,
        primary key (id)
    );
    create table TFunction (
        id bigint not null auto_increment,
        description varchar(500),
        displayName varchar(100) not null,
        displayOrder integer,
        entryUrl varchar(500),
        name varchar(50) not null,
        type varchar(255) not null,
        primary key (id)
    );
    create table TGroup (
        id bigint not null auto_increment,
        description varchar(100),
        name varchar(50) not null,
        primary key (id)
    );
    create table TNotice (
        id bigint not null auto_increment,
        author varchar(50),
        content longtext,
        createTime datetime,
        noticeDate date,
        noticetypeId bigint,
        parentNoticeTypeId bigint,
        publishDate datetime,
        published boolean not null,
        title varchar(250) not null,
        primary key (id)
    );
    create table TNoticeAttachment (
        id bigint not null auto_increment,
        name varchar(255),
        noticeId bigint,
        path varchar(255),
        size bigint,
        primary key (id)
    );
    create table TNoticeType (
        id bigint not null auto_increment,
        noticememo varchar(500),
        noticeorder bigint,
        noticetype varchar(100) not null,
        parentId bigint,
        primary key (id)
    );
    create table TNotice_TDepartment (
        id bigint not null auto_increment,
        departmentId bigint,
        noticeId bigint,
        primary key (id)
    );
    create table TReply (
        id bigint not null auto_increment,
        authorId bigint not null,
        replyContent varchar(5000) not null,
        replyDate datetime not null,
        suggestionId bigint,
        primary key (id)
    );
    create table TRole (
        id bigint not null auto_increment,
        displayName varchar(255) not null,
        name varchar(255) not null,
        primary key (id)
    );
    create table TSoftware (
        id bigint not null auto_increment,
        author varchar(50),
        createTime datetime not null,
        description varchar(500),
        filePath varchar(1000) not null,
        name varchar(255) not null,
        parentId bigint not null,
        size bigint not null,
        primary key (id)
    );
    create table TSuggestion (
        id bigint not null auto_increment,
        authorId bigint,
        suggestionContent varchar(5000) not null,
        suggestionDate datetime not null,
        suggestionTitle varchar(255) not null,
        primary key (id)
    );
    create table TUser (
        id bigint not null auto_increment,
        loginName varchar(32) not null unique,
        name varchar(20),
        password varchar(16) not null,
        departmentId bigint,
        primary key (id)
    );
    
    create table TDatabaseChange(
    	id bigint not null auto_increment primary  key,
    	majorVersion int,
    	minorVersion int,
    	fixVersion int,
    	fileName varchar(100)
    );
    
    alter table TDepartment_TRole 
        add index FKAFB32831640855ED (departmentId), 
        add constraint FKAFB32831640855ED 
        foreign key (departmentId) 
        references TDepartment (id);

    alter table TDepartment_TRole 
        add index FKAFB32831911031B5 (roleId), 
        add constraint FKAFB32831911031B5 
        foreign key (roleId) 
        references TRole (id);

    alter table TUser 
        add index FK4C8163F640855ED (departmentId), 
        add constraint FK4C8163F640855ED 
        foreign key (departmentId) 
        references TDepartment (id);
        
        
insert into TDepartment(name) values ('信息科');
 insert into TDepartment(name) values ('党总支');
insert into TDepartment(name) values ('行政党支部');
insert into TDepartment(name) values ('门诊党支部');
insert into TDepartment(name) values ('内儿党支部');
insert into TDepartment(name) values ('术科系统党支部');
insert into TDepartment(name) values ('工会委员会');
insert into TDepartment(name) values ('共青团');
insert into TDepartment(name) values ('妇女工作委员会');
insert into TDepartment(name) values ('院办');
insert into TDepartment(name) values ('医务科');
insert into TDepartment(name) values ('护理部');
insert into TDepartment(name) values ('感控办');
insert into TDepartment(name) values ('审计科');
insert into TDepartment(name) values ('财务科');
insert into TDepartment(name) values ('总务科');
insert into TDepartment(name) values ('保卫科');
insert into TDepartment(name) values ('急诊科');
insert into TDepartment(name) values ('呼吸消化内科');
insert into TDepartment(name) values ('心血管神经内科');
insert into TDepartment(name) values ('儿科');
insert into TDepartment(name) values ('感染科');
insert into TDepartment(name) values ('骨科');
insert into TDepartment(name) values ('普外科');
insert into TDepartment(name) values ('泌尿神经外科');
insert into TDepartment(name) values ('手术麻醉科');
insert into TDepartment(name) values ('妇产科');
insert into TDepartment(name) values ('五官科');
insert into TDepartment(name) values ('口腔科');
insert into TDepartment(name) values ('中医科');
insert into TDepartment(name) values ('皮肤科');
insert into TDepartment(name) values ('防保科');
insert into TDepartment(name) values ('供应室');
insert into TDepartment(name) values ('检验科');
insert into TDepartment(name) values ('放射科');
insert into TDepartment(name) values ('功能科');
insert into TDepartment(name) values ('胃镜室');
insert into TDepartment(name) values ('心电图室');
insert into TDepartment(name) values ('药剂科');
insert into TDepartment(name) values ('门诊药房');
insert into TDepartment(name) values ('住院部药房');
insert into TDepartment(name) values ('中药房');

insert into TRole(name,displayName) values('ROLE_ADMIN','超级管理员');
insert into TRole(name,displayName) values('ROLE_NOTICE_MANAGER','通知管理');
insert into TRole(name,displayName) values('ROLE_SUGGESTION_MANAGER','意见簿管理');
insert into TRole(name,displayName) values('ROLE_PUBLICNEWS_MANAGER','院务政务信息公开管理');
insert into TRole(name,displayName) values('ROLE_SOFTWARE_MANAGER','软件管理');

insert into TDepartment_TRole(departmentId,roleId) values(1,1);
insert into TUser(loginName,password,departmentId) values('admin','admin',1);

insert into TNoticeType(noticetype,noticememo,noticeorder,parentId)values('通知公告','',1,0);
insert into TNoticeType(noticetype,noticememo,noticeorder,parentId)values('院务、政务公开','',1,0);
insert into TNoticeType(noticetype,noticememo,noticeorder,parentId)values('党务','',1,1);
insert into TNoticeType(noticetype,noticememo,noticeorder,parentId)values('行政','',2,1);
insert into TNoticeType(noticetype,noticememo,noticeorder,parentId)values('部门','',3,1);
insert into TNoticeType(noticetype,noticememo,noticeorder,parentId)values('其他','',4,1);
insert into TNoticeType(noticetype,noticememo,noticeorder,parentId)values('职工薪级工资变动情况','',1,2);
insert into TNoticeType(noticetype,noticememo,noticeorder,parentId)values('职工福利待遇情况','',1,2);
insert into TNoticeType(noticetype,noticememo,noticeorder,parentId)values('职工岗位结构情况','',1,2);
insert into TNoticeType(noticetype,noticememo,noticeorder,parentId)values('职工岗位设置情况','',1,2);
insert into TNoticeType(noticetype,noticememo,noticeorder,parentId)values('职工岗位竞聘情况','',1,2);
insert into TNoticeType(noticetype,noticememo,noticeorder,parentId)values('人事任免','',1,2);
insert into TNoticeType(noticetype,noticememo,noticeorder,parentId)values('奖励','',1,2);
insert into TNoticeType(noticetype,noticememo,noticeorder,parentId)values('惩罚','',1,2);



insert into TFolder(name,description,author,createTime,parentId) values('文件','root','admin',now(),-1);
insert into TFolder(name,description,author,createTime,parentId) values('软件','root','admin',now(),-2);
insert into TFolder(name,description,author,createTime,parentId) values('院规、院纪','院规、院纪','admin',now(),1);
insert into TFolder(name,description,author,createTime,parentId) values('计划、总结','root','计划、总结',now(),1);
insert into TFolder(name,description,author,createTime,parentId) values('晋升、晋级相关文件','晋升、晋级相关文件','admin',now(),1);
insert into TFolder(name,description,author,createTime,parentId) values('婚、丧、产等假期规定','婚、丧、产等假期规定','admin',now(),1);
insert into TFolder(name,description,author,createTime,parentId) values('办公软件','办公软件','admin',now(),2);
insert into TFolder(name,description,author,createTime,parentId) values('工具软件','工具软件','admin',now(),2);

insert into TDatabaseChange(majorVersion,minorVersion,fixVersion,fileName) values(1,0,0,'1.0.base.sql');