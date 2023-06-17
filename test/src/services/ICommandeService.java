/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package services;

import entities.Commande;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author omarb
 */
public interface ICommandeService {
      public void ajouter(Commande p)throws SQLException;
    public void modifier(Commande p)throws SQLException ;
    public void supprimer(Commande p)throws SQLException ;
    public List<Commande> afficher()throws SQLException;
}
