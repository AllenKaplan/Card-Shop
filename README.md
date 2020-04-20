# Card-Shop
## Overview

## Deploying Locally

Deploying locally is quite simple! In order to do so, you have to have the following programs and frameworks set up:

Java EE
Tomcat
Eclipse Java EE Edition (Recommended)

Once you have all of these included, git clone the repository and you should be ready to go!

Note that sometimes the Web.xml file can cause exceptions to be thrown when run locally. If this happens, you need to change the redirectPath context-parameter value to be the name of the Card-Shop folder on your local machine. By default, this will be set to the root path "/".

## Deploying to Cloud

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