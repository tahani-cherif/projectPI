package services;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import entities.Offre;
import utils.DataSource;

public class ServiceOffre implements IService<Offre>{
	private Connection cnx = DataSource.getInstance().getCnx();
	@Override
	public void ajouter(Offre o) {
		try {
            String req = "INSERT INTO offre(pourcentage, prix,nom,type) VALUES ('"
                    +o.getPourcentage()+"','"+o.getPrix()+"','"+o.getNom()+"','"+o.getType()+"');";
            Statement st = cnx.createStatement();
            st.executeUpdate(req);
            System.out.println("Offre ajoutée !");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
	}

	@Override
	public void modifier(Offre o) {
		try {
            String req = "UPDATE offre SET pourcentage='"
                    +o.getPourcentage()+"', prix='"+o.getPrix()+"', nom='"+o.getNom()+"', type='"+o.getType()
                    +"' WHERE id="+o.getId();
            Statement st = cnx.createStatement();
            st.executeUpdate(req);
            System.out.println("Offre modifiée !");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
	}

	@Override
	public void supprimer(Offre o) {
	       try {
	            String req = "DELETE from offre WHERE id="+o.getId();
	            Statement st = cnx.createStatement();
	            st.executeUpdate(req);
	            System.out.println("Offre supprimée !");
	        } catch (SQLException ex) {
	            System.out.println(ex.getMessage());
	        }
		
	}

	@Override
	public List<Offre> afficher() {
		  List<Offre> list = new ArrayList<>();
	        
	        String req = "SELECT * FROM offre";
	        try {
	            Statement st = cnx.createStatement();
	            ResultSet rs = st.executeQuery(req);
	            while(rs.next()) {
	                list.add(new Offre(rs.getInt("id"),rs.getDouble("pourcentage"), rs.getDouble("prix"),rs.getString("nom"),rs.getString("type")));
	            }
	        } catch (SQLException ex) {
	            System.out.println(ex.getMessage());
	        }
	        
	        
	        return list;
	}

}
