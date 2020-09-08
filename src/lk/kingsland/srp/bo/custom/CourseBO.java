package lk.kingsland.srp.bo.custom;

import lk.kingsland.srp.bo.SuperBO;
import lk.kingsland.srp.dto.courseDTO;

public interface CourseBO extends SuperBO {
    boolean addCourse(courseDTO dto) throws Exception;
}
