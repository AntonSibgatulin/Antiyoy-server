package uk.AntonSibgatulin.Battle.battles;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

import javax.swing.Timer;

import uk.AntonSibgatulin.Battle.Player;

public class Oil implements ActionListener{
public Player player = null;
public double level = 1;
public Game game = null;
Timer t = new Timer(5000,this);
public double base = 0.1;
public double maxV = 25+new Random().nextInt(25);
public int pos = 0;
public String color = null;
	public Oil(Player player,int pos,Game game ) {
		this.player = player;
		this.color = player.color;
		t.start();
		this.game = game;
		this.pos = pos;
		// TODO Auto-generated constructor stub
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		this.player.oil+=base*2.5;
		maxV-=base*2.5;
		if(player!=null)
		player.updateOilSocket();
		if(maxV<=0){
			game.OilBase.remove(pos);
			game.OilPlayer.remove(this);
			game.send_to_all("showFigureAt;1;"+pos+";"+player.color, null);
			int y = pos/game.battle.height;
			game.battle.map[(int)pos/game.battle.height][pos-y*game.battle.height]=player.color+";";
			t.stop();
		}
	}

}
