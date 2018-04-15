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
  ('ROLE_USER', 100002),
  ('ROLE_ADMIN', 100000),
  ('ROLE_USER', 100000);

INSERT INTO restaurants (name, address) VALUES
  ('McDonalds', 'Lenina 1'),
  ('KFC', 'Sovetskiy 1'),
  ('Burger King', 'Leningradskiy 1'),
  ('Podorojnik', 'Pionersky 1'),
  ('Silver Food', 'Krasnoarmeyskaya 1');

INSERT INTO menus (restaurant_id, date_time) VALUES
  (100003, '2018-04-15 8:00:00'),
  (100004, '2018-04-15 8:00:00'),
  (100005, '2018-04-15 8:00:00'),
  (100006, '2018-04-15 8:00:00'),
  (100007, '2018-04-15 8:00:00');

INSERT INTO dishes (restaurant_id, name, price) VALUES
  (100003, 'BigMac', 150),
  (100003, 'Pepsi', 50),

  (100004, 'Twister', 200),
  (100004, 'CocaCola', 50),

  (100005, 'DoubleBurger', 300),
  (100005, 'MDew', 60),

  (100006, 'Tapochek', 80),
  (100006, 'Mors', 40),

  (100007, 'Pizza', 120),
  (100007, 'Juice', 80);

INSERT INTO menu_dish (menu_id, dish_id) VALUES
  (100008, 100013),
  (100008, 100014),

  (100009, 100015),
  (100009, 100016),

  (100010, 100017),
  (100010, 100018),

  (100011, 100019),
  (100011, 100020),

  (100012, 100021),
  (100012, 100022);

INSERT INTO users (name, email, password) VALUES
  ('User03', 'user03@gmail.com', 'user03'),
  ('User04', 'user04@gmail.com', 'user04'),
  ('User05', 'user05@gmail.com', 'user05');

INSERT INTO votes (user_id, restaurant_id, local_date) VALUES
  (100000, 100003, '2018-04-15'),
  (100001, 100004, '2018-04-15'),
  (100002, 100003, '2018-04-15'),
  (100023, 100004, '2018-04-15'),
  (100024, 100004, '2018-04-15'),
  (100025, 100005, '2018-04-15');
