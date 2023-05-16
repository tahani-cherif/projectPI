/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.tourisme_sante.entities;

/**
 *
 * @author Mariem
 */
public class Hotel {
    private int id;
    private String nom;
   private String adresse;
    private String classification;   
    private String email;
    private int telephone;

   public Hotel(int id, String nom, String classification, String email, int telephone) {
        this.id = id;
        this.nom = nom;
        this.classification = classification;
        this.email = email;
        this.telephone = telephone;
    }

    public Hotel(String nom, String adresse, String classification, String email, int telephone) {
        this.nom = nom;
        this.adresse = adresse;
        this.classification = classification;
        this.email = email;
        this.telephone = telephone;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public void setClassification(String classification) {
        this.classification = classification;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setTelephone(int telephone) {
        this.telephone = telephone;
    }

    public int getId() {
        return id;
    }

    public String getNom() {
        return nom;
    }

    public String getAdresse() {
        return adresse;
    }

    public String getClassification() {
        return classification;
    }

    public String getEmail() {
        return email;
    }

    public int getTelephone() {
        return telephone;
    }

  
       @Override
    public String toString() {
        return "Hotel{" + "id=" + id + ", nom=" + nom + ", adresse=" + adresse + ", classification=" + classification + ", email=" + email + ", telephone=" + telephone + '}';
    }

    public String getMatricule() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}
