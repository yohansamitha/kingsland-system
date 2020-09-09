package lk.kingsland.srp.dao.custom.impl;

import lk.kingsland.srp.dao.CrudUtil;
import lk.kingsland.srp.dao.custom.AddNewCourseDAO;
import lk.kingsland.srp.entity.course;
import lk.kingsland.srp.entity.student;

import java.sql.ResultSet;
import java.util.ArrayList;

public class AddNewCourseDAOimpl implements AddNewCourseDAO {
    @Override
    public boolean add(course course) throws Exception {
        String sql ="INSERT INTO course VALUES(?,?,?,?,?)";
        return CrudUtil.executeUpdate(sql,course.getCodeID(),course.getCourseName(),course.getDuration(),course.getRegistrationFee(),course.getIntake());
    }

    @Override
    public boolean delete(String s) throws Exception {
        return false;
    }

    @Override
    public boolean update(course course) throws Exception {
        return false;
    }

    @Override
    public course search(String s) throws Exception {
        ResultSet rst = CrudUtil.executeQuery("select * from course where courseName=?", s);
        while(rst.next())
            return new course(rst.getString(1),
                    rst.getString(2),
                    rst.getString(3),
                    rst.getDouble(4),
                    rst.getString(5));
        return null;
    }

    @Override
    public ArrayList<course> getAll() throws Exception {
        String sql = "SELECT * from course";
        ArrayList<course> course = new ArrayList<>();
        ResultSet resultSet = CrudUtil.executeQuery(sql);
        while (resultSet.next()){
            course.add(new course(resultSet.getString(1),resultSet.getString(2),resultSet.getString(3),resultSet.getDouble(4),resultSet.getString(5)));
        }
        return course;
    }
}
