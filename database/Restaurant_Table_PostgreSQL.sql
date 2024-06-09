create table categories 
(
	id serial,
	name varchar(200) not null,
	description varchar(1000),
	url_key varchar(250),
	state char(1) not null default 'A',
	created_at timestamp not null default CURRENT_TIMESTAMP,
	updated_at timestamp,
	
	constraint categories_pk primary key (id),
	constraint categories_name_uq unique (name)
);

create table products 
(
	id serial,
	name varchar(300) not null,
	description varchar(4000),
	price decimal (18,2) not null default 0,
	stock integer not null default 0,
	category_id integer,
	state char(1) not null default 'A',
	created_at timestamp not null default CURRENT_TIMESTAMP,
	updated_at timestamp,
	
	constraint products_pk primary key (id),
	constraint products_category_id_fk foreign key (category_id) references categories(id),
	constraint products_name_subcategory_id_uq unique (name,category_id)
);


create table profiles 
(
	id serial,
	name varchar(200) not null,
	description varchar(1000),
	state char(1) not null default 'A',
	created_at timestamp not null default CURRENT_TIMESTAMP,
	updated_at timestamp,
	
	constraint profiles_pk primary key (id),
	constraint profiles_name_uq unique (name)
);


create table users 
(
	id serial,
	name varchar(200) not null,
	last_name varchar(200),
	email varchar(100) not null,
	password varchar(200) not null,
	profile_id integer,
	state char(1) not null default 'A',
	created_at timestamp not null default CURRENT_TIMESTAMP,
	updated_at timestamp,
	
	constraint users_pk primary key (id),
	constraint users_profile_id_fk foreign key (profile_id) references profiles (id),
	constraint users_email_uq unique (email)
);

create table document_types 
(
	id serial,
	name varchar(200) not null,
	description varchar(1000),
	sunat_code varchar(2),
	size integer,
	is_size_exact integer not null default 0,
	is_numeric integer not null default 0,
	state char(1) not null default 'A',
	created_at timestamp not null default CURRENT_TIMESTAMP,
	updated_at timestamp,
	
	constraint document_types_pk primary key (id),
	constraint document_types_name_uq unique (name)
);


create table customers 
(
	id serial,
	name varchar(200) not null,
	last_name varchar(200),
	document_type_id integer not null,
	document_number varchar(20) not null,
	phone_number varchar(25),
	address varchar(2000),
	state char(1) not null default 'A',
	created_at timestamp not null default CURRENT_TIMESTAMP,
	updated_at timestamp,
	
	constraint customers_pk primary key (id),
	constraint customers_document_type_id_fk foreign key (document_type_id) references document_types (id),
	constraint customers_document_type_id_document_number_uq unique (document_type_id, document_number)
);


create table invoices 
(
	id serial,
	invoice_date timestamp,
	customer_id integer,
	user_id integer,
	state char(1) not null default 'A',
	created_at timestamp not null default CURRENT_TIMESTAMP,
	updated_at timestamp,
	
	constraint invoices_pk primary key (id),
	constraint invoices_customer_id foreign key (customer_id) references customers (id),
	constraint invoices_user_id foreign key (user_id) references users (id)
);


create table invoice_details 
(
	invoice_id integer,
	product_id integer,
	quantity integer,
	price decimal (18,2) not null,
	state char(1) not null default 'A',
	created_at timestamp not null default CURRENT_TIMESTAMP,
	updated_at timestamp,
	
	constraint invoice_details_pk primary key (invoice_id, product_id),
	constraint invoice_details_invoice_id foreign key (invoice_id) references invoices (id),
	constraint invoice_details_product_id foreign key (product_id) references products (id)
);


-- tables for the practical part of the class

create table tables
(
    id serial,
    name varchar(200) not null,
    description varchar(1000),
    state char(1) not null default 'A',
    created_at timestamp not null default CURRENT_TIMESTAMP,
    updated_at timestamp,

    constraint tables_pk primary key (id),
    constraint tables_name_uq unique (name)
);


create table invoice_statements
(
    id serial,
    name varchar(200) not null,
    description varchar(1000),
    state char(1) not null default 'A',
    created_at timestamp not null default CURRENT_TIMESTAMP,
    updated_at timestamp,

    constraint invoice_statements_pk primary key (id),
    constraint invoice_statements_name_uq unique (name)
);


create table payment_types
(
    id serial,
    name varchar(200) not null,
    description varchar(1000),
    state char(1) not null default 'A',
    created_at timestamp not null default CURRENT_TIMESTAMP,
    updated_at timestamp,

    constraint payment_types_pk primary key (id),
    constraint payment_types_name_uq unique (name)
);


create table order_types
(
    id serial,
    name varchar(200) not null,
    description varchar(1000),
    state char(1) not null default 'A',
    created_at timestamp not null default CURRENT_TIMESTAMP,
    updated_at timestamp,

    constraint order_types_pk primary key (id),
    constraint order_types_name_uq unique (name)
);


create table order_status
(
    id serial,
    name varchar(200) not null,
    description varchar(1000),
    state char(1) not null default 'A',
    created_at timestamp not null default CURRENT_TIMESTAMP,
    updated_at timestamp,

    constraint order_status_pk primary key (id),
    constraint order_status_name_uq unique (name)
);
