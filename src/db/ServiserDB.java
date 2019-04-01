package db;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import servis.Serviser;

public class ServiserDB 
{
	Database db = Database.getKonekcijaNaBazu();
	
	public void addServiser(Serviser serviser)
	{
		try 
		{
			String query = "INSERT INTO serviser (name, password, salary) VALUES (?, SHA1(?), ?)";
			PreparedStatement prepS = db.conn.prepareStatement(query);
			prepS.setString(1, serviser.getName());
			prepS.setString(2, serviser.getPassword());
			prepS.setDouble(3, serviser.getSalary());
			prepS.executeUpdate();
			prepS.close();
		} 
		catch (SQLException e) 
		{
			e.printStackTrace();
		}
	}
	
	public void updateServiser(Serviser serviser)
	{
		String query = "";
		if (serviser.getPassword().length() < 30)
			query = "UPDATE serviser SET name = ?, password = SHA1(?), salary = ? WHERE idServiser = ?";
		else
			query = "UPDATE serviser SET name = ?, password = ?, salary = ? WHERE idServiser = ?";
		try 
		{
			PreparedStatement prepS = db.conn.prepareStatement(query);
			prepS.setString(1, serviser.getName());
			prepS.setString(2, serviser.getPassword());
			prepS.setDouble(3, serviser.getSalary());
			prepS.setInt(4, serviser.getIdServiser());
			prepS.executeUpdate();
			prepS.close();
		} 
		catch (SQLException e) 
		{
			e.printStackTrace();
		}
	}
	
	public void deleteServiser(Serviser serviser)
	{
		try 
		{
			String query = "DELETE FROM serviser WHERE idServiser = ?";
			PreparedStatement prepS = db.conn.prepareStatement(query);
			prepS.setInt(1, serviser.getIdServiser());
			prepS.executeUpdate();
			prepS.close();
		} 
		catch (SQLException e) 
		{
			e.printStackTrace();
		}
	}
	
	public Serviser readServiser(int idServiser)
	{
		try
		{
			String query = "SELECT * FROM serviser WHERE idServiser = ?";
			
			PreparedStatement prepS = db.conn.prepareStatement(query);
			prepS.setInt(1, idServiser);
			
			ResultSet res = prepS.executeQuery();
			
			if (res.next()) 
			{
				//int id1 = res.getInt("id");//ovo je suvisno jer vec imamo potreban id
				String name = res.getString("name").trim();
				String password = res.getString("password").trim();
				double salary = res.getDouble("salary");
				Serviser serviser = new Serviser(idServiser, name, password, salary);
				return serviser;
			}
			return null;
		}
		catch (SQLException e) 
		{
			e.printStackTrace();
			return null;
		}
	}
	
	public ArrayList<Serviser> readServisers()
	{
		ArrayList<Serviser> listaServisera = new ArrayList<>();
		try
		{
			String query = "SELECT * FROM serviser";
			
			PreparedStatement prepS = db.conn.prepareStatement(query);
			
			ResultSet res = prepS.executeQuery();
			
			while (res.next()) 
			{
				int id = res.getInt("idServiser");
				String name = res.getString("name").trim();
				String password = res.getString("password").trim();
				double salary = res.getDouble("salary");
				Serviser serviser = new Serviser(id, name, password, salary);
				listaServisera.add(serviser);
			}
			return listaServisera;
		}
		catch (SQLException e) 
		{
			e.printStackTrace();
			return listaServisera;
		}
	}
	
}
