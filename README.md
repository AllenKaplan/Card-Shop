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

## File Structure