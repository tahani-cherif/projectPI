/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.tourisme_sante.entities;
/**
 *
 * @author Chimou
 */
public class Interventions {
    
        private int idInter;
        private String nom;
        private String deescripition;

    public Interventions  (String nom, String deescripition ) {
        this.nom = nom;
        this.deescripition= deescripition;
    }
      public Interventions (int idInter, String nom, String deescripition ) {
        this.idInter = idInter;
        this.nom = nom;
        this.deescripition = deescripition;
    }
       public int getIdInter() {
        return idInter;
    }

    public void setId(int idInter) {
        this.idInter = idInter;
    }
    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getdeescripition() {
        return deescripition;
    }

    public void setPrenom(String deescripition) {
        this.deescripition = deescripition;
    }

    @Override
    public String toString() {
        return "Personne{" + "idInter=" + idInter + ", nom=" + nom + ", deescripition=" + deescripition+ '}';
    }

}
