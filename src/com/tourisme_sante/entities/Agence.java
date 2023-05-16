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
    private double prix;

      public Agence(int id, String nom, String adresse, int telephone,double prix) {
        this.id = id;
        this.nom = nom;
        this.adresse = adresse;
        this.telephone = telephone;
        this.prix=prix;
    }

    public Agence(String nom, String adresse, int telephone,double prix) {
        this.nom = nom;
        this.adresse = adresse;
        this.telephone = telephone;
                this.prix=prix;

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

   public void setPrix(double prix) {
        this.prix = prix;
    }

    public double getPrix() {
        return prix;
    }

  @Override
    public String toString() {
        return "Agence{" + "id=" + id + ", nom=" + nom + ", adresse=" + adresse + ", telephone=" + telephone + ", prix=" + prix +'}';
    }

   
 

}
