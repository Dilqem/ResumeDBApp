/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javadbc;

import com.company.dao.impl.CountryDaoImpl;
import com.company.dao.impl.EmploymentHistoryDaoImpl;
import com.company.dao.impl.SkillDaoImpl;
import com.company.dao.impl.UserDaoImpl;
import com.company.dao.impl.UserSkillDaoImpl;
import com.company.dao.inter.CountryDaoInter;
import com.company.dao.inter.EmploymentHistoryDaoInter;
import com.company.dao.inter.SkillDaoInter;
import com.company.dao.inter.UserDaoInter;
import com.company.dao.inter.UserSkillDaoInter;

/**
 *
 * @author V&V
 */
public class Context {

    public static UserDaoInter InstanceUserDao() {
        return new UserDaoImpl();
    }
    
    public static UserSkillDaoInter InstanceUserSkillDao() {
        return new UserSkillDaoImpl();
    }
    
    public static EmploymentHistoryDaoInter InstanceEmploymentHistoryDao() {
        return new EmploymentHistoryDaoImpl();
    }
    
    public static CountryDaoInter InstanceCountryDao() {
        return new CountryDaoImpl();
    }
    
    public static SkillDaoInter InstanceSkillDao() {
        return new SkillDaoImpl();
    }
}
