
create table users (
    id SERIAL UNIQUE,
    resume_id int,
    first_name varchar(50) not null,
    last_name varchar(50) not null,
    email_address varchar(50) not null,
    password varchar(500) not null);

create unique index idx_user_email on users (email_address);



/*==============================================================*/
/* Nom de SGBD :  PostgreSQL 8                                  */
/* Date de création :  13/11/2022 16:16:41                      */
/*==============================================================*/


/*==============================================================*/
/* Table : ADVANTAGE                                            */
/*==============================================================*/
create table ADVANTAGE
(
    ADVANTAGE_ID     SERIAL        not null,
    LIBELLE_AVANTAGE VARCHAR(50) not null,
    constraint PK_ADVANTAGE primary key (ADVANTAGE_ID)
);

comment
on column ADVANTAGE.ADVANTAGE_ID is
'id of an advantage';

comment
on column ADVANTAGE.LIBELLE_AVANTAGE is
'An advantage ex: free meal
';

/*==============================================================*/
/* Index : AVANTAGE_PK                                          */
/*==============================================================*/
create unique index AVANTAGE_PK on ADVANTAGE (
                                              ADVANTAGE_ID
    );

/*==============================================================*/
/* Table : ADVANTAGES                                           */
/*==============================================================*/
create table ADVANTAGES
(
    OFFER_ID     INT8 not null,
    ADVANTAGE_ID INT8 not null,
    constraint PK_ADVANTAGES primary key (OFFER_ID, ADVANTAGE_ID)
);

comment
on column ADVANTAGES.OFFER_ID is
'Id of an offer
';

comment
on column ADVANTAGES.ADVANTAGE_ID is
'id of an advantage

';

/*==============================================================*/
/* Index : ADVANTAGES_PK2                                       */
/*==============================================================*/
create unique index ADVANTAGES_PK2 on ADVANTAGES (
                                                  OFFER_ID,
                                                  ADVANTAGE_ID
    );

/*==============================================================*/
/* Index : ADVANTAGES_FK2                                       */
/*==============================================================*/
create index ADVANTAGES_FK2 on ADVANTAGES (
                                           OFFER_ID
    );

/*==============================================================*/
/* Index : ADVANTAGES2_FK2                                      */
/*==============================================================*/
create index ADVANTAGES2_FK2 on ADVANTAGES (
                                            ADVANTAGE_ID
    );

/*==============================================================*/
/* Table : AFFECT                                               */
/*==============================================================*/
create table AFFECT
(
    USER_ID  INT8 not null,
    OFFER_ID INT8 not null,
    IS_ACCEPTED     BOOLEAN NOT NULL DEFAULT FALSE,
    constraint PK_AFFECT primary key (USER_ID, OFFER_ID)
);

comment
on column AFFECT.USER_ID is
'Id of the User';

comment
on column AFFECT.OFFER_ID is
'Id of an offer
';

/*==============================================================*/
/* Index : AFFECT_PK                                            */
/*==============================================================*/
create unique index AFFECT_PK on AFFECT (
                                         USER_ID,
                                         OFFER_ID
    );

/*==============================================================*/
/* Index : IS_AFFECTED_FK                                       */
/*==============================================================*/
create index IS_AFFECTED_FK on AFFECT (
                                       USER_ID
    );

/*==============================================================*/
/* Index : CAN_AFFECT_FK                                        */
/*==============================================================*/
create index CAN_AFFECT_FK on AFFECT (
                                      OFFER_ID
    );

/*==============================================================*/
/* Table : INTEREST                                             */
/*==============================================================*/
create table INTEREST
(
    USER_ID    INT8 not null,
    KEYWORD_ID INT8 not null,
    constraint PK_INTEREST primary key (USER_ID, KEYWORD_ID)
);

comment
on column INTEREST.USER_ID is
'Id of the User';

comment
on column INTEREST.KEYWORD_ID is
'Id of a keyword';

/*==============================================================*/
/* Index : INTEREST_PK                                          */
/*==============================================================*/
create unique index INTEREST_PK on INTEREST (
                                             USER_ID,
                                             KEYWORD_ID
    );

/*==============================================================*/
/* Index : INTEREST_FK                                          */
/*==============================================================*/
create index INTEREST_FK on INTEREST (
                                      USER_ID
    );

/*==============================================================*/
/* Index : INTEREST2_FK                                         */
/*==============================================================*/
create index INTEREST2_FK on INTEREST (
                                       KEYWORD_ID
    );

/*==============================================================*/
/* Table : KEYWORD                                              */
/*==============================================================*/
create table KEYWORD
(
    KEYWORD_ID SERIAL not null,
    LIBELLE    VARCHAR(50) not null,
    constraint PK_KEYWORD primary key (KEYWORD_ID)
);

comment
on column KEYWORD.KEYWORD_ID is
'Id of a keyword';

comment
on column KEYWORD.LIBELLE is
'Libelle of a keyword, ex: Python ';

