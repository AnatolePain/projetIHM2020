package fr.iutfbleau.projetIHM2020FI2.MODEL;
import java.sql.*;
import java.awt.*;

public class ConnectionBD
{
	private static Connection cnx;

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
        	return ConnectionBD.cnx;
		}
	}

	public static void deconnection()
	{
	
	}
}