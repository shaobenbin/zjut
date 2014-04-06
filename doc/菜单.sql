insert into s_module(id,system_id,name,up_id,url,pattern,target,lvl,sts)
       values(seq_app.nextval,(select id from s_system where name='后台管理系统'),'学院管理',null,null,'/manager/college*','mainFrame',1,'Y');
insert into s_module(id,system_id,name,up_id,url,pattern,target,lvl,sts)
       values(seq_app.nextval,(select id from s_system where name='后台管理系统'),'国际学院',null,null,'/manager/ic/*','mainFrame',1,'Y');
insert into s_module(id,system_id,name,up_id,url,pattern,target,lvl,sts)
       values(seq_app.nextval,(select id from s_system where name='后台管理系统'),'学生组织',null,null,'/manager/student/*','mainFrame',1,'Y');

insert into s_module(id,system_id,name,up_id,url,pattern,target,lvl,sts)
       values(seq_app.nextval,(select id from s_system where name='后台管理系统'),'学院校园生活',
             (select id from s_module where name='学院管理'),
             'manager/college/schoollifes/list.html','manager/college/schoollifes/list.html',
             'mainFrame',2,'Y');
insert into s_module(id,system_id,name,up_id,url,pattern,target,lvl,sts)
       values(seq_app.nextval,(select id from s_system where name='后台管理系统'),'学院下载资源',
              (select id from s_module where name='学院管理'),
              'manager/college/resources/main.html','manager/college/resources/*',
              'mainFrame',2,'Y');
insert into s_module(id,system_id,name,up_id,url,pattern,target,lvl,sts)
       values(seq_app.nextval,(select id from s_system where name='后台管理系统'),'学院菜单内容',
              (select id from s_module where name='学院管理'),
              'manager/college/menucontents/list.html','manager/college/menucontents/*',
              'mainFrame',2,'Y');
insert into s_module(id,system_id,name,up_id,url,pattern,target,lvl,sts)
       values(seq_app.nextval,(select id from s_system where name='后台管理系统'),'学院公告',
              (select id from s_module where name='学院管理'),
              'manager/college/bulletins/list.html','manager/college/bulletins/*',
              'mainFrame',2,'Y');
insert into s_module(id,system_id,name,up_id,url,pattern,target,lvl,sts)
       values(seq_app.nextval,(select id from s_system where name='后台管理系统'),'学院新闻',
             (select id from s_module where name='学院管理'),
             'manager/college/news/list.html','/manager/college/news/*',
             'mainFrame',2,'Y');

insert into s_module(id,system_id,name,up_id,url,pattern,target,lvl,sts)
       values(seq_app.nextval,(select id from s_system where name='后台管理系统'),'国际学院图片新闻',
             (select id from s_module where name='国际学院'),
             'manager/ic/news/list.html?zjut_new_typeDomain.name=image','manager/ic/news/*',
             'mainFrame',2,'Y');
insert into s_module(id,system_id,name,up_id,url,pattern,target,lvl,sts)
       values(seq_app.nextval,(select id from s_system where name='后台管理系统'),'合作院校',
             (select id from s_module where name='国际学院'),
             'manager/partnerunis/list.html','manager/partnerunis/*',
             'mainFrame',2,'Y');
insert into s_module(id,system_id,name,up_id,url,pattern,target,lvl,sts)
       values(seq_app.nextval,(select id from s_system where name='后台管理系统'),'国际学院新闻',
             (select id from s_module where name='国际学院'),
             'manager/ic/news/list.html','manager/ic/news/*',
             'mainFrame',2,'Y');
insert into s_module(id,system_id,name,up_id,url,pattern,target,lvl,sts)
       values(seq_app.nextval,(select id from s_system where name='后台管理系统'),'国际学院菜单内容',
             (select id from s_module where name='国际学院'),
             'manager/ic/menucontents/main.html','manager/ic/menucontents/*',
             'mainFrame',2,'Y');
insert into s_module(id,system_id,name,up_id,url,pattern,target,lvl,sts)
       values(seq_app.nextval,(select id from s_system where name='后台管理系统'),'国际学院校园生活',
             (select id from s_module where name='国际学院'),
             'manager/ic/schoollifes/list.html','manager/ic/schoollifes/*',
             'mainFrame',2,'Y');
insert into s_module(id,system_id,name,up_id,url,pattern,target,lvl,sts)
       values(seq_app.nextval,(select id from s_system where name='后台管理系统'),'国际学院公告',
             (select id from s_module where name='国际学院'),
             'manager/ic/bulletins/list.html','manager/ic/bulletins/*',
             'mainFrame',2,'Y');
insert into s_module(id,system_id,name,up_id,url,pattern,target,lvl,sts)
       values(seq_app.nextval,(select id from s_system where name='后台管理系统'),'国际学院下载资源',
             (select id from s_module where name='国际学院'),
             'manager/ic/resources/main.html','manager/ic/resources/*',
             'mainFrame',2,'Y');
             
