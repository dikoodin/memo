MemoSoft Demo
=============

Application is to demonstrate integration of Java 6, JSF 2.0, Spring 3, JPA, PostgreSQL, Tomcat 7.
Please check the versions in pom.xml file.

Running the Demo
----------------

- Get a checkout of demo::

    $ git clone https://github.com/dikoodin/memo ~/memodemo

In newly checked out demo package navigate to *src/main/resources/config/database* and there you can find two files **db_setup.sql** and **db.properties**. 
File *db_setup.sql* containes tables needed to run the application. 
Create the tables in database. 
After that edit *db.properties* and replace *{host}*, *{port}*, *{database}*, *{username}*, *{password}* with corresponding values.
After finished with db setup we can build and deploy application.

- ``cd`` to the newly checked out demo package::

    $ cd ~/memodemo

- Run ``mvn clean install`` command to build application::

    $ mvn clean install

- In memodemo/target folder you can find memosoft.war and copy it to tomcat deployment directory.
- Run the tomcat.
- Visit [http://localhost:8080/memosoft](http://localhost:8080/memosoft) in a browser to see the demo.
- With sign up you can register new user. If you want to be admin then update admin column in users table to be true.

Running the Demo's Unit Tests
---------------------------------

- Run unit tests with command::

	$ mvn test
