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

    @Override
    public String toString() {
        return "Marks for Assignment: " + super.getTitle() + " Oralmark=" + oralmark + ", Totalmark=" + totalmark;
    }
    
    
}
