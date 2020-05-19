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
    private int miss_attack;
    private int n_attacks;

    public Champion(String name, int damage, int health, int armor, int prob_miss) {
        this.name = name;
        this.damage = damage;
        this.health = health;
        this.armor = armor;
        this.prob_miss = prob_miss;
        this.miss_attack = 0;
        this.n_attacks = 0;
    }

    public Champion(String name) {
        this.name = name;
    }

    public int getN_attacks() {
        return n_attacks;
    }

    public void setN_attacks(int n_attacks) {
        this.n_attacks = n_attacks;
    }

    public int getMiss_attack() {
        return miss_attack;
    }

    public void setMiss_attack(int miss_attack) {
        this.miss_attack = miss_attack;
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
        switch (skill.getSkill()) {
            case "LessProbMiss":
                this.prob_miss = (int) (prob_miss * skill.getModifier());
                break;
            case "MoreArmor":
                this.armor = (int) (armor * skill.getModifier());
                break;
            case "MoreDamage":
                this.damage = (int) (damage * skill.getModifier());
                break;
            case "MoreHealth":
                this.health = (int) (health * skill.getModifier());
                break;
            default:
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
        return "[" + name + "]" + ", daño:" + damage + ", vida:" + health + ", armadura:" + armor + ", % fallo de ataque:" + prob_miss;
    }

}
