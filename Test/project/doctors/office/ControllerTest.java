package project.doctors.office;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.stage.Stage;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.testfx.api.FxRobot;
import org.testfx.framework.junit5.ApplicationExtension;
import org.testfx.framework.junit5.Start;

import java.io.File;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;
@ExtendWith(ApplicationExtension.class)
class ControllerTest {
    PatientDAOBase dao = PatientDAOBase.getInstance();
    @Start
    public void start(Stage primaryStage) throws Exception{

        PatientDAOBase.removeInstance();
        File dbfile = new File("database.db");
        dbfile.delete();
        PatientDAOBase dao = PatientDAOBase.getInstance();

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/main.fxml"));
        loader.setController(new Controller());
        Parent root =loader.load();
        primaryStage.setTitle("Doctors office");
        primaryStage.setScene(new Scene(root, 641, 566));
        primaryStage.setResizable(false);
        primaryStage.show();

    }

    @Test
    public void addPatient(FxRobot robot) {
        robot.clickOn("#addPatientBtn");

        robot.clickOn("#nameFld");
        robot.write("Sam");
        robot.clickOn("#lastnameFld");
        robot.write("Cool");
        robot.clickOn("#secNumFld");
        robot.eraseText(1);
        robot.write("666666");
        robot.clickOn("#adressFld");
        robot.write("Good guy ST");
        robot.clickOn("#phoneFld");
        robot.write("063/625-526");
        robot.clickOn("#heigthFld");
        robot.eraseText(1);
        robot.write("180");
        robot.clickOn("#weigthFld");
        robot.eraseText(1);
        robot.write("90");
        robot.clickOn("#complaintFld");
        robot.write("Broken arm");
        PatientDAOBase dao2 = PatientDAOBase.getInstance();
        ArrayList<Patient> patients = dao2.getPatients();
        assertEquals("Sam", patients.get(4).getName());
        assertEquals("Cool", patients.get(4).getSurname());


    }

    @Test
    void deletePatient(FxRobot robot) {
        PatientDAOBase dao2 = PatientDAOBase.getInstance();
        ArrayList<Patient> patients = dao2.getPatients();
        int a=patients.size();
        robot.clickOn("Jim Franklin, 783121");
        robot.clickOn("#deletePatientBtn");
        robot.clickOn("OK");
        robot.clickOn("Johny Sans, 614331");
        robot.clickOn("#deletePatientBtn");
        robot.clickOn("OK");
        patients=dao2.getPatients();
        assertEquals(a-2,patients.size());

    }
    // Function for testing css
    boolean hasCss(TextField polje, String stil) {
        for (String s : polje.getStyleClass())
            if (s.equals(stil)) return true;
        return false;
    }

    @Test
    public void allInfoFalse(FxRobot robot){
        robot.clickOn("#addPatientBtn");

        robot.clickOn("#nameFld");
        robot.write("");
        robot.clickOn("#lastnameFld");
        robot.write("");
        robot.clickOn("#secNumFld");
        robot.eraseText(1);
        robot.write("4");
        robot.clickOn("#adressFld");
        robot.write("");
        robot.clickOn("#phoneFld");
        robot.write("06/-");
        robot.clickOn("#heigthFld");
        robot.eraseText(1);
        robot.write("1");
        robot.clickOn("#weigthFld");
        robot.eraseText(1);
        robot.write("9");

        TextField field = robot.lookup("#nameFld").queryAs(TextField.class);
        assertTrue(hasCss(field, "invalid"));
        field = robot.lookup("#lastnameFld").queryAs(TextField.class);
        assertTrue(hasCss(field, "invalid"));
        field = robot.lookup("#secNumFld").queryAs(TextField.class);
        assertTrue(hasCss(field, "invalid"));
        field = robot.lookup("#adressFld").queryAs(TextField.class);
        assertTrue(hasCss(field, "invalid"));
        field = robot.lookup("#phoneFld").queryAs(TextField.class);
        assertTrue(hasCss(field, "invalid"));
        field = robot.lookup("#heigthFld").queryAs(TextField.class);
        assertTrue(hasCss(field, "invalid"));
        field = robot.lookup("#weigthFld").queryAs(TextField.class);
        assertTrue(hasCss(field, "invalid"));

    }


    @Test
    public void editInfo(FxRobot robot){
        robot.clickOn("Jim Franklin, 783121");
        robot.clickOn("#nameFld");
        KeyCode ctrl = KeyCode.CONTROL;

        //If Os is MAC
        if (System.getProperty("os.name").equals("Mac OS X"))
            ctrl = KeyCode.COMMAND;
        robot.press(ctrl).press(KeyCode.A).release(KeyCode.A).release(ctrl);
        robot.press(KeyCode.DELETE).release(KeyCode.DELETE);

        robot.write("The");
        robot.clickOn("#lastnameFld");
        robot.press(ctrl).press(KeyCode.A).release(KeyCode.A).release(ctrl);
        robot.press(KeyCode.DELETE).release(KeyCode.DELETE);
        robot.write("Rock");

        PatientDAOBase dao2 = PatientDAOBase.getInstance();
        ArrayList<Patient> patients = dao2.getPatients();
        assertEquals("The",patients.get(3).getName());
        assertEquals("Rock",patients.get(3).getSurname());


    }
}