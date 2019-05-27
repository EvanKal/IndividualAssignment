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
public class Course {

    private int courseid;
    private String title;
    private String stream;
    private String type;
    private LocalDate startdate;
    private LocalDate enddate;
    private String description;

    public Course() {
    }

    public int getCourseid() {
        return courseid;
    }

    public void setCourseid(int courseid) {
        this.courseid = courseid;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
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

    public LocalDate getStartdate() {
        return startdate;
    }

    public void setStartdate(String startdatestr) {
        this.startdate = LocalDate.parse(startdatestr);
    }

    public LocalDate getEnddate() {
        return enddate;
    }

    public void setEnddate(String enddatestr) {
        this.enddate = LocalDate.parse(enddatestr);
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "Course: " + courseid + " - " + title + " - " + stream + " - " + type + " - " + startdate + " - " + enddate;
    }
    
    
}
