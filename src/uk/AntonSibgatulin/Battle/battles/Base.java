package uk.AntonSibgatulin.Battle.battles;

import uk.AntonSibgatulin.Battle.Position;

public class Base {
public Position position = null;
public int type = 0;
public int level = 0;

	public Base(int type,Position position,int level) {
		this.type = type;
		this.position = position;
		this.level = level;
	}

}
