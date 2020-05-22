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
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author migue
 */
public class ScoreDAO {
    /**
     * Guarda en la base de datos una tabla de la puntuacion del jugador con valores a 0 para su posterior actualizacion
     * @param username recibe el usuario
     * @return devuelve cierto si ha podido guardar la tabla puntuacion en la base de datos
     */
    
    public static boolean createScore(String username){
       boolean result = false;

        try {
            java.sql.Connection conn = ConnectionUtil.getConnection();
            String q = "INSERT INTO Score (username_player, victories, defeats, total_games) VALUES(?,?,?,?)";
            PreparedStatement ps = conn.prepareStatement(q);
            ps.setString(1, username);
            ps.setInt(2, 0);
            ps.setInt(3, 0);
            ps.setInt(4, 0);
            int rs = ps.executeUpdate();
            if (rs > 0) {
                result = true;
            }

        } catch (SQLException ex) {
            Logger.getLogger(ScoreDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return result;
    }
    
    /**
     * Recibe una lista con todas las puntuaciones de la base de datos
     * @return devuelve una lista de todas las puntuaciones
     */
    public static List<Score> selectAll(){
        List<Score> aux = new ArrayList<>();
        Score score = null;
        try {
            java.sql.Connection conn = ConnectionUtil.getConnection();
            String q = "SELECT * FROM Score";
            PreparedStatement ps = conn.prepareStatement(q);
            ResultSet s = ps.executeQuery();
            
            while(s.next()){
                score = new Score(s.getString("username_player"),s.getInt("victories"), s.getInt("defeats"),s.getInt("total_games"));
                aux.add(score);
            }
           
        } catch (SQLException ex) {
            Logger.getLogger(ScoreDAO.class.getName()).log(Level.SEVERE, null, ex);
        } 
        
        
        return aux;
    }
    /**
     * Recibe una puntuacion del jugador para su posterior creacion
     * @param username usuario del jugador
     * @return devuelve la puntuacion creada del jugador
     */
    public static Score selectAllFromPlayer(String username){
        Score score = null;
        try {
            java.sql.Connection conn = ConnectionUtil.getConnection();
            String q = "SELECT * FROM Score where username_player=?";
            PreparedStatement ps = conn.prepareStatement(q);
            ps.setString(1, username);
            ResultSet s = ps.executeQuery();
            
            while(s.next()){
                score = new Score(s.getString("username_player"),s.getInt("victories"), s.getInt("defeats"),s.getInt("total_games"));
            }
           
        } catch (SQLException ex) {
            Logger.getLogger(ScoreDAO.class.getName()).log(Level.SEVERE, null, ex);
        } 
        
        
        return score;
    }
    
    /**
     * Actualiza la informacion de la puntuacion del jugador
     * @param username recibe el usuario del jugador
     * @param victories recibe las victorias del jugador
     * @param defeats recibe las derrotas del jugador
     * @param total_games recibe las partidas totales del jugador
     * @return devuelve cierto si ha podido actualizar la informacion del jugador
     */
    public static boolean update(String username, int victories, int defeats, int total_games){
        int result2 = 0;
        boolean result = false;
        try {
            java.sql.Connection conn = ConnectionUtil.getConnection();
            String q = "UPDATE Score SET victories=?,defeats=?,total_games=? where username_player=?";
            PreparedStatement ps = conn.prepareStatement(q);
            ps.setInt(1,victories);
            ps.setInt(2,defeats);
            ps.setInt(3,total_games);
            ps.setString(4, username);
            result2 = ps.executeUpdate();
            
            if(result2 > 0){
                result = true;
            }
            
           
        } catch (SQLException ex) {
            Logger.getLogger(ScoreDAO.class.getName()).log(Level.SEVERE, null, ex);
        } 
        
        
        return result;
    }
    
    
}
