insert into zjut_organization(id,name,display_name,create_date,modify_date,sts)
       values(zjut_app.nextval,'college','学院',sysdate,sysdate,'Y');
insert into zjut_organization(id,name,display_name,create_date,modify_date,sts)
       values(zjut_app.nextval,'student','学生',sysdate,sysdate,'Y');
insert into zjut_new(id,title,author,context,is_top,is_valid,create_date,modify_date,sts)
       values(zjut_app.nextval,'test','test','test','N','Y',sysdate,sysdate,'Y');
insert into zjut_bulletin(id,title,author,context,is_top,is_valid,create_date,modify_date,sts)
       values(zjut_app.nextval,'test','test','test','N','Y',sysdate,sysdate,'Y');
insert into zjut_organization_bulletin(id,organization_id,bulletin_id,create_date,modify_date,sts)
       values(zjut_app.nextval,(select id from zjut_organization where name='college'),(select id from zjut_bulletin where title='test'),sysdate,sysdate,'Y');
insert into zjut_organization_new(id,organization_id,new_id,create_date,modify_date,sts)
       values(zjut_app.nextval,(select id from zjut_organization where name='college'),(select id from zjut_new where title='test'),sysdate,sysdate,'Y');  
