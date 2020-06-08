package project.doctors.office;

import java.sql.SQLException;
import java.util.ArrayList;

public interface PatientDAO {
    void addPatient(Patient patient);
    void updatePatient(Patient patient);
    void deletePatient(Patient patient);
    ArrayList<Patient> getPatients();
    void addAppointment(Appointment appointment);
    void updateAppointment(Appointment appointment);
    ArrayList<Appointment> getAppointments();
    void deleteAppointment(Appointment appointment);
    boolean validate(String user, String password) throws SQLException;
    void close();
}
