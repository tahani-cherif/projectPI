/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package comm.tourisme_sante.services;

import com.tourisme_sante.entities.Commande;
import com.tourisme_sante.utils.Datasource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author User
 */
public class CommandeService implements ICommandeService {
     
      private Connection cnx;

    public CommandeService() {
        cnx = Datasource.getInstance().getCnx();
    }


 @Override
public void ajouter(Commande p) throws SQLException {
    String req = "INSERT INTO `commande`(`idProduit`,`datePassation`, `quantite`, `idClient`, `mt`) VALUES (?,?,?,?,?)";

    PreparedStatement ps = cnx.prepareStatement(req);

    ps.setLong(1, p.getIdProduit());
    ps.setString(2, p.getDatePassation());
    ps.setInt(3, p.getQuantite());
    ps.setLong(4, p.getIdClient());
    ps.setDouble(5,p.getMt());

    ps.executeUpdate();
    System.out.println("Commande ajouté !");
}


    @Override
    public void modifier(Commande p) throws SQLException {
     
        String req = "UPDATE commande SET `idProduit`= ?, `datePassation`= ?, `quantite`= ?, `idClient`= ?, `mt`=? WHERE id=" + p.getId();
        PreparedStatement ps = cnx.prepareStatement(req);
        ps.setLong(1, p.getIdProduit());
        ps.setString(2, p.getDatePassation());
        ps.setInt(3, p.getQuantite());
        ps.setLong(4, p.getIdClient());
        ps.setDouble(5,p.getMt());
        ps.executeUpdate();
        System.out.println("Commande a été modifiée avec succés !");

    }

    @Override
    public void supprimer(Commande p) throws SQLException {
          String req = "DELETE FROM `commande` WHERE `id`=?";
        PreparedStatement ps = cnx.prepareStatement(req);
        ps.setLong(1, p.getId());
        ps.executeUpdate();
        System.out.println("Commande supprimée !");    }

    @Override
    public List<Commande> afficher() throws SQLException{
         List<Commande> temp = new ArrayList<>();
        
        String req = "SELECT * FROM `commande` ";
        Statement st = cnx.createStatement();
        
        ResultSet rs = st.executeQuery(req);
        
        while (rs.next()) {
            Commande c = new  Commande();
         
            c.setId(rs.getInt("id"));
               // Convert the date string to LocalDate
            String dateString = rs.getString("datePassation");
            LocalDate datePassation = LocalDate.parse(dateString);

            // Format the LocalDate back to a string in the desired format
            String formattedDate = datePassation.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));

            c.setDatePassation(formattedDate);
            c.setQuantite(rs.getInt("quantite"));
            c.setIdClient(rs.getLong("idClient"));
            c.setMt(rs.getDouble("mt"));
            
            c.setIdProduit(rs.getLong("idProduit"));
                        
            temp.add(c);
        }
        
        
        return temp;
        
    
    }
}
