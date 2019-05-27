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

    @Override
    public String toString() {
        return "Marks for Assignment: " + super.getTitle() + " Oralmark=" + oralmark + ", Totalmark=" + totalmark;
    }
    
    
}
