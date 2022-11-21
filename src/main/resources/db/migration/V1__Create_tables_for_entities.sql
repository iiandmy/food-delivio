CREATE TABLE IF NOT EXISTS users
(
    id BIGINT NOT NULL,
    name VARCHAR(255),
    delivery_address VARCHAR(255),
    CONSTRAINT user_pkey PRIMARY KEY (id)
);

CREATE TABLE IF NOT EXISTS restaurants
(
    id BIGINT NOT NULL,
    name VARCHAR(255),
    address VARCHAR(255),
    work_time VARCHAR(255),
    CONSTRAINT restaurant_pkey PRIMARY KEY (id)
);

CREATE TABLE IF NOT EXISTS reviews
(
    id BIGINT NOT NULL,
    user_id BIGINT NOT NULL,
    restaurant_id BIGINT NOT NULL,
    CONSTRAINT review_pkey PRIMARY KEY (id),

    CONSTRAINT user_id FOREIGN KEY (user_id)
    REFERENCES users (id) MATCH SIMPLE
    ON UPDATE CASCADE
    ON DELETE NO ACTION,

    CONSTRAINT restaurant_id FOREIGN KEY (restaurant_id)
    REFERENCES restaurants (id) MATCH SIMPLE
    ON UPDATE CASCADE
    ON DELETE NO ACTION
);

CREATE TABLE IF NOT EXISTS menus
(
    id BIGINT NOT NULL,
    name VARCHAR(255),
    restaurant_id BIGINT NOT NULL,
    CONSTRAINT menu_pkey PRIMARY KEY (id),

    CONSTRAINT restaurant_id FOREIGN KEY (restaurant_id)
    REFERENCES restaurants (id) MATCH SIMPLE
    ON UPDATE CASCADE
    ON DELETE NO ACTION
);

CREATE TABLE IF NOT EXISTS dishes
(
    id BIGINT NOT NULL,
    name VARCHAR(255),
    image VARCHAR(255),
    description VARCHAR(255),
    price REAL,
    calories INT,
    menu_id BIGINT NOT NULL,
    CONSTRAINT dish_pkey PRIMARY KEY (id),

    CONSTRAINT menu_id FOREIGN KEY (menu_id)
    REFERENCES menus (id) MATCH SIMPLE
    ON UPDATE CASCADE
    ON DELETE NO ACTION
);