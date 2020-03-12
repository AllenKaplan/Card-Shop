DROP TABLE IF EXISTS Logins;
DROP TABLE IF EXISTS Accounts;
DROP TABLE IF EXISTS Addresses;
DROP TABLE IF EXISTS CreditCards;

CREATE TABLE Logins (
    username VARCHAR(20) not null,
    password VARCHAR(20) not null,
    PRIMARY KEY (username)
);

CREATE TABLE Accounts (
    username INT not null,
    fname VARCHAR(25) not null,
    lname VARCHAR(25) not null,
    email VARCHAR(100) not null,
    accountType ENUM('CUSTOMER', 'ADMIN', 'VISITOR') not null,
    FOREIGN KEY (username) REFERENCES Logins(username)
);

CREATE TABLE Addresses (
    addressID INT unsigned not null auto_increment,
    street VARCHAR(100) not null,
    province VARCHAR(20) not null,
    country VARCHAR(20) not null,
    zip VARCHAR(20) not null,
    phone VARCHAR(20),
    PRIMARY KEY(addressID)
);

CREATE TABLE CreditCards (
    cardID INT not null,
    username INT not null,
    billingAddressID INT not null,
    cardNumber INT not null, 
    holderName VARCHAR(100),
    expirationMonth INT not null,
    expirationDay INT not null,
    cvv INT not null,
    PRIMARY KEY (username),
    FOREIGN KEY (username) REFERENCES Logins(username),
    FOREIGN KEY (billingAddressID) REFERENCES Addresses(addressID)
);


