# Users

Users is a Spring Boot application that stores user information for future retrieval in a H2 database. The application provides a RESTful API for interacting with it.

# How To Build

The Users module makes use of maven for its build lifecycle. To run tests you can run `./mvnw test`, or for a full build you can run `./mvnw install`.

A docker image can be produced by running `./mvnw spring-boot:build-image -D"spring-boot.build-image.imageName"=stctest/users`

# Running the Application

To run the application from the docker image produced in the previous section you can use `docker run -p 8080:8080 -t stctest/users`. This will start the application which will then be accessible on your localhost using port 8080.

The persistence mechanism is using a H2 database so in this example application it will be cleared on restart. If you wish to interrogate the database whilst running the application you can navigate to http://localhost:8080/h2console, select Generic H2 (Server) as the settings name and use jdbc:h2:file:./demodb as the JDBC URL value. The username and password fields should be blank. This will allow you to use the H2 console to query the underlying data in the database.

# Using the Application

There are two endpoints available to interact with the application

## User Creation Endpoint

The user creation endpoint is hosted at http://localhost:8080/user. This endpoint accepts a POST with a Content-Type header of application/json and a JSON body in the format:

```json
{
  "username": "user@name", //this string must be a valid email address
  "firstName": "test", //this can be any valid string
  "lastName": "user" //this can be any valid string
}
```

On success you will recieve a 201 status code in response and the Location header will contain the location for querying this user.

## Get User Endpoint

A GET request can be sent to http://localhost:8080/user/{id} where id must be a valid users id. JSON will be returned in the body for the response containing the user information. E.g.

```json
{
    "username": "user@name",
    "userId": 1,
    "firstName": "test",
    "lastName": "user"
}
```
