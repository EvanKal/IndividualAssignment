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
import model.Trainer;
import utils.DBUtils;

/**
 *
 * @author Los_e
 */
public class TrainerDAO {
    
    public static ArrayList<Trainer> getAllTrainers() {

        ArrayList<Trainer> list = new ArrayList<Trainer>();
        Connection con = DBUtils.getConnection();
        PreparedStatement pst = null;
        String sql = "select * from trainers";

        try {
            pst = con.prepareStatement(sql);
            ResultSet rs = pst.executeQuery(sql);

            while (rs.next()) {

                Trainer trainer = new Trainer();
                trainer.setTrainerid(rs.getInt(1));
                trainer.setFirstname(rs.getString(2));
                trainer.setLastname(rs.getString(3));
                trainer.setSubject(rs.getString(4));

                list.add(trainer);

            }
        } catch (SQLException ex) {
            Logger.getLogger(TrainerDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {

            try {
                pst.close();
            } catch (SQLException ex) {
                Logger.getLogger(TrainerDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
            try {
                con.close();
            } catch (SQLException ex) {
                Logger.getLogger(TrainerDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        return list;
    }

    public Trainer getTrainerById(int trainerid) {

        Trainer trainer = new Trainer();
        Connection con = DBUtils.getConnection();
        PreparedStatement pst = null;
        String sql = "select * from trainers where trainerid=?;";

        try {

            pst = con.prepareStatement(sql);
            pst.setInt(1, trainerid);
            ResultSet rs = pst.executeQuery();

            while (rs.next()) {

                trainer.setTrainerid(rs.getInt(1));
                trainer.setFirstname(rs.getString(2));
                trainer.setLastname(rs.getString(3));
                trainer.setSubject(rs.getString(4));

            }
        } catch (SQLException ex) {
            Logger.getLogger(TrainerDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {

            try {
                pst.close();
            } catch (SQLException ex) {
                Logger.getLogger(TrainerDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
            try {
                con.close();
            } catch (SQLException ex) {
                Logger.getLogger(TrainerDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        return trainer;
    }
    
    public static void insertTrainer(Trainer trainer) {

        Connection con = DBUtils.getConnection();
        PreparedStatement pst = null;
        String sql = "insert into trainers(trainerid, firstname, lastname, subject) values (?, ?, ?, ?)";
        boolean result = false;

        try {
            pst = con.prepareStatement(sql);
            pst.setInt(1, trainer.getTrainerid());
            pst.setString(2, trainer.getFirstname());
            pst.setString(3, trainer.getLastname());
            pst.setString(4, trainer.getSubject());
            pst.executeUpdate();

            result = true;
        } catch (SQLException ex) {
            Logger.getLogger(TrainerDAO.class.getName()).log(Level.SEVERE, null, ex);
            result = false;
        } finally {
            try {
                pst.close();
            } catch (SQLException ex) {
                Logger.getLogger(TrainerDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
            try {
                con.close();
            } catch (SQLException ex) {
                Logger.getLogger(TrainerDAO.class.getName()).log(Level.SEVERE, null, ex);
            }

        }

        if (result) {
            System.out.println("Inserted new Trainer");
        }
//        return result;

    }


    public static void updateTrainer(Trainer trainer) {

        Connection con = DBUtils.getConnection();
        PreparedStatement pst = null;
        String sql = "UPDATE trainers SET firstname = ? /*1*/, lastname= ? /*2*/, subject = ? /*3*/ WHERE trainerid = ? /*4*/";
        boolean result = false;

        try {
            pst = con.prepareStatement(sql);
            pst.setString(1, trainer.getFirstname());
            pst.setString(2, trainer.getLastname());
            pst.setString(3, trainer.getSubject());
            pst.setInt(4, trainer.getTrainerid());
            pst.executeUpdate();

            result = true;
        } catch (SQLException ex) {
            Logger.getLogger(TrainerDAO.class.getName()).log(Level.SEVERE, null, ex);
            result = false;
        } finally {
            try {
                pst.close();
            } catch (SQLException ex) {
                Logger.getLogger(TrainerDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
            try {
                con.close();
            } catch (SQLException ex) {
                Logger.getLogger(TrainerDAO.class.getName()).log(Level.SEVERE, null, ex);
            }

        }

        if (result) {
            System.out.println("Updated Trainer");
        }
//        return result;

    }
    
    public static void deleteTrainer(int trainerid) {

        Connection con = DBUtils.getConnection();
        PreparedStatement pst = null;
        String sql = "DELETE FROM trainers WHERE trainerid = ?";
        boolean result = false;

        try {
            pst = con. prepareStatement(sql);
            pst.setInt(1, trainerid);
            pst.executeUpdate();

            result = true;
        } catch (SQLException ex) {
            Logger.getLogger(TrainerDAO.class.getName()).log(Level.SEVERE, null, ex);
            result = false;
        } finally {
            try {
                pst.close();
            } catch (SQLException ex) {
                Logger.getLogger(TrainerDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
            try {
                con.close();
            } catch (SQLException ex) {
                Logger.getLogger(TrainerDAO.class.getName()).log(Level.SEVERE, null, ex);
            }

        }

        if (result) {
            System.out.println("Deleted Trainer");
        }
//        return result;

    }
}
