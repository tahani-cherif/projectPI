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
 //public static  enum TypeTransport {VOITURE,AUTOBUS  } 
public enum TypeTransport {
    VOITURE("Car"),
    AUTOBUS("Bus");

    private String displayName;

    TypeTransport(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    }
    
 public void settDisplayName(String displayName) {
        this.displayName = displayName;
    }
    public static TypeTransport getByDisplayName(String displayName) {
        for (TypeTransport type : TypeTransport.values()) {
            if (type.getDisplayName().equals(displayName)) {
                return type;
            }
        }
        throw new IllegalArgumentException("Invalid display name: " + displayName);
    }
}
    private int id;
    private String matricule;
   private String transportType;
      private String transportTypeaffiche;
       private float prix;
       private int idAgence;
       String x;
       private String nomAgence;

    public void setNomAgence(String nomAgence) {
        this.nomAgence = nomAgence;
    }

    public String getNomAgence() {
        return nomAgence;
    }
         public Transport(int id, String matricule, String transportType,float prix,int idAgence,String nomAgence) {
        this.id = id;
        this.matricule = matricule;
        this.transportType = transportType;
        this.prix = prix;
        this.idAgence=idAgence;
        this.nomAgence=nomAgence;

    }
   
   public Transport(int id, String matricule, String transportType,float prix,int idAgence) {
        this.id = id;
        this.matricule = matricule;
        this.transportType = transportType;
        this.prix = prix;
        this.idAgence=idAgence;

    }
//  public Transport(int id, String matricule, String transport,float prix,int idAgence) {
//       this.id = id;
//       this.matricule = matricule;
//        this.x = transport;
//        this.prix=prix;
//        this.idAgence=idAgence;
//   }

    public String getTransportTypeaffiche() {
        return transportTypeaffiche;
    }

    public void setTransportTypeaffiche(String transportTypeaffiche) {
        this.transportTypeaffiche = transportTypeaffiche;
    }

    public String getX() {
        return x;
    }

    public void setX(String x) {
        this.x = x;
    }
    public Transport(String matricule, String transportType,float prix,int idAgence) {
        this.matricule = matricule;
        this.transportType = transportType;
                this.prix=prix;
                this.idAgence=idAgence;

    }

    public int getIdAgence() {
        return idAgence;
    }

    public void setIdAgence(int idAgence) {
        this.idAgence = idAgence;
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

    public String getTransportType() {
        return transportType;
    }

    public void setTransportType(String transportType) {
        this.transportType = transportType;
    }

    @Override
    public String toString() {
        return "Transport{" + "id=" + id + ", matricule=" + matricule + ", transportType=" + transportType + ", prix=" + prix +  ", idAgence=" + idAgence + '}';
    }


 
 

}
