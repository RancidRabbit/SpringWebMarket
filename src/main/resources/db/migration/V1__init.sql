create table categories (id bigserial primary key, title varchar(40));
insert into categories (title) values
('Оружие'),
('Доспехи'),
('Магические предметы');


create table items (id bigserial primary key, title varchar(25), price int, category_id bigint references categories(id));

insert into items (title, price, category_id) values
('Меч', 100, 1),
('Копье', 110, 1),
('Кинжал', 90, 1),
('Булава', 120, 1),
('Топор', 130, 1),
('Щит', 123, 2),
('Латный доспех', 100, 2),
('Кольчуга', 112, 2),
('Кожаный доспех', 110, 2),
('Шлем', 80, 2),
('Сапоги', 60, 2),
('Перчатки', 40, 2),
('Посох', 200, 3),
('Двуручный меч', 300, 1),
('Роба', 230, 3),
('Пояс', 35, 2),
('Арбалет', 170, 1),
('Алебарда', 400, 1),
('Кольцо', 4000, 3),
('Амулет', 6000, 3),
('Ring of Ant Queen', 50000, 3);


