/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.miguel.proyectojava.model;

import com.miguel.proyectojava.utils.ConnectionUtil;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author migue
 */
public class PlayerDAO extends Player{
    
    public PlayerDAO(String name, String lastname, String email, String username, String password) {
        super(name, lastname, email, username, password);

    }
    
    //constructor para el save y delete
    public PlayerDAO(Player player){
        name = player.name;
        lastname = player.lastname;
        email = player.email;
        username = player.username;
        password = player.password;
       
    }
    private static Player IA;

    public static Player getIA() {
        return IA;
    }

    public static void setIA(Player IA) {
        PlayerDAO.IA = IA;
    }
    
    private static Player p;

    public static Player getP() {
        return p;
    }

    public static void setP(Player p) {
        PlayerDAO.p = p;
    }
    
    /**
     * Inserta en la base de datos la informacion del jugador
     * @param name nombre del jugador
     * @param lastname apellidos del jugador
     * @param email email del jugador
     * @param username usuario del jugador
     * @param password contraseña del jugador
     * @return devuelve cierto si se ha guardado los datos del jugador
     */
    public static boolean signup(String name, String lastname, String email, String username, String password) {
        boolean result = false;

        try {
            java.sql.Connection conn = ConnectionUtil.getConnection();
            String q = "INSERT INTO Player VALUES(?,?,?,?,?)";
            PreparedStatement ps = conn.prepareStatement(q);
            ps.setString(1, name);
            ps.setString(2, lastname);
            ps.setString(3, email);
            ps.setString(4, username);
            ps.setString(5, password);
            int rs = ps.executeUpdate();
            if (rs > 0) {
                result = true;
            }

        } catch (SQLException ex) {
            Logger.getLogger(PlayerDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return result;
    }
    
    /**
     * Recoge todos los datos de un jugador 
     * @param username usuario del jugador
     * @return devuelve el jugador que se pasa por parametro
     */
    public static Player selectAllFromPlayerLogin(String username) {
        Player result = null;

        try {
            java.sql.Connection conn = ConnectionUtil.getConnection();
            String q = "SELECT * FROM Player WHERE username = ? ";
            PreparedStatement ps = conn.prepareStatement(q);
            ps.setString(1, username);
            ResultSet rs = ps.executeQuery();

            if (rs != null && rs.next()) {
                result = new Player(rs.getString("name"), rs.getString("last_name"), rs.getString("email"), rs.getString("username"), rs.getString("password"));

            }

        } catch (SQLException ex) {
            Logger.getLogger(PlayerDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return result;
    }
    
    /**
     * Comprueba si el jugador existe
     * @param username reciobe el usuario del jugador
     * @return devuelve cierto si el usuario ya existe
     */
    public static boolean isAvailablePlayer(String username) {
        boolean result = false;
        try {
            java.sql.Connection conn = ConnectionUtil.getConnection();
            String q = "SELECT username FROM Player where username=?";
            PreparedStatement ps = conn.prepareStatement(q);
            ps.setString(1, username);
            ResultSet rs = ps.executeQuery();

            if (rs != null) {
                if (rs.next()) {
                    result = true;
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(PlayerDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

        return result;
    }
    
    /**
     * actualiza la informacion del jugador
     * @return devuelve cierto si ha actualizado la informacion
     */
    public boolean save() {
        boolean result = false;
        int result2;
        try {
            java.sql.Connection csql = ConnectionUtil.getConnection();
            String q = "UPDATE Player SET name = ?, last_name = ?, email = ?, username = ?, password = ? WHERE username = ?";
            PreparedStatement ps = csql.prepareStatement(q);
            ps.setString(1, name);
            ps.setString(2, lastname);
            ps.setString(3, email);
            ps.setString(4, username);
            ps.setString(5, password);
            ps.setString(6, p.getUsername());
            result2 = ps.executeUpdate();

            if (result2 > 0) {
                result = true;
                //Guardo la nueva informacion del perfil
                p = selectAllFromPlayerLogin(username);
            }
        } catch (SQLException ex) {
            Logger.getLogger(PlayerDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

        return result;
    }
    /**
     * Borra el usuario 
     * @return devuelve true si se ha borrado el usuario
     */
    public boolean delete() {
        boolean result = false;
        try {
            java.sql.Connection conn = ConnectionUtil.getConnection();
            String q = "DELETE FROM Player WHERE username=?";
            PreparedStatement ps = conn.prepareStatement(q);
            ps.setString(1, username);
            int rs = ps.executeUpdate();
            if (rs > 0) {
                result = true;
                //al borrar de la bd lo borro tambien de java
                p = null;
            }

        } catch (SQLException ex) {
            Logger.getLogger(PlayerDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

        return result;

    }

}
