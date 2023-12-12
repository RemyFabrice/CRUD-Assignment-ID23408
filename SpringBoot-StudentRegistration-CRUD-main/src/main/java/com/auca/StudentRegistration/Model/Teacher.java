package com.auca.StudentRegistration.Model;

import jakarta.persistence.*;

@Entity
public class Teacher {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private int tr_id;
    private String tr_code;
    private String fullNames;
    private String email;
    private String phoneNbr;
    @Enumerated(EnumType.STRING)
    private Qualification qualification;

    public Teacher() {
    }

    public Teacher(int tr_id, String tr_code, String fullNames, String email, String phoneNbr, Qualification qualification) {
        this.tr_id = tr_id;
        this.tr_code = tr_code;
        this.fullNames = fullNames;
        this.email = email;
        this.phoneNbr = phoneNbr;
        this.qualification = qualification;
    }

    public int getTr_id() {
        return tr_id;
    }

    public void setTr_id(int tr_id) {
        this.tr_id = tr_id;
    }

    public String getTr_code() {
        return tr_code;
    }

    public void setTr_code(String tr_code) {
        this.tr_code = tr_code;
    }

    public String getFullNames() {
        return fullNames;
    }

    public void setFullNames(String fullNames) {
        this.fullNames = fullNames;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNbr() {
        return phoneNbr;
    }

    public void setPhoneNbr(String phoneNbr) {
        this.phoneNbr = phoneNbr;
    }

    public Qualification getQualification() {
        return qualification;
    }

    public void setQualification(Qualification qualification) {
        this.qualification = qualification;
    }
}
