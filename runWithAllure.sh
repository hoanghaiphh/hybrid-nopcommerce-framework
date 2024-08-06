called_path=${0%/*}
project_path=${called_path#[^/]*}
cd "$project_path"
java -javaagent:"$project_path/libraries/allure/aspectjweaver-1.9.22.1.jar" -classpath "$project_path/out/production/hybrid-nopcommerce-framework:$project_path/libraries/allure/*:$project_path/libraries/extentReports/*:$project_path/libraries/log4j-reportng/*:$project_path/libraries/selenium-testng/*" org.testng.TestNG "$project_path/resources/allure.xml"
source ~/.bash_profile
allure serve ./allure-results/
#kill `ps -A | grep -w Terminal.app | grep -v grep | awk '{print $1}'`