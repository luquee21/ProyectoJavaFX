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
public class Champion implements IChampion {

    protected String name;
    protected int damage;
    protected int health;
    protected int armor;
    protected Skill skill;
    protected int prob_miss;

    public Champion(String name, int damage, int health, int armor, int prob_miss) {
        this.name = name;
        this.damage = damage;
        this.health = health;
        this.armor = armor;
        this.prob_miss = prob_miss;
    }

    public Champion(String name) {
        this.name = name;
    }
    
    

    @Override
    public String getName() {
        return name;
    }

    @Override
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public int getDamage() {
        return damage;
    }

    @Override
    public void setDamage(int damage) {
        this.damage = damage;
    }

    @Override
    public int getHealth() {
        return health;
    }

    @Override
    public void setHealth(int health) {
        this.health = health;
    }

    @Override
    public int getArmor() {
        return armor;
    }

    @Override
    public void setArmor(int armor) {
        this.armor = armor;
    }

    @Override
    public Skill getSkill() {
        return skill;
    }

    @Override
    public void setSkill(Skill skill) {
        switch (name) {
            case "Killer":
                switch (skill.getSkill()) {
                    case "LessProbMiss":
                        this.prob_miss = 10;
                    case "MoreArmor":
                        this.armor = 100;
                    case "MoreDamage":
                        this.damage = 210;
                    case "MoreHealth":
                        this.health = 3200;
                }
                break;
            case "Tank":
                switch (skill.getSkill()) {
                    case "LessProbMiss":
                        this.prob_miss = 20;
                    case "MoreArmor":
                        this.armor = 125;
                    case "MoreDamage":
                        this.damage = 140;
                    case "MoreHealth":
                        this.health = 4100;

                }
                break;

            case "Wizard":
                switch (skill.getSkill()) {
                    case "LessProbMiss":
                        this.prob_miss = 5;
                    case "MoreArmor":
                        this.armor = 90;
                    case "MoreDamage":
                        this.damage = 180;
                    case "MoreHealth":
                        this.health = 3000;

                }
                break;
        }
    }

    @Override
    public int getProb_Miss() {
        return prob_miss;
    }

    @Override
    public void setProb_Miss(int prob_miss) {
        this.prob_miss = prob_miss;
    }

    @Override
    public String toString() {
        return "daño:" + damage + " vida:" + health + " armadura:" + armor + " probabilidad de fallo al atacar:" + prob_miss;
    }

}
