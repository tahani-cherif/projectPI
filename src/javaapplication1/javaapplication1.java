/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package javaapplication1;


import com.tourisme_sante.entities.Interventions;
import com.tourisme_sante.entities.TypeInterventions;
import comm.tourisme_sante.services.ServicesInterventions;
import comm.tourisme_sante.services.Service_TypeInterventions;

/**
 *
 * @author Chimou
 */
public class javaapplication1 {
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        // TODO code application logic here
          ServicesInterventions sI= new ServicesInterventions();
        sI.ajouter( new Interventions("chayma",200,"fatiguée",1,1));
        //System.out.println(sI.afficher());
        //sI.modifier(new Interventions(2,"chayma","trop"));
        //Service_TypeInterventions st= new Service_TypeInterventions();
        //st.ajouter( new TypeInterventions("visage",1000,"oval"));
        //st.ajouter( new TypeInterventions(1,"chaima","grande"));
        //st.supprimer(new TypeInterventions(1,"chayma","fatiquée"));
        //System.out.println(sI.afficher());
        //sI.modifier(new Interventions(2,"visage","rond"));
        //st.supprimer( new TypeInterventions(2,"chaima","grande"));
        //sI.modifier(new Interventions(2,"chaima",300,"trop",3,4));
      

       
    }
    
}
