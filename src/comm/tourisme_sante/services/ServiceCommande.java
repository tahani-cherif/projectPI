package comm.tourisme_sante.services;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

import com.tourisme_sante.entities.Commande;
import com.tourisme_sante.utils.Datasource;
import comm.tourisme_sante.services.services;
import java.sql.Connection;
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
public class ServiceCommande implements services<Commande> {

    private Connection cnx = Datasource.getInstance().getCnx();

    public void ajouter(Commande p) {
        try {
            String req = "INSERT INTO commande(datePassation, quantite, MT) VALUES ('"
                    +p.getDatePassation()+"','"+p.getQuantite()+"','"+p.getMT()+"')";
            Statement st = cnx.createStatement();
            st.executeUpdate(req);
            System.out.println("Commande ajoutée !");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public void modifier(Commande p) {
        try {
            String req = "UPDATE commande SET datePassation='"
                    +p.getDatePassation()+"', quantite='"+p.getQuantite()+"', MT='"+p.getMT()+"' WHERE id="+p.getId();
            Statement st = cnx.createStatement();
            st.executeUpdate(req);
            System.out.println("Commande modifiée !");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public void supprimer(Commande p) {
        try {
            String req = "DELETE FROM commande WHERE id="+p.getId();
            Statement st = cnx.createStatement();
            st.executeUpdate(req);
            System.out.println("Commande supprimée !");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public List<Commande> afficher() {
        List<Commande> list = new ArrayList<>();

        String req = "SELECT * FROM commande";
        try {
            Statement st = cnx.createStatement();
            ResultSet rs = st.executeQuery(req);
            while(rs.next()) {
                list.add(new Commande(rs.getInt("id"), rs.getInt("datePassation"), rs.getInt("quantite"), rs.getDouble("MT")));
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

        return list;
    }
}
