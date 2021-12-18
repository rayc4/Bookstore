-- Increase the corresponding book quantities when an order is placed by a user
create trigger order_made 
    after insert on book_order
    for each row
    execute procedure add_book_quantity();

create function add_book_quantity()
returns trigger
language PLPGSQL
AS $$
begin
    update book 
    set quantity = quantity + new.quantity
    where isbn = new.isbn;
    return new;
end;
$$;
