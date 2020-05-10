/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.miguel.proyectojava.model;

import com.miguel.proyectojava.model.Player;

/**
 *
 * @author migue
 */
public class Score {
    private int id;
    private Player pUsername;
    private int victories;
    private int defeats;
    private int total_games;

    public Score(Player pUsername, int victories, int defeats, int total_games) {
        this.pUsername = pUsername;
        this.victories = victories;
        this.defeats = defeats;
        this.total_games = total_games;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Player getpUsername() {
        return pUsername;
    }

    public void setpUsername(Player pUsername) {
        this.pUsername = pUsername;
    }

    public int getVictories() {
        return victories;
    }

    public void setVictories(int victories) {
        this.victories = victories;
    }

    public int getDefeats() {
        return defeats;
    }

    public void setDefeats(int defeats) {
        this.defeats = defeats;
    }

    public int getTotal_games() {
        return total_games;
    }

    public void setTotal_games(int total_games) {
        this.total_games = total_games;
    }
    
    
}
