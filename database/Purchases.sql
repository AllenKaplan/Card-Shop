CREATE TABLE Orders(
    orderID INT not null GENERATED ALWAYS AS IDENTITY (START WITH 1 INCREMENT BY 1),
    username VARCHAR(20) not null,
    itemNumber INT not null, 
    itemName varchar(200) not null,
    salePrice REAL not null,
    quantity INT not null, 
    creditCardID BIGINT not null,
    deliveryAddress INT not null, 
    datePurchased DATE not null,
    dateDelivered DATE,
    status VARCHAR(10) NOT NULL,
    PRIMARY KEY (orderID),
    FOREIGN KEY (username) REFERENCES Logins(username),
    FOREIGN KEY (itemNumber) REFERENCES Cards(number),
    FOREIGN KEY (creditCardID) REFERENCES CreditCards(cardNumber),
    FOREIGN KEY (deliveryAddress) REFERENCES Addresses(addressID),
    CONSTRAINT valid_status CHECK (status = 'PROCESSING' OR status='ENROUTE' OR status='DELIVERED' OR status='DENIED')
);

