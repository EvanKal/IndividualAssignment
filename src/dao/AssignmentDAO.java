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
import model.Assignment;
import utils.DBUtils;

/**
 *
 * @author Los_e
 */
public class AssignmentDAO {

    public static ArrayList<Assignment> getAllAssignments() {

        ArrayList<Assignment> list = new ArrayList<Assignment>();
        Connection con = DBUtils.getConnection();
        PreparedStatement pst = null;
        String sql = "select * from assignments";

        try {
            pst = con.prepareStatement(sql);
            ResultSet rs = pst.executeQuery(sql);

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

    public Assignment getAssignmentById(int assignmentid) {

        Assignment assignment = new Assignment();
        Connection con = DBUtils.getConnection();
        PreparedStatement pst = null;
        String sql = "select * from assignments where assignmentid=?;";

        try {

            pst = con.prepareStatement(sql);
            pst.setInt(1, assignmentid);
            ResultSet rs = pst.executeQuery();

            while (rs.next()) {

                assignment.setAssignmentid(rs.getInt(1));
                assignment.setTitle(rs.getString(2));
                assignment.setDescription(rs.getString(3));

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

        return assignment;
    }

    public static void insertAssignment(Assignment assignment) {

        Connection con = DBUtils.getConnection();
        PreparedStatement pst = null;
        String sql = "insert into assignments(title, description) values (?, ?)";
        boolean result = false;

        try {
            pst = con.prepareStatement(sql);
            pst.setString(1, assignment.getTitle());
            pst.setString(2, assignment.getDescription());
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
            System.out.println("Inserted new Assignment");
        }
//        return result;

    }

    public static void updateAssignment(Assignment assignment) {

        Connection con = DBUtils.getConnection();
        PreparedStatement pst = null;
        String sql = "UPDATE assignments SET title = ? /*1*/, description= ? /*2*/ WHERE assignmentid = ? /*3*/";
        boolean result = false;

        try {
            pst = con.prepareStatement(sql);
            pst.setString(1, assignment.getTitle());
            pst.setString(2, assignment.getDescription());
            pst.setInt(3, assignment.getAssignmentid());
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
            System.out.println("Updated Assignment");
        }
//        return result;

    }

    public static void deleteAssignment(int assignmentid) {

        Connection con = DBUtils.getConnection();
        PreparedStatement pst = null;
        String sql = "DELETE FROM assignments WHERE assignmentid = ?";
        boolean result = false;

        try {
            pst = con.prepareStatement(sql);
            pst.setInt(1, assignmentid);
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
            System.out.println("Deleted Assignment");
        }
//        return result;

    }
}
