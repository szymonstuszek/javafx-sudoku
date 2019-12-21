# JavaFX Sudoku Solver

Sudoku Solver implementation in JavaFX. The user can fill out fields with numbers and after hitting the "solve" button the algorithm will fill out the board or inform that there is no solution to a given scenario.

## Getting Started

Use git clone to copy the repository to your machine. The project was written with IntelliJ. <br/>
git clone https://github.com/szymonstuszek/javafx-sudoku.git

![alt text](https://github.com/szymonstuszek/javafx-sudoku/blob/master/src/main/resources/screens/sudoku.PNG)

## Technologies

- JDK 1.8
- JavaFX 8.0.171
- Maven

## Versioning

Used git for versioning.

## Purpose

Project created to practice writing desktop apps in Java. Used a MVC pattern to separate the backend from the view. The algorithm performs validity checks and updates the state of the board elements. The controller binds the backend and the visual part. Used lambda expressions to keep the code more readable and concise. The app uses a backtracking algorithm to find a solution to the sudoku.

## Sources

Project inspired by a task from a programming bootcamp that I took part in, extended it with a frontend layer.
https://kodilla.com/pl
