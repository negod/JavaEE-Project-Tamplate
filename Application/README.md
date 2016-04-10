# JavaEE7-Archetype
Archetype for JavaEE7 projects

This project is a good start for a JavaEE7 project it includes the following..

+ [SLF4J](http://www.slf4j.org/) with [Logback](http://logback.qos.ch/) - logging is implemented and should place itself under C:\apps\YourApplicationName
+ [SWAGGER](http://swagger.io/) with [swagger-jaxrs-doclet](https://github.com/ryankennedy/swagger-jaxrs-doclet) Implemented in the REST Service - When deployed you should get the UI from http://localhost:8080/YourApplicationName/apidocs
+ [Hibernate 4.3.1](http://hibernate.org/) - In the archetype a Javax metamodel of the entities is generated for use with CriteriaBuilder
+ [Lombok 1.16.8](https://projectlombok.org/) - For automatic generation of Getters, Setters, ToString etc with annotations

##### Easiest environment to get up and running fast

+ [Netbeans 8.1] (https://netbeans.org/downloads/) 
+ [JDK8] (http://www.oracle.com/technetwork/java/javase/downloads/jdk8-downloads-2133151.html)
+ [Glassfish 4.1 ( NOT 4.1.1 )] (https://glassfish.java.net/download-archive.html)
+ [Maven 3 > ] (https://maven.apache.org/download.cgi)

This project uses the built in Derby in memory database that is preconfigured in Glassfish 4.1.
You should be able to compile the project and deploy it directly.

When doing the first "mvn clean install" the following happens.

1. JPAModelGen generates the metamodel 
2. A logging.properties file is generated and that is later used by logback.xml for its properties
3. Swagger documentation is generated from Javadoc 

#### Configure to fit your project
+ Update persistance.xml to enable it for your datasource
+ Generate och create entities
+ Create new services


