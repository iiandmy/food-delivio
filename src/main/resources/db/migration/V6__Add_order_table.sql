CREATE TABLE IF NOT EXISTS orders
(
    id SERIAL PRIMARY KEY,
    user_id SERIAL REFERENCES users(id),
    order_date VARCHAR(255)
);

CREATE TABLE IF NOT EXISTS dish_in_order
(
    order_id SERIAL REFERENCES orders(id),
    dish_id SERIAL REFERENCES dishes(id),
    PRIMARY KEY (order_id, dish_id)
);

ALTER TABLE reviews
    ADD order_id SERIAL REFERENCES orders(id);

ALTER TABLE reviews
    ADD review_date VARCHAR(255);
