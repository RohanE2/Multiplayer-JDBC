package com.jspiders.songmethod;


import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;
import java.util.Scanner;

import com.jspiders.songoperation.SongOperation;

public class SongMethod {
	
	private static Connection connection;
	private static Statement statement;
	private static int result;
	private static ResultSet resultset;
	private static FileReader filereader;
	private static Properties properties;
	private static String filepath ="C:\\Users\\rohan\\eclipse-workspace\\weje2\\multipalyer1\\resources\\db_info.properties";
	private static String query;
	private static Boolean loop = true;
	
	static SongOperation[] Songs;
	
	public static void menu() {
		
		while(loop == true) {
		System.out.println("(1) Play Song");
		System.out.println("(2) Add/Remove Song");
		System.out.println("(3) Edit");
		System.out.println("(4) Exit");
			
			loadSong();
			Scanner sc = new Scanner(System.in);
			int input = sc.nextInt();
			switch(input) {
			case 1: playSong();
					break;
			case 2: addRemoveSong();
					break;
			case 3: edit();
					break;
			case 4: loop = false;
					break;		
			default: System.out.println("Invaild Number " + input);
					menu();
					
			 }
			sc.close();
			
			}
	}
	
		public static void playSong() {
			
			System.out.println("(1) Choose Song");
			System.out.println("(2) Random Song");
			System.out.println("(3) All Song");
			System.out.println("(4) Go Back");
			
			Scanner sc = new Scanner(System.in);
			int input = sc.nextInt();
	
			switch(input) {
			case 1: chooseSong();
					break;
			case 2: randomSong();
					break;
			case 3: allSong();
					break;
			case 4: goBack();
					break;
			default: System.out.println("Invaild Number " + input);		
					playSong();
			}
		}
		
			public static void back() {
			
			Scanner sc = new Scanner(System.in);
			int input = sc.nextInt();
			switch(input) {
			case 1: playSong();
					break;
			default: System.out.println("Invaild Number " + input);
					System.out.println("press 1");
					 back();
			}
			sc.close();
		}
			
			public static void choosePlay()  {
				try {	
				System.out.println("Choose the song no to play.");
				Scanner sc = new Scanner(System.in);
				int id = sc.nextInt();
				 if(id<=Songs.length) {
				System.out.println(Songs[id-1]);
				 }
				 else {
					 System.out.println("You have enter the wrong no");
					 choosePlay();
				 }
				System.out.println("(1) Go back");
				back();
				} catch(Exception e) {
					e.printStackTrace();
				}
			}
					
		public static void chooseSong() {
			try {
				filereader = new FileReader(filepath);
				properties = new Properties();
				properties.load(filereader);
				
				Class.forName(properties.getProperty("driverpath"));
				
				connection = DriverManager.getConnection(properties.getProperty("dburl"),properties);
				  
				statement = connection.createStatement();
				query = "Select * from songs";
				resultset = statement.executeQuery(query);
				int no = 1;
				while(resultset.next()) {
					System.out.println(no++ +") "+
									   resultset.getString(2)+" By "+
									   resultset.getString(3));
//									   resultset.getString(4)+"||"+
//									   resultset.getString(5));
//									   resultset.getString(6));
				}
				choosePlay();
				
			} catch(ClassNotFoundException | SQLException | IOException e) {
				e.printStackTrace();
				
			} finally {
				try {
					if (connection != null) {
						connection.close();
					}
					if (statement != null) {
						statement.close();
					}
					if (resultset != null) {
						resultset.close();
					}
					if (filereader != null) {
						filereader.close();
					}
					
				}catch(SQLException | IOException e) {
					e.printStackTrace();
				}
			}
		}
		public static void randomSong() {
				int rand = (int)(Math.random()* (int)Songs.length);

				int id = rand;
				System.out.println(Songs[id]);
				
				System.out.println("(1) Go back");
				back();
		}
			
