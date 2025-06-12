CREATE TABLE IF NOT EXISTS products (
    id SERIAL PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    precio DECIMAL(10, 2) NOT NULL
);

INSERT INTO products (name, precio) VALUES
('Producto 1', 100.50),
('Producto 2', 200.75),
('Producto 3', 50.00);

CREATE TABLE IF NOT EXISTS customers (
    id SERIAL PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    email VARCHAR(255) UNIQUE NOT NULL
);

insert into customers(name, email) values ('Juan Perez','juan@example.com') ON CONFLICT (email) DO NOTHING;