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

	@Test
	public void baseDeDonneCreateTuple()
	{
		/*Connection cnx;
		PreparedStatement nouveauJoueur;
		ResultSet rs;
		cnx = ConnectionBD.getConnection();
        if(cnx != null)
        {
            try
            {
                nouveauJoueur = ConnectionBD.getConnection().prepareStatement("INSERT INTO `Joueur` (`id`, `nom`, `idPieceActuelle`) VALUES ('?', '?', '?')");
            }
            catch(SQLException se)
            {
                System.err.println(se);
            }   
        }

        if(nouveauJoueur != null)
        {
            try
            {
                rs = nouveauJoueur.executeQuery();
                rs.close();
            }
            catch(SQLException se)
            {
                System.err.println(se);
            } 
        }*/
	}
}