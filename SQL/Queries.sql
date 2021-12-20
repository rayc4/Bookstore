-- The following are all the SQL queries used in our application

-- insert user
insert into user_ values (?,?,?,?,?,?,?);

-- get user (for attempted login)
select * from user_ where username=? and password=?;

-- insert book
insert into book values (?,?,?,?,?,?,?);

-- insert publisher
insert into publisher values (?,?,?,?,?,?,?);

-- get all book ISBNs
select ISBN from book;

-- get book info
select * from book where ISBN=?;

-- delete book
delete from book where isbn=?;

-- insert order
insert into order_ values (default,?,'pending','2021-01-01',?,?);

-- get all orders made by a user
select * from order_ where username=?;

-- get order by id
select * from order_ where id=?;

-- get titles of books in an order
select title from book_order join book on (book_order.isbn = book.isbn)
where order_id = ?

-- insert into book genre
insert into book_genre values (?,?);

-- get genres of a given book
select genre from book_genre where isbn=?;

-- insert author
insert into author values (?,?,?);

-- get authors who wrote a given book
select first_name, last_name from author where isbn=?;

-- insert book_order
insert into book_order values (?,?,?);

-- get the latest order
select max(id) from order_ ;

-- get revenue and expenditures given date range
select sum(revenue) as revenue, sum(expenditures) as expenditures from date_revenue_expenditures
where date between ? and ?;

-- get revenue and expenditures given date range
select genre, sum(revenue) as revenue from date_genre_revenue
where date between ? and ?
group by genre; 

-- get revenue and expenditures given date range
select first_name, last_name, sum(revenue) as revenue from date_author_revenue
where date between ? and ?
group by first_name, last_name;
