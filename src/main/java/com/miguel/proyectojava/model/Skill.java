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
    private String skill;
    private String description;
    private float modifier;

    public Skill(String skill, String description, float modifier) {
        this.skill = skill;
        this.description = description;
        this.modifier = modifier;
    }

    

    public Skill(String skill) {
        this.skill = skill;
    }
    

    public String getDescription() {
        return description;
    }

    public float getModifier() {
        return modifier;
    }

    public void setModifier(int modifier) {
        this.modifier = modifier;
    }
    

    public void setDescription(String description) {
        this.description = description;
    }

    public String getSkill() {
        return skill;
    }

    public void setSkill(String skill) {
        this.skill = skill;
        
    }

    

    
}
