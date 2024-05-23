# Arithmetic Calculator API
This Spring boot project defines rest endpoints for authentication, executing operations and user records.

## Running the project locally
There are 2 options, using **docker** or manual installation of project deps.

* For docker, install docker in your computer and at the project root folder run `docker-compose up` command and thats it.

* For manual running, please install jdk17 or openjdk17 and mysql on your computer, then start mysql server. At the project root folder execute `./mvnw spring-boot:run`. Remove `./` at the beginning of the command if you are on windows cmd. Make sure to update `spring.datasource.url` prop in `application.properties` file accordingly with your mysql host, port and database name, right now it is configured to work for docker primarly.

**The application will seed the database with a default root user and the basic operations a user can execute.**

To test if the api is working we can verify by login in with the default user and get our session token by doing this http request:
```
POST http://localhost:8080/login
Content-Type: application/json

{
  "username": "root@gmail.com",
  "password": "secret123"
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

The file `./routes.rest` has more examples of rest endpoints we can execute. This project is still in progress.

## Missing functionality

* More Unit tests
* Pagination, Filtering and Sorting for records.
