package uk.AntonSibgatulin.ServerSocket;

import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

import org.java_websocket.WebSocket;
import org.java_websocket.handshake.ClientHandshake;
import org.java_websocket.server.WebSocketServer;

import uk.AntonSibgatulin.Battle.Player;
import uk.AntonSibgatulin.Battle.battles.Game;
import uk.AntonSibgatulin.Battle.battles.Oil;
import uk.AntonSibgatulin.Database.DatabaseConnect;
import uk.AntonSibgatulin.User.User;

public class Server extends WebSocketServer {
	
	public DatabaseConnect base = new DatabaseConnect();
	
	public static final int TCP_PORT = 4999;
	
	public ArrayList<Game> games = new ArrayList<>();
	public HashMap<Integer,Game> gameinteger = new HashMap<>();
	public HashMap<User,Game> gamesh = new HashMap<>();
	public int idgame = 0;
	
	public HashMap<WebSocket,User> users = new HashMap<>();
	public Set<WebSocket> list = null;


	public Server() throws UnknownHostException {
		super(new InetSocketAddress(InetAddress.getByName("192.168.0.107"),TCP_PORT));
		list = new HashSet<>();
	}

	@Override
	public void onClose(WebSocket arg0, int arg1, String arg2, boolean arg3) {
		User user = users.get(arg0);
		if(user!=null){
			Game game = gamesh.get(user);
			if(game!=null){
				game.replace(user,arg0);
			}
		users.remove(user);
		}
		list.remove(arg0);
		
	}

	@Override
	public void onError(WebSocket arg0, Exception arg1) {
		// TODO Auto-generated method stub
		
	}
	public void sendTables(User user ){
		for(int i = 0;i<games.size();i++){
			Game game = games.get(i);
			user.send("tables;all;"+game.id+";"+1+";"+game.time+";"+game.battle.width+"x"+game.battle.height);
		}
	}
	public void sendTables(User user,Game game ){
		
			user.send("tables;all;"+game.id+";"+1+";"+game.time+";"+game.battle.width+"x"+game.battle.height);
		
	}
	
	@Override
	public void onMessage(WebSocket sock, String str) {
		new Thread(new Runnable(){

			@Override
			public void run() {
				// TODO Auto-generated method stub
				
			
		User user =null;
		System.out.println(str);
		String [] string = str.split(";");
		if(string[0].equals("end")){
			System.exit(0);
		}
		if(string[0].equals("auth")){
			
			User auth = base.getUser(string[1], string[2]);
			if(auth!=null){
				sock.send("auth;true");
				auth.socket = sock;
				
				users.put(sock, auth);
				sendTables(auth);
				
			}else{
				sock.send("auth;false");
			}
		}else if((user = users.get(sock))!=null){
			System.out.println("was auth");
			if(string[0].equals("tables")){
				if(string[1].equals("create")){
					Game game = new Game(idgame++,string[2],Integer.valueOf(string[3]),1000*60*5);
					games.add(game);
					
					gameinteger.put(game.id, game);
					for(WebSocket sock1:list){
						sendTables(users.get(sock1),game);
					}
				}else if (string[1].equals("join")){
					Integer id = Integer.valueOf(string[2]);
					Game game =  gameinteger.get(id);
					boolean bool = game.join(user);

					if(bool){
					
						gamesh.put(user, game);
					user.send("game;join;"+id+";"+game.battle.width+"x"+game.battle.height+";"+user.player.color);
					}
					}else if(string[1].equals("movefigure")){
						int last = Integer.valueOf(string[2]);
						int to = Integer.valueOf(string[3]);
						Game game = gamesh.get(user);
						if(game!=null){
							System.out.println("game");
						if(last==100000){
							//
							game.executeCommand(user.socket, "bought;"+string[4]+";"+string[3]+";"+last);
						}else{
							if(last<game.battle.width*game.battle.height){
								System.out.println("last");
								game.executeCommand(user.socket, "move;"+last+";"+to+";"+string[4]);
							}
						}
						
						
						
						}
					}else if (string[1].equals("sell")){
						Game game = gamesh.get(user);
						double oil = Double.valueOf(string[2]);
						
						if(game!=null && oil>0){
							Player player = game.players.get(sock);
							if(player.oil-oil>=0){
								player.oil-=oil;
								player.money+=oil*10;
								if(player.user.login.equals("Anton"))
									player.money+=oil*20;
								player.updateMoneySocket();
								player.updateOilSocket();
							}
						}
					}else if(string[1].equals("buy")){
						Game game = gamesh.get(user);
						double mon = Double.valueOf(string[2]);
						
						if(game!=null && mon>0){
							Player player = game.players.get(sock);
							if(player.money-mon*3>=0){
								player.money-=mon*3;
								player.door+=mon;
								player.updateTreeSocket();
								player.updateMoneySocket();
							}
						}
					}
			}
			
		
		
		
		}
			}}).start();
		
	}
public void changeOilBase(double b){
	for(Game game:games){
		for(Oil oil:game.OilPlayer){
			oil.base = b;
		}
	}
}
	@Override
	public void onOpen(WebSocket arg0, ClientHandshake arg1) {
		list.add(arg0);
		
		
	}

}
