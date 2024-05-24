# Arithmetic Calculator API
This Spring boot project defines rest endpoints for authentication, executing operations and user records.

## Running the project locally

1. Install Docker and jdk17 or openJdk17 and set your `JAVA_HOME` env variable in your machine.

2. Start mysql by running `docker-compose up` at the project root folder. Alterantively you could download your own mysql server in your computer, but make sure it is running on port `3306` and the database name is `pitsdb`.

3. Start spring project by executing `./mvnw spring-boot:run` command at the root folder. Remove `./` at the beginning of the command if you are on windows cmd. 

**The application will seed the database with a default root user and the basic operations a user can execute.**

To test if the api is working we can verify by login in with the default user and get our session token by doing this http request:
```
POST http://localhost:8080/login
Content-Type: application/json

{
  "username": "root@gmail.com",
  "password": "your-secret-key-defined-in-.ENV"
}
```

Then, we need to use that resulting token and add it to every request as an Authorization header like this:
```
POST http://localhost:8080/operations/execute
Content-Type: application/json
Authorization: Bearer eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJhbGJlcnRvLmNyY3UxNUBnbWFpbC5jb20iLCJleHAiOjE3MTY0MjE2NTN9.yx6HCflHkQpiAbNkQOXdtavKT_RGhEUcKffnSRrk90c

{
  "operationType": "subtraction",
  "number1": 10,
  "number2": 5
}
```


## Missing functionality

* More Unit tests
