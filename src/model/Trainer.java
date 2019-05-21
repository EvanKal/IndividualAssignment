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
public class Trainer {

    private int trainerid;
    private String firstname;
    private String lastname;
    private String subject;

    public Trainer() {
    }

    public int getTrainerid() {
        return trainerid;
    }

    public void setTrainerid(int trainerid) {
        this.trainerid = trainerid;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    @Override
    public String toString() {
        return "Trainer: "+ trainerid + " - " + firstname + " - " + lastname + " - " + subject;
    }
    
    
}
