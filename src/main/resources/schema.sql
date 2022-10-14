CREATE TABLE CUSTOMER_ORDER
(
    orderID    INTEGER      NOT NULL AUTO_INCREMENT,
    email      VARCHAR(255) NOT NULL,
    first_name VARCHAR(128),
    last_name  VARCHAR(128),
    productID  VARCHAR(128) NOT NULL,
    PRIMARY KEY (orderID)
);