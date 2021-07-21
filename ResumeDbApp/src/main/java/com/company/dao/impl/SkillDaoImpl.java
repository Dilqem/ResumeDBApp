/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.company.dao.impl;

import com.company.entity.Skill;
import com.company.dao.inter.AbstractDAO;
import com.company.dao.inter.SkillDaoInter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author V&V
 */
public class SkillDaoImpl extends AbstractDAO implements SkillDaoInter {

    private Skill getSkill(ResultSet rs) throws Exception {
        int skillId = rs.getInt("id");
        String skillName = rs.getString("name");

        return new Skill(skillId, skillName);
    }

    @Override
    public List<Skill> getAllSkill() {
        List<Skill> list = new ArrayList();
        try (Connection c = connect();) {
            PreparedStatement stmt = c.prepareStatement("select * from skill;");
            stmt.execute();
            ResultSet rs = stmt.getResultSet();

            while (rs.next()) {
                Skill u = getSkill(rs);
                list.add(u);
            }
        } catch (Exception ex) {
            Logger.getLogger(SkillDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }

    @Override
    public boolean removeUserSkill( int skillId) {
        try(Connection c = connect();) {
          PreparedStatement pstmt  = c.prepareStatement("delete from user_skill where id=?"); 
          pstmt.setInt(1, skillId);
          return pstmt.execute();
           
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public boolean insertSkill(Skill skill) {
        boolean result=true;
        try(Connection conn = connect();) {
           PreparedStatement stmt = conn.prepareStatement("insert into skill(name) values(?)",
                   Statement.RETURN_GENERATED_KEYS);
            stmt.setString(1, skill.getName());
            stmt.execute();
            
            ResultSet generatedKeys = stmt.getGeneratedKeys(); 
            if (generatedKeys.next()) {
                skill.setId(generatedKeys.getInt(1));
            }    
        
        
        } catch (Exception e) {
            e.getStackTrace();
        }
        
        return result;
    }

}
