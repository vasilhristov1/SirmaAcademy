Pair of Employees Application
Task Description
Create an application that identifies pairs of employees who have worked together on common projects for the longest period of time, recording the duration of each collaboration. The application reads input data from a CSV file, inserts it into a database table named 'employee,' and then identifies and records the pairs of employees with the longest collaboration on common projects.

Prerequisites
Before starting the application, make sure to modify the application.properties file to configure the database settings.

Running the Application
Open your database and ensure it is running.
Run the Spring Boot application.
Testing with Postman
Use Postman to test the application by sending appropriate requests to the exposed endpoints.

Workflow
Read and Insert Data: The application reads all data from a CSV file (input.csv) and inserts it into the newly created database table 'employee.'

Identify Pairs of Employees: The application checks if there are pairs of employees who worked together on common projects in the same time period. If such pairs exist, the project ID, the two employees' IDs, and the duration they worked on the project are recorded.

Update Files: Every time the data in the database is manipulated, two files are updated:

The file with all employee data (input.csv) is updated with any new entries.
The file with the final result (output.csv) is updated with information about pairs of employees who worked together for the longest period.
File Structure
input.csv: Contains input data with the format: EmpID, ProjectID, DateFrom, DateTo.
output.csv: The final result file containing information about pairs of employees who worked together for the longest period.
application.properties: Configuration file for database settings.
src/main/java/com/academy/PairOfEmployees: Contains the Java source code for the application.
PairOfEmployeesApplication.java: Main class to run the Spring Boot application.
WorkCalculator.java: Class to calculate the duration of employee pairs' collaboration.
WorkService.java: Service class to interact with the database and perform calculations.
CSVWriter.java: Class to write data to CSV files.
CSVReader.java: Class to read data from CSV files.
models/: Contains the Employee and Project model classes.
services/: Contains service classes for employees and projects.
controllers/: Contains controller classes for handling HTTP requests.
repositories/: Contains repository interfaces for database interactions.
operationsWithFiles/: Contains interfaces and classes for reading and writing files.
Note
This application provides a simple solution for identifying pairs of employees who worked together for the longest period. For larger-scale applications, additional considerations, optimizations, and error handling may be necessary. Always ensure that the database connection is properly configured and that you have the necessary dependencies installed.





