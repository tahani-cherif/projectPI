package com.tourisme_sante.entities;

import java.util.Date;

public class Reservation {
    
	private int id;
	private Date dateDebut;
	private Date dateFin;
	private int idAgence;
	private int idUser;
    private int idHotels;
    private int idTransport;
    private String nom;   
     private String nomHotel;
        private String nomUser; 

    public String getNomUser() {
        return nomUser;
    }

    public void setNomUser(String nomUser) {
        this.nomUser = nomUser;
    }
     
     
    

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

    public Reservation(int id, Date dateDebut, Date dateFin, int idAgence, int idUser, int idHotels, int idTransport, String nom) {
        this.id = id;
        this.dateDebut = dateDebut;
        this.dateFin = dateFin;
        this.idAgence = idAgence;
        this.idUser = idUser;
        this.idHotels = idHotels;
        this.idTransport = idTransport;
        this.nom = nom;
    }
        public Reservation(int id, Date dateDebut, Date dateFin, int idAgence, int idUser, int idHotels, int idTransport, String nom,String nomHotel) {
        this.id = id;
        this.dateDebut = dateDebut;
        this.dateFin = dateFin;
        this.idAgence = idAgence;
        this.idUser = idUser;
        this.idHotels = idHotels;
        this.idTransport = idTransport;
        this.nom = nom;
        this.nomHotel = nomHotel;
    }
            public Reservation(int id, Date dateDebut, Date dateFin, int idAgence, int idUser, int idHotels, int idTransport, String nom,String nomHotel,String nomUser) {
        this.id = id;
        this.dateDebut = dateDebut;
        this.dateFin = dateFin;
        this.idAgence = idAgence;
        this.idUser = idUser;
        this.idHotels = idHotels;
        this.idTransport = idTransport;
        this.nom = nom;
        this.nomHotel = nomHotel;
        this.nomUser =nomUser;
    }
    
	public int getId() {
		return id;
	}
        public String getNom() {
		return nom;
	}
        public String getNomHotel() {
		return nomHotel;
	}
	public void setId(int id) {
		this.id = id;
	}
	public Date getDateDebut() {
		return dateDebut;
	}
        public void setNom(String nom) {
		this.nom = nom;
	}
          public void setNomHotel(String nomHotel) {
		this.nomHotel = nomHotel;
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
				+ ", idUser=" + idUser + ", idHotels=" + idHotels + ", idTransport=" + idTransport +  ", Nom=" + nom +"]";
	}
    
}
