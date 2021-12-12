create table user_
    (username varchar(20),
     email varchar(50) unique,
     password varchar(20),
     is_owner boolean,
     first_name varchar(20),
     last_name varchar(20),
     address varchar(100),
     primary key(username)
    );

create table order_
    (id serial,
     username varchar(20),
     status varchar(20),
     shipping_address varchar(100),
     billing_address varchar(100),
     primary key(id),
     foreign key(username) references user_
        on delete set null
    );

create table shipment
    (tracking_number varchar(20),
     order_id int,
     facility_name varchar(20),
     city varchar(20),
     primary key(tracking_number),
     foreign key(order_id) references order_(id)
        on delete cascade
    );

create table publisher
    (name varchar(20),
     website varchar(20),
     email varchar(50),
     address varchar(100),
     branch_transit_number numeric(5,0),
     financial_institution_number numeric(3,0),
     account_number numeric(9,0),
     primary key(name)
    );

create table publisher_phone
    (name varchar(20),
     phone varchar(20),
     primary key(name, phone),
     foreign key(name) references publisher
        on delete cascade
    );

create table book
    (ISBN char(13),
     title varchar(20),
     num_pages smallint,
     price numeric(6,2),
     publisher_name varchar(20),
     publisher_cut numeric(1,4),
     primary key(ISBN),
     foreign key(publisher_name) references publisher(name)
        on delete set null
    );

create table book_genre
    (ISBN char(13),
     genre varchar(20),
     primary key(ISBN),
     foreign key(ISBN) references book
        on delete cascade
    );

create table book_order
    (order_id int,
     ISBN char(13),
     primary key(order_id, ISBN),
     foreign key(order_id) references order_
        on delete cascade,
     foreign key(ISBN) references book
        on delete cascade
    );

create table author
    (first_name varchar(20),
     middle_name varchar(20),
     last_name varchar(20),
     ISBN char(13),
     primary key(first_name, middle_name, last_name, ISBN),
     foreign key(ISBN) references book
        on delete cascade
    );
