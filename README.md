# Bertoni Test

## Design Considerations
* Testability
* Performance is not a main concern

## Maven
This project is build using maven, so you can issue test, build, and install. Results are displayed on the console or 
in the log file.

## Run Test
Although test are not sufficient (per time constraints),

```shell
mvn test
```

## To Do
Per time constraint and considering this is not a _real_ project, some aspect have
been neglected, however it's worth mention them:

* UT are not nearly enough ( coverage should be ~100%)
 * Coverage can be deceiving in this case, as many cases need to be tested
* Integration test are also necessary
* Static analysis tools should be included (ex: findbugs, checkstyle, etc)
* Mutation Coverage should be included (ex: https://pitest.org/)
* Error description should be better handled
* Validations are not in place including _cheating_ (ex: same card on same hand) 
* Mockito and PowerMock (and related dependencies) where included but not used
* Log4j was included
* Code can be improved on several places like score calculations

## How to run 
Just build the project and execute _com.bertoni.test.Play.java_ with the directory with the 
files to be process, 
test file is provided as resources on _/test/resources/_, you can also use mvn to execute the 
processor, but first adjust the parameters on the _pom.xml_ to you local env

```shell
 mvn clean compile exec:java
 
```

* This has only be tested on Mac OS X, but should be portable on other s.o.