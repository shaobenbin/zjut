insert into s_module(id,system_id,name,up_id,url,pattern,target,lvl,sts)
       values(seq_app.nextval,(select id from s_system where name='��̨����ϵͳ'),'ѧԺ����',null,null,'/manager/college*','mainFrame',1,'Y');
insert into s_module(id,system_id,name,up_id,url,pattern,target,lvl,sts)
       values(seq_app.nextval,(select id from s_system where name='��̨����ϵͳ'),'����ѧԺ',null,null,'/manager/ic/*','mainFrame',1,'Y');
insert into s_module(id,system_id,name,up_id,url,pattern,target,lvl,sts)
       values(seq_app.nextval,(select id from s_system where name='��̨����ϵͳ'),'ѧ����֯',null,null,'/manager/student/*','mainFrame',1,'Y');

insert into s_module(id,system_id,name,up_id,url,pattern,target,lvl,sts)
       values(seq_app.nextval,(select id from s_system where name='��̨����ϵͳ'),'ѧԺУ԰����',
             (select id from s_module where name='ѧԺ����'),
             'manager/college/schoollifes/list.html','manager/college/schoollifes/list.html',
             'mainFrame',2,'Y');
insert into s_module(id,system_id,name,up_id,url,pattern,target,lvl,sts)
       values(seq_app.nextval,(select id from s_system where name='��̨����ϵͳ'),'ѧԺ������Դ',
              (select id from s_module where name='ѧԺ����'),
              'manager/college/resources/main.html','manager/college/resources/*',
              'mainFrame',2,'Y');
insert into s_module(id,system_id,name,up_id,url,pattern,target,lvl,sts)
       values(seq_app.nextval,(select id from s_system where name='��̨����ϵͳ'),'ѧԺ�˵�����',
              (select id from s_module where name='ѧԺ����'),
              'manager/college/menucontents/list.html','manager/college/menucontents/*',
              'mainFrame',2,'Y');
insert into s_module(id,system_id,name,up_id,url,pattern,target,lvl,sts)
       values(seq_app.nextval,(select id from s_system where name='��̨����ϵͳ'),'ѧԺ����',
              (select id from s_module where name='ѧԺ����'),
              'manager/college/bulletins/list.html','manager/college/bulletins/*',
              'mainFrame',2,'Y');
insert into s_module(id,system_id,name,up_id,url,pattern,target,lvl,sts)
       values(seq_app.nextval,(select id from s_system where name='��̨����ϵͳ'),'ѧԺ����',
             (select id from s_module where name='ѧԺ����'),
             'manager/college/news/list.html','/manager/college/news/*',
             'mainFrame',2,'Y');

insert into s_module(id,system_id,name,up_id,url,pattern,target,lvl,sts)
       values(seq_app.nextval,(select id from s_system where name='��̨����ϵͳ'),'����ѧԺͼƬ����',
             (select id from s_module where name='����ѧԺ'),
             'manager/ic/news/list.html?zjut_new_typeDomain.name=image','manager/ic/news/*',
             'mainFrame',2,'Y');
insert into s_module(id,system_id,name,up_id,url,pattern,target,lvl,sts)
       values(seq_app.nextval,(select id from s_system where name='��̨����ϵͳ'),'����ԺУ',
             (select id from s_module where name='����ѧԺ'),
             'manager/partnerunis/list.html','manager/partnerunis/*',
             'mainFrame',2,'Y');
insert into s_module(id,system_id,name,up_id,url,pattern,target,lvl,sts)
       values(seq_app.nextval,(select id from s_system where name='��̨����ϵͳ'),'����ѧԺ����',
             (select id from s_module where name='����ѧԺ'),
             'manager/ic/news/list.html','manager/ic/news/*',
             'mainFrame',2,'Y');
insert into s_module(id,system_id,name,up_id,url,pattern,target,lvl,sts)
       values(seq_app.nextval,(select id from s_system where name='��̨����ϵͳ'),'����ѧԺ�˵�����',
             (select id from s_module where name='����ѧԺ'),
             'manager/ic/menucontents/main.html','manager/ic/menucontents/*',
             'mainFrame',2,'Y');
