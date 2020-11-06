package fr.iutfbleau.projetIHM2020FI2.MODEL;
import java.sql.*;
import java.awt.*;

public class ConnectionBD
{
	private static Connection cnx;
	private static boolean reset = true;
	private static PreparedStatement deleteALLTrucPS;
	private static PreparedStatement deleteALLPiecePS;
	private static PreparedStatement deleteALLPassagePS;
	private static PreparedStatement deleteALLJoueurPS;

	public static Connection getConnection()
	{
		if(ConnectionBD.cnx != null)
		{
			return ConnectionBD.cnx;
		}
		else
		{
			try
			{
				Class.forName("org.mariadb.jdbc.Driver");
			}
			catch(ClassNotFoundException cnf)
			{
           		System.err.println(cnf);
            	return null;
			}

			try
			{
            	ConnectionBD.cnx = DriverManager.getConnection("jdbc:mariadb://dwarves.iut-fbleau.fr/pain","pain","phpMyAdmin40");
        	}
        	catch(SQLException se)
			{
				System.err.println(se);
				return null;
        	}
			if(ConnectionBD.cnx != null && ConnectionBD.reset)
			{
				try
				{
					ConnectionBD.deleteALLTrucPS = ConnectionBD.cnx.prepareStatement("DELETE FROM API_Truc");
					ConnectionBD.deleteALLPiecePS = ConnectionBD.cnx.prepareStatement("DELETE FROM API_Piece");
					ConnectionBD.deleteALLPassagePS = ConnectionBD.cnx.prepareStatement("DELETE FROM API_Passage");
					ConnectionBD.deleteALLJoueurPS = ConnectionBD.cnx.prepareStatement("DELETE FROM API_Joueur");
					executeQueryPS(ConnectionBD.deleteALLTrucPS);
					executeQueryPS(ConnectionBD.deleteALLPiecePS);
					executeQueryPS(ConnectionBD.deleteALLPassagePS);
					executeQueryPS(ConnectionBD.deleteALLJoueurPS);

				}
				catch(SQLException se)
				{
					System.err.println(se);
				}   
			}
        	return ConnectionBD.cnx;
		}
	}

	private static void executeQueryPS(PreparedStatement ps)
	{
		try
		{
			ResultSet rs;
			rs = ps.executeQuery();
            rs.close();
		}
		catch(SQLException se)
		{
			System.err.println(se);
		}   
	}

	public static void deconnection()
	{
		if(ConnectionBD.cnx != null)
		{
			try
			{
				ConnectionBD.cnx.close();
			}
			catch(SQLException se)
			{
				System.err.println(se);
			}   
		}
	}
}