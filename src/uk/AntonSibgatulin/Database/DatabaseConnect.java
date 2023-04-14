package uk.AntonSibgatulin.Database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.java_websocket.WebSocket;
import org.json.JSONException;
import org.json.JSONObject;

import uk.AntonSibgatulin.User.TypeUser;
import uk.AntonSibgatulin.User.User;

public class DatabaseConnect {

	// public Object userList;
	/**
	 * @author AntonSibgatulin
	 */
	ResultSet resultSet;
	int res;
	Connection connection = null;
	Statement statement = null;
public User getBot(String login){

	User user=null ;
	Statement statement = null;
	try {
		statement = connection.createStatement();
	} catch (SQLException throwables) {
		throwables.printStackTrace();
	}
	String log_in = "SELECT * FROM `users` WHERE `login`='" + login + "' AND `nich` = 3";
	try {
		resultSet = statement.executeQuery(log_in);

		// System.out.println("Retrieving data from database...");
		// System.out.println("\nusers:");
		while (resultSet.next()) {
			int id = resultSet.getInt("id");
			int win = resultSet.getInt("win");
			String gameId = resultSet.getString("games");
			int i = resultSet.getInt("nich");
			int over = resultSet.getInt("over");
			String name = resultSet.getString("login");
			String pass = resultSet.getString("password");
			int mon = resultSet.getInt("money");
			//int ban = resultSet.getInt("ban");
			

				user = new User(name,pass,1,i);
				user.money = mon;

			
			/// int salary = resultSet.getInt("salary");
			// resultSet = statement.executeQuery(log_in);

			// System.out.println("id: " + id);
			// System.out.println("login: " + name);
			// System.out.println("password: " + pass);
			// System.out.println("Salary: $" + salary);
		}

		//return user;
	} catch (SQLException e) {

		// System.out.println(obj.toString());

		//return user;
	}
	return user;
}










	public DatabaseConnect() {
		System.out.println("Registering JDBC driver...");
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {

		}
		try {
			System.out.println("Creating database connection...");
			connection = DriverManager.getConnection(DATABASE_URL, USER, PASSWORD);

			System.out.println("Executing statement...");
			statement = connection.createStatement();
		} catch (SQLException e) {
		}
	}

	public void updateData() throws SQLException {
		if (connection.isClosed() == true ) {
			System.out.println("I am sleep");
			System.out.println("Registering JDBC driver...");
			try {
				Class.forName("com.mysql.jdbc.Driver");
			} catch (ClassNotFoundException e) {

			}
			try {
				System.out.println("Creating database connection...");
				connection = DriverManager.getConnection(DATABASE_URL, USER, PASSWORD);

				System.out.println("Executing statement...");
				statement = connection.createStatement();
			} catch (SQLException e) {
			}
		}else System.out.println("I don't sleep");
	}

	/**
	 * JDBC Driver and database url
	 */
	static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
	static final String DATABASE_URL = "jdbc:mysql://localhost/yoy";

	/**
	 * User and Password
	 */
	static final String USER = "root";
	static final String PASSWORD = "Dert869$$";

	public boolean banIp(WebSocket sock, String login) {
		String cmd = "INSERT INTO `blackip` (`id`, `ip`) VALUES (NULL, '"
				+ sock.getRemoteSocketAddress().getAddress().getHostAddress() + "');";

		try {
			res = statement.executeUpdate(cmd);
			cmd = "UPDATE `users` SET `ban` = '2' WHERE `users`.`login` = '" + login + "';";
			res = statement.executeUpdate(cmd);

			return true;
		} catch (SQLException throwables) {
			return false;
		}

	}

	public boolean addMoneyFundcuzGetUser(User us, int money) {
		Statement statement = null;
		try {
			statement = connection.createStatement();
		} catch (SQLException throwables) {
			throwables.printStackTrace();
		}
		String login = us.login, password = us.password;
		// System.out.println("main.Server: "+login+" ... "+password);
		JSONObject obj = getUserInfo(login, password);
		if (obj.has("log_in")) {
			// System.out.println("main.Server: has");
			try {

				if (obj.getString("log_in").equals("allok")) {
					int moneyUser = obj.getInt("money");
					// System.out.println("main.Server: "+moneyUser);
					if (moneyUser - money >= 0) {
						int mon = moneyUser + money;
						String cmd = "UPDATE `users` SET `money` = '" + mon + "' WHERE `users`.`login` = '" + login
								+ "';";
						// System.out.println("main.Server: "+cmd);
						try {
							res = statement.executeUpdate(cmd);
							statement.close();
							return true;
						} catch (SQLException throwables) {
							try {
								statement.close();
							} catch (SQLException e) {
								e.printStackTrace();
							}
							System.out.println("main.Server: " + throwables);
							return false;

						}

					} else {
						return false;

					}
				} else {
					return true;
				}
			} catch (JSONException e) {
				return false;
			}
		} else {
			return false;
		}
	}