		public static void allSong() {
			for(SongOperation var:Songs) {
			System.out.println(var);
			}
			
			System.out.println("Songs Finish");
			System.out.println("(1) Go back");
			back();
		}
		public static void goBack() {
			menu();
		}
		public static void addRemoveSong() {
			System.out.println("(1) Add Song");
			System.out.println("(2) Remove Song");
			System.out.println("(3) Go Back");
			
			Scanner sc = new Scanner(System.in);
			int input = sc.nextInt();
	
			switch(input) {
			case 1: addSong();
					break;
			case 2: removeSong();
					break;
			case 3: goBack();
					break;
			default: System.out.println("Invaild Number " + input);		
					addRemoveSong();
			}
			sc.close();
			
		}
		public static void addSong() {
			try {
				filereader = new FileReader(filepath);
				properties = new Properties();
				properties.load(filereader);
				
				Class.forName(properties.getProperty("driverpath"));
				
				connection = DriverManager.getConnection(properties.getProperty("dburl"),properties);
				
				statement = connection.createStatement();
				
				Scanner sc = new Scanner(System.in);
				System.out.println("How many song to add");
				int input =sc.nextInt();
				
				 Songs = new SongOperation[input];
				 for(int i = 0;i<Songs.length;i++) {
					 Songs[i] = new SongOperation();
					 
				 }
				
				 int count = 0;
				 for(SongOperation var:Songs) {
					 count = var.getNo();
				 }
				 
				 
				for(SongOperation var:Songs) {
					int id = 1+count++;
					System.out.println("Enter the Details of Song "+id);
					 
					 sc.nextLine();
					System.out.println("Enter the Song name");
					var.setSongName(sc.nextLine());
					String name = var.getSongName();
					
					System.out.println("Enter the Song Artist name");
					var.setArtistName(sc.nextLine());
					String artist = var.getArtistName();
					
					System.out.println("Enter the Song Producer name");
					var.setProd(sc.nextLine());
					String prod = var.getProd();
					
					System.out.println("Enter the Song  Duration");
					var.setDuration(sc.nextDouble());
					Double duration = var.getDuration();
					
					sc.nextLine();
					System.out.println("Enter the Song Release Date");
					var.setReleaseDt(sc.nextLine());
					String date = var.getReleaseDt();
					
					query = "insert into songs values"
							+"("+id+","+"'"+name+"'"+","+"'"+artist+"'"+","+"'"+prod+"'"+","+duration+","+"'"+date+"'"+")";
					result = statement.executeUpdate(query);
				}
				
				loadSong();
				System.out.println("Song Added successfully");
				System.out.println("(1)Go back");
				back1();
				
			}catch(IOException | ClassNotFoundException | SQLException e) {
				e.printStackTrace();
			}finally {
				try {
					if (connection != null) {
						connection.close();
					}
					if (statement != null) {
						statement.close();
					}
					if(filereader != null) {
						filereader.close();
					}
				} catch(IOException | SQLException e) {
					e.printStackTrace();
				}
			}

		}
		
		public static void removeSong() {
			try {
				filereader = new FileReader(filepath);
				properties = new Properties();
				properties.load(filereader);
				
				Class.forName(properties.getProperty("driverpath"));
				
				connection = DriverManager.getConnection(properties.getProperty("dburl"),properties);
				
				statement = connection.createStatement();
				query = "Select * from songs";
				resultset = statement.executeQuery(query);
				
				while(resultset.next()) {
					System.out.println(resultset.getString(1)+") "+
									   resultset.getString(2)+" By "+
									   resultset.getString(3));
//									   resultset.getString(4)+"||"+
//									   resultset.getString(5));
//									   resultset.getString(6));
				}
//				
				
				System.out.println("Enter the song no to remove");
	
				Scanner sc = new Scanner(System.in); 
				int id = sc.nextInt();
					
				query = "delete from songs " +
						"where id in"+"("+id+")";
				result = statement.executeUpdate(query);
				
				System.out.println("Song remove Successfully");		
				
				System.out.println("(1) Go back");
				back1();
				
				} catch(ClassNotFoundException | SQLException | IOException e) {
					e.printStackTrace();
				} finally {
					try {
						if (connection != null) {
							connection.close();
						}
						if (statement != null) {
							statement.close();
						}
						if (resultset != null) {
							resultset.close();
						}
						if (filereader != null) {
							filereader.close();
						}
						
					}catch(SQLException | IOException e) {
						e.printStackTrace();
					}
				}
		}
		
			public static void back1() {
				
				Scanner sc = new Scanner(System.in);
				int input = sc.nextInt();
				switch(input) {
				case 1:addRemoveSong();
				break;
				default:System.out.println("Invaild Number " + input);
				addRemoveSong();
			}
		}
		
