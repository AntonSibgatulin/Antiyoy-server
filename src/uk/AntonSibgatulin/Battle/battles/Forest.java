package uk.AntonSibgatulin.Battle.battles;

import java.util.ArrayList;
import java.util.Random;

import org.json.JSONException;

import uk.AntonSibgatulin.Battle.Position;

public class Forest {
public ArrayList<Tree> trees = new ArrayList<>();
public int id = 0;
public Battle battle = null;
public Game game = null;

	public Forest(Battle battle,Game game) {
		this.battle = battle;
		this.game = game;
		
	}
	
	
	
	public void update(){
		int i = new Random().nextInt(battle.width);
		int j = new Random().nextInt(battle.height);
		if(battle.map[i][j].split(";").length==1){
			battle.map[i][j] = battle.map[i][j].split(";")[0]+";r";
			game.send_to_all("showFigureAt;r;"+(i*battle.height+j)+";none", null);
		//	System.out.println(i+" "+j);
		}
		
	}
}
