package lk.kingsland.srp.bo.custom.impl;

import lk.kingsland.srp.bo.custom.AddNewStudentBO;
import lk.kingsland.srp.dao.DAOFactory;
import lk.kingsland.srp.dao.custom.AddNewCourseDAO;
import lk.kingsland.srp.dao.custom.AddNewStudentDAO;
import lk.kingsland.srp.dao.custom.RegistrationDAO;
import lk.kingsland.srp.dao.custom.impl.AddNewStudentDAOImpl;
import lk.kingsland.srp.db.DBConnection;
import lk.kingsland.srp.dto.RegistrationDetailDTO;
import lk.kingsland.srp.dto.courseDTO;
import lk.kingsland.srp.dto.studentDTO;
import lk.kingsland.srp.entity.course;
import lk.kingsland.srp.entity.registration;
import lk.kingsland.srp.entity.student;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

public class AddNewStudentBOImpl implements AddNewStudentBO {
    //    public static class AddNewCourseBOimpl implements AddNewCourseBO {
    AddNewStudentDAO addNewStudentDAO = (AddNewStudentDAOImpl) DAOFactory.getInstance().getDAO(DAOFactory.DAOType.AddNewStudentDAOImpl);
    AddNewCourseDAO addNewCourseDAO = (AddNewCourseDAO) DAOFactory.getInstance().getDAO(DAOFactory.DAOType.AddNewCourseDAOimpl);
    RegistrationDAO registrationDAO = (RegistrationDAO) DAOFactory.getInstance().getDAO(DAOFactory.DAOType.RegistrationDAOimpl);

    @Override
    public boolean setRegister(RegistrationDetailDTO registrationDetailDTO) throws Exception {
        Connection connection = DBConnection.getInstance().getConnection();
        connection.setAutoCommit(false);
        registration registration = new registration(registrationDetailDTO.getRegistrationDTO().getRegDate(),
                registrationDetailDTO.getRegistrationDTO().getRegFee(),
                registrationDetailDTO.getRegistrationDTO().getStudentID(),
                registrationDetailDTO.getRegistrationDTO().getCodeID());

        student student = new student(registrationDetailDTO.getStudentID(), registrationDetailDTO.getStudentName(), registrationDetailDTO.getAddress(),
                registrationDetailDTO.getContact(), registrationDetailDTO.getDob(), registrationDetailDTO.getGender());

//        boolean addStudent = addNewStudentDAO.add();

//        if (addStudent) {
//            boolean addReg = registrationDAO.add(registration);
//            if (addReg) {
//                connection.commit();
//            }
//        }
//        return false;

        try {
            if (addNewStudentDAO.validateStudent(registrationDetailDTO.getStudentID())) {
                if (registrationDAO.add(registration)) {
                    connection.commit();
                    return true;
                }
            } else {
                if (addNewStudentDAO.add(student)) {
                    if (registrationDAO.add(registration)) {
                        connection.commit();
                        return true;
                    }
                }
                connection.rollback();
                return false;
            }
            return false;
        } finally {
            connection.setAutoCommit(true);
        }

    }

    @Override
    public ArrayList<studentDTO> getAllStudent() throws Exception {
        ArrayList<studentDTO> allStudentID = new ArrayList<>();
        ArrayList<student> allStudentID1 = addNewStudentDAO.getAll();
        for (lk.kingsland.srp.entity.student student : allStudentID1) {
            allStudentID.add(new studentDTO(student.getStudentID(), student.getStudentName(), student.getAddress(), student.getContact(), student.getDob(), student.getGender()));
        }
        return allStudentID;
    }

    @Override
    public String getLastID() throws SQLException, ClassNotFoundException {
        int reg = addNewStudentDAO.getRogCount();
        if (reg < 9) {
            return ("S00" + (reg + 1));
        } else if (reg < 99) {
            return ("S0" + (reg + 1));
        } else {
            return ("S" + (reg + 1));
        }
    }

    @Override
    public ArrayList<courseDTO> getAllCourse() throws Exception {
        ArrayList<course> all = addNewCourseDAO.getAll();
        ArrayList<courseDTO> allCourse = new ArrayList<>();
        for (course courses : all) {
            allCourse.add(new courseDTO(courses.getCodeID(), courses.getCourseName(), courses.getDuration(), courses.getRegistrationFee(), courses.getIntake()));
        }
        return allCourse;
    }

    @Override
    public String getCourseID(String value) throws Exception {
        course search = addNewCourseDAO.search(value);
        return search.getCodeID();
    }


//    }
}
