package fr.iutfbleau.projetIHM2020FI2.MODEL;
import fr.iutfbleau.projetIHM2020FI2.API.*;
import java.util.*;

public class IDObject<E>
{
	private Map<E, Integer> objectToId = new HashMap<E, Integer>();
	private Map<Integer, E> idToObject = new HashMap<Integer, E>();
	private E element;

	public IDObject(E obj,Integer i)
	{
		this.put(obj,i);
	}

	public void put(E obj,Integer i)
	{
		this.objectToId.put(obj,i);
		this.idToObject.put(i,obj);
	}

	public Integer getID(E obj)
	{
		return this.objectToId.get(obj);
	}

	public E getObj(Integer i)
	{
		return this.idToObject.get(i);
	}

	public boolean sameClass(String classObj)
	{
		return this.element.getClass().getName() == classObj;
	}
}