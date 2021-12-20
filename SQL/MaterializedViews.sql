-- revenue vs. expenses
create materialized view basic_stats as
select * from
    (
        select sum(addend)::numeric(10,2) as revenue from (
            select (sum(book_order.quantity) * price) * (1 - publisher_cut) as addend
            from book join book_order using (isbn)
            group by isbn
        ) as revenue_per_book
    ) as revenue
natural join
    (
        select sum(addend)::numeric(10,2) as expenses from (
            select (sum(book_order.quantity) * price) * publisher_cut as addend
            from book join book_order using (isbn)
            group by isbn
        ) as expense_per_book
    ) as expenses;

-- revenue per genre
create materialized view genre_revenue as
select genre, sum(book_revenue)::numeric(10,2) as revenue from book_genre natural join (
    select isbn, (sum(book_order.quantity) * price) * (1 - publisher_cut) as book_revenue
    from book join book_order using (isbn)
    group by isbn) as _
group by genre;


-- revenue per author last month
create materialized view author_revenue as
select first_name, last_name, sum(book_revenue)::numeric(10,2) as revenue from author natural join (
	select isbn, (sum(book_order.quantity) * price) * (1 - publisher_cut) as book_revenue
	from book join book_order using (isbn)
	where order_id =
		(select id from order_
		where date >= date_trunc('month', CURRENT_DATE - interval '1' month)
		and date < date_trunc('month', CURRENT_DATE))
	group by isbn) as _
group by first_name, last_name;
