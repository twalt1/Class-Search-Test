package com.example.seg2105;

public class UserAccount {
    public String name;
    public int age;
    public String email;
    public String phoneNo;
    public String userName;
    public String passWord;
    public String acctType;

    public UserAccount(String name, int age, String email, String phoneNo, String userName, String passWord, String acctType){
        this.name = name;
        this.age = age;
        this.email = email;
        this.phoneNo = phoneNo;
        this.userName = userName;
        this.passWord = passWord;
        this.acctType = acctType;
    }

    public UserAccount() {

    }

    @Override
    public String toString() {
        return "UserAccount{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", email='" + email + '\'' +
                ", phoneNo='" + phoneNo + '\'' +
                ", userName='" + userName + '\'' +
                ", passWord='" + passWord + '\'' +
                ", acctType='" + acctType + '\'' +
                '}';
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
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

    public String getAcctType() {
        return acctType;
    }

    public void setAcctType(String acctType) {
        this.acctType = acctType;
    }
}
