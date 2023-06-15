/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package comm.tourisme_sante.services;

import java.util.List;


/**
 *
 * @author Chimou
 * @param <T>
 */
public interface IService <T> {
    
    public void ajouter(T i);
    public void modifier(T i);
    public void supprimer(T i);
    public List<T> afficher();
}
