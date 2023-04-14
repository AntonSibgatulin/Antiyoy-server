package uk.AntonSibgatulin.Battle.battles.People;

import uk.AntonSibgatulin.Battle.Player;
import uk.AntonSibgatulin.Battle.Position;

public abstract class APeople {
	public int maxx = 1;
	public int maxy = 1;
	public int level = 1;
	
	public PeopleType type =null;
	public Player player = null;
	public int power = 0;
	public int id = 0;
	public int health = 0;
	public boolean isdied = false;
	public int idhome = 0;
	public int weidth = 0;
public Position pos = null;

	public APeople(PeopleType type) {
		this.type = type;
	}

}
