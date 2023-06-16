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
    private int classification;   
    private String email;
    private int telephone;
    private float prix;
     private int likes;

    public void setNomAgence(String nomAgence) {
        this.nomAgence = nomAgence;
    }
     private int idAgence;
private String nomAgence;
    public void setIdAgence(int idAgence) {
        this.idAgence = idAgence;
    }

    public String getNomAgence() {
        return nomAgence;
    }

    public int getIdAgence() {
        return idAgence;
    }

    public void setLikes(int likes) {
        this.likes = likes;
    }

    public int getLikes() {
        return likes;
    }

        public Hotel(int id, String nom, String adresse,int classification, String email, int telephone,float prix,int likes,int idAgence,String nomAgence ) {
         this.id = id;
        this.nom = nom;
        this.adresse=adresse;
        this.classification = classification;
        this.email = email;
        this.telephone = telephone;
        this.prix = prix;
        this.likes=likes;
        this.idAgence=idAgence;
        this.nomAgence=nomAgence;
    }


   public Hotel(int id, String nom, String adresse,int classification, String email, int telephone,float prix,int likes,int idAgence) {
        this.id = id;
        this.nom = nom;
        this.adresse=adresse;
        this.classification = classification;
        this.email = email;
        this.telephone = telephone;
        this.prix = prix;
        this.likes=likes;
        this.idAgence=idAgence;

    }

    public Hotel(String nom, String adresse, int classification, String email, int telephone,float prix,int likes,int idAgence) {
        this.nom = nom;
        this.adresse = adresse;
        this.classification = classification;
        this.email = email;
        this.telephone = telephone;
        this.prix = prix;
                this.likes=likes;
                        this.idAgence=idAgence;



    }

    public void setPrix(float prix) {
        this.prix = prix;
    }

    public float getPrix() {
        return prix;
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

    public void setClassification(int classification) {
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

    public int getClassification() {
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
        return "Hotel{" + "id=" + id + ", nom=" + nom + ", adresse=" + adresse + ", classification=" + classification + ", email=" + email + ", telephone=" + telephone + ", prix=" + prix + ", likes=" + likes + ", idAgence=" + idAgence + '}';
    }





  
 


}
