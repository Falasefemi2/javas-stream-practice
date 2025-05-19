package com.stream.studentproject;

public class Student {

    private String name;
    private int age;
    private String gradeLevwl;
    private double score;

    public Student(String name, int age, String gradeLevwl, double score) {
        this.name = name;
        this.age = age;
        this.gradeLevwl = gradeLevwl;
        this.score = score;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public String getGradeLevwl() {
        return gradeLevwl;
    }

    public double getScore() {
        return score;
    }

    @Override
    public String toString() {
        return "Grade Level: " + gradeLevwl + "\n -------------------- " + " \n Name | " + name + "\n Score: " + score + " Age " + age;
    }
}
