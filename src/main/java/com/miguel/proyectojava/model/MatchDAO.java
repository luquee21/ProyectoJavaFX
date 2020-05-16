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
public class MatchDAO {

    
    
    
    public static List<Match> selectAll(String username){
        List<Match> aux = new ArrayList<>();
        Match match;
        try {
            java.sql.Connection conn = ConnectionUtil.getConnection();
            String q = "SELECT * FROM juego.Match where username_player=?";
            PreparedStatement ps = conn.prepareStatement(q);
            ps.setString(1, username);
            ResultSet s = ps.executeQuery();
            
            while(s.next()){
                match = new Match(s.getInt("id"),s.getString("username_player"), s.getString("champion_player_name"), s.getString("skill_champion_player"), s.getString("champion_IA_name"), s.getString("skill_champion_IA"), s.getString("winner"), s.getDate("date"), s.getTime("time"));
                aux.add(match);        
                
            }
           
        } catch (SQLException ex) {
            Logger.getLogger(PlayerDAO.class.getName()).log(Level.SEVERE, null, ex);
        } 
        
        
        return aux;
    }
    
}
