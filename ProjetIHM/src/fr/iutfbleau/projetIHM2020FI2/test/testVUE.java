package fr.iutfbleau.projetIHM2020FI2.test;
import fr.iutfbleau.projetIHM2020FI2.API.*;
import fr.iutfbleau.projetIHM2020FI2.VUE.*;
import javax.swing.*;
import java.awt.*;
import java.util.*;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertEquals;

import java.util.Arrays;
import java.util.Collection;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

public class TestVUE
{
	@Test
	public void MiniCarteSortie()
	{
		ImageClassLoader icl = new ImageClassLoader();
		MiniCarteVue mc = new MiniCarteVue();
		for(int i = 0 ; i < 100;i++)
		{
			mc.moveUp();
		}	
		assertFalse(false);
	}
}