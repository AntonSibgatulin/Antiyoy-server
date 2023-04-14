	package uk.AntonSibgatulin.Battle.battles;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Random;

import javax.swing.Timer;

import org.java_websocket.WebSocket;
import org.json.JSONException;
import org.json.JSONObject;

import uk.AntonSibgatulin.Battle.Player;
import uk.AntonSibgatulin.Battle.Position;
import uk.AntonSibgatulin.Battle.battles.People.IPeople;
import uk.AntonSibgatulin.Battle.battles.People.People;
import uk.AntonSibgatulin.User.User;

public class Game extends Thread implements ActionListener{
	public HashMap<Integer,Oil> OilBase = new HashMap<>();
	public ArrayList<Oil> OilPlayer = new ArrayList<>();
	
public HashMap<Player,String> teams = new HashMap<>();
public HashMap<WebSocket,Player> players = new HashMap<>();
public HashSet<WebSocket> clients = null;
public String[] color = {"blue","green","red","aqua"};
public String tradeColorRoad = "yellow";
public ArrayList<String> colour = new ArrayList<String>();




public static final int p = 2;
public static final int R = 4;
public static final int K = 6;
public static final int k = 7;
public static final int P = 4;


public static final double pp = 3.5;
public static final double tb = 8;
public static final double pb = 12;
public static final double pR = 5.99;
public static final double tR = 1;
public static final double pK = 13.99;
public static final double tK = 5;

public static final double pP = 50;
public static final double tP = 20;
public static final double po = 25;
public static final double to = 30;

public static final double pt = 55.99;
public static final double tt = 50;

public static final double pc = 120.99;
public static final double tc = 100;

public static final double ps = 80.99;
public static final double ts = 50;


public int id = 0 ;

public Battle battle = new Battle(25,30,this);
public String name = null;

	public Timer t = new Timer(5000,this);
	
