/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.company.dao.impl;

import com.company.dao.inter.AbstractDAO;
import com.company.dao.inter.CountryDaoInter;
import com.company.entity.Country;
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
public class CountryDaoImpl extends AbstractDAO implements CountryDaoInter {

    private Country getAllCountry(ResultSet rs) throws Exception {
        int countryId = rs.getInt("id");
        String name = rs.getString("name");
        String nationality = rs.getString("nationality");

        return new Country(countryId,name,nationality );
    }

    @Override
    public  List<Country> getAllCountry() {
        List<Country> list = new ArrayList();
        try (Connection c = connect();) {
            PreparedStatement stmt = c.prepareStatement("select * from country;");
            stmt.execute();
            ResultSet rs = stmt.getResultSet();

            while (rs.next()) {
                Country u = getAllCountry(rs);
                list.add(u);
            }
        } catch (Exception ex) {
            Logger.getLogger(CountryDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }

}
