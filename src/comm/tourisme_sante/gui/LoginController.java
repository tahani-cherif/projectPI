/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package comm.tourisme_sante.gui;

import com.tourisme_sante.entities.Admin;
import com.tourisme_sante.entities.Client;
import com.tourisme_sante.entities.Gestionnaire;
import com.tourisme_sante.entities.Utilisateur;
import com.tourisme_sante.utils.Datasource;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javax.swing.JOptionPane;

/**
 * FXML Controller class
 *
 * @author User
 */
public class LoginController implements Initializable {

  
    @FXML
    private TextField txtname;
    @FXML
    private Button btnok;
    @FXML
    private PasswordField txtpass;
    Connection con;
    PreparedStatement pst;
    ResultSet rs;
   public static Utilisateur users;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void login(ActionEvent event) throws SQLException, IOException, ClassNotFoundException {
        String nom =txtname.getText();
        String pass=txtpass.getText();
        
       try {
               // Class.forName("com.mysql.jdbc.Driver");
               // con =DriverManager.getConnection("jdbc:mysql://localhost/tourisme_sante","root","");
                con = Datasource.getInstance().getCnx();
                pst=con.prepareStatement("select * from utilisateur where nom=?and MDP=?  ");
                pst.setString(1, nom);
                pst.setString(2, pass  );
                rs=pst.executeQuery();
                
            while (rs.next()) {
                System.out.println(rs.getString("Role"));
                
                switch (rs.getString("Role")) {
                    case "admin":
                        {  
                            users=new Admin(rs.getInt("id"),rs.getString("nom"),rs.getString("prenom"),rs.getString("email"),rs.getString("MDP"),rs.getString("role"));
                            FXMLLoader loader = new FXMLLoader(getClass().getResource("admin.fxml"));
                            Parent root = loader.load();
                            btnok.getScene().setRoot(root);
                            JOptionPane.showMessageDialog(null, "Bienvenue Admin!");
                            break;
                        }
                    case "client":
                    {   
                    users= new Client ( rs.getInt("number"), rs.getString("sex"), rs.getString("adresse"),rs.getInt("id"),rs.getString("nom"),rs.getString("prenom"),rs.getString("email"),rs.getString("MDP"),rs.getString("role"));
                            FXMLLoader loader = new FXMLLoader(getClass().getResource("GestionClient.fxml"));
                            Parent root = loader.load();
                            btnok.getScene().setRoot(root);
                            JOptionPane.showMessageDialog(null, "Bienvenue Client!");
                            break;
                        }
                     case "gestionnaire":
                    {   
                    users= new Gestionnaire ( rs.getInt("number"),  rs.getString("adresse"),rs.getInt("id"),rs.getString("nom"),rs.getString("prenom"),rs.getString("email"),rs.getString("MDP"),rs.getString("role"));
                            FXMLLoader loader = new FXMLLoader(getClass().getResource("gestionnaire.fxml"));
                            Parent root = loader.load();
                            btnok.getScene().setRoot(root);
                            JOptionPane.showMessageDialog(null, "Bienvenue Gestionnaire!");
                            break;
                    }
                   
                    default:
                        JOptionPane.showMessageDialog(null, "Username ou password invalide, Merci de réessayer");
                        break;
                }
           
            }
            
        }
        catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

    }
        
        /*if(nom.equals("")&& pass.equals("") )
        {
            JOptionPane.showMessageDialog(null, "UserName or Password Banked");
        }
        else
        {
            try {
                Class.forName("com.mysql.jdbc.Driver");
                con =DriverManager.getConnection("jdbc:mysql://localhost/tourisme_sante","root","");
                pst=con.prepareStatement("select * from utilisateur where nom=?and MDP=?  ");
                pst.setString(1, nom);
                pst.setString(2, pass  );
                rs=pst.executeQuery();
                
                if(rs.next())
                {
                                JOptionPane.showMessageDialog(null, "login success");


                  while (rs.next()) {
                
                
                switch (rs.getString("Role")) {
                    case "Admin":
                        { 
                            FXMLLoader loader = new FXMLLoader(getClass().getResource("GestionAdminis.fxml"));
                            Parent root = loader.load();
                            btnok.getScene().setRoot(root);
                            JOptionPane.showMessageDialog(null, "Bienvenue Admin!");
                            break;
                        }
                    case "Client":
                                        Client x = null;

                    {     x =new Client ( rs.getInt("number"), rs.getString("sex"), rs.getString("adresse"),rs.getInt("id"),rs.getString("nom"),rs.getString("prenom"),rs.getString("email"),rs.getString("MDP"),rs.getString("role"));
                            FXMLLoader loader = new FXMLLoader(getClass().getResource("GestionClient.fxml"));
                            Parent root = loader.load();
                            btnok.getScene().setRoot(root);
                            JOptionPane.showMessageDialog(null, "Bienvenue Client!");
                            break;
                        }
                }
                                          }
                }
                
                else
                {
                                JOptionPane.showMessageDialog(null, "login failed");
                                txtname.setText("");
                                txtpass.setText("");
                                txtname.requestFocus();


                }

                
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
            } catch (SQLException ex) {
                Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
      */  
    
    @FXML
    private void motdepassoublie(MouseEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("Motdepasse.fxml"));
                            Parent root = loader.load();
                            btnok.getScene().setRoot(root);
    }
    
    
}
