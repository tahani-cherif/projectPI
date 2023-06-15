/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package comm.tourisme_sante.services;

import com.tourisme_sante.entities.Panier;
import com.tourisme_sante.entities.Produit;
import com.tourisme_sante.utils.Datasource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author User
 */
public class PanierService  implements IPanierService{
    private ProduitService prodserv;
private Connection cnx;

    public PanierService() {
        cnx = Datasource.getInstance().getCnx();
    }
    @Override
    public void ajouter(Panier p) throws SQLException {
         String req = "INSERT INTO `panier`(`prix`, `idClient`, `idProduit`,`qt`) VALUES (?,?,?,?)";

    PreparedStatement ps = cnx.prepareStatement(req);

    ps.setDouble(1, p.getPrix());
    ps.setLong(2, p.getIdClient());
    ps.setLong(3, p.getIdProduit());
    ps.setInt(4, p.getQt());

    ps.executeUpdate();
    System.out.println("Panier cree !");    }

    @Override
    public void modifier(Panier p) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void supprimer(long id,long idProduit) throws SQLException {
     
    
        String req = "DELETE FROM `panier` WHERE `idClient`=? AND `idProduit`=? ";
        PreparedStatement ps = cnx.prepareStatement(req);
        ps.setLong(1, id);
        ps.setLong(2, idProduit);
        ps.executeUpdate();
        System.out.println("Panier supprimée !");    }

    @Override
    public List<Panier> afficher() throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

   @Override
public List<Produit> getPanierByUser(long id) throws SQLException {
    prodserv = new ProduitService();
    List<Produit> tempProduits = new ArrayList<>();

    String req = "SELECT * FROM panier WHERE idClient = ?";
    PreparedStatement ps = cnx.prepareStatement(req);
    ps.setLong(1, id);
    ResultSet rs = ps.executeQuery();

    while (rs.next()) {
        Panier r = new Panier();
        r.setId(rs.getLong("id"));
        r.setIdClient(rs.getLong("idClient"));
        r.setIdProduit(rs.getLong("idProduit"));
        r.setPrix(rs.getDouble("prix"));
         r.setQt(rs.getInt("qt"));
        try {
            Produit produit = prodserv.getProduitById(r.getIdProduit());
            tempProduits.add(produit);
        } catch (SQLException ex) {
            Logger.getLogger(PanierService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    return tempProduits;
}

    @Override
    public int getQtByProduit(long id) throws SQLException {
       
   int qt=1;
 
    String req = "SELECT qt FROM panier WHERE idProduit = ?";
    PreparedStatement ps = cnx.prepareStatement(req);
    ps.setLong(1, id);
    ResultSet rs = ps.executeQuery();

    while (rs.next()) {

        qt = rs.getInt("qt");
    }

    return qt;
    }

    @Override
    public void updateQtByProduit(long id, int qt) throws SQLException {
         String req = "UPDATE panier SET `qt`= ? WHERE idProduit=" + id;
        PreparedStatement ps = cnx.prepareStatement(req);
        ps.setInt(1, qt);
        ps.executeUpdate();
        System.out.println("Quantité a été modifiée avec succés !");    }


    
}
