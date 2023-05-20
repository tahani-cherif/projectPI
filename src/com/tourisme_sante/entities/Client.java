/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.tourisme_sante.entities;

/**
 *
 * @author hamza
 */
public class Client extends Utilisateur{
    private int number;
    private String sex;
    private String adresse;

    public Client(int number, String sex, String adresse, String nom, String prenom, String email, String MDP, String role) {
        super(nom, prenom, email, MDP, role);
        this.number = number;
        this.sex = sex;
        this.adresse = adresse;
    }

    public Client(int number, String sex, String adresse, int id, String nom, String prenom, String email, String MDP, String role) {
        super(id, nom, prenom, email, MDP, role);
        this.number = number;
        this.sex = sex;
        this.adresse = adresse;
    }

    public int getNumber() {
        return number;
    }

    public String getSex() {
        return sex;
    }

    public String getAdresse() {
        return adresse;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    @Override
    public String toString() {
        return "Client{" + "number=" + number + ", sex=" + sex + ", adresse=" + adresse + '}';
    }

    
    
}
