package com.example.tttt;

import java.util.Date;

public class Class {
    String title, type, description, difficulty, date, time, instructor, dayOfWeek;
    //Date[] dayOfWeek, time;
    int capacity;

    /*
        I am thinking that instead of making 2 Data[] arrays dayOfWeek and time, we can
        make a dropdown menu in the class enroll interface (once it is created)
     */

    public Class(String title, String type, String description, String difficulty, int capacity, String date, String time, String instructor, String dayOfWeek) {
        this.title = title;
        this.type = type;
        this.description = description;
        this.difficulty = difficulty;
        this.date = date;
        this.time = time;
        this.capacity = capacity;
        this.instructor = instructor;
        this.dayOfWeek = dayOfWeek;
    }

    public Class() {

    }

    /*
    Getters and Setters are created.
     */

    public void setTitle(String title) {

        this.title = title;

    }

    public void setDescription(String description) {

        this.description = description;

    }

    public void setDifficulty(String difficulty) {

        this.difficulty = difficulty;

    }

    public void setDate(String date) {

        this.date = date;

    }
    public void setTime(String time) {

        this.time = time;

    }

    public void setCapacity(int capacity) {

        this.capacity = capacity;

    }

    public void setInstructor(String instructor) {

        this.instructor = instructor;

    }

    public void setType(String type) {

        this.type = type;

    }

    public void setDayOfWeek(String dayOfWeek){
        this.dayOfWeek = dayOfWeek;
    }

    public String getDayOfWeek(){
        return dayOfWeek;
    }

    public String getTitle() {

        return title;

    }

    public String getDescription() {

        return description;

    }

    public String getDifficulty() {

        return difficulty;

    }

    public String getDate() {

        return date;

    }

    public String getTime() {

        return time;

    }

    public int getCapacity() {

        return capacity;

    }

    public String getType() {

        return type;

    }

    public String getInstructor() {

        return instructor;

    }

    /** This method checks whether there is a conflict of a class
     *
     * @param aClass Type Class
     * @return true if there is no conflict; false otherwise
     */
    public boolean checkClass(Class aClass) {

        if (this.getTitle().equals(aClass.getTitle()) && this.getDate().equals(aClass.getDate())) {

            return false;

        }

        return true;

    }

    public String toString(){

        String res = "Title: " + title + "\n" + "Type: " + "\n" + "Description: " + description + "\n"
                + "Difficulty: " + difficulty + "\n" + "Date" + date + "\n"
                + "Time: " + time + "\n" + "Capacity: " + capacity + "\n" + "Instructor: " + instructor;

        return res;

    }

    //String title, description, difficulty, date, time;
    //Date[] dayOfWeek, time;
    //int capacity;

}
