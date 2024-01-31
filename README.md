# VodafoneZiggo - OrderAPI
OrderAPI is a RESTful API that's built with Java / Maven / Spring Boot (version 2.7.4) application that is built for managing orders for customers. 
It provides two functionalities via HTTP GET/POST endpoints: 
  - Getting list of all orders (Content-Type: application/json): HTTP GET - http://localhost:8080/order
  - Creating an order (Content-Type: application/json): HTTP POST - http://localhost:8080/order

OrderAPI uses an in-memory database (H2) to store the data. It can be accessed via its own console: http://localhost:8080/h2-console (username: sa, password: password)

There's an openapi specificiation included in JSON format, for more information on endpoints, please refer to <b>src/main/resources/orderapi.json</b>.
## Requirements
To build and run this API, Java 17 and Maven version 3.x is required.
## Build
To build the application, you can use following command on project folder:
```
mvnw clean package
```
To build a docker image (you need Docker installed and Docker daemon running), you can use following command:
```
docker build . -t orderapi
```
## Run
Type the following command from project folder:
```
java -jar target/orderapi-<version>.jar
```
To run the Docker image, type following command
```
docker run -d -p 8080:8080 orderapi
```
OrderAPI will run on an embedded Tomcat server on port 8080.
## Test
Please import the included postman collection <b>src/test/resources/postman/OrderAPI_postman_test_collection.json</b> to Postman to test the API. 



