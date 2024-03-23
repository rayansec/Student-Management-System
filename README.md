# Student Management System

This is a simple student management system implemented in Java. It allows users to perform various operations related to student management, such as adding, updating, and deleting student records.

## Features

- Add new student records with details like name, roll number, and grades.
- Update existing student records.
- Delete student records.
- View all student records.
- Search for a student by roll number.

## Technologies Used

- Java
- Object-Oriented Programming (OOP) principles

## How to Use

1. Clone the repository to your local machine:

```bash
git clone https://github.com/your-username/student-management-system.git
```
## File Paths

This project utilizes a properties file (`config.properties`) to store file paths. Before running the application, ensure that the file paths in `config.properties` are correctly set according to your local file system.

Here's how you can update the file paths:

1. Open the `config.properties` file located in the `src/resource` directory.
2. Update the values of the following properties according to the location of your data files:
    - `student-path`: Path to the student data file.
    - `course-path`: Path to the course data file.
    - `grade-path`: Path to the grade data file.

Example:
```properties
student-path=C:\\Java\\student management system\\data\\student.data
course-path=C:\\Java\\student management system\\data\\course.data
grade-path=C:\\Java\\student management system\\data\\grade.data
