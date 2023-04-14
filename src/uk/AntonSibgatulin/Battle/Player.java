package uk.AntonSibgatulin.Battle;

import java.util.ArrayList;
import java.util.HashMap;

import uk.AntonSibgatulin.Battle.battles.Home;
import uk.AntonSibgatulin.Battle.battles.People.IPeople;
import uk.AntonSibgatulin.User.User;

public class Player {
	public HashMap<Integer,Home> homes = new HashMap<>();
	public ArrayList<Integer> idoils = new ArrayList<>();
	public double bars = 5.5;
	
	
	public int level = 1;
	public int idsize = 0;
	public String color = "";
	
	public int door = 0;
	public double oil = 0;
	public int silver = 0;
	public int ferrum = 0;
	public int watter = 0;
	
	public double doorstart = 0;

	
	
	public void setMoney(double money){
		this.money = money;
		
	}

	public void updateMoneySocket(){
		this.user.send("tables;money;"+this.money);
	}

	public void updateOilSocket(){
		this.user.send("tables;oil;"+this.oil);
	}
	
	
	public void updateTreeSocket(){
		this.user.send("tables;tree;"+this.door);
	}
	public int pricep = 10;
	public int pricev = 15;
	public int priceh = 8;
	public int pricec = 100;
	public int pricef = 25;
	public int dprice = 3;
	
	
	public HashMap<Integer,IPeople> piple = new HashMap<>();
	
	public User user  = null;
	public int nextday = 5;
	
	public double money = -1;
	//public HashMap<>
	
	public Player(double money,int nextday,User user) {
		this.money = money;
		this.nextday = nextday;
		this.user = user;
		
		// TODO Auto-generated constructor stub
	}
	
	
	
}
