package uk.AntonSibgatulin.Main;

import java.net.UnknownHostException;
import java.util.Scanner;

import uk.AntonSibgatulin.ServerSocket.Server;

public class Main {

	public Main() {
		// TODO Auto-generated constructor stub
	}

	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		Server server = null;
		
		try {
			 server = new Server();
			server.start();
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		while(true){
			String str = scan.nextLine();
			if(str!=null){
				String[]string = str.split(";");
				if(string[0].equals("timeoil")){
					double d = Double.valueOf(string[1]);
					server.changeOilBase(d);
				}
			}
		}
	}

}
