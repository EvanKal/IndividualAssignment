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
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Assignment;
import model.AssignmentInCourse;
import model.Course;
import model.Student;
import model.StudentInCourse;
import model.Trainer;
import model.TrainerInCourse;
import utils.DBUtils;

/**
 *
 * @author Los_e
 */
public class AssignmentInCourseDAO {

    public static ArrayList<AssignmentInCourse> getAllAssignmentsPerCourse() {

        ArrayList<AssignmentInCourse> list = new ArrayList<>();
        Connection con = DBUtils.getConnection();
        PreparedStatement pst = null;
        String sql = "SELECT  * FROM courses c "
                + "inner join "
                + "assignmentsincourses sc on sc.courseid = c.courseid "
                + "inner join "
                + "assignments s on sc.assignmentid = s.assignmentid "
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
                course.setStartdate(rs.getString(6));

                Assignment assignment = new Assignment();
                assignment.setAssignmentid(rs.getInt(10));
                assignment.setTitle(rs.getString(11));
                assignment.setDescription(rs.getString(12));

                AssignmentInCourse assignmentincourse = new AssignmentInCourse();
                assignmentincourse.setAssignment(assignment);
                assignmentincourse.setCourse(course);
                assignmentincourse.setSubmissiondatetime(rs.getString(9));

                list.add(assignmentincourse);

            }
        } catch (SQLException ex) {
            Logger.getLogger(AssignmentInCourseDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {

            try {
                pst.close();
            } catch (SQLException ex) {
                Logger.getLogger(AssignmentInCourseDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
            try {
                con.close();
            } catch (SQLException ex) {
                Logger.getLogger(AssignmentInCourseDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        return list;

    }

    public static ArrayList<Assignment> getAssignmentsNotAppointedToCourse(int courseid) {

        ArrayList<Assignment> list = new ArrayList<Assignment>();
        Connection con = DBUtils.getConnection();
        PreparedStatement pst = null;
        String sql = "select table1.* from "
                + ""
                + "(select s2.* from assignments s2 ) as table1 "
                + ""
                + "left join "
                + ""
                + "(select s1.* from assignments s1 "
                + "left join assignmentsincourses sc1 on sc1.assignmentid = s1.assignmentid "
                + "where sc1.courseid=?) as table2 "
                + ""
                + "on table1.assignmentid = table2.assignmentid "
                + "where table2.assignmentid is null "
                + ";";

        try {

            pst = con.prepareStatement(sql);
            pst.setInt(1, courseid);
            ResultSet rs = pst.executeQuery();

            while (rs.next()) {

                Assignment assignment = new Assignment();
                assignment.setAssignmentid(rs.getInt(1));
                assignment.setTitle(rs.getString(2));
                assignment.setDescription(rs.getString(3));

                list.add(assignment);

            }
        } catch (SQLException ex) {
            Logger.getLogger(AssignmentDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {

            try {
                pst.close();
            } catch (SQLException ex) {
                Logger.getLogger(AssignmentDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
            try {
                con.close();
            } catch (SQLException ex) {
                Logger.getLogger(AssignmentDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        return list;
    }

    public static ArrayList<Assignment> getAssignmentsAppointedToCourse(int courseid) {

        ArrayList<Assignment> list = new ArrayList<Assignment>();
        Connection con = DBUtils.getConnection();
        PreparedStatement pst = null;
        String sql = "SELECT * "
                + "FROM assignments s "
                + "LEFT JOIN assignmentsincourses sc ON sc.assignmentid = s.assignmentid "
                + "WHERE sc.courseid=?;";

        try {

            pst = con.prepareStatement(sql);
            pst.setInt(1, courseid);
            ResultSet rs = pst.executeQuery();

            while (rs.next()) {

                Assignment assignment = new Assignment();
                assignment.setAssignmentid(rs.getInt(1));
                assignment.setTitle(rs.getString(2));
                assignment.setDescription(rs.getString(3));

                list.add(assignment);

            }
        } catch (SQLException ex) {
            Logger.getLogger(AssignmentDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {

            try {
                pst.close();
            } catch (SQLException ex) {
                Logger.getLogger(AssignmentDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
            try {
                con.close();
            } catch (SQLException ex) {
                Logger.getLogger(AssignmentDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        return list;
    }

    public static void insertAssignmentInCourse(AssignmentInCourse assignmentincourse) {

        Connection con = DBUtils.getConnection();
        PreparedStatement pst = null;
        String sql = "insert into assignmentsincourses(courseid, assignmentid,  submissiondatetime) values (?, ?, ?)";
        boolean result = false;

        try {
            pst = con.prepareStatement(sql);
            pst.setInt(1, assignmentincourse.getCourse().getCourseid());
            pst.setInt(2, assignmentincourse.getAssignment().getAssignmentid());
            pst.setString(3, assignmentincourse.getSubmissiondatetime().toString());

            pst.executeUpdate();

            result = true;
        } catch (SQLException ex) {
            Logger.getLogger(AssignmentDAO.class.getName()).log(Level.SEVERE, null, ex);
            result = false;
        } finally {
            try {
                pst.close();
            } catch (SQLException ex) {
                Logger.getLogger(AssignmentDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
            try {
                con.close();
            } catch (SQLException ex) {
                Logger.getLogger(AssignmentDAO.class.getName()).log(Level.SEVERE, null, ex);
            }

        }

        if (result) {
            System.out.println("Appointed Assignment to Course");
        }
//        return result;

    }

    public static void updateAssignmentInCourse(AssignmentInCourse assignmentincourse) {

        Connection con = DBUtils.getConnection();
        PreparedStatement pst = null;
        String sql = "UPDATE assignmentsincourses SET submissiondatetime = ? /*1*/ WHERE assignmentid = ? /*2*/ and courseid = ? /*3*/ ";
        boolean result = false;

        try {

            pst = con.prepareStatement(sql);
            pst.setString(1, assignmentincourse.getSubmissiondatetime().toString());
            pst.setInt(2, assignmentincourse.getAssignment().getAssignmentid());
            pst.setInt(3, assignmentincourse.getCourse().getCourseid());

            pst.executeUpdate();

            result = true;
        } catch (SQLException ex) {
            Logger.getLogger(AssignmentDAO.class.getName()).log(Level.SEVERE, null, ex);
            result = false;
        } finally {
            try {
                pst.close();
            } catch (SQLException ex) {
                Logger.getLogger(AssignmentDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
            try {
                con.close();
            } catch (SQLException ex) {
                Logger.getLogger(AssignmentDAO.class.getName()).log(Level.SEVERE, null, ex);
            }

        }

        if (result) {
            System.out.println("Enrolled Assignment to Course");
        }
//        return result;

    }

    public static void deleteAssignmentFromCourse(int assignmentid, int courseid) {

        Connection con = DBUtils.getConnection();
        PreparedStatement pst = null;
        String sql = "DELETE from assignmentsincourses  WHERE assignmentid = ? /*1*/ and courseid = ? /*2*/ ";
        boolean result = false;

        try {
            pst = con.prepareStatement(sql);

            pst.setInt(1, assignmentid);
            pst.setInt(2, courseid);

            pst.executeUpdate();

            result = true;
        } catch (SQLException ex) {
            Logger.getLogger(AssignmentDAO.class.getName()).log(Level.SEVERE, null, ex);
            result = false;
        } finally {
            try {
                pst.close();
            } catch (SQLException ex) {
                Logger.getLogger(AssignmentDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
            try {
                con.close();
            } catch (SQLException ex) {
                Logger.getLogger(AssignmentDAO.class.getName()).log(Level.SEVERE, null, ex);
            }

        }

        if (result) {
            System.out.println("Deleted Assignment from Course");
        }
//        return result;

    }
}
