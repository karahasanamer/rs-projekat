package project.doctors.office;

import com.sun.applet2.preloader.event.ApplicationExitEvent;
import com.sun.scenario.effect.impl.sw.sse.SSEBlend_SRC_OUTPeer;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;

import java.io.File;
import java.sql.SQLException;

public class Controller {

    public TextField userFld;
    public TextField passFld;
    private PatientDAOBase dao;


    public Controller() {
        dao = PatientDAOBase.getInstance();
       // patientsList = FXCollections.observableArrayList(dao.getPatients());
       // appointmentsList = FXCollections.observableArrayList(dao.getAppointments());
    }

    public void resetDao(){
        PatientDAOBase.removeInstance();
        File dbfile=new File("database.db.sql");
        dbfile.delete();
        dao=PatientDAOBase.getInstance();
    }

    public void login(ActionEvent actionEvent) throws SQLException {

        if (userFld.getText().isEmpty() || passFld.getText().isEmpty()) {
        // TODO: NAPRAVIT VALIDACIJU POLJA
            if(userFld.getText().trim().isEmpty()) {
                userFld.getStyleClass().add("invalid");
            } else {
                userFld.getStyleClass().removeAll("invalid");
            }
            if(passFld.getText().trim().isEmpty()) {
                passFld.getStyleClass().add("invalid");
            } else {
                passFld.getStyleClass().removeAll("invalid");
            }

            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Error while loging in.");
            alert.setContentText("You did not insert user or password, please try again.");
            alert.show();
            return;
        }

        String user = userFld.getText();
        String password = passFld.getText();


            boolean flag=dao.validate(user,password);

        if (!flag) {
            Alert alert =new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Error while logging in.");
            alert.setContentText("You did not insert correct username or password, pleace try again.");
            alert.show();

        } else {

            Alert alert =new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Successful");
            alert.setHeaderText("Login successful!");
            alert.show();
        }

    }

    public void exitBtn(ActionEvent actionEvent) {
        Platform.exit();
    }

    public void removeStyle(KeyEvent keyEvent) {
        passFld.getStyleClass().removeAll("invalid");
        userFld.getStyleClass().removeAll("invalid");


    }
}
