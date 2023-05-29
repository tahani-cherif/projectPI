/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.agence_medicale;


/**
 *
 * @author Chimou
 */
public class Interventions {
    
    
        private int id;
        private String nomType;
        private int prix;
        private String descripition;
        private	int idmedecin;
        private int idtypeintervention;
        private String nomTypeIntervention;
          private String medecin;
        

    public Interventions  (String nomType, int prix, String descripition, int idmedecin , int idtypeintervention ) {
        this.nomType = nomType;
        this.prix=prix;
        this.descripition= descripition;
        this.idmedecin= idmedecin;
        this.idtypeintervention=idtypeintervention;
    }
      public Interventions (int id, String nomType,int prix, String descripition, int idmedecin, int idtypeintervention ) {
        this.id = id;
        this.nomType = nomType;
        this.prix=prix;
        this.descripition = descripition;
        this.idmedecin= idmedecin;
        this.idtypeintervention=idtypeintervention;
    }

    public Interventions(int id, String nomType, int prix, String descripition, int idmedecin, int idtypeintervention, String nomTypeIntervention, String medecin) {
        this.id = id;
        this.nomType = nomType;
        this.prix = prix;
        this.descripition = descripition;
        this.idmedecin = idmedecin;
        this.idtypeintervention = idtypeintervention;
        this.nomTypeIntervention = nomTypeIntervention;
        this.medecin = medecin;
    }

    public String getDescripition() {
        return descripition;
    }

    public void setDescripition(String descripition) {
        this.descripition = descripition;
    }

    public String getNomTypeIntervention() {
        return nomTypeIntervention;
    }

    public void setNomTypeIntervention(String nomTypeIntervention) {
        this.nomTypeIntervention = nomTypeIntervention;
    }

    public String getMedecin() {
        return medecin;
    }

    public void setMedecin(String medecin) {
        this.medecin = medecin;
    }
      
      
      

    //public Interventions(String text, String text0) {
        //throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    //}
       public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    public String getNomType() {
        return nomType;
    }

    public void setNomType(String nomType) {
        this.nomType = nomType;
    }
    
     public double getPrix() {
        return prix;
    }

    public void setPrix(int prix) {
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
        return "Interventions{" + "id=" + id + ", nomType=" + nomType + ", prix=" + prix + ", descripition=" + descripition+ '}';
    }
       public int getIdmedecin() {
        return idmedecin;
    }
        public void setIdmedecin(int idmedecin) {
        this.idmedecin = idmedecin;
    }

         public int getIdtypeintervention() {
        return idtypeintervention;
    }

    public void setIdtypeintervention(int idtypeintervention) {
        this.idtypeintervention = idtypeintervention;
    }

      

    
}
