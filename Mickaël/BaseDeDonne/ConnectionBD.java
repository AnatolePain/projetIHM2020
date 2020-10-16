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
            this.cnx = DriverManager.getConnection("jdbc:mariadb://dwarves.iut-fbleau.fr/pain","pain","phpMyAdmin40");
        }
        catch(SQLException se)
		{
			System.err.println(se);
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