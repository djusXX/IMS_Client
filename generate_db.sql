create database ims_client
go

use ims_client
go
--------------------------------------------------------------

create table users (
	id int primary key,
	name varchar(50),
	srv_id varchar(50),
	priv_id varchar(50),
	passwd char(64),
	srv_name varchar(50)
);

create table contacts(
	id int primary key,
	name varchar(50),
	srv_id varchar(50),
	priv_id varchar(50)
);

create table messages (
	id int primary key,
	msg_type varchar(50),
	user_id int references users(id),
	contact_id int references contacts(id),
	msg_time smalldatetime,
	msg_content nvarchar(max)
);

create table calls (
	id int primary key,
	call_type varchar(50),
	user_id int references users(id),
	contact_id int references contacts(id),
	start_time smalldatetime,
	stop_time smalldatetime
);

create table contact_list (
	id int primary key,
	user_id int references users(id),
	contact_id int references contacts(id),
);

create table message_list (
	id int primary key,
	user_id int references users(id),
	contact_id int references contacts(id),
);
go
----------------------------------------------------------------

