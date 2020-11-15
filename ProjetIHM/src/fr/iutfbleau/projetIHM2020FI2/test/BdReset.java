package fr.iutfbleau.projetIHM2020FI2.test;
import java.sql.*;
import java.awt.*;

public class BdReset
{
	private static Connection cnx;
	private static boolean reset = true;
	private static PreparedStatement deleteALLTrucPS;
	private static PreparedStatement deleteALLPiecePS;
	private static PreparedStatement deleteALLPassagePS;
	private static PreparedStatement deleteALLJoueurPS;

	public static void main(String[] args) 
	{
		getConnection();
		deconnection();
	}


	private static Connection getConnection()
	{
		if(BdReset.cnx != null)
		{
			return BdReset.cnx;
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
            	BdReset.cnx = DriverManager.getConnection("jdbc:mariadb://dwarves.iut-fbleau.fr/pain","pain","phpMyAdmin40");
        	}
        	catch(SQLException se)
			{
				System.err.println(se);
				return null;
        	}
			if(BdReset.cnx != null && BdReset.reset)
			{
				try
				{
					BdReset.deleteALLTrucPS = BdReset.cnx.prepareStatement("DELETE FROM API_Truc");
					BdReset.deleteALLPiecePS = BdReset.cnx.prepareStatement("DELETE FROM API_Piece");
					BdReset.deleteALLPassagePS = BdReset.cnx.prepareStatement("DELETE FROM API_Passage");
					BdReset.deleteALLJoueurPS = BdReset.cnx.prepareStatement("DELETE FROM API_Joueur");
					executeQueryPS(BdReset.deleteALLTrucPS);
					executeQueryPS(BdReset.deleteALLPiecePS);
					executeQueryPS(BdReset.deleteALLPassagePS);
					executeQueryPS(BdReset.deleteALLJoueurPS);

				}
				catch(SQLException se)
				{
					System.err.println(se);
				}   
			}
        	return BdReset.cnx;
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
		if(BdReset.cnx != null)
		{
			try
			{
				BdReset.cnx.close();
			}
			catch(SQLException se)
			{
				System.err.println(se);
			}   
		}
	}
}