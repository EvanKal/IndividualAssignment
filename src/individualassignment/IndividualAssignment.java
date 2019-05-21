/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package individualassignment;

import model.Student;


/**
 *
 * @author Los_e
 */
public class IndividualAssignment {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        Student student = new Student();
        student.setFirstname("Evangelos");
        student.setLastname("Kalabokis");
        student.setStudentid(10);
        
        System.out.println(student.toString());
        
        
    }
    
}
