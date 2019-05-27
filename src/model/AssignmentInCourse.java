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
public class AssignmentInCourse {

    private Assignment assignment;
    private Course course;
    private LocalDate submissiondate;
    private LocalTime submissiontime;
    private LocalDateTime submissiondatetime;
    private String submissiondatetimetoparsedstring;
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    public AssignmentInCourse() {
    }

    public Assignment getAssignment() {
        return assignment;
    }

    public void setAssignment(Assignment assignment) {
        this.assignment = assignment;
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
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

    public void setSubmissiondatetime(String submissiondatetimestr) {
        this.submissiondatetime = LocalDateTime.parse(submissiondatetimestr);
        this.submissiondatetimetoparsedstring = this.submissiondatetime.format(formatter);
    }

    public void setSubmissiondatetime(LocalDate submissiondate, LocalTime submissiontime) {
        this.submissiondatetime = LocalDateTime.of(submissiondate, submissiontime);
        this.submissiondatetimetoparsedstring = this.submissiondatetime.format(formatter);
    }

    public DateTimeFormatter getFormatter() {
        return formatter;
    }

    public void setFormatter(DateTimeFormatter formatter) {
        this.formatter = formatter;
    }

    public String getSubmissiondatetimetoparsedstring() {
        return submissiondatetimetoparsedstring;
    }

}
