/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.company.entity;

import java.sql.Date;
import java.util.List;

/**
 *
 * @author V&V
 */
public class User {

    private int id;
    private String phone;
    private String name;
    private String surname;
    private String email;
    private String profileDescription;
    private String address;
    private Country nationality;
    private Country birthPlace;
    private Date birthDate;
    private List<UserSkill> list;

    public List<UserSkill> getList() {
        return list;
    }

    public void setList(List<UserSkill> list) {
        this.list = list;
    }
    
    public String getProfileDescription() {
        return profileDescription;
    }

    public void setProfileDescription(String profileDescription) {
        this.profileDescription = profileDescription;
    }

    public User() {
    }

    public User(int id) {
        this.id = id;
    }

    public User(int id, String phone, String name, String surname, String email, String profileDesc, String address,
            Country nationality, Country birthPlace, Date date) {
        this.id = id;
        this.phone = phone;
        this.name = name;
        this.surname = surname;
        this.email = email;
        this.profileDescription = profileDesc;
        this.address = address;
        this.nationality = nationality;
        this.birthPlace = birthPlace;
        this.birthDate = date;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Country getNationality() {
        return nationality;
    }

    public void setNationality(Country nationality) {
        this.nationality = nationality;
    }

    public Country getBirthPlace() {
        return birthPlace;
    }

    public void setBirthPlace(Country birthPlace) {
        this.birthPlace = birthPlace;
    }

    public Date getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Date date) {
        this.birthDate = date;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Override
    public String toString() {
        return "User{" + "id=" + id + ", phone=" + phone + ", name=" + name + ", surname=" + surname + ", email=" + email + ", profileDescription=" + profileDescription + ", address=" + address + ", nationality=" + nationality + ", birthPlace=" + birthPlace + ", birthDate=" + birthDate + ", list=" + list + '}';
    }

    
}
