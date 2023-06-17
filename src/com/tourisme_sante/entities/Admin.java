/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.tourisme_sante.entities;

/**
 *
 * @author hamza
 */
public class Admin extends Utilisateur{

    public Admin(String nom, String prenom, String email, String MDP, String role) {
        super(nom, prenom, email, MDP, role);
    }

    public Admin(int id, String nom, String prenom, String email, String MDP, String role) {
        super(id, nom, prenom, email, MDP, role);
    }

    @Override
    public String toString() {
        return super.toString();
    }
    
    
    
}
