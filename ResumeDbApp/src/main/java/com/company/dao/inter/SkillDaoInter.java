/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.company.dao.inter;

import com.company.entity.Skill;
import java.util.List;

/**
 *
 * @author V&V
 */
public interface SkillDaoInter {

    public List<Skill> getAllSkill();
    public boolean removeUserSkill(int skill_id);
    public boolean insertSkill(Skill skill);
}
