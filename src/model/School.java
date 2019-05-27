/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import at.favre.lib.crypto.bcrypt.BCrypt;
import dao.AssignmentDAO;
import dao.AssignmentInCourseDAO;
import dao.CourseDAO;
import dao.IndividualAssignmentDAO;
import dao.StudentDAO;
import dao.StudentInCourseDAO;
import dao.TrainerDAO;
import dao.TrainerInCourseDAO;
import dao.UserDAO;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Scanner;
import menus.HeadmasterMenu;
import menus.StudentMenu;
import menus.TrainerMenu;
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
//        setLoggedinuser(Utils.DefaultLogInAsHeadmaster());
        System.out.println("Welcome " + loggedinuser.getUsername() + "!");

        if (loggedinuser.getRole().equals("headmaster")) {
            HeadmasterMenu.headmasterMainMenu(sc);
        } else if (loggedinuser.getRole().equals("student")) {
            StudentMenu.studentMainMenu(sc, this);
        } else if (loggedinuser.getRole().equals("trainer")) {
            TrainerMenu.trainerMainMenu(sc, this);
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

    public static void pickAndEditCourseDates(Scanner sc) {
        System.out.println("Editing course dates");

        System.out.println("Pick stream");
        String stream = InputUtils.inputStringStream(sc);
        System.out.println("Pick type");
        String type = InputUtils.inputStringType(sc);
        ArrayList<Course> courselist = CourseDAO.getAllCoursesOrderedByStartdatePerStreamType(stream, type);

        if (courselist.size() > 0) {

            int indexofcourse = Utils.printListElementsAndPickOne(sc, courselist);
            Utils.editCourseDates(sc, courselist.get(indexofcourse - 1));
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

    public static void appointStudentsToCourse(Scanner sc) {
        System.out.println("Appointing students to course");

        ArrayList<Course> courselist = CourseDAO.getAllCoursesOrderedByStreamType();

        if (courselist.size() > 0) {

            ArrayList<StudentInCourse> list = new ArrayList();

            int indexofcourse = Utils.printListElementsAndPickOne(sc, courselist);
            Course course = courselist.get(indexofcourse - 1);

            ArrayList<Student> studentlist = StudentInCourseDAO.getStudentsNotAppointedToCourse(course.getCourseid(), course.getStream(), course.getType());

            if (studentlist.size() > 0) {
                ArrayList<Integer> studentschosen = Utils.printListElementsAndCollectChoices(sc, studentlist);

                for (Integer index : studentschosen) {
                    StudentInCourse studentincourse = new StudentInCourse();

                    studentincourse.setCourse(course);
                    studentincourse.setStudent(studentlist.get(index - 1));
                    studentincourse.setEnrolled(false);

                    StudentInCourseDAO.insertStudentInCourse(studentincourse);
                }
            } else {
                System.out.println("Currently no students available to be appointed to a " + course.getStream() + " " + course.getType() + "course");
            }

        }

    }

    public static void enrollStudentsToCourse(Scanner sc) {

        System.out.println("Enrolling student to course");

        ArrayList<Course> courselist = CourseDAO.getAllCoursesOrderedByStreamType();

        if (courselist.size() > 0) {

            ArrayList<StudentInCourse> list = new ArrayList();

            int indexofcourse = Utils.printListElementsAndPickOne(sc, courselist);
            Course course = courselist.get(indexofcourse - 1);

            System.out.println("Printing not enrolled users to course " + course.toString());
            ArrayList<Student> studentlist = StudentInCourseDAO.getStudentsNotEnrolledToCourse(course.getCourseid());

            if (studentlist.size() > 0) {
                ArrayList<Integer> studentschosen = Utils.printListElementsAndCollectChoices(sc, studentlist);

                for (Integer index : studentschosen) {
                    StudentInCourse studentincourse = new StudentInCourse();

                    studentincourse.setCourse(course);
                    studentincourse.setStudent(studentlist.get(index - 1));
                    studentincourse.setEnrolled(true);

                    StudentInCourseDAO.updateStudentInCourse(studentincourse);
                    School.appointAssignmentsToStudent(course, studentlist.get(index - 1));
                }
            } else {
                System.out.println("Currently no students to be enrolled.");
            }
        }

    }

    public static void dismissStudentsFromCourse(Scanner sc) {

        System.out.println("Dismissing students from course");

        ArrayList<Course> courselist = CourseDAO.getAllCoursesOrderedByStreamType();

        if (courselist.size() > 0) {

            ArrayList<StudentInCourse> list = new ArrayList();

            int indexofcourse = Utils.printListElementsAndPickOne(sc, courselist);
            Course course = courselist.get(indexofcourse - 1);

            ArrayList<Student> studentlist = StudentInCourseDAO.getStudentsAppointedToCourse(course.getCourseid());

            if (studentlist.size() > 0) {
                ArrayList<Integer> studentschosen = Utils.printListElementsAndCollectChoices(sc, studentlist);

                for (Integer index : studentschosen) {

                    StudentInCourseDAO.deleteStudentFromCourse(studentlist.get(index - 1).getStudentid(), course.getCourseid());

                }
            } else {
                System.out.println("Currently no students appointed to " + course.toString());
            }
        }

    }

    public static void appointTrainersToCourse(Scanner sc) {
        System.out.println("Appointing trainers to course");

        ArrayList<Course> courselist = CourseDAO.getAllCoursesOrderedByStreamType();

        if (courselist.size() > 0) {

            ArrayList<TrainerInCourse> list = new ArrayList();

            int indexofcourse = Utils.printListElementsAndPickOne(sc, courselist);
            Course course = courselist.get(indexofcourse - 1);

            ArrayList<Trainer> trainerlist = TrainerInCourseDAO.getTrainersNotAppointedToCourse(course.getCourseid());

            if (trainerlist.size() > 0) {
                ArrayList<Integer> trainerschosen = Utils.printListElementsAndCollectChoices(sc, trainerlist);

                for (Integer index : trainerschosen) {
                    TrainerInCourse trainerincourse = new TrainerInCourse();

                    trainerincourse.setCourse(course);
                    trainerincourse.setTrainer(trainerlist.get(index - 1));

                    TrainerInCourseDAO.insertTrainerInCourse(trainerincourse);
                }
            } else {
                System.out.println("Currently no trainers available to be appointed to course");
            }

        }

    }

    public static void dismissTrainersFromCourse(Scanner sc) {

        System.out.println("Dismissing trainers from course");

        ArrayList<Course> courselist = CourseDAO.getAllCoursesOrderedByStreamType();

        if (courselist.size() > 0) {

            ArrayList<TrainerInCourse> list = new ArrayList();

            int indexofcourse = Utils.printListElementsAndPickOne(sc, courselist);
            Course course = courselist.get(indexofcourse - 1);

            ArrayList<Trainer> trainerlist = TrainerInCourseDAO.getTrainersAppointedToCourse(course.getCourseid());

            if (trainerlist.size() > 0) {
                ArrayList<Integer> trainerschosen = Utils.printListElementsAndCollectChoices(sc, trainerlist);

                for (Integer index : trainerschosen) {

                    TrainerInCourseDAO.deleteTrainerFromCourse(trainerlist.get(index - 1).getTrainerid(), course.getCourseid());

                }
            } else {
                System.out.println("Currently no trainers appointed to " + course.toString());
            }
        }

    }

    public static void appointAssignmentsToCourse(Scanner sc) {
        System.out.println("Appointing assignments to course");

        ArrayList<Course> courselist = CourseDAO.getAllCoursesOrderedByStreamType();

        if (courselist.size() > 0) {

            ArrayList<AssignmentInCourse> list = new ArrayList();

            int indexofcourse = Utils.printListElementsAndPickOne(sc, courselist);
            Course course = courselist.get(indexofcourse - 1);

            ArrayList<Assignment> assignmentlist = AssignmentInCourseDAO.getAssignmentsNotAppointedToCourse(course.getCourseid());

            if (assignmentlist.size() > 0) {
                ArrayList<Integer> assignmentschosen = Utils.printListElementsAndCollectChoices(sc, assignmentlist);

                for (Integer index : assignmentschosen) {
                    AssignmentInCourse assignmentincourse = new AssignmentInCourse();

                    assignmentincourse.setCourse(course);
                    assignmentincourse.setAssignment(assignmentlist.get(index - 1));
                    assignmentincourse.setSubmissiondatetime(School.defineSubmissiondatetime(sc, course, assignmentlist.get(index - 1)));

                    AssignmentInCourseDAO.insertAssignmentInCourse(assignmentincourse);
                }
            } else {
                System.out.println("Currently no assignments available to be appointed at course");
            }

        }

    }

    public static String defineSubmissiondatetime(Scanner sc, Course course, Assignment assignment) {

        System.out.println("Setting submission date and time for " + assignment.toString() + " in " + course.toString());
        System.out.println("Submission date (format yyyy-mm-dd):");
        LocalDate submissiondate = LocalDate.parse(InputUtils.inputStringSubmissionDate(sc, course.getStartdate().toString()));
        System.out.println("Submission time (format hh:mm):");
        LocalTime submissiontime = LocalTime.parse(InputUtils.inputStringTime(sc));

        LocalDateTime submissiondatetime = LocalDateTime.of(submissiondate, submissiontime);

        return submissiondatetime.toString();

    }

    public static void dismissAssignmentsFromCourse(Scanner sc) {

        System.out.println("Dismissing assignments from course");

        ArrayList<Course> courselist = CourseDAO.getAllCoursesOrderedByStreamType();

        if (courselist.size() > 0) {

            ArrayList<AssignmentInCourse> list = new ArrayList();

            int indexofcourse = Utils.printListElementsAndPickOne(sc, courselist);
            Course course = courselist.get(indexofcourse - 1);

            ArrayList<Assignment> assignmentlist = AssignmentInCourseDAO.getAssignmentsAppointedToCourse(course.getCourseid());

            if (assignmentlist.size() > 0) {
                ArrayList<Integer> assignmentschosen = Utils.printListElementsAndCollectChoices(sc, assignmentlist);

                for (Integer index : assignmentschosen) {

                    AssignmentInCourseDAO.deleteAssignmentFromCourse(assignmentlist.get(index - 1).getAssignmentid(), course.getCourseid());

                }
            } else {
                System.out.println("Currently no assignments appointed to " + course.toString());
            }
        }

    }

    public static void editSubmissiondatetimeOfAssignment(Scanner sc) {

        System.out.println("Editing submission date and time of  assignment");

        ArrayList<Course> courselist = CourseDAO.getAllCoursesOrderedByStreamType();

        if (courselist.size() > 0) {

            ArrayList<AssignmentInCourse> list = new ArrayList();

            int indexofcourse = Utils.printListElementsAndPickOne(sc, courselist);
            Course course = courselist.get(indexofcourse - 1);

            System.out.println("Select the assignments to edit their submission date and time");
            ArrayList<Assignment> assignmentlist = AssignmentInCourseDAO.getAssignmentsAppointedToCourse(course.getCourseid());

            if (assignmentlist.size() > 0) {
                ArrayList<Integer> assignmentschosen = Utils.printListElementsAndCollectChoices(sc, assignmentlist);

                for (Integer index : assignmentschosen) {
                    AssignmentInCourse assignmentincourse = new AssignmentInCourse();

                    assignmentincourse.setCourse(course);
                    assignmentincourse.setAssignment(assignmentlist.get(index - 1));
                    assignmentincourse.setSubmissiondatetime(School.defineSubmissiondatetime(sc, course, assignmentlist.get(index - 1)));

                    AssignmentInCourseDAO.updateAssignmentInCourse(assignmentincourse);
                }
            } else {
                System.out.println("Currently no assignments appointed to course.");
            }
        }

    }

    public static void enrollStudentToCourses(Scanner sc, int studentid) {

        System.out.println("Enrolling to course");

        ArrayList<Course> courselist = StudentInCourseDAO.getCoursesStudentIsNotEnrolledTo(studentid);

        if (courselist.size() > 0) {

            System.out.println("Printing courses that student with ID " + studentid + "has not been enrolled to.");

            ArrayList<Integer> courseschosen = Utils.printListElementsAndCollectChoices(sc, courselist);

            for (Integer index : courseschosen) {
                StudentInCourse studentincourse = new StudentInCourse();
                Course course = courselist.get(index - 1);
                Student student = StudentDAO.getStudentById(studentid);

                studentincourse.setCourse(course);
                studentincourse.setStudent(student);
                studentincourse.setEnrolled(true);

                StudentInCourseDAO.updateStudentInCourse(studentincourse);
                School.appointAssignmentsToStudent(course, student);
            }
        } else {
            System.out.println("Currently no courses to be enrolled to.");
        }

    }
    
    public static void submitAssignment(Scanner sc, int studentid) {

        System.out.println("Submitting assignments");

        ArrayList<IndividualAssignment> assignmentlist = IndividualAssignmentDAO.getNotSubmittedIndividualAssignmentsByStudentId(studentid);

        if (assignmentlist.size() > 0) {

            System.out.println("Printing not submitted assignments of student with ID " + studentid);

            ArrayList<Integer> assignmentschosen = Utils.printListElementsAndCollectChoicesAssignments(sc, assignmentlist);

            for (Integer index : assignmentschosen) {
                
                IndividualAssignment individualassignment = assignmentlist.get(index - 1);
                
                individualassignment.setSubmitted(true);

                IndividualAssignmentDAO.updateIndividualAssignment(individualassignment);
            }
        } else {
            System.out.println("Currently no courses to be enrolled to.");
        }

    }

    public static void appointAssignmentsToStudent(Course course, Student student) {

        ArrayList<Assignment> assignmentlist = AssignmentInCourseDAO.getAssignmentsAppointedToCourse(course.getCourseid());

        if (assignmentlist.size() > 0) {

            for (Assignment assignment : assignmentlist) {
                IndividualAssignment individualassignment = new IndividualAssignment(assignment);

                individualassignment.setStudent(student);
                individualassignment.setAssignment(assignment);
                individualassignment.setCourseid(course.getCourseid());

                IndividualAssignmentDAO.insertIndividualAssignment(individualassignment);
            }
        } else {
            System.out.println("Currently no assignments in course " + course.getTitle() + " to be appointed to student");

        }

    }
    
    public static void markAssignments(Scanner sc) {

        System.out.println("Marking assignments");

        ArrayList<IndividualAssignment> assignmentlist = IndividualAssignmentDAO.getAllSubmittedIndividualAssignments();

        if (assignmentlist.size() > 0) {

            System.out.println("Printing submitted assignments");

            ArrayList<Integer> assignmentschosen = Utils.printListElementsAndCollectChoicesAssignmentsToMark(sc, assignmentlist);

            for (Integer index : assignmentschosen) {
                
                IndividualAssignment individualassignment = assignmentlist.get(index - 1);
                
                System.out.println("Set oral mark: ");
                int oralmark = InputUtils.inputInt(sc);
                System.out.println("Set total mark: ");
                int totalmark = InputUtils.inputInt(sc);
                
                individualassignment.setOralmark(oralmark);
                individualassignment.setTotalmark(totalmark);

                IndividualAssignmentDAO.updateIndividualAssignment(individualassignment);
            }
        } else {
            System.out.println("Currently no assignments submitted.");
        }

    }

}
