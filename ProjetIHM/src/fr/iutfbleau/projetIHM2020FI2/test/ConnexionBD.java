package fr.iutfbleau.projetIHM2020FI2.test;
import java.sql.*;

public class ConnexionBD {

	private static Connection cnx;
	private static PreparedStatement pst;

	public static void main(String[] args) {
		
		try{
			Class.forName("org.mariadb.jdbc.Driver");
		}catch(ClassNotFoundException e1){
			System.out.println("-----------------------");
			System.out.println("Il y'a une class not found execption");
			System.out.println("e1 = "+e1);
		}

		try{
			cnx = DriverManager.getConnection("jdbc:mariadb://dwarves.iut-fbleau.fr/pain","pain", "phpMyAdmin40");
		}catch(SQLException e1){
			System.out.println("-----------------------");
			System.out.println("Il y'a une une sql excepetion ");
			System.out.println("e1 = "+e1);
		}

		try{
		pst = cnx.prepareStatement(
  			"SELECT * FROM Vote");
		}catch(SQLException e1){
			System.out.println("-----------------------");
			System.out.println(" SQL execption in prepareStatement ");
			System.out.println(" e1 = "+e1);
		}

		try{
			ResultSet rs = pst.executeQuery();	
			rs.next();
			rs.next();
			rs.next();
			System.out.println("rs.getNString(competiteur) = " + rs.getString("competiteur"));
		}catch(SQLException e1){
			System.out.println("-----------------------");
			System.out.println(" SQL execption in executeQuery ");
			System.out.println(" e1 = "+e1);
		}

		System.out.println("fin BD");

	}

}