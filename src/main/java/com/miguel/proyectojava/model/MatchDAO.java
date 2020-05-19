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
public class MatchDAO extends Match {

    public MatchDAO(Match m) {
        cIA = m.cIA;
        cPlayer = m.cPlayer;
        date = m.date;
        player = m.player;
        skill_champion_IA = m.skill_champion_IA;
        skill_champion_player = m.skill_champion_player;
        time = m.time;
        winner = m.winner;
                
    }

    
    
    
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
                match = new Match(s.getString("username_player"), s.getString("champion_player_name"), s.getString("skill_champion_player"), s.getString("champion_IA_name"), s.getString("skill_champion_IA"), s.getString("winner"), s.getDate("date"), s.getTime("time"));
                aux.add(match);        
                
            }
           
        } catch (SQLException ex) {
            Logger.getLogger(PlayerDAO.class.getName()).log(Level.SEVERE, null, ex);
        } 
        
        
        return aux;
    }
    
    public boolean save(){
        boolean result = false;
        int result2;
        try {
            java.sql.Connection csql = ConnectionUtil.getConnection();
            String q = "INSERT INTO juego.Match(`username_player`, `champion_player_name`, `champion_IA_name`, `skill_champion_player`, `skill_champion_IA`, `winner`, `date`, `time`) VALUES(?,?,?,?,?,?,?,?)";
            PreparedStatement ps = csql.prepareStatement(q);
            ps.setString(1, player.getUsername());
            ps.setString(2, cPlayer.getName());
            ps.setString(3, cIA.getName());
            ps.setString(4, skill_champion_player.getSkill());
            ps.setString(5, skill_champion_IA.getSkill());
            ps.setString(6, winner);
            ps.setDate(7, date);
            ps.setTime(8, time);
            result2 = ps.executeUpdate();

            if (result2 > 0) {
                result = true;
            }
        } catch (SQLException ex) {
            Logger.getLogger(PlayerDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

        return result;
    }
    
}
