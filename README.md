Java EE - Sample Code
==============

Java EE sample code for fast prototyping.

The code could be used as an example of JEE application for rapid and quick prototyping. Features exploited:

* EJB (transactional behavior, etc.)
* Persistence
* JMS Messaging
* Service layer (WebServices and REST)

No webviews (e.g., JSP, JSF, etc.) available.
 
### Build and Run:

You can build the code and get it up and running using Maven and JBoss:

* Apache Maven 3.0.4
* JBoss AS 7.1.1.Final
* JDK 1.6.0_35

You can run `mvn clean package`, take EAR archive and deploy it into the standalone deployment directory of JBoss7.

*__NOTE__*: Run JBoss standalone-full instance:

```
$JBOSS_BIN> ./standalone.sh  --server-config=standalone-full.xml
```

In order to give it a try, you can call a REST endpoint at:

`http://localhost:8080/articles-war/rest/articles/createCode`

POSTing this data:

```
{
   code: "12345"
}
```
