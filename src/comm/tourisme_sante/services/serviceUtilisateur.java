

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package comm.tourisme_sante.services;

import com.tourisme_sante.entities.Utilisateur;
import com.tourisme_sante.utils.Datasource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author hamza
 */
public class serviceUtilisateur implements services<Utilisateur>{

     private Connection cnx = Datasource.getInstance().getCnx();
    public void modifier_M(Utilisateur U){
        try {
            String req = "UPDATE INTO Utilisateur(MDP) VALUES ('"
                    +U.getMDP()+"',');";
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
            String req = "UPDATE Utilisateur SET nom='"
                    +U.getNom()+"', prenom='"+U.getPrenom()+"',email='"+U.getEmail()+"',MDP='"+U.getMDP()
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
            String req = "INSERT INTO Utilisateur(nom, prenom,email,MDP) VALUES (?,?,?,?);";
            PreparedStatement pst = cnx.prepareStatement(req);
            pst.setString(1, U.getNom());
            pst.setString(2, U.getPrenom());
            pst.setString(3, U.getEmail());
            pst.setString(4, U.getMDP());
            pst.executeUpdate();
            System.out.println("Utilisateur ajoutée !");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
    
     @Override
    public List<Utilisateur> afficher() {
        List<Utilisateur> list = new ArrayList<>();
        
        String req = "SELECT * FROM Utilisateur";
        try {
            Statement st = cnx.createStatement();
            ResultSet rs = st.executeQuery(req);
            while(rs.next()) {
                list.add(new Utilisateur(rs.getInt("id"), rs.getString("nom"), rs.getString("prenom"),rs.getString("email"),rs.getString("MDP")));
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        
        
        return list;
    }

    @Override
    public void supprimer(Utilisateur p) {
      try {
            String req = "DELETE from Utilisateur WHERE id=?";
            PreparedStatement pst = cnx.prepareStatement(req);
            pst.setInt(1, p.getId());
            pst.executeUpdate();
            System.out.println("Utlisateur supprimée !");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

}


