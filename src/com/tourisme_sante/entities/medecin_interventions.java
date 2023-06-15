/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.tourisme_sante.entities;

/**
 *
 * @author LENOVO
 */
public class medecin_interventions {
    
    private int id;
    private int id_medecin;
    private int id_interventios;

    public medecin_interventions(int id_medecin, int id_interventios) {
        this.id_medecin = id_medecin;
        this.id_interventios = id_interventios;
    }

    public medecin_interventions(int id, int id_medecin, int id_interventios) {
        this.id = id;
        this.id_medecin = id_medecin;
        this.id_interventios = id_interventios;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId_medecin() {
        return id_medecin;
    }

    public void setId_medecin(int id_medecin) {
        this.id_medecin = id_medecin;
    }

    public int getId_interventios() {
        return id_interventios;
    }

    public void setId_interventios(int id_interventios) {
        this.id_interventios = id_interventios;
    }

    @Override
    public String toString() {
        return "medecin_interventions{" + "id=" + id + ", id_medecin=" + id_medecin + ", id_interventios=" + id_interventios + '}';
    }
    
    
}
