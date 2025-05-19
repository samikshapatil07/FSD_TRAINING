create database ecommerce;

use ecommerce;

-- drop database ecommerce;

-- create table queries
CREATE TABLE customer (
    id INT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(45),
    city VARCHAR(45)
);

CREATE TABLE category (
    id INT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(45)
);

CREATE TABLE product (
    id INT PRIMARY KEY AUTO_INCREMENT,
    title VARCHAR(45),
    price DOUBLE,
    description VARCHAR(100),
    category_id INT,
    FOREIGN KEY (category_id) REFERENCES category(id)
);

CREATE TABLE purchase (
    id INT PRIMARY KEY AUTO_INCREMENT,
    date_of_purchase VARCHAR(45),
    customer_id INT,
    product_id INT,
    quantity INT NOT NULL,
    total_price DECIMAL(10,2) NOT NULL,
    coupon VARCHAR(20),
    FOREIGN KEY (customer_id) REFERENCES customer(id),
    FOREIGN KEY (product_id) REFERENCES product(id)
);

-- sample data insertion
INSERT INTO customer (name, city) VALUES 
('John Doe', 'Mumbai'),
('Jane Smith', 'Chennai'),
('Ravi Kumar', 'Delhi'),
('Ayesha Khan', 'Hyderabad'),
('Ankit Mehta', 'Pune');

INSERT INTO category (name) VALUES 
('Mobiles'),
('Laptops'),
('Tablets'),
('Accessories'),
('Televisions');
