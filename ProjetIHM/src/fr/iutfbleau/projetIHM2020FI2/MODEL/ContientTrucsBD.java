package fr.iutfbleau.projetIHM2020FI2.MODEL;
import fr.iutfbleau.projetIHM2020FI2.API.*;
import java.sql.*;
import java.util.*;

public class ContientTrucsBD implements ContientTrucs
{

	private Set<Truc> contenu;

    public ContientTrucsBD()
	{
        this.contenu= new LinkedHashSet<Truc>();	
    }

    @Override
    public Iterator<Truc> getTrucs(){return this.contenu.iterator();}
    
    @Override
    public boolean addTruc(Truc t){return false;}

    @Override
    public boolean removeTruc(Truc t){return false;}

    @Override
    public boolean containsTruc(Truc t){return false;}  

    @Override
    public int combienTrucs(){return 0;}

}