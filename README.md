# TDD Mini Project
This is a Spring Boot project that utilizes Test-Driven Development by implementing CRUD (Create, Read, Update, Delete) operations for the Order entity.

To set up the project:
-
- clone the repisitory: git clone https://github.com/vuelee123/TDDMiniProject.git
- import the project into Intellij as a Maven project.
- run the application from Intellij or use the command 'mvn spring-boot:run' in the terminal.

H2 Database Console
-
The project is configure with an H2 in-memory database where you can access the console to view and interact with the database.
After starting the application:
- open your web browser and navigate to localhost:8080/h2-console
- the only thing you will enter is the username and password.
  - username: lee
  - password: vue
- click the 'connect' button to access the H2 Console

#### The project includes REST APIs for CRUD operations on the Order entity. You can use Postman to interact with the APIs.

- POST /api/orders - Create a new order.
- GET /api/orders - Retrieve all orders.
- GET /api/orders/{id} - Retrieve an order by ID.
- PUT /api/orders/{id} - Update an existing order.
- DELETE /api/orders/{id} - Delete an order by ID.

  
