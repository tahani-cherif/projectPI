package services;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import entities.Personne;
import entities.Reservation;
import utils.DataSource;

public class ServiceReservation implements IService<Reservation> {
    private Connection cnx = DataSource.getInstance().getCnx();

	@Override
	public void ajouter(Reservation r) {
		 try {
	            String req = "INSERT INTO reservation(dateDebut, dateFin,idAgence,idUser,idHotels,idTransport) VALUES ('"
	                    +r.getDateDebut()+"','"+r.getDateFin()+"','"+r.getIdAgence()+"','"+r.getIdUser()+"','"+r.getIdHotels()+"','"+r.getIdTransport()+"');";
	            Statement st = cnx.createStatement();
	            st.executeUpdate(req);
	            System.out.println("Reservation ajoutée !");
	        } catch (SQLException ex) {
	            System.out.println(ex.getMessage());
	        }
		
	}

	@Override
	public void modifier(Reservation r) {
		 try {
	            String req = "UPDATE reservation SET dateDebut='"
	                    +r.getDateDebut()+"', dateFin='"+r.getDateFin()+"', idAgence='"+r.getIdAgence()+"', idUser='"+r.getIdUser()+"', idHotels='"+r.getIdHotels()+"', idTransport='"+r.getIdTransport()
	                    +"' WHERE id="+r.getId();
	            Statement st = cnx.createStatement();
	            st.executeUpdate(req);
	            System.out.println("Reservation modifiée !");
	        } catch (SQLException ex) {
	            System.out.println(ex.getMessage());
	        }
		
	}

	@Override
	public void supprimer(Reservation r) {
		 try {
	            String req = "DELETE from reservation WHERE id="+r.getId();
	            Statement st = cnx.createStatement();
	            st.executeUpdate(req);
	            System.out.println("Reservation supprimée !");
	        } catch (SQLException ex) {
	            System.out.println(ex.getMessage());
	        }
		
	}

	@Override
	public List<Reservation> afficher() {
List<Reservation> list = new ArrayList<>();
        
        String req = "SELECT * FROM reservation";
        try {
            Statement st = cnx.createStatement();
            ResultSet rs = st.executeQuery(req);
            while(rs.next()) {
                list.add(new Reservation(rs.getInt("id"), rs.getDate("dateDebut"), rs.getDate("dateFin"), rs.getInt("idAgence"), rs.getInt("idUser"), rs.getInt("idHotels"), rs.getInt("idTransport")));
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        
        
        return list;
	}

}