/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.tourisme_sante.entities;

/**
 *
 * @author hamza
 */
public class Gestionnaire extends Utilisateur {
    int number;
    String adresse;

    public Gestionnaire(int number, String adresse, String nom, String prenom, String email, String MDP, String role) {
        super(nom, prenom, email, MDP, role);
        this.number = number;
        this.adresse = adresse;
    }

    public Gestionnaire(int number, String adresse, int id, String nom, String prenom, String email, String MDP, String role) {
        super(id, nom, prenom, email, MDP, role);
        this.number = number;
        this.adresse = adresse;
    }

    public int getNumber() {
        return number;
    }

    public String getAdresse() {
        return adresse;
    }

    public void setNumber(int numero) {
        this.number = number;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    @Override
    public String toString() {
        return "Gestionnaire{id=" + super.getId() + ", nom=" + super.getNom() + ", prenom=" + super.getPrenom() + ", email=" + super.getEmail() + ", MDP=" + super.getMDP() +",role=" + super.getRole() + "numero=" + number + ", adresse=" + adresse + '}';
    }
    
}
