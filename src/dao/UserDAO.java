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
import model.User;
import utils.DBUtils;

/**
 *
 * @author Los_e
 */
public class UserDAO {

    public static ArrayList<User> getAllUsers() {

        ArrayList<User> list = new ArrayList<User>();
        Connection con = DBUtils.getConnection();
        PreparedStatement pst = null;
        String sql = "select * from users";

        try {
            pst = con.prepareStatement(sql);
            ResultSet rs = pst.executeQuery(sql);

            while (rs.next()) {

                User user = new User();
                user.setUserid(rs.getInt(1));
                user.setUsername(rs.getString(2));
                user.setPassword(rs.getString(3));
                user.setRole(rs.getString(4));

                list.add(user);

            }
        } catch (SQLException ex) {
            Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {

            try {
                pst.close();
            } catch (SQLException ex) {
                Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
            try {
                con.close();
            } catch (SQLException ex) {
                Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        return list;
    }

    public static void updateUser(User user) {

        Connection con = DBUtils.getConnection();
        PreparedStatement pst = null;
        String sql = "UPDATE users SET username = ? /*1*/, password= ? /*2*/, role = ? /*3*/  WHERE userid = ? /*4*/";
        boolean result = false;

        try {
            pst = con.prepareStatement(sql);
            pst.setString(1, user.getUsername());
            pst.setString(2, user.getPassword());
            pst.setString(3, user.getRole());
            pst.setInt(4, user.getUserid());
            pst.executeUpdate();

            result = true;
        } catch (SQLException ex) {
            Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);
            result = false;
        } finally {
            try {
                pst.close();
            } catch (SQLException ex) {
                Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
            try {
                con.close();
            } catch (SQLException ex) {
                Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);
            }

        }

        if (result) {
            System.out.println("Updated User");
        }
//        return result;

    }

    public static User getUserTryingToLogIn(String username) {

        User user = new User();
        Connection con = DBUtils.getConnection();
        PreparedStatement pst = null;
        String sql = "select * from users where username = ?";

        try {
            pst = con.prepareStatement(sql);
            pst.setString(1, username);
            ResultSet rs = pst.executeQuery();

            while (rs.next()) {

                user.setUserid(rs.getInt(1));
                user.setUsername(rs.getString(2));
                user.setPassword(rs.getString(3));
                user.setRole(rs.getString(4));
                
            }
        } catch (SQLException ex) {
            Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {

            try {
                pst.close();
            } catch (SQLException ex) {
                Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
            try {
                con.close();
            } catch (SQLException ex) {
                Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        return user;
    }
}
