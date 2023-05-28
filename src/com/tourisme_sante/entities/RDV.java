/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.tourisme_sante.entities;

import java.sql.Date;

/**
 *
 * @author LENOVO
 */
public class RDV {
    
    private int id;
    private int idmedecin;
    private int iduser;
    private Date dateRDV;
    private String fullName;
    private String nomuser;

    public RDV(int id,int idmedecin, int iduser, String fullName, String nom, Date dateRDV) {
       this.idmedecin = idmedecin;
        this.iduser = iduser;
        this.id = id;
        this.fullName = fullName;
        this.nomuser = nom;
        this.dateRDV = dateRDV;
    }


    public RDV(int idmedecin, int iduser, Date dateRDV) {
        this.idmedecin = idmedecin;
        this.iduser = iduser;
        this.dateRDV = dateRDV;
    }

    public RDV(int id, int idmedecin, int iduser, Date dateRDV) {
        this.id = id;
        this.idmedecin = idmedecin;
        this.iduser = iduser;
        this.dateRDV = dateRDV;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdmedecin() {
        return idmedecin;
    }

    public void setIdmedecin(int idmedecin) {
        this.idmedecin = idmedecin;
    }

    public int getIduser() {
        return iduser;
    }

    public void setIduser(int iduser) {
        this.iduser = iduser;
    }

    public Date getDateRDV() {
        return dateRDV;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getNomuser() {
        return nomuser;
    }

    public void setNomuser(String nomuser) {
        this.nomuser = nomuser;
    }

    public void setDateRDV(Date dateRDV) {
        this.dateRDV = dateRDV;
    }

    @Override
    public String toString() {
        return "RDV{" + "id=" + id + ", idmedecin=" + idmedecin + ", iduser=" + iduser + ", dateRDV=" + dateRDV + ", fullName=" + fullName + ", nommedecin=" + nomuser + '}';
    }           
}
