/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javadbc;

import com.company.dao.inter.UserDaoInter;
import com.company.dao.inter.UserSkillDaoInter;
import com.company.entity.Skill;
import com.company.entity.UserSkill;

/**
 *
 * @author mysqltutorial.org
 */
public class Main {

    public static void main(String[] args) throws Exception {
        UserDaoInter userDao = Context.InstanceUserDao();
        System.out.println();

//        u.setId(4);
//        u.setName("ELNUR");
//        daoint.updateUser(u);
//        List<User> list = daoint.getAll();
//        System.out.println(list);
//        User u = daoint.getById(2);
//        u.setName("NICAT");
//        daoint.updateUser(u);
//          User u = new User(8, "99477777777", "Kenan", "Salahov", "k.salahov@gmail.com");
//          daoint.addUser(u);
        //daoint.removeUser(8);
    }

}
