/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.tourisme_sante.entities;

/**
 *
 * @author Chimou
 */
public class TypeInterventions {
    
      private int Id;
        private String nomType;
        private Double prix;
        private String descripition;

    public TypeInterventions  (String nomType, Double prix, String descripition ) {
        this.nomType = nomType;
        this.prix=prix;
        this.descripition= descripition;
    }
      public TypeInterventions (int Id, String nomType,Double prix, String descripition ) {
        this.Id = Id;
        this.nomType = nomType;
        this.prix=prix;
        this.descripition = descripition;
    }
       public int getId() {
        return Id;
    }

    public void setId(int Id) {
        this.Id = Id;
    }
    public String getNomType() {
        return nomType;
    }

    public void setNomType(String nomType) {
        this.nomType = nomType;
    }
    
     public Double getPrix() {
        return prix;
    }

    public void setPrix(Double prix) {
        this.prix = prix;
    }

    public String getdescripition() {
        return descripition;
    }

    public void setPrenom(String descripition) {
        this.descripition = descripition;
    }

    @Override
    public String toString() {
        return "Personne{" + "Id=" + Id + ", nomType=" + nomType + ", prix=" + prix + ", descripition=" + descripition+ '}';
    }

      

    
}
