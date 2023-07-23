# MiniProject-Baby-Names
Assignments from OOP Course on Java Programming, week 4. https://www.coursera.org/learn/java-programming/supplement/VeM7L/miniproject-exercise-guide

PROJECT TITLE: "MiniProject: Baby Names"

PURPOSE OF PROJECT: Use of CSV Parser to manage data in different datasets that 
                    contain information about baby names born between 1800-2014 years. 
                    Several tasks had to be done: 
                    1) find the number of girls names, the number of boys names and the 
                    total names in the file. Also, find number of females and males born; 
                    2) find the rank of a specific name and gender in dataset (rank 1 is the 
                    name with the largest number of births); 
                    3) given rank, year and gender find the name with this rank in a dataset; 
                    4) find what name would have been named if they were born in a different 
                    year, based on the same popularity (same rank, but different year); 
                    5) find the year with the highest rank for a given name and gender, through
                    multiple files; 
                    6) find the average rank for a name and gender from multiple files;
                    7) find the total number of births of those names with the same gender and 
                    same year who are ranked higher than given name and gender.

DATE: 22.07.2023

HOW TO START THIS PROJECT: Use BlueJ Environment to open project named "package.bluej". 
                           Find inside of this project 1 class: Part1 - compile, 
                           create object of type Part1 and start one of the following functions: 
                           "testTotalBirths" for task 1; 
                           "testGetRank" for task 2; 
                           "testGetName" for task 3; 
                           "testWhatIsNameInYear" for task 4; 
                           "testYearOfHighestRank" for task 5; 
                           "testGetAverageRank" for task 6;
                           "testGetTotalBirthsRankedHigher" for task 7;
                           and select one or multiple CSV file(s) from data folder;

AUTHOR: Gusev Roman

USER INSTRUCTIONS: you will need 3 libraries: 
                   "edu.duke.*" (simplified version of different functions from Java) 
                   "org.apache.commons.csv.*" (CSV Parser)
                   and "java.io.*" (File usage)
