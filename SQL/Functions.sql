-- get genres and their revenue given start and end date
create function get_genre_revenue(start_date date, end_date date)
returns table(genre varchar(20), revenue numeric(10,2))
language plpgsql
as $$
begin
    select genre, sum(book_revenue)::numeric(10,2) as revenue from book_genre natural join (
        select isbn, (sum(bo.quantity) * price) * (1 - publisher_cut) as book_revenue
            from book join (
                select * from book_order
                where order_id in
                (select id from order_ where date between start_date and end_date)
            ) as bo using (isbn)
            group by isbn
    ) as _
    group by genre;
end; $$

-- get author and their revenue given start and end date
create function get_author_revenue(start_date date, end_date date)
returns table(author varchar(20), revenue numeric(10,2))
language plpgsql
as $$
begin
    select first_name, last_name, sum(book_revenue)::numeric(10,2) as revenue from author natural join (
        select isbn, (sum(bo.quantity) * price) * (1 - publisher_cut) as book_revenue
            from book join (
                select * from book_order
                where order_id in
                (select id from order_ where date between start_date and end_date)
            ) as bo using (isbn)
            group by isbn
    ) as _
    group by first_name, last_name;
end;
$$
