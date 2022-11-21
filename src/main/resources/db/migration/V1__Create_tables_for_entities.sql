CREATE TABLE IF NOT EXISTS users
(
    id SERIAL PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    delivery_address VARCHAR(255)
);

CREATE TABLE IF NOT EXISTS restaurants
(
    id SERIAL PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    address VARCHAR(255) NOT NULL,
    work_time VARCHAR(255) NOT NULL
);

CREATE TABLE IF NOT EXISTS reviews
(
    id SERIAL PRIMARY KEY,
    user_id SERIAL REFERENCES users,
    restaurant_id SERIAL REFERENCES restaurants
);

CREATE TABLE IF NOT EXISTS menus
(
    id SERIAL PRIMARY KEY,
    name VARCHAR(255),
    restaurant_id SERIAL REFERENCES restaurants
);

CREATE TABLE IF NOT EXISTS dishes
(
    id SERIAL PRIMARY KEY,
    name VARCHAR(255),
    image VARCHAR(255),
    description VARCHAR(255),
    price REAL,
    calories INT,
    menu_id SERIAL REFERENCES menus
);