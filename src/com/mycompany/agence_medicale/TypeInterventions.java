/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.agence_medicale;


/**
 *
 * @author Chimou
 */
public class TypeInterventions {
    
        private int id;
        private String nom;
        private String deescripition;

    public TypeInterventions  (String nom, String deescripition ) {
        this.nom = nom;
        this.deescripition= deescripition;
    }
      public TypeInterventions (int id, String nom, String deescripition ) {
        this.id = id;
        this.nom = nom;
        this.deescripition = deescripition;
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

    public String getDeescripition() {
        return deescripition;
    }

    public void setDeescripition(String deescripition) {
        this.deescripition = deescripition;
    }

    

    

    @Override
    public String toString() {
        return "TypeInterventions{" + "id=" + id + ", nom=" + nom + ", deescripition=" + deescripition+ '}';
    }

      


}