insert into s_module(id,system_id,name,up_id,url,pattern,target,lvl,sts)
       values(seq_app.nextval,(select id from s_system where name='��̨����ϵͳ'),'����ѧԺУ԰����',
             (select id from s_module where name='����ѧԺ'),
             'manager/ic/schoollifes/list.html','manager/ic/schoollifes/*',
             'mainFrame',2,'Y');
insert into s_module(id,system_id,name,up_id,url,pattern,target,lvl,sts)
       values(seq_app.nextval,(select id from s_system where name='��̨����ϵͳ'),'����ѧԺ����',
             (select id from s_module where name='����ѧԺ'),
             'manager/ic/bulletins/list.html','manager/ic/bulletins/*',
             'mainFrame',2,'Y');
insert into s_module(id,system_id,name,up_id,url,pattern,target,lvl,sts)
       values(seq_app.nextval,(select id from s_system where name='��̨����ϵͳ'),'����ѧԺ������Դ',
             (select id from s_module where name='����ѧԺ'),
             'manager/ic/resources/main.html','manager/ic/resources/*',
             'mainFrame',2,'Y');
             
insert into s_module(id,system_id,name,up_id,url,pattern,target,lvl,sts)
       values(seq_app.nextval,(select id from s_system where name='��̨����ϵͳ'),'ѧ�����',
             (select id from s_module where name='ѧ����֯'),
             'manager/studentstyle/list.html','manager/studentstyle/*',
             'mainFrame',2,'Y');
insert into s_module(id,system_id,name,up_id,url,pattern,target,lvl,sts)
       values(seq_app.nextval,(select id from s_system where name='��̨����ϵͳ'),'������̬',
             (select id from s_module where name='ѧ����֯'),
             'manager/student/partydynamic/list.html','manager/student/partydynamic/*',
             'mainFrame',2,'Y');
insert into s_module(id,system_id,name,up_id,url,pattern,target,lvl,sts)
       values(seq_app.nextval,(select id from s_system where name='��̨����ϵͳ'),'��������',
             (select id from s_module where name='ѧ����֯'),
             '','manager/student/*',
             'mainFrame',2,'Y');
insert into s_module(id,system_id,name,up_id,url,pattern,target,lvl,sts)
       values(seq_app.nextval,(select id from s_system where name='��̨����ϵͳ'),'��',
             (select id from s_module where name='ѧ����֯'),
             'manager/student/jiang/list.html','manager/student/jiang/*',
             'mainFrame',3,'Y');
insert into s_module(id,system_id,name,up_id,url,pattern,target,lvl,sts)
       values(seq_app.nextval,(select id from s_system where name='��̨����ϵͳ'),'��',
             (select id from s_module where name='ѧ����֯'),
             'manager/student/qin/list.html','manager/student/qin/*',
             'mainFrame',3,'Y');
insert into s_module(id,system_id,name,up_id,url,pattern,target,lvl,sts)
       values(seq_app.nextval,(select id from s_system where name='��̨����ϵͳ'),'��',
             (select id from s_module where name='ѧ����֯'),
             'manager/student/zhu/list.html','manager/student/zhu/*',
             'mainFrame',3,'Y');
insert into s_module(id,system_id,name,up_id,url,pattern,target,lvl,sts)
       values(seq_app.nextval,(select id from s_system where name='��̨����ϵͳ'),'��',
             (select id from s_module where name='ѧ����֯'),
             'manager/student/dai/list.html','manager/student/dai/*',
             'mainFrame',3,'Y');
insert into s_module(id,system_id,name,up_id,url,pattern,target,lvl,sts)
       values(seq_app.nextval,(select id from s_system where name='��̨����ϵͳ'),'��ҵ',
             (select id from s_module where name='ѧ����֯'),
             '','manager/student/*',
             'mainFrame',2,'Y');
