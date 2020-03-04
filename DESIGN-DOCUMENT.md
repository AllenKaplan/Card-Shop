# Card-Shop Design Doc 
## Team Members
Allen Kaplan - 215494925 - allenkap@my.yorku.ca
Damanveer Bharat
Jeremy Winkler
Conner Ahearn

# Table of Contents
- Architecture 
- Implementaion
- Performance Testing REport
- Team Member Contributions
- Signatures

# Architecture
## Specs
### Data
#### Products
Cards are a type of product

### Users
Username and password

### Services
#### Product Serivce
Provides product information
- Name
- Price
- Images
- Reviews
- Category

#### Order Service
Processes orders
- processes payments from user service orders

#### User Service
Manages all user action
- login
- new account

##### Session Controller
- holds shopping cart
- holds other session related values

### Webpages
#### Home page 
Displays the contents of the store organized by category and by product.   
The visitor must be able to 
- UC M1: browse Book Categories (Science, Engineering, Fiction) and see the books available.   
- UC M2: select a book and see the information for that title (price, ratings etc.).   
- UC M3: add a review for book 
- UC M4: search (text search) the store 
- UC M5: add an individual book to shopping cart.  
- UC M6: Shopping cart butto

#### Shopping Cart Page
The Shopping Cart Page allows a visitor to
- UC C1: view all the items  in the shopping basket and their information (price, etc.).   
- UC C2: remove individual items from the shopping cart or increase/decrease the quantity.  While doing so, the total bill is updated. 
- UC C3: “Payment” submit button indicating they wish to purchase the items in the shopping cart. 

#### Payment Page 
The visitor can   
- UC P1: either log into their account with a password, or create a new account. 
- UC P2: for a new account they enter their account name, password, and default billing and shipping information. The new account is submitted to the Order Processing service.    
- UC P3: to submit their order, they verify their billing and shipping information, and enter in their credit card number. 
- UC P4: “Confirm order” button 
Note: You should hard code that every 3rd request is denied on your website.  If the order is approved, you should display “Order Successfully Completed.”   If it is denied, you should display “Credit Card Authorization Failed.

#### Analytics Page
The Administrator should be able to  
- UC A1: generate a report with the books sold each month 
- UC A2: provide real time analytics (Listeners) with most popular products (life time), like “top 10 sold books”) 
- (optional) UC A3: provide annomized reports (Filters) with user buying statistics.  
Note: by statistics, one can understand the amount each user has spent, the zip code of the buyers.  By 
“annomized” one can understand that the user names are replaced on the fly (in the filter) with ****, so the admin cannot identify the buyers (due to privacy concerns).  
 
## About
Describe the patterns you used, the main design decisions, trade-offs 

## UML use cases (?)

## UML Class DIagarams

## Sequence Diagrams (AT LEAST 2)

# Implementation
Here describe the implementation decisions, the trade-offs. Also, discuss the limitations, especially with regard to testing 

## Database
https://www.ibm.com/cloud/learn/relational-databases

## Security
In addition, the store website should run under https, SSL. SSL is activated by setting up the application server, you do not have to program anything special in your application.     
“Payment” page and “Confirm Order” action must be secured so that a login is required and the password is not passed in plain text. The visitor MUST type in their credit card each time.  It should not be stored.

# Performance testing report 
onduct a performance test of your application. For this project focus just on one Service (either B or C above). Test your application with 1, 2…N clients. Draw the throughput and the response time curves. N should be chosen such that utilization of the computer is less than 60% Assume 3 seconds “think time” between user requests. An implementation suggestion: you can emulate each client with a thread…record the time the thread sends the request, record the time the thread gets back the response; the difference is the response time. Repeat that for 1, 2…N threads. Check the slides for the general shape of the response time…By response time we mean the average across all clients(threads) 

# team member contributions
In one paragraph, describe how the team worked, how often you met and how you collaborated. Then for each team member, detail the individual contributions in one paragraph/member; also explain how each team member learned about elements of the projects done by other members.  

# Signatures 
![alt text](https://github.com/adam-p/markdown-here/raw/master/src/common/images/icon48.png "Allen Kaplan's Signature")
![alt text](https://github.com/adam-p/markdown-here/raw/master/src/common/images/icon48.png "Damanveer Bharat's Signature")
![alt text](https://github.com/adam-p/markdown-here/raw/master/src/common/images/icon48.png "Jeremy Winkler's Signature")
![alt text](https://github.com/adam-p/markdown-here/raw/master/src/common/images/icon48.png "Conner Ahearn's Signature")
Each member of the team sign the document to attest that team member contributions reflect the reality