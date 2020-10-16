package fr.iutfbleau.projetIHM2020FI2.MODEL;
import java.sql.*;
import javax.swing.*;
import java.awt.*;

public class ConnectionBD
{
	private static ConnectionBD cbd;
	private Connection cnx;

	public ConnectionBD()
	{
		ConnectionBD.cbd = this;
		try
		{
			Class.forName("org.mariadb.jdbc.Driver");
		}
		catch(ClassNotFoundException cnf)
		{
            System.err.println(cnf);
            return;
		}

		try
		{
            this.cnx = DriverManager.getConnection("jdbc:mariadb://dwarves.iut-fbleau.fr/pain","pain","phpMyAdmin40");
        }
        catch(SQLException se)
		{
			System.err.println(se);
			return;
        }
	}

	public static Connection getConnection()
	{
		return ConnectionBD.cbd.cnx;
	}

	public static void deconnection()
	{
	
	}
}