-- revenue, expenditure by day
create materialized view date_revenue_expenditures as
select date, sum(b.price * o.quantity) as revenue, sum(b.expenditures * o.quantity) as expenditures from (
    (select isbn, price, (price * publisher_cut) as expenditures from book natural join book_genre) as b
    natural join
    (select date, isbn, quantity from book_order join order_ on (book_order.order_id = order_.id)) as o
) group by (date)

-- revenue by genre AND date
create materialized view date_genre_revenue as
select date, genre, sum(b.price * o.quantity) as revenue from (
    (select isbn, price, genre from book natural join book_genre) as b
    natural join
    (select date, isbn, quantity from book_order join order_ on (book_order.order_id = order_.id)) as o
) group by (date,  genre)

-- revenue by author AND date
create materialized view date_author_revenue as
select date, first_name, last_name, sum(b.price * o.quantity) as revenue from (
    (select isbn, price, first_name, last_name from book natural join author) as b
    natural join
    (select date, isbn, quantity from book_order join order_ on (book_order.order_id = order_.id)) as o
) group by (date,  first_name, last_name)
