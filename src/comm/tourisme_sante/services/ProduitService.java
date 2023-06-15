/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package comm.tourisme_sante.services;

import com.tourisme_sante.entities.Produit;
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
 * @author User
 */
public class ProduitService implements IProduitService {
    
      private Connection cnx;

    public ProduitService() {
        cnx = Datasource.getInstance().getCnx();
    }


 @Override
public void ajouter(Produit p) throws SQLException {
    String req = "INSERT INTO `produit`(`nom`,`prix`, `qtDisponible`, `qtUtilisee`) VALUES (?,?,?,?)";

    PreparedStatement ps = cnx.prepareStatement(req);

    ps.setString(1, p.getNom());
    ps.setDouble(2, p.getPrix());
    ps.setInt(3, p.getQtDisponible());
    ps.setInt(4, p.getQtUtilisee());

    ps.executeUpdate();
    System.out.println("Produit ajouté !");
}


    @Override
    public void modifier(Produit p) throws SQLException {
     
        String req = "UPDATE produit SET `nom`= ?, `prix`= ?, `qtDisponible`= ?, `qtUtilisee`= ? WHERE id=" + p.getId();
        PreparedStatement ps = cnx.prepareStatement(req);
        ps.setString(1, p.getNom());
        ps.setDouble(2, p.getPrix());
        ps.setInt(3, p.getQtDisponible());
        ps.setInt(4, p.getQtUtilisee());
        ps.executeUpdate();
        System.out.println("Question a été modifiée avec succés !");

    }

    @Override
    public void supprimer(Produit p) throws SQLException {
          String req = "DELETE FROM `produit` WHERE `id`=?";
        PreparedStatement ps = cnx.prepareStatement(req);
        ps.setLong(1, p.getId());
        ps.executeUpdate();
        System.out.println("Produit supprimée !");    }

    @Override
    public List<Produit> afficher() throws SQLException{
         List<Produit> temp = new ArrayList<>();
        
        String req = "SELECT * FROM `produit` ";
        Statement st = cnx.createStatement();
        
        ResultSet rs = st.executeQuery(req);
        
        while (rs.next()) {
            Produit p = new  Produit();
            
            p.setId(rs.getInt(1));
            p.setNom(rs.getString(2));
            p.setPrix(rs.getDouble(3));
            p.setQtDisponible(rs.getInt(4));
            p.setQtUtilisee(rs.getInt(5));
                        
            temp.add(p);
        }
        
        
        return temp;
        
    
    }

    @Override
    public Produit getProduitById(long id) throws SQLException {
       
        Produit r = new Produit();
        String req = "SELECT * FROM produit WHERE id="+id+" ORDER BY id";
        Statement st = cnx.createStatement();
        
        ResultSet rs = st.executeQuery(req);
        
        while (rs.next()) {
            
            r.setId(rs.getInt("id"));
            r.setNom(rs.getString("nom"));
            r.setPrix(rs.getDouble("prix"));
            r.setQtDisponible(rs.getInt("qtDisponible"));
            r.setQtUtilisee(rs.getInt("qtUtilisee")); 
        }
        
        return r;
    }
    
}
