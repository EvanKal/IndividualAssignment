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
import model.Course;
import model.IndividualAssignment;
import model.Student;
import model.StudentInCourse;
import utils.DBUtils;

/**
 *
 * @author Los_e
 */
public class IndividualAssignmentDAO {

    public static ArrayList<IndividualAssignment> getAllIndividualAssignments() {
        ArrayList<IndividualAssignment> list = new ArrayList<IndividualAssignment>();
        Connection con = DBUtils.getConnection();
        PreparedStatement pst = null;
        String sql = "SELECT   * FROM individualassignments ia "
                + "inner join assignments a on a.assignmentid = ia.assignmentid "
                + "inner join students s on s.studentid = ia.studentid "
                + "inner join studentsincourses sc on sc.studentid = ia.studentid "
                + "inner join assignmentsincourses ac on  ia.assignmentid = ac.assignmentid and sc.courseid = ac.courseid "
                + "inner join courses c on ac.courseid = c.courseid "
                + "where ac.courseid = ia.courseid "
                + "order by c.courseid, s.studentid;";

        try {

            pst = con.prepareStatement(sql);
            ResultSet rs = pst.executeQuery(sql);

            while (rs.next()) {

                Assignment assignment = new Assignment();
                assignment.setAssignmentid(rs.getInt(7));
                assignment.setTitle(rs.getString(8));
                assignment.setDescription(rs.getString(9));
                assignment.setCourseid(rs.getInt(18));
                assignment.setSubmissiondatetime(rs.getString(22));

                Student student = new Student();
                student.setStudentid(rs.getInt(10));
                student.setFirstname(rs.getString(11));
                student.setLastname(rs.getString(12));
                student.setDateofbirth(rs.getString(13));
                student.setTuitionfees(rs.getFloat(14));
                student.setStream(rs.getString(15));
                student.setType(rs.getString(16));

                IndividualAssignment individualassignment = new IndividualAssignment(assignment);
                individualassignment.setAssignment(assignment);
                individualassignment.setStudent(student);

                if (rs.getInt(4) == 0) {
                    individualassignment.setSubmitted(false);
                } else if (rs.getInt(4) == 1) {
                    individualassignment.setSubmitted(true);
                }

                individualassignment.setOralmark(rs.getInt(5));
                individualassignment.setTotalmark(rs.getInt(6));

                list.add(individualassignment);

            }
        } catch (SQLException ex) {
            Logger.getLogger(IndividualAssignment.class.getName()).log(Level.SEVERE, null, ex);
        } finally {

            try {
                pst.close();
            } catch (SQLException ex) {
                Logger.getLogger(IndividualAssignment.class.getName()).log(Level.SEVERE, null, ex);
            }
            try {
                con.close();
            } catch (SQLException ex) {
                Logger.getLogger(IndividualAssignment.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        return list;
    }
    
    public static ArrayList<IndividualAssignment> getAllIndividualAssignmentsByTrainer(int trainerid) {
        ArrayList<IndividualAssignment> list = new ArrayList<IndividualAssignment>();
        Connection con = DBUtils.getConnection();
        PreparedStatement pst = null;
        String sql = "SELECT   * FROM individualassignments ia "
                + "inner join assignments a on a.assignmentid = ia.assignmentid "
                + "inner join students s on s.studentid = ia.studentid "
                + "inner join studentsincourses sc on sc.studentid = ia.studentid "
                + "inner join assignmentsincourses ac on  ia.assignmentid = ac.assignmentid and sc.courseid = ac.courseid "
                + "inner join courses c on ac.courseid = c.courseid "
                + "inner join trainersincourses tc on tc.courseid = c.courseid "
                + "where ac.courseid = ia.courseid and tc.trainerid = ? "
                + "order by c.courseid, s.studentid;";

        try {

            pst = con.prepareStatement(sql);
            pst.setInt(1, trainerid);
            ResultSet rs = pst.executeQuery();

            while (rs.next()) {

                Assignment assignment = new Assignment();
                assignment.setAssignmentid(rs.getInt(7));
                assignment.setTitle(rs.getString(8));
                assignment.setDescription(rs.getString(9));
                assignment.setCourseid(rs.getInt(18));
                assignment.setSubmissiondatetime(rs.getString(22));

                Student student = new Student();
                student.setStudentid(rs.getInt(10));
                student.setFirstname(rs.getString(11));
                student.setLastname(rs.getString(12));
                student.setDateofbirth(rs.getString(13));
                student.setTuitionfees(rs.getFloat(14));
                student.setStream(rs.getString(15));
                student.setType(rs.getString(16));

                IndividualAssignment individualassignment = new IndividualAssignment(assignment);
                individualassignment.setAssignment(assignment);
                individualassignment.setStudent(student);

                if (rs.getInt(4) == 0) {
                    individualassignment.setSubmitted(false);
                } else if (rs.getInt(4) == 1) {
                    individualassignment.setSubmitted(true);
                }

                individualassignment.setOralmark(rs.getInt(5));
                individualassignment.setTotalmark(rs.getInt(6));

                list.add(individualassignment);

            }
        } catch (SQLException ex) {
            Logger.getLogger(IndividualAssignment.class.getName()).log(Level.SEVERE, null, ex);
        } finally {

            try {
                pst.close();
            } catch (SQLException ex) {
                Logger.getLogger(IndividualAssignment.class.getName()).log(Level.SEVERE, null, ex);
            }
            try {
                con.close();
            } catch (SQLException ex) {
                Logger.getLogger(IndividualAssignment.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        return list;
    }
    
    public static ArrayList<IndividualAssignment> getAllSubmittedIndividualAssignments() {
        ArrayList<IndividualAssignment> list = new ArrayList<IndividualAssignment>();
        Connection con = DBUtils.getConnection();
        PreparedStatement pst = null;
        String sql = "SELECT   * FROM individualassignments ia "
                + "inner join assignments a on a.assignmentid = ia.assignmentid "
                + "inner join students s on s.studentid = ia.studentid "
                + "inner join studentsincourses sc on sc.studentid = ia.studentid "
                + "inner join assignmentsincourses ac on  ia.assignmentid = ac.assignmentid and sc.courseid = ac.courseid "
                + "inner join courses c on ac.courseid = c.courseid "
                + "where ac.courseid = ia.courseid and ia.submitted = 1 "
                + "order by c.courseid, s.studentid;";

        try {

            pst = con.prepareStatement(sql);
            ResultSet rs = pst.executeQuery();

            while (rs.next()) {

                Assignment assignment = new Assignment();
                assignment.setAssignmentid(rs.getInt(7));
                assignment.setTitle(rs.getString(8));
                assignment.setDescription(rs.getString(9));
                assignment.setCourseid(rs.getInt(18));
                assignment.setSubmissiondatetime(rs.getString(22));

                Student student = new Student();
                student.setStudentid(rs.getInt(10));
                student.setFirstname(rs.getString(11));
                student.setLastname(rs.getString(12));
                student.setDateofbirth(rs.getString(13));
                student.setTuitionfees(rs.getFloat(14));
                student.setStream(rs.getString(15));
                student.setType(rs.getString(16));

                IndividualAssignment individualassignment = new IndividualAssignment(assignment);
                individualassignment.setAssignment(assignment);
                individualassignment.setStudent(student);

                if (rs.getInt(4) == 0) {
                    individualassignment.setSubmitted(false);
                } else if (rs.getInt(4) == 1) {
                    individualassignment.setSubmitted(true);
                }

                individualassignment.setOralmark(rs.getInt(5));
                individualassignment.setTotalmark(rs.getInt(6));

                list.add(individualassignment);

            }
        } catch (SQLException ex) {
            Logger.getLogger(IndividualAssignment.class.getName()).log(Level.SEVERE, null, ex);
        } finally {

            try {
                pst.close();
            } catch (SQLException ex) {
                Logger.getLogger(IndividualAssignment.class.getName()).log(Level.SEVERE, null, ex);
            }
            try {
                con.close();
            } catch (SQLException ex) {
                Logger.getLogger(IndividualAssignment.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        return list;
    }
    
    public static ArrayList<IndividualAssignment> getAllSubmittedIndividualAssignmentsByTrainer(int trainerid) {
        ArrayList<IndividualAssignment> list = new ArrayList<IndividualAssignment>();
        Connection con = DBUtils.getConnection();
        PreparedStatement pst = null;
        String sql = "SELECT   * FROM individualassignments ia "
                + "inner join assignments a on a.assignmentid = ia.assignmentid "
                + "inner join students s on s.studentid = ia.studentid "
                + "inner join studentsincourses sc on sc.studentid = ia.studentid "
                + "inner join assignmentsincourses ac on  ia.assignmentid = ac.assignmentid and sc.courseid = ac.courseid "
                + "inner join courses c on ac.courseid = c.courseid "
                + "inner join trainersincourses tc on tc.courseid = c.courseid "
                + "where ac.courseid = ia.courseid and ia.submitted = 1 and tc.trainerid =? "
                + "order by c.courseid, s.studentid;";

        try {

            pst = con.prepareStatement(sql);
            pst.setInt(1, trainerid);
            ResultSet rs = pst.executeQuery();

            while (rs.next()) {

                Assignment assignment = new Assignment();
                assignment.setAssignmentid(rs.getInt(7));
                assignment.setTitle(rs.getString(8));
                assignment.setDescription(rs.getString(9));
                assignment.setCourseid(rs.getInt(18));
                assignment.setSubmissiondatetime(rs.getString(22));

                Student student = new Student();
                student.setStudentid(rs.getInt(10));
                student.setFirstname(rs.getString(11));
                student.setLastname(rs.getString(12));
                student.setDateofbirth(rs.getString(13));
                student.setTuitionfees(rs.getFloat(14));
                student.setStream(rs.getString(15));
                student.setType(rs.getString(16));

                IndividualAssignment individualassignment = new IndividualAssignment(assignment);
                individualassignment.setAssignment(assignment);
                individualassignment.setStudent(student);

                if (rs.getInt(4) == 0) {
                    individualassignment.setSubmitted(false);
                } else if (rs.getInt(4) == 1) {
                    individualassignment.setSubmitted(true);
                }

                individualassignment.setOralmark(rs.getInt(5));
                individualassignment.setTotalmark(rs.getInt(6));

                list.add(individualassignment);

            }
        } catch (SQLException ex) {
            Logger.getLogger(IndividualAssignment.class.getName()).log(Level.SEVERE, null, ex);
        } finally {

            try {
                pst.close();
            } catch (SQLException ex) {
                Logger.getLogger(IndividualAssignment.class.getName()).log(Level.SEVERE, null, ex);
            }
            try {
                con.close();
            } catch (SQLException ex) {
                Logger.getLogger(IndividualAssignment.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        return list;
    }
    
    public static ArrayList<IndividualAssignment> getAllIndividualAssignmentsByStudentId(int studentid) {
        ArrayList<IndividualAssignment> list = new ArrayList<IndividualAssignment>();
        Connection con = DBUtils.getConnection();
        PreparedStatement pst = null;
        String sql = "SELECT   * FROM individualassignments ia "
                + "inner join assignments a on a.assignmentid = ia.assignmentid "
                + "inner join students s on s.studentid = ia.studentid "
                + "inner join studentsincourses sc on sc.studentid = ia.studentid "
                + "inner join assignmentsincourses ac on  ia.assignmentid = ac.assignmentid and sc.courseid = ac.courseid "
                + "inner join courses c on ac.courseid = c.courseid "
                + "where ia.studentid = ? and ac.courseid = ia.courseid "
                + "order by c.courseid, s.studentid;";

        try {

            pst = con.prepareStatement(sql);
            pst.setInt(1, studentid);
            ResultSet rs = pst.executeQuery();

            while (rs.next()) {

                Assignment assignment = new Assignment();
                assignment.setAssignmentid(rs.getInt(7));
                assignment.setTitle(rs.getString(8));
                assignment.setDescription(rs.getString(9));
                assignment.setCourseid(rs.getInt(18));
                assignment.setSubmissiondatetime(rs.getString(22));

                Student student = new Student();
                student.setStudentid(rs.getInt(10));
                student.setFirstname(rs.getString(11));
                student.setLastname(rs.getString(12));
                student.setDateofbirth(rs.getString(13));
                student.setTuitionfees(rs.getFloat(14));
                student.setStream(rs.getString(15));
                student.setType(rs.getString(16));

                IndividualAssignment individualassignment = new IndividualAssignment(assignment);
                individualassignment.setAssignment(assignment);
                individualassignment.setStudent(student);

                if (rs.getInt(4) == 0) {
                    individualassignment.setSubmitted(false);
                } else if (rs.getInt(4) == 1) {
                    individualassignment.setSubmitted(true);
                }

                individualassignment.setOralmark(rs.getInt(5));
                individualassignment.setTotalmark(rs.getInt(6));

                list.add(individualassignment);

            }
        } catch (SQLException ex) {
            Logger.getLogger(IndividualAssignment.class.getName()).log(Level.SEVERE, null, ex);
        } finally {

            try {
                pst.close();
            } catch (SQLException ex) {
                Logger.getLogger(IndividualAssignment.class.getName()).log(Level.SEVERE, null, ex);
            }
            try {
                con.close();
            } catch (SQLException ex) {
                Logger.getLogger(IndividualAssignment.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        return list;
    }

    public static ArrayList<IndividualAssignment> getNotSubmittedIndividualAssignmentsByStudentId(int studentid) {
        ArrayList<IndividualAssignment> list = new ArrayList<IndividualAssignment>();
        Connection con = DBUtils.getConnection();
        PreparedStatement pst = null;
        String sql = "SELECT   * FROM individualassignments ia "
                + "inner join assignments a on a.assignmentid = ia.assignmentid "
                + "inner join students s on s.studentid = ia.studentid "
                + "inner join studentsincourses sc on sc.studentid = ia.studentid "
                + "inner join assignmentsincourses ac on  ia.assignmentid = ac.assignmentid and sc.courseid = ac.courseid "
                + "inner join courses c on ac.courseid = c.courseid "
                + "where ia.studentid = ? and ac.courseid = ia.courseid and ia.submitted = 0 "
                + "order by c.courseid, s.studentid;";

        try {

            pst = con.prepareStatement(sql);
            pst.setInt(1, studentid);
            ResultSet rs = pst.executeQuery();

            while (rs.next()) {

                Assignment assignment = new Assignment();
                assignment.setAssignmentid(rs.getInt(7));
                assignment.setTitle(rs.getString(8));
                assignment.setDescription(rs.getString(9));
                assignment.setCourseid(rs.getInt(18));
                assignment.setSubmissiondatetime(rs.getString(22));

                Student student = new Student();
                student.setStudentid(rs.getInt(10));
                student.setFirstname(rs.getString(11));
                student.setLastname(rs.getString(12));
                student.setDateofbirth(rs.getString(13));
                student.setTuitionfees(rs.getFloat(14));
                student.setStream(rs.getString(15));
                student.setType(rs.getString(16));

                IndividualAssignment individualassignment = new IndividualAssignment(assignment);
                individualassignment.setAssignment(assignment);
                individualassignment.setStudent(student);

                if (rs.getInt(4) == 0) {
                    individualassignment.setSubmitted(false);
                } else if (rs.getInt(4) == 1) {
                    individualassignment.setSubmitted(true);
                }

                individualassignment.setOralmark(rs.getInt(5));
                individualassignment.setTotalmark(rs.getInt(6));

                list.add(individualassignment);

            }
        } catch (SQLException ex) {
            Logger.getLogger(IndividualAssignment.class.getName()).log(Level.SEVERE, null, ex);
        } finally {

            try {
                pst.close();
            } catch (SQLException ex) {
                Logger.getLogger(IndividualAssignment.class.getName()).log(Level.SEVERE, null, ex);
            }
            try {
                con.close();
            } catch (SQLException ex) {
                Logger.getLogger(IndividualAssignment.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        return list;
    }

    public static void insertIndividualAssignment(IndividualAssignment individualassignment) {

        Connection con = DBUtils.getConnection();
        PreparedStatement pst = null;
        String sql = "insert into individualassignments(studentid, assignmentid, courseid, submitted, oralmark, totalmark) select ?, ?, ?, ?, ?, ?"
                + " WHERE NOT EXISTS "
                + " (select * from individualassignments WHERE studentid = ? /*6*/ and assignmentid = ? /*7*/  and courseid = ?)";
        boolean result = false;

        try {
            pst = con.prepareStatement(sql);
            pst.setInt(1, individualassignment.getStudent().getStudentid());
            pst.setInt(2, individualassignment.getAssignment().getAssignmentid());
            pst.setInt(3, individualassignment.getCourseid());
            pst.setInt(4, 0);
            pst.setInt(5, 0);
            pst.setInt(6, 0);
            pst.setInt(7, individualassignment.getStudent().getStudentid());
            pst.setInt(8, individualassignment.getAssignment().getAssignmentid());
            pst.setInt(9, individualassignment.getCourseid());

            pst.executeUpdate();

            result = true;
        } catch (SQLException ex) {
            Logger.getLogger(IndividualAssignmentDAO.class.getName()).log(Level.SEVERE, null, ex);
            result = false;
        } finally {
            try {
                pst.close();
            } catch (SQLException ex) {
                Logger.getLogger(IndividualAssignmentDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
            try {
                con.close();
            } catch (SQLException ex) {
                Logger.getLogger(IndividualAssignmentDAO.class.getName()).log(Level.SEVERE, null, ex);
            }

        }

        if (result) {
            System.out.println("Assignments of the course were appointed to Student");
        }
//        return result;

    }

    public static void updateIndividualAssignment(IndividualAssignment individualassignment) {

        Connection con = DBUtils.getConnection();
        PreparedStatement pst = null;
        String sql = "UPDATE individualassignments SET submitted = ? /*1*/, oralmark = ? /*2*/, totalmark = ? /*3*/   WHERE studentid = ? /*4*/ and assignmentid = ? /*5*/  and courseid = ? /*6*/";
        boolean result = false;

        try {
            pst = con.prepareStatement(sql);

            if (individualassignment.isSubmitted()) {
                pst.setInt(1, 1);
            } else {
                pst.setInt(1, 0);
            }

            pst.setInt(2, individualassignment.getOralmark());
            pst.setInt(3, individualassignment.getTotalmark());
            pst.setInt(4, individualassignment.getStudent().getStudentid());
            pst.setInt(5, individualassignment.getAssignmentid());
            pst.setInt(6, individualassignment.getCourseid());

            pst.executeUpdate();

            result = true;
        } catch (SQLException ex) {
            Logger.getLogger(IndividualAssignmentDAO.class.getName()).log(Level.SEVERE, null, ex);
            result = false;
        } finally {
            try {
                pst.close();
            } catch (SQLException ex) {
                Logger.getLogger(IndividualAssignmentDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
            try {
                con.close();
            } catch (SQLException ex) {
                Logger.getLogger(IndividualAssignmentDAO.class.getName()).log(Level.SEVERE, null, ex);
            }

        }

        if (result) {
            System.out.println("Updated Assignment");
        }
//        return result;

    }
}
