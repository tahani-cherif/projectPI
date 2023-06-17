/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package services;

import entities.Panier;
import entities.Produit;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author omarb
 */
public interface IPanierService {
    public void ajouter(Panier p)throws SQLException;
    public void modifier(Panier p)throws SQLException ;
    public void supprimer(long id,long idProduit)throws SQLException ;
    public List<Panier> afficher()throws SQLException;
    public List<Produit> getPanierByUser(long id)throws SQLException;
    public int getQtByProduit(long id)throws SQLException;
    public void updateQtByProduit(long id,int qt)throws SQLException;
    
}
