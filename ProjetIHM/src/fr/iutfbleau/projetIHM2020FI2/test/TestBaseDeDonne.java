package fr.iutfbleau.projetIHM2020FI2.test;
import fr.iutfbleau.projetIHM2020FI2.MODEL.*;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertEquals;

import java.util.Arrays;
import java.util.Collection;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;
import java.sql.*;

public class TestBaseDeDonne 
{
    Connection cnx;
    PreparedStatement nouveauJoueur;
    PreparedStatement resetAutoIncremente;
    ResultSet rs;

	@Test
	public void baseDeDonneConnection()
	{
		assertFalse(ConnectionBD.getConnection() == null);
	}

	@Test
	public void baseDeDonneDeconnection()
	{
		ConnectionBD.deconnection();
		assertFalse(ConnectionBD.getConnection() == null);
	}

    @Test()//expected = SQLException.class
	public void baseDeDonneCreateTuple()
	{
        try
        {
            cnx = ConnectionBD.getConnection();
            nouveauJoueur = cnx.prepareStatement("INSERT INTO `API_Joueur` (`id`, `idPieceActuelle`) VALUES (?, ?)");
            nouveauJoueur.setInt(1,0);
            nouveauJoueur.setInt(2,0);
            rs = nouveauJoueur.executeQuery();
            rs.close();
            ConnectionBD.deconnection();
        }
        catch(SQLException se)
        {
            System.err.println(se);
        } 
	}

    @Test()//expected = SQLException.class
    public void baseDeDonneDeleteTuple()
    {
        try
        {
            cnx = ConnectionBD.getConnection();
            resetAutoIncremente = cnx.prepareStatement("ALTER TABLE API_Joueur AUTO_INCREMENT = 0");
            nouveauJoueur = cnx.prepareStatement("DELETE FROM API_Joueur WHERE id = ?;");
            nouveauJoueur.setInt(1,1);
            rs = resetAutoIncremente.executeQuery();
            rs.close();
            rs = nouveauJoueur.executeQuery();
            rs.close();
            ConnectionBD.deconnection();
        }
        catch(SQLException se)
        {
            System.err.println(se);
        } 
    }
}