	public boolean getwin(User us) {
		String login = us.login, password = us.password;
		// System.out.println("main.Server: "+login+" ... "+password);
		JSONObject obj = getUserInfo(login, password);
		if (obj.has("log_in")) {
			// System.out.println("main.Server: has");
			try {

				if (obj.getString("log_in").equals("allok")) {
					// int moneyUser = obj.getInt("money");
					// System.out.println("main.Server: "+moneyUser);
					if (0 == 0) {
						// int mon = moneyUser+money;
						String cmd = "UPDATE `users` SET `win` = `win`+" + 1 + " WHERE `users`.`login` = '" + login
								+ "';";
						Statement statement=null;
						// System.out.println("main.Server: "+cmd);
						try {
							statement = connection.createStatement();
							res = statement.executeUpdate(cmd);
							return true;
						} catch (SQLException throwables) {

							System.out.println("main.Server: " + throwables);
							return false;

						}

					} else {
						return false;

					}
				} else {
					return true;
				}
			} catch (JSONException e) {
				return false;
			}
		} else {
			return false;
		}
	}

	public boolean drawU(User us) {
		String login = us.login, password = us.password;
		// System.out.println("main.Server: "+login+" ... "+password);
		JSONObject obj = getUserInfo(login, password);
		if (obj.has("log_in")) {
			// System.out.println("main.Server: has");
			try {

				if (obj.getString("log_in").equals("allok")) {
					// int moneyUser = obj.getInt("money");
					// System.out.println("main.Server: "+moneyUser);
					if (0 == 0) {
						// int mon = moneyUser+money;
						String cmd = "UPDATE `users` SET `statics` =`statics`+ " + 1 + " WHERE `users`.`login` = '"
								+ login + "';";
					Statement statement=null;
						// System.out.println("main.Server: "+cmd);
						try {
							statement = connection.createStatement();
							res = statement.executeUpdate(cmd);
							return true;
						} catch (SQLException throwables) {

							System.out.println("main.Server: " + throwables);
							return false;

						}

					} else {
						return false;

					}
				} else {
					return true;
				}
			} catch (JSONException e) {
				return false;
			}
		} else {
			return false;
		}
	}

	public boolean getloser(User us) {
		String login = us.login;
			if (0 == 0) {
				Statement statement = null;
						// int mon = moneyUser+money;
						String cmd = "UPDATE `users` SET `over` = `over`+ " + 1 + " WHERE `users`.`login` = '" + login
								+ "';";
				try {
					statement = connection.createStatement();
							res = statement.executeUpdate(cmd);
					statement.close();
							return true;
						} catch (SQLException throwables) {

							System.out.println("main.Server: " + throwables);
							return false;

						}

					} else {
						return false;

					}
				}



	public boolean boardBattle(User us, int id) {

		String login = us.login, password = us.password;
		// System.out.println("main.Server: "+login+" ... "+password);
		// int moneyUser = obj.getInt("money");
		 System.out.println("main.Server: "+login);
		Statement statement = null;
		if (id > 0) {
			// int mon = moneyUser+money;
			String cmd = "UPDATE `users` SET `games` = '" + id + "' WHERE `users`.`login` = '" + login
					+ "';";
			// System.out.println("main.Server: "+cmd);
			try {
				statement = connection.createStatement();
				res = statement.executeUpdate(cmd);
				statement.close();
				return true;
			} catch (SQLException throwables) {

				System.out.println("main.Server: " + throwables);
				return false;

			}

		} else {
			return false;

		}

	}

	
	public User getUser(String login, String pass) {

		JSONObject obj = getUserInfo(login, pass);
		System.out.println(obj.toString());
		if (obj.has("log_in")) {
			try {

				// System.out.println(obj.toString()+"");
				if (obj.getString("log_in").equals("allok")) {
					User user = new User(obj.getString("login"), obj.getString("pass"), obj.getInt("level"),
							obj.getInt("type"));
					user.id = obj.getInt("id");
					//user.money = obj.getInt("money");
					// System.out.println(obj.getInt("ban"));
					user.ban = obj.getInt("ban");
					

					return user;
				} else {
					return null;
				}
			} catch (JSONException e) {

				e.printStackTrace();
				return null;
			}
		} else {
			return null;
		}
	}

	public boolean registerUser(String login, String pass) {
		String log_in = "SELECT * FROM `users` WHERE `login` = '" + login + "';";
	Statement statement = null;
		try {
			statement = connection.createStatement();
			resultSet = statement.executeQuery(log_in);
			if (resultSet.absolute(1)) {

				return false;
			} else {
				log_in = "INSERT INTO `users` (`id`, `login`, `password`, `win`, `over`, `games`, `statics`, `boardid`, `money`, `nich`, `ban`,`chatBan`) VALUES (NULL, '"
						+ login + "', '" + pass + "', '0', '0', '0', '0', '0', '500', '0', '0', '0')";
				res = statement.executeUpdate(log_in);
				return true;
			}
		} catch (SQLException throwables) {
			throwables.printStackTrace();
			// System.out.println(throwables);
			return false;
		}

	}

