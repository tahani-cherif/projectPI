/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package comm.tourisme_sante.services;

import com.tourisme_sante.entities.Produit;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author User
 */
public interface IProduitService {
        public void ajouter(Produit p)throws SQLException;
    public void modifier(Produit p)throws SQLException ;
    public void supprimer(Produit p)throws SQLException ;
    public List<Produit> afficher()throws SQLException;
    public Produit getProduitById(long id)throws SQLException;
}
