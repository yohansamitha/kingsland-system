package lk.kingsland.srp.controller;

import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import lk.kingsland.srp.bo.BOFactory;
import lk.kingsland.srp.bo.custom.CourseBO;
import lk.kingsland.srp.dto.courseDTO;

public class AddCourseFormController {
    public JFXTextField txtCourseID;
    public JFXTextField txtCourseName;
    public JFXTextField txtDuration;
    public JFXTextField txtRegFee;
    public JFXTextField txtIntake;
    //AddNewCourseDAO addNewCourseDAO = (AddNewCourseDAO) DAOFactory.getInstance().getDAO(DAOFactory.DAOType.AddNewCourseDAOimpl);
    CourseBO courseBO = (CourseBO) BOFactory.getInstance().getBO(BOFactory.BOType.CourseBOimpl);

    public void btnAddCourse(ActionEvent actionEvent) {
        try {
            courseDTO dto = new courseDTO(txtCourseID.getText(), txtCourseName.getText(), txtDuration.getText(), Double.parseDouble(txtRegFee.getText()), txtIntake.getText());
            boolean b = courseBO.addCourse(dto);
            if (b){
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setHeaderText(null);
                alert.setContentText("Course details successfully added!");
                alert.showAndWait();
            }else{
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setHeaderText(null);
                alert.setContentText("Something went Wrong!");
                alert.showAndWait();
            }
        } catch (NumberFormatException s){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setContentText("Please fill course detail!");
            alert.showAndWait();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
