/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.time.LocalDate;

/**
 *
 * @author Los_e
 */
public class Student {

    private int studentid;
    private String firstname;
    private String lastname;
    private String stream;
    private String type;
    private float tuitionfees;
    private LocalDate dateofbirth;

    public Student() {
    }

    public int getStudentid() {
        return studentid;
    }

    public void setStudentid(int studentid) {
        this.studentid = studentid;
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

    public float getTuitionfees() {
        return tuitionfees;
    }

    public void setTuitionfees(float tuitionfees) {
        this.tuitionfees = tuitionfees;
    }

    public LocalDate getDateofbirth() {
        return dateofbirth;
    }

    public void setDateofbirth(String dateofbirthstr) {
        this.dateofbirth = LocalDate.parse(dateofbirthstr);
    }

    public String getStream() {
        return stream;
    }

    public void setStream(String stream) {
        this.stream = stream;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "Student: " + studentid + " - " + firstname + " - " + lastname + " - " + stream + " - " + type + " - " + tuitionfees + " - " + dateofbirth;
    }

}
