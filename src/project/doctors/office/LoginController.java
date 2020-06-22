package project.doctors.office;

import com.sun.applet2.preloader.event.ApplicationExitEvent;
import com.sun.scenario.effect.impl.sw.sse.SSEBlend_SRC_OUTPeer;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.sql.SQLException;

public class LoginController {

    public TextField userFld;
    public TextField passFld;
    private PatientDAOBase dao;


    public LoginController() {
        dao = PatientDAOBase.getInstance();

    }


    public void login(ActionEvent actionEvent) throws SQLException {

        if (userFld.getText().isEmpty() || passFld.getText().isEmpty()) {

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
            userFld.getStyleClass().add("invalid");
            passFld.getStyleClass().add("invalid");
            Alert alert =new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Error while logging in.");
            alert.setContentText("You did not insert correct username or password, pleace try again.");
            alert.show();

        } else {

            Stage stage2 = new Stage();
            Parent root;
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/main.fxml"));
                Controller controller = new Controller();
                loader.setController(controller);
                root = loader.load();
                stage2.setTitle("Doctors office");
                stage2.setScene(new Scene(root, 641, 566));
                stage2.setResizable(false);

                stage2.show();
                ((Node)(actionEvent.getSource())).getScene().getWindow().hide();

            } catch (IOException e) {
                e.printStackTrace();
            }



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
