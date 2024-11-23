

# Email OTP Service in Spring Boot

This project demonstrates how to send and verify OTPs (One-Time Passwords) via email using **Spring Boot**. 
The application uses `Spring Mail` for email services and an in-memory store initially

---

## Features

- **Send OTP via email**: Users can receive an OTP on their email for verification.
- **Verify OTP**: The application validates the user-entered OTP against the stored OTP in the database.
- **Expire OTPs**: OTPs automatically expire after a configured time (e.g., 5 minutes).


---

## Prerequisites

 - **Java 11+**
 - **Maven** or **Gradle** for dependency      management.
 - **Spring Boot** framework.
 -  **SMTP Email Account** (e.g., Gmail,   MailTrap, or any other provider).

---

## Setup Instructions

### 1. Clone the Repository

```bash
git clone https://github.com/amaanalikhan3000/EmailOtp.git
cd email-otp-springboot
```

### 2. Configure the Application

Update the `application.yml` or `application.properties` file with your email service

#### Example: `application.yml`

```yaml
spring:
  mail:
    host: smtp.gmail.com
    port: 587
    username: your-email@gmail.com
    password: your-email-password
    properties:
      mail:
        smtp:
          auth: true
          starttls:
            enable: true

```

---

### 3. Build and Run the Application

Build and run the Spring Boot application.

```bash
# Using Maven
mvn clean install
mvn spring-boot:run

# Or using Gradle
./gradlew build
./gradlew bootRun
```

The application will be available at `http://localhost:8080`.

---

## Usage

### 1. Send OTP

**Endpoint**: `POST /api/otp/send`  
**Payload**:

```json
{
  "email": "user@example.com"
}
```

**Response**:

```json
{
  "message": "OTP sent successfully to user@example.com"
}
```

---

### 2. Verify OTP

**Endpoint**: `POST /api/otp/verify`  
**Payload**:

```json
{
  "email": "user@example.com",
  "otp": "123456"
}
```

**Response (Success)**:

```json
{
  "message": "OTP verification successful"
}
```

**Response (Failure)**:

```json
{
  "message": "Invalid or expired OTP"
}
```

---

## Project Structure

```
src/main/java
├── com.email.demo
│   ├── controller
│   │   ├── OtpController.java    # Handles OTP-related API endpoints
│   ├── service
│   │   ├── EmailOtpService.java  # Sends OTP and handles email functionality
│   │   ├── OtpStorageService.java # Manages OTP storage 
│   ├── Entity
│   │   ├── OtpDetails.java       # OTP Details 
│   
```

---

## Dependencies

Add the following dependencies to your `pom.xml`:

```xml
<dependencies>
    <!-- Spring Web -->
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-web</artifactId>
    </dependency>

    <!-- Spring Mail -->
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-mail</artifactId>
    </dependency>

    <!-- Lombok -->
    <dependency>
        <groupId>org.projectlombok</groupId>
        <artifactId>lombok</artifactId>
        <optional>true</optional>
    </dependency>
</dependencies>
```

---

## Security

- OTPs are short-lived and stored in internally with an expiration time.
- Use an **App Password** for Gmail or secure credentials for your SMTP server.





## Troubleshooting

- **Email Not Sending**: Ensure SMTP settings are correct and your email provider allows less secure app access or uses an App Password.

---

## License

This project is licensed under the MIT License.


