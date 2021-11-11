### Project Description ###

This is an example of REST API testing using Rest Assured.

System Under Test (SUT): Swagger Pet Store - https://petstore.swagger.io/. The service is provided as a training ground 
for API testing. The data for creating pet entities is generated randomly.

List of some tools and libraries used:
- JUNIT 5
- Maven
- Rest Assured
- Allure-Junit5
- AssertJ
- Javafaker
- Jenkins

**NOTE:** Some API test results may and will occur as failed. That's an expected behavior caused by
the SUT implementation
inconsistencies.


## 1. Prerequisites
Before running tests make sure you have installed: 
- JDK 11: https://openjdk.java.net/projects/jdk/11/
- Maven 3.6.3 and above: https://maven.apache.org/download.cgi
- [Optional] Allure 2.15.0 and above to manually generate reports. See https://docs.qameta.io/allure/ \
for installation guidance

## 2. Running tests locally


#### 2.1 Load the project from the GitHub
Project URL: https://github.com/in7hesky/java-rest-assured-automation

For example, you may load the project using the command line:
```
git clone git@github.com:in7hesky/java-rest-assured-automation.git
```
#### 2.2 Execute run command
As soon as project is loaded, go to the root directory and perform a command:
```
mvn clean test
```
That will run all the tests concurrently and generate the report data. If you wish to run a particular test class you may use:
```
mvn clean test -Dtest="PetDeletionTest"
```
Note that the name of a class is used, not the path.
## 3. Generating the report
The report data always appear after running tests. It's located in `/target/allure-results/`.
You may generate a report using:
```
allure serve target/allure-results
```
**NOTE:** If you want to change the default report data path, you can configure `allure.properties` file located in
`src/test/resources` directory.


### Extra: Jenkins integration
You may also use the `Jenkinsfile` located in the root directory to rapidly build a demo pipeline to see tests runs
and their results as Allure reports.

Make sure you have the "Allure" Jenkins plugin installed!

Refer to https://www.jenkins.io/doc/book/pipeline/ in case you want to find out more
about `Jenkinsfile` usage.