insert into s_module(id,system_id,name,up_id,url,pattern,target,lvl,sts)
       values(seq_app.nextval,(select id from s_system where name='后台管理系统'),'学生风采',
             (select id from s_module where name='学生组织'),
             'manager/studentstyle/list.html','manager/studentstyle/*',
             'mainFrame',2,'Y');
insert into s_module(id,system_id,name,up_id,url,pattern,target,lvl,sts)
       values(seq_app.nextval,(select id from s_system where name='后台管理系统'),'党建动态',
             (select id from s_module where name='学生组织'),
             'manager/student/partydynamic/list.html','manager/student/partydynamic/*',
             'mainFrame',2,'Y');
insert into s_module(id,system_id,name,up_id,url,pattern,target,lvl,sts)
       values(seq_app.nextval,(select id from s_system where name='后台管理系统'),'奖勤助贷',
             (select id from s_module where name='学生组织'),
             '','manager/student/*',
             'mainFrame',2,'Y');
insert into s_module(id,system_id,name,up_id,url,pattern,target,lvl,sts)
       values(seq_app.nextval,(select id from s_system where name='后台管理系统'),'奖',
             (select id from s_module where name='学生组织'),
             'manager/student/jiang/list.html','manager/student/jiang/*',
             'mainFrame',3,'Y');
insert into s_module(id,system_id,name,up_id,url,pattern,target,lvl,sts)
       values(seq_app.nextval,(select id from s_system where name='后台管理系统'),'勤',
             (select id from s_module where name='学生组织'),
             'manager/student/qin/list.html','manager/student/qin/*',
             'mainFrame',3,'Y');
insert into s_module(id,system_id,name,up_id,url,pattern,target,lvl,sts)
       values(seq_app.nextval,(select id from s_system where name='后台管理系统'),'助',
             (select id from s_module where name='学生组织'),
             'manager/student/zhu/list.html','manager/student/zhu/*',
             'mainFrame',3,'Y');
insert into s_module(id,system_id,name,up_id,url,pattern,target,lvl,sts)
       values(seq_app.nextval,(select id from s_system where name='后台管理系统'),'贷',
             (select id from s_module where name='学生组织'),
             'manager/student/dai/list.html','manager/student/dai/*',
             'mainFrame',3,'Y');
insert into s_module(id,system_id,name,up_id,url,pattern,target,lvl,sts)
       values(seq_app.nextval,(select id from s_system where name='后台管理系统'),'就业',
             (select id from s_module where name='学生组织'),
             '','manager/student/*',
             'mainFrame',2,'Y');
insert into s_module(id,system_id,name,up_id,url,pattern,target,lvl,sts)
       values(seq_app.nextval,(select id from s_system where name='后台管理系统'),'就业政策',
             (select id from s_module where name='学生组织'),
             'manager/student/employmentpolicy/list.html','manager/student/employmentpolicy/*',
             'mainFrame',3,'Y');
insert into s_module(id,system_id,name,up_id,url,pattern,target,lvl,sts)
       values(seq_app.nextval,(select id from s_system where name='后台管理系统'),'招聘信息',
             (select id from s_module where name='学生组织'),
             'manager/student/recruitmentinfo/list.html','manager/student/recruitmentinfo/*',
             'mainFrame',3,'Y');
insert into s_module(id,system_id,name,up_id,url,pattern,target,lvl,sts)
       values(seq_app.nextval,(select id from s_system where name='后台管理系统'),'创业基地',
             (select id from s_module where name='学生组织'),
             'manager/student/entrepreneurialbase/list.html','manager/student/entrepreneurialbase/*',
             'mainFrame',3,'Y');
insert into s_module(id,system_id,name,up_id,url,pattern,target,lvl,sts)
       values(seq_app.nextval,(select id from s_system where name='后台管理系统'),'科技创新',
             (select id from s_module where name='学生组织'),
             '','manager/student/*',
             'mainFrame',2,'Y');
insert into s_module(id,system_id,name,up_id,url,pattern,target,lvl,sts)
       values(seq_app.nextval,(select id from s_system where name='后台管理系统'),'奖励办法',
             (select id from s_module where name='学生组织'),
             'manager/student/rewardsolution/list.html','manager/student/rewardsolution/*',
             'mainFrame',3,'Y');
insert into s_module(id,system_id,name,up_id,url,pattern,target,lvl,sts)
       values(seq_app.nextval,(select id from s_system where name='后台管理系统'),'历年获奖',
             (select id from s_module where name='学生组织'),
             'manager/student/winprizehistory/list.html','manager/student/winprizehistory/*',
             'mainFrame',3,'Y');
