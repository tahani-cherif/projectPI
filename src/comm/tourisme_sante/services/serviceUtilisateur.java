

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package comm.tourisme_sante.services;

import com.tourisme_sante.entities.Admin;
import com.tourisme_sante.entities.Client;
import com.tourisme_sante.entities.Gestionnaire;
import com.tourisme_sante.entities.Utilisateur;
import com.tourisme_sante.utils.Datasource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author hamza
 */
public class serviceUtilisateur implements services<Utilisateur>{

     private Connection cnx = Datasource.getInstance().getCnx();
    public void modifier_M(Utilisateur U){
        try {
            String req = "UPDATE  utilisateur SET MDP='"+U.getMDP()+"' WHERE id="+U.getId();
            Statement st = cnx.createStatement();
            st.executeUpdate(req);
            System.out.println("MDP modfier");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
    
     @Override
    public void modifier(Utilisateur U){
        try {
            String req = "UPDATE utilisateur SET nom='"
                    +U.getNom()+"', prenom='"+U.getPrenom()+"',email='"+U.getEmail()+"',MDP='"+U.getMDP()+"',role='"+U.getRole()
                    +"' WHERE id="+U.getId();
            Statement st = cnx.createStatement();
            st.executeUpdate(req);
            System.out.println("Profile modifiée !");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
       public void modifier2(Client U){
        try {
            String req = "UPDATE utilisateur SET nom='"
                    +U.getNom()+"', prenom='"+U.getPrenom()+"',email='"+U.getEmail()+"',MDP='"+U.getMDP()+"',role='"+U.getRole()
                    +"',adresse='"+U.getAdresse()+
                    "',number='"+U.getNumber()+
                    "',sex='"+U.getSex()
                    +"' WHERE id="+U.getId();
            Statement st = cnx.createStatement();
            st.executeUpdate(req);
            System.out.println("Profile modifiée !");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
       
       public void modifier3(Gestionnaire U){
        try {
            String req = "UPDATE utilisateur SET nom='"
                    +U.getNom()+"', prenom='"+U.getPrenom()+"',email='"+U.getEmail()+"',MDP='"+U.getMDP()+"',role='"+U.getRole()+
                    "',number='"+U.getNumber()+"',adresse='"+U.getAdresse()
                    +"' WHERE id="+U.getId();
            Statement st = cnx.createStatement();
            st.executeUpdate(req);
            System.out.println("Profile modifiée !");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
     @Override
      public void ajouter(Utilisateur U) {
        try {
            String req = "INSERT INTO 	utilisateur(nom, prenom,email,MDP,role,number,sex,adresse) VALUES (?,?,?,?,?,?,?,?);";
            PreparedStatement pst = cnx.prepareStatement(req);
                        System.out.println(U);

            pst.setString(1, U.getNom()); 
            pst.setString(2, U.getPrenom());
            pst.setString(3, U.getEmail());
            pst.setString(4, U.getMDP());
            pst.setString(5, U.getRole());
            
            if (U.getRole().equals("client"))
            {
                pst.setInt(6,((Client)U).getNumber());
                pst.setString(7,((Client)U).getSex());
                pst.setString(8,((Client)U).getAdresse());
            }
            if(U.getRole().equals("gestionnaire")){
                            pst.setInt(6,((Gestionnaire)U).getNumber());
                            pst.setInt(7,0);
                            pst.setString(8,((Gestionnaire)U).getAdresse());

            }else{
                pst.setInt(6,0);
                pst.setInt(7,0);
                pst.setInt(8,0);
            

            }
            pst.executeUpdate();
            System.out.println("Utilisateur ajoutée !");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
     /* public void ajouter(Utilisateur U ) {
        try {
            String req = "INSERT INTO 	utilisateur(nom, prenom,email,MDP,role,number,sex,adresse) VALUES (?,?,?,?,?,?,?,?);";
            PreparedStatement pst = cnx.prepareStatement(req);
            System.out.println(U);
            pst.setString(1, U.getNom());
            pst.setString(2, U.getPrenom());
            pst.setString(3, U.getEmail());
            pst.setString(4, U.getMDP());
            pst.setString(5, U.getRole());

            if (U instanceof Client) {
                pst.setDouble(6,((Client)U).getNumber());
                pst.setString(7,((Client)U).getSex());
                pst.setString(8,((Client)U).getAdresse());
            }else{
                pst.setInt(6,0);
                pst.setString(7,null);
                pst.setString(8,null);
            }
            
            
            
            if(U instanceof Admin){
                pst.setString(1, U.getNom());
                pst.setString(2, U.getPrenom());
                pst.setString(3, U.getEmail());
                pst.setString(4, U.getMDP());
                pst.setString(5, U.getRole());
                
            }
            
            int rs = pst.executeUpdate();
            System.out.println(rs);
            
            System.out.println("Utlisateur ajoutée !");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }*/
    
     @Override
    public List<Utilisateur> afficher() {
        List<Utilisateur> list = new ArrayList<>();
        
        String req = "SELECT * FROM utilisateur";
        try {
            Statement st = cnx.createStatement();
            ResultSet rs = st.executeQuery(req);
            while(rs.next()) {
                Client x = null;
                Admin y=null;
            //if (rs.getString("role").equals("client"))
            //{
               x =new Client ( rs.getInt("number"), rs.getString("sex"), rs.getString("adresse"),rs.getInt("id"),rs.getString("nom"),rs.getString("prenom"),rs.getString("email"),rs.getString("MDP"),rs.getString("role"));
           // }
         // else{
            // y =new Admin (  rs.getInt("id"),rs.getString("nom"),rs.getString("prenom"),rs.getString("email"),rs.getString("MDP"),rs.getString("role"));

          // }
               list.add(x); 
              // Utilisateur.u = x;
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        
        
        return list;
    }

    public List<Utilisateur> afficher2(Utilisateur u) {
        List<Utilisateur> list = new ArrayList<>();
        System.out.println(u);
        String req = "SELECT * FROM utilisateur where id=?";
        try {
           PreparedStatement pst = cnx.prepareStatement(req);
           pst.setInt(1, u.getId());
            ResultSet rs = pst.executeQuery();
            while(rs.next()) {
                Client x = null;
            //if (rs.getString("role").equals("client"))
            //{
               x =new Client ( rs.getInt("number"), rs.getString("sex"), rs.getString("adresse"),rs.getInt("id"),rs.getString("nom"),rs.getString("prenom"),rs.getString("email"),rs.getString("MDP"),rs.getString("role"));
           // }
         // else{
            // y =new Admin (  rs.getInt("id"),rs.getString("nom"),rs.getString("prenom"),rs.getString("email"),rs.getString("MDP"),rs.getString("role"));

          // }
               list.add(x); 
              // Utilisateur.u = x;
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        
        
        return list;
    }
    public List<Utilisateur> afficher3(Utilisateur u) {
        List<Utilisateur> list = new ArrayList<>();
        System.out.println(u);
        String req = "SELECT * FROM utilisateur where id=?";
        try {
           PreparedStatement pst = cnx.prepareStatement(req);
           pst.setInt(1, u.getId());
            ResultSet rs = pst.executeQuery();
            while(rs.next()) {
                Gestionnaire x = null;
            //if (rs.getString("role").equals("client"))
            //{
               x =new Gestionnaire ( rs.getInt("number"), rs.getString("adresse"),rs.getInt("id"),rs.getString("nom"),rs.getString("prenom"),rs.getString("email"),rs.getString("MDP"),rs.getString("role"));
           // }
         // else{
            // y =new Admin (  rs.getInt("id"),rs.getString("nom"),rs.getString("prenom"),rs.getString("email"),rs.getString("MDP"),rs.getString("role"));

          // }
               list.add(x); 
              // Utilisateur.u = x;
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        
        
        return list;
    }
   /* public   List<Utilisateur> afficher() {
        List<Utilisateur> list = new ArrayList<>();
        
        String req = "SELECT * FROM utilisateur";
        try {
            PreparedStatement pst = cnx.prepareStatement(req);
            ResultSet rs = pst.executeQuery();
            while(rs.next()) {
                System.out.println(rs.getString("Role")); 
                if (rs.getString("Role").equals("Client")) {
               
                    Utilisateur U = new Client ( rs.getInt("number"), rs.getString("sex"), rs.getString("adresse"),rs.getInt("id"),rs.getString("nom"),rs.getString("prenom"),rs.getString("email"),rs.getString("MDP"),rs.getString("role"));
               list.add(U); }
               
               if (rs.getString("Role").equals("Admin")) {
                    Utilisateur U = new Admin (  rs.getInt("id"),rs.getString("nom"),rs.getString("prenom"),rs.getString("email"),rs.getString("MDP"),rs.getString("role"));
               list.add(U) ;
                }
              
              
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        
        
        return list;
    }
    */
    
      public List<Admin> afficherAdmin() {
        List<Admin> list = new ArrayList<>();
        
        String req = "SELECT * FROM utilisateur";
        try {
            Statement st = cnx.createStatement();
            ResultSet rs = st.executeQuery(req);
            while(rs.next()) {
                Admin x = null;
            if (rs.getString("role").equals("Admin"))
             {
               x =new Admin (rs.getInt("id"),rs.getString("nom"),rs.getString("prenom"),rs.getString("email"),rs.getString("MDP"),rs.getString("role"));
            }
               list.add(x); 
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        
        
        return list;
    }

    @Override
    public void supprimer(Utilisateur p) {
      try {
            String req = "DELETE from utilisateur WHERE id=?";
            PreparedStatement pst = cnx.prepareStatement(req);
            pst.setInt(1, p.getId());
            pst.executeUpdate();
            System.out.println("Utlisateur supprimée !");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
    public boolean isvalidmail(String x) {
         String req = "SELECT * FROM utilisateur where email =?";
          try {
            PreparedStatement pst = cnx.prepareStatement(req);
            pst.setString(1,x);
            ResultSet rs = pst.executeQuery();
            if (rs.next()) {
                return true; 
            } } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }     return false ;
     }

 public void modifpassword(String password, String email) {
        try {
            String req = "UPDATE utilisateur SET MDP=? WHERE email=?"; 
            PreparedStatement pst = cnx.prepareStatement(req);
            pst.setString(1,password);
            pst.setString(2, email );
             pst.executeUpdate();
            System.out.println("Password modifié !");   
   } 
        catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        
        
}}


