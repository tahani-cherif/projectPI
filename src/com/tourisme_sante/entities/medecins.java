/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.tourisme_sante.entities;

import java.util.ArrayList;
import javafx.scene.control.Button;

/**
 *
 * @author LENOVO
 */
public class medecins {
    
    private int id;
    private String fullName;
    private String email;
    private String adresse;
    private int numero;
    private String specialite;

    public medecins(String fullName, String email, String adresse, int numero, String specialite) {
        this.fullName = fullName;
        this.email = email;
        this.adresse = adresse;
        this.numero = numero;
        this.specialite = specialite;

    }

  
       public medecins(int id, String fullName, String email, String adresse, int numero, String specialite) {
        this.id = id;
        this.fullName = fullName;
        this.email = email;
        this.adresse = adresse;
        this.numero = numero;
        this.specialite = specialite;
  
    }

    
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public String getSpecialite() {
        return specialite;
    }

    public void setSpecialite(String specialite) {
        this.specialite = specialite;
    }

    @Override
    public String toString() {
        return "medecins{" + "id=" + id + ", fullName=" + fullName + ", email=" + email + ", adresse=" + adresse + ", numero=" + numero + ", specialite=" + specialite + '}';
    }
    
    
}