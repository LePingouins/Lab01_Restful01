DROP TABLE IF EXISTS customer_phonenumbers;
DROP TABLE IF EXISTS customers;
DROP TABLE IF EXISTS employees;
DROP TABLE IF EXISTS vehicles;
DROP TABLE IF EXISTS vehicle_options;
DROP TABLE IF EXISTS inventories;
DROP TABLE IF EXISTS sales;



CREATE TABLE IF NOT EXISTS customers (
    id INTEGER NOT NULL AUTO_INCREMENT PRIMARY KEY,
    customer_id VARCHAR(50)UNIQUE NOT NULL,
    last_name VARCHAR(50),
    first_name VARCHAR(50),
    email_address VARCHAR(50),
    street_address VARCHAR(50),
    postal_code VARCHAR(50),
    city VARCHAR(50),
    province VARCHAR(50),
    username VARCHAR(50),
    password VARCHAR(50)
    );

CREATE TABLE IF NOT EXISTS customer_phonenumbers (
    customer_id VARCHAR(50) NOT NULL,
    type VARCHAR(50),
    number VARCHAR(50),
    FOREIGN KEY (customer_id) REFERENCES customers(id) ON DELETE CASCADE
    );


CREATE TABLE IF NOT EXISTS employees (
    id INTEGER NOT NULL AUTO_INCREMENT PRIMARY KEY,
    employee_id VARCHAR(10) UNIQUE NOT NULL,
    first_name VARCHAR(50) NOT NULL,
    last_name VARCHAR(50) NOT NULL,
    dob DATE NOT NULL,
    email VARCHAR(100) UNIQUE NOT NULL,
    title VARCHAR(50) NOT NULL,
    salary DECIMAL(10, 2) NOT NULL
    );
CREATE TABLE IF NOT EXISTS inventories (
                                           id INTEGER NOT NULL AUTO_INCREMENT PRIMARY KEY,
                                           inventory_id VARCHAR(36) UNIQUE,
    type VARCHAR(100)
    );
CREATE TABLE IF NOT EXISTS vehicle_options (
                                 vehicle_id INTEGER,
                                 name VARCHAR(100),
                                 description VARCHAR(200),
                                 cost DECIMAL(19,2)
);

CREATE TABLE IF NOT EXISTS vehicles (
                          id INTEGER NOT NULL AUTO_INCREMENT PRIMARY KEY,
                          vin VARCHAR(17) UNIQUE,
                          inventory_id VARCHAR(36),
                          vehicle_status VARCHAR(50),
                          usage_type VARCHAR(50),
                          vehicle_year INTEGER,
                          manufacturer VARCHAR(100),
                          make VARCHAR(50),
                          model VARCHAR(75),
                          body_class VARCHAR(100),
                          msrp DECIMAL(19, 2),
                          cost DECIMAL(19, 2),
                          total_options_cost DECIMAL(19, 2)

);
CREATE TABLE IF NOT EXISTS sales (
                                     id INTEGER NOT NULL AUTO_INCREMENT PRIMARY KEY,
                                     purchase_id VARCHAR(36) UNIQUE,
    inventory_id VARCHAR(36),
    vin VARCHAR(17),
    customer_id VARCHAR(36),
    employee_id VARCHAR(36),
    amount DECIMAL(19,2),
    currency VARCHAR(3),
    payment_currency VARCHAR(3),
    sale_status VARCHAR(50),
    number_of_monthly_payments INTEGER,
    monthly_payment_amount DECIMAL(19,2),
    down_payment_amount DECIMAL(19,2),
    sale_offer_date DATE,
    warranty_end_date DATE,
    warranty_terms VARCHAR(250)
    );










