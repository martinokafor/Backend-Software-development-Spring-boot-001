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

---
CREATE TABLE IF NOT EXISTS customer_order
(
    order_id int auto_increment,
    customer_id int NOT NULL,
    brand varchar(255),
    currency varchar(255),
    price varchar(255),
    consent varchar(255),
    PRIMARY KEY(order_id)
);

---
CREATE TABLE IF NOT EXISTS vehicle
(
    vin varchar(255) NOT NULL,
    vehicle_name varchar(255) NULL,
    model varchar(255) NULL,
    customer_id int NOT NULL,
    order_id int NOT NULL,
    created_on datetime NULL,
    updated_on datetime NULL,
    PRIMARY KEY(vin)
);
---

CREATE TABLE IF NOT EXISTS customer_users
(
    id int auto_increment,
    customer_id int NOT NULL,
    password varchar(255) NOT NULL,
    role varchar(255) NULL,
    user_name varchar(255) NULL,
    PRIMARY KEY(id)
);
---

CREATE TABLE IF NOT EXISTS user_vehicle
(
    user_id int auto_increment,
    vehicle_vin varchar(255) NOT NULL
);

