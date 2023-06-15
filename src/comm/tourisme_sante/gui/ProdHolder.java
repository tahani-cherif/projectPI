/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package comm.tourisme_sante.gui;

import com.tourisme_sante.entities.Produit;

/**
 *
 * @author User
 */
public class ProdHolder {
    private Produit prod;
    private final static ProdHolder INSTANCE =new ProdHolder();

    public ProdHolder() {
    }
    
    public static ProdHolder getInstance(){
     return INSTANCE;   
    }
    
    
    
    public void setProduit (Produit p){
        this.prod =p;
    }
    
        public Produit getProduit (){
        return  this.prod;
    }
}
