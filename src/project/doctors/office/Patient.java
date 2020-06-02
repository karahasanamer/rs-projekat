package project.doctors.office;

import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;

import java.time.LocalDate;

public class Patient {

      SimpleStringProperty name,surname, adress, phone,complaint;
      SimpleIntegerProperty securityNum;
      SimpleObjectProperty<LocalDate>birthday;
      SimpleDoubleProperty weight, height;
      int id;

    public Patient(int id,String name, String surname,int securityNum, String adress, String phone, String complaint, LocalDate birthday, double weight, double height) {
        this.id = id;
        this.name = new SimpleStringProperty(name);
        this.surname = new SimpleStringProperty(surname);
        this.securityNum=new SimpleIntegerProperty(securityNum);
        this.adress = new SimpleStringProperty(adress);
        this.phone = new SimpleStringProperty(phone);
        this.complaint = new SimpleStringProperty(complaint);
        this.birthday = new SimpleObjectProperty<>(birthday);
        this.weight = new SimpleDoubleProperty(weight);
        this.height = new SimpleDoubleProperty(height);
    }

    public Patient() {
        this.name = new SimpleStringProperty("");
        this.surname = new SimpleStringProperty("");
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
        return getName() + " " + getSurname() + " " + getSecurityNum();
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

    public String getSurname() {
        return surname.get();
    }

    public SimpleStringProperty surnameProperty() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname.set(surname);
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
