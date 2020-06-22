package project.doctors.office;

import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;

import java.time.LocalDate;

public class Patient {

     private SimpleStringProperty name, lastname, adress, phone,complaint;
     private SimpleIntegerProperty securityNum;
     private SimpleObjectProperty<LocalDate>birthday;
     private  SimpleDoubleProperty weight, height;
     private int id;

    public Patient(int id, String name, String lastname, int securityNum, String phone, String adress, String complaint, double weight, double height, LocalDate birthday) {
        this.id = id;
        this.name = new SimpleStringProperty(name);
        this.lastname = new SimpleStringProperty(lastname);
        this.securityNum=new SimpleIntegerProperty(securityNum);
        this.adress = new SimpleStringProperty(adress);
        this.phone = new SimpleStringProperty(phone);
        this.complaint = new SimpleStringProperty(complaint);
        this.birthday = new SimpleObjectProperty<>(birthday);
        this.weight = new SimpleDoubleProperty(weight);
        this.height = new SimpleDoubleProperty(height);
    }

    public Patient() {
        PatientDAOBase patientDAOBase = PatientDAOBase.getInstance();
        this.id=patientDAOBase.getNextPatientID();
        this.name = new SimpleStringProperty("");
        this.lastname = new SimpleStringProperty("");
        this.securityNum=new SimpleIntegerProperty(0);
        this.adress = new SimpleStringProperty("");
        this.phone = new SimpleStringProperty("");
        this.complaint = new SimpleStringProperty("");
        this.birthday = new SimpleObjectProperty<>(LocalDate.now());
        this.weight = new SimpleDoubleProperty(0.0);
        this.height = new SimpleDoubleProperty(0.0);
    }

    @Override
    public String toString() {
        return getName() + " " + getLastname() + ", " + getSecurityNum();
    }

    public String getName() {
        return name.get();
    }

    public SimpleStringProperty nameProperty() {
        return name;
    }

    public void setName(String name) {
        this.name.set(name);
    }

    public String getLastname() {
        return lastname.get();
    }

    public SimpleStringProperty lastnameProperty() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname.set(lastname);
    }

    public String getAdress() {
        return adress.get();
    }

    public SimpleStringProperty adressProperty() {
        return adress;
    }

    public void setAdress(String adress) {
        this.adress.set(adress);
    }

    public String getPhone() {
        return phone.get();
    }

    public int getSecurityNum() {
        return securityNum.get();
    }

    public SimpleIntegerProperty securityNumProperty() {
        return securityNum;
    }

    public void setSecurityNum(int securityNum) {
        this.securityNum.set(securityNum);
    }

    public SimpleStringProperty phoneProperty() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone.set(phone);
    }

    public String getComplaint() {
        return complaint.get();
    }

    public SimpleStringProperty complaintProperty() {
        return complaint;
    }

    public void setComplaint(String complaint) {
        this.complaint.set(complaint);
    }

    public LocalDate getBirthday() {
        return birthday.get();
    }

    public SimpleObjectProperty<LocalDate> birthdayProperty() {
        return birthday;
    }

    public void setBirthday(LocalDate birthday) {
        this.birthday.set(birthday);
    }

    public double getWeight() {
        return weight.get();
    }

    public SimpleDoubleProperty weightProperty() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight.set(weight);
    }

    public double getHeight() {
        return height.get();
    }

    public SimpleDoubleProperty heightProperty() {
        return height;
    }

    public void setHeight(double height) {
        this.height.set(height);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
