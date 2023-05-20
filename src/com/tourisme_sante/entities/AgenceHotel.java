/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.tourisme_sante.entities;

/**
 *
 * @author 21626
 */
public class AgenceHotel {
        private int idAgenceHotel;
         private int idAgence;
         private int idHotel;


  public AgenceHotel(int idAgence, int idHotel) {
        this.idAgence = idAgence;
        this.idHotel = idHotel;
    }

    public AgenceHotel(int idAgenceHotel, int idAgence, int idHotel) {
        this.idAgenceHotel = idAgenceHotel;
        this.idAgence = idAgence;
        this.idHotel = idHotel;
    }
    public void setIdAgenceHotel(int idAgenceHotel) {
        this.idAgenceHotel = idAgenceHotel;
    }

    public void setIdAgence(int idAgence) {
        this.idAgence = idAgence;
    }

    public void setIdHotel(int idHotel) {
        this.idHotel = idHotel;
    }

    public int getIdAgenceHotel() {
        return idAgenceHotel;
    }

    public int getIdAgence() {
        return idAgence;
    }

    public int getIdHotel() {
        return idHotel;
    }

  
    @Override
    public String toString() {
        return "AgenceHotel{" + "idAgenceHotel=" + idAgenceHotel + ", idAgence=" + idAgence + ", idHotel=" + idHotel + '}';
    }
}
