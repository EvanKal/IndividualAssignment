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
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Course;
import utils.DBUtils;

/**
 *
 * @author Los_e
 */
public class CourseDAO {
    public static ArrayList<Course> getAllCourses() {

        ArrayList<Course> list = new ArrayList<Course>();
        Connection con = DBUtils.getConnection();
        PreparedStatement pst = null;
        String sql = "select * from courses";

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

                list.add(course);

            }
        } catch (SQLException ex) {
            Logger.getLogger(CourseDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {

            try {
                pst.close();
            } catch (SQLException ex) {
                Logger.getLogger(CourseDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
            try {
                con.close();
            } catch (SQLException ex) {
                Logger.getLogger(CourseDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        return list;
    }

    public Course getCourseById(int courseid) {

        Course course = new Course();
        Connection con = DBUtils.getConnection();
        PreparedStatement pst = null;
        String sql = "select * from courses where courseid=?;";

        try {

            pst = con.prepareStatement(sql);
            pst.setInt(1, courseid);
            ResultSet rs = pst.executeQuery();

            while (rs.next()) {

                course.setCourseid(rs.getInt(1));
                course.setTitle(rs.getString(2));
                course.setStream(rs.getString(3));
                course.setType(rs.getString(4));
                course.setStartdate(rs.getString(5));
                course.setStartdate(rs.getString(6));
                
            }
        } catch (SQLException ex) {
            Logger.getLogger(CourseDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {

            try {
                pst.close();
            } catch (SQLException ex) {
                Logger.getLogger(CourseDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
            try {
                con.close();
            } catch (SQLException ex) {
                Logger.getLogger(CourseDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        return course;
    }
    
    public static void insertCourse(Course course) {

        Connection con = DBUtils.getConnection();
        PreparedStatement pst = null;
        String sql = "insert into courses(title, stream, type, startdate, enddate) values (?, ?, ?, ?, ?)";
        boolean result = false;

        try {
            pst = con.prepareStatement(sql);
            pst.setString(1, course.getTitle());
            pst.setString(2, course.getStream());
            pst.setString(3, course.getType());
            pst.setString(4, course.getStartdate().toString());
            pst.setString(5, course.getEnddate().toString());
            pst.executeUpdate();

            result = true;
        } catch (SQLException ex) {
            Logger.getLogger(CourseDAO.class.getName()).log(Level.SEVERE, null, ex);
            result = false;
        } finally {
            try {
                pst.close();
            } catch (SQLException ex) {
                Logger.getLogger(CourseDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
            try {
                con.close();
            } catch (SQLException ex) {
                Logger.getLogger(CourseDAO.class.getName()).log(Level.SEVERE, null, ex);
            }

        }

        if (result) {
            System.out.println("Inserted new Course");
        }
//        return result;

    }


    public static void updateCourse(Course course) {

        Connection con = DBUtils.getConnection();
        PreparedStatement pst = null;
        String sql = "UPDATE courses SET title = ? /*1*/, stream= ? /*2*/, type = ? /*3*/ , startdate = ? /*4*/, enddate = ? /*5*/  WHERE courseid = ? /*6*/";
        boolean result = false;

        try {
            pst = con.prepareStatement(sql);
            pst.setString(1, course.getTitle());
            pst.setString(2, course.getStream());
            pst.setString(3, course.getType());
            pst.setString(4, course.getStartdate().toString());
            pst.setString(5, course.getEnddate().toString());
            pst.setInt(6, course.getCourseid());
            pst.executeUpdate();

            result = true;
        } catch (SQLException ex) {
            Logger.getLogger(CourseDAO.class.getName()).log(Level.SEVERE, null, ex);
            result = false;
        } finally {
            try {
                pst.close();
            } catch (SQLException ex) {
                Logger.getLogger(CourseDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
            try {
                con.close();
            } catch (SQLException ex) {
                Logger.getLogger(CourseDAO.class.getName()).log(Level.SEVERE, null, ex);
            }

        }

        if (result) {
            System.out.println("Updated Course");
        }
//        return result;

    }
    
    public static void deleteCourse(int courseid) {

        Connection con = DBUtils.getConnection();
        PreparedStatement pst = null;
        String sql = "DELETE FROM courses WHERE courseid = ?";
        boolean result = false;

        try {
            pst = con. prepareStatement(sql);
            pst.setInt(1, courseid);
            pst.executeUpdate();

            result = true;
        } catch (SQLException ex) {
            Logger.getLogger(CourseDAO.class.getName()).log(Level.SEVERE, null, ex);
            result = false;
        } finally {
            try {
                pst.close();
            } catch (SQLException ex) {
                Logger.getLogger(CourseDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
            try {
                con.close();
            } catch (SQLException ex) {
                Logger.getLogger(CourseDAO.class.getName()).log(Level.SEVERE, null, ex);
            }

        }

        if (result) {
            System.out.println("Deleted Course");
        }
//        return result;

    }
}
