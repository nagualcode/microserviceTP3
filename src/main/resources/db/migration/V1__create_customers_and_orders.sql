-- Tabela de Customer
CREATE TABLE customer (
    id SERIAL PRIMARY KEY,
    email VARCHAR(255) NOT NULL,
    name VARCHAR(255) NOT NULL
);

INSERT INTO customer (email, name) VALUES ('test@example.com', 'Test Customer');

-- Tabela de KitchenOrder
CREATE TABLE kitchen_order (
    id SERIAL PRIMARY KEY,
    product VARCHAR(255) NOT NULL,
    quantity INT NOT NULL,
    total_price NUMERIC NOT NULL,
    customer_id INT,
    CONSTRAINT fk_customer
      FOREIGN KEY(customer_id) 
      REFERENCES customer(id)
);
