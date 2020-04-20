CREATE TABLE Logins (
    username VARCHAR(20) not null,
    password VARCHAR(40) not null,
    PRIMARY KEY (username)
);

CREATE TABLE Accounts (
    username VARCHAR(20) not null,
    fname VARCHAR(25) not null,
    lname VARCHAR(25) not null,
    email VARCHAR(100) not null,
    address INT not null,
    phone VARCHAR(20),
    accountType VARCHAR(13) not null,
    FOREIGN KEY (username) REFERENCES Logins(username),
    FOREIGN KEY (address) REFERENCES Addresses(addressID),
    CONSTRAINT valid CHECK (accountType = 'CUSTOMER' OR accountType = 'ADMINISTRATOR' OR accountType='VISITOR')
);

CREATE TABLE Addresses (
    addressID INT not null GENERATED ALWAYS AS IDENTITY (START WITH 1 INCREMENT BY 1),
    street VARCHAR(100) not null,
    province VARCHAR(50) not null,
    city VARCHAR(50) not null,
    zip VARCHAR(20) not null,
    PRIMARY KEY(addressID)
);