/*==============================================================*/
/* Index : KEYWORDS_PK                                          */
/*==============================================================*/
create unique index KEYWORDS_PK on KEYWORD (
                                            KEYWORD_ID
    );

/*==============================================================*/
/* Table : OFFER                                                */
/*==============================================================*/
create table OFFER
(
    OFFER_ID          SERIAL not null,
    USER_ID           INT8 not null,
    ADDRESS           VARCHAR(50) not null,
    TITLE_OFFER       VARCHAR(50) not null,
    DESCRIPTION_OFFER TEXT not null,
    STARTING_DATE     DATE not null,
    DATE_END          DATE not null,
    NB_JOBS           INT4 not null,
    SALARY            INT4 not null,
    constraint PK_OFFER primary key (OFFER_ID)
);

comment
on column OFFER.OFFER_ID is
'Id of an offer
';

comment
on column OFFER.USER_ID is
'Id of the User';

comment
on column OFFER.ADDRESS is
'Where the offer takes place
';

comment
on column OFFER.TITLE_OFFER is
'Title of the offer';

comment
on column OFFER.DESCRIPTION_OFFER is
'Description of an offer';

comment
on column OFFER.STARTING_DATE is
'Starting date of the offer';

comment
on column OFFER.DATE_END is
'Date
end of an offer';

comment
on column OFFER.NB_JOBS is
'Number of person an offer can take';

comment
on column OFFER.SALARY is
'Salary of the offer';

/*==============================================================*/
/* Index : OFFER_PK                                             */
/*==============================================================*/
create unique index OFFER_PK on OFFER (
                                       OFFER_ID
    );

/*==============================================================*/
/* Index : CREATE_FK                                            */
/*==============================================================*/
create index CREATE_FK on OFFER (
                                 USER_ID
    );

/*==============================================================*/
/* Table : RECOMMENDATE                                         */
/*==============================================================*/
create table RECOMMENDATE
(
    USER_ID  INT8 not null,
    OFFER_ID INT8 not null,
    constraint PK_RECOMMENDATE primary key (USER_ID, OFFER_ID)
);

comment
on column RECOMMENDATE.USER_ID is
'Id of the User';

comment
on column RECOMMENDATE.OFFER_ID is
'Id of an offer
';

/*==============================================================*/
/* Index : RECOMMENDATE_PK                                      */
/*==============================================================*/
create unique index RECOMMENDATE_PK on RECOMMENDATE (
                                                     USER_ID,
                                                     OFFER_ID
    );

/*==============================================================*/
/* Index : RECOMMENDATE_FK                                      */
/*==============================================================*/
create index RECOMMENDATE_FK on RECOMMENDATE (
                                              USER_ID
    );

/*==============================================================*/
/* Index : RECOMMENDATE2_FK                                     */
/*==============================================================*/
create index RECOMMENDATE2_FK on RECOMMENDATE (
                                               OFFER_ID
    );

/*==============================================================*/
/* Table : RESUME                                               */
/*==============================================================*/
create table RESUME
(
    RESUME_ID    SERIAL        not null,
    USER_ID      INT8 not null,
    TITLE_RESUME VARCHAR(50) not null,
    DESCRIPTION_RESUME TEXT        not null,
    constraint PK_RESUME primary key (RESUME_ID)
);

comment
on column RESUME.RESUME_ID is
'Id of the resume';

comment
on column RESUME.USER_ID is
'Id of the User';

comment
on column RESUME.TITLE_RESUME is
'Title of the resume ';

comment
on column RESUME.DESCRIPTION_RESUME is
'Description of a resume';

/*==============================================================*/
/* Index : RESUME_PK                                            */
/*==============================================================*/
create unique index RESUME_PK on RESUME (
                                         RESUME_ID
    );

/*==============================================================*/
/* Index : HAVE2_FK                                             */
/*==============================================================*/
create index HAVE2_FK on RESUME (
                                 USER_ID
    );

/*==============================================================*/
/* Table : SCORE                                                */
/*==============================================================*/
create table SCORE
(
    ORIGIN_USER INT8 not null,
    TARGET_USER INT8 not null,
    SCORE       INT8 not null,
    constraint PK_SCORE primary key (ORIGIN_USER, TARGET_USER)
);

comment
on column SCORE.ORIGIN_USER is
'Id of the User';

comment
on column SCORE.TARGET_USER is
'Id of the User';

/*==============================================================*/
/* Index : SCORE_PK                                             */
/*==============================================================*/
create unique index SCORE_PK on SCORE (
                                       ORIGIN_USER,
                                       TARGET_USER
    );

/*==============================================================*/
/* Index : SCORE_FK                                             */
/*==============================================================*/
create index SCORE_FK on SCORE (
                                ORIGIN_USER
    );

/*==============================================================*/
/* Index : SCORE2_FK                                            */
/*==============================================================*/
create index SCORE2_FK on SCORE (
                                 TARGET_USER
    );

