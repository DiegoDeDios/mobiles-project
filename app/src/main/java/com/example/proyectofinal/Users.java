package com.example.proyectofinal;

public class Users {
    String name,
            fatherLastName,
            motherLastName,
            email,
            schoolGrade,
            password,
            phone;

    Integer age;

    public Users() {   }

    public Users(String name, String fatherLastName, String motherLastName, String email, String schoolGrade, Integer age, String phone, String password) {
        this.name = name;
        this.fatherLastName = fatherLastName;
        this.motherLastName = motherLastName;
        this.email = email;
        this.schoolGrade = schoolGrade;
        this.age = age;
        this.phone = phone;
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFatherLastName() {
        return fatherLastName;
    }

    public void setFatherLastName(String fatherLastName) {
        this.fatherLastName = fatherLastName;
    }

    public String getMotherLastName() {
        return motherLastName;
    }

    public void setMotherLastName(String motherLastName) {
        this.motherLastName = motherLastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSchoolGrade() {
        return schoolGrade;
    }

    public void setSchoolGrade(String schoolGrade) {
        this.schoolGrade = schoolGrade;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
