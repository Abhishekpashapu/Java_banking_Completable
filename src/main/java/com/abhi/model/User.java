package com.abhi.model;

public class User {

    private String userName;
    private Double salary;
    private String panCard;
    private int cibilScore;

    public User(String userName, String panCard, Double salary, int cibilScore) {
        this.userName = userName;
        this.panCard = panCard;
        this.salary = salary;
        this.cibilScore = cibilScore;

    }

    public Double getSalary() {
        return salary;
    }

    public String getUserName() {
        return userName;
    }

    public String getPanCard() {
        return panCard;
    }

    public int getCibilScore() {
        return cibilScore;
    }

    public void setSalary(Double salary) {
        this.salary = salary;
    }

    public void setCibilScore(int cibilScore) {
        this.cibilScore = cibilScore;
    }
}

