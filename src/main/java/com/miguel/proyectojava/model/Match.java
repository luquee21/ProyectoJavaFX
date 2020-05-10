/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.miguel.proyectojava.model;

import com.miguel.proyectojava.model.Champion;
import com.miguel.proyectojava.model.Player;
import com.miguel.proyectojava.model.Skill;
import java.time.LocalDateTime;

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
    private LocalDateTime date;
    private Skill skill_champion_player;
    private Skill skill_champion_IA;

    public Match(Player player, Champion cPlayer, Champion cIA, Skill skill_champion_player, Skill skill_champion_IA) {
        this.player = player;
        this.cPlayer = cPlayer;
        this.cIA = cIA;
        this.date = LocalDateTime.now();
        this.skill_champion_player = skill_champion_player;
        this.skill_champion_IA = skill_champion_IA;
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

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
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
