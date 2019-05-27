/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package individualassignment;

import dao.StudentDAO;
import dao.UserDAO;
import java.util.Scanner;
import model.School;
import model.Student;
import model.User;
import utils.PrintUtils;
import utils.Utils;

/**
 *
 * @author Los_e
 */
public class IndividualAssignment {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {

        School school = new School();
        
        school.setLoggedinuser(UserDAO.getUserTryingToLogIn("student2"));
        
        while (true) {
                school.initiate();
        }
//PrintUtils.printList(StudentDAO.getAllStudents());

//Process to add new student
//        User user = new User();

//        user.setUsername("nick10");
//        user.setPassword("nick10");
//        user.setRole("student");
//        
//        UserDAO.insertUser(user);
//
//        Student student = new Student();
//
//        student.setStudentid(6);
//        student.setFirstname("Nikos");
//        student.setLastname("Aksios");
//        student.setDateofbirthstr("1990-07-18");
//        student.setTuitionfees((float) 12.25);
//        student.setStream("Java");
//        student.setType("full");
//
//        StudentDAO.insertStudent(student);


    }

}
