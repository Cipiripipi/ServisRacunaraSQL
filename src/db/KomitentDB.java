package db;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import servis.Komitent;

public class KomitentDB 
{
	Database db = Database.getKonekcijaNaBazu();
	
	public void addKomitent(Komitent komitent)
	{
		try 
		{
			String query = "INSERT INTO komitent (name, telephoneNumber) VALUES (?, ?)";
			PreparedStatement prepS = db.conn.prepareStatement(query);
			prepS.setString(1, komitent.getName());
			prepS.setString(2, komitent.getTelephoneNumber());
			prepS.executeUpdate();
			prepS.close();
		} 
		catch (SQLException e) 
		{
			e.printStackTrace();
		}
	}
	
	public void updateKomitent(Komitent komitent)
	{
		try 
		{
			String query = "UPDATE komitent SET name = ?, telephoneNumber = ? WHERE idKomitent = ?";
			PreparedStatement prepS = db.conn.prepareStatement(query);
			prepS.setString(1, komitent.getName());
			prepS.setString(2, komitent.getTelephoneNumber());
			prepS.setInt(3, komitent.getIdKomitent());
			prepS.executeUpdate();
			prepS.close();
		} 
		catch (SQLException e) 
		{
			e.printStackTrace();
		}
	}
	
	public void deleteKomitent(Komitent komitent)
	{
		try 
		{
			String query = "DELETE FROM komitent WHERE idKomitent = ?";
			PreparedStatement prepS = db.conn.prepareStatement(query);
			prepS.setInt(1, komitent.getIdKomitent());
			prepS.executeUpdate();
			prepS.close();
		} 
		catch (SQLException e) 
		{
			e.printStackTrace();
		}
	}
	
	public Komitent readKomitent(int idKomitent)
	{
		try
		{
			String query = "SELECT * FROM komitent WHERE idKomitent = ?";
			
			PreparedStatement prepS = db.conn.prepareStatement(query);
			prepS.setInt(1, idKomitent);
			
			ResultSet res = prepS.executeQuery();
			
			if (res.next()) 
			{
				String name = res.getString("name").trim();
				String telephoneNumber = res.getString("telephoneNumber").trim();
				Komitent komitent = new Komitent(idKomitent, name, telephoneNumber);
				return komitent;
			}
			return null;
		}
		catch (SQLException e) 
		{
			e.printStackTrace();
			return null;
		}
	}
	
	public ArrayList<Komitent> readKomitents()
	{
		ArrayList<Komitent> listaKomitent = new ArrayList<>();
		try
		{
			String query = "SELECT * FROM komitent";
			
			PreparedStatement prepS = db.conn.prepareStatement(query);
			
			ResultSet res = prepS.executeQuery();
			
			while (res.next()) 
			{
				int idKomitent = res.getInt("idKomitent");
				String name = res.getString("name").trim();
				String telephoneNumber = res.getString("telephoneNumber").trim();
				Komitent komitent = new Komitent(idKomitent, name, telephoneNumber);
				listaKomitent.add(komitent);
			}
			return listaKomitent;
		}
		catch (SQLException e) 
		{
			e.printStackTrace();
			return listaKomitent;
		}
	}
}
