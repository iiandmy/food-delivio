CREATE TABLE IF NOT EXISTS roles
(
    id SERIAL PRIMARY KEY,
    name VARCHAR(255)
);

CREATE TABLE IF NOT EXISTS user_roles
(
    user_id SERIAL REFERENCES users(id),
    role_id SERIAL REFERENCES roles(id),
    PRIMARY KEY (user_id, role_id)
);