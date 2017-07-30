/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mediaid.dt.medical.doctor.mediaiddoctor.beans;

/**
 * @author user
 */
public class HospitalBean {
    private int hospital_id;
    private String hospital_name, hospital_contact, hospital_adress;

    public int getHospital_id() {
        return hospital_id;
    }

    public void setHospital_id(int hospital_id) {
        this.hospital_id = hospital_id;
    }

    public String getHospital_name() {
        return hospital_name;
    }

    public void setHospital_name(String hospital_name) {
        this.hospital_name = hospital_name;
    }

    public String getHospital_contact() {
        return hospital_contact;
    }

    public void setHospital_contact(String hospital_contact) {
        this.hospital_contact = hospital_contact;
    }

    public String getHospital_adress() {
        return hospital_adress;
    }

    public void setHospital_adress(String hospital_adress) {
        this.hospital_adress = hospital_adress;
    }


}
