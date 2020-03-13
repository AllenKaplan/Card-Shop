CREATE TABLE Logins (
    username VARCHAR(20) not null,
    password VARCHAR(20) not null,
    PRIMARY KEY (username)
);

CREATE TABLE Accounts (
    username VARCHAR(20) not null,
    fname VARCHAR(25) not null,
    lname VARCHAR(25) not null,
    email VARCHAR(100) not null,
    accountType VARCHAR(13) not null,
    FOREIGN KEY (username) REFERENCES Logins(username),
    CONSTRAINT valid CHECK (accountType = 'CUSTOMER' OR accountType = 'ADMINISTRATOR' OR accountType='VISITOR')
);

CREATE TABLE Addresses (
    addressID INT not null GENERATED ALWAYS AS IDENTITY (START WITH 1 INCREMENT BY 1),
    street VARCHAR(100) not null,
    province VARCHAR(20) not null,
    country VARCHAR(20) not null,
    zip VARCHAR(20) not null,
    phone VARCHAR(20),
    PRIMARY KEY(addressID)
);

CREATE TABLE CreditCards (
    cardID INT not null,
    username VARCHAR(20) not null,
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


