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
import model.Student;
import model.Student;
import utils.DBUtils;

/**
 *
 * @author Los_e
 */
public class StudentDAO {

    public static ArrayList<Student> getAllStudents() {

        ArrayList<Student> list = new ArrayList<Student>();
        Connection con = DBUtils.getConnection();
        PreparedStatement pst = null;
        String sql = "select * from students";

        try {
            pst = con.prepareStatement(sql);
            ResultSet rs = pst.executeQuery(sql);

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
            Logger.getLogger(StudentDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {

            try {
                pst.close();
            } catch (SQLException ex) {
                Logger.getLogger(StudentDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
            try {
                con.close();
            } catch (SQLException ex) {
                Logger.getLogger(StudentDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        return list;
    }

    public static Student getStudentById(int studentid) {

        Student student = new Student();
        Connection con = DBUtils.getConnection();
        PreparedStatement pst = null;
        String sql = "select * from students where studentid=?;";

        try {

            pst = con.prepareStatement(sql);
            pst.setInt(1, studentid);
            ResultSet rs = pst.executeQuery();

            while (rs.next()) {

                student.setStudentid(rs.getInt(1));
                student.setFirstname(rs.getString(2));
                student.setLastname(rs.getString(3));
                student.setDateofbirth(rs.getString(4));
                student.setTuitionfees(rs.getFloat(5));
                student.setStream(rs.getString(6));
                student.setType(rs.getString(7));

            }
        } catch (SQLException ex) {
            Logger.getLogger(StudentDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {

            try {
                pst.close();
            } catch (SQLException ex) {
                Logger.getLogger(StudentDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
            try {
                con.close();
            } catch (SQLException ex) {
                Logger.getLogger(StudentDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        return student;
    }

    public static void insertStudent(Student student) {

        Connection con = DBUtils.getConnection();
        PreparedStatement pst = null;
        String sql = "insert into students(studentid, firstname, lastname, dateofbirth, tuitionfees, stream, type) values (?, ?, ?, ?, ?, ?, ?)";
        boolean result = false;

        try {
            pst = con.prepareStatement(sql);
            pst.setInt(1, student.getStudentid());
            pst.setString(2, student.getFirstname());
            pst.setString(3, student.getLastname());
            pst.setString(4, student.getDateofbirth().toString());
            pst.setFloat(5, student.getTuitionfees());
            pst.setString(6, student.getStream());
            pst.setString(7, student.getType());
            pst.executeUpdate();

            result = true;
        } catch (SQLException ex) {
            Logger.getLogger(StudentDAO.class.getName()).log(Level.SEVERE, null, ex);
            result = false;
        } finally {
            try {
                pst.close();
            } catch (SQLException ex) {
                Logger.getLogger(StudentDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
            try {
                con.close();
            } catch (SQLException ex) {
                Logger.getLogger(StudentDAO.class.getName()).log(Level.SEVERE, null, ex);
            }

        }

        if (result) {
            System.out.println("Inserted new Student");
        }
//        return result;

    }

    public static void updateStudent(Student student) {

        Connection con = DBUtils.getConnection();
        PreparedStatement pst = null;
        String sql = "UPDATE students SET firstname = ? /*1*/, lastname= ? /*2*/, dateofbirth = ? /*3*/ , tuitionfees = ? /*4*/, stream = ? /*5*/, type = ? /*6*/  WHERE studentid = ? /*7*/";
        boolean result = false;

        try {
            pst = con.prepareStatement(sql);
            pst.setString(1, student.getFirstname());
            pst.setString(2, student.getLastname());
            pst.setString(3, student.getDateofbirth().toString());
            pst.setFloat(4, student.getTuitionfees());
            pst.setString(5, student.getStream());
            pst.setString(6, student.getType());
            pst.setInt(7, student.getStudentid());
            pst.executeUpdate();

            result = true;
        } catch (SQLException ex) {
            Logger.getLogger(StudentDAO.class.getName()).log(Level.SEVERE, null, ex);
            result = false;
        } finally {
            try {
                pst.close();
            } catch (SQLException ex) {
                Logger.getLogger(StudentDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
            try {
                con.close();
            } catch (SQLException ex) {
                Logger.getLogger(StudentDAO.class.getName()).log(Level.SEVERE, null, ex);
            }

        }

        if (result) {
            System.out.println("Updated Student");
        }
//        return result;

    }

    public static void deleteStudent(int studentid) {

        Connection con = DBUtils.getConnection();
        PreparedStatement pst = null;
        String sql = "DELETE FROM students WHERE studentid = ?";
        boolean result = false;

        try {
            pst = con.prepareStatement(sql);
            pst.setInt(1, studentid);
            pst.executeUpdate();

            result = true;
        } catch (SQLException ex) {
            Logger.getLogger(StudentDAO.class.getName()).log(Level.SEVERE, null, ex);
            result = false;
        } finally {
            try {
                pst.close();
            } catch (SQLException ex) {
                Logger.getLogger(StudentDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
            try {
                con.close();
            } catch (SQLException ex) {
                Logger.getLogger(StudentDAO.class.getName()).log(Level.SEVERE, null, ex);
            }

        }

        if (result) {
            System.out.println("Deleted Student");
        }
//        return result;

    }
}