insert into s_module(id,system_id,name,up_id,url,pattern,target,lvl,sts)
       values(seq_app.nextval,(select id from s_system where name='后台管理系统'),'赛事简介',
             (select id from s_module where name='学生组织'),
             'manager/student/events/list.html','manager/student/events/*',
             'mainFrame',3,'Y');
insert into s_module(id,system_id,name,up_id,url,pattern,target,lvl,sts)
       values(seq_app.nextval,(select id from s_system where name='后台管理系统'),'通知公告',
             (select id from s_module where name='学生组织'),
             'manager/student/technologynotice/list.html','manager/student/technologynotice/*',
             'mainFrame',3,'Y');
insert into s_module(id,system_id,name,up_id,url,pattern,target,lvl,sts)
       values(seq_app.nextval,(select id from s_system where name='后台管理系统'),'学生校园生活',
             (select id from s_module where name='学生组织'),
             'manager/student/schoollifes/list.html','manager/student/schoollifes/*',
             'mainFrame',2,'Y');
insert into s_module(id,system_id,name,up_id,url,pattern,target,lvl,sts)
       values(seq_app.nextval,(select id from s_system where name='后台管理系统'),'下载资源',
             (select id from s_module where name='学生组织'),
             'manager/college/resources/main.html','manager/college/resources/*',
             'mainFrame',2,'Y');
insert into s_module(id,system_id,name,up_id,url,pattern,target,lvl,sts)
       values(seq_app.nextval,(select id from s_system where name='后台管理系统'),'学生公告',
             (select id from s_module where name='学生组织'),
             'manager/student/bulletins/list.html','/manager/student/bulletins/*',
             'mainFrame',2,'Y');
insert into s_module(id,system_id,name,up_id,url,pattern,target,lvl,sts)
       values(seq_app.nextval,(select id from s_system where name='后台管理系统'),'学生新闻',
             (select id from s_module where name='学生组织'),
             'manager/student/news/list.html','/manager/student/news/*',
             'mainFrame',2,'Y');
insert into s_module(id,system_id,name,up_id,url,pattern,target,lvl,sts)
       values(seq_app.nextval,(select id from s_system where name='后台管理系统'),'学生菜单内容',
             (select id from s_module where name='学生组织'),
             'manager/student/menucontents/list.html','manager/student/menucontents/*',
             'mainFrame',2,'Y');
             
insert into s_rbac_rolemodule 
       values(seq_app.nextval,(select id from s_rbac_role where name='管理员'),(select id from s_module where name='学院管理'));
insert into s_rbac_rolemodule 
       values(seq_app.nextval,(select id from s_rbac_role where name='管理员'),(select id from s_module where name='国际学院图片新闻'));
insert into s_rbac_rolemodule 
       values(seq_app.nextval,(select id from s_rbac_role where name='管理员'),(select id from s_module where name='学生风采'));
insert into s_rbac_rolemodule 
       values(seq_app.nextval,(select id from s_rbac_role where name='管理员'),(select id from s_module where name='党建动态'));
insert into s_rbac_rolemodule 
       values(seq_app.nextval,(select id from s_rbac_role where name='管理员'),(select id from s_module where name='奖勤助贷'));
insert into s_rbac_rolemodule 
       values(seq_app.nextval,(select id from s_rbac_role where name='管理员'),(select id from s_module where name='奖'));
insert into s_rbac_rolemodule 
       values(seq_app.nextval,(select id from s_rbac_role where name='管理员'),(select id from s_module where name='贷'));
insert into s_rbac_rolemodule 
       values(seq_app.nextval,(select id from s_rbac_role where name='管理员'),(select id from s_module where name='就业'));
insert into s_rbac_rolemodule 
       values(seq_app.nextval,(select id from s_rbac_role where name='管理员'),(select id from s_module where name='就业政策'));
insert into s_rbac_rolemodule 
       values(seq_app.nextval,(select id from s_rbac_role where name='管理员'),(select id from s_module where name='招聘信息'));
insert into s_rbac_rolemodule 
       values(seq_app.nextval,(select id from s_rbac_role where name='管理员'),(select id from s_module where name='科技创新'));
insert into s_rbac_rolemodule 
       values(seq_app.nextval,(select id from s_rbac_role where name='管理员'),(select id from s_module where name='奖励办法'));
insert into s_rbac_rolemodule 
       values(seq_app.nextval,(select id from s_rbac_role where name='管理员'),(select id from s_module where name='创业基地'));
insert into s_rbac_rolemodule 
       values(seq_app.nextval,(select id from s_rbac_role where name='管理员'),(select id from s_module where name='学院校园生活'));
insert into s_rbac_rolemodule 
       values(seq_app.nextval,(select id from s_rbac_role where name='管理员'),(select id from s_module where name='合作院校'));
