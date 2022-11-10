## Framework structure

### src/test/java

#### test
It contains all Contract and Functional tests using RestAssured.

## Libraries
* [RestAssured](http://rest-assured.io/) library to test REST APIs
* [JUnit 5](https://junit.org/junit5/) to support the test creation
* [Owner](http://owner.aeonbits.org/) to manage the property files
* [Allure Report](https://docs.qameta.io/allure/) as the testing report strategy

## Required software
* Java JDK 11+
* Maven installed and in your classpath

## How to setup project

Clone the project via the command line:
```
git clone 
```

## How to execute the tests
You can open each test class on `src/test/java` and execute all of them, but I recommend you run it by the
command line. It enables us to run in different test execution strategies and, also in a pipeline, that is the repo purpose.

### Running the test suites

The test suites can be run directly by your IDE or by command line.
If you run `mvn surefire:test` all the tests will execute because it's the regular Maven lifecycle to run all the tests.

### Generating the test report

You can use the command line to generate:
* ` allure serve target/surefire-reports
