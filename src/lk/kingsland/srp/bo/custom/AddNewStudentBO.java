package lk.kingsland.srp.bo.custom;

import lk.kingsland.srp.bo.SuperBO;
import lk.kingsland.srp.dto.RegistrationDetailDTO;
import lk.kingsland.srp.dto.courseDTO;
import lk.kingsland.srp.dto.studentDTO;

import java.sql.SQLException;
import java.util.ArrayList;

public interface AddNewStudentBO extends SuperBO {
    ArrayList<studentDTO> getAllStudent() throws Exception;

    String getLastID() throws SQLException, ClassNotFoundException;

    ArrayList<courseDTO> getAllCourse() throws Exception;

    String getCourseID(String value) throws Exception;

    boolean setRegister(RegistrationDetailDTO registrationDetailDTO) throws Exception;
}
