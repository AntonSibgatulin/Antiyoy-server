package uk.AntonSibgatulin.Battle.battles.People;

import java.util.ArrayList;

import uk.AntonSibgatulin.Battle.Player;
import uk.AntonSibgatulin.Battle.Position;
import uk.AntonSibgatulin.Battle.battles.Battle;

public class Voin extends APeople implements IPeople{


	public Voin(Player player,Position pos,int id,int idhome,int width,int power,int level) {
		super(PeopleType.Voin);
		this.player = player;
		
		this.pos = pos;
		this.id = id;
		this.idhome = idhome;
		this.weidth = width;
		this.power = power;
		this.level = level;
		
		
		// TODO Auto-generated constructor stub
	}

	@Override
	public Player getPlayer() {
		// TODO Auto-generated method stub
		return player;
	}

	@Override
	public int getHealth() {
		// TODO Auto-generated method stub
		return health;
	}

	@Override
	public boolean isDied(int power) {
		// TODO Auto-generated method stub
		return isdied;
	}

	@Override
	public PeopleType getType() {
		// TODO Auto-generated method stub
		return type;
	}

	@Override
	public int getPower() {
		// TODO Auto-generated method stub
		return power;
	}

	@Override
	public int getWeight() {
		// TODO Auto-generated method stub
		return weidth;
	}

	@Override
	public int getIdHome() {
		// TODO Auto-generated method stub
		return idhome;
	}

	@Override
	public int getId() {
		// TODO Auto-generated method stub
		return id;
	}

	@Override
	public void shot(Battle battle, IPeople[] player) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void shot(Battle battle, ArrayList<IPeople> player) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean isdied() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void shotme(Battle battle, IPeople people, boolean isdied, int power) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Position getPosition() {
		// TODO Auto-generated method stub
		return pos;
	}

	@Override
	public void setPosition(int x ,int y) {
		
		pos.x = x;
		pos.y = y;
		
	}

	@Override
	public void move(int x, int y) {
		maxx = 2+level;
		maxy = 2+level;
		
		if(Math.abs(pos.x-x)<=maxx && Math.abs(pos.y-y)<=maxy){
			
			pos.x = x;
			pos.y = y;
			
		}
		
	}

	@Override
	public int getLevel() {
		// TODO Auto-generated method stub
		return level;
	}

}
