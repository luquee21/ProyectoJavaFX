/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.miguel.proyectojava.model;

import java.sql.Date;
import java.sql.Time;





/**
 *
 * @author migue
 */
public class Match {
 
    private int id;
    private Player player;
    private Champion cPlayer;
    private Champion cIA;
    private String winner;
    private Date date;
    private Time time;
    private Skill skill_champion_player;
    private Skill skill_champion_IA;

    public Match(int id, Player player, Champion cPlayer, Champion cIA, Date date,Time time, Skill skill_champion_player, Skill skill_champion_IA) {
        this.id = id;
        this.player = player;
        this.cPlayer = cPlayer;
        this.cIA = cIA;
        this.date = date;
        this.time = time;
        this.skill_champion_player = skill_champion_player;
        this.skill_champion_IA = skill_champion_IA;
    }
    
    public Match(int id, String username, String champion_name, String skill, String Champion_name_IA, String skill_IA, String winner, Date date, Time time){
        this.id = id;
        this.player = new Player(username);
        this.cPlayer = new Champion(champion_name);
        this.skill_champion_player = new Skill(skill);
        this.cIA = new Champion(Champion_name_IA);
        this.skill_champion_IA = new Skill(skill_IA);
        this.winner = winner;
        this.date = date;
        this.time = time;
    }
        
    

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public Champion getcPlayer() {
        return cPlayer;
    }

    public void setcPlayer(Champion cPlayer) {
        this.cPlayer = cPlayer;
    }

    public Champion getcIA() {
        return cIA;
    }

    public void setcIA(Champion cIA) {
        this.cIA = cIA;
    }

    public String getWinner() {
        return winner;
    }

    public void setWinner(String winner) {
        this.winner = winner;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Time getTime() {
        return time;
    }

    public void setTime(Time time) {
        this.time = time;
    }

    

    public Skill getSkill_champion_player() {
        return skill_champion_player;
    }

    public void setSkill_champion_player(Skill skill_champion_player) {
        this.skill_champion_player = skill_champion_player;
    }

    public Skill getSkill_champion_IA() {
        return skill_champion_IA;
    }

    public void setSkill_champion_IA(Skill skill_champion_IA) {
        this.skill_champion_IA = skill_champion_IA;
    }
    
    
    
    
}
