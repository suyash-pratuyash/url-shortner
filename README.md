# URL Shortener

A simple and efficient URL shortener application built with Spring Boot and MySQL. This application allows users to convert long URLs into short, manageable links with random alphanumeric codes.

## 🚀 Features

- **URL Shortening**: Convert long URLs into compact short codes (7 characters)
- **URL Retrieval**: Access original URLs using their short codes
- **Automatic Redirects**: Seamless HTTP 302 redirects to original URLs
- **Unique Code Generation**: Ensures all short codes are unique using SecureRandom
- **Data Persistence**: Stores URL mappings in MySQL database
- **Timestamps**: Tracks creation time for each shortened URL
- **Input Validation**: Validates incoming requests using Jakarta Validation
- **Exception Handling**: Comprehensive error handling with custom exceptions

## 📋 Prerequisites

Before running this project, ensure you have the following installed:

- **Java**: JDK 21 or higher
- **Maven**: 3.6.0 or higher
- **MySQL**: 5.7 or higher
- **Git**: For version control

## 🛠️ Project Setup

### 1. Clone or Extract the Project

```bash
cd "D:\Creation\Url shortner project\url-shortner"
```

### 2. Create Database

Create a MySQL database and table:

```sql
-- Create database
CREATE DATABASE url_shortener;

-- Use the database
USE url_shortener;

-- Create table (auto-created by Hibernate, but you can create it manually)
CREATE TABLE url_mapping (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    short_code VARCHAR(255) UNIQUE NOT NULL,
    original_url VARCHAR(2048) NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);
```

### 3. Configure Application

Update the database connection settings in `src/main/resources/application.properties`:

```properties
spring.datasource.url=jdbc:mysql://localhost:3306/url_shortener
spring.datasource.username=root
spring.datasource.password=YOUR_PASSWORD
```

Replace `YOUR_PASSWORD` with your MySQL root password.

### 4. Build the Project

```bash
# Using Maven wrapper
./mvnw.cmd clean install

# Or using your system Maven
mvn clean install
```

## 🚀 Running the Application

### Using Maven

```bash
./mvnw.cmd spring-boot:run
```

### Using Java

```bash
java -jar target/url-shortner-0.0.1-SNAPSHOT.jar
```

The application will start on `http://localhost:8080`

## 📡 API Endpoints

### 1. Shorten URL

**Endpoint:** `POST /api/shorten`

**Description:** Creates a short URL for a given long URL

**Request Body:**
```json
{
  "originalUrl": "https://www.example.com/very/long/url/path/that/is/too/long"
}
```

**Response (201 Created):**
```json
{
  "shortCode": "aBc1234",
  "shortUrl": "http://localhost:8080/aBc1234"
}
```

**cURL Example:**
```bash
curl -X POST http://localhost:8080/api/shorten \
  -H "Content-Type: application/json" \
  -d '{"originalUrl":"https://www.example.com/very/long/url"}'
```

### 2. Redirect to Original URL

**Endpoint:** `GET /{shortCode}`

**Description:** Redirects to the original URL associated with the short code

**Response (302 Found):**
- Redirects to the original URL stored in the database

**Example:**
```
GET http://localhost:8080/aBc1234
Response: HTTP 302 Found
Location: https://www.example.com/very/long/url
```

**cURL Example:**
```bash
curl -L http://localhost:8080/aBc1234
```

## 📁 Project Structure

```
url-shortner/
├── src/
│   ├── main/
│   │   ├── java/com/suyash/url_shortner/
│   │   │   ├── UrlShortnerApplication.java      # Main Spring Boot application
│   │   │   ├── controller/
│   │   │   │   ├── UrlController.java           # Handles URL shortening requests
│   │   │   │   └── RedirectController.java      # Handles redirects
│   │   │   ├── dto/
│   │   │   │   ├── ShortenRequest.java          # Request DTO
│   │   │   │   └── ShortenResponse.java         # Response DTO
│   │   │   ├── entity/
│   │   │   │   └── Url.java                     # JPA entity for URL mapping
│   │   │   ├── exception/
│   │   │   │   ├── GlobalExceptionHandler.java  # Global exception handler
│   │   │   │   └── UrlNotFoundException.java    # Custom exception
│   │   │   ├── repository/
│   │   │   │   └── UrlRepository.java           # Spring Data JPA repository
│   │   │   └── service/
│   │   │       └── UrlService.java              # Business logic
│   │   └── resources/
│   │       └── application.properties           # Application configuration
│   └── test/
│       └── java/com/suyash/url_shortner/
│           └── UrlShortnerApplicationTests.java # Test cases
├── pom.xml                                       # Maven configuration
└── README.md                                     # This file
```

## 🔧 Configuration

### Application Properties (`application.properties`)

| Property | Description | Default |
|----------|-------------|---------|
| `spring.application.name` | Application name | url-shortner |
| `spring.datasource.url` | MySQL database URL | jdbc:mysql://localhost:3306/url_shortener |
| `spring.datasource.username` | Database username | root |
| `spring.datasource.password` | Database password | - |
| `spring.jpa.hibernate.ddl-auto` | Hibernate DDL strategy | update |
| `spring.jpa.show-sql` | Show SQL queries | true |
| `server.port` | Server port | 8080 |

### Short Code Generation

- **Algorithm**: Secure random alphanumeric generation
- **Characters**: A-Z, a-z, 0-9
- **Length**: 7 characters
- **Uniqueness**: Verified before storage

## 📦 Technologies Used

| Technology | Version | Purpose |
|------------|---------|---------|
| Spring Boot | 4.1.0 | Web framework |
| Spring Data JPA | Latest | ORM and database access |
| MySQL | 5.7+ | Database |
| Jakarta Persistence | Latest | JPA annotations |
| Jakarta Validation | Latest | Input validation |
| Lombok | Latest | Reduce boilerplate code |
| Maven | 3.6.0+ | Build tool |
| Java | 21 | Programming language |

## 🧪 Testing

Run the test suite:

```bash
./mvnw.cmd test
```

## ⚠️ Important Notes

1. **Database Connection**: Ensure MySQL is running and the connection details in `application.properties` are correct
2. **Port Availability**: Port 8080 should be available
3. **Short Code Format**: Short codes are case-sensitive
4. **URL Validation**: Original URLs must be valid
5. **Database Initialization**: Hibernate will automatically create tables if they don't exist (based on `ddl-auto=update`)

## 🔐 Security Considerations

- **Input Validation**: All inputs are validated using Jakarta Validation
- **SQL Injection Prevention**: Uses parameterized queries via JPA
- **Unique Constraints**: Database enforces unique short codes
- **SecureRandom**: Uses cryptographically secure random number generation

## 🤝 Contributing

Feel free to fork this project and submit pull requests for any improvements.

## 📝 License

This project is open source and available under the MIT License.

## 👤 Author

**Suyash**

- GitHub: [@suyash](https://github.com)
- Email: Contact for collaboration

## 📞 Support

For issues, questions, or suggestions, please create an issue on the project repository.

---

**Last Updated**: July 2026  
**Version**: 0.0.2-SNAPSHOT

