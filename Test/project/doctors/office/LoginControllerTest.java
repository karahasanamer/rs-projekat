package project.doctors.office;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.stage.Stage;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestTemplate;
import org.junit.jupiter.api.extension.ExtendWith;
import org.testfx.api.FxAssert;
import org.testfx.api.FxRobot;
import org.testfx.framework.junit5.ApplicationExtension;
import org.testfx.framework.junit5.Start;
import javafx.scene.control.TextField;
import org.testfx.matcher.base.WindowMatchers;

import java.awt.*;
import java.io.File;
import java.lang.reflect.Field;
import java.text.Format;

import static java.awt.SystemColor.window;
import static org.junit.jupiter.api.Assertions.*;
@ExtendWith(ApplicationExtension.class)
class LoginControllerTest {
    LoginController reset;
    @Start
    public void start(Stage primaryStage) throws Exception{
        PatientDAOBase.removeInstance();
        File dbfile = new File("database.db");
        dbfile.delete();
        PatientDAOBase dao = PatientDAOBase.getInstance();



        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/login.fxml"));
        loader.setController(new LoginController());
        Parent root =loader.load();
        primaryStage.setTitle("Doctors office");
        primaryStage.setMinHeight(300);
        primaryStage.setMinWidth(600);
        primaryStage.setScene(new Scene(root, 600, 300));
        primaryStage.setResizable(false);
        primaryStage.show();
    }

    // Function for testing css
    boolean hasCss(TextField polje, String stil) {
        for (String s : polje.getStyleClass())
            if (s.equals(stil)) return true;
        return false;
    }

    @Test
    public void testLoginEmptyFields(FxRobot robot){

        robot.clickOn("#userFld");
        KeyCode ctrl = KeyCode.CONTROL;

        //If Os is MAC
        if (System.getProperty("os.name").equals("Mac OS X"))
            ctrl = KeyCode.COMMAND;
        robot.press(ctrl).press(KeyCode.A).release(KeyCode.A).release(ctrl);
        robot.press(KeyCode.DELETE).release(KeyCode.DELETE);

        robot.clickOn("#passFld");
        robot.press(ctrl).press(KeyCode.A).release(KeyCode.A).release(ctrl);
        robot.press(KeyCode.DELETE).release(KeyCode.DELETE);

        robot.lookup("#loginBtn");
        robot.clickOn("#loginBtn");
        TextField field = robot.lookup("#userFld").queryAs(TextField.class);
        TextField field2 = robot.lookup("#passFld").queryAs(TextField.class);
        robot.clickOn("OK");
        assertTrue(hasCss(field, "invalid"));
        assertTrue(hasCss(field2,"invalid"));

    }

    @Test
    public void testLoginWrongInfro(FxRobot robot){

        robot.clickOn("#userFld");
        robot.write("mfhfdio428");
        robot.clickOn("#passFld");
        robot.write("on83924nmzecrtg8wg2h");
        robot.lookup("#loginBtn");
        robot.clickOn("#loginBtn");
        TextField field = robot.lookup("#userFld").queryAs(TextField.class);
        TextField field2 = robot.lookup("#passFld").queryAs(TextField.class);
        robot.clickOn("OK");
        assertTrue(hasCss(field,"invalid"));
        assertTrue(hasCss(field2,"invalid"));


    }

    @Test
    public void testLoginUserEmpty(FxRobot robot){
        robot.clickOn("#passFld");
        robot.write("123");
        robot.clickOn("#userFld");
        KeyCode ctrl=KeyCode.CONTROL;
        if (System.getProperty("os.name").equals("Mac OS X"))
            ctrl = KeyCode.COMMAND;
        robot.press(ctrl).press(KeyCode.A).release(KeyCode.A).release(ctrl);
        robot.press(KeyCode.DELETE).release(KeyCode.DELETE);
        TextField field = robot.lookup("#userFld").queryAs(TextField.class);
        TextField field2 = robot.lookup("#passFld").queryAs(TextField.class);
        robot.clickOn("#loginBtn");
        robot.clickOn("OK");
        assertTrue(hasCss(field,"invalid"));
        assertTrue(!hasCss(field2,"invalid"));

    }


    @Test
    public void testLoginPasswordEmpty(FxRobot robot){
        robot.clickOn("#userFld");
        robot.write("abcedf");
        robot.clickOn("#passFld");
        KeyCode ctrl=KeyCode.CONTROL;
        if (System.getProperty("os.name").equals("Mac OS X"))
            ctrl = KeyCode.COMMAND;
        robot.press(ctrl).press(KeyCode.A).release(KeyCode.A).release(ctrl);
        robot.press(KeyCode.DELETE).release(KeyCode.DELETE);
        TextField field = robot.lookup("#userFld").queryAs(TextField.class);
        TextField field2 = robot.lookup("#passFld").queryAs(TextField.class);
        robot.clickOn("#loginBtn");
        robot.clickOn("OK");
        assertTrue(!hasCss(field,"invalid"));
        assertTrue(hasCss(field2,"invalid"));

    }

    @Test
    public void testLoginAllTrue(FxRobot robot){

        robot.clickOn("#userFld");
        KeyCode ctrl = KeyCode.CONTROL;

        //If Os is MAC
        if (System.getProperty("os.name").equals("Mac OS X"))
            ctrl = KeyCode.COMMAND;
        robot.press(ctrl).press(KeyCode.A).release(KeyCode.A).release(ctrl);
        robot.press(KeyCode.DELETE).release(KeyCode.DELETE);
        robot.write("Amer");
        robot.clickOn("#passFld");
        robot.press(ctrl).press(KeyCode.A).release(KeyCode.A).release(ctrl);
        robot.press(KeyCode.DELETE).release(KeyCode.DELETE);
        robot.write("123");
        robot.lookup("#loginBtn");
        robot.clickOn("#loginBtn");
        robot.window("Doctors office");



    }
}