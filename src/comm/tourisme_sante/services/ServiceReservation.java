package comm.tourisme_sante.services;

import com.tourisme_sante.entities.Reservation;
import com.tourisme_sante.utils.Datasource;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;


import java.sql.Date;
import java.time.LocalDate;


public class ServiceReservation implements services<Reservation> {
    private Connection cnx = Datasource.getInstance().getCnx();

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
        String req2="SELECT dateDebut,reservation.id,dateFin,reservation.idAgence,idUser,idTransport,idHotels,agence.nom,hotel.nom as nomHotel,utilisateur.prenom as nomUser FROM agence JOIN reservation on agence.id=reservation.idAgence JOIN transport on reservation.idTransport=transport.id JOIN hotel on reservation.idHotels=hotel.id JOIN utilisateur on reservation.idUser =utilisateur.id;";
        try {
            Statement st = cnx.createStatement();
            ResultSet rs = st.executeQuery(req2);
            while(rs.next()) {
                list.add(new Reservation(rs.getInt("id"), rs.getDate("dateDebut"), rs.getDate("dateFin"), rs.getInt("idAgence"), rs.getInt("idUser"), rs.getInt("idHotels"), rs.getInt("idTransport"),rs.getString("nom"),rs.getString("nomHotel"),rs.getString("nomUser")));
            }
            System.out.println("comm.tourisme_sante.services.ServiceReservation.afficher()"+list);
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        
        
        return list;
	}
        	public List<Reservation> afficherParDate(Date dateDebut,Date dateFin) {
List<Reservation> list = new ArrayList<>();
        
        String req = "SELECT * FROM reservation";
        String req2="SELECT dateDebut,reservation.id,dateFin,idAgence,idUser,idTransport,idHotels,agence.nom FROM reservation join agence on reservation.idAgence=agence.id WHERE dateFin BETWEEN '"+dateDebut+"' AND '"+dateFin+"'";
        try {
            Statement st = cnx.createStatement();
            ResultSet rs = st.executeQuery(req2);

            while(rs.next()) {
                list.add(new Reservation(rs.getInt("id"), rs.getDate("dateDebut"), rs.getDate("dateFin"), rs.getInt("idAgence"), rs.getInt("idUser"), rs.getInt("idHotels"), rs.getInt("idTransport"),rs.getString("nom")));
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        
        
        return list;
	}

}
