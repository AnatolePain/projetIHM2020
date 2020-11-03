package fr.iutfbleau.projetIHM2020FI2.MODEL;
import fr.iutfbleau.projetIHM2020FI2.API.*;
import java.util.*;

public class GestionIDBD
{
	private static List<IDObject> idobj = new ArrayList<IDObject>();
	
	public static void put(Object element,Integer i)
	{
		boolean find = false;
		for(IDObject idElements : GestionIDBD.idobj)
		{
			if(idElements.sameClass(element.getClass().getName()))
			{
				find = true;
				idElements.put(element,i);
			}
		}
		if(!find)
		{
			GestionIDBD.idobj.add(new IDObject(element,i));
		}
	}

	public static int getID(Object element)
	{
		for(IDObject idElements : GestionIDBD.idobj)
		{
			if(idElements.sameClass(element.getClass().getName()))
			{
				return idElements.getID(element);
			}
		}
		return 0;
	}

	public static Object getElement(int i,String classObj)
	{
		for(IDObject idElements : GestionIDBD.idobj)
		{
			if(idElements.sameClass(classObj))
			{
				return idElements.getObj(i);
			}
		}
		return null;
	}
}