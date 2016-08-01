/*DEVELOPMENT SETUP */

//LOMBOK setup for getter and setters. lombok.jar is in lib folder so no need to copy or place in classpath
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
		Properties files: DEV-odcAccess.properties
	
	TEST for both Unit and Integration testing. 
		Properties files: TEST-odcAccess.properties
	
	PROD for production. 
		Properties files : PROD-odcAccess.properties
	
	How to set the Environment variable:
	Ex: For production configuration
	For Windows, Right click "My Computer" --> Properties --> Advance System Settings --> Environment Variables. Add the following property 
	 	variable: spring.profiles.active
	 	value: PROD
	 	
	 	For *nix, add the following property in before calling server script.
	 		export spring.profiles.active=PROD 

	  