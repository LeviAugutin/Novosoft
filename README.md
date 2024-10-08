# Assessment - Spring Boot Application

To run this project, ensure you have the following installed:

- Java 17
- Maven 3.6+
- MySQL database

Technologies Used

Spring Boot: 3.3.4
Spring Data JPA
Spring Security: For securing the API
Lombok: For reducing boilerplate code
Springdoc OpenAPI: For generating API documentation

API Endpoints
Base URL: http://localhost:8080/

Swagger UI (API Documentation): http://localhost:8080/swagger-ui/index.html for API documentation and testing.
API Endpoints

User API
Register a New User
URL: POST /novo/users/register
(before accessing the swagger need to register users and here we use spring default login for our login system
once we hit with register url like in postman 
ex:
{
"username": "levi",
"password": "123",
"role": "ROLE_USER"
}
)
for logout clear the catch file from browser (swagger) 

Get All Products
URL: GET /novo/products/products

Get Product by ID
* URL: GET /novo/products/products/{id}

Create a New Product
* URL: POST /novo/products

Update an Existing Product
URL: PUT /novo/products/{id}

Delete a Product
URL: DELETE /novo/products/{id}