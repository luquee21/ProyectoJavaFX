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
public class Score {
    private String username_player;
    private int victories;
    private int defeats;
    private int total_games;

    public Score(String username_player, int victories, int defeats, int total_games) {
        this.username_player = username_player;
        this.victories = victories;
        this.defeats = defeats;
        this.total_games = total_games;
    }

    public String getUsername_player() {
        return username_player;
    }

    public void setUsername_player(String username_player) {
        this.username_player = username_player;
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
