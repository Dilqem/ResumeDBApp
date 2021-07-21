/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.company.dao.impl;

import com.company.entity.Country;
import com.company.entity.User;
import com.company.dao.inter.AbstractDAO;
import com.company.dao.inter.UserDaoInter;
import java.sql.Connection;
import java.sql.Date;
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
public class UserDaoImpl extends AbstractDAO implements UserDaoInter {

    private User getUser(ResultSet rs) throws Exception {
        int id = rs.getInt("id");
        String name = rs.getString("name");
        String surname = rs.getString("surname");
        String phone = rs.getString("phone");
        String email = rs.getString("email");
        String profileDesc = rs.getString("profile_description");
        String address = rs.getString("address");
        int nationalityId = rs.getInt("nationality_id");
        int birthpaceId = rs.getInt("birthplace_id");
        String nationalityStr = rs.getString("nationality");
        String birthplaceStr = rs.getString("birthplace");
        Date birthDate = rs.getDate("birthdate");

        Country nationality = new Country(nationalityId, null, nationalityStr);
        Country birthplace = new Country(birthpaceId, birthplaceStr, null);

        User u = new User(id, phone, name, surname, email, profileDesc, address, nationality, birthplace, birthDate);
        return u;
    }
    
    private User getUserSimple(ResultSet rs) throws Exception {
        int id = rs.getInt("id");
        String name = rs.getString("name");
        String surname = rs.getString("surname");
        String phone = rs.getString("phone");
        String email = rs.getString("email");
        String profileDesc = rs.getString("profile_description");
        String address = rs.getString("address");
        int nationalityId = rs.getInt("nationality_id");
        int birthpaceId = rs.getInt("birthplace_id");
        Date birthDate = rs.getDate("birthdate");


        User u = new User(id, phone, name, surname, email, profileDesc, address, null, null, birthDate);
        return u;
    }

    @Override
    public List<User> getAll(String name, String surname, Integer nationalityId) {
        List<User> list = new ArrayList();
        try (Connection c = connect();) {

            String sql = "select  u.*, n.nationality, c.name as birthplace from user as u "
                    + "left join country as n on u.nationality_id=n.id "
                    + "left join country as c on u.birthplace_id=c.id where 1=1 ";

            if (name != null && !name.trim().isEmpty()) {
                sql += " and u.name=? ";
            }
            if (surname != null && !name.trim().isEmpty()) {
                sql += " and u.surname=? ";
            }
            if (nationalityId != null) {
                sql += " and u.nationality_id=? ";
            }
            PreparedStatement stmt = c.prepareStatement(sql);
            int i = 1;
            if (name != null && !name.trim().isEmpty()) {
                stmt.setString(i, name);
                i++;
            }
            if (surname != null && !surname.trim().isEmpty()) {
                stmt.setString(i, surname);
                i++;
            }
            if (nationalityId != null) {
                stmt.setInt(i, nationalityId);
            }
            stmt.execute();
            ResultSet rs = stmt.getResultSet();

            while (rs.next()) {
                User u = getUser(rs);
                list.add(u);
            }
        } catch (Exception ex) {
            Logger.getLogger(UserDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }

    @Override
    public User findByEmailAndPassword(String email, String password) {
        User result = null;
        try (Connection c = connect();) {
            PreparedStatement stmt = c.prepareStatement("select * from user where email=? and password=?");
            stmt.setString(1, email);
            stmt.setString(2, password);
            ResultSet rs = stmt.executeQuery();
            
            while (rs.next()) {
               result = getUserSimple(rs);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public boolean updateUser(User u) {
        try (Connection c = connect();) {
            //Statement stmt = c.createStatement();
            PreparedStatement stmt = c.prepareStatement("update resume.user set name=?, surname=?, email=?, "
                    + "phone=?, profile_description=?, address=?, birthdate=?,birthplace_id=? where id=?");
            stmt.setString(1, u.getName());
            stmt.setString(2, u.getSurname());
            stmt.setString(3, u.getEmail());
            stmt.setString(4, u.getPhone());
            stmt.setString(5, u.getProfileDescription());
            stmt.setString(6, u.getAddress());
            stmt.setDate(7, u.getBirthDate());
            stmt.setInt(8, u.getBirthPlace().getId());
            stmt.setInt(9, u.getId());
            return stmt.execute();
        } catch (Exception ex) {
            Logger.getLogger(UserDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }

    @Override
    public boolean removeUser(int id) {
        try (Connection c = connect();) {
            Statement stmt = c.createStatement();
            return stmt.execute("delete from resume  where id =" + id);
        } catch (Exception ex) {
            Logger.getLogger(UserDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }

    @Override
    public User getById(int userId) {
        User result = null;
        try (Connection c = connect();) {
            Statement stmt = c.createStatement();
            stmt.execute("select "
                    + " u.*, "
                    + "n.nationality, "
                    + "c.name as birthplace "
                    + "from user as u "
                    + "left join country as n on u.nationality_id=n.id "
                    + "left join country as c on u.birthplace_id=c.id where u.id =" + userId);
            ResultSet rs = stmt.getResultSet();

            while (rs.next()) {
                result = getUser(rs);
            }
        } catch (Exception ex) {
            Logger.getLogger(UserDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return result;
    }

    @Override
    public boolean addUser(User u) {
        try (Connection c = connect();) {
            //Statement stmt = c.createStatement();
            PreparedStatement stmt = c.prepareStatement("insert into resume(name,surname,email,"
                    + "profile_description,phone,address,id) values(?,?,?,?,?,?,?)");
            stmt.setString(1, u.getName());
            stmt.setString(2, u.getSurname());
            stmt.setString(3, u.getEmail());
            stmt.setString(4, u.getProfileDescription());
            stmt.setString(5, u.getPhone());
            stmt.setString(6, u.getAddress());
            stmt.setInt(7, u.getId());
            return stmt.execute();
        } catch (Exception ex) {
            Logger.getLogger(UserDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }

}
