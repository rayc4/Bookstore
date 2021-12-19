-- Decrease the corresponding book quantities when an order is placed by a user
-- Rollback if not enough stock
create function decrease_book_quantity()
returns trigger
language PLPGSQL
AS $$
begin
    if new.quantity > (select quantity from book where isbn=new.isbn) then
        rollback;
    else
        update book
        set quantity = quantity - new.quantity
        where isbn = new.isbn;
        return new;
    end if;
end;
$$;

create trigger order_made
    before insert on book_order
    for each row
    execute procedure decrease_book_quantity();

-- Order quantity of books based on last month's order. New order of books is
-- lower than 10 books in stock.
create function check_monthly_sales ()
returns trigger
language PLPGSQL
AS $$
declare order_quantity integer;
begin
  if new.quantity < 10 then
    select count(*) into order_quantity
    from book_order
    where isbn = new.isbn and order_id =
    	(select id from order_
    	where date >= date_trunc('month', CURRENT_DATE - interval '1' month)
    	and date < date_trunc('month', CURRENT_DATE));
    update book
    set quantity = quantity + order_quantity
    where isbn = new.isbn;
    return new;
  end if;
end;
$$;


create trigger order_books
	after update on book
	for each row
	execute procedure check_monthly_sales();
