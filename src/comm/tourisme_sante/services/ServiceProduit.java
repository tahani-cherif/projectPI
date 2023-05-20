package comm.tourisme_sante.services;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */


import com.tourisme_sante.entities.produit;
import com.tourisme_sante.utils.Datasource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
/**
 *
 * @author USER
 */
public class ServiceProduit implements services<produit> {
    
    private Connection cnx = Datasource.getInstance().getCnx();
    
    public void ajouter(produit p) {
        try {
            String req = "INSERT INTO produit(prix, quantiteDisponible, quantiteUtilisee) VALUES (?, ?, ?)";
            PreparedStatement ps = cnx.prepareStatement(req);
            ps.setDouble(1, p.getPrix());
            ps.setInt(2, p.getQuantiteDisponible());
            ps.setInt(3, p.getQuantiteUtilisee());
            ps.executeUpdate();
            System.out.println("Produit ajouté !");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
    
    public void modifier(produit p) {
        try {
            String req = "UPDATE produit SET prix=?, quantiteDisponible=?, quantiteUtilisee=? WHERE id=?";
            PreparedStatement ps = cnx.prepareStatement(req);
            ps.setDouble(1, p.getPrix());
            ps.setInt(2, p.getQuantiteDisponible());
            ps.setInt(3, p.getQuantiteUtilisee());
            ps.setInt(4, p.getId());
            ps.executeUpdate();
            System.out.println("Produit modifié !");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
    
    public void supprimer(produit p) {
        try {
            String req = "DELETE FROM produit WHERE id=?";
            PreparedStatement ps = cnx.prepareStatement(req);
            ps.setInt(1, p.getId());
            ps.executeUpdate();
            System.out.println("Produit supprimé !");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
    
    public List<produit> afficher() {
        List<produit> list = new ArrayList<>();
        
        String req = "SELECT * FROM produit";
        try {
            Statement st = cnx.createStatement();
            ResultSet rs = st.executeQuery(req);
            while(rs.next()) {
                list.add(new produit(rs.getInt("id"), rs.getDouble("prix"), rs.getInt("quantiteDisponible"), rs.getInt("quantiteUtilisee")));
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        
        return list;
    }
}
