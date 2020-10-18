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

public class TestBaseDeDonne 
{
	@Test
	public void baseDeDonne()
	{
		ConnectionBD baseDonne = new ConnectionBD();
		assertFalse(ConnectionBD.getConnection() == null);
	}
}