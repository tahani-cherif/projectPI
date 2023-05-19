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

public class Reservation {
	private int id;
	private Date dateDebut;
	private Date dateFin;
	private int idAgence;
	private int idUser;
        private int idHotels;
        private int idTransport;
    public Reservation( Date dateDebut, Date dateFin, int idAgence, int idUser, int idHotels,
 		int idTransport) {
 		this.dateDebut = dateDebut;
 		this.dateFin = dateFin;
 		this.idAgence = idAgence;
 		this.idUser = idUser;
 		this.idHotels = idHotels;
 		this.idTransport = idTransport;
 	}
    public Reservation(int id, Date dateDebut, Date dateFin, int idAgence, int idUser, int idHotels,
 			int idTransport) {
 		this.id = id;
 		this.dateDebut = dateDebut;
 		this.dateFin = dateFin;
 		this.idAgence = idAgence;
 		this.idUser = idUser;
 		this.idHotels = idHotels;
 		this.idTransport = idTransport;
 	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public Date getDateDebut() {
		return dateDebut;
	}
	public void setDateDebut(Date dateDebut) {
		this.dateDebut = dateDebut;
	}
	public Date getDateFin() {
		return dateFin;
	}
	public void setDateFin(Date dateFin) {
		this.dateFin = dateFin;
	}
	public int getIdAgence() {
		return idAgence;
	}
	public void setIdAgence(int idAgence) {
		this.idAgence = idAgence;
	}
	public int getIdUser() {
		return idUser;
	}
	public void setIdUser(int idUser) {
		this.idUser = idUser;
	}
	public int getIdHotels() {
		return idHotels;
	}
	public void setIdHotels(int idHotels) {
		this.idHotels = idHotels;
	}
	public int getIdTransport() {
		return idTransport;
	}
	public void setIdTransport(int idTransport) {
		this.idTransport = idTransport;
	}
	@Override
	public String toString() {
		return "Reservation [id=" + id + ", dateDebut=" + dateDebut + ", dateFin=" + dateFin + ", idAgence=" + idAgence
				+ ", idUser=" + idUser + ", idHotels=" + idHotels + ", idTransport=" + idTransport + "]";
	}
    
}
