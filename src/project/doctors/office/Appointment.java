package project.doctors.office;

import java.sql.Time;
import java.time.LocalDate;

public class Appointment {
    private int id;
    private Patient patient;
    private LocalDate date;
    private Time time;

    public Appointment(int id, Patient patient, LocalDate date, Time time) {
        this.id = id;
        this.patient = patient;
        this.date = date;
        this.time = time;
    }

    public Appointment() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Patient getPatient() {
        return patient;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public Time getTime() {
        return time;
    }

    public void setTime(Time time) {
        this.time = time;
    }
}