	public void setColor(int size,Player player,int x,int y){
		int xx = size;
		int yy = size;
		int x1 = x-xx;
		int y1 = y-xx;
		for(int i = x1;i<x1+xx*2+1;i++){
			for(int j = y1;j<y1+yy*2+1;j++){
			//	System.out.print(battle.map[i][j]+" ");
				
				if(i<battle.width && j<battle.height &&(i==x && j==y)==false && battle.map[i][j].split(";")[0].equals(player.color)==false){
					if(player.user.login.equals("Anton")){
						if(battle.map[i][j].split(";")[0].equals(player.color)==false && battle.map[i][j].split(";").length==2 && (battle.map[i][j].split(";")[1].equals("0")))
							
						{
							
							
						}
						else{battle.map[i][j]=player.color+";";
						this.send_to_all("showFigureAt;1;"+(i*battle.height+j)+";"+player.color+";none", null);
						}
					}else{
					if(battle.map[i][j].split(";")[0].equals(player.color)==false && battle.map[i][j].split(";").length==2 && (battle.map[i][j].split(";")[1].equals("c") ||  battle.map[i][j].split(";")[1].equals("P") ||  battle.map[i][j].split(";")[1].equals("o")))
					
					{
						
						
					}
					else{battle.map[i][j]=player.color+";";
					this.send_to_all("showFigureAt;1;"+(i*battle.height+j)+";"+player.color+";none", null);
					}
					}
				}
				
				//if(battle.map[i][j].split(";").length==1 || battle)
				
			} 
			
			//System.out.println();
		}
	}
public boolean accept(int size,String color,int x,int y){
	int xx = size;
	int yy = size;
	x = x-xx;
	y = y-xx;
	
	
	
	for(int i = x;i<x+xx*2+1;i++){
		for(int j = y;j<y+yy*2+1;j++){
			if(i>=0 && i<battle.width && j>=0 && j<battle.height){
		//	System.out.print(battle.map[i][j]+" ");
			
				if(battle.map[i][j].split(";")[0].equals(color))return true;
			}
			
		}
		
	}
	
	return false;
}private double pow2(double value) {
    return Math.pow(value, 2.0D);
 }
public double distanceTo(int x,int y,int x1,int y1) {
    return Math.sqrt(this.pow2((double)(x-x1)) + this.pow2((double)(y-y1)));
 }
public void executeCommand(WebSocket con,String str){
	String[] string = str.split(";");
	
	Player player = players.get(con);
	if(player == null)return;
	if(string[0].equals("move")){
		String type = string[3];
		int last = Integer.valueOf(string[1]);
		int to = Integer.valueOf(string[2]);
		//int pos = Integer.valueOf(string[2]);
		int xx = (int) (last/battle.height);
		int yy = (last-(xx*battle.height));
		if(battle.map[xx][yy].split(";")[1].equals(string[3]) && battle.map[xx][yy].split(";")[0].equals(player.color)){
			int x = (int) (to/battle.height);
			int  y = (to-(x*battle.height));
		
			
			
			if(type.equals("p")  ){
				 if(accept(1,player.color,x,y)){
					if(( Math.abs(xx-x)<=p && Math.abs(yy-y)<=p)&&( battle.map[x][y].split(";")[0].equals("none") || battle.map[x][y].split(";").length==1 || (battle.map[x][y].split(";")[0].equals(player.color)==false && battle.map[x][y].split(";").length>1 && (battle.map[x][y].split(";")[1].equals("p") || battle.map[x][y].split(";")[1].equals("r") || battle.map[x][y].split(";")[1].equals("m")|| battle.map[x][y].split(";")[1].equals("b")))) || (battle.map[x][y].split(";").length==2 && battle.map[x][y].split(";")[0].equals(player.color) && (battle.map[x][y].split(";")[1].equals("r") || battle.map[x][y].split(";")[1].equals("m")))){
						if(battle.map[x][y].split(";").length==2 ){
							if(battle.map[x][y].split(";")[1].equals("r")){
								int g = new Random().nextInt(14)+1;
								player.door+=g;
								System.out.println("tree = "+g);
								player.updateTreeSocket();
							}else if(battle.map[x][y].split(";")[1].equals("m")){
								int calc = new Random().nextInt(25);
								double g = new Random().nextInt(9)+(Double.valueOf("0."+calc))+1;
								player.money+=g;
								player.updateMoneySocket();
								System.out.println("money = "+g);
							}
							
						}
						battle.map[x][y] = player.color+";p";
						battle.map[xx][yy] = battle.map[xx][yy].split(";")[0]+";";
						this.send_to_all("showFigureAt;p;"+to+";"+player.color+";"+last, null);
					}else{
						//
						if(battle.map[x][y].split(";").length==1){
							this.send_to("showFigureAt;"+1+";"+Integer.valueOf(string[2])+";"+battle.map[x][y].split(";")[0], con);
							}else{
								this.send_to("showFigureAt;"+battle.map[x][y].split(";")[1]+";"+Integer.valueOf(string[2])+";"+battle.map[x][y].split(";")[0], con);
								
							}
							this.send_to("showFigureAt;p;"+last+";"+player.color, con);
								}
					}else{
						if(battle.map[x][y].split(";").length==1){
						this.send_to("showFigureAt;"+1+";"+Integer.valueOf(string[2])+";"+battle.map[x][y].split(";")[0], con);
						}else{
							this.send_to("showFigureAt;"+battle.map[x][y].split(";")[1]+";"+Integer.valueOf(string[2])+";"+battle.map[x][y].split(";")[0], con);
							
						}
						this.send_to("showFigureAt;p;"+last+";"+player.color, con);
						
					}
			 }
			
			
			
			
			
			
			
			
			
			
			

			
			
			if(type.equals("R")  ){
				 if(accept(1,player.color,x,y)){
					if(( Math.abs(xx-x)<=R && Math.abs(yy-y)<=R)&&(battle.map[x][y].split(";")[0].equals("none") || battle.map[x][y].split(";").length==1 || (battle.map[x][y].split(";")[0].equals(player.color)==false && battle.map[x][y].split(";").length>1 && (battle.map[x][y].split(";")[1].equals("p") || battle.map[x][y].split(";")[1].equals("r") || battle.map[x][y].split(";")[1].equals("m")|| battle.map[x][y].split(";")[1].equals("b"))))){
						if(battle.map[x][y].split(";").length==2 ){
							if(battle.map[x][y].split(";")[1].equals("r")){
								int g = new Random().nextInt(14)+1;
								player.door+=g;
								System.out.println("tree = "+g);
								player.updateTreeSocket();
							}else if(battle.map[x][y].split(";")[1].equals("m")){
								int calc = new Random().nextInt(25);
								double g = new Random().nextInt(9)+(Double.valueOf("0."+calc))+1;
								player.money+=g;
								player.updateMoneySocket();
								System.out.println("money = "+g);
							}
							
						}
						battle.map[x][y] = player.color+";R";
						battle.map[xx][yy] = battle.map[xx][yy].split(";")[0]+";";
						
						this.send_to_all("showFigureAt;R;"+to+";"+player.color+";"+last, null);
					}else{
						
						if(battle.map[x][y].split(";").length==1){
							this.send_to("showFigureAt;"+1+";"+Integer.valueOf(string[2])+";"+battle.map[x][y].split(";")[0], con);
							}else{
								this.send_to("showFigureAt;"+battle.map[x][y].split(";")[1]+";"+Integer.valueOf(string[2])+";"+battle.map[x][y].split(";")[0], con);
								
							}
							this.send_to("showFigureAt;R;"+last+";"+player.color, con);
								}
					}else{
						if(battle.map[x][y].split(";").length==1){
						this.send_to("showFigureAt;"+1+";"+Integer.valueOf(string[2])+";"+battle.map[x][y].split(";")[0], con);
						}else{
							this.send_to("showFigureAt;"+battle.map[x][y].split(";")[1]+";"+Integer.valueOf(string[2])+";"+battle.map[x][y].split(";")[0], con);
							
						}
						this.send_to("showFigureAt;R;"+last+";"+player.color, con);
						
					}
			 }
			 ////////////////////////////////////////////////////////////////////////////////
			 
			 
			 
			 
			 
			 
			 if(type.equals("P")  ){
				 System.out.println("Solder moving");
				 if(accept(P,player.color,x,y)){
					if(( Math.abs(xx-x)<=P && Math.abs(yy-y)<=P)&&( battle.map[x][y].split(";")[0].equals("none") || battle.map[x][y].split(";").length==1 || (battle.map[x][y].split(";")[0].equals(player.color)==false )) || (battle.map[x][y].split(";").length==2 && battle.map[x][y].split(";")[0].equals(player.color) && (battle.map[x][y].split(";")[1].equals("r") || battle.map[x][y].split(";")[1].equals("m")))){
						 System.out.println("Solder moving in if "+battle.map[x][y]);
						 battle.map[xx][yy] = battle.map[xx][yy].split(";")[0]+";";
							
						if(battle.map[x][y].split(";").length>1 && battle.map[x][y].split(";")[1]!=null&&battle.map[x][y].split(";")[1].equals("s")){
							battle.map[x][y] = player.color+";";
							this.send_to_all("showFigureAt;0;"+to+";"+player.color+";"+last, null);
						}else{
						battle.map[x][y] = player.color+";P";
						this.send_to_all("showFigureAt;P;"+to+";"+player.color+";"+last, null);
						}
						
					}else{
						
						if(battle.map[x][y].split(";").length==1){
							this.send_to("showFigureAt;"+1+";"+Integer.valueOf(string[2])+";"+battle.map[x][y].split(";")[0], con);
							}else{
								this.send_to("showFigureAt;"+battle.map[x][y].split(";")[1]+";"+Integer.valueOf(string[2])+";"+battle.map[x][y].split(";")[0], con);
								
							}
							this.send_to("showFigureAt;P;"+last+";"+player.color, con);
								}
					}else{
						if(battle.map[x][y].split(";").length==1){
						this.send_to("showFigureAt;"+1+";"+Integer.valueOf(string[2])+";"+battle.map[x][y].split(";")[0], con);
						}else{
							this.send_to("showFigureAt;"+battle.map[x][y].split(";")[1]+";"+Integer.valueOf(string[2])+";"+battle.map[x][y].split(";")[0], con);
							
						}
						this.send_to("showFigureAt;P;"+last+";"+player.color, con);
						
					}
			 }
			 ////////////////////////////////////////////////////////////////////////////////
			
			 
			 
			 
			 
			 if(type.equals("k")  ){
				 if(accept(1,player.color,x,y)){
					if(( Math.abs(xx-x)<=k && Math.abs(yy-y)<=k)&&( battle.map[x][y].split(";")[0].equals("none") || battle.map[x][y].split(";").length==1 || (battle.map[x][y].split(";")[0].equals(player.color)==false )) || (battle.map[x][y].split(";").length==2 && (battle.map[x][y].split(";")[1].equals("r") || battle.map[x][y].split(";")[1].equals("m"))) ){
						battle.map[x][y] = player.color+";k";
						battle.map[xx][yy] = battle.map[xx][yy].split(";")[0]+";";
						this.send_to_all("showFigureAt;k;"+to+";"+player.color+";"+last, null);
					}else{
						
						if(battle.map[x][y].split(";").length==1){
							this.send_to("showFigureAt;"+1+";"+Integer.valueOf(string[2])+";"+battle.map[x][y].split(";")[0], con);
							}else{
								this.send_to("showFigureAt;"+battle.map[x][y].split(";")[1]+";"+Integer.valueOf(string[2])+";"+battle.map[x][y].split(";")[0], con);
								
							}
							this.send_to("showFigureAt;k;"+last+";"+player.color, con);
								}
					}else{
						if(battle.map[x][y].split(";").length==1){
						this.send_to("showFigureAt;"+1+";"+Integer.valueOf(string[2])+";"+battle.map[x][y].split(";")[0], con);
						}else{
							this.send_to("showFigureAt;"+battle.map[x][y].split(";")[1]+";"+Integer.valueOf(string[2])+";"+battle.map[x][y].split(";")[0], con);
							
						}
						this.send_to("showFigureAt;k;"+last+";"+player.color, con);
						
					}
			 }
			 
			 
			 
			 
			 
			 
			 
			 
			 
			 
			 
			 
			 
			 
			 
			 
			 if(type.equals("K")  ){
				 if(accept(1,player.color,x,y)){
					if(( Math.abs(xx-x)<=K && Math.abs(yy-y)<=K)&&(battle.map[x][y].split(";")[0].equals("none") || battle.map[x][y].split(";").length==1 || (battle.map[x][y].split(";")[0].equals(player.color)==false && battle.map[x][y].split(";").length>1 && (battle.map[x][y].split(";")[1].equals("p") || battle.map[x][y].split(";")[1].equals("r") || battle.map[x][y].split(";")[1].equals("m")|| battle.map[x][y].split(";")[1].equals("b")|| battle.map[x][y].split(";")[1].equals("B")|| battle.map[x][y].split(";")[1].equals("t"))))){
						battle.map[x][y] = player.color+";K";
						battle.map[xx][yy] = battle.map[xx][yy].split(";")[0]+";";
						this.send_to_all("showFigureAt;K;"+to+";"+player.color+";"+last, null);
					}else{
						
						if(battle.map[x][y].split(";").length==1){
							this.send_to("showFigureAt;"+1+";"+Integer.valueOf(string[2])+";"+battle.map[x][y].split(";")[0], con);
							}else{
								this.send_to("showFigureAt;"+battle.map[x][y].split(";")[1]+";"+Integer.valueOf(string[2])+";"+battle.map[x][y].split(";")[0], con);
								
							}
							this.send_to("showFigureAt;K;"+last+";"+player.color, con);
								}
					}else{
						if(battle.map[x][y].split(";").length==1){
						this.send_to("showFigureAt;"+1+";"+Integer.valueOf(string[2])+";"+battle.map[x][y].split(";")[0], con);
						}else{
							this.send_to("showFigureAt;"+battle.map[x][y].split(";")[1]+";"+Integer.valueOf(string[2])+";"+battle.map[x][y].split(";")[0], con);
							
						}
						this.send_to("showFigureAt;K;"+last+";"+player.color, con);
						
					}
			 }
			 ////////////////////////////////////////////////////////////////////////////////
			 
			 
		}else{
			
			int x = (int) (to/battle.height);
			int  y = (to-(x*battle.height));
			if(battle.map[x][y].split(";").length==1){
				this.send_to("showFigureAt;"+1+";"+Integer.valueOf(string[2])+";"+battle.map[x][y].split(";")[0], con);
				}else{
					this.send_to("showFigureAt;"+battle.map[x][y].split(";")[1]+";"+Integer.valueOf(string[2])+";"+battle.map[x][y].split(";")[0], con);
					
				}
			
			System.out.println(battle.map[x][y]+" "+(battle.map[xx][yy]));
			
			
			if(battle.map[xx][yy].split(";").length==1){
				this.send_to("showFigureAt;"+1+";"+Integer.valueOf(string[1])+";"+battle.map[xx][yy].split(";")[0], con);
				}else{
					this.send_to("showFigureAt;"+battle.map[xx][yy].split(";")[1]+";"+Integer.valueOf(string[1])+";"+battle.map[xx][yy].split(";")[0], con);
					
				}
			//	this.send_to("showFigureAt;p;"+last+";"+player.color, con);
					}
		
		
	}
	if(string[0].equals("bought")){
		
		
		String type = string[1];
		int pos = Integer.valueOf(string[2]);
		int x = (int) (pos/battle.height);
		int y = (pos-(x*battle.height));
		
		
		//рабочий
		if(type.equals("p") && accept(1,player.color,x,y) && player.money-pp>=0){
			if(battle.map[x][y].split(";")[0].equals("none") || battle.map[x][y].split(";").length==1 || (battle.map[x][y].split(";")[0].equals(player.color)==false && battle.map[x][y].split(";").length>1 && (battle.map[x][y].split(";")[1].equals("p") || battle.map[x][y].split(";")[1].equals("r") || battle.map[x][y].split(";")[1].equals("m")|| battle.map[x][y].split(";")[1].equals("b")))){
				battle.map[x][y] = player.color+";p";
				player.money-=pp;
				player.updateMoneySocket();
				this.send_to_all("showFigureAt;p;"+pos+";"+player.color, null);
			}else{
				
				this.send_to("showFigureAt;p;"+Integer.valueOf(string[3])+";"+player.color, con);
				this.send_to("showFigureAt;"+battle.map[x][y].split(";")[1]+";"+Integer.valueOf(string[2])+";"+battle.map[x][y].split(";")[0], con);
			}
			}
		
		
		if(type.equals("R") && accept(1,player.color,x,y) && player.money-pR>=0 && player.door-tR>=0){
			if(battle.map[x][y].split(";")[0].equals("none") || battle.map[x][y].split(";").length==1 || (battle.map[x][y].split(";")[0].equals(player.color)==false && battle.map[x][y].split(";").length>1 && (battle.map[x][y].split(";")[1].equals("p") || battle.map[x][y].split(";")[1].equals("r") || battle.map[x][y].split(";")[1].equals("m")|| battle.map[x][y].split(";")[1].equals("b")))){
				battle.map[x][y] = player.color+";R";
				player.money-=pR;
				player.door-=tR;
				player.updateTreeSocket();
				player.updateMoneySocket();
				this.send_to_all("showFigureAt;R;"+pos+";"+player.color, null);
			}else{
				
				this.send_to("showFigureAt;R;"+Integer.valueOf(string[3])+";"+player.color, con);
				this.send_to("showFigureAt;"+battle.map[x][y].split(";")[1]+";"+Integer.valueOf(string[2])+";"+battle.map[x][y].split(";")[0], con);
			}
			}
		
		
		
		if(type.equals("K") && accept(1,player.color,x,y) && player.money-pK>=0 && player.door-tK>=0){
			if(battle.map[x][y].split(";")[0].equals("none") || battle.map[x][y].split(";").length==1 || (battle.map[x][y].split(";")[0].equals(player.color)==false && battle.map[x][y].split(";").length>1 && (battle.map[x][y].split(";")[1].equals("p") || battle.map[x][y].split(";")[1].equals("r") || battle.map[x][y].split(";")[1].equals("m")|| battle.map[x][y].split(";")[1].equals("b")|| battle.map[x][y].split(";")[1].equals("B")|| battle.map[x][y].split(";")[1].equals("t")))){
				battle.map[x][y] = player.color+";K";
				player.money-=pK;
				player.door-=tK;
				player.updateMoneySocket();
				player.updateTreeSocket();
				this.send_to_all("showFigureAt;K;"+pos+";"+player.color, null);
			}else{
				
				this.send_to("showFigureAt;t;"+Integer.valueOf(string[3])+";"+player.color, con);
				this.send_to("showFigureAt;"+battle.map[x][y].split(";")[1]+";"+Integer.valueOf(string[2])+";"+battle.map[x][y].split(";")[0], con);
			}
			}
		
		if(type.equals("b") && accept(1,player.color,x,y) && player.money-pb>=0 && player.door-tb>=0){
			if(battle.map[x][y].split(";")[0].equals("none") || battle.map[x][y].split(";").length==1 || (battle.map[x][y].split(";").length>1 && (battle.map[x][y].split(";")[1].equals("r") || battle.map[x][y].split(";")[1].equals("m")))){
					battle.map[x][y] = player.color+";b";
					player.money-=pb;
					player.door-=tb;
					player.updateMoneySocket();
					player.updateTreeSocket();
					
				this.send_to_all("showFigureAt;b;"+pos+";"+player.color, null);
			}else{
				
				this.send_to("showFigureAt;b;"+Integer.valueOf(string[3])+";"+player.color, con);
				this.send_to("showFigureAt;"+battle.map[x][y].split(";")[1]+";"+Integer.valueOf(string[2])+";"+battle.map[x][y].split(";")[0], con);
			}
			}
		
		//деревня
		if(type.equals("B") && accept(1,player.color,x,y) && player.money-(pb*3)>=0 && player.door-(tb*3)>=0 ){
			if(battle.map[x][y].split(";")[0].equals("none") || battle.map[x][y].split(";").length==1 || (battle.map[x][y].split(";").length>1 && (battle.map[x][y].split(";")[1].equals("r") || battle.map[x][y].split(";")[1].equals("m")))){
				battle.map[x][y] = player.color+";B";
				player.money-=pb*3;
				player.door-=tb*3;
				player.updateMoneySocket();
				player.updateTreeSocket();
				this.send_to_all("showFigureAt;B;"+pos+";"+player.color, null);
			}else{
				
				this.send_to("showFigureAt;b;"+Integer.valueOf(string[3])+";"+player.color, con);
				this.send_to("showFigureAt;"+battle.map[x][y].split(";")[1]+";"+Integer.valueOf(string[2])+";"+battle.map[x][y].split(";")[0], con);
			}
			}
		
		
		
		
		if(type.equals("t") && accept(1,player.color,x,y)){
			if(battle.map[x][y].split(";")[0].equals("none") || battle.map[x][y].split(";").length==1 || (battle.map[x][y].split(";").length>1 && (battle.map[x][y].split(";")[1].equals("r") || battle.map[x][y].split(";")[1].equals("m")))){
				battle.map[x][y] = player.color+";t";
				this.send_to_all("showFigureAt;t;"+pos+";"+player.color, null);
			}else{
				
				this.send_to("showFigureAt;b;"+Integer.valueOf(string[3])+";"+player.color, con);
				this.send_to("showFigureAt;"+battle.map[x][y].split(";")[1]+";"+Integer.valueOf(string[2])+";"+battle.map[x][y].split(";")[0], con);
			}
			}
		
		
		
		if(type.equals("s") && accept(2,player.color,x,y) && player.money-ps>=0 && player.door-ts>=0){
			
			//Башня с флагом
			if(battle.map[x][y].split(";")[0].equals("none") || battle.map[x][y].split(";").length==1 || (battle.map[x][y].split(";").length>1 && (battle.map[x][y].split(";")[1].equals("r") || battle.map[x][y].split(";")[1].equals("m")))   ){
				battle.map[x][y] = player.color+";s";
				this.send_to_all("showFigureAt;s;"+pos+";"+player.color, null);
				setColor(1,player,x,y);
				
				if(player.user.login.equals("Anton"))return;
				player.money-=ps;
				player.door-=ts;
				player.updateMoneySocket();
				player.updateTreeSocket();
			}else{
				
				this.send_to("showFigureAt;b;"+Integer.valueOf(string[3])+";"+player.color, con);
				this.send_to("showFigureAt;"+battle.map[x][y].split(";")[1]+";"+Integer.valueOf(string[2])+";"+battle.map[x][y].split(";")[0], con);
			}
			}
		
		
		
		
		if(type.equals("k") && accept(1,player.color,x,y)){
			if(!(battle.map[x][y].split(";")[0].equals(player.color)  && battle.map[x][y].split(";").length>=2)){
				battle.map[x][y] = player.color+";k";
				this.send_to_all("showFigureAt;k;"+pos+";"+player.color, null);
			}else{
				
				this.send_to("showFigureAt;p;"+Integer.valueOf(string[3])+";"+player.color, con);
				this.send_to("showFigureAt;"+battle.map[x][y].split(";")[1]+";"+Integer.valueOf(string[2])+";"+battle.map[x][y].split(";")[0], con);
			}
			}
		
		//солдат
		if(type.equals("P") && accept(1,player.color,x,y) && player.money-pP>=0){
			if(!(battle.map[x][y].split(";")[0].equals(player.color)  && battle.map[x][y].split(";").length>=2)){
				
				battle.map[x][y] = player.color+";P";
				this.send_to_all("showFigureAt;P;"+pos+";"+player.color, null);
				if(player.user.login.equals("Anton"))return;
				
				player.money-=pP;
				player.door-=tP;
				player.updateMoneySocket();
				player.updateTreeSocket();
			}else{
				
				this.send_to("showFigureAt;p;"+Integer.valueOf(string[3])+";"+player.color, con);
				this.send_to("showFigureAt;"+battle.map[x][y].split(";")[1]+";"+Integer.valueOf(string[2])+";"+battle.map[x][y].split(";")[0], con);
			}
			}
		
		
		if(type.equals("c") && battle.map[x][y].split(";")[0].equals(player.color) ){
			battle.map[x][y] = player.color+";c";
			this.send_to_all("showFigureAt;c;"+pos+";"+player.color, null);
			
		}
		if(type.equals("o") && player.money -po>=0 && player.door-to>=0 && battle.map[x][y].split(";")[0].equals(player.color)){
			System.out.println(battle.oilposition.get(pos)+" pos"+ OilBase.get(pos));
			if(OilBase.get(pos)==null && battle.oilposition.get(pos)==1){
			battle.map[x][y] = player.color+";o";
			player.money-=po;
			player.door-=to;
			player.updateMoneySocket();
			player.updateTreeSocket();
			this.send_to_all("showFigureAt;o;"+pos+";"+player.color, null);
			Oil oil = new Oil(player,pos,this);
			//OilPlayer.put(player, oil);
			OilBase.put(pos, oil);
			}
			
		}
		
		
	}
	if(str.startsWith("move;1") && true ==false){
		String st = str.replace("move;","");
		try {
			JSONObject json = new JSONObject (st);
			
			String[] from = json.getString("from").split(";");
			String[] to = json.getString("to").split(";");
			int id = json.getInt("id");
			IPeople people = player.piple.get(id);
			if(people!=null && people.getPosition().x == Integer.valueOf(from[0])  && people.getPosition().y == Integer.valueOf(from[1])){
				people.move(Integer.valueOf(to[0]), Integer.valueOf(to[1]));
			}
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}else if (str.startsWith("buy;")){
		String st = str.replace("buy;","");
		try {
			JSONObject json = new JSONObject (st);
		String type = json.getString("type");
		String podtype = json.getString("podtype");
		
		if(type.equals("people")){
			
			if(podtype.equals("people")){
				if(player.money-player.pricep>0){
					People people = new People(player,player.idsize++,0,5,0, new Position(0,4,4),player.level);
					player.piple.put(people.getId(), people);
					
				
				}
			}
			
			
			if(podtype.equals("voin")){}
			
			
		}else if(type.equals("building")){
			
			if(podtype.equals("home")){}
			if(podtype.equals("castle")){}
			
			
			
			
		}
		
		
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	
}
public Forest forest = new Forest(battle,this);
public long time = 0;

public void initme(User user){
	battle.initilizate(user);
}
public boolean join(User user){
	
	user.player = new Player(1000.5,0,user);
	user.player.bars = 5.5;
	user.player.door = 1000;
	user.player.updateOilSocket();
	user.player.color = colour.get(0);
	this.players.put(user.socket, user.player);
	this.teams.put(user.player, colour.get(0));
	clients.add(user.socket);
	user.player.updateMoneySocket();
	user.player.updateTreeSocket();
	colour.remove(0);
	this.initme(user);
	user.send("showFigure;"+battle.toString());
	for(int i = 0;i<OilPlayer.size();i++){
		Oil o = OilPlayer.get(i);
		if(o!=null){
			if(o.color.equals(user.player.color) && o.player==null){
				o.player = user.player;
			}
			
			
		}
	}
	return true;
	
}
	public Game(int id,String name,int start_money,long time) {
		this.id = id;
		if(name==null ||name.length()<=3){
			name="game";
		}
		this.name = name;
		battle.init();
		clients = new HashSet<WebSocket>();
	this.time = time;
		for(int i =0;i<color.length;i++){
		colour.add(color[i]);
		}
		t.start();
		
		
	}

	@Override
	public void run(){
		while(true){
			try {
				Thread.sleep(1000L);
				
				
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	public void startMoney(Player player ,double money){
		if(player!=null){
			player.setMoney(money);
			player.user.send("tables;money;update;"+player.money);
		}
	}
	@Override
	public void actionPerformed(ActionEvent arg0) {
		
		
		forest.update();
		
		
	}
	public void send_to_all(String string,WebSocket socket) {
		for(WebSocket sock:clients){
			if(socket!=null){
			if(socket!=sock)
			sock.send(string);
			}else{
				sock.send(string);
				
			}
		}
		
	}
	
	public void send_to(String string,WebSocket sock) {
			sock.send(string);
	}
	public void replace(User user,WebSocket sock) {
		
		this.clients.remove(sock);
		this.players.remove(sock);
		if(user.player!=null){
		this.teams.remove(user.player);
		colour.add(user.player.color);
		for(Oil oils:OilPlayer){
			if(oils.player==user.player){
				OilBase.remove(oils.pos);
				OilPlayer.remove(oils);
			}
		}
		}
	}

}
