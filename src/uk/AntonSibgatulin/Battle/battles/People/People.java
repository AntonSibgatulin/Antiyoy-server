package uk.AntonSibgatulin.Battle.battles.People;

import java.util.ArrayList;

import uk.AntonSibgatulin.Battle.Player;
import uk.AntonSibgatulin.Battle.Position;
import uk.AntonSibgatulin.Battle.battles.Battle;

public class People extends APeople implements IPeople{


	public People(Player player ,int id,int idhome,int width,int power,Position pos,int level) {
		super(PeopleType.People);
		this.player = player;
		this.id = id;
		this.pos = pos;
		this.idhome = idhome;
		maxx = 1;
		maxy  = 1;
		
		this.power = power;
		this.level = level;
		
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
	public Player getPlayer() {
		// TODO Auto-generated method stub
		return player;
	}

	
	
		
	@Override
	public void shot(Battle battle, IPeople[] player) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void shot(Battle battle, ArrayList<IPeople> player) {
		
		for(int i = 0;i<player.size();i++){
			IPeople people = player.get(i);
		boolean died = 	people.isDied(getPower());
		people.shotme(battle, (IPeople)this, died, i);
		}
		
	}

	@Override
	public int getHealth() {
		// TODO Auto-generated method stub
		return health;
	}

	@Override
	public boolean isDied(int power) {
		// TODO Auto-generated method stub
		if(this.getHealth()-power<=0){
			isdied = true;
		return true;
		}else{
			this.health -= getPower();
			
			return false;
		}
	}

	@Override
	public void shotme(Battle battle, IPeople people, boolean isdied, int power) {
		if(isdied){
			//battle.send_to_all("die;"+this.getId()+";"+people.getId());
		}else{
			people.shotme(battle, (IPeople)this, people.isDied(this.getPower()), getPower());
		}
		
	}

	@Override
	public boolean isdied() {
		// TODO Auto-generated method stub
		return this.isdied;
	}

	@Override
	public Position getPosition() {
		// TODO Auto-generated method stub
		return pos;
	}

	@Override
	public void setPosition(int x,int y) {
		pos.x = x;
		pos.y = y;
	}

	@Override
	public void move(int x, int y) {
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