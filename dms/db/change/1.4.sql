-- 关闭安全更新模式（safe update mode）
set sql_safe_updates=0;	

-- 用户角色表
create table TUserRole (
	id bigint not null auto_increment,
	userId bigint not null,
	role varchar(50) not null,
	primary key(id)
);

    alter table TUserRole 
        add index IDX_TUSERROLE_USERID (userId), 
        add constraint FK_TUSERROLE_USERID
        foreign key (userId) 
        references TUser (id);

    alter table TUserRole 
        add index IDX_TUSER_TROLE_ROLE (role);
        
	-- 设置管理员的权限
	insert into tuserrole(userid,role)  select u.id,'ROLE_ADMIN' from tuser u where u.loginName='admin';
	
	-- 更新数据库变更记录
	insert into TDatabaseChange(majorVersion,minorVersion,fixVersion,fileName,opdate) values(1,4,0,'1.4.sql',now());

	-- 恢复安全更新
	set sql_safe_updates=1;