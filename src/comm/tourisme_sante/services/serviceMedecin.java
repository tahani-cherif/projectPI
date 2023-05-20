/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package comm.tourisme_sante.services;

//import com.mysql.cj.x.protobuf.MysqlxDatatypes.Array;
import com.tourisme_sante.entities.RDV;
import com.tourisme_sante.entities.medecins;
import com.tourisme_sante.utils.Datasource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 *
 * @author LENOVO
 */
public class serviceMedecin implements services <medecins>{
  private Connection cnx = Datasource.getInstance().getCnx();
    @Override
    public void ajouter(medecins p) {
         try {
            String req = "INSERT INTO medecins(fullName, email,adresse,numero,specialite) VALUES (?,?,?,?,?);";
            PreparedStatement pst = cnx.prepareStatement(req);
            pst.setString(1, p.getFullName());
            pst.setString(2, p.getEmail());
            pst.setString(3,p.getAdresse());
            pst.setInt(4,p.getNumero());
            pst.setString(5,p.getSpecialite());
       //     pst.setArray(6, ((ArrayList)Arrays.asList(p.getId_type_intervention())));
            pst.executeUpdate();
            System.out.println("medecins ajoutée !");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @Override
    public void modifier(medecins p) {
        try {
            String req = "UPDATE medecins SET fullName=?, email=? , adresse=? ,numero=?, specialite=? WHERE id=?";
            PreparedStatement pst = cnx.prepareStatement(req);
            pst.setString(1, p.getFullName());
            pst.setString(2, p.getEmail());
            pst.setString(3,p.getAdresse());
            pst.setInt(4,p.getNumero());
            pst.setString(5,p.getSpecialite());
          //  pst.setArray(6, ((ArrayList)Arrays.asList(p.getId_type_intervention())));
             pst.setInt(6,p.getId());
            pst.executeUpdate();
            System.out.println("medecins modifiée !");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }    }

    @Override
    public void supprimer(medecins p) {
         try {
            String req = "DELETE from medecins WHERE id=?";
            PreparedStatement pst = cnx.prepareStatement(req);
            pst.setInt(1,p.getId());
            pst.executeUpdate();
            System.out.println("medecins supprimée !");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @Override
    public List afficher() {
        List<medecins> list = new ArrayList<>();
        
        String req = "SELECT * FROM medecins";
        try {
            PreparedStatement pst = cnx.prepareStatement(req);
            ResultSet rs = pst.executeQuery();
            while(rs.next()) {
                list.add(new medecins(rs.getInt("id"), rs.getString("fullName"), rs.getString("email"),rs.getString("adresse"),rs.getInt("numero"),rs.getString("specialite")));
          //, (ArrayList<Integer>) rs.getArray("id_type_intervention")
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        
        
        return list;
    }
    
}
