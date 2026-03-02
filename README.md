# Photomodel-agency-management
Web application for the management of a photomodel agency, featuring a simple web interface. It allows the registration of system users who can then manage entries such as models, impresarios and events through an SQL server database.

## Features
- User registration and login
- Database table visualization
- Full CRUD operations on models, impresarios and events
- Filtering models by attributes such as hair color and height category
- Complex queries with subqueries
- SQL queries using JOINs across multiple tables

## Built with
- Java servlets and JSP
- HTML/CSS
- JDBC and SQL Server
- Apache Tomcat

## Database schema
![Database schema](docs/schema.png)

## Setup and Running
1. Clone the repository
2. Copy src/java/credentials.properties.example to src/java/credentials.properties and fill in the database details
3. Run sql/database_setup.sql in SQL Server
4. Open the project in NetBeans with Apache Tomcat configured and press Run, or build the project to generate a `.war` file and deploy it manually to Tomcat