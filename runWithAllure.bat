set ProjectPath=%~dp0
cd %ProjectPath%
set p=%PATH%
java -javaagent:"%ProjectPath%libraries\allure\aspectjweaver-1.9.22.1.jar" -classpath "%ProjectPath%out\production\hybrid-nopcommerce-framework;%ProjectPath%libraries\allure\*;%ProjectPath%libraries\extentReports\*;%ProjectPath%libraries\log4j-reportng\*;%ProjectPath%libraries\selenium-testng\*;" org.testng.TestNG "%ProjectPath%resources\allure.xml"
allure serve .\allure-results\
pause