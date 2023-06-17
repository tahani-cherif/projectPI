/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entities;

/**
 *
 * @author omarb
 */
public class Produit {
    
    private long id;
    private String nom;
    private double prix;
    private int qtDisponible;
    private int qtUtilisee;

    public Produit(){
        
    }
    public Produit(long id,String nom, double prix, int qtDisponible, int qtUtilisee) {
        this.id = id;
        this.nom = nom;
        this.prix = prix;
        this.qtDisponible = qtDisponible;
        this.qtUtilisee = qtUtilisee;
    }
    
    public Produit(String nom,double prix, int qtDisponible, int qtUtilisee) {
        this.nom = nom;
        this.prix = prix;
        this.qtDisponible = qtDisponible;
        this.qtUtilisee = qtUtilisee;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public double getPrix() {
        return prix;
    }

    public void setPrix(double prix) {
        this.prix = prix;
    }

    public int getQtDisponible() {
        return qtDisponible;
    }

    public void setQtDisponible(int qtDisponible) {
        this.qtDisponible = qtDisponible;
    }

    public int getQtUtilisee() {
        return qtUtilisee;
    }

    public void setQtUtilisee(int qtUtilisee) {
        this.qtUtilisee = qtUtilisee;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }
    
    

    @Override
    public String toString() {
        return "Produit{" + "id=" + id + ", prix=" + prix + ", qtDisponible=" + qtDisponible + ", qtUtilisee=" + qtUtilisee + '}';
    }
    
    
    
    
    
    
    
    
}
