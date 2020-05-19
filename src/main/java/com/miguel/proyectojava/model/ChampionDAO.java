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
public class ChampionDAO {
    private static Champion champion;
    private static Champion championIA;

    public static Champion getChampionIA() {
        return championIA;
    }

    public static void setChampionIA(Champion championIA) {
        ChampionDAO.championIA = championIA;
    }
    

    public static Champion getChampion() {
        return champion;
    }

    public static void setChampion(Champion champion) {
        ChampionDAO.champion = champion;
    }
    
    public static List<Champion> selectAll(){
        List<Champion> aux = new ArrayList<>();
        try {
            java.sql.Connection conn = ConnectionUtil.getConnection();
            String q = "SELECT * FROM Champion";
            PreparedStatement ps = conn.prepareStatement(q);
            ResultSet s = ps.executeQuery();
            
            while(s.next()){
                Champion c = new Champion(s.getString("name"), s.getInt("damage"),s.getInt("health"),s.getInt("armor"),s.getInt("prob_miss"));
                aux.add(c);
            }
           
        } catch (SQLException ex) {
            Logger.getLogger(ChampionDAO.class.getName()).log(Level.SEVERE, null, ex);
        } 
        
        
        return aux;
    }
    
    
    
}
