This is a simple RESTful API for a BookStore implemented using Spring Boot.

## Table of Contents

- [Features](#features)
- [Technologies Used](#technologies-used)
- [Getting Started](#getting-started)
  - [Prerequisites](#prerequisites)
  - [Installation](#installation)
- [Usage](#usage)
  - [Registering a User](#registering-a-user)
  - [Authentication](#authentication)
  - [Accessing Endpoints](#accessing-endpoints)
- [Running Tests](#running-tests)
- [Contributing](#contributing)
- [License](#license)

## Features

- JWT-based authorization for secure endpoints
- Mapping between DTOs and entities using ModelMapper
- Validation of DTOs using custom validators
- Unit and integration tests

## Technologies Used

- Java 19
- Spring Boot
- Maven
- Spring Security
- JSON Web Tokens (JWT)
- ModelMapper
- Hibernate Validator
- JUnit
- Mockito
- Postgres
- Docker
- Maven Spotless


## Getting Started

### Prerequisites

- Java 17
- Maven

### Installation

Clone the repository:

   ```bash
   git clone https://github.com/admirn/spring.git
```
## Usage

### Registering-a-user
To use the API, users must first register. Send a POST request to /auth/register with the following JSON payload:

```bash
{
   "fistName"": "yourfirstname,
   "lastname": "yourlastname"
   "email": "youreamil",
   "password": "yourpassword"
}
```

### authentication
You can get a token by sending POST request to /auth/login with the following JSON payload: 
```bash
{
   "email": "youreamil",
   "password": "yourpassword"
}
```

### Accessing-endpoints
Once authenticated, you can access the API endpoints by including the access token in the Authorization header of your requests:
```bash
GET /books
Authorization: Bearer your_access_token
```

Here is the list of all endpoints implemented:
```bash
PUT /books/{isbn} #book creation or updeting if already exists
GET /books #get all the books implemented with pagonation
GET /books/{isbn} #get a specific book
PATCH /books/{isbn} #partial update of a book
DELETE /books/{isbn} #delete a book

POST /authors #create an author
GET /authors #get all the authors
GET /authors/{id} #get the specific author
PUT /authors/{id} #complete update of the author
PATCH /authors/{id} #partial update of the author
DELET /authors/{id} #delete an author
```

## Running-tests
To ensure code quality and functionality, run the unit and integration tests:
```bash
mvn test
```

## Contributing
Contributions are welcome! If you find any issues or have suggestions for improvements, please open an issue or create a pull request.

## License
This project is licensed under the MIT License.

