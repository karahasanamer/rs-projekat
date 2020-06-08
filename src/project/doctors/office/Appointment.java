package project.doctors.office;

import java.sql.Time;
import java.sql.Timestamp;
import java.time.LocalDate;

public class Appointment {
    private int id;
    private Patient patient;
    private LocalDate appoDate;
    private Time appoTime;

    public Appointment(int id, Timestamp appoDate, Patient patient) {
        this.id = id;
        this.patient = patient;
        this.appoDate = appoDate.toLocalDateTime().toLocalDate();
        this.appoTime = Time.valueOf(appoDate.toLocalDateTime().toLocalTime());
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

    public LocalDate getAppoDate() {
        return appoDate;
    }

    public void setAppoDate(LocalDate appoDate) {
        this.appoDate = appoDate;
    }

    public Time getAppoTime() {
        return appoTime;
    }

    public void setAppoTime(Time appoTime) {
        this.appoTime = appoTime;
    }
}
