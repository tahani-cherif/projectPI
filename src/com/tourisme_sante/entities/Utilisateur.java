/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.tourisme_sante.entities;

/**
 *
 * @author hamza
 */
public abstract class Utilisateur {


    
    private int id;
    private String nom;
    private String prenom;
    private String email;
    private String MDP;
    private String role;
    

    public Utilisateur(String nom, String prenom,String email, String MDP,String role) {
         this.nom = nom;
        this.prenom = prenom;
        this.email = email;
        this.MDP = MDP;
        this.role=role;
    }

    public Utilisateur(int id, String nom, String prenom, String email, String MDP,String role) {
        this.id = id;
        this.nom = nom;
        this.prenom = prenom;
        this.email = email;
        this.MDP = MDP;
        this.role=role;
    }
   public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setMDP(String MDP) {
        this.MDP = MDP;
    }

    public String getEmail() {
        return email;
    }

    public String getMDP() {
        return MDP;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    @Override
    public String toString() {
        return "Utilisateur{" + "id=" + id + ", nom=" + nom + ", prenom=" + prenom + ", email=" + email + ", MDP=" + MDP + '}';
    }

}

    

