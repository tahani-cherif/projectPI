/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.tourisme_sante.entities;

import java.util.logging.Logger;

/**
 *
 * @author Mariem
 */
public class Transport {
 public static  enum TypeTransport {VOITURE,CARE  } 
    private int id;
    private String matricule;
   private TypeTransport transportType;
      private String transportTypeaffiche;
       private float prix;
   
   public Transport(int id, String matricule, TypeTransport transportType,float prix) {
        this.id = id;
        this.matricule = matricule;
        this.transportType = transportType;
        this.prix = prix;

    }
  public Transport(int id, String matricule, String transportType,float prix) {
       this.id = id;
       this.matricule = matricule;
        this.transportTypeaffiche = transportType;
        this.prix=prix;
   }
    public Transport(String matricule, TypeTransport transportType,float prix) {
        this.matricule = matricule;
        this.transportType = transportType;
                this.prix=prix;

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

    public void setMatricule(String matricule) {
        this.matricule = matricule;
    }


    public int getId() {
        return id;
    }

    public String getMatricule() {
        return matricule;
    }

    public TypeTransport getTransportType() {
        return transportType;
    }

    public void setTransportType(TypeTransport transportType) {
        this.transportType = transportType;
    }

    @Override
    public String toString() {
        return "Transport{" + "id=" + id + ", matricule=" + matricule + ", transportType=" + transportType + ", prix=" + prix + '}';
    }


 
 

}
