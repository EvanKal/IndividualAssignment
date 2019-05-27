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
import model.Student;
import model.StudentInCourse;
import model.Trainer;
import model.TrainerInCourse;
import utils.DBUtils;

/**
 *
 * @author Los_e
 */
public class TrainerInCourseDAO {
    
    public static ArrayList<TrainerInCourse> getAllTrainersPerCourse() {

        ArrayList<TrainerInCourse> list = new ArrayList<>();
        Connection con = DBUtils.getConnection();
        PreparedStatement pst = null;
        String sql = "SELECT  * FROM courses c "
                + "inner join "
                + "trainersincourses sc on sc.courseid = c.courseid "
                + "inner join "
                + "trainers s on sc.trainerid = s.trainerid "
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

                Trainer trainer = new Trainer();
                trainer.setTrainerid(rs.getInt(9));
                trainer.setFirstname(rs.getString(10));
                trainer.setLastname(rs.getString(11));
                trainer.setSubject(rs.getString(12));


                TrainerInCourse trainerincourse = new TrainerInCourse();
                trainerincourse.setTrainer(trainer);
                trainerincourse.setCourse(course);


                list.add(trainerincourse);

            }
        } catch (SQLException ex) {
            Logger.getLogger(TrainerInCourseDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {

            try {
                pst.close();
            } catch (SQLException ex) {
                Logger.getLogger(TrainerInCourseDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
            try {
                con.close();
            } catch (SQLException ex) {
                Logger.getLogger(TrainerInCourseDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        return list;

    }
    
     public static ArrayList<Trainer> getTrainersNotAppointedToCourse(int courseid) {

        ArrayList<Trainer> list = new ArrayList<Trainer>();
        Connection con = DBUtils.getConnection();
        PreparedStatement pst = null;
        String sql = "select table1.* from "
                + ""
                + "(select s2.* from trainers s2 ) as table1 "
                + ""
                + "left join "
                + ""
                + "(select s1.* from trainers s1 "
                + "left join trainersincourses sc1 on sc1.trainerid = s1.trainerid "
                + "where sc1.courseid=?) as table2 "
                + ""
                + "on table1.trainerid = table2.trainerid "
                + "where table2.trainerid is null "
                + ";";

        try {

            pst = con.prepareStatement(sql);
            pst.setInt(1, courseid);
            ResultSet rs = pst.executeQuery();

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
     
     public static void insertTrainerInCourse(TrainerInCourse trainerincourse) {

        Connection con = DBUtils.getConnection();
        PreparedStatement pst = null;
        String sql = "insert into trainersincourses(trainerid, courseid) values (?, ?)";
        boolean result = false;

        try {
            pst = con.prepareStatement(sql);
            pst.setInt(1, trainerincourse.getTrainer().getTrainerid());
            pst.setInt(2, trainerincourse.getCourse().getCourseid());

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
            System.out.println("Appointed Trainer to Course");
        }
//        return result;

    }
     
     public static void deleteTrainerFromCourse(int trainerid, int courseid) {

        Connection con = DBUtils.getConnection();
        PreparedStatement pst = null;
        String sql = "DELETE from trainersincourses  WHERE trainerid = ? /*1*/ and courseid = ? /*2*/ ";
        boolean result = false;

        try {
            pst = con.prepareStatement(sql);

            pst.setInt(1, trainerid);
            pst.setInt(2, courseid);

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
            System.out.println("Deleted Trainer from Course");
        }
//        return result;

    }
     
     public static ArrayList<Trainer> getTrainersAppointedToCourse(int courseid) {

        ArrayList<Trainer> list = new ArrayList<Trainer>();
        Connection con = DBUtils.getConnection();
        PreparedStatement pst = null;
        String sql = "SELECT * " +
"FROM trainers s " +
"LEFT JOIN trainersincourses sc ON sc.trainerid = s.trainerid " +
"WHERE sc.courseid=?;";

        try {

            pst = con.prepareStatement(sql);
            pst.setInt(1, courseid);
            ResultSet rs = pst.executeQuery();

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
}
