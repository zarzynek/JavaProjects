/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package studentdatabaseapp;

import java.math.BigDecimal;
import java.util.List;

/**
 *
 * @author adamw
 */
public class Student {
    // Variables
    private String firstName;
    private String lastName;
    private int id;
    private String gradeYear;
    private List<Course> courses;
    private BigDecimal tuitionBalance;
    
    
    // Constructor 
    public Student(String aFirstName, String aLastName, String aGradeYear)
    {
        this.firstName = aFirstName;
        this.lastName = aLastName;
        this.gradeYear = aGradeYear;
        this.id = Student.generateId();
    }
       
    
    // Generate an ID
    public static int generateId()
    {
        int maxId = 0;
       if(StudentDatabaseApp.getStudents().isEmpty())
       {
           return 1;
       }
       else
       {
            for(Student student : StudentDatabaseApp.getStudents())
            {
                if(student.getId() > maxId) maxId = student.getId();
            }
            return maxId + 1;
       }
    }
    // Enroll in courses
    
    // View balance
    
    // Pay tuition
    
    // Show status
    
    // Setter and Getter methods
    public int getId()
    {
      return this.id;
    }
    public BigDecimal getTuitionBalance()
    {
        return this.tuitionBalance;
    }
}