		public static void edit() {
			try {
				filereader = new FileReader(filepath);
				properties = new Properties();
				properties.load(filereader);
				
				Class.forName(properties.getProperty("driverpath"));
				
				connection = DriverManager.getConnection(properties.getProperty("dburl"),properties);
				
				statement = connection.createStatement();
				query = "Select * from songs";
						
				resultset = statement.executeQuery(query);
				
				while(resultset.next()) {
					System.out.println(resultset.getString(1)+")|"+
									   resultset.getString(2)+"|"+
									   resultset.getString(3)+"|"+
									   resultset.getString(4)+"|"+
									   resultset.getString(5)+"|"+
									   resultset.getString(6));
				}
				
				Scanner sc = new Scanner(System.in);
				int id = sc.nextInt();
				if(id<=Songs.length) {
				System.out.println("What You want to Edit");
				System.out.println("(1)ID");
				System.out.println("(2)Song Name");
				System.out.println("(3)Artist Name");
				System.out.println("(4)Producer name");
				System.out.println("(5)Duration");
				System.out.println("(6)Release Date");
				
				int input = sc.nextInt();
				
				switch(input) {
				case 1:System.out.println("Enter the id no to edit");
					   int setid = sc.nextInt();
					   query = "update songs " 
					         + "set id = "+"'"+setid+"'"
					         + "where id in"+"("+id+")";
					   result = statement.executeUpdate(query);
					   break;
				case 2:System.out.println("Enter the Song Name to edit");
					   sc.nextLine();
					   String songName = sc.nextLine();
					   query = "update songs " 
						     + "set `song name` = "+"'"+songName+"'"
						     + "where id in"+"("+id+")";
					   result = statement.executeUpdate(query);
					   break;
				case 3:System.out.println("Enter the Artist Name to edit");
					   sc.nextLine();
				   	   String artistName = sc.nextLine();
				       query = "update songs " 
				    		 + "set `artist name`= "+"'"+artistName+"'"
				    		 + "where id in"+"("+id+")";
				       result = statement.executeUpdate(query);
				       break;
				case 4:System.out.println("Enter the Producer Name to edit");
					   sc.nextLine();
					   String producerName = sc.nextLine();
					   query = "update songs " 
							 + "set `poducer name`= "+"'"+producerName+"'"
			    		   	 + "where id in"+"("+id+")";
					   result = statement.executeUpdate(query);
					   break;
				case 5:System.out.println("Enter the Duration time to edit");
					   Double duration = sc.nextDouble();
					   query = "update songs " 
							 + "set duration = "+"'"+duration+"'"
		    		     	 + "where id in"+"("+id+")";
					   result = statement.executeUpdate(query);
					   break;
				case 6:System.out.println("Enter the Release Date to edit");
					   sc.nextLine();
					   String releaseDate = sc.nextLine();
				       query = "update songs " 
					    	 + "set `Release Date` = "+"'"+releaseDate+"'"
	    		         	 + "where id in"+"("+id+")";
				       result = statement.executeUpdate(query);
				       break;
				
				default:System.out.println("Invaild Number " + input);	
						edit();
				}
				System.out.println("Song edited Successfully");
				}
				else {
					System.out.println("You enter the wrong Song no");
				}	
				System.out.println("(1) Go back");
				int back1 = sc.nextInt();
				if(back1==1) {
				goBack();
				}
				sc.close();
				} catch(ClassNotFoundException | SQLException | IOException e) {
					e.printStackTrace();
					
				} finally {
					try {
						if (connection != null) {
							connection.close();
						}
						if (statement != null) {
							statement.close();
						}
						if (resultset != null) {
							resultset.close();
						}
						if (filereader != null) {
							filereader.close();
						}
						
					}catch(SQLException | IOException e) {
						e.printStackTrace();
					}
				}
		}
		
		public static void exit() {
			loop = false;
		}
		
		public static void loadSong() {
			
			try {
				filereader = new FileReader(filepath);
				properties = new Properties();
				properties.load(filereader);
				
				Class.forName(properties.getProperty("driverpath"));
				
				connection = DriverManager.getConnection(properties.getProperty("dburl"),properties);
				
				statement = connection.createStatement();
				

				statement = connection.createStatement();
				query = "Select count(id) from songs ";
						
				resultset = statement.executeQuery(query);
				int count =0;
				while(resultset.next()) {
					 count +=resultset.getInt(1);
					}
				
				 Songs = new SongOperation[count];
				 for(int i = 0;i<Songs.length;i++) {
					 Songs[i] = new SongOperation();
					 
				 }
				 
				 statement = connection.createStatement();
					query = "Select * from songs";
							
					resultset = statement.executeQuery(query);

					for(SongOperation obj: Songs) {
						
						while(resultset.next()) {
							
						 obj.setNo(resultset.getInt(1));
						 obj.setSongName(resultset.getString(2)) ;
						 obj.setArtistName(resultset.getString(3));
						 obj.setProd(resultset.getString(4));
						 obj.setDuration(resultset.getDouble(5));
						 obj.setReleaseDt(resultset.getString(6));
						 break;
						 
						}
					}
			
			}catch(IOException | ClassNotFoundException | SQLException e) {
				e.printStackTrace();
			}finally {
				try {
					if (connection != null) {
						connection.close();
					}
					if (statement != null) {
						statement.close();
					}
					if(filereader != null) {
						filereader.close();
					}
				} catch(IOException | SQLException e) {
					e.printStackTrace();
				}
			}
	
		}
	}
