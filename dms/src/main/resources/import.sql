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

--insert into TRole(name,displayName) values('ROLE_USER','普通用户');
insert into TRole(name,displayName) values('ROLE_ADMIN','超级管理员');
insert into TRole(name,displayName) values('ROLE_NOTICE_MANAGER','通知管理');
insert into TRole(name,displayName) values('ROLE_SUGGESTION_MANAGER','意见簿管理');
insert into TRole(name,displayName) values('ROLE_PUBLICNEWS_MANAGER','院务政务信息公开管理');
insert into TRole(name,displayName) values('ROLE_SOFTWARE_MANAGER','软件管理');

insert into TDepartment_TRole(departmentId,roleId) values(1,1);
insert into TDepartment_TRole(departmentId,roleId) values(1,2);
insert into TUser(loginName,password,departmentId) values('admin','admin',1);
insert into TDepartment_TRole(departmentId,roleId) values(2,2);
insert into TUser(loginName,password,departmentId) values('notice','1234',2);
insert into TDepartment_TRole(departmentId,roleId) values(3,3);
insert into TUser(loginName,password,departmentId) values('suggestion','1234',3);

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
