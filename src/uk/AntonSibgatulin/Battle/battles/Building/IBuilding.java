package uk.AntonSibgatulin.Battle.battles.Building;

import java.util.ArrayList;

import uk.AntonSibgatulin.Battle.Position;
import uk.AntonSibgatulin.Battle.battles.People.IPeople;

public interface IBuilding {
	public Position getPosition();
	
	public void setPosition(int x,int y);
public int getId();
public BuildingType getType();
public int getMoney();

public ArrayList<IPeople> getPeoplesList();
public IPeople[] getPeoples();

public int getNalog();


}