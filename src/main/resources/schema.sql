DROP TABLE IF EXISTS votes;
DROP TABLE IF EXISTS menu_dish;
DROP TABLE IF EXISTS dishes;
DROP TABLE IF EXISTS menus;
DROP TABLE IF EXISTS restaurants;
DROP TABLE IF EXISTS user_roles;
DROP TABLE IF EXISTS users;
DROP SEQUENCE IF EXISTS global_seq;

CREATE SEQUENCE global_seq
  START 100000;

CREATE TABLE users
(
  id       INTEGER PRIMARY KEY DEFAULT nextval('global_seq'),
  name     VARCHAR                 NOT NULL,
  password VARCHAR                 NOT NULL,
  email    VARCHAR                 NOT NULL,
  enabled  BOOL DEFAULT TRUE       NOT NULL
);
CREATE UNIQUE INDEX users_unique_email_idx
  ON users (email);

CREATE TABLE user_roles
(
  user_id INTEGER NOT NULL,
  role    VARCHAR,
  CONSTRAINT user_roles_idx UNIQUE (user_id, role),
  FOREIGN KEY (user_id) REFERENCES users (id) ON DELETE CASCADE
);

CREATE TABLE restaurants (
  id      INTEGER PRIMARY KEY DEFAULT nextval('global_seq'),
  name    VARCHAR NOT NULL,
  address VARCHAR NOT NULL
);

CREATE TABLE menus (
  id            INTEGER PRIMARY KEY DEFAULT nextval('global_seq'),
  restaurant_id INTEGER   NOT NULL,
  local_date     DATE NOT NULL,
  FOREIGN KEY (restaurant_id) REFERENCES restaurants (id) ON DELETE CASCADE
);
CREATE UNIQUE INDEX menus_unique_restaurant_localdate_idx
  ON menus (restaurant_id, local_date);

CREATE TABLE dishes (
  id            INTEGER PRIMARY KEY DEFAULT nextval('global_seq'),
  restaurant_id INTEGER NOT NULL,
  name          VARCHAR NOT NULL,
  price         INT     NOT NULL,
  FOREIGN KEY (restaurant_id) REFERENCES restaurants (id) ON DELETE CASCADE
);

CREATE TABLE menu_dish (
  menu_id INTEGER REFERENCES menus (id) ON UPDATE CASCADE ON DELETE CASCADE,
  dish_id INTEGER REFERENCES dishes (id) ON UPDATE CASCADE ON DELETE CASCADE,
  CONSTRAINT menus_dishes_pkey PRIMARY KEY (menu_id, dish_id)
);

CREATE TABLE votes (
  id            INTEGER PRIMARY KEY DEFAULT nextval('global_seq'),
  user_id       INTEGER   NOT NULL,
  restaurant_id INTEGER   NOT NULL,
  date_time     TIMESTAMP NOT NULL,
  FOREIGN KEY (user_id) REFERENCES users (id) ON DELETE CASCADE,
  FOREIGN KEY (restaurant_id) REFERENCES restaurants (id) ON DELETE CASCADE
);
CREATE UNIQUE INDEX votes_unique_user_datetime_idx
  ON votes (user_id, date_time);







