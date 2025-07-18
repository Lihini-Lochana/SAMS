package com.ijse.sams.entity;
import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "classes")
public class ClassSchedule {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private LocalDate date;
    @ManyToOne
    @JoinColumn(name = "subject_id")
    private Subject subject;

    @ManyToOne
    @JoinColumn(name = "course_id")
    private Course course;

    @ManyToOne
    @JoinColumn(name = "lecturer_id")
    private Lecturer lecturer;

    public ClassSchedule() {
    }

    public ClassSchedule(int id, LocalDate date, Subject subject, Course course, Lecturer lecturer) {
        this.id = id;
        this.date = date;
        this.subject = subject;
        this.course = course;
        this.lecturer = lecturer;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public Subject getSubject() {
        return subject;
    }

    public void setSubject(Subject subject) {
        this.subject = subject;
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    public Lecturer getLecturer() {
        return lecturer;
    }

    public void setLecturer(Lecturer lecturer) {
        this.lecturer = lecturer;
    }

    @Override
    public String toString() {
        return course.getName() + " - " + subject.getName() + " on " + date + "  assigned for lecturer " + lecturer.getName();
    }


}
