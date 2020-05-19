/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.miguel.proyectojava.model;

import org.apache.commons.codec.digest.DigestUtils;

/**
 *
 * @author migue
 */
public class Player extends Person {
    protected String username;
    protected String password;

    public Player(String name, String lastname, String email, String username, String password) {
        super(name, lastname, email);
        this.username = username;
        this.password = password;
    }

    public Player(String username) {
        super("", "", "");
        this.username = username;
    }

    public Player() {
    }
    
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
    
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = DigestUtils.sha512Hex(password);
    }
    
 
     @Override
    public String toString() {
        return super.toString() + "Jugador{" + "usuario=" + username +'}';
    }
}
