# Stock Management and Demand Forecasting Project for Micro Businesses with Spring Boot

## Project Goal
The primary goal of this project is to enable micro businesses to easily manage their inventory digitally and provide decision support for purchasing by forecasting demand based on sales data from the last 3 months. The system includes a purchase screen for bulk buying and a statistics screen that visualizes inventory data with charts. Additionally, a financial report screen is designed to give business owners quick access to income, expenses, and profit information with a single click.

## Project Purpose
This project aims to deliver a user-friendly inventory tracking and demand forecasting system developed with Spring Boot and backed by a MySQL database. The system offers an integrated solution for micro businesses to digitalize their operations by supporting product management (add, update, delete), product search by name, purchase and sales operations, statistical charts, demand analysis, and financial reporting modules.

## Technologies Used
1. **Java 17**: The project uses Java 17 to leverage modern language features such as records and switch expressions.  
2. **Spring Boot**: Provides the core framework for the application, enabling fast and efficient development with modular annotations like `@SpringBootApplication`, `@Controller`, `@Service`, and `@Repository`.  
3. **Spring MVC**: Separates the application into presentation (HTML), business logic (Service), and data layers (Entity & Repository), improving code readability and maintainability.  
4. **Spring Data JPA (Hibernate)**: Facilitates database operations without writing SQL by using method names for CRUD. Hibernate acts as the underlying ORM engine.  
5. **Thymeleaf**: Used as the template engine to create dynamic HTML pages with Java variables and structures such as `th:each` and `th:text`.  
6. **Lombok**: Simplifies code by auto-generating getters, setters, constructors, and other boilerplate using annotations like `@Getter`, `@Setter`, `@Data`, `@AllArgsConstructor`, and `@NoArgsConstructor`.  
7. **Bootstrap 5**: Enhances the user interface with responsive and modern design elements, improving visual appeal of reports, tables, and buttons.  
8. **Postman**: Used during development to test backend APIs.  
9. **Spring Security**: Implements login and user authorization, restricting access to inventory and sales screens only to authorized users.  
10. **Spring Initializr**: Used for rapid project setup and dependency management with Maven.  
11. **Maven**: Manages project dependencies like Spring Boot, JPA, and Lombok.  
12. **Chart.js**: Visualizes product stock data with charts.  
13. **MySQL & MySQL Workbench**: MySQL is chosen as the database. Tables such as products, purchases, and sales are managed manually or automatically via JPA. MySQL Workbench facilitates easy database management.  
14. **Spring Tool Suite 4 (STS)**: The integrated development environment (IDE) used for building and debugging the Spring Boot project.  

## Achievements
- Successfully launched the Spring Boot project.  
- Implemented login screen and authorization system.  
- Completed CRUD operations for adding, updating, and deleting products.  
- Added product search by name feature.  
- Highlighted stock quantities below 5 with red text.  
- Developed purchase and sales modules with dynamic stock updates.  
- Created graphical statistics screen using Chart.js.  
- Displayed error messages for products with insufficient stock for sale.  
- Integrated demand forecasting module based on average sales over the last 3 months.  
- Added financial report displaying purchase, sales, and net profit information.  

