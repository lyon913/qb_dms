-- 关闭安全更新模式（safe update mode）
set sql_safe_updates=0;	

-- 公共附件表
create table TUser_TRole (
	userId bigint not null,
	roleId bigint not null
);

    alter table TUser_TRole 
        add index IDX_TUSER_TROLE_USERID (userId), 
        add constraint FK_TUSER_TROLE_USERID
        foreign key (userId) 
        references TUser (id);

    alter table TUser_TRole 
        add index IDX_TUSER_TROLE_ROLEID (roleId), 
        add constraint FK_TUSER_TROLE_ROLEID 
        foreign key (roleId)
        references TRole (id);
        
    -- 更改数据库及全部表的字符集
    ALTER DATABASE dms DEFAULT CHARACTER SET gbk COLLATE gbk_chinese_ci;
    
    ALTER TABLE tattachment CONVERT TO CHARACTER SET gbk COLLATE gbk_chinese_ci;
	ALTER TABLE tdatabasechange CONVERT TO CHARACTER SET gbk COLLATE gbk_chinese_ci;
	ALTER TABLE tdepartment CONVERT TO CHARACTER SET gbk COLLATE gbk_chinese_ci;
	ALTER TABLE tdepartment_trole CONVERT TO CHARACTER SET gbk COLLATE gbk_chinese_ci;
	ALTER TABLE tfile CONVERT TO CHARACTER SET gbk COLLATE gbk_chinese_ci;
	ALTER TABLE tfile_tdepartment CONVERT TO CHARACTER SET gbk COLLATE gbk_chinese_ci;
	ALTER TABLE tfolder CONVERT TO CHARACTER SET gbk COLLATE gbk_chinese_ci;
	ALTER TABLE tfunction CONVERT TO CHARACTER SET gbk COLLATE gbk_chinese_ci;
	ALTER TABLE tgroup CONVERT TO CHARACTER SET gbk COLLATE gbk_chinese_ci;
	ALTER TABLE tloginaudit CONVERT TO CHARACTER SET gbk COLLATE gbk_chinese_ci;
	ALTER TABLE tnotice CONVERT TO CHARACTER SET gbk COLLATE gbk_chinese_ci;
	ALTER TABLE tnotice_tdepartment CONVERT TO CHARACTER SET gbk COLLATE gbk_chinese_ci;
	ALTER TABLE tnoticeattachment CONVERT TO CHARACTER SET gbk COLLATE gbk_chinese_ci;
	ALTER TABLE tnoticetype CONVERT TO CHARACTER SET gbk COLLATE gbk_chinese_ci;
	ALTER TABLE treply CONVERT TO CHARACTER SET gbk COLLATE gbk_chinese_ci;
	ALTER TABLE trole CONVERT TO CHARACTER SET gbk COLLATE gbk_chinese_ci;
	ALTER TABLE tsoftware CONVERT TO CHARACTER SET gbk COLLATE gbk_chinese_ci;
	ALTER TABLE tsuggestion CONVERT TO CHARACTER SET gbk COLLATE gbk_chinese_ci;
	ALTER TABLE tuser CONVERT TO CHARACTER SET gbk COLLATE gbk_chinese_ci;
	ALTER TABLE tuser_trole CONVERT TO CHARACTER SET gbk COLLATE gbk_chinese_ci;

	-- 设置管理员的权限
	insert into tuser_trole(userid,roleid)  select u.id,r.id from tuser u,trole r where u.loginName='admin' and r.name='ROLE_ADMIN';
	
	-- 更新数据库变更记录
	insert into TDatabaseChange(majorVersion,minorVersion,fixVersion,fileName,opdate) values(1,4,0,'1.4.sql',now());

	-- 恢复安全更新
	set sql_safe_updates=1;