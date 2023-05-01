create table orders
(
    id          bigserial primary key,
    /* user_id     bigint not null references users (id),*/
    total_price int not null
);

create table order_items
(
    id                bigserial primary key,
    title              varchar(64) not null,
    quantity          int    not null,
    price_per_product int    not null,
    price             int    not null,
    order_id          bigint not null references orders (id)
)