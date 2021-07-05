/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package studentdatabaseapp;

import java.util.ArrayList;
import java.util.List;

/**
 * Main class.
 * @author adamw
 */
public class StudentDatabaseApp {
    // class variables
    private static List<Student> students = new ArrayList<>();
    
    // main method
    public static void main(String[] args)
    {
        // Ask how many Students we want to add
        
        // Create n number of Students - for each Student ask for firstName, lastName, and year
        
    }
    
    // getter and setter methods
    public static List<Student> getStudents()
    {
        return StudentDatabaseApp.students;
    }
}
