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
 public  enum TypeTransport {
      voiture,
     car  } 
    private int id;
    private String matricule;
   private TypeTransport transportType;
   
   public Transport(int id, String matricule, TypeTransport type) {
        this.id = id;
        this.matricule = matricule;
        this.transportType = transportType;
    }
    public Transport(String matricule, TypeTransport type) {
        this.matricule = matricule;
        this.transportType = transportType;
    }


    public void setId(int id) {
        this.id = id;
    }

    public void setMatricule(String matricule) {
        this.matricule = matricule;
    }

    public void setType(TypeTransport type) {
        this.transportType = type;
    }

    public int getId() {
        return id;
    }

    public String getMatricule() {
        return matricule;
    }

    public TypeTransport getType() {
        return transportType;
    }

 
    @Override
    public String toString() {
        return "Transport{" + "id=" + id + ", matricule=" + matricule + ", type=" + transportType + '}';
    }

}
