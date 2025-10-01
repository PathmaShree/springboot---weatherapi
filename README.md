# springboot---weatherapi
A Spring Boot RESTful API that fetches real-time weather information from the OpenWeatherMap API, stores search history in a database, and provides CRUD operations for managing weather records.

# FEATURES
- Fetch current weather for any city using OpenWeatherMap API.
- Save search history in the database.
- Retrieve all weather search records or a specific record by ID.
- Update records fully or partially.
- Delete weather records by ID.
- Proper exception handling and validation.

# TECHNOLOGIES
- Backend: Spring Boot, Spring Data JPA, RestTemplate
- Database: MySQL / Oracle (configurable)
- API Integration: OpenWeatherMap API
- Tools: Postman for API testing, Maven for build management

# PREREQUISITES
- Java 17 or higher
- Maven
- OpenWeatherMap API key
- IDE (Eclipse, IntelliJ IDEA, VS Code)

# SET UP
# Clone the repository

git clone https://github.com/PathmaShree/SpringBoot-WeatherAPI.git  
cd SpringBoot-WeatherAPI

# Configure application.properties

# H2 Database
spring.datasource.url=jdbc:h2:mem:testdb
spring.datasource.driverClassName=org.h2.Driver
spring.datasource.username= sa
spring.datasource.password=
spring.jpa.database-platform=org.hibernate.dialect.H2Dialect
spring.jpa.hibernate.ddl-auto=update

### Weather API Key    

weather.api.key=YOUR_OPENWEATHERMAP_API_KEY

### H2 Console (Optional)  

spring.h2.console.enabled=true
spring.h2.console.path=/h2-console


# Oracle Database
spring.datasource.url=jdbc:oracle:thin:@localhost:1521:XE
spring.datasource.driverClassName=oracle.jdbc.OracleDriver
spring.datasource.username=YOUR_ORACLE_USERNAME
spring.datasource.password=YOUR_ORACLE_PASSWORD
spring.jpa.database-platform=org.hibernate.dialect.Oracle10gDialect
spring.jpa.hibernate.ddl-auto=update

### Weather API Key  

weather.api.key=YOUR_OPENWEATHERMAP_API_KEY


# MySQL Database
spring.datasource.url=jdbc:mysql://localhost:3306/weatherdb?useSSL=false&serverTimezone=UTC
spring.datasource.driverClassName=com.mysql.cj.jdbc.Driver
spring.datasource.username=YOUR_MYSQL_USERNAME
spring.datasource.password=YOUR_MYSQL_PASSWORD
spring.jpa.database-platform=org.hibernate.dialect.MySQL8Dialect
spring.jpa.hibernate.ddl-auto=update

### Weather API Key  

weather.api.key=YOUR_OPENWEATHERMAP_API_KEY


# Run the application
mvn spring-boot:run

# API Endpoints

| Method | Endpoint             | Description                                | Request Body                  | Response                        |
|--------|--------------------|--------------------------------------------|-------------------------------|---------------------------------|
| GET    | `/fetch?city={city}` | Fetch current weather for a city and save history | N/A                           | WeatherSearchHistory JSON       |
| GET    | `/id/{id}`          | Get weather history by ID                  | N/A                           | WeatherSearchHistory JSON       |
| GET    | `/all`              | Get all weather history                     | N/A                           | List of WeatherSearchHistory    |
| PUT    | `/{id}`             | Update entire weather record by ID         | WeatherSearchHistory JSON     | Updated record JSON             |
| PATCH  | `/{id}`             | Partial update of weather record           | JSON with fields to update    | Updated record JSON             |
| DELETE | `/{id}`             | Delete weather record by ID                 | N/A                           | Success message                 |



# SAMPLE REQUEST

- Fetch Weather for London  
GET http://localhost:8080/weatherapi/fetch?city=London

- Response  
{  
  "id": 2,  
  "cityName": "London",  
  "temperature": "16.34°C",  
  "weatherDesc": "light rain",  
  "searchedAt": "2025-10-01T17:14:39.812"  
}

# WEATHER API  

https://openweathermap.org/api
