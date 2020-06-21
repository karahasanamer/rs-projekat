package project.doctors.office;

import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import javafx.util.converter.NumberStringConverter;

import java.io.IOException;
import java.time.LocalDate;
import java.util.Optional;

public class Controller {
    public TextField nameFld;
    public TextField lastnameFld;
    public TextField secNumFld;
    public DatePicker birthPicker;
    public TextField adressFld;
    public TextField phoneFld;
    public TextField heightFld;
    public TextField weightFld;
    public TextField complaintFld;
    public TableView<Appointment> tableAppointments;
    public ListView<Patient>patientsList;
    public TableColumn appointmentNumCol;
    public TableColumn<Appointment, String> patientCol;
    public TableColumn dateCol;
    public TableColumn timeCol;
    @FXML
    private ObservableList<Patient> patientAList;
    private ObservableList<Appointment> appointmentsAList;
    private PatientDAOBase dao;

    public Controller() {
        dao = PatientDAOBase.getInstance();
        patientAList = FXCollections.observableArrayList(dao.getPatients());
        appointmentsAList=FXCollections.observableArrayList(dao.getAppointments());

    }

    @FXML
    public void initialize(){
        //patient list
        patientsList.setItems(patientAList);
        //apointmet table
        tableAppointments.setItems(appointmentsAList);
        appointmentNumCol.setCellValueFactory(new PropertyValueFactory("id"));
        dateCol.setCellValueFactory(new PropertyValueFactory("appoDate"));
        timeCol.setCellValueFactory(new PropertyValueFactory("appoTime"));
        patientCol.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getPatient().toString()));
        tableAppointments.getSelectionModel().selectFirst();

        patientsList.getSelectionModel().selectedItemProperty().addListener((observableValue, oldValue, newValue) -> {
            if (oldValue != null) {
                nameFld.textProperty().unbindBidirectional(oldValue.nameProperty());
                lastnameFld.textProperty().unbindBidirectional(oldValue.lastnameProperty());
                secNumFld.textProperty().unbindBidirectional(oldValue.securityNumProperty());
                birthPicker.valueProperty().unbindBidirectional(oldValue.birthdayProperty());
                weightFld.textProperty().unbindBidirectional(oldValue.weightProperty());
                heightFld.textProperty().unbindBidirectional(oldValue.heightProperty());
                adressFld.textProperty().unbindBidirectional(oldValue.adressProperty());
                phoneFld.textProperty().unbindBidirectional(oldValue.phoneProperty());
                complaintFld.textProperty().unbindBidirectional(oldValue.complaintProperty());
            }
            if (newValue == null) {
                nameFld.setText("");
                lastnameFld.setText("");
                secNumFld.setText("0");
                birthPicker.setValue(LocalDate.now());
                weightFld.setText("");
                heightFld.setText("");
                adressFld.setText("");
                phoneFld.setText("");
                complaintFld.setText("");
            } else {
                nameFld.textProperty().bindBidirectional(newValue.nameProperty());
                lastnameFld.textProperty().bindBidirectional(newValue.lastnameProperty());
                secNumFld.textProperty().bindBidirectional(newValue.securityNumProperty(), new NumberStringConverter("#"));
                birthPicker.valueProperty().bindBidirectional(newValue.birthdayProperty());
                weightFld.textProperty().bindBidirectional(newValue.weightProperty(), new NumberStringConverter());
                heightFld.textProperty().bindBidirectional(newValue.heightProperty(), new NumberStringConverter());
                adressFld.textProperty().bindBidirectional(newValue.adressProperty());
                phoneFld.textProperty().bindBidirectional(newValue.phoneProperty());
                complaintFld.textProperty().bindBidirectional(newValue.complaintProperty());
            }
        });

        patientsList.getSelectionModel().selectFirst();

        lastnameFld.textProperty().addListener((observableValue, oldValue, newValue) -> {

            patientsList.getSelectionModel().getSelectedItem().setLastname(newValue);
            dao.updatePatient(patientsList.getSelectionModel().getSelectedItem());
            appointmentsAList = FXCollections.observableArrayList(dao.getAppointments());
            tableAppointments.setItems(appointmentsAList);
            tableAppointments.refresh();
            patientsList.setItems(patientAList);
            patientsList.refresh();
        });
        nameFld.textProperty().addListener((observableValue, oldValue, newValue) -> {

            if(patientAList.isEmpty()) return;
            patientsList.getSelectionModel().getSelectedItem().setName(newValue);
            dao.updatePatient(patientsList.getSelectionModel().getSelectedItem());
            appointmentsAList = FXCollections.observableArrayList(dao.getAppointments());
            tableAppointments.setItems(appointmentsAList);
            tableAppointments.refresh();
            patientsList.setItems(patientAList);
            patientsList.refresh();
        });
        secNumFld.textProperty().addListener((observableValue, oldValue, newValue) -> {

            if(patientAList.isEmpty()) return;
            if(!secNumFld.getText().isEmpty())patientsList.getSelectionModel().getSelectedItem().setSecurityNum(Integer.parseInt(newValue));
            dao.updatePatient(patientsList.getSelectionModel().getSelectedItem());
            appointmentsAList = FXCollections.observableArrayList(dao.getAppointments());
            tableAppointments.setItems(appointmentsAList);
            tableAppointments.refresh();
            patientsList.setItems(patientAList);
            patientsList.refresh();
        });

        nameFld.textProperty().addListener((observableValue, s, t1) -> {validate(); if(patientAList.isEmpty()) return; dao.updatePatient(patientsList.getSelectionModel().getSelectedItem());});
        lastnameFld.textProperty().addListener((observableValue, s, t1) -> {validate(); if(patientAList.isEmpty()) return; dao.updatePatient(patientsList.getSelectionModel().getSelectedItem());});
        secNumFld.textProperty().addListener((observableValue, s, t1) -> {validate(); if(patientAList.isEmpty()) return; dao.updatePatient(patientsList.getSelectionModel().getSelectedItem());});
        birthPicker.valueProperty().addListener((observableValue, s, t1) -> {validate(); if(patientAList.isEmpty()) return; dao.updatePatient(patientsList.getSelectionModel().getSelectedItem());});
        weightFld.textProperty().addListener((observableValue, s, t1) -> {validate(); if(patientAList.isEmpty()) return; dao.updatePatient(patientsList.getSelectionModel().getSelectedItem());});
        heightFld.textProperty().addListener((observableValue, s, t1) -> {validate(); if(patientAList.isEmpty()) return; dao.updatePatient(patientsList.getSelectionModel().getSelectedItem());});
        adressFld.textProperty().addListener((observableValue, s, t1) -> {validate(); if(patientAList.isEmpty()) return; dao.updatePatient(patientsList.getSelectionModel().getSelectedItem());});
        phoneFld.textProperty().addListener((observableValue, s, t1) -> {validate(); if(patientAList.isEmpty()) return; dao.updatePatient(patientsList.getSelectionModel().getSelectedItem());});
        complaintFld.textProperty().addListener((observableValue, s, t1) -> {validate(); if(patientAList.isEmpty()) return; dao.updatePatient(patientsList.getSelectionModel().getSelectedItem());});



    }

    public void validate(){
        if(nameFld.getText().trim().isEmpty() || !nameFld.getText().matches("^([A-Z][a-z]+([ ]?[a-z]?['-]?[A-Z][a-z]+)?)$")) {
            nameFld.getStyleClass().add("invalid");
        } else {
            nameFld.getStyleClass().removeAll("invalid");
        }
        if(lastnameFld.getText().trim().isEmpty()|| !lastnameFld.getText().matches("^([A-Z][a-z]+([ ]?[a-z]?['-]?[A-Z][a-z]+)?)$")) {
            lastnameFld.getStyleClass().add("invalid");
        } else {
            lastnameFld.getStyleClass().removeAll("invalid");
        }
        if(secNumFld.getText().trim().equals("0") || !secNumFld.getText().matches("^[0-9]{6}$")) {
            secNumFld.getStyleClass().add("invalid");
        } else {
            secNumFld.getStyleClass().removeAll("invalid");
        }
        if(adressFld.getText().trim().isEmpty()) {
            adressFld.getStyleClass().add("invalid");
        } else {
            adressFld.getStyleClass().removeAll("invalid");
        }
        if(!birthPicker.getValue().isBefore(LocalDate.now().plusDays(1))) {
            birthPicker.getStyleClass().add("invalid");
        }else{
            birthPicker.getStyleClass().removeAll("invalid");
        }
        if(phoneFld.getText().trim().isEmpty() || !phoneFld.getText().matches("(\\+?( |-|\\.)?\\d{1,4}( |-|\\.|\\/)?)?(\\d{3})( |-|\\.|\\/)?(\\d{3}( |-|\\.|\\/)?\\d{3,4})")) {
            phoneFld.getStyleClass().add("invalid");
        } else {
            phoneFld.getStyleClass().removeAll("invalid");
        }
        if(heightFld.getText().trim().equals("0") || !heightFld.getText().matches("[1-9][0-9][0-9]")) {
            heightFld.getStyleClass().add("invalid");
        } else {
            heightFld.getStyleClass().removeAll("invalid");
        }
        if(weightFld.getText().trim().equals("0")|| !weightFld.getText().matches("[1-9][0-9][0-9]?")) {
            weightFld.getStyleClass().add("invalid");
        } else {
            weightFld.getStyleClass().removeAll("invalid");
        }
    }


    public void addPatient(ActionEvent actionEvent) {

        patientAList.add(new Patient());
        patientsList.refresh();
        patientsList.getSelectionModel().selectLast();
        dao.addPatient(patientsList.getSelectionModel().getSelectedItem());



    }

    public void deletePatient(ActionEvent actionEvent) {
        if (patientsList.getSelectionModel().isEmpty()) return;
        Patient patient = patientsList.getSelectionModel().getSelectedItem();
        if (patient == null) return;

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirm deletion");
        alert.setHeaderText("Deleting patient " + patient.toString());
        alert.setContentText("Are you sure you want to delete patient " +patient.toString()+" and all related appointments?");

        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK){
            //Delete all related appointments first
            dao.deleteAppointmentByPatient(patientsList.getSelectionModel().getSelectedItem());
            appointmentsAList = FXCollections.observableArrayList(dao.getAppointments());
            tableAppointments.setItems(appointmentsAList);
            tableAppointments.refresh();
            //Delete patient
            dao.deletePatient(patientsList.getSelectionModel().getSelectedItem());
            patientAList.removeAll(patientsList.getSelectionModel().getSelectedItem());
            patientsList.refresh();
        }
    }


    public void addAppointmentBtn(ActionEvent actionEvent) {

        if (patientAList.isEmpty()) return;
        Stage stage = new Stage();
        Parent root;
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/makeAppointment.fxml"));
            makeAppointmentController makeAppointmentController = new makeAppointmentController(null, dao.getPatients());
            loader.setController(makeAppointmentController);
            root = loader.load();
            stage.setTitle("Make appointment");
            stage.setScene(new Scene(root, 341, 387));
            stage.setResizable(false);
            stage.show();
            stage.setOnHiding(event -> {
                Appointment appointment = makeAppointmentController.getAppointment();
                if (appointment != null) {
                    dao.addAppointment(appointment);
                    appointmentsAList.setAll(dao.getAppointments());
                    tableAppointments.refresh();
                }
            });
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void deleteAppointmentBtn(ActionEvent actionEvent) {
        if (tableAppointments.getSelectionModel().isEmpty()) return;
        Appointment appointment = tableAppointments.getSelectionModel().getSelectedItem();
        if (appointment == null) return;

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirm deletion");
        alert.setHeaderText("Deleting appointment for " + appointment.getPatient().toString());
        alert.setContentText("Are you sure you want to delete this appointment?");

        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK){
            dao.deleteAppointment(tableAppointments.getSelectionModel().getSelectedItem());
            appointmentsAList.removeAll(tableAppointments.getSelectionModel().getSelectedItem());
            tableAppointments.refresh();
        }
    }
}
