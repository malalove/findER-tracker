create table bed (
       name varchar2(255 char) not null,
        time varchar2(255 char) not null,
        count number(10,0),
        local_date_time timestamp,
        primary key (name, time)
);

create table hospital (
       id number(19,0) not null,
        created_date timestamp,
        modified_date timestamp,
        address varchar2(255 char),
        ambulance varchar2(255 char),
        ct varchar2(255 char),
        emergency_contact varchar2(255 char),
        latitude double precision,
        longitude double precision,
        mri varchar2(255 char),
        name varchar2(255 char),
        representative_contact varchar2(255 char),
        simple_address varchar2(255 char),
        primary key (id)
);

create table link (
       id number(19,0) not null,
        family_relations varchar2(255 char),
        linked_user_id number(19,0),
        user_id number(19,0),
        primary key (id)
);

create table questionnaire (
       id number(19,0) not null,
        address varchar2(255 char),
        allergy varchar2(255 char),
        birthday varchar2(255 char),
        blood_type varchar2(255 char),
        drinking_cycle varchar2(255 char),
        etc varchar2(255 char),
        family_relations varchar2(255 char),
        gender varchar2(255 char),
        medicine varchar2(255 char),
        name varchar2(255 char),
        phone_num varchar2(255 char),
        smoking_cycle varchar2(255 char),
        user_id number(19,0),
        primary key (id)
);

create table users (
       id number(19,0) not null,
        created_date timestamp,
        modified_date timestamp,
        email varchar2(255 char),
        name varchar2(255 char),
        password varchar2(255 char),
        refresh_token varchar2(255 char),
        role varchar2(255 char),
        primary key (id)
);

alter table link
       add constraint lu
       unique (user_id, linked_user_id);

alter table link
       add constraint lf
       foreign key (user_id)
       references users;

alter table questionnaire
       add constraint qf
       foreign key (user_id)
       references users;