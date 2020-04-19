INSERT INTO ADDRESSES 
(Street,Province,city,zip)
VALUES
('4700 Keele St','ON','Toronto','M3J 1P3');

INSERT INTO LOGINS
VALUES
('terribleDad','boarman');

INSERT INTO ACCOUNTS
(username,fname,lname,email,address,phone,accounttype)
VALUES
('terribleDad','Ging','Freecss','ging@gmail.com',1,'9051234567','ADMINISTRATOR');

INSERT INTO ORDERS
(username,itemnumber,itemname,saleprice,quantity,deliveryaddress,datePurchased,datedelivered,status)
VALUES
('terribleDad',1,'Patch of Forest (Area of Dense Forest)',33.33,2,1,'2019-02-19',null,'ENROUTE'),
('terribleDad',2,'Plot of Beach (Strip of Beach)',33.33,1,1,'2019-03-04',null,'ENROUTE'),
('terribleDad',1,'Patch of Forest (Area of Dense Forest)',33.33,1,1,'2019-03-04',null,'ENROUTE'),
('terribleDad',2,'Plot of Beach (Strip of Beach)',33.33,2,1,'2019-03-12',null,'ENROUTE');

