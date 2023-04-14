package uk.AntonSibgatulin.Battle.battles;

import java.util.HashMap;
import java.util.Random;

import org.json.JSONObject;

import uk.AntonSibgatulin.User.User;

public class Battle {
	public int width = 0;
	public int height = 0;
	public HashMap<Integer,Integer> oilposition = new HashMap<Integer,Integer>();
	
	public void initilizate(User user){
		int x = new Random().nextInt(this.width);
		int y = new Random().nextInt(this.height);
		
		
		if(map[x][y].equals("none;")  && x>3 && y>3 && width-x>3 && height-y>3){
			map[x][y]=user.player.color+";c";
			
			
			
			
			
			/////////////////////////////////////////////////////////////////////
			if(map[x-1][y-1].equals("none;") || map[x-1][y-1].equals("none;r")){
				map[x-1][y-1]=user.player.color+";";
				game.send_to_all("showFigureAt;1;"+((x-1)*height+(y-1))+";"+user.player.color,user.socket);
				
			}
			if(map[x][y-1].equals("none;") || map[x][y-1].equals("none;r")){
				map[x][y-1]=user.player.color+";";
				game.send_to_all("showFigureAt;1;"+((x)*height+(y-1))+";"+user.player.color,user.socket);
				
			}
if(map[x+1][y-1].equals("none;") || map[x+1][y].equals("none;r")){
	map[x+1][y-1]=user.player.color+";";	
	game.send_to_all("showFigureAt;1;"+((x+1)*height+(y-1))+";"+user.player.color,user.socket);
	
			}



if(map[x-1][y].equals("none;") || map[x-1][y].equals("none;r")){
	map[x-1][y]=user.player.color+";";
	game.send_to_all("showFigureAt;1;"+((x-1)*height+(y))+";"+user.player.color,user.socket);
	
}
if(map[x+1][y].equals("none;") || map[x+1][y].equals("none;r")){
	map[x+1][y]=user.player.color+";";
	game.send_to_all("showFigureAt;1;"+((x+1)*height+(y))+";"+user.player.color,user.socket);
	
}



if(map[x-1][y+1].equals("none;") || map[x-1][y+1].equals("none;r")){
	map[x-1][y+1]=user.player.color+";";
	game.send_to_all("showFigureAt;1;"+((x-1)*height+(y+1))+";"+user.player.color,user.socket);
	
}

if(map[x][y+1].equals("none;") || map[x][y+1].equals("none;r")){
	map[x][y+1]=user.player.color+";";
	game.send_to_all("showFigureAt;1;"+((x)*height+(y+1))+";"+user.player.color,user.socket);
	
}

if(map[x+1][y+1].equals("none;") || map[x+1][y+1].equals("none;r")){
	map[x+1][y+1]=user.player.color+";";
	game.send_to_all("showFigureAt;1;"+((x+1)*height+(y+1))+";"+user.player.color,user.socket);
	
}
////////////////////////////////////////////////////////////






			game.send_to_all("showFigureAt;c;"+(x*height+y)+";"+user.player.color,user.socket);
		}else{
			initilizate(user);
			
		}
		
		
	}
	
	public Game game = null;
	
public String [][] map = null;
	public Battle(int  width ,int height,Game game) {
		this.game = game;
		// TODO Auto-generated constructor stub
	this.width = width;
	this.height = height;
	
	}
	@Override
	public String toString(){
		String str = "";
		for(int i =0;i<width;i++){
			for(int j =0;j<height;j++){
				str+=map[i][j]+"&";
			}
		}
		return str;
		
	}
	public static void main(String... args){
		int w = 30;
		int h = 30;
		String [][] map = new String[w][h];
		for(int i =0;i<h;i++){
			for(int j = 0;j<w;j++){
				map[j][i] = "1";
				
			}
		}
		
		for(int i = 0;i<w*h;i++){
			int x = new Random().nextInt(w);
			int y =  new Random().nextInt(h);
			int in =  new Random().nextInt(10);
			if(in == 5)
		map[x][y]="r";
		}
		
		for(int i =0;i<h;i++){
			for(int j = 0;j<w;j++){
				System.out.print(map[j][i]);
				
			}
			System.out.println();
		}
		
		String str = "";
		for(int i =0;i<w;i++){
			for(int j =0;j<h;j++){
				str+=map[i][j]+"";
			}
		}
		System.out.println(str);
	}

	
	
	
	public JSONObject Map = null;
	public void init(){
		map = new String[width][height];
		
		for(int i = 0;i<this.height;i++){
			
			for(int j = 0;j<this.width;j++){
				
				map[j][i]  ="none;";
				System.out.println(map[j][i]);
			}
			
		}
		for(int i = 0;i<width*height;i++){
			int x = new Random().nextInt(width);
			int y =  new Random().nextInt(height);
			int in =  new Random().nextInt(10);
			int in1 =  new Random().nextInt(30);
			if(in == 5 || i == 3){
				map[x][y]="none;r";
					}
			
			
			if(in1==25 || in1 == 24){
				oilposition.put(x*height+y,1);
				System.out.println(x*height+y+" - position");
				map[x][y]="none;m";
					}
		}
		for(int i =0;i<height;i++){
			for(int j = 0;j<width;j++){
				System.out.print(map[j][i].replace("none;","1").replace("1r", "r")+" ");
				
			}
			System.out.println();
		}
		/*
		Map = new JSONObject();
		
		for(int i=0;i<width;i++){
			for(int j = 0;j<height;j++){
				JSONArray jsonarray = new JSONArray();
				
				try {
					jsonarray.put("none");
					
					Map.put(i+";"+j, jsonarray);
				} catch (JSONException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			}
		}
	
	
	*/
	}
	

}
