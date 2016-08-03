/*DEVELOPMENT SETUP */

//LOMBOK setup for getter and setters. lombok.jar is in WEB-INF/lib folder so no need to copy or place in classpath
	Close your Eclipse IDE 
	Double click on the lombok.jar
	Specify the location of Eclipse
	click Install/Update button
	Use your Eclipse IDE as usual 

/*JSON 	URI request mappings*/
GET request to /api/user/ returns a list of users
GET request to /api/user/1 returns the user with ID 1
POST request to /api/user/ with a user object as JSON creates a new user
PUT request to /api/user/3 with a user object as JSON updates the user with ID 3
DELETE request to /api/user/4 deletes the user with ID 4
DELETE request to /api/user/ deletes all the users

/*Environment specific information*/
Set spring.profiles.active environment variable according to environment to load environment specific properties files. 
 
	DEV for development. 
		Properties files: DEV-crudExample.properties
	
	TEST for both Unit and Integration testing. 
		Properties files: TEST-crudExample.properties
	
	PROD for production.
		Properties files : PROD-crudExample.properties
	
	How to set the Environment variable:
	Ex: For production configuration
	For Windows, Right click "My Computer" --> Properties --> Advance System Settings --> Environment Variables. Add the following property 
	 	variable: spring.profiles.active
	 	value: PROD
	 	
	 	For *nix, add the following property in before calling server script.
	 		export spring.profiles.active=PROD 


/*HSQLDB configuration. 

HSQLDB is both in-memory and file DB. We are using file DB. 
Since it is file DB,Either application or standalone connectivity accepted, not both*/

/*HSQL Standalone readonly connectivity (while application running)*/
java -cp C:/Users/rnel1941/workspace/CrudExample/test/lib/hsqldb.jar org.hsqldb.util.DatabaseManagerSwing -driver org.hsqldb.jdbcDriver -url jdbc:hsqldb:file:C:/Users/rnel1941/odcAccess_db;shutdown=true;hsqldb.write_delay=false;readonly=true -user sa


/* SPRING Security DB remember-me functionality. It needs database table to get and stored*/
create table persistent_logins (username varchar(64) not null, series varchar(64) primary key, token varchar(64) not null, last_used timestamp not null)

