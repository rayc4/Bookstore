create trigger call_order
after update
on book for each row
when
quantity < 10
begin
insert into book_order(order_id, isbn, quantity)
values (NEW.order_id, NEW.isbn, ())
end; 
