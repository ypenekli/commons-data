drop table common.projects;
create table common.projects(
	id varchar(50) not null,
	name varchar(150) not null,
	description varchar(150) default '',
	url varchar(50) default '',
	target varchar(50) default '',
	icon varchar(30) default '',
	status char not null default 'A',
	autor varchar(50) default '',	
	
	owner varchar(50),
	remaddress varchar(50),
	datetime numeric(17,0),
	lastowner varchar(50),
	lastremaddress varchar(50),
	lastdatetime numeric(17,0),
	constraint pk_common_projects primary key  
(
	id 
)
);
drop table common.project_subfuncs;
create table common.project_subfuncs(
	id varchar(50) not null,	
	projectid varchar(50) not null,		
	name varchar(150) not null,
	description varchar(150) default '',
	url varchar(50) default '',
	target varchar(50) default '',    
    autor varchar(50) default '',
	
    parentid integer not null default -1,
	idx int not null default 0,
    level int not null default 0,
    hierarchy varchar(250),
    leaf char not null default 'F',    
	icon varchar(30) default '',
	status char not null default 'A',
	
	owner varchar(50),
	remaddress varchar(50),
	datetime numeric(17,0),
	lastowner varchar(50),
	lastremaddress varchar(50),
	lastdatetime numeric(17,0),
	constraint pk_common_project_subfuncs primary key  
(
	id 
)
);
drop table common.groups;
create table common.groups(
	id integer not null,
	name varchar(150) not null,
	projectid varchar(50) not null,		
	hierarchy varchar(150),
	group_type char not null default 'U',
	status char not null default 'A',	
	
	owner varchar(50),
	remaddress varchar(50),
	datetime numeric(17,0),
	lastowner varchar(50),
	lastremaddress varchar(50),
	lastdatetime numeric(17,0),
	constraint pk_common_groups primary key  
(
	id 
)
);
drop table common.group_funcs;
create table common.group_funcs(
	groupid integer not null,
	funcid varchar(150) not null,	
	
	owner varchar(50),
	remaddress varchar(50),
	datetime numeric(17,0),
	lastowner varchar(50),
	lastremaddress varchar(50),
	lastdatetime numeric(17,0),
	constraint pk_common_group_funcs primary key  
(
	groupid, funcid 
)
);
drop table common.group_funcs_history;
create table common.group_funcs_history(
	idx bigint not null,
	groupid integer not null,
	funcid varchar(150) not null,	
	
	update_userid integer,
	update_username varchar(100),
	update_usertitle varchar(50),
	update_mode char not null default 'A',
	
	owner varchar(50),
	remaddress varchar(50),
	datetime numeric(17,0),
	constraint pk_common_group_funcs_history primary key  
(
	idx 
)
);
drop table common.group_users;
create table common.group_users(
	groupid integer not null,
	userid integer not null,
	
	owner varchar(50),
	remaddress varchar(50),
	datetime numeric(17,0),
	lastowner varchar(50),
	lastremaddress varchar(50),
	lastdatetime numeric(17,0),
	constraint pk_common_group_users primary key  
(
	groupid, userid 
)
);
drop table common.group_users_history;
create table common.group_users_history(
	idx bigint not null,
	groupid integer not null,
	userid integer not null,
	
	update_userid integer,
	update_username varchar(100),
	update_usertitle varchar(50),
	update_mode char not null default 'A',
	
	owner varchar(50),
	remaddress varchar(50),
	datetime numeric(17,0),
	constraint pk_common_group_users_history primary key  
(
	idx 
)
);
drop table common.users;
create table common.users(
	id integer not null,
	citizenship_no numeric(11,0) NOT NULL DEFAULT 0,
	name varchar(75) not null default '',
	surname varchar(75) not null default '',
	birth_date numeric(8,0) default 0,
	birth_city integer   default -1,
	gender char(1) default '',
	title integer  default -1,
	profession integer  default -1,	
	position integer  default -1,	
	checkin_date numeric(8,0)  default 0,
	checkout_date numeric(8,0)  default 0,
	email varchar(50) not null default '',
	phoneno1 varchar(15) default '',
	phoneno2 varchar(15) default '',
	phoneno3 varchar(15) default '',
	password varchar(50) not null default '*11111*',
	login_error_count integer  default 0,
	pwd_update_datetime numeric(17,0)  DEFAULT 0,
	iban char(26)   DEFAULT '',
	paycard_no char(16)   DEFAULT '',
	paycard_type char  default 'M',
	home_city integer   default -1,
	home_district integer  default -1,
	home_address varchar(150)   default '',
	invoice_city integer   default -1,
	invoice_district integer  default -1,
	invoice_address varchar(150)   default '',
	status char not null default 'A',	
	
	owner varchar(50),
	remaddress varchar(50),
	datetime numeric(17,0),
	lastowner varchar(50),
	lastremaddress varchar(50),
	lastdatetime numeric(17,0),
	constraint pk_common_users primary key  
(
	id 
)
);
drop table common.user_images;
create table common.user_images(
	userid integer not null,
	idx integer not null,
	image blob not null,	
	
	owner varchar(50),
	remaddress varchar(50),
	datetime numeric(17,0),
	lastowner varchar(50),
	lastremaddress varchar(50),
	lastdatetime numeric(17,0),
	constraint pk_common_user_images primary key  
(
	userid, idx 
)
);
drop table common.login_history;
create table common.login_history(
	idx bigint not null,
	projectid integer not null,
	userid integer not null,
	login_datetime numeric(17, 0),
	
	owner varchar(50),
	remaddress varchar(50),
	datetime numeric(17,0),
	constraint pk_common_login_history primary key  
(
	idx 
)
);
drop table common.pwdu_history;
create table common.pwdu_history(
	idx bigint not null,
	userid integer not null,
	password varchar(50) not null default '',
	
	update_datetime numeric(17, 0),
	update_userid integer,
	update_username varchar(100),
	update_usertitle varchar(50),
	
	owner varchar(50),
	remaddress varchar(50),
	datetime numeric(17,0),
	constraint pk_common_pwdu_history primary key  
(
	idx 
)
);
drop table common.commons;
create table common.commons(
	id integer not null,
	name varchar(150) not null,
	abrv varchar(75) not null,
	description varchar(150) default '', 	
	group_code integer not null default -1,	
	
    parentid integer not null default -1,
	idx int not null default 0,
    level int not null default 0,
    hierarchy varchar(150),
    leaf char not null default 'F',    
	icon varchar(30) default '',
	status char not null default 'A',
	
	owner varchar(50),
	remaddress varchar(50),
	datetime numeric(17,0),
	lastowner varchar(50),
	lastremaddress varchar(50),
	lastdatetime numeric(17,0),
	constraint pk_common_commons primary key  
	(
		id 
	)
);	
drop table common.transfers;
create table common.transfers(
	source_schema varchar(50) not null,
	source_table varchar(50) not null,
	target_schema varchar(50) not null,
	target_table varchar(50) not null,
	query varchar(250) default '*', 
	source_count int not null default 0,
	target_count int not null default 0,

	start_datetime numeric(17, 0) not null default 0, 	
	end_datetime numeric(17, 0) not null default 0, 	
	group_code char(1) not null default 'A' ,	
	idx int not null default 0,    
    messages varchar(150),    
	error_code int not null default 0,
	constraint pk_common_transfers primary key  
	(
		source_schema, source_table, target_schema, target_table 
	)
);
drop table common.indeks;
create table common.indeks(
	idx int not null default 0, 
	constraint pk_common_indeks primary key  
	(
		idx 
	)
);