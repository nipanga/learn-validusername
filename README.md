# Validusername App

This project was made using [Spring Boot](https://projects.spring.io/spring-boot/), [Maven](https://maven.apache.org/) and [MySQL version 5.7](https://www.mysql.com/)
  
Before building the app, create two databases in your MySQL instance, called `validusername` and `validusernametest`.
Then check the application settings under `${project-home}/validusername/src/main/resources/application.properties` and  `${project-home}/validusername/src/main/resources/application-test.properties`, and make sure that the database user and password are correct.
  
To build the app, run `mvn package` from the project's home folder, and it should run the tests and build the app jar.
  
To run the program, run `mvn spring-boot:run` from the project's home folder, and it should be available under the `http://localhost:808` URL. Note that you should see a error in the screen, but that's ok, since the app does not contain a Controller/Request Mapping for the `/` URL.
  
To check a username, make a `POST` request to the `http://localhost:8080/users/check-username`, with the HTTP Header `Content-Type:application/json`, and the following JSON Body:
``` json
{
    "username" : "johnny"
}
```
  
And you should receive a JSON response, like this:
``` json
{
    "userResponseDTOs": [
        {
            "successful": true,
            "suggestedUsernames": []
        }
    ]
}
```
  
 Or, in case you pass a invalid username, the app should response a little bit differently:
  ```
 {
    "userResponseDTOs": [
        {
            "successful": false,
            "suggestedUsernames": [
                "johnjo",
                "johnjoh",
                "john1j",
                "john2944",
                .
                .
                .
            ]
        }
    ]
}
 ```
   
   
 If you don't want to download the source code, and just run it, download the [Runnable Jar File](https://github.com/nipanga/learn-validusername/blob/master/validusername/target/validusername-0.0.1.jar) under the `target` folder, and run it using `java -jar validusername-0.0.1.jar`. This should produce the same results as the `mvn spring-boot:run` command.
   
 That's it. Any questions please contact [Me](https://github.com/nipanga).