insert into s_module(id,system_id,name,up_id,url,pattern,target,lvl,sts)
       values(seq_app.nextval,(select id from s_system where name='��̨����ϵͳ'),'��ҵ����',
             (select id from s_module where name='ѧ����֯'),
             'manager/student/employmentpolicy/list.html','manager/student/employmentpolicy/*',
             'mainFrame',3,'Y');
insert into s_module(id,system_id,name,up_id,url,pattern,target,lvl,sts)
       values(seq_app.nextval,(select id from s_system where name='��̨����ϵͳ'),'��Ƹ��Ϣ',
             (select id from s_module where name='ѧ����֯'),
             'manager/student/recruitmentinfo/list.html','manager/student/recruitmentinfo/*',
             'mainFrame',3,'Y');
insert into s_module(id,system_id,name,up_id,url,pattern,target,lvl,sts)
       values(seq_app.nextval,(select id from s_system where name='��̨����ϵͳ'),'��ҵ����',
             (select id from s_module where name='ѧ����֯'),
             'manager/student/entrepreneurialbase/list.html','manager/student/entrepreneurialbase/*',
             'mainFrame',3,'Y');
insert into s_module(id,system_id,name,up_id,url,pattern,target,lvl,sts)
       values(seq_app.nextval,(select id from s_system where name='��̨����ϵͳ'),'�Ƽ�����',
             (select id from s_module where name='ѧ����֯'),
             '','manager/student/*',
             'mainFrame',2,'Y');
insert into s_module(id,system_id,name,up_id,url,pattern,target,lvl,sts)
       values(seq_app.nextval,(select id from s_system where name='��̨����ϵͳ'),'�����취',
             (select id from s_module where name='ѧ����֯'),
             'manager/student/rewardsolution/list.html','manager/student/rewardsolution/*',
             'mainFrame',3,'Y');
insert into s_module(id,system_id,name,up_id,url,pattern,target,lvl,sts)
       values(seq_app.nextval,(select id from s_system where name='��̨����ϵͳ'),'�����',
             (select id from s_module where name='ѧ����֯'),
             'manager/student/winprizehistory/list.html','manager/student/winprizehistory/*',
             'mainFrame',3,'Y');
insert into s_module(id,system_id,name,up_id,url,pattern,target,lvl,sts)
       values(seq_app.nextval,(select id from s_system where name='��̨����ϵͳ'),'���¼��',
             (select id from s_module where name='ѧ����֯'),
             'manager/student/events/list.html','manager/student/events/*',
             'mainFrame',3,'Y');
insert into s_module(id,system_id,name,up_id,url,pattern,target,lvl,sts)
       values(seq_app.nextval,(select id from s_system where name='��̨����ϵͳ'),'֪ͨ����',
             (select id from s_module where name='ѧ����֯'),
             'manager/student/technologynotice/list.html','manager/student/technologynotice/*',
             'mainFrame',3,'Y');
insert into s_module(id,system_id,name,up_id,url,pattern,target,lvl,sts)
       values(seq_app.nextval,(select id from s_system where name='��̨����ϵͳ'),'ѧ��У԰����',
             (select id from s_module where name='ѧ����֯'),
             'manager/student/schoollifes/list.html','manager/student/schoollifes/*',
             'mainFrame',2,'Y');
insert into s_module(id,system_id,name,up_id,url,pattern,target,lvl,sts)
       values(seq_app.nextval,(select id from s_system where name='��̨����ϵͳ'),'������Դ',
             (select id from s_module where name='ѧ����֯'),
             'manager/college/resources/main.html','manager/college/resources/*',
             'mainFrame',2,'Y');
insert into s_module(id,system_id,name,up_id,url,pattern,target,lvl,sts)
       values(seq_app.nextval,(select id from s_system where name='��̨����ϵͳ'),'ѧ������',
             (select id from s_module where name='ѧ����֯'),
             'manager/student/bulletins/list.html','/manager/student/bulletins/*',
             'mainFrame',2,'Y');
insert into s_module(id,system_id,name,up_id,url,pattern,target,lvl,sts)
       values(seq_app.nextval,(select id from s_system where name='��̨����ϵͳ'),'ѧ������',
             (select id from s_module where name='ѧ����֯'),
             'manager/student/news/list.html','/manager/student/news/*',
             'mainFrame',2,'Y');
insert into s_module(id,system_id,name,up_id,url,pattern,target,lvl,sts)
       values(seq_app.nextval,(select id from s_system where name='��̨����ϵͳ'),'ѧ���˵�����',
             (select id from s_module where name='ѧ����֯'),
             'manager/student/menucontents/list.html','manager/student/menucontents/*',
             'mainFrame',2,'Y');
             
insert into s_rbac_rolemodule 
       values(seq_app.nextval,(select id from s_rbac_role where name='����Ա'),(select id from s_module where name='ѧԺ����'));
insert into s_rbac_rolemodule 
       values(seq_app.nextval,(select id from s_rbac_role where name='����Ա'),(select id from s_module where name='����ѧԺͼƬ����'));
insert into s_rbac_rolemodule 
       values(seq_app.nextval,(select id from s_rbac_role where name='����Ա'),(select id from s_module where name='ѧ�����'));
insert into s_rbac_rolemodule 
       values(seq_app.nextval,(select id from s_rbac_role where name='����Ա'),(select id from s_module where name='������̬'));
insert into s_rbac_rolemodule 
       values(seq_app.nextval,(select id from s_rbac_role where name='����Ա'),(select id from s_module where name='��������'));
insert into s_rbac_rolemodule 
       values(seq_app.nextval,(select id from s_rbac_role where name='����Ա'),(select id from s_module where name='��'));
insert into s_rbac_rolemodule 
       values(seq_app.nextval,(select id from s_rbac_role where name='����Ա'),(select id from s_module where name='��'));
insert into s_rbac_rolemodule 
       values(seq_app.nextval,(select id from s_rbac_role where name='����Ա'),(select id from s_module where name='��ҵ'));
insert into s_rbac_rolemodule 
       values(seq_app.nextval,(select id from s_rbac_role where name='����Ա'),(select id from s_module where name='��ҵ����'));
insert into s_rbac_rolemodule 
       values(seq_app.nextval,(select id from s_rbac_role where name='����Ա'),(select id from s_module where name='��Ƹ��Ϣ'));
insert into s_rbac_rolemodule 
       values(seq_app.nextval,(select id from s_rbac_role where name='����Ա'),(select id from s_module where name='�Ƽ�����'));
insert into s_rbac_rolemodule 
       values(seq_app.nextval,(select id from s_rbac_role where name='����Ա'),(select id from s_module where name='�����취'));
insert into s_rbac_rolemodule 
       values(seq_app.nextval,(select id from s_rbac_role where name='����Ա'),(select id from s_module where name='��ҵ����'));
insert into s_rbac_rolemodule 
       values(seq_app.nextval,(select id from s_rbac_role where name='����Ա'),(select id from s_module where name='ѧԺУ԰����'));
insert into s_rbac_rolemodule 
       values(seq_app.nextval,(select id from s_rbac_role where name='����Ա'),(select id from s_module where name='����ԺУ'));
insert into s_rbac_rolemodule 
       values(seq_app.nextval,(select id from s_rbac_role where name='����Ա'),(select id from s_module where name='����ѧԺ����'));
insert into s_rbac_rolemodule 
       values(seq_app.nextval,(select id from s_rbac_role where name='����Ա'),(select id from s_module where name='ѧ��У԰����'));
insert into s_rbac_rolemodule 
       values(seq_app.nextval,(select id from s_rbac_role where name='����Ա'),(select id from s_module where name='��վ����'));
insert into s_rbac_rolemodule 
       values(seq_app.nextval,(select id from s_rbac_role where name='����Ա'),(select id from s_module where name='��֯����'));
insert into s_rbac_rolemodule 
       values(seq_app.nextval,(select id from s_rbac_role where name='����Ա'),(select id from s_module where name='�û�����'));
insert into s_rbac_rolemodule 
       values(seq_app.nextval,(select id from s_rbac_role where name='����Ա'),(select id from s_module where name='ģ�����'));
insert into s_rbac_rolemodule 
       values(seq_app.nextval,(select id from s_rbac_role where name='����Ա'),(select id from s_module where name='ģ��'));
insert into s_rbac_rolemodule 
       values(seq_app.nextval,(select id from s_rbac_role where name='����Ա'),(select id from s_module where name='ϵͳ����'));
insert into s_rbac_rolemodule 
       values(seq_app.nextval,(select id from s_rbac_role where name='����Ա'),(select id from s_module where name='ϵͳ'));
insert into s_rbac_rolemodule 
       values(seq_app.nextval,(select id from s_rbac_role where name='����Ա'),(select id from s_module where name='������Դ'));
insert into s_rbac_rolemodule 
       values(seq_app.nextval,(select id from s_rbac_role where name='����Ա'),(select id from s_module where name='ѧԺ������Դ'));
insert into s_rbac_rolemodule 
       values(seq_app.nextval,(select id from s_rbac_role where name='����Ա'),(select id from s_module where name='����ѧԺ�˵�����'));
insert into s_rbac_rolemodule 
       values(seq_app.nextval,(select id from s_rbac_role where name='����Ա'),(select id from s_module where name='ѧԺ�˵�����'));
insert into s_rbac_rolemodule 
       values(seq_app.nextval,(select id from s_rbac_role where name='����Ա'),(select id from s_module where name='����ѧԺУ԰����'));
insert into s_rbac_rolemodule 
       values(seq_app.nextval,(select id from s_rbac_role where name='����Ա'),(select id from s_module where name='�����'));
       
insert into s_rbac_rolemodule 
       values(seq_app.nextval,(select id from s_rbac_role where name='����Ա'),(select id from s_module where name='Ȩ�޹���'));
       
insert into s_rbac_rolemodule 
       values(seq_app.nextval,(select id from s_rbac_role where name='����Ա'),(select id from s_module where name='��ɫ'));
insert into s_rbac_rolemodule 
       values(seq_app.nextval,(select id from s_rbac_role where name='����Ա'),(select id from s_module where name='ѧ������'));
insert into s_rbac_rolemodule 
       values(seq_app.nextval,(select id from s_rbac_role where name='����Ա'),(select id from s_module where name='����ѧԺ'));
insert into s_rbac_rolemodule 
       values(seq_app.nextval,(select id from s_rbac_role where name='����Ա'),(select id from s_module where name='ѧ����֯'));
insert into s_rbac_rolemodule 
       values(seq_app.nextval,(select id from s_rbac_role where name='����Ա'),(select id from s_module where name='ѧ������'));
insert into s_rbac_rolemodule 
       values(seq_app.nextval,(select id from s_rbac_role where name='����Ա'),(select id from s_module where name='����ѧԺ����'));
insert into s_rbac_rolemodule 
       values(seq_app.nextval,(select id from s_rbac_role where name='����Ա'),(select id from s_module where name='ѧԺ����'));
insert into s_rbac_rolemodule 
       values(seq_app.nextval,(select id from s_rbac_role where name='����Ա'),(select id from s_module where name='����ѧԺ������Դ'));
insert into s_rbac_rolemodule 
       values(seq_app.nextval,(select id from s_rbac_role where name='����Ա'),(select id from s_module where name='ѧ���˵�����'));
insert into s_rbac_rolemodule 
       values(seq_app.nextval,(select id from s_rbac_role where name='����Ա'),(select id from s_module where name='��'));
insert into s_rbac_rolemodule 
       values(seq_app.nextval,(select id from s_rbac_role where name='����Ա'),(select id from s_module where name='ѧԺ����'));
insert into s_rbac_rolemodule 
       values(seq_app.nextval,(select id from s_rbac_role where name='����Ա'),(select id from s_module where name='��'));
insert into s_rbac_rolemodule 
       values(seq_app.nextval,(select id from s_rbac_role where name='����Ա'),(select id from s_module where name='���¼��'));
insert into s_rbac_rolemodule 
       values(seq_app.nextval,(select id from s_rbac_role where name='����Ա'),(select id from s_module where name='֪ͨ����'));
commit;
