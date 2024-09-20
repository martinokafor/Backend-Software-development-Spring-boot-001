CREATE TABLE IF NOT EXISTS customer
(
    customer_id int auto_increment,
    customer_name varchar(255) NULL,
    city varchar(255) NULL,
    no_of_vehicle int NULL,
    created_on datetime NULL,
    updated_on datetime NULL,
    PRIMARY KEY(customer_id)
);