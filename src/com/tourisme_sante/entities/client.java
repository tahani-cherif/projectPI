/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.tourisme_sante.entities;

/**
 *
 * @author LENOVO
 */
public class client extends Utilisateur{
   private int number;
   private String sex;
    public client(int id,String nom, String prenom, String email, String MDP,int number,String role) {
        super(id,nom, prenom, email, MDP,role);
        this.number=number;
    }

 

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }
    
    
}
