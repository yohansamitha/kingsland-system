package lk.kingsland.srp.dao.custom.impl;

import lk.kingsland.srp.dao.CrudUtil;
import lk.kingsland.srp.dao.custom.RegistrationDAO;
import lk.kingsland.srp.entity.registration;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class RegistrationDAOimpl implements RegistrationDAO {
    @Override
    public boolean add(registration registration) throws Exception {
        String sql ="insert into registration " +
                "(regDate, registrationFee, studentID, codeID) " +
                "values" +
                "(?,?,?,?)";
        return CrudUtil.executeUpdate(sql,registration.getRegDate(),registration.getRegFee(),registration.getStudentID(),registration.getCodeID());
//        return false;
    }

    @Override
    public boolean delete(String s) throws Exception {
        return false;
    }

    @Override
    public boolean update(registration registration) throws Exception {
        return false;
    }

    @Override
    public registration search(String s) throws Exception {
        return null;
    }

    @Override
    public ArrayList<registration> getAll() throws Exception {
        return null;
    }

}
