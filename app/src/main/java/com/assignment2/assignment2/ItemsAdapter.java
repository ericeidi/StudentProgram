package com.assignment2.assignment2;

public class ItemsAdapter {
    Integer id;
    String name;
    String lastName;
    String marks;
    String course;

    public ItemsAdapter(Integer id, String name, String lastName, String marks, String course, String credit) {
        this.id = id;
        this.name = name;
        this.lastName = lastName;
        this.marks = marks;
        this.course = course;
        this.credit = credit;
    }

    String credit;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getMarks() {
        return marks;
    }
    public void setMarks(String marks) {
        this.marks = marks;
    }

    public String getCourse() {
        return course;
    }

    public void setCourse(String course) {
        this.course = course;
    }

    public String getCredit() {
        return credit;
    }

    public void setCredit(String credit) {
        this.credit = credit;
    }


}
