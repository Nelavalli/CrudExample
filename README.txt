/*DEVELOPMENT SETUP */

//LOMBOK setup for getter and setters. lombok.jar is in lib folder so no need to copy or place in classpath
	Close your Eclipse IDE 
	Double click on the lombok.jar
	Specify the location of Eclipse
	click Install/Update button
	Use your Eclipse IDE as usual 

GET request to /api/user/ returns a list of users
GET request to /api/user/1 returns the user with ID 1
POST request to /api/user/ with a user object as JSON creates a new user
PUT request to /api/user/3 with a user object as JSON updates the user with ID 3
DELETE request to /api/user/4 deletes the user with ID 4
DELETE request to /api/user/ deletes all the users