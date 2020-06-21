package project.doctors.office;

import org.junit.jupiter.api.Test;

import java.io.File;
import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class PatientDAOBaseTest {

    @Test
    void addPatient() {
        PatientDAOBase.removeInstance();
        File dbfile = new File("database.db");
        dbfile.delete();
        PatientDAOBase dao = PatientDAOBase.getInstance();

        ArrayList<Patient> patients = dao.getPatients();
        int a=patients.size();
        Patient patient = new Patient();
        patient.setName("Patient");
        patient.setSurname("Sick");
        patient.setBirthday(LocalDate.now());
        patient.setComplaint("Test");
        patient.setHeight(180);
        patient.setWeight(90);
        patient.setAdress("Demo ST");
        patient.setPhone("999-999-999");
        dao.addPatient(patient);

        patients = dao.getPatients();
        assertEquals(a+1,patients.size());
        assertEquals("Patient", patients.get(4).getName());
    }

    @Test
    void deletePatient() {
        PatientDAOBase.removeInstance();
        File dbfile = new File("database.db");
        dbfile.delete();
        PatientDAOBase dao = PatientDAOBase.getInstance();

        ArrayList<Patient> patients = dao.getPatients();
        int a=patients.size();
        dao.deletePatient(dao.getPatients().get(3));
        patients = dao.getPatients();
        assertEquals(a-1, patients.size());
        assertEquals("Davelot", patients.get(0).getName());
        assertEquals("Dany", patients.get(1).getName());
        assertEquals("Johny", patients.get(2).getName());


    }

    @Test
    void addAppointment() {
        PatientDAOBase.removeInstance();
        File dbfile = new File("database.db");
        dbfile.delete();
        PatientDAOBase dao = PatientDAOBase.getInstance();

        Appointment appointment = new Appointment();
        appointment.setId(dao.getNextAppointmentID());
        appointment.setAppoDate(LocalDate.now());
        appointment.setAppoTime(Time.valueOf(LocalTime.now()));
        appointment.setPatient(dao.getPatients().get(1));
        dao.addAppointment(appointment);
        ArrayList<Appointment> appointments = dao.getAppointments();
        assertEquals(4, appointments.size());
    }

    @Test
    void deleteAppointment() {
        PatientDAOBase.removeInstance();
        File dbfile = new File("database.db");
        dbfile.delete();
        PatientDAOBase dao = PatientDAOBase.getInstance();

        dao.deleteAppointment(dao.getAppointments().get(1));
        ArrayList<Appointment> appointments = dao.getAppointments();
        assertEquals(2, appointments.size());


    }

    @Test
    void regenerateFile() {
        PatientDAOBase.removeInstance();
        File dbfile = new File("database.db");
        dbfile.delete();
        PatientDAOBase dao = PatientDAOBase.getInstance();
        ArrayList<Patient> patients = dao.getPatients();
        assertEquals("Davelot", patients.get(0).getName());
        assertEquals("Dany", patients.get(1).getName());
        assertEquals("Johny", patients.get(2).getName());
        assertEquals("Jim", patients.get(3).getName());

    }

}