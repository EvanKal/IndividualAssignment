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
import utils.DBUtils;
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
        
        //README
        
        String readme = ""
                + "Users"
                + ""
                + "1. User passwords are same as the usernames e.g. headmaster1 - headmaster1, minaidiskon-minaidiskon"
                + ""
                + "Students"
                + ""
                + "1. Students can only be appointed to courses of their respective stream and type"
                + "2. Students must enroll to course to get their assignments, view and submit them"
                + "3. In schedule, courses may overlap regarding their start and enddates so double recordes"
                + "may appear under a certain date."
                + ""
                + "Trainers"
                + ""
                + "1. May view all the appointed students under their courses even if they haven't enrolled yet"
                + "2. May view all individual assignments and their status (Submitted or Not submitted)"
                + "3. Can mark only submitted assignments."
                + "";
        
        
        System.out.println("****Make sure to log in to your database server****");
        
        School school = new School();
        
        while (true) {
                school.initiate();
        }

    }

}
