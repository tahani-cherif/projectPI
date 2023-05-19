/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package tests;

import java.sql.Date;

import entities.Offre;
import entities.Personne;
import entities.Reservation;
import services.ServicePersonne;
import services.ServicePersonne2;
import services.ServiceReservation;
import services.ServiceOffre;
import utils.DataSource;

/**
 *
 * @author abdel
 */
public class MainProg {
    
    public static void main(String[] args) {
//        ServicePersonne sp = new ServicePersonne();
//        System.out.println(sp.afficher());
        ServiceOffre so = new ServiceOffre();
        ServiceReservation sr = new ServiceReservation();
        sr.ajouter(new Reservation(Date.valueOf("2023-05-14"),Date.valueOf("2023-05-15"),1,1,1,1));
    }
}
