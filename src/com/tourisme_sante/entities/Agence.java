/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.tourisme_sante.entities;

/**
 *
 * @author Mariem
 */
public class Agence {
    
      private int id;
    private String nom;
    private String adresse;   
    private int telephone;
    private String email;

    public void setEmail(String email) {
        this.email = email;
    }
    

      public Agence(int id, String nom, String adresse, int telephone, String email) {
        this.id = id;
        this.nom = nom;
        this.adresse = adresse;
        this.telephone = telephone;
        this.email=email;
        
    }

    public Agence(String nom, String adresse, int telephone,String email) {
        this.nom = nom;
        this.adresse = adresse;
        this.telephone = telephone;
        this.email=email;

    }

    public String getEmail() {
        return email;
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

    public int getTelephone() {
        return telephone;
    }

    @Override
    public String toString() {
        return "Agence{" + "id=" + id + ", nom=" + nom + ", adresse=" + adresse + ", telephone=" + telephone + ", email=" + email + '}';
    }



   
 

}
