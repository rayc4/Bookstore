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


--create trigger call_order
    --after update
    --on book for each row
  --  when
  --  quantity < 10
  --  begin
  --  insert into book_order(order_id, isbn, quantity)
  --  values (NEW.order_id, NEW.isbn, ())
  --  end;