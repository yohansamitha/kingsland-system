package lk.kingsland.srp.bo.custom.impl;

import lk.kingsland.srp.bo.custom.CourseBO;
import lk.kingsland.srp.dao.DAOFactory;
import lk.kingsland.srp.dao.custom.AddNewCourseDAO;
import lk.kingsland.srp.dto.courseDTO;
import lk.kingsland.srp.entity.course;

public class CourseBOimpl implements CourseBO {
    AddNewCourseDAO addNewCourseDAO = (AddNewCourseDAO) DAOFactory.getInstance().getDAO(DAOFactory.DAOType.AddNewCourseDAOimpl);
    @Override
    public boolean addCourse(courseDTO dto) throws Exception {
        course course = new course(dto.getCodeID(),dto.getCourseName(),dto.getDuration(),dto.getRegistrationFee(),dto.getIntake());
        return addNewCourseDAO.add(course);
    }
}
