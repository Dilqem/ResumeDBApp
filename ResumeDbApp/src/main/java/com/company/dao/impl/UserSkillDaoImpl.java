/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.company.dao.impl;

import com.company.entity.Skill;
import com.company.entity.User;
import com.company.entity.UserSkill;
import com.company.dao.inter.AbstractDAO;
import com.company.dao.inter.UserSkillDaoInter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author V&V
 */
public class UserSkillDaoImpl extends AbstractDAO implements UserSkillDaoInter {

    private UserSkill getUserSkill(ResultSet rs) throws Exception {
        int userSkillId = rs.getInt("user_skill_id");
        int userId = rs.getInt("id");
        int skillId = rs.getInt("skill_id");
        String skillName = rs.getString("skill_name");
        int power = rs.getInt("power");

        return new UserSkill(userSkillId, new Skill(skillId, skillName), new User(userId), power);
    }

    @Override
    public List<UserSkill> getAllSkillByUserId(int userId) {
        List<UserSkill> list = new ArrayList();
        try (Connection c = connect();) {
            PreparedStatement stmt = c.prepareStatement("select us.id as user_skill_id, "
                    + "u.*, us.skill_id, us.power, s.name as skill_name "
                    + "from user_skill us left join user u on us.user_id = u.id "
                    + "left join skill s on us.skill_id  = s.id "
                    + "where us.user_id = ?;");
            stmt.setInt(1, userId);
            stmt.execute();
            ResultSet rs = stmt.getResultSet();

            while (rs.next()) {
                UserSkill u = getUserSkill(rs);
                list.add(u);
            }
        } catch (Exception ex) {
            Logger.getLogger(UserSkillDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }

    @Override
    public boolean removeUserSkill(int userSkillId) {
        try (Connection c = connect();) {
            PreparedStatement pstmt = c.prepareStatement("delete from user_skill where id=?");
            pstmt.setInt(1, userSkillId);
            return pstmt.execute();

        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public boolean addUserSkill(UserSkill userskill) {
        boolean result = true;
        try(Connection conn = connect();) {
            PreparedStatement stmt = conn.prepareStatement("insert into "
                    + "user_skill( user_id, skill_id, power) value(?,?,?)");
            stmt.setInt(1, userskill.getUser().getId());
            stmt.setInt(2, userskill.getSkill().getId());
            stmt.setInt(3, userskill.getPower());
            stmt.execute();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return result;
    }

    @Override
    public boolean updateUserSkill(UserSkill userSkill) {
        boolean result = true;
        try(Connection conn = connect();) {
            PreparedStatement stmt = conn.prepareStatement("update user_skill set skill_id=?,user_id=?,power=? where id=?");
            stmt.setInt(1, userSkill.getSkill().getId());
            stmt.setInt(2, userSkill.getUser().getId());
            stmt.setInt(3, userSkill.getPower());
            stmt.setInt(4, userSkill.getId());
            stmt.execute();
        } catch (Exception ex) {
            Logger.getLogger(UserSkillDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
            result = false;
        }
        return result;
    }

}