	public boolean addBoardGame(String his) {
		String log_in = "INSERT INTO `boardsgame` (`id`, `game`) VALUES (NULL, '" + his + "');";
Statement statement = null;
		try {
statement =connection.createStatement();
			res = statement.executeUpdate(log_in);
			statement.close();
			return true;

		} catch (SQLException throwables) {
			throwables.printStackTrace();

			return false;
		}

	}

	public JSONObject getUserInfo(String login, String password) {
		System.out.println();
		JSONObject obj = new JSONObject();
		Statement statement = null;
		try {
			statement = connection.createStatement();
		} catch (SQLException throwables) {
			throwables.printStackTrace();
		}
		String log_in = "SELECT * FROM `users` WHERE `login`='" + login + "' AND `password` = '" + password + "'";
		try {
			ResultSet resultSet = statement.executeQuery(log_in);

			// System.out.println("Retrieving data from database...");
			// System.out.println("\nusers:");
			try {
				while (resultSet.next()) {
					int id = resultSet.getInt("id");
					String gameId = resultSet.getString("games");

					String name = resultSet.getString("login");
					String pass = resultSet.getString("password");
					int level = resultSet.getInt("level");
					int ban = resultSet.getInt("ban");
					int type = resultSet.getInt("type");
					/// int salary = resultSet.getInt("salary");
					// resultSet = statement.executeQuery(log_in);
					if (name.equals(login) && pass.equals(password) && id > 0) {
						obj.put("login", name);
						obj.put("id", id);
						obj.put("level", level);
						obj.put("ban", ban);
						obj.put("log_in", "allok");
						obj.put("gameId", gameId);
						obj.put("pass", password);
						obj.put("type", type);
					} else {
						obj.put("log_in", "error");

					}
					// System.out.println("id: " + id);
					// System.out.println("login: " + name);
					// System.out.println("password: " + pass);
					// System.out.println("Salary: $" + salary);
				}
			} catch (JSONException e) {

			}
			statement.close();
			return obj;
		} catch (SQLException e) {

			JSONObject object = new JSONObject();
			try {
				object.put("log_in", "error");
			} catch (JSONException jsonException) {
				jsonException.printStackTrace();
			}
			// System.out.println(obj.toString());
			try {
				statement.close();
			} catch (SQLException throwables) {
				throwables.printStackTrace();
			}
			return object;
		}
	}
public void exit(){
	try {
		resultSet.close();
	} catch (SQLException throwables) {
		throwables.printStackTrace();
	}
	try {
		statement.close();
	} catch (SQLException throwables) {
		throwables.printStackTrace();
	}
	try {
		connection.isClosed();

		connection.close();
	} catch (SQLException throwables) {
		throwables.printStackTrace();
	}
}
	
	/*
	 * public static void main(String[] args) {
	 * 
	 * DatabasConnect c = new DatabasConnect(); c.getUser("Anton","Dert869$$");
	 * c.exit();
	 * 
	 * 
	 * }
	 */
	public String banChat(String login,int i){
		String cmd = "SELECT `password` FROM `users` WHERE `login`='"+login+"'";
//	boolean  ret = false;

		String last = "";
		if(login!=null)
		{
			long time = System.currentTimeMillis()   +(i*60000);
			String cmd1="UPDATE `users` SET `chatBan` = '"+time+"' WHERE `login` = '"+login+"';";
			Statement statement = null;

			try {
				statement = connection.createStatement();
				ResultSet resultSet = statement.executeQuery(cmd);
				while(resultSet.next()) {
				//System.out.println(resultSet.toString());
				last = resultSet.getString("password");
				if (last != null)
					statement.executeUpdate(cmd1);
				break;
				 }
			} catch (SQLException throwables) {
				throwables.printStackTrace();
			}
		}



		return last;
	}


	public String UnBanChat(String login){
		String cmd = "SELECT * FROM `users` WHERE `login`='"+login+"'";
		String last = "";
		if(login!=null)
		{
		String cmd1="UPDATE `users` SET `chatBan` = '"+0+"' WHERE `login` = '"+login+"';";
			Statement statement = null;

			try {
				statement = connection.createStatement();

				 resultSet = statement.executeQuery(cmd);

				while(resultSet.next()){
				last = resultSet.getString("password");
				System.out.println(last);
				if (last != null)
					statement.executeUpdate(cmd1);
				break;
			}

			} catch (SQLException throwables) {
				throwables.printStackTrace();
			}
		}



		return last;
	}
}