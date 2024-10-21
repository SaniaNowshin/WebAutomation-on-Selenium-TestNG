##  About this project 
This repository contains Selenium TestNG project that automates a webform.
## Scenario:
Automate the following webform: https://dailyfinance.roadtocareer.net/ 

## Technology used:
    1. Language: Java
    2. Build System: Gradle
    3. Automation tool and framework: Selenium and TestNG
    4. Data manipulation: Simple JSON and CSV Parser

## Project Flow:
  1. Log in with Admin credentials:
  2. Register new users.
  3. Log in as admin and check if the last registered user is displayed on the admin dashboard and assert it against the saved JSON data for the user.
  4. Log in with the last registered user and update their profile image.
  5. Add a cost/expenditure from a CSV file and assert it against your expected total sum of the amounts. Search for an item by name from the list and assert that the total cost matches the item's price.
 

## How to run this project

    Clone the project
    Open the project from IntellIJ; File>Open>Select and expand folder>Open as project
    Hit this command: gradle clean test -PsuiteName="regressionSuite.xml" to run the regression suite or gradle clean test -PsuiteName="smokeSuite.xml"
    Generate Allure report:
    allure generate allure-results --clean -output allure serve allure-results
    
## Reports 


![10](https://github.com/user-attachments/assets/caf0f142-1975-4572-8dfc-6d2827802fcf)
![9](https://github.com/user-attachments/assets/4af3ccb4-2513-4d07-97df-34b18d8a5bbe)

## Video






https://github.com/user-attachments/assets/266c8919-7d21-4192-8e98-e1d41bd7b5b5













