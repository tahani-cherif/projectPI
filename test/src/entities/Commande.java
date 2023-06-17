/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entities;

import java.util.List;

/**
 *
 * @author omarb
 */
public class Commande {
    
    private long id;
    private long idProduit;
    private String datePassation;
    private int quantite;
    private long idClient;
    private double mt;
    
    public Commande(){
        
    }
     public Commande(long id, long idProduit, String datePassation, int quantite, long idClient, double mt) {
         this.id= id;
        this.idProduit = idProduit;
        this.datePassation = datePassation;
        this.quantite = quantite;
        this.idClient = idClient;
        this.mt = mt;
    }


    public Commande(long idProduit, String datePassation, int quantite, long idClient, double mt) {
        this.idProduit = idProduit;
        this.datePassation = datePassation;
        this.quantite = quantite;
        this.idClient = idClient;
        this.mt = mt;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getIdProduit() {
        return idProduit;
    }

    public void setIdProduit(long idProduit) {
        this.idProduit = idProduit;
    }

   

    public String getDatePassation() {
        return datePassation;
    }

    public void setDatePassation(String datePassation) {
        this.datePassation = datePassation;
    }

    public int getQuantite() {
        return quantite;
    }

    public void setQuantite(int quantite) {
        this.quantite = quantite;
    }

    public long getIdClient() {
        return idClient;
    }

    public void setIdClient(long idClient) {
        this.idClient = idClient;
    }

    public double getMt() {
        return mt;
    }

    public void setMt(double mt) {
        this.mt = mt;
    }
    
    
    
    
    
    
    
}
