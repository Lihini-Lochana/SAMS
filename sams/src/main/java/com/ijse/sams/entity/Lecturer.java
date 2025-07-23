package com.ijse.sams.entity;
import javax.persistence.*;


@Entity
@Table(name = "lecturers")
public class Lecturer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String name;

    public Lecturer(int id, String name, String contact, Subject subject) {
        this.id = id;
        this.name = name;
        this.contact = contact;
        this.subject = subject;
    }

    private String contact;

    @ManyToOne
    @JoinColumn(name = "subject_id")
    private Subject subject;

    public Lecturer() {
    }



    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public Subject getSubject() {
        return subject;
    }

    public void setSubject(Subject subject) {
        this.subject = subject;
    }

    @Override
    public String toString() {
        return name;
    }

}
