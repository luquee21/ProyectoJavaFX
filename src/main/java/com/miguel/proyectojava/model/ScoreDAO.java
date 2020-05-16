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
            Logger.getLogger(PlayerDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return result;
    }
    
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
            Logger.getLogger(PlayerDAO.class.getName()).log(Level.SEVERE, null, ex);
        } 
        
        
        return aux;
    }
    
}
