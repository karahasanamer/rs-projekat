package project.doctors.office;

import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.util.converter.NumberStringConverter;

import java.time.LocalDate;
import java.util.Optional;

public class Controller {
    public TextField nameFld;
    public TextField lastnameFld;
    public TextField secNumFld;
    public DatePicker birthPicker;
    public TextField adressFld;
    public TextField phoneFld;
    public TextField heigthFld;
    public TextField weigthFld;
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
        appointmentNumCol.setCellValueFactory(new PropertyValueFactory("id"));
        dateCol.setCellValueFactory(new PropertyValueFactory("appoDate"));
        timeCol.setCellValueFactory(new PropertyValueFactory("appoTime"));
        patientCol.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getPatient().toString()));
        tableAppointments.getSelectionModel().selectFirst();

        patientsList.getSelectionModel().selectedItemProperty().addListener((observableValue, oldValue, newValue) -> {
            if (oldValue != null) {
                nameFld.textProperty().unbindBidirectional(oldValue.nameProperty());
                lastnameFld.textProperty().unbindBidirectional(oldValue.surnameProperty());
                secNumFld.textProperty().unbindBidirectional(oldValue.securityNumProperty());
                birthPicker.valueProperty().unbindBidirectional(oldValue.birthdayProperty());
                weigthFld.textProperty().unbindBidirectional(oldValue.weightProperty());
                heigthFld.textProperty().unbindBidirectional(oldValue.heightProperty());
                adressFld.textProperty().unbindBidirectional(oldValue.adressProperty());
                phoneFld.textProperty().unbindBidirectional(oldValue.phoneProperty());
                complaintFld.textProperty().unbindBidirectional(oldValue.complaintProperty());
            }
            if (newValue == null) {
                nameFld.setText("");
                lastnameFld.setText("");
                secNumFld.setText("");
                secNumFld.clear();
                birthPicker.setValue(LocalDate.now());
                weigthFld.setText("");
                heigthFld.setText("");
                adressFld.setText("");
                phoneFld.setText("");
                complaintFld.setText("");
            } else {
                nameFld.textProperty().bindBidirectional(newValue.nameProperty());
                lastnameFld.textProperty().bindBidirectional(newValue.surnameProperty());
                secNumFld.textProperty().bindBidirectional(newValue.securityNumProperty(), new NumberStringConverter("#"));
                birthPicker.valueProperty().bindBidirectional(newValue.birthdayProperty());
                weigthFld.textProperty().bindBidirectional(newValue.weightProperty(), new NumberStringConverter());
                heigthFld.textProperty().bindBidirectional(newValue.heightProperty(), new NumberStringConverter());
                adressFld.textProperty().bindBidirectional(newValue.adressProperty());
                phoneFld.textProperty().bindBidirectional(newValue.phoneProperty());
                complaintFld.textProperty().bindBidirectional(newValue.complaintProperty());
            }
        });

        patientsList.getSelectionModel().selectFirst();

        lastnameFld.textProperty().addListener((observableValue, oldValue, newValue) -> {

            patientsList.getSelectionModel().getSelectedItem().setSurname(newValue);
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

        birthPicker.valueProperty().addListener((observableValue, s, t1) -> {if(patientAList.isEmpty()) return; dao.updatePatient(patientsList.getSelectionModel().getSelectedItem());});
        secNumFld.textProperty().addListener((observableValue, s, t1) -> {if(patientAList.isEmpty()) return; dao.updatePatient(patientsList.getSelectionModel().getSelectedItem());});
        weigthFld.textProperty().addListener((observableValue, s, t1) -> {if(patientAList.isEmpty()) return; dao.updatePatient(patientsList.getSelectionModel().getSelectedItem());});
        heigthFld.textProperty().addListener((observableValue, s, t1) -> {if(patientAList.isEmpty()) return; dao.updatePatient(patientsList.getSelectionModel().getSelectedItem());});
        adressFld.textProperty().addListener((observableValue, s, t1) -> {if(patientAList.isEmpty()) return; dao.updatePatient(patientsList.getSelectionModel().getSelectedItem());});
        phoneFld.textProperty().addListener((observableValue, s, t1) -> {if(patientAList.isEmpty()) return; dao.updatePatient(patientsList.getSelectionModel().getSelectedItem());});
        complaintFld.textProperty().addListener((observableValue, s, t1) -> {if(patientAList.isEmpty()) return; dao.updatePatient(patientsList.getSelectionModel().getSelectedItem());});



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


}
