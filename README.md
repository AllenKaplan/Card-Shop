# Card-Shop

## Overview
The Card Shop is an interactive e-commerce platform based on the fictional store from the television Series Hunter x Hunter. The Card Shop is an interpretation of the card shop from the series with all cards available for purchase. Cards are represented by an id, a class rating (SS, S, A...F), type category and limit (on the number of cards that can be purchased). Users are able to query the store through a search box or by card categories such as class rating or type. Further, the  platform provides REST endpoints for users who are looking to get card and order information directly. Through REST endpoints, partners can integrate the Card Shop platform into their services.


## Deploying Locally

Deploying locally is quite simple! In order to do so, you have to have the following programs and frameworks set up:

Java EE
Tomcat
Eclipse Java EE Edition (Recommended)

Once you have all of these included, git clone the repository and you should be ready to go!

If DatabaseAccess.java is throwing exceptions, then anywhere the DEPLOYMENT_ACCESS string is being used needs to be changed to DEVELOPMENT_ACCESS.

Note that sometimes the Web.xml file can cause exceptions to be thrown when run locally. If this happens, you need to change the redirectPath context-parameter value to be the name of the Card-Shop folder on your local machine. By default, this will be set to the root path "/".

## Deploying to Cloud

THE CLOUD APPLICATION CAN BE FOUND HERE: https://eecs4413-cardshop.mybluemix.net/home 

In order to deploy this java application onto the IBM cloud foundry, ensure you have the following resources: 

Eclipse Java EE Edition (Recommended) or your preferred IDE
Cloud Foundry Software installed on your IDE
A valid IBM cloud foundry account

Firstly, set up the IBM cloud foundry server in your IDE so that Cloud Foundry appears as an option when you are trying to run an application.

Secondly, navigate to the class /Card-Shop/src/dao/DatabaseAccess.java and ensure that on the last line of this class, the constructor argument for the instance is set to DEPLOYMENT_ACCESS and not DEVELOPMENT_ACCESS. This will let your cloud application connect to the database. 

Thirdly, navigate to the /Card-Shop/WebContent/WEB-INF/web.xml file and change the "redirectPath" context-parameter value to "/" (no quotations).  

Lastly, navigate to the following class, /Card-Shop/src/controller/Shop.java. Right click -> run as -> Cloud Foundry to deploy this application on the IBM foundry.

The card shop will now be deployed on cloud! :)

## File Structure
![image](https://user-images.githubusercontent.com/35306396/79798985-c6c53e80-8327-11ea-8148-b5923fd9cb7a.png)

From the image above, we can see that the main java code is separated into 6 packages: 
- bean - Contains all the beans which hold spcific data
- controller - Each controller deals with tasks realted to it
- dao - A separate dao is used for each model
- listeners - Our one listener used for analytics
- model - Each model is used for accessing related data
- rest - Two rest services for partners

The database folder contains all our SQL queries ran to create tables

The WebContent folder contains all our jspx files for the user.

Within WebContent/resources there are two folders, one for javascript and one for sql.
