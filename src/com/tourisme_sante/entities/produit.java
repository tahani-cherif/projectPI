/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.tourisme_sante.entities;
/**
 *
 * @author USER
 */
public class produit {
    
    private int id;
    private double prix;
    private int quantiteDisponible;
    private int quantiteUtilisee;

    public produit(double prix, int quantiteDisponible, int quantiteUtilisee) {
        this.prix = prix;
        this.quantiteDisponible = quantiteDisponible;
        this.quantiteUtilisee = quantiteUtilisee;

    }

    public produit(int id, double prix, int quantiteDisponible, int quantiteUtilisee) {
        this.id = id;
        this.prix = prix;
        this.quantiteDisponible = quantiteDisponible;
        this.quantiteUtilisee = quantiteUtilisee;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getPrix() {
        return prix;
    }

    public void setPrix(double prix) {
        this.prix = prix;
    }

    public int getQuantiteDisponible() {
        return quantiteDisponible;
    }

    public void setQuantiteDisponible(int quantiteDisponible) {
        this.quantiteDisponible = quantiteDisponible;
    }

    public int getQuantiteUtilisee() {
        return quantiteUtilisee;
    }

    public void setQuantiteUtilisee(int quantiteUtilisee) {
        this.quantiteUtilisee = quantiteUtilisee;
    }

    @Override
    public String toString() {
        return "produit{" + "id=" + id + ", prix=" + prix + ", quantiteDisponible=" + quantiteDisponible + ", quantiteUtilisee=" + quantiteUtilisee + '}';
    }
}