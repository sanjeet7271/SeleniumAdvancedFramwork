# NAGP Assignment 2

## Features supported
1. Dependency management and project management by using MAVEN 
2. Functional UI Automation by using Selenium
3. Singleton Design pattern used
4. Interface used for batter code maintainability
5. Modular Approach via Page Object model
6. Browser supported - Chrome, IE and Firefox
7. HTML Report by including Extent Reports
8. Logging via Log4j
9. Property Reader to read Test data from properties files.
10. Archived Last execution results by utilizing `NAGPTraining-MM-dd-yy hhmmss a.zip`. in D drive 
11. TestNG Listeners to tackle Skipped Test Case in extent report.
12. Custom assertion to print custom message when assertion fails.
13. Capture screen shots for Passed and failed Test cases.
14. Implicit and Explicit waits are considered for Windows to Load and WebElement to load.
15. Maven is configured in such a way that will run different testNG.xml provided at run time.
16. Used Regex to verify email address and price
17. last run test report zip file added into framework

## Pre-requisite
1. Java above 1.8.
2. Maven version above 3.0.
3. TestNG 6.14.3

## Plugins used
1. Selenium 3.141.59
2. Achache POI 3.17
3. Log4j 1.2.27
4. Extent Report vai aventstack 3.1.5

## How to install & Run using command prompt
1. Please extract the project at your desired path.
2. Go to `/src/main/resources/configuration/configuration.properties` file and update configurations. 
3. Open the command prompt and go to the project path.
4. Run the command "mvn clean install -DsuiteXmlFile=testng.xml"
5. All the automated test cases in the testNG.xml will be executed.
6. or Go to `/src/test/java/com/nagp/runner` and run with right click.

		
## To view Report 
1. Go to the runtime `/runtime/screenshots` where screen shot name save date and time `MMddyyyyhhmmss`
2. All past reports are saved under `Project/extentreport.html` 


Note: Test cases are available in `TestCases.xlsx` for your reference.


