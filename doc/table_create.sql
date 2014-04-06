/*==============================================================*/
/* Table: zjut_organization                                   */
/*==============================================================*/
create table zjut_organization  (
   id                 number(12)                      not null,
   name               varchar2(50),
   display_name       varchar2(100),
   create_date        date,
   create_user_id     number(12),
   modify_date        date,
   modify_user_id     number(12),
   sts                varchar2(1),
   constraint PK_ZJUT_ORGANIZATION primary key (id)
);

/*==============================================================*/
/* Table: zjut_organization_new                               */
/*==============================================================*/
create table zjut_organization_new  (
   id                 number(12)                      not null,
   organization_id    number(12),
   new_id             number(12),
   create_date        date,
   create_user_id     number(12),
   modify_date        date,
   modify_user_id     number(12),
   sts                varchar2(1),
   constraint PK_ZJUT_ORGANIZATION_NEW primary key (id)
);

/*==============================================================*/
/* Table: zjut_organization_bulletin                          */
/*==============================================================*/
create table zjut_organization_bulletin  (
   id                 number(12)                      not null,
   organization_id    number(12),
   bulletin_id        number(12),
   create_date        date,
   create_user_id     number(12),
   modify_date        date,
   modify_user_id     number(12),
   sts                varchar2(1),
   constraint PK_ZJUT_ORGANIZATION_BULLETIN primary key (id)
);

/*==============================================================*/
/* Table: zjut_new                                           */
/*==============================================================*/
create table zjut_new  (
   id                 number(12)                      not null,
   title              varchar2(100),
   author             varchar2(50),
   context            varchar2(4000),
   is_top             varchar2(1),
   is_valid           varchar2(1),
   expire_time        date,
   create_date        date,
   create_user_id     number(12),
   modify_date        date,
   modify_user_id     number(12),
   sts                varchar2(1),
   constraint PK_ZJUT_NEWS primary key (id)
);

/*==============================================================*/
/* Table: zjut_bulletin                                       */
/*==============================================================*/
create table zjut_bulletin  (
   id                 number(12)                      not null,
   title              varchar2(100),
   author             varchar2(50),
   context            varchar2(4000),
   is_top             varchar2(1),
   is_valid           varchar2(1),
   expire_time        date,
   create_date        date,
   create_user_id     number(12),
   modify_date        date,
   modify_user_id     number(12),
   sts                varchar2(1),
   constraint PK_ZJUT_BULLETIN primary key (id)
);

/******************************************************************************/
/*            2013.3.4ÐÂÔö                                                    */
/******************************************************************************/

/*==============================================================*/
/* Table: zjut_organization_resource                          */
/*==============================================================*/
create table zjut_organization_resource  (
   id                 number(12)                      not null,
   organization_id    number(12),
   resource_id        number(12),
   create_date        date,
   create_user_id     number(12),
   modify_date        date,
   modify_user_id     number(12),
   sts                varchar2(1),
   constraint PK_ZJUT_ORGANIZATION_RESOURCE primary key (id)
);

/*==============================================================*/
/* Table: zjut_resources                                   */
/*==============================================================*/
create table zjut_resources  (
   id                 number(12)                      not null,
   type_id            number(12),
   name               varchar2(50),
   description        varchar2(500),
   download_path      varchar2(500),
   expire_date        date,
   create_date        date,
   modify_date        date,
   create_user_id     number(12),
   modify_user_id     number(12),
   sts                varchar2(1),
   constraint PK_RESOURCES primary key (id)
);

/*==============================================================*/
/* Table: zjut_resource_type                                */
/*==============================================================*/
create table zjut_resource_type  (
   id                  number(12)                      not null,
   name               varchar2(50),
   display_name       varchar2(200),
   description        varchar2(500),
   up_id              number(12),
   create_date        date,
   create_user_id     number(12),
   modify_date        date,
   modify_user_id     number(12),
   sts                varchar2(1),
   constraint PK_RESOURCE_TYPE primary key (id)
);


/*==============================================================*/
/* Table: zjut_menu_content                                   */
/*==============================================================*/
create table zjut_menu_content  (
   id                 number(12)                      not null,
   menu_name          varchar2(100),
   menu_display_name  varchar2(200),
   up_id              number(12),
   content            varchar2(4000),
   organization_id    number(12),
   create_date        date,
   create_user_id     number(12),
   modify_date        date,
   modify_user_id     number(12),
   sts                varchar2(1),
   constraint PK_ZJUT_MENU_CONTENT primary key (id)
);



alter table zjut_new add source varchar2(50);
alter table zjut_new add browse_count number(12);
alter table zjut_bulletin add source varchar2(50);
alter table zjut_bulletin add browse_count number(12);


/*==============================================================*/
/* Table: zjut_school_life                                    */
/*==============================================================*/
create table zjut_school_life  (
   id                 number(12)                      not null,
   title              varchar2(100),
   author             varchar2(100),
   summary            varchar2(2000),
   content            varchar2(400),
   image              varchar2(100),
   is_top             varchar2(1),
   is_validate        varchar2(1),
   create_user_id     number(12),
   create_date        date,
   modify_user_id     number(12),
   modify_date        date,
   sts                varchar2(1),
   constraint PK_ZJUT_SCHOOL_LIFE primary key (id)
);


/*==============================================================*/
/* Table: zjut_organization_life                              */
/*==============================================================*/
create table zjut_organization_life  (
   id                 number(12)                      not null,
   organization_id    number(12),
   school_life_id     number(12),
   create_user_id     number(12),
   create_date        date,
   modify_user_id     number(12),
   modify_date               date,
   sts                varchar2(1),
   constraint PK_ZJUT_ORGANIZATION_LIFE primary key (id)
);


/*==============================================================*/
/* Sequence: ZJUT_APP                                      */
/*==============================================================*/
create sequence ZJUT_APP
minvalue 1
maxvalue 999999999999999999999999999
start with 1889
increment by 1
cache 20;
