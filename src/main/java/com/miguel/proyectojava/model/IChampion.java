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
public interface IChampion {

    String getName();

    void setName(String name);

    int getDamage();

    void setDamage(int damage);
    
    int getHealth();
    
    void setHealth(int health);
    
    int getArmor();
    
    void setArmor(int armor);
    
    Skill getSkill();
    
    void setSkill(Skill skill);
    
    int getProb_Miss();
    
    void setProb_Miss(int prob_miss);
}
