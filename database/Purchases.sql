DROP TABLE IF EXISTS Orders;
DROP TABLE IF EXISTS ItemsPurchased; 

CREATE TABLE Orders(
    orderID INT not null,
    username INT not null,
    creditCardID INT not null,
    deliveryAddress INT not null, 
    totalPrice REAL not null,
    datePurchased DATE not null,
    dateDelivered DATE,
    status ENUM('PROCESSING','ENROUTE','DELIVERED','DENIED'),
    PRIMARY KEY (orderID),
    FOREIGN KEY (username) REFERENCES Users.Logins(username),
    FOREIGN KEY (creditCardID) REFERENCES Users.CreditCards(cardID),
    FOREIGN KEY (deliveryAddress) REFERENCES Users.Addresses(addressID)
);

CREATE TABLE ItemsPurchased(
    orderID INT not null, 
    itemNumber INT not null, 
    salePrice REAL not null,
    quantity INT not null, 
    FOREIGN KEY (orderID) REFERENCES Orders(orderID),
    FOREIGN KEY (itemNumber) REFERENCES Cards.Cards(number)
);