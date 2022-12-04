CREATE TABLE IF NOT EXISTS order_dishes
(
    id SERIAL PRIMARY KEY,
    dish_id SERIAL REFERENCES dishes(id),
    order_id SERIAL REFERENCES orders(id),
    quantity INTEGER
);

DROP TABLE dish_in_order;