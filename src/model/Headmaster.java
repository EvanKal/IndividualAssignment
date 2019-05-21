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
public class Headmaster {
    
    private int headmasterid;
    private String firstname;
    private String lastname;

    public Headmaster() {
    }

    public int getHeadmasterid() {
        return headmasterid;
    }

    public void setHeadmasterid(int headmasterid) {
        this.headmasterid = headmasterid;
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

    @Override
    public String toString() {
        return "Headmaster: " + headmasterid + " - " + firstname + " - " + lastname;
    }

    
    
}
