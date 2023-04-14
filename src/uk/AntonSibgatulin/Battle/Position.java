package uk.AntonSibgatulin.Battle;

public class Position {
public int id = -1;
public int x = -1;
public int y = -1;
	public Position(int id ,int x,int y) {
		this.id = id;
		this.x = x;
		this.y = y;
	}
	public void setPos(int x,int y){
		this.x = x;
		this.y = y;
	}

}
