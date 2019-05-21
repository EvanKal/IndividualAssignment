/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

/**
 *
 * @author Los_e
 */
public class Assignment {

    private int assignmentid;
    private String title;
    private String description;
    private LocalDate submissiondate;
    private LocalTime submissiontime;
    private LocalDateTime submissiondatetime;
    private String submissiondatetimetoparsedstring;
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    public Assignment() {
    }

    public Assignment(Assignment assignment) {
        this.assignmentid = assignment.getAssignmentid();
        this.title = assignment.getTitle();
        this.description = assignment.getDescription();
        this.submissiondate = assignment.getSubmissiondate();
        this.submissiontime = assignment.getSubmissiontime();
        this.submissiondatetime = assignment.getSubmissiondatetime();
        this.submissiondatetimetoparsedstring = assignment.getSubmissiondatetimetoparsedstring();
    }

    public int getAssignmentid() {
        return assignmentid;
    }

    public void setAssignmentid(int assignmentid) {
        this.assignmentid = assignmentid;
    }
    
    
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDate getSubmissiondate() {
        return submissiondate;
    }

    public void setSubmissiondate(String submissiondatestr) {
        this.submissiondate = LocalDate.parse(submissiondatestr);
    }

    public LocalTime getSubmissiontime() {
        return submissiontime;
    }

    public void setSubmissiontime(String submissiontimestr) {
        this.submissiontime = LocalTime.parse(submissiontimestr);
    }

    public LocalDateTime getSubmissiondatetime() {
        return submissiondatetime;
    }

    public void setSubmissiondatetime(LocalDate submissiondate, LocalTime submissiontime) {
        this.submissiondatetime = LocalDateTime.of(submissiondate, submissiontime);
        this.submissiondatetimetoparsedstring = this.submissiondatetime.format(formatter);
    }

    public String getSubmissiondatetimetoparsedstring() {
        return submissiondatetimetoparsedstring;
    }

//    public void setSubmissiondatetimetoparsedstring(String submissiondatetimetoparsedstring) {
//        this.submissiondatetimetoparsedstring = submissiondatetimetoparsedstring;
//    }
    public DateTimeFormatter getFormatter() {
        return formatter;
    }

    public void setFormatter(DateTimeFormatter formatter) {
        this.formatter = formatter;
    }

    @Override
    public String toString() {
        return "Assignment: " + assignmentid + " - " + title + " - " + description + " - " + submissiondatetime;
    }
    
    
}
