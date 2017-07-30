/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mediaid.dt.medical.doctor.mediaiddoctor.beans;

import java.io.Serializable;

/**
 * @author user
 */
public class LabReportBean implements Serializable {
    private int patient_id, test_id, doctor_id;
    private String test_date, doctor_name, patient_name;
    private double rbc, haemoglobin, mcv, wbv, neutrophils, lymphocytes, monocytes, esoinophils, basophils, platelets, blood_urea, serum_uric_acid, serum_bilirubin, alkaline_phosphate, sodium, potassium, chlorides, calcium, phosphorous, acid_phosphates, proteins, albumin, fasting_blood_sugar;

    public int getPatient_id() {
        return patient_id;
    }

    public void setPatient_id(int patient_id) {
        this.patient_id = patient_id;
    }

    public int getTest_id() {
        return test_id;
    }

    public void setTest_id(int test_id) {
        this.test_id = test_id;
    }

    public double getRbc() {
        return rbc;
    }

    public void setRbc(double rbc) {
        this.rbc = rbc;
    }

    public double getHaemoglobin() {
        return haemoglobin;
    }

    public void setHaemoglobin(double haemoglobin) {
        this.haemoglobin = haemoglobin;
    }

    public double getMcv() {
        return mcv;
    }

    public void setMcv(double mcv) {
        this.mcv = mcv;
    }

    public double getWbv() {
        return wbv;
    }

    public void setWbv(double wbv) {
        this.wbv = wbv;
    }

    public double getNeutrophils() {
        return neutrophils;
    }

    public void setNeutrophils(double neutrophils) {
        this.neutrophils = neutrophils;
    }

    public double getLymphocytes() {
        return lymphocytes;
    }

    public void setLymphocytes(double lymphocytes) {
        this.lymphocytes = lymphocytes;
    }

    public double getMonocytes() {
        return monocytes;
    }

    public void setMonocytes(double monocytes) {
        this.monocytes = monocytes;
    }

    public double getEsoinophils() {
        return esoinophils;
    }

    public void setEsoinophils(double esoinophils) {
        this.esoinophils = esoinophils;
    }

    public double getBasophils() {
        return basophils;
    }

    public void setBasophils(double basophils) {
        this.basophils = basophils;
    }

    public double getPlatelets() {
        return platelets;
    }

    public void setPlatelets(double platelets) {
        this.platelets = platelets;
    }

    public double getBlood_urea() {
        return blood_urea;
    }

    public void setBlood_urea(double blood_urea) {
        this.blood_urea = blood_urea;
    }

    public double getSerum_uric_acid() {
        return serum_uric_acid;
    }

    public void setSerum_uric_acid(double serum_uric_acid) {
        this.serum_uric_acid = serum_uric_acid;
    }

    public double getSerum_bilirubin() {
        return serum_bilirubin;
    }

    public void setSerum_bilirubin(double serum_bilirubin) {
        this.serum_bilirubin = serum_bilirubin;
    }

    public double getAlkaline_phosphate() {
        return alkaline_phosphate;
    }

    public void setAlkaline_phosphate(double alkaline_phosphate) {
        this.alkaline_phosphate = alkaline_phosphate;
    }

    public double getSodium() {
        return sodium;
    }

    public void setSodium(double sodium) {
        this.sodium = sodium;
    }

    public double getPotassium() {
        return potassium;
    }

    public void setPotassium(double potassium) {
        this.potassium = potassium;
    }

    public double getChlorides() {
        return chlorides;
    }

    public void setChlorides(double chlorides) {
        this.chlorides = chlorides;
    }

    public double getCalcium() {
        return calcium;
    }

    public void setCalcium(double calcium) {
        this.calcium = calcium;
    }

    public double getPhosphorous() {
        return phosphorous;
    }

    public void setPhosphorous(double phosphorous) {
        this.phosphorous = phosphorous;
    }

    public double getAcid_phosphates() {
        return acid_phosphates;
    }

    public void setAcid_phosphates(double acid_phosphates) {
        this.acid_phosphates = acid_phosphates;
    }

    public double getProteins() {
        return proteins;
    }

    public void setProteins(double proteins) {
        this.proteins = proteins;
    }

    public double getAlbumin() {
        return albumin;
    }

    public void setAlbumin(double albumin) {
        this.albumin = albumin;
    }

    public double getFasting_blood_sugar() {
        return fasting_blood_sugar;
    }

    public void setFasting_blood_sugar(double fasting_blood_sugar) {
        this.fasting_blood_sugar = fasting_blood_sugar;
    }

    public int getDoctor_id() {
        return doctor_id;
    }

    public void setDoctor_id(int doctor_id) {
        this.doctor_id = doctor_id;
    }

    public String getTest_date() {
        return test_date;
    }

    public void setTest_date(String test_date) {
        this.test_date = test_date;
    }

    public String getDoctor_name() {
        return doctor_name;
    }

    public void setDoctor_name(String doctor_name) {
        this.doctor_name = doctor_name;
    }

    public String getPatient_name() {
        return patient_name;
    }

    public void setPatient_name(String patient_name) {
        this.patient_name = patient_name;
    }


}
