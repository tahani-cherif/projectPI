/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package services;

import java.util.List;
import entities.Produit;
import java.sql.SQLException;

/**
 *
 * @author omarb
 */
public interface IProduitService  {
    
    public void ajouter(Produit p)throws SQLException;
    public void modifier(Produit p)throws SQLException ;
    public void supprimer(Produit p)throws SQLException ;
    public List<Produit> afficher()throws SQLException;
    public Produit getProduitById(long id)throws SQLException;
    
}
