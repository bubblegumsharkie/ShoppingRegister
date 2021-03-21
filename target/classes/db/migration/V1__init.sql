CREATE TABLE clients (id serial, name varchar(100));
CREATE TABLE items (id serial, name varchar(100), price decimal);
CREATE TABLE orders (id serial, priceofpurchase varchar(100), client_id integer, order_id integer);

INSERT INTO clients (name) VALUES ('Alex'), ('Gleb'), ('Brad'), ('Peter'), ('John');
INSERT INTO items (name, price) VALUES ('Coffee', 2.99), ('Tea', 1.49), ('Snickers', 0.99);