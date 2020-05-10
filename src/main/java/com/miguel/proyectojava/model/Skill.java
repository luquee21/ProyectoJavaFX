/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.miguel.proyectojava.model;

/**
 *
 * @author migue
 */
public class Skill {
     enum Skills {
        LessProbMiss, MoreDamage, MoreHealth, MoreArmor;
    }
    private String name;
    private String description;
    private Skills eSkill;

    public Skill(String name, String description, Skills eSkill) {
        this.name = name;
        this.description = description;
        this.eSkill = eSkill;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Skills geteSkill() {
        return eSkill;
    }

    public void seteSkill(Skills eSkill) {
        this.eSkill = eSkill;
    }

    
}
