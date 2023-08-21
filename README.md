# Bistro-Application
Application for Bistro management using Spring Boot and MySQL for storing the data.

# Task 1 Description
Create a new Spring Boot project using Spring Initializr with spring-boot-starter-web.

Define a database model that could be used to manage a bistro. Information about available products, customers, and orders should be stored (use MySQL or PostgreSQL).

The following type of products can be ordered by a customer from the bistro: cake, waffles, croissant, donut, pasta, pizza, risotto. Each product has its price and a recipe with different ingredients. The ingredients have their own cost. For example, if a customer orders a waffle with extra topping, the topping ingredient will be added to the price of the product.

Use the following technologies: java 8, maven, spring boot, spring data, rest and a MVC architecture (you should also have a repository layer). Apply SOLID principles. Application logic will be implemented at the service layer.

Using spring-boot-starter-data-jpa, implement a solution to perform the following actions:
- add a product to the bistro database
- list all products stored in the bistro database 
- list a product with a given ID 
- update the price of a product
- remove a product with a given ID
- add a customer to the bistro
- list all customers in the bistro database
- remove a customer with a given ID
- list top 3 products that are the most wanted

Use feature branches for each task (define a feature branch using the convention feature/firstname-lastname-TASK-{Task_Number}). Create a pull request from your feature branch to the branch with your name when the implementation of a task is finished and ready for review.

Implement a Global Exception Handling mechanism using your custom exception.

Use unit and integration testing for the application.
