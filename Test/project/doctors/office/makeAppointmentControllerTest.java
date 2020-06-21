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
class makeAppointmentControllerTest {

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
    void deleteAppointment(FxRobot robot) {
        robot.clickOn("#appointmentsTab");
        PatientDAOBase dao2 = PatientDAOBase.getInstance();
        ArrayList<Appointment> appointments = dao2.getAppointments();
        int a=appointments.size();
        robot.clickOn("Jim Franklin, 783121");
        robot.clickOn("#deleteAppointment");
        robot.clickOn("OK");
        appointments=dao2.getAppointments();
        assertEquals(a-1,appointments.size());

    }

    @Test
    public void addAppointment(FxRobot robot){
        robot.clickOn("#appointmentsTab");
        PatientDAOBase dao2 = PatientDAOBase.getInstance();
        ArrayList<Appointment> appointments = dao2.getAppointments();
        int a=appointments.size();
        robot.clickOn("#addApointment");
        robot.clickOn("#hoursFld");
        robot.write("13");
        robot.clickOn("#minutesFld");
        robot.write("30");
        robot.clickOn("#confirmBtn");


        appointments=dao2.getAppointments();
        assertEquals(a+1,appointments.size());

    }

    boolean hasCss(TextField polje, String stil) {
        for (String s : polje.getStyleClass())
            if (s.equals(stil)) return true;
        return false;
    }

    @Test
    public void emptyFields(FxRobot robot){
        robot.clickOn("#appointmentsTab");
        robot.clickOn("#addApointment");
        robot.clickOn("#hoursFld");
        KeyCode ctrl = KeyCode.CONTROL;

        //If Os is MAC
        if (System.getProperty("os.name").equals("Mac OS X"))
            ctrl = KeyCode.COMMAND;
        robot.press(ctrl).press(KeyCode.A).release(KeyCode.A).release(ctrl);
        robot.press(KeyCode.DELETE).release(KeyCode.DELETE);
        robot.clickOn("#minutesFld");
        robot.press(ctrl).press(KeyCode.A).release(KeyCode.A).release(ctrl);
        robot.press(KeyCode.DELETE).release(KeyCode.DELETE);
        robot.clickOn("#confirmBtn");
        robot.clickOn("OK");

        TextField field = robot.lookup("#hoursFld").queryAs(TextField.class);
        assertTrue(hasCss(field, "invalid"));
        field = robot.lookup("#minutesFld").queryAs(TextField.class);
        assertTrue(hasCss(field, "invalid"));

    }

    @Test
    public void wrongTime(FxRobot robot){
        robot.clickOn("#appointmentsTab");
        robot.clickOn("#addApointment");
        robot.clickOn("#hoursFld");
        KeyCode ctrl = KeyCode.CONTROL;

        //If Os is MAC
        if (System.getProperty("os.name").equals("Mac OS X"))
            ctrl = KeyCode.COMMAND;
        robot.press(ctrl).press(KeyCode.A).release(KeyCode.A).release(ctrl);
        robot.press(KeyCode.DELETE).release(KeyCode.DELETE);
        robot.write("150");
        robot.clickOn("#minutesFld");
        robot.press(ctrl).press(KeyCode.A).release(KeyCode.A).release(ctrl);
        robot.press(KeyCode.DELETE).release(KeyCode.DELETE);
        robot.write("200");
        robot.clickOn("#confirmBtn");
        robot.clickOn("OK");

        TextField field = robot.lookup("#hoursFld").queryAs(TextField.class);
        assertTrue(hasCss(field, "invalid"));
        field = robot.lookup("#minutesFld").queryAs(TextField.class);
        assertTrue(hasCss(field, "invalid"));

    }

    @Test
    public void deletedPatient(FxRobot robot){

        PatientDAOBase dao2 = PatientDAOBase.getInstance();
        ArrayList<Appointment> appointments = dao2.getAppointments();
        int appNum=appointments.size();


        ArrayList<Patient> patients = dao2.getPatients();
        int patNum=patients.size();

        robot.clickOn("Jim Franklin, 783121");
        robot.clickOn("#deletePatientBtn");
        robot.clickOn("OK");

        appointments = dao2.getAppointments();

        patients = dao2.getPatients();

        assertEquals(appNum-1,appointments.size());
        assertEquals(patNum-1,patients.size());

    }

}