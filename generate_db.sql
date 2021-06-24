create database ims_client
go

use ims_client
go
--------------------------------------------------------------

create table users (
	id int primary key,
	display_name varchar(50),
	public_id varchar(50),
	private_id varchar(50),
	password char(64),
	realm varchar(50),
	proxy_cscf_host varchar(15),
	proxy_cscf_port int
);

create table contacts(
	id int primary key,
	user_id int references users(id),
	display_name varchar(50),
	sip_uri varchar(50)
);

create table messages (
	id int primary key,
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

go
----------------------------------------------------------------

