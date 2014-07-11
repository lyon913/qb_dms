-- 关闭安全更新模式（safe update mode）
set sql_safe_updates=0;	

-- 用户角色表
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
        
	-- 设置管理员的权限
	insert into tuser_trole(userid,roleid)  select u.id,r.id from tuser u,trole r where u.loginName='admin' and r.name='ROLE_ADMIN';
	
	-- 更新数据库变更记录
	insert into TDatabaseChange(majorVersion,minorVersion,fixVersion,fileName,opdate) values(1,4,0,'1.4.sql',now());

	-- 恢复安全更新
	set sql_safe_updates=1;