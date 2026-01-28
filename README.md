# Smart Employee Management API
_Forked by Gabriel Virrey (GitHub/ppgabe) of AilurusLabs_

This project is a simple Employee Management API built with Spring Boot and Spring Data JPA. It allows basic operations for managing employee records.

## About This Fork
This is a fork of @clementejb-jibe's repository at [SmartEmployeeManagementAPI](https://github.com/clementejb-jibe/SmartEmployeeManagementAPI)

This version includes a redesign of the entire data model, leveraging records and sealed interfaces to
align with modern idiomatic Java. It also refactors the core mechanisms of the app, from the controller and service,
as well as exception handling and response status codes. Lastly, it adds a basic integration test suite using
TestContainers, which made this refactoring effort possible in the first place by providing a basis for all changes.

All changes in this fork were made by Gabriel Virrey.

## Features

* Create, read, update, and delete employee records
* Uses MySQL as the database for production, and H2 in-memory for local development
* Spring Boot DevTools enabled for hot reload

## Running the application locally

1. Clone the repository:
```shell
   git clone https://github.com/ppgabe/SmartEmployeeManagementAPI.git
```

2. Create `application-local.yml` based on `application.yml` in `src/main/resources` and set 
the following variables:

```yaml
datasource:
   url: "jdbc:h2:mem:testdb;DB_CLOSE_DELAY=-1"
   username: "sa"
   password: "password"
   driver-class-name: org.h2.Driver
```

> **Note:** It comes without saying that you should never share credentials like this in an actual, production app.
> Here, it's alright, since this is a local in-memory database that's only meant for local development.

3. Run the application:

```shell
./mvnw spring-boot:run -Dspring-profiles.active=local
```

Alternatively, configure the run configuration of your IDE to set active profiles to `local`, and then run the
main class (`SmartEmployeeManagementApiApplication`).

## License

This project is based on [SmartEmployeeManagementAPI](https://github.com/clementejb-jibe/SmartEmployeeManagementAPI),
licensed by the original author under MIT. Modifications are licensed under Apache 2.0.

