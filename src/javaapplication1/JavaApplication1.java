/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package javaapplication1;

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
        ServiceProduit sp=new ServiceProduit();
        //sp.ajouter(new produit(10,10,10));
       // sp.modifier(new produit(2,20,20,20));
      // sp.supprimer(new produit(2,20,20,20));
      System.out.println(sp.afficher());
      serviceUtilisateur su=new serviceUtilisateur();
      //su.ajouter(new Utilisateur("achref","hamzewi","achref@gmail.com","test"));
      //su.modifier(new Utilisateur(2,"achref2","hamzewi","achref@gmail.com","test"));
      //su.supprimer(new Utilisateur(2,"achref2","hamzewi","achref@gmail.com","test"));
      System.out.println(su.afficher());
      Services_TypeInterventions sti=new Services_TypeInterventions();
      //sti.ajouter(new TypeInterventions("test",10.0,"test"));
     //sti.modifier(new TypeInterventions(2,"test222",10.0,"test"));
     //sti.supprimer(new TypeInterventions(2,"test222",10.0,"test"));
      System.out.println(sti.afficher());
      ServiceTransport st=new ServiceTransport();
      st.ajouter(new Transport("123tn244",Transport.TypeTransport.voiture));
    }
    
}
