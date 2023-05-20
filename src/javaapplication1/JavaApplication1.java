/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package javaapplication1;

import com.tourisme_sante.entities.Admin;
import com.tourisme_sante.entities.Client;
import com.tourisme_sante.entities.RDV;
import com.tourisme_sante.entities.Transport;
import com.tourisme_sante.entities.TypeInterventions;
import com.tourisme_sante.entities.Utilisateur;
import com.tourisme_sante.entities.medecins;
import com.tourisme_sante.entities.produit;
import comm.tourisme_sante.services.ServiceProduit;
import comm.tourisme_sante.services.ServiceTransport;
import comm.tourisme_sante.services.Services_TypeInterventions;
import comm.tourisme_sante.services.serviceMedecin;
import comm.tourisme_sante.services.serviceRDV;
import comm.tourisme_sante.services.serviceUtilisateur;
import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;

/**
 *
 * @author LENOVO
 */
public class JavaApplication1 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws ParseException {
        
      serviceUtilisateur su=new serviceUtilisateur();
    //  Utilisateur x= new Admin("achref","hamza","achref@com,fr","test","Admin");
      // Utilisateur x= new Client(23752236,"homme","mnihla","achref","hamza","achref@com,fr","test","client");
      //su.ajouter(x);
    //  Utilisateur x=new Admin (1,"achref222222","hamzewi","achref@gmail.com","test","Admin");
        //Utilisateur x= new Client(23752236,"homme","mnihla",3,"xwvxvx","hamza","achref@com,fr","test","client");
        //  su.modifier(x);
     //   Utilisateur x= new Client(23752236,"homme","mnihla",3,"xwvxvx","hamza","achref@com,fr","test","client");
        //su.supprimer(x);
      //su.supprimer(new Utilisateur(2,"achref2","hamzewi","achref@gmail.com","test"));
      System.out.println(su.afficher());
     
    }
    
}