insert into s_rbac_rolemodule 
       values(seq_app.nextval,(select id from s_rbac_role where name='管理员'),(select id from s_module where name='国际学院新闻'));
insert into s_rbac_rolemodule 
       values(seq_app.nextval,(select id from s_rbac_role where name='管理员'),(select id from s_module where name='学生校园生活'));
insert into s_rbac_rolemodule 
       values(seq_app.nextval,(select id from s_rbac_role where name='管理员'),(select id from s_module where name='网站配置'));
insert into s_rbac_rolemodule 
       values(seq_app.nextval,(select id from s_rbac_role where name='管理员'),(select id from s_module where name='组织机构'));
insert into s_rbac_rolemodule 
       values(seq_app.nextval,(select id from s_rbac_role where name='管理员'),(select id from s_module where name='用户管理'));
insert into s_rbac_rolemodule 
       values(seq_app.nextval,(select id from s_rbac_role where name='管理员'),(select id from s_module where name='模块管理'));
insert into s_rbac_rolemodule 
       values(seq_app.nextval,(select id from s_rbac_role where name='管理员'),(select id from s_module where name='模块'));
insert into s_rbac_rolemodule 
       values(seq_app.nextval,(select id from s_rbac_role where name='管理员'),(select id from s_module where name='系统管理'));
insert into s_rbac_rolemodule 
       values(seq_app.nextval,(select id from s_rbac_role where name='管理员'),(select id from s_module where name='系统'));
insert into s_rbac_rolemodule 
       values(seq_app.nextval,(select id from s_rbac_role where name='管理员'),(select id from s_module where name='下载资源'));
insert into s_rbac_rolemodule 
       values(seq_app.nextval,(select id from s_rbac_role where name='管理员'),(select id from s_module where name='学院下载资源'));
insert into s_rbac_rolemodule 
       values(seq_app.nextval,(select id from s_rbac_role where name='管理员'),(select id from s_module where name='国际学院菜单内容'));
insert into s_rbac_rolemodule 
       values(seq_app.nextval,(select id from s_rbac_role where name='管理员'),(select id from s_module where name='学院菜单内容'));
insert into s_rbac_rolemodule 
       values(seq_app.nextval,(select id from s_rbac_role where name='管理员'),(select id from s_module where name='国际学院校园生活'));
insert into s_rbac_rolemodule 
       values(seq_app.nextval,(select id from s_rbac_role where name='管理员'),(select id from s_module where name='历年获奖'));
       
insert into s_rbac_rolemodule 
       values(seq_app.nextval,(select id from s_rbac_role where name='管理员'),(select id from s_module where name='权限管理'));
       
insert into s_rbac_rolemodule 
       values(seq_app.nextval,(select id from s_rbac_role where name='管理员'),(select id from s_module where name='角色'));
insert into s_rbac_rolemodule 
       values(seq_app.nextval,(select id from s_rbac_role where name='管理员'),(select id from s_module where name='学生公告'));
insert into s_rbac_rolemodule 
       values(seq_app.nextval,(select id from s_rbac_role where name='管理员'),(select id from s_module where name='国际学院'));
insert into s_rbac_rolemodule 
       values(seq_app.nextval,(select id from s_rbac_role where name='管理员'),(select id from s_module where name='学生组织'));
insert into s_rbac_rolemodule 
       values(seq_app.nextval,(select id from s_rbac_role where name='管理员'),(select id from s_module where name='学生新闻'));
insert into s_rbac_rolemodule 
       values(seq_app.nextval,(select id from s_rbac_role where name='管理员'),(select id from s_module where name='国际学院公告'));
insert into s_rbac_rolemodule 
       values(seq_app.nextval,(select id from s_rbac_role where name='管理员'),(select id from s_module where name='学院公告'));
insert into s_rbac_rolemodule 
       values(seq_app.nextval,(select id from s_rbac_role where name='管理员'),(select id from s_module where name='国际学院下载资源'));
insert into s_rbac_rolemodule 
       values(seq_app.nextval,(select id from s_rbac_role where name='管理员'),(select id from s_module where name='学生菜单内容'));
insert into s_rbac_rolemodule 
       values(seq_app.nextval,(select id from s_rbac_role where name='管理员'),(select id from s_module where name='勤'));
insert into s_rbac_rolemodule 
       values(seq_app.nextval,(select id from s_rbac_role where name='管理员'),(select id from s_module where name='学院新闻'));
insert into s_rbac_rolemodule 
       values(seq_app.nextval,(select id from s_rbac_role where name='管理员'),(select id from s_module where name='助'));
insert into s_rbac_rolemodule 
       values(seq_app.nextval,(select id from s_rbac_role where name='管理员'),(select id from s_module where name='赛事简介'));
insert into s_rbac_rolemodule 
       values(seq_app.nextval,(select id from s_rbac_role where name='管理员'),(select id from s_module where name='通知公告'));
commit;
