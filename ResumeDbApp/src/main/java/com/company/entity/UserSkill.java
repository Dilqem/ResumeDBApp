/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.company.entity;

/**
 *
 * @author V&V
 */
public class UserSkill {

    private Integer id;
    private Skill skill;
    private User user;
    private int power;

    public UserSkill() {
    }

    public UserSkill(Integer id, Skill skill, User user, int power) {
        this.id = id;
        this.skill = skill;
        this.user = user;
        this.power = power;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Skill getSkill() {
        return skill;
    }

    public void setSkill(Skill skill) {
        this.skill = skill;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public int getPower() {
        return power;
    }

    public void setPower(int power) {
        this.power = power;
    }

    @Override
    public String toString() {
        return "UserSkill{" + "id=" + id + ", skill=" + skill + ", user=" + user + ", power=" + power + '}';
    }

}
