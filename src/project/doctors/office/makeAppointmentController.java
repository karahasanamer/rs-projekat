package project.doctors.office;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;

import java.sql.Time;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;

public class makeAppointmentController {

    private Appointment appointment;
    private ObservableList<Patient> patients;
    public ChoiceBox<Patient> patientsCb;
    public DatePicker datePicker;
    public TextField hoursFld;
    public TextField minutesFld;

    public makeAppointmentController(Appointment appointment, ArrayList<Patient> patients) {
        this.appointment = appointment;
        this.patients = FXCollections.observableArrayList(patients);

    }

    public Appointment getAppointment() {
        return appointment;
    }

    private int findId() {
        int id = 0;
        for (Patient p : patients
        ) {
            if (p.getId() == appointment.getPatient().getId()) id = patients.indexOf(p);
        }

        return id;
    }

    @FXML
    public void initialize() {

        patientsCb.setItems(patients);
        
        if (appointment == null) {
            patientsCb.getSelectionModel().selectFirst();
            datePicker.setValue(LocalDate.now());
        } else {
            patientsCb.getSelectionModel().select(findId());
            minutesFld.setText(Integer.toString(appointment.getAppoTime().getMinutes()));
            hoursFld.setText(Integer.toString(appointment.getAppoTime().getHours()));
            datePicker.valueProperty().setValue(appointment.getAppoDate());
        }




    }



    public void confirmBtn(ActionEvent actionEvent) {
        if (appointment == null) {
            appointment = new Appointment();
        }

        if(!datePicker.getValue().isAfter(LocalDate.now().minusDays(1))){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Error while creating appointment.");
            alert.setContentText("Your date is not valid.");
            alert.show();
            datePicker.getStyleClass().add("invalid");
        }else if(hoursFld.getText().trim().isEmpty() || minutesFld.getText().trim().isEmpty()){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Error while creating appointment.");
            alert.setContentText("Your hours or minutes field may be empty.");
            alert.show();

            if(hoursFld.getText().trim().isEmpty()){
                hoursFld.getStyleClass().add("invalid");
            }else{
                hoursFld.getStyleClass().removeAll("invalid");
            }

            if(minutesFld.getText().trim().isEmpty()){
                minutesFld.getStyleClass().add("invalid");
            }else{
                minutesFld.getStyleClass().removeAll("invalid");
            }

        }else if( minutesFld.getText().trim().isEmpty()){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Error while creating appointment.");
            alert.setContentText("Your hours or minutes field may be empty.");
            alert.show();

            if(hoursFld.getText().trim().isEmpty()){
                hoursFld.getStyleClass().add("invalid");
            }else{
                hoursFld.getStyleClass().removeAll("invalid");
            }

            if(minutesFld.getText().trim().isEmpty()){
                minutesFld.getStyleClass().add("invalid");
            }else{
                minutesFld.getStyleClass().removeAll("invalid");
            }
        }else {
            datePicker.getStyleClass().removeAll("invalid");
            appointment.setPatient(patientsCb.getValue());
            appointment.setAppoDate(datePicker.getValue());
            appointment.setAppoTime(Time.valueOf(hoursFld.getText() + ":" + minutesFld.getText() + ":00"));
            Stage stage = (Stage) patientsCb.getScene().getWindow();
            stage.close();
        }
    }

    public void cancelBtn(ActionEvent actionEvent) {
        appointment = null;
        Stage stage = (Stage) patientsCb.getScene().getWindow();
        stage.close();
    }

    public void checkFlds(KeyEvent keyEvent) {
        //if(minutesFld.getText())
    }
}
