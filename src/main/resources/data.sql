DELETE FROM votes;
DELETE FROM menu_dish;
DELETE FROM dishes;
DELETE FROM menus;
DELETE FROM restaurants;
DELETE FROM user_roles;
DELETE FROM users;
ALTER SEQUENCE global_seq RESTART WITH 100000;

INSERT INTO users (name, email, password) VALUES
  ('Admin', 'admin@gmail.com', 'admin'),
  ('User01', 'user01@gmail.com', 'user01'),
  ('User02', 'user02@gmail.com', 'user02');

INSERT INTO user_roles (role, user_id) VALUES
  ('ROLE_USER', 100001),
  ('ROLE_ADMIN', 100000),
  ('ROLE_USER', 100000);

INSERT INTO restaurants (name, address) VALUES
  ('McDonalds', 'Lenina 1'),
  ('KFC', 'Sovetskiy 1'),
  ('Burger King', 'Leningradskiy 1');

INSERT INTO menus (restaurant_id, date_time) VALUES
  (100003, '2018-01-26 8:00:00'),
  (100003, '2018-01-27 12:00:00'),
  (100004, '2018-01-30 8:00:00');

INSERT INTO dishes (restaurant_id, name, price) VALUES
  (100003, 'BigMac', 150),
  (100003, 'FreePotato', 100),
  (100003, 'PepsiCola', 50),
  (100004, 'Twister', 200);

INSERT INTO menu_dish (menu_id, dish_id) VALUES
  (100006, 100009),
  (100006, 100010),
  (100007, 100012);

INSERT INTO votes (user_id, restaurant_id, date_time) VALUES
  (100000, 100003, '2018-01-19 10:00:00'),
  (100000, 100003, '2018-01-20 10:00:00'),
  (100001, 100003, '2018-01-19 08:30:00');
