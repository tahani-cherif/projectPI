/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.tourisme_sante.entities;
/**
 *
 * @author USER
 */
public class Commande {
    
    private int id;
    private int datePassation;
    private int quantite;
    private double MT;

    public Commande(int datePassation, int quantite, double MT) {
        this.datePassation = datePassation;
        this.quantite = quantite;
        this.MT = MT;
    }

    public Commande(int id, int datePassation, int quantite, double MT) {
        this.id = id;
        this.datePassation = datePassation;
        this.quantite = quantite;
        this.MT = MT;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getDatePassation() {
        return datePassation;
    }

    public void setDatePassation(int datePassation) {
        this.datePassation = datePassation;
    }

    public int getQuantite() {
        return quantite;
    }

    public void setQuantite(int quantite) {
        this.quantite = quantite;
    }

    public double getMT() {
        return MT;
    }

    public void setMT(double MT) {
        this.MT = MT;
    }
    
    @Override
    public String toString() {
        return "Commande{" + "id=" + id + ", datePassation=" + datePassation + ", quantite=" + quantite + ", MT=" + MT + '}';
    }
}
