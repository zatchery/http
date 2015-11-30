Zatch's Http Service
===================

----------
A simple foray into implementing a RFC 2616 compliant webserver.

Building and Running
-------------

This project is built using the  [Maven Shade Plugin][1].  To build download the source, navigate to the root directory of the project and run:
```
mvn clean install
java -jar target/parent-0.0.1-SNAPSHOT-standalone.jar
```

This will package the jar into a runnable and then start it. It runs on port 8080 and logs to stout. The level and output location can be configured in logback.xml found in src/main/resources.

  [1]: https://maven.apache.org/plugins/maven-shade-plugin/

