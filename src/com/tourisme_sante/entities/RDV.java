/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.tourisme_sante.entities;

import java.sql.Date;
import java.time.ZonedDateTime;

/**
 *
 * @author LENOVO
 */
public class RDV {
    
    private int id;
    private int idmedecin;
    private int iduser;
    private Date dateRDV;
    private ZonedDateTime date;
    private String fullName;
    private String nomuser;
    private String heureRDV;

    public RDV(int id,int idmedecin, int iduser, String fullName, String nom, Date dateRDV,String heureRDV) {
       this.idmedecin = idmedecin;
        this.iduser = iduser;
        this.id = id;
        this.fullName = fullName;
        this.nomuser = nom;
        this.dateRDV = dateRDV;
        this.heureRDV=heureRDV;
    }
        public RDV(int id,int idmedecin, int iduser, String fullName, String nom, ZonedDateTime dateRDV) {
       this.idmedecin = idmedecin;
        this.iduser = iduser;
        this.id = id;
        this.fullName = fullName;
        this.nomuser = nom;
        this.date = dateRDV;
    }

    public ZonedDateTime getDate() {
        return date;
    }

    public String getHeureRDV() {
        return heureRDV;
    }

    public void setHeureRDV(String heureRDV) {
        this.heureRDV = heureRDV;
    }

    public void setDate(ZonedDateTime date) {
        this.date = date;
    }


    public RDV(int idmedecin, int iduser, Date dateRDV,String heureRDV) {
        this.idmedecin = idmedecin;
        this.iduser = iduser;
        this.dateRDV = dateRDV;
        this.heureRDV=heureRDV;
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
