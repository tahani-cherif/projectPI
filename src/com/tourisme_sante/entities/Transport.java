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
 public static  enum TypeTransport {
      voiture,
     car  } 
    private int id;
    private String matricule;
   private TypeTransport transportType;
   private String transportTypeaffiche;
   
   public Transport(int id, String matricule, TypeTransport type) {
        this.id = id;
        this.matricule = matricule;
        this.transportType = type;
    }
     public Transport(int id, String matricule, String type) {
        this.id = id;
        this.matricule = matricule;
        this.transportTypeaffiche = type;
    }
    public Transport(String matricule, TypeTransport type) {
        this.matricule = matricule;
        this.transportType = type;
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
        return "Transport{" + "id=" + id + ", matricule=" + matricule + ", type=" + transportType + '}';
    }

}
