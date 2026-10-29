JerseyMS – Football Jersey Management System

A secure, browser-based web application for managing football jersey stock. An authorised admin can log in, view the full stock, search by team or player, and add, edit or delete jersey records.

Built as a mini project for Advance Internet Programming (26CAH-614), MCA, University Institute of Computing, Chandigarh University.

Author: Ashutosh Dash (UID: 26MCD10089) Submitted to: Ms. Tanisha, Assistant Professor

Problem Statement

Football shops, clubs and academies that track jersey stock in paper registers or spreadsheets struggle to search quickly, avoid duplicates and control who can change records. JerseyMS replaces this with a lightweight, secure web application backed by a database.

Features
Admin login and logout using HTTP sessions
All stock pages protected by a servlet filter (AuthFilter)
View the complete jersey stock
Search by team name or player name (partial match)
Add, edit and delete jerseys (full CRUD) through one shared form
Delete confirmation dialog
Form validation: required fields, jersey number 1–99, non-negative price and quantity
SQL injection protection via PreparedStatement
Tech Stack
Layer	Technology
Language	Java 17
Controller	Jakarta Servlet / Filter (Jakarta EE 10)
View	JSP with JSTL 3.0
Database	MySQL with Connector/J (JDBC)
Build tool	Apache Maven (WAR packaging)
Server	Apache Tomcat 10+ (any Jakarta EE 10 servlet container)
IDE / VCS	NetBeans, Git, GitHub

Note: Tomcat 9 or older will not work, because the project uses jakarta.* package names.

Architecture

The project follows the MVC pattern with a DAO layer.

Browser → AuthFilter → Servlet (Controller) → DAO → DBConnection → MySQL
                                   ↓
                              JSP (View)

Every request to a stock page first passes through AuthFilter. With a valid session it continues to JerseyServlet; otherwise the user is redirected to /login. Servlets call the DAO classes, which run parameterised SQL, and the results are forwarded to JSP views.

Project Structure

Package: com.aip.jersey

Component	Role
Jersey.java	Model (POJO): id, teamName, playerName, jerseyNumber, size, price, quantity
DBConnection.java	JDBC connection utility
JerseyDAO.java	getAll, search, get, add, update, delete
UserDAO.java	validate() checks credentials with a parameterised query
AuthFilter.java	Mapped to /jerseys, /jerseys.jsp, /jerseyForm.jsp; checks the user session attribute
LoginServlet.java	GET shows login page; POST validates credentials and creates the session
JerseyServlet.java	Handles list, search, add, edit and delete via the action parameter
LogoutServlet.java	Invalidates the session and redirects to login
index.jsp	Home page with links to login and jersey list
login.jsp	Login form with error message
jerseys.jsp	Jersey list (JSTL c:forEach), search, Edit/Delete/Logout controls
jerseyForm.jsp	Shared Add/Edit form
Database Design

Database: jerseydb

users

Column	Notes
id	Primary key
username	Admin username
password	Admin password

jerseys

Column	Notes
id	Primary key
team_name	Searched with LIKE
player_name	Searched with LIKE
jersey_number	1–99
size	e.g. S, M, L, XL
price	Non-negative
quantity	Non-negative
Sample setup script
sql
CREATE DATABASE jerseydb;
USE jerseydb;

CREATE TABLE users (
    id INT AUTO_INCREMENT PRIMARY KEY,
    username VARCHAR(50) NOT NULL UNIQUE,
    password VARCHAR(100) NOT NULL
);

CREATE TABLE jerseys (
    id INT AUTO_INCREMENT PRIMARY KEY,
    team_name VARCHAR(100) NOT NULL,
    player_name VARCHAR(100) NOT NULL,
    jersey_number INT NOT NULL,
    size VARCHAR(10) NOT NULL,
    price DECIMAL(10,2) NOT NULL,
    quantity INT NOT NULL
);

INSERT INTO users (username, password) VALUES ('admin', 'admin123');
Getting Started
Prerequisites
JDK 17
Apache Maven
MySQL Server
Apache Tomcat 10+
NetBeans (optional)
Setup
Clone the repository:
bash
   git clone https://github.com/ashutoshdash8/JerseyMS.git
   cd JerseyMS
Create the database and tables using the SQL script above.
Update the MySQL URL, username and password in DBConnection.java.
Build the WAR file:
bash
   mvn clean package
Deploy target/*.war to Tomcat's webapps folder (or run from NetBeans).
Open http://localhost:8080/JerseyMS/ and log in as the admin.
Test Cases
#	Test	Expected result
1	Valid login	Session created, redirected to /jerseys
2	Invalid login	"Invalid username or password" shown
3	Open /jerseys without login	Redirected to /login
4	Search by team/player	Only matching jerseys listed
5	Add jersey	New jersey appears in list
6	Edit jersey	Updated values appear
7	Delete jersey	Removed on confirm; kept on cancel
8	Logout	Session invalidated, login page shown
Security Notes
PreparedStatement is used throughout to prevent SQL injection.
Stock pages are blocked for unauthenticated users by AuthFilter.
Passwords are currently stored in plain text, which is acceptable for a mini project only. See future work.
Scope and Limitations
Single role (admin) only
Customer accounts, ordering, billing and payments are out of scope
Future Work
Password hashing (e.g. BCrypt)
Customer accounts and online ordering
Billing and payment integration
Multiple user roles
Reports and low-stock alerts
References
Jakarta Servlet and JSP Specifications, Eclipse Foundation
MySQL Reference Manual and Connector/J Developer Guide, Oracle
Apache Maven Getting Started Guide
D. Alur, J. Crupi and D. Malks, Core J2EE Patterns, 2nd ed., Prentice Hall, 2003
License

Developed for academic purposes.
