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

## LICENSE - "MIT License"

Copyright (c) 2013 Alessio Della Motta, http://alessiodellamotta.com

Permission is hereby granted, free of charge, to any person
obtaining a copy of this software and associated documentation
files (the "Software"), to deal in the Software without
restriction, including without limitation the rights to use,
copy, modify, merge, publish, distribute, sublicense, and/or sell
copies of the Software, and to permit persons to whom the
Software is furnished to do so, subject to the following
conditions:

The above copyright notice and this permission notice shall be
included in all copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND,
EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES
OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND
NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT
HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY,
WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING
FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR
OTHER DEALINGS IN THE SOFTWARE.
