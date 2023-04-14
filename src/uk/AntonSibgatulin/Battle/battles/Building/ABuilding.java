package uk.AntonSibgatulin.Battle.battles.Building;

import java.util.ArrayList;

import uk.AntonSibgatulin.Battle.Position;
import uk.AntonSibgatulin.Battle.battles.People.IPeople;

public abstract class ABuilding {
public BuildingType type = null;
public int money  = 0;
public Position pos = null;
public int id = 0;

public int nalog = 0;
public ArrayList<IPeople> peoples = new ArrayList<>();
public IPeople[] people = null;


	public ABuilding(BuildingType type) {
		
		this.type = type;
		
	}
	
	
	

}
