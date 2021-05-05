package com.noabsent.beans;

public class Student {

    private long id;
    private String rgm;
    private String password;
    private String name;

    public Student(long id, String rgm, String password, String name) {
        this.id = id;
        this.rgm = rgm;
        this.password = password;
        this.name = name;
    }

    public Student() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getRgm() {
        return rgm;
    }

    public void setRgm(String rgm) {
        this.rgm = rgm;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
