package lk.kingsland.srp.bo;

import lk.kingsland.srp.bo.custom.impl.AddNewStudentBOImpl;
import lk.kingsland.srp.bo.custom.impl.CourseBOimpl;

public class BOFactory {
    static BOFactory boFactory;

    private BOFactory() {
    }

    public static BOFactory getInstance() {
        return (boFactory == null) ? boFactory = new BOFactory() : boFactory;
    }

    public SuperBO getBO(BOType boType) {
        switch (boType) {
            case AddNewStudentBOImpl:
                return new AddNewStudentBOImpl();
            case CourseBOimpl:
                return new CourseBOimpl();
            default:
                return null;
        }
    }

    public enum BOType {
        AddNewStudentBOImpl, CourseBOimpl, RegistrationBOimpl
    }
}
