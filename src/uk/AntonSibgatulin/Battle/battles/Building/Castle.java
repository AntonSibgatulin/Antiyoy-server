package uk.AntonSibgatulin.Battle.battles.Building;

import java.util.ArrayList;

import uk.AntonSibgatulin.Battle.Position;
import uk.AntonSibgatulin.Battle.battles.People.IPeople;

public class Castle extends ABuilding implements IBuilding{

	public Castle(int x,int y) {
		super(BuildingType.castle);
		pos = new Position(0,x,y);
		// TODO Auto-generated constructor stub
	}

	@Override
	public int getId() {
		// TODO Auto-generated method stub
		return id;
	}

	@Override
	public BuildingType getType() {
		// TODO Auto-generated method stub
		return type;
	}

	@Override
	public int getMoney() {
		// TODO Auto-generated method stub
		return money;
	}

	@Override
	public ArrayList<IPeople> getPeoplesList() {
		// TODO Auto-generated method stub
		return this.peoples;
	}

	@Override
	public IPeople[] getPeoples() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int getNalog() {
		// TODO Auto-generated method stub
		return nalog;
	}

	@Override
	public Position getPosition() {
		// TODO Auto-generated method stub
		return pos;
	}

	@Override
	public void setPosition(int x, int y) {
		pos.setPos(pos.x+x, pos.y+y);
		
	}

}
