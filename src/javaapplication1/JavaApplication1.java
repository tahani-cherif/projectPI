/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package javaapplication1;

import com.tourisme_sante.entities.RDV;
import com.tourisme_sante.entities.medecins;
import comm.tourisme_sante.services.serviceMedecin;
import comm.tourisme_sante.services.serviceRDV;
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
        
        serviceMedecin sm=new serviceMedecin();
        
      //  sm.ajouter(new  medecins ("tahani cherif 2","cheriftahani92@gmail.com","cite cheker rawed",27711622,"test"));
        System.out.println(sm.afficher());
       // sm.modifier(new  medecins (2,"test","cheriftahani92@gmail.com","cite cheker rawed",27711622,"test"));
   //    sm.supprimer(new  medecins (2,"test","cheriftahani92@gmail.com","cite cheker rawed",27711622,"test"));
   serviceRDV srdv=new serviceRDV();
  // srdv.ajouter(new RDV(1,1,Date.valueOf("2023-05-15")));
  //srdv.modifier(new RDV(2,1,1,Date.valueOf("2023-06-15")));
  //srdv.supprimer(new RDV(2,1,1,Date.valueOf("2023-05-15")));
   System.out.println(srdv.afficher());
      
    }
    
}
