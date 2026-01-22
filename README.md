# java-voting-system

## Project Overview

This project is a console-based Voting System developed in Java.

It allows an Admin to manage candidates and Voters to register, log in, and cast their vote securely.

The system uses file handling to permanently store data, ensuring that candidates, votes, and voter records are not lost when the program exits.

This project is designed for learning and academic purposes and demonstrates practical usage of core Java concepts.

## Features
# Admin Module

Secure admin login

Add new candidates

Remove existing candidates

View list of candidates

View voting results (vote count of each candidate)

# Voter Module

Voter registration with age verification (18+)

Login using username and password

View available candidates

## File Handling (Core Concept)

This project uses Java File Handling to store and retrieve data permanently.

# Files Used

# Candidates File
Stores:
Candidate ID

Candidate Name

Vote Count

# Voters File
Stores:
Username

Password

Age

Voting Status (true / false)

# Why File Handling?
Data remains saved after program ends

Voters cannot vote more than once

Candidates and votes persist between program runs

Makes the system realistic and reliable

# Technologies & Concepts Used
Java

Scanner (for input handling)

File & FileWriter (for file handling)

ArrayList (for dynamic data storage)

Loops and conditional statements

Basic authentication system

## How to Run the Project

Clone this repository:

       git clone https://github.com/nazalveena71@gmail.com/java-voting-system.git

1. Open the project in any Java IDE (NetBeans, IntelliJ, Eclipse, etc.)

2. Make sure file paths are correct (or use relative paths).

3. Run the VotingSystem.java file.

4. Follow the on-screen menu instructions.
   
# Default Admin Credentials
Username: admin
Password: admin123

# Learning Outcomes

. Understanding Java file input/output

. Implementing login and registration systems

. Preventing duplicate data entries

. Working with ArrayLists instead of databases

. Building a real-world console application

# Notes

This is a console-based application, not GUI-based.

Passwords are stored in plain text (for learning purposes only).

The project does not use a database; files act as persistent storage.

# Author

Developed by: Alveena naz
BS Software Engineering Student
Language: Java
Cast vote (only once per voter)

Duplicate voting prevention
