package lk.kingsland.srp.dao;

import lk.kingsland.srp.dao.custom.impl.AddNewCourseDAOimpl;
import lk.kingsland.srp.dao.custom.impl.AddNewStudentDAOImpl;
import lk.kingsland.srp.dao.custom.impl.QueryDAOimpl;
import lk.kingsland.srp.dao.custom.impl.RegistrationDAOimpl;

public class DAOFactory {
    public static DAOFactory daoFactory;

    private DAOFactory() {
    }

    public static DAOFactory getInstance() {
        return (daoFactory == null) ? daoFactory = new DAOFactory() : daoFactory;
    }

    public SuperDAO getDAO(DAOType daoType) {
        switch (daoType) {
            case AddNewStudentDAOImpl:
                return new AddNewStudentDAOImpl();
            case AddNewCourseDAOimpl:
                return new AddNewCourseDAOimpl();
            case RegistrationDAOimpl:
                return new RegistrationDAOimpl();
            case QueryDAOimpl:
                return new QueryDAOimpl();
            default:
                return null;
        }
    }

    public enum DAOType {
        AddNewStudentDAOImpl, QueryDAOimpl,AddNewCourseDAOimpl,RegistrationDAOimpl
    }
}
