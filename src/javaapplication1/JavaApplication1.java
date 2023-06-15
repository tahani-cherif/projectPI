/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package javaapplication1;


import com.tourisme_sante.entities.Offre;
import com.tourisme_sante.entities.RDV;
import com.tourisme_sante.entities.Reservation;
import com.tourisme_sante.entities.Transport;
import com.tourisme_sante.entities.TypeInterventions;
import com.tourisme_sante.entities.Utilisateur;
import com.tourisme_sante.entities.admin;
import com.tourisme_sante.entities.medecin_interventions;
import com.tourisme_sante.entities.medecins;
import comm.tourisme_sante.services.ServiceAgence;
import comm.tourisme_sante.services.ServiceHotel;
import comm.tourisme_sante.services.ServiceMedecinIntervention;
import comm.tourisme_sante.services.ServiceOffre;
import comm.tourisme_sante.services.ServiceReservation;
import comm.tourisme_sante.services.ServiceTransport;
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
        
       //ServiceProduit sp=new ServiceProduit();
        //sp.ajouter(new produit(10,10,10));
       // sp.modifier(new produit(2,20,20,20));
      // sp.supprimer(new produit(2,20,20,20));
      //System.out.println(sp.afficher());
      //serviceUtilisateur su=new serviceUtilisateur();
      //Utilisateur x=new admin("achref","hamzewi","achref@gmail.com","test","admin");
     // su.ajouter(x);
      //su.modifier(new Utilisateur(2,"achref2","hamzewi","achref@gmail.com","test"));
      //su.supprimer(new Utilisateur(2,"achref2","hamzewi","achref@gmail.com","test"));
     // System.out.println(su.afficher());
     // Services_TypeInterventions sti=new Services_TypeInterventions();
      //sti.ajouter(new TypeInterventions("test",10.0,"test"));
     //sti.modifier(new TypeInterventions(2,"test222",10.0,"test"));
     //sti.supprimer(new TypeInterventions(2,"test222",10.0,"test"));
     // System.out.println(sti.afficher());
     // ServiceTransport st=new ServiceTransport();
      //st.ajouter(new Transport("123tn244",Transport.TypeTransport.voiture));
      //st.modifier(new Transport(3,"123tn245",Transport.TypeTransport.voiture));
      //st.supprimer(new Transport(3,"123tn245",Transport.TypeTransport.voiture));
     // System.out.println(st.afficher());
      //ServiceReservation srs=new ServiceReservation();
     // srs.ajouter(new Reservation(Date.valueOf("2023-05-15"),Date.valueOf("2023-05-15"),1,1,1,1));
     //srs.modifier(new Reservation(2,Date.valueOf("2023-05-16"),Date.valueOf("2023-05-15"),1,1,1,1));
     //srs.supprimer(new Reservation(2,Date.valueOf("2023-05-16"),Date.valueOf("2023-05-15"),1,1,1,1));
    // System.out.println(srs.afficher());
    // ServiceOffre sof=new ServiceOffre();
     //sof.ajouter(new Offre(10.0,10.0,"test","test"));
     //sof.modifier(new Offre(2,10.0,10.0,"test2","test2"));
    // sof.supprimer(new Offre(2,10.0,10.0,"test2","test2"));
 
     //  System.out.println(sof.afficher());
    // ServiceInterventions si=new ServiceInterventions();
    //si.ajouter(new Interventions("test3","test2"));
        //si.modifier(new Interventions(2,"test2","test2"));
       // si.supprimer(new Interventions(2,"test2","test2"));
      //  System.out.println(si.afficher());
      //  ServiceCommande sc=new ServiceCommande();  
       // sc.ajouter(new Commande(10,10,10.0));
       //sc.modifier(new Commande(2,10,10,20.0));
       //sc.supprimer(new Commande(2,10,10,20.0));
       //System.out.println(sc.afficher()); 

       serviceMedecin sm=new serviceMedecin();
        
    sm.ajouter(new  medecins ("tahani cherif 2","cheriftahani92@gmail.com","cite cheker rawed",27711622,"test"));
       // sm.modifier(new  medecins (3,"test","cheriftahani92@gmail.com","cite cheker rawed",27711622,"test"));
    //     sm.supprimer(new  medecins (6,"tahani cherif 2","cheriftahani92@gmail.com","cite cheker rawed",27711622,"test"));
            //  System.out.println(sm.afficher()); 
      serviceRDV srdv=new serviceRDV();
   //  srdv.ajouter(new RDV(1,1,Date.valueOf("2023-05-15")));
    //   srdv.modifier(new RDV(6,1,1,Date.valueOf("2023-06-16")));
      // srdv.supprimer(new RDV(3,1,1,Date.valueOf("2023-05-15")));
       
        System.out.println(srdv.afficher());
        ServiceMedecinIntervention smi=new ServiceMedecinIntervention();
       // smi.ajouter(new medecin_interventions(5,1));
     // smi.modifier(new medecin_interventions(1,3,1));
    //  smi.supprimer(new medecin_interventions(4,3,4));
      System.out.println(smi.afficher());
    }
    
}
