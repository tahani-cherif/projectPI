/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package comm.tourisme_sante.services;

import java.util.List;
import javafx.collections.ObservableList;

/**
 *
 * @author LENOVO
 */
public interface services<T> {
     public void ajouter(T p);
    public void modifier(T p);
    public void supprimer(T p);
    public List<T> afficher();
   
}
