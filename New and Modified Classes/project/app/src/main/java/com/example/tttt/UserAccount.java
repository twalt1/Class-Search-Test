package com.example.tttt;

public class UserAccount {

    public String age;
    public String email;
    public String phoneNo;
    public String userName;
    public String passWord;

    public UserAccount(String userName, String passWord, String email, String age, String phoneNo){
        this.userName = userName;
        this.passWord = passWord;
        this.email = email;
        this.age = age;
        this.phoneNo = phoneNo;
    }

    public UserAccount() {

    }

    @Override
    public String toString() {
        return "UserAccount{" +
                "name='" + userName + '\'' +
                ", password=" + passWord +
                ", email='" + email + '\'' +
                ", phoneNo='" + phoneNo + '\'' +
                ", age='" + age + '\'' +
                '}';
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNo() {
        return phoneNo;
    }

    public void setPhoneNo(String phoneNo) {
        this.phoneNo = phoneNo;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassWord() {
        return passWord;
    }

    public void setPassWord(String passWord) {
        this.passWord = passWord;
    }
}