/*==============================================================*/
/* Table : TAGS                                                 */
/*==============================================================*/
create table TAGS
(
    KEYWORD_ID INT8 not null,
    OFFER_ID   INT8 not null,
    constraint PK_TAGS primary key (KEYWORD_ID, OFFER_ID)
);

comment
on column TAGS.KEYWORD_ID is
'Id of a keyword';

comment
on column TAGS.OFFER_ID is
'Id of an offer
';

/*==============================================================*/
/* Index : ADVANTAGES_PK                                        */
/*==============================================================*/
create unique index ADVANTAGES_PK on TAGS (
                                           KEYWORD_ID,
                                           OFFER_ID
    );

/*==============================================================*/
/* Index : ADVANTAGES_FK                                        */
/*==============================================================*/
create index ADVANTAGES_FK on TAGS (
                                    KEYWORD_ID
    );

/*==============================================================*/
/* Index : ADVANTAGES2_FK                                       */
/*==============================================================*/
create index ADVANTAGES2_FK on TAGS (
                                     OFFER_ID
    );


alter table ADVANTAGES
    add constraint FK_ADVANTAG_ADVANTAGE_OFFER foreign key (OFFER_ID)
        references OFFER (OFFER_ID)
        on delete restrict on update restrict;

alter table ADVANTAGES
    add constraint FK_ADVANTAG_ADVANTAGE_ADVANTAG foreign key (ADVANTAGE_ID)
        references ADVANTAGE (ADVANTAGE_ID)
        on delete restrict on update restrict;

alter table AFFECT
    add constraint FK_AFFECT_CAN_AFFEC_OFFER foreign key (OFFER_ID)
        references OFFER (OFFER_ID)
        on delete restrict on update restrict;

alter table AFFECT
    add constraint FK_AFFECT_IS_AFFECT_USERS foreign key (USER_ID)
        references USERS (id)
        on delete restrict on update restrict;

alter table INTEREST
    add constraint FK_INTEREST_INTEREST_USERS foreign key (USER_ID)
        references USERS (id)
        on delete restrict on update restrict;

alter table INTEREST
    add constraint FK_INTEREST_INTEREST2_KEYWORD foreign key (KEYWORD_ID)
        references KEYWORD (KEYWORD_ID)
        on delete restrict on update restrict;

alter table OFFER
    add constraint FK_OFFER_CREATE_USERS foreign key (USER_ID)
        references USERS (id)
        on delete restrict on update restrict;

alter table RECOMMENDATE
    add constraint FK_RECOMMEN_RECOMMEND_USERS foreign key (USER_ID)
        references USERS (id)
        on delete restrict on update restrict;

alter table RECOMMENDATE
    add constraint FK_RECOMMEN_RECOMMEND_OFFER foreign key (OFFER_ID)
        references OFFER (OFFER_ID)
        on delete restrict on update restrict;

alter table RESUME
    add constraint FK_RESUME_HAVE2_USERS foreign key (USER_ID)
        references USERS (id)
        on delete restrict on update restrict;

alter table SCORE
    add constraint FK_SCORE_SCORE_USERS foreign key (ORIGIN_USER)
        references USERS (id)
        on delete restrict on update restrict;

alter table SCORE
    add constraint FK_SCORE_SCORE2_USERS foreign key (TARGET_USER)
        references USERS (id)
        on delete restrict on update restrict;

alter table TAGS
    add constraint FK_TAGS_TAGS_KEYWORD foreign key (KEYWORD_ID)
        references KEYWORD (KEYWORD_ID)
        on delete restrict on update restrict;

alter table TAGS
    add constraint FK_TAGS_TAGS2_OFFER foreign key (OFFER_ID)
        references OFFER (OFFER_ID)
        on delete restrict on update restrict;



create table roles (
  id SERIAL UNIQUE,
  role_name varchar(50) not null
);

create table user_roles (
	id SERIAL,
	user_id int not null,
	role_id int not null,
	constraint fk_userroles_user_id foreign key(user_id) references users(id),
	constraint fk_userroles_role_id foreign key(role_id) references roles(id)
);

create unique index idx_user_role_id on user_roles (user_id, role_id);

-- Password is admin01@123#
INSERT INTO users (first_name, last_name, email_address, password)
VALUES ('Super', 'Admin 01', 'admin01@tw.com', '$2a$10$4LEwPTJ86OF/oZUn8hl0vOhSUhFqX5YwNO./i/bTeTD6cn5lRLj2S');


INSERT INTO roles (id, role_name)
VALUES (1, 'ROLE_ADMIN');

INSERT INTO roles (id, role_name)
VALUES (2, 'ROLE_USER');


INSERT INTO user_roles (user_id, role_id)
select u.id, r.id
from users u, roles r
where u.email_address = 'admin01@tw.com'
and r.role_name = 'ROLE_ADMIN';