package db;

import java.sql.*;

public class Database {

	private static Database database = null;
	public Connection conn;

	private Database() {
		String connString = "jdbc:mysql://localhost:3306/servisracunara?user=root&password=";
		try 
		{
			conn = DriverManager.getConnection(connString);
		} catch (SQLException e)
		{
			System.out.println("Neka greska sa bazom!");
		}
	}

	public static Database getKonekcijaNaBazu() 
	{
		if (database == null)
			database = new Database();

		return database;
	}
	
}
