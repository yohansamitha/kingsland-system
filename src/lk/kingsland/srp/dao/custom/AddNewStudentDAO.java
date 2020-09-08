package lk.kingsland.srp.dao.custom;

import lk.kingsland.srp.dao.CrudDAO;
import lk.kingsland.srp.entity.student;

import java.sql.SQLException;

public interface AddNewStudentDAO extends CrudDAO<student, String> {
    int getRogCount() throws SQLException, ClassNotFoundException;

    //    ArrayList<student> getAllStudentID() throws Exception;
    boolean validateStudent(String ID) throws Exception;
}
