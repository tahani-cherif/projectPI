/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package comm.tourisme_sante.services;

import java.util.List;

/**
 *
 * @author Chimou
 */
public interface TIServices  <T> {
    
    public void ajouter(T ti);
    public void modifier(T ti);
    public void supprimer(T ti);
    public List<T> afficher();
}
