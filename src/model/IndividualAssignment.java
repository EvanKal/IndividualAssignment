/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

/**
 *
 * @author Los_e
 */
public class IndividualAssignment extends Assignment {
    
    private Student student;
    private Assignment assignment;
    private boolean submitted;
    private int oralmark;
    private int totalmark;
    
    public IndividualAssignment(Assignment assignment) {
        super(assignment);
    }

    public int getOralmark() {
        return oralmark;
    }

    public void setOralmark(int oralmark) {
        this.oralmark = oralmark;
    }

    public int getTotalmark() {
        return totalmark;
    }

    public void setTotalmark(int totalmark) {
        this.totalmark = totalmark;
    }
    
     public boolean isSubmitted() {
        return submitted;
    }

    public void setSubmitted(boolean submitted) {
        this.submitted = submitted;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public Assignment getAssignment() {
        return assignment;
    }

    public void setAssignment(Assignment assignment) {
        this.assignment = assignment;
    }


    
    

    @Override
    public String toString() {
        return "Marks for Assignment: " + super.getTitle() + " Oralmark=" + oralmark + ", Totalmark=" + totalmark;
    }
    
    
}
