/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import at.favre.lib.crypto.bcrypt.BCrypt;
import dao.AssignmentDAO;
import dao.CourseDAO;
import dao.StudentDAO;
import dao.TrainerDAO;
import dao.UserDAO;
import java.util.ArrayList;
import java.util.Scanner;
import menus.HeadmasterMenu;
import utils.InputUtils;
import utils.Utils;

/**
 *
 * @author Los_e
 */
public class School {

    Scanner sc = new Scanner(System.in);
    private User loggedinuser;

    public User getLoggedinuser() {
        return loggedinuser;
    }

    public void setLoggedinuser(User loggedinuser) {
        this.loggedinuser = loggedinuser;
    }

    public void initiate() {

        System.out.println("Welcome");

//        while (loggedinuser == null) {
//
//            System.out.println("Please log in.");
//            setLoggedinuser(Utils.logIn(sc));
//            System.out.println(loggedinuser.toString());
//
//        }
        setLoggedinuser(Utils.DefaultLogInAsHeadmaster());

        System.out.println("Welcome " + loggedinuser.getUsername() + "!");

        if (loggedinuser.getRole().equals("headmaster")) {
            HeadmasterMenu.headmasterMainMenu(sc);
        }

    }

    public static String createUser(Scanner sc, String role) {

        System.out.println("Type username for new " + role + " :");
        String username = InputUtils.inputString(sc);

        while (UserDAO.checkIfUsernameExists(username) != 0) {
            System.out.println("Username " + username + " already exists in database. Type a different one: ");
            username = InputUtils.inputString(sc);
        }

        System.out.println("Type password:");
        String password = InputUtils.inputString(sc);
        String bcryptHashString = BCrypt.withDefaults().hashToString(12, password.toCharArray());

        User user = new User();
        user.setUsername(username);
        user.setPassword(bcryptHashString);
        user.setRole(role);

        UserDAO.insertUser(user);
        return username;
    }

    public static void createStudent(Scanner sc) {

        System.out.println("Creating new student");

        String username = School.createUser(sc, "student");
        int userid = UserDAO.checkIfUsernameExists(username);

        if (userid != 0) {
            Student student = new Student();
            student.setStudentid(userid);
            System.out.println("Type firstname:");
            student.setFirstname(InputUtils.inputStringName(sc));
            System.out.println("Type lastname:");
            student.setLastname(InputUtils.inputStringName(sc));
            System.out.println("Type dateofbirth:");
            student.setDateofbirth(InputUtils.inputStringDate(sc));
            System.out.println("Type tuitionfees:");
            student.setTuitionfees(InputUtils.inputFloat(sc));
            System.out.println("Type stream:");
            student.setStream(InputUtils.inputStringStream(sc));
            System.out.println("Type type:");
            student.setType(InputUtils.inputStringType(sc));

            StudentDAO.insertStudent(student);
        } else {
            System.out.println("Something went wrong. User record was not found");
        }

    }

    public static void pickAndEditStudent(Scanner sc) {
        System.out.println("Editing student");

        ArrayList<Student> studentlist = StudentDAO.getAllStudents();

        if (studentlist.size() > 0) {

            int indexofstudent = Utils.printListElementsAndPickOne(sc, studentlist);
            Utils.editEntity(sc, studentlist.get(indexofstudent - 1));
            StudentDAO.updateStudent(studentlist.get(indexofstudent - 1));

        }

    }

    public static void pickAndDeleteStudent(Scanner sc) {
        System.out.println("Deleting student");

        ArrayList<Student> studentlist = StudentDAO.getAllStudents();

        if (studentlist.size() > 0) {

            int indexofstudent = Utils.printListElementsAndPickOne(sc, studentlist);
            UserDAO.deleteUser(studentlist.get(indexofstudent - 1).getStudentid());

        }

    }

    public static void createCourse(Scanner sc) {

        System.out.println("Creating new course");

        Course course = new Course();
        System.out.println("Type Title:");
        course.setTitle(InputUtils.inputString(sc));
        System.out.println("Type Stream:");
        course.setStream(InputUtils.inputStringStream(sc));
        System.out.println("Type Type:");
        course.setType(InputUtils.inputStringType(sc));
        System.out.println("Type start date:");
        course.setStartdate(InputUtils.inputStringDate(sc));
        System.out.println("Type end date:");
        course.setEnddate(InputUtils.inputStringEndDate(sc, course.getStartdate().toString()));


        CourseDAO.insertCourse(course);

    }

    public static void pickAndEditCourse(Scanner sc) {
        System.out.println("Editing course");

        ArrayList<Course> courselist = CourseDAO.getAllCourses();

        if (courselist.size() > 0) {

            int indexofcourse = Utils.printListElementsAndPickOne(sc, courselist);
            Utils.editEntity(sc, courselist.get(indexofcourse - 1));
            CourseDAO.updateCourse(courselist.get(indexofcourse - 1));

        }

    }

    public static void pickAndDeleteCourse(Scanner sc) {
        System.out.println("Deleting course");

        ArrayList<Course> courselist = CourseDAO.getAllCourses();

        if (courselist.size() > 0) {

            int indexofcourse = Utils.printListElementsAndPickOne(sc, courselist);
            CourseDAO.deleteCourse(courselist.get(indexofcourse - 1).getCourseid());

        }

    }
    
    public static void createAssignment(Scanner sc) {

        System.out.println("Creating new assignment");

        Assignment assignment = new Assignment();
        System.out.println("Type Title:");
        assignment.setTitle(InputUtils.inputString(sc));
        System.out.println("Type Description:");
        assignment.setDescription(InputUtils.inputString(sc));

        AssignmentDAO.insertAssignment(assignment);

    }

    public static void pickAndEditAssignment(Scanner sc) {
        System.out.println("Editing assignment");

        ArrayList<Assignment> assignmentlist = AssignmentDAO.getAllAssignments();

        if (assignmentlist.size() > 0) {

            int indexofassignment = Utils.printListElementsAndPickOne(sc, assignmentlist);
            Utils.editEntity(sc, assignmentlist.get(indexofassignment - 1));
            AssignmentDAO.updateAssignment(assignmentlist.get(indexofassignment - 1));

        }

    }

    public static void pickAndDeleteAssignment(Scanner sc) {
        System.out.println("Deleting assignment");

        ArrayList<Assignment> assignmentlist = AssignmentDAO.getAllAssignments();

        if (assignmentlist.size() > 0) {

            int indexofassignment = Utils.printListElementsAndPickOne(sc, assignmentlist);
            AssignmentDAO.deleteAssignment(assignmentlist.get(indexofassignment - 1).getAssignmentid());

        }

    }

    public static void createTrainer(Scanner sc) {
        System.out.println("Creating new trainer");

        String username = School.createUser(sc, "trainer");
        int userid = UserDAO.checkIfUsernameExists(username);

        if (userid != 0) {
            Trainer trainer = new Trainer();
            trainer.setTrainerid(userid);
            System.out.println("Type firstname:");
            trainer.setFirstname(InputUtils.inputStringName(sc));
            System.out.println("Type lastname:");
            trainer.setLastname(InputUtils.inputStringName(sc));
            System.out.println("Type subject:");
            trainer.setSubject(InputUtils.inputString(sc));

            TrainerDAO.insertTrainer(trainer);
        } else {
            System.out.println("Something went wrong. User record was not found");
        }
    }

    public static void pickAndEditTrainer(Scanner sc) {
        System.out.println("Editing trainer");

        ArrayList<Trainer> trainerlist = TrainerDAO.getAllTrainers();

        if (trainerlist.size() > 0) {

            int indexoftrainer = Utils.printListElementsAndPickOne(sc, trainerlist);
            Utils.editEntity(sc, trainerlist.get(indexoftrainer - 1));
            TrainerDAO.updateTrainer(trainerlist.get(indexoftrainer - 1));

        }

    }

    public static void pickAndDeleteTrainer(Scanner sc) {
        System.out.println("Deleting trainer");

        ArrayList<Trainer> trainerlist = TrainerDAO.getAllTrainers();

        if (trainerlist.size() > 0) {

            int indexoftrainer = Utils.printListElementsAndPickOne(sc, trainerlist);
            UserDAO.deleteUser(trainerlist.get(indexoftrainer - 1).getTrainerid());

        }

    }

}
