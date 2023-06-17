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
public class Panier {
    
    private long id;
    private long idProduit;
    private long idClient;
    private double prix;
    private int qt;

    public Panier(){
        
    }
    public Panier(long idProduit, long idClient, double prix,int qt) {
      
        this.idProduit = idProduit;
        this.idClient = idClient;
        this.prix = prix;
          this.qt = qt;
    }
    
    public Panier(long id, long idProduit, int idClient, double prix) {
        this.id = id;
        this.idProduit = idProduit;
        this.idClient = idClient;
        this.prix = prix;
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

   

    public long getIdClient() {
        return idClient;
    }

    public void setIdClient(long idClient) {
        this.idClient = idClient;
    }

    public double getPrix() {
        return prix;
    }

    public void setPrix(double prix) {
        this.prix = prix;
    }

    public int getQt() {
        return qt;
    }

    public void setQt(int qt) {
        this.qt = qt;
    }
    
    
    
    
}
