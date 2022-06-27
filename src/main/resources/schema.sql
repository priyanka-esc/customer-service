create schema if not exists testdb;

CREATE TABLE IF NOT EXISTS customer
(
    c_id BIGINT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    c_uuid varchar(20),
    c_name varchar(50) not null
);

CREATE TABLE IF NOT EXISTS orders(
    o_id BIGINT PRIMARY KEY,
    o_uuid varchar(20),
    o_name varchar(50),
   o_customer_id BIGINT,
  FOREIGN KEY (o_customer_id) REFERENCES customer(c_id)
);

CREATE TABLE IF NOT EXISTS order_history
(
    oh_id BIGINT PRIMARY KEY,
    oh_last_modified date,
    oh_quantity float ,
    oh_quantity_unit varchar(20),
    oh_order_id BIGINT,
    FOREIGN KEY (oh_order_id) REFERENCES orders(o_id)
);
