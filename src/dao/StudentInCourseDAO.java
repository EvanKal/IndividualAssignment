/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Course;
import model.Student;
import model.StudentInCourse;
import utils.DBUtils;

/**
 *
 * @author Los_e
 */
public class StudentInCourseDAO {

    public static ArrayList<StudentInCourse> getAllStudentsPerCourse() {

        ArrayList<StudentInCourse> list = new ArrayList<>();
        Connection con = DBUtils.getConnection();
        PreparedStatement pst = null;
        String sql = "SELECT  * FROM courses c "
                + "inner join "
                + "studentsincourses sc on sc.courseid = c.courseid "
                + "inner join "
                + "students s on sc.studentid = s.studentid "
                + "order by c.courseid;";

        try {
            pst = con.prepareStatement(sql);
            ResultSet rs = pst.executeQuery(sql);

            while (rs.next()) {

                Course course = new Course();
                course.setCourseid(rs.getInt(1));
                course.setTitle(rs.getString(2));
                course.setStream(rs.getString(3));
                course.setType(rs.getString(4));
                course.setStartdate(rs.getString(5));
                course.setEnddate(rs.getString(6));

                Student student = new Student();
                student.setStudentid(rs.getInt(10));
                student.setFirstname(rs.getString(11));
                student.setLastname(rs.getString(12));
                student.setDateofbirth(rs.getString(13));
                student.setTuitionfees(rs.getFloat(14));
                student.setStream(rs.getString(15));
                student.setType(rs.getString(16));

                StudentInCourse studentincourse = new StudentInCourse();
                studentincourse.setStudent(student);
                studentincourse.setCourse(course);
                if (rs.getInt(9) == 1) {
                    studentincourse.setEnrolled(true);
                } else {
                    studentincourse.setEnrolled(false);
                }

                list.add(studentincourse);

            }
        } catch (SQLException ex) {
            Logger.getLogger(StudentInCourseDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {

            try {
                pst.close();
            } catch (SQLException ex) {
                Logger.getLogger(StudentInCourseDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
            try {
                con.close();
            } catch (SQLException ex) {
                Logger.getLogger(StudentInCourseDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        return list;

    }
    
    public static ArrayList<StudentInCourse> getAllCoursesPerStudent(int studentid) {

        ArrayList<StudentInCourse> list = new ArrayList<>();
        Connection con = DBUtils.getConnection();
        PreparedStatement pst = null;
        String sql = "SELECT  * FROM courses c "
                + "inner join "
                + "studentsincourses sc on sc.courseid = c.courseid "
                + "inner join "
                + "students s on sc.studentid = s.studentid "
                + "where sc.studentid = ? "
                + "order by c.startdate;";

        try {
            pst = con.prepareStatement(sql);
            pst.setInt(1, studentid);
            ResultSet rs = pst.executeQuery();

            while (rs.next()) {

                Course course = new Course();
                course.setCourseid(rs.getInt(1));
                course.setTitle(rs.getString(2));
                course.setStream(rs.getString(3));
                course.setType(rs.getString(4));
                course.setStartdate(rs.getString(5));
                course.setEnddate(rs.getString(6));

                Student student = new Student();
                student.setStudentid(rs.getInt(10));
                student.setFirstname(rs.getString(11));
                student.setLastname(rs.getString(12));
                student.setDateofbirth(rs.getString(13));
                student.setTuitionfees(rs.getFloat(14));
                student.setStream(rs.getString(15));
                student.setType(rs.getString(16));

                StudentInCourse studentincourse = new StudentInCourse();
                studentincourse.setStudent(student);
                studentincourse.setCourse(course);
                if (rs.getInt(9) == 1) {
                    studentincourse.setEnrolled(true);
                } else {
                    studentincourse.setEnrolled(false);
                }

                list.add(studentincourse);

            }
        } catch (SQLException ex) {
            Logger.getLogger(StudentInCourseDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {

            try {
                pst.close();
            } catch (SQLException ex) {
                Logger.getLogger(StudentInCourseDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
            try {
                con.close();
            } catch (SQLException ex) {
                Logger.getLogger(StudentInCourseDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        return list;

    }

    public static ArrayList<Student> getStudentsNotAppointedToCourse(int courseid, String stream, String type) {

        ArrayList<Student> list = new ArrayList<Student>();
        Connection con = DBUtils.getConnection();
        PreparedStatement pst = null;
        String sql = "select table1.* from "
                + ""
                + "(select s2.* from students s2 where s2.stream=? and s2.`type`=?) as table1 "
                + ""
                + "left join "
                + ""
                + "(select s1.* from students s1 "
                + "left join studentsincourses sc1 on sc1.studentid = s1.studentid "
                + "where sc1.courseid=?) as table2 "
                + ""
                + "on table1.studentid = table2.studentid "
                + "where table2.studentid is null "
                + ";";

        try {

            pst = con.prepareStatement(sql);
            pst.setString(1, stream);
            pst.setString(2, type);
            pst.setInt(3, courseid);
            ResultSet rs = pst.executeQuery();

            while (rs.next()) {

                Student student = new Student();
                student.setStudentid(rs.getInt(1));
                student.setFirstname(rs.getString(2));
                student.setLastname(rs.getString(3));
                student.setDateofbirth(rs.getString(4));
                student.setTuitionfees(rs.getFloat(5));
                student.setStream(rs.getString(6));
                student.setType(rs.getString(7));

                list.add(student);

            }
        } catch (SQLException ex) {
            Logger.getLogger(StudentInCourseDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {

            try {
                pst.close();
            } catch (SQLException ex) {
                Logger.getLogger(StudentInCourseDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
            try {
                con.close();
            } catch (SQLException ex) {
                Logger.getLogger(StudentInCourseDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        return list;
    }

    public static ArrayList<Student> getStudentsAppointedToCourse(int courseid) {

        ArrayList<Student> list = new ArrayList<Student>();
        Connection con = DBUtils.getConnection();
        PreparedStatement pst = null;
        String sql = "SELECT * "
                + "FROM students s "
                + "LEFT JOIN studentsincourses sc ON sc.studentid = s.studentid "
                + "WHERE sc.courseid=?;";

        try {

            pst = con.prepareStatement(sql);
            pst.setInt(1, courseid);
            ResultSet rs = pst.executeQuery();

            while (rs.next()) {

                Student student = new Student();
                student.setStudentid(rs.getInt(1));
                student.setFirstname(rs.getString(2));
                student.setLastname(rs.getString(3));
                student.setDateofbirth(rs.getString(4));
                student.setTuitionfees(rs.getFloat(5));
                student.setStream(rs.getString(6));
                student.setType(rs.getString(7));

                list.add(student);

            }
        } catch (SQLException ex) {
            Logger.getLogger(StudentInCourseDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {

            try {
                pst.close();
            } catch (SQLException ex) {
                Logger.getLogger(StudentInCourseDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
            try {
                con.close();
            } catch (SQLException ex) {
                Logger.getLogger(StudentInCourseDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        return list;
    }

    public static ArrayList<Student> getStudentsNotEnrolledToCourse(int courseid) {

        ArrayList<Student> list = new ArrayList<Student>();
        Connection con = DBUtils.getConnection();
        PreparedStatement pst = null;
        String sql = "SELECT * "
                + "FROM students s "
                + "LEFT JOIN studentsincourses sc ON sc.studentid = s.studentid "
                + "WHERE sc.courseid=? and sc.enrolled=0;";

        try {

            pst = con.prepareStatement(sql);
            pst.setInt(1, courseid);
            ResultSet rs = pst.executeQuery();

            while (rs.next()) {

                Student student = new Student();
                student.setStudentid(rs.getInt(1));
                student.setFirstname(rs.getString(2));
                student.setLastname(rs.getString(3));
                student.setDateofbirth(rs.getString(4));
                student.setTuitionfees(rs.getFloat(5));
                student.setStream(rs.getString(6));
                student.setType(rs.getString(7));

                list.add(student);

            }
        } catch (SQLException ex) {
            Logger.getLogger(StudentInCourseDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {

            try {
                pst.close();
            } catch (SQLException ex) {
                Logger.getLogger(StudentInCourseDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
            try {
                con.close();
            } catch (SQLException ex) {
                Logger.getLogger(StudentInCourseDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        return list;
    }
    
    public static ArrayList<Student> getStudentsEnrolledToCourse(int courseid) {

        ArrayList<Student> list = new ArrayList<Student>();
        Connection con = DBUtils.getConnection();
        PreparedStatement pst = null;
        String sql = "SELECT * "
                + "FROM students s "
                + "LEFT JOIN studentsincourses sc ON sc.studentid = s.studentid "
                + "WHERE sc.courseid=? and sc.enrolled=1;";

        try {

            pst = con.prepareStatement(sql);
            pst.setInt(1, courseid);
            ResultSet rs = pst.executeQuery();

            while (rs.next()) {

                Student student = new Student();
                student.setStudentid(rs.getInt(1));
                student.setFirstname(rs.getString(2));
                student.setLastname(rs.getString(3));
                student.setDateofbirth(rs.getString(4));
                student.setTuitionfees(rs.getFloat(5));
                student.setStream(rs.getString(6));
                student.setType(rs.getString(7));

                list.add(student);

            }
        } catch (SQLException ex) {
            Logger.getLogger(StudentInCourseDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {

            try {
                pst.close();
            } catch (SQLException ex) {
                Logger.getLogger(StudentInCourseDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
            try {
                con.close();
            } catch (SQLException ex) {
                Logger.getLogger(StudentInCourseDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        return list;
    }

    public static ArrayList<Course> getCoursesAppointedToStudent(int studentid) {

        ArrayList<Course> list = new ArrayList<Course>();
        Connection con = DBUtils.getConnection();
        PreparedStatement pst = null;
        String sql = "SELECT * "
                + "FROM courses c "
                + "LEFT JOIN studentsincourses sc ON sc.courseid = c.courseid "
                + "WHERE sc.studentid=?;";

        try {

            pst = con.prepareStatement(sql);
            pst.setInt(1, studentid);
            ResultSet rs = pst.executeQuery();

            while (rs.next()) {

                Course course = new Course();
                course.setCourseid(rs.getInt(1));
                course.setTitle(rs.getString(2));
                course.setStream(rs.getString(3));
                course.setType(rs.getString(4));
                course.setStartdate(rs.getString(5));
                course.setEnddate(rs.getString(6));

                list.add(course);

            }
        } catch (SQLException ex) {
            Logger.getLogger(StudentInCourseDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {

            try {
                pst.close();
            } catch (SQLException ex) {
                Logger.getLogger(StudentInCourseDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
            try {
                con.close();
            } catch (SQLException ex) {
                Logger.getLogger(StudentInCourseDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        return list;
    }

    public static ArrayList<Course> getCoursesStudentIsNotEnrolledTo(int studentid) {

        ArrayList<Course> list = new ArrayList<Course>();
        Connection con = DBUtils.getConnection();
        PreparedStatement pst = null;
        String sql = "SELECT * "
                + "FROM courses c "
                + "LEFT JOIN studentsincourses sc ON sc.courseid = c.courseid "
                + "WHERE sc.studentid=? and sc.enrolled=0;";

        try {

            pst = con.prepareStatement(sql);
            pst.setInt(1, studentid);
            ResultSet rs = pst.executeQuery();

            while (rs.next()) {

                Course course = new Course();
                course.setCourseid(rs.getInt(1));
                course.setTitle(rs.getString(2));
                course.setStream(rs.getString(3));
                course.setType(rs.getString(4));
                course.setStartdate(rs.getString(5));
                course.setEnddate(rs.getString(6));

                list.add(course);

            }
        } catch (SQLException ex) {
            Logger.getLogger(StudentInCourseDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {

            try {
                pst.close();
            } catch (SQLException ex) {
                Logger.getLogger(StudentInCourseDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
            try {
                con.close();
            } catch (SQLException ex) {
                Logger.getLogger(StudentInCourseDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        return list;
    }

    public static void insertStudentInCourse(StudentInCourse studentincourse) {

        Connection con = DBUtils.getConnection();
        PreparedStatement pst = null;
        String sql = "insert into studentsincourses(studentid, courseid, enrolled) values (?, ?, ?)";
        boolean result = false;

        try {
            pst = con.prepareStatement(sql);
            pst.setInt(1, studentincourse.getStudent().getStudentid());
            pst.setInt(2, studentincourse.getCourse().getCourseid());

            if (studentincourse.isEnrolled()) {
                pst.setInt(3, 1);
            } else {
                pst.setInt(3, 0);
            }

            pst.executeUpdate();

            result = true;
        } catch (SQLException ex) {
            Logger.getLogger(StudentInCourseDAO.class.getName()).log(Level.SEVERE, null, ex);
            result = false;
        } finally {
            try {
                pst.close();
            } catch (SQLException ex) {
                Logger.getLogger(StudentInCourseDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
            try {
                con.close();
            } catch (SQLException ex) {
                Logger.getLogger(StudentInCourseDAO.class.getName()).log(Level.SEVERE, null, ex);
            }

        }

        if (result) {
            System.out.println("Appointed Student to Course");
        }
//        return result;

    }

    public static void updateStudentInCourse(StudentInCourse studentincourse) {

        Connection con = DBUtils.getConnection();
        PreparedStatement pst = null;
        String sql = "UPDATE studentsincourses SET enrolled = ? /*1*/ WHERE studentid = ? /*2*/ and courseid = ? /*3*/ ";
        boolean result = false;

        try {
            pst = con.prepareStatement(sql);
            if (studentincourse.isEnrolled()) {
                pst.setInt(1, 1);
            } else {
                pst.setInt(1, 0);
            }
            pst.setInt(2, studentincourse.getStudent().getStudentid());
            pst.setInt(3, studentincourse.getCourse().getCourseid());

            pst.executeUpdate();

            result = true;
        } catch (SQLException ex) {
            Logger.getLogger(StudentInCourseDAO.class.getName()).log(Level.SEVERE, null, ex);
            result = false;
        } finally {
            try {
                pst.close();
            } catch (SQLException ex) {
                Logger.getLogger(StudentInCourseDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
            try {
                con.close();
            } catch (SQLException ex) {
                Logger.getLogger(StudentInCourseDAO.class.getName()).log(Level.SEVERE, null, ex);
            }

        }

        if (result) {
            System.out.println("Enrolled Student to Course");
        }
//        return result;

    }

    public static void deleteStudentFromCourse(int studentid, int courseid) {

        Connection con = DBUtils.getConnection();
        PreparedStatement pst = null;
        String sql = "DELETE from studentsincourses  WHERE studentid = ? /*1*/ and courseid = ? /*2*/ ";
        boolean result = false;

        try {
            pst = con.prepareStatement(sql);

            pst.setInt(1, studentid);
            pst.setInt(2, courseid);

            pst.executeUpdate();

            result = true;
        } catch (SQLException ex) {
            Logger.getLogger(StudentInCourseDAO.class.getName()).log(Level.SEVERE, null, ex);
            result = false;
        } finally {
            try {
                pst.close();
            } catch (SQLException ex) {
                Logger.getLogger(StudentInCourseDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
            try {
                con.close();
            } catch (SQLException ex) {
                Logger.getLogger(StudentInCourseDAO.class.getName()).log(Level.SEVERE, null, ex);
            }

        }

        if (result) {
            System.out.println("Deleted Student from Course");
        }
//        return result;

    }
}
