/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mediaid.dt.medical.doctor.mediaiddoctor.beans;

/**
 * @author user
 */
public class DoctorBean {
    private int doctor_id, user_id, hospital_id;
    private String doctor_name, doctor_address, doctor_dob, doctor_specialization, doctor_qualification, doctor_precautions, doctor_medicines, user_name, user_password, user_email, user_contact, user_type;
    private boolean doctor_gender, user_status;

    public int getDoctor_id() {
        return doctor_id;
    }

    public void setDoctor_id(int doctor_id) {
        this.doctor_id = doctor_id;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public int getHospital_id() {
        return hospital_id;
    }

    public void setHospital_id(int hospital_id) {
        this.hospital_id = hospital_id;
    }

    public String getDoctor_name() {
        return doctor_name;
    }

    public void setDoctor_name(String doctor_name) {
        this.doctor_name = doctor_name;
    }

    public String getDoctor_address() {
        return doctor_address;
    }

    public void setDoctor_address(String doctor_address) {
        this.doctor_address = doctor_address;
    }

    public String getDoctor_specialization() {
        return doctor_specialization;
    }

    public void setDoctor_specialization(String doctor_specialization) {
        this.doctor_specialization = doctor_specialization;
    }

    public String getDoctor_qualification() {
        return doctor_qualification;
    }

    public void setDoctor_qualification(String doctor_qualification) {
        this.doctor_qualification = doctor_qualification;
    }

    public String getDoctor_precautions() {
        return doctor_precautions;
    }

    public void setDoctor_precautions(String doctor_precautions) {
        this.doctor_precautions = doctor_precautions;
    }

    public String getDoctor_medicines() {
        return doctor_medicines;
    }

    public void setDoctor_medicines(String doctor_medicines) {
        this.doctor_medicines = doctor_medicines;
    }

    public String getUser_name() {
        return user_name;
    }

    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }

    public String getUser_password() {
        return user_password;
    }

    public void setUser_password(String user_password) {
        this.user_password = user_password;
    }

    public String getUser_email() {
        return user_email;
    }

    public void setUser_email(String user_email) {
        this.user_email = user_email;
    }

    public String getUser_contact() {
        return user_contact;
    }

    public void setUser_contact(String user_contact) {
        this.user_contact = user_contact;
    }

    public String getUser_type() {
        return user_type;
    }

    public void setUser_type(String user_type) {
        this.user_type = user_type;
    }

    public boolean isDoctor_gender() {
        return doctor_gender;
    }

    public void setDoctor_gender(boolean doctor_gender) {
        this.doctor_gender = doctor_gender;
    }

    public boolean isUser_status() {
        return user_status;
    }

    public void setUser_status(boolean user_status) {
        this.user_status = user_status;
    }

    public String getDoctor_dob() {
        return doctor_dob;
    }

    public void setDoctor_dob(String doctor_dob) {
        this.doctor_dob = doctor_dob;
    }


}
