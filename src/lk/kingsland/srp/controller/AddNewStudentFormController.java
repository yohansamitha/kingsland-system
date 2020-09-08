package lk.kingsland.srp.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import lk.kingsland.srp.bo.BOFactory;
import lk.kingsland.srp.bo.custom.AddNewStudentBO;
import lk.kingsland.srp.dto.RegistrationDTO;
import lk.kingsland.srp.dto.RegistrationDetailDTO;
import lk.kingsland.srp.dto.courseDTO;
import lk.kingsland.srp.dto.studentDTO;

import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class AddNewStudentFormController implements Initializable {
    public JFXComboBox cblStudentID;
    public JFXTextField txtFullName;
    public JFXTextField txtAddress;
    public JFXTextField txtContact;
    public JFXTextField txtDOB;
    public JFXComboBox cblGender;
    public JFXTextField txtDate;
    public JFXComboBox cblSelectCourse;
    public JFXButton btnRegister;
    public JFXTextField txtRegFee;
    ObservableList<String> studentObservableList = FXCollections.observableArrayList();
    AddNewStudentBO addNewStudentBOImpl = (AddNewStudentBO) BOFactory.getInstance().getBO(BOFactory.BOType.AddNewStudentBOImpl);
    String newStudentID;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        loadAllStudent();
        loadAllCourse();
        loadGender();
        setDate();
    }


    private void setDate() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        txtDate.setText(LocalDateTime.now().format(formatter));
    }


    public void btnNewStudent(ActionEvent actionEvent) {
        try {
            cblStudentID.getItems().clear();
            ObservableList<String> studentList = FXCollections.observableArrayList();
            newStudentID = getNewStudentID();
            cblStudentID.setPromptText(newStudentID);
            txtFullName.setDisable(false);
            txtAddress.setDisable(false);
            txtContact.setDisable(false);
            txtDOB.setDisable(false);
            cblGender.setDisable(false);
            cblSelectCourse.setDisable(false);
            btnRegister.setDisable(false);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }


    public void btnRegister(ActionEvent actionEvent) {
        try {
            String gender = String.valueOf(cblGender.getValue());
            String courseID = addNewStudentBOImpl.getCourseID(String.valueOf(cblSelectCourse.getValue()));
            System.out.println(courseID + "course id");
            RegistrationDetailDTO registrationDetailDTO = new RegistrationDetailDTO(newStudentID, txtFullName.getText(), txtAddress.getText(),
                    txtContact.getText(), txtDOB.getText(), gender, new RegistrationDTO(
                    txtDate.getText(),
                    Double.parseDouble(txtRegFee.getText()),
                    newStudentID,
                    courseID));
            boolean isAdded = addNewStudentBOImpl.setRegister(registrationDetailDTO);
            if (isAdded) {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setHeaderText(null);
                alert.setContentText("Student registered Successfully!");
                alert.showAndWait();
            } else {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setHeaderText(null);
                alert.setContentText("Something went Wrong!");
                alert.showAndWait();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void cblSelectGender(ActionEvent actionEvent) {
        System.out.println("on action");
    }

    public void cblGetStudentID(ActionEvent actionEvent) {

    }


    public void cmbSelectGender(ActionEvent actionEvent) {

    }

    public void btnCancel(ActionEvent actionEvent) {
        cblStudentID.getItems().clear();
        cblStudentID.setPromptText("");
        loadAllStudent();
        txtFullName.setDisable(true);
        txtAddress.setDisable(true);
        txtContact.setDisable(true);
        txtDOB.setDisable(true);
        cblGender.setDisable(true);
        cblGender.getItems().clear();
        cblGender.setPromptText("Gender");
        loadGender();
        cblSelectCourse.setDisable(true);
        cblSelectCourse.getItems().clear();
        cblSelectCourse.setPromptText("Select Course");
        loadAllCourse();
        btnRegister.setDisable(true);
    }

    private String getNewStudentID() throws SQLException, ClassNotFoundException {
        return addNewStudentBOImpl.getLastID();
    }

    private void loadGender() {
        ObservableList<String> gender = FXCollections.observableArrayList("Male", "Female");
        cblGender.setItems(gender);
    }

    private void loadAllCourse() {
        try {
            ArrayList<courseDTO> allCourse = addNewStudentBOImpl.getAllCourse();
            ObservableList<String> allCourseNames = FXCollections.observableArrayList();
            for (int i = 0; i < allCourse.size(); i++) {
                allCourseNames.add(allCourse.get(i).getCourseName());
            }
            cblSelectCourse.setItems(allCourseNames);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void loadAllStudent() {
        try {
            ArrayList<studentDTO> allStudentID = addNewStudentBOImpl.getAllStudent();
            ObservableList<String> studentObservableList = FXCollections.observableArrayList();
            for (int i = 0; i < allStudentID.size(); i++) {
                studentObservableList.addAll(allStudentID.get(i).getStudentID());
            }
            cblStudentID.setItems(studentObservableList);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
