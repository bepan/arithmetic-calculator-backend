# Arithmetic Calculator API
This Spring boot project defines rest endpoints for authentication, executing operations and user records.

## Running the project locally

1. Install Docker and jdk17 or openJdk17 and set your `JAVA_HOME` env variable in your machine.

2. Start mysql by running `docker-compose up` at the project root folder. Alterantively you could download your own mysql server in your computer, but make sure it is running on port `3306` and the database name is `pitsdb`.

3. Start spring project by executing `./mvnw spring-boot:run` command at the root folder. Remove `./` at the beginning of the command if you are on windows cmd. 

**The application will seed the database with a default root user and the basic operations a user can execute.**

## API Documentation

### Login endpoint
To test if the api is working we can verify by login in with the default user and get our session token by doing this http request:
```
POST http://localhost:8080/login
Content-Type: application/json

{
  "username": "root@gmail.com",
  "password": "your-secret-password-defined-in-dotenv-file"
}
```

### Operation Execution endpoint
Then, we need to use that resulting token and add it to every request as an Authorization header like this:
```
POST http://localhost:8080/operations/execute
Content-Type: application/json
Authorization: Bearer eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJhbGJlcnRvLmNyY3UxNUBnbWFpbC5jb20iLCJleHAiOjE3MTY0MjE2NTN9.yx6HCflHkQpiAbNkQOXdtavKT_RGhEUcKffnSRrk90c

{
  "operationType": "subtraction" | "addition" | "multiplication" | "random_string" | "division" | "square_root",
  "number1": 10,
  "number2": 5
}
```

### Getting Authenticated user records
The endpoint accepts some query string options like pageNumber, pageSize, sortBy and searchInputQuery

```
GET http://localhost:8080/records/mine?pageNumber={number}&pageSize{number}=&sortBy={date:asc}&searchInputQuery={string}
Authorization: Bearer eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJhbGJlcnRvLmNyY3UxNUBnbWFpbC5jb20iLCJleHAiOjE3MTY0MjE2NTN9.yx6HCflHkQpiAbNkQOXdtavKT_RGhEUcKffnSRrk90c
```

### Soft Delete a Record
```
DELETE http://localhost:8080/records/{recordId}
Authorization: Bearer eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJhbGJlcnRvLmNyY3UxNUBnbWFpbC5jb20iLCJleHAiOjE3MTY0MjE2NTN9.yx6HCflHkQpiAbNkQOXdtavKT_RGhEUcKffnSRrk90c
```

### Logout Endpoint
```
POST http://localhost:8080/logout
Authorization: Bearer eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJhbGJlcnRvLmNyY3UxNUBnbWFpbC5jb20ifQ.etHe0_4Y1lTLreqjmWJtua9NagdPaTBwBcrYb-U0bLs
```


