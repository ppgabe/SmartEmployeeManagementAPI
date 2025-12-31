# Smart Employee Management API

This project is a simple Employee Management API built with Spring Boot, Spring Data JPA, and MySQL. It allows basic operations for managing employee records.

## Features

* Create, read, update, and delete employee records
* Uses MySQL as the database
* Configurable via environment variables
* Spring Boot DevTools enabled for hot reload

## Setup

1. Clone the repository:
   git clone <your-repo-url>

2. Create a `.env` file in the project root and set the following variables:

   
   DB_URL=jdbc:mysql://localhost:3306/your_database
   DB_USERNAME=your_db_username
   DB_PASSWORD=your_db_password
   PORT=8080


3. Make sure `.env` is added to `.gitignore` to keep your credentials safe.

4. Run the application:


   ./mvnw spring-boot:run


   Or run the main class `SmartEmployeeManagementApiApplication` from your IDE.

## Notes

-The API runs on the port specified in `.env` (default is 8080)
- Make sure your MySQL server is running and the database exists
- System properties are set in the main class using `Dotenv` library

## License

This project is licensed under the MIT License. See the [LICENSE](LICENSE) file for details.
