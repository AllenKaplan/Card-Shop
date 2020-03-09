DROP TABLE if exists Address;

CREATE TABLE Address (
    id INT UNSIGNED NOT NULL AUTO_INCREMENT,
    street VARCHAR(100) NOT NULL,
    province VARCHAR(20) NOT NULL,
    country VARCHAR(20) NOT NULL,
    zip VARCHAR(20) NOT NULL,
    phone VARCHAR(20),
    PRIMARY KEY(id)
);

DROP TABLE if exists PO;

CREATE TABLE PO (
    id INT UNSIGNED NOT NULL AUTO_INCREMENT,
    lname VARCHAR(20) NOT NULL,
    fname VARCHAR(20) NOT NULL,
    status ENUM('ORDERED', 'PROCESSED', 'DENIED') NOT NULL,
    address INT UNSIGNED NOT NULL,
    PRIMARY KEY(id),
    INDEX (address),
    FOREIGN KEY (address) REFERENCES Address (id) ON DELETE CASCADE
);

DROP TABLE if exists POItem;

CREATE TABLE POItem (
    id INT UNSIGNED NOT NULL,
    bid VARCHAR(20) NOT NULL,
    price INT UNSIGNED NOT NULL,
    PRIMARY KEY(id, bid),
    INDEX (id),
    FOREIGN KEY(id) REFERENCES PO(id) ON DELETE CASCADE,
    INDEX (bid),
    FOREIGN KEY(bid) REFERENCES Book(bid) ON DELETE CASCADE
);

InsertingTABLE if exists VisitEvent;

CREATE TABLE VisitEvent (
    day varchar(8) NOT NULL,
    bid varchar(20) not null REFERENCES Book.bid,
    eventtype ENUM('VIEW', 'CART', 'PURCHASE') NOT NULL,
    FOREIGN KEY(bid) REFERENCES Book(bid)
);