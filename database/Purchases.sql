CREATE TABLE Orders(
    orderID INT not null,
    username VARCHAR(20) not null,
    creditCardID INT not null,
    deliveryAddress INT not null, 
    totalPrice REAL not null,
    datePurchased DATE not null,
    dateDelivered DATE,
    status VARCHAR(10) NOT NULL,
    PRIMARY KEY (orderID),
    FOREIGN KEY (username) REFERENCES Logins(username),
    FOREIGN KEY (creditCardID) REFERENCES CreditCards(cardNumber),
    FOREIGN KEY (deliveryAddress) REFERENCES Addresses(addressID),
    CONSTRAINT valid_status CHECK (status = 'PROCESSING' OR status='ENROUTE' OR status='DELIVERED' OR status='DENIED')
);

CREATE TABLE ItemsPurchased(
    orderID INT not null, 
    itemNumber INT not null, 
    salePrice REAL not null,
    quantity INT not null, 
    FOREIGN KEY (orderID) REFERENCES Orders(orderID),
    FOREIGN KEY (itemNumber) REFERENCES Cards(number)
);