/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mediaid.dt.medical.doctor.mediaiddoctor.beans;

/**
 * @author user
 */
public class PatientBean {
    private int patient_id, user_id;
    private String patient_name, patient_dob, patient_bloodgroup, user_name, user_password, user_email, user_contact;
    private String user_type;
    private boolean user_status, patient_gender;

    public int getPatient_id() {
        return patient_id;
    }

    public void setPatient_id(int patient_id) {
        this.patient_id = patient_id;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public String getPatient_name() {
        return patient_name;
    }

    public void setPatient_name(String patient_name) {
        this.patient_name = patient_name;
    }

    public String getPatient_dob() {
        return patient_dob;
    }

    public void setPatient_dob(String patient_dob) {
        this.patient_dob = patient_dob;
    }

    public String getPatient_bloodgroup() {
        return patient_bloodgroup;
    }

    public void setPatient_bloodgroup(String patient_bloodgroup) {
        this.patient_bloodgroup = patient_bloodgroup;
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

    public boolean isUser_status() {
        return user_status;
    }

    public void setUser_status(boolean user_status) {
        this.user_status = user_status;
    }

    public String getUser_type() {
        return "patient";
    }

    public boolean isPatient_gender() {
        return patient_gender;
    }

    public void setPatient_gender(boolean patient_gender) {
        this.patient_gender = patient_gender;
    }


}
