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
public class SkillDAO {
    private static Skill skill;
    private static Skill skillIA;

    public static Skill getSkillIA() {
        return skillIA;
    }

    public static void setSkillIA(Skill skillIA) {
        SkillDAO.skillIA = skillIA;
    }
    

    public static Skill getSkill() {
        return skill;
    }

    public static void setSkill(Skill skill) {
        SkillDAO.skill = skill;
    }
    
    
    public static List<Skill> selectAll(){
        List<Skill> aux = new ArrayList<>();
        try {
            java.sql.Connection conn = ConnectionUtil.getConnection();
            String q = "SELECT * FROM Skill";
            PreparedStatement ps = conn.prepareStatement(q);
            ResultSet s = ps.executeQuery();
            
            while(s.next()){
                Skill skill = new Skill(s.getString("skill"), s.getString("description"), s.getFloat("modifier"));
                aux.add(skill);
            }
           
        } catch (SQLException ex) {
            Logger.getLogger(SkillDAO.class.getName()).log(Level.SEVERE, null, ex);
        } 
        
        
        return aux;
    }
    
}
