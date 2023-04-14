package uk.AntonSibgatulin.User;

import org.java_websocket.WebSocket;

import uk.AntonSibgatulin.Battle.Player;

public class User {
public String password = null;
public String login = null;
public int money = -1;
public int level = 0;
public int typeuser = -1;
public WebSocket socket = null;
public Player player = null;
public int id = 0;
public int ban = 0;


public void send(String words){
	socket.send(words);
}
	public User(String login,String password,int level,int typeuser) {
		
		this.login = login;
		this.password = password;
		this.level = level;
		this.money = money;
		this.typeuser = typeuser;
		
	}

}
