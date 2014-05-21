create table user (
    id bigint not null primary key auto_increment,
    login_name varchar(255) not null, 
    password varchar(255) not null,
    name varchar(255) not null, 
    create_time timestamp not null
) character set utf8;


create table book(
    id bigint not null primary key auto_increment,
    name varchar(255) not null, 
    price varchar(255) not null, 
    author varchar(255) not null,
    create_time timestamp not null
) character set utf8;


create table remark(
    id bigint not null primary key auto_increment,
    user_name varchar(255) not null, 
    book_id  bigint, 
    essay varchar(2000) not null,
    create_time timestamp not null
) character set utf8;



create table oper_log(
    id bigint not null primary key auto_increment,
    user_name varchar(255) not null, 
    resource_pattern varchar(255) not null, 
    resource_id varchar(255) not null, 
    success tinyint(1)  not null,
    remarks varchar(255) not null, 
    create_time timestamp not null
) character set utf8;
