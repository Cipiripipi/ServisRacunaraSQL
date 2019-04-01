package db;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import servis.Computer;
import servis.TypeOfComputer;

public class ComputerDB 
{
	Database db = Database.getKonekcijaNaBazu();
	
	public void addComputer(Computer computer)
	{
		try 
		{
			String query = "INSERT INTO computer (typeOfComputer, brand, model, serialNumber, note) VALUES (?, ?, ?, ?, ?)";
			PreparedStatement prepS = db.conn.prepareStatement(query);
			prepS.setString(1, computer.getTypeOfComputer().toString());
			prepS.setString(2, computer.getBrand());
			prepS.setString(3, computer.getModel());
			prepS.setString(4, computer.getSerialNumber());
			prepS.setString(5, computer.getNote());
			prepS.executeUpdate();
			prepS.close();
		} 
		catch (SQLException e) 
		{
			e.printStackTrace();
		}
	}
	
	public void updateComputer(Computer computer)
	{
		try 
		{
			String query = "UPDATE computer SET typeOfComputer = ?, brand = ?, model = ?, serialNumber = ?, note = ? WHERE idComputer = ?";
			PreparedStatement prepS = db.conn.prepareStatement(query);
			prepS.setString(1, computer.getTypeOfComputer().toString());
			prepS.setString(2, computer.getBrand());
			prepS.setString(3, computer.getModel());
			prepS.setString(4, computer.getSerialNumber());
			prepS.setString(5, computer.getNote());
			prepS.setInt(6, computer.getIdComputer());
			prepS.executeUpdate();
			prepS.close();
		} 
		catch (SQLException e) 
		{
			e.printStackTrace();
		}
	}
	
	public void deleteComputer(Computer computer)
	{
		try 
		{
			String query = "DELETE FROM computer WHERE idComputer = ?";
			PreparedStatement prepS = db.conn.prepareStatement(query);
			prepS.setInt(1, computer.getIdComputer());
			prepS.executeUpdate();
			prepS.close();
		} 
		catch (SQLException e) 
		{
			e.printStackTrace();
		}
	}
	
	public Computer readComputer(int idComputer)
	{
		try
		{
			String query = "SELECT * FROM computer WHERE idComputer = ?";
			PreparedStatement prepS = db.conn.prepareStatement(query);
			prepS.setInt(1, idComputer);
			
			ResultSet res = prepS.executeQuery();
			
			if (res.next()) 
			{
				TypeOfComputer type = TypeOfComputer.valueOf(res.getString("typeOfComputer"));
				String brand = res.getString("brand").trim();
				String model = res.getString("model").trim();
				String serialNumber = res.getString("serialNumber").trim();
				String note = res.getString("note").trim();
				Computer computer = new Computer(idComputer, type, brand, model, serialNumber, note);
				return computer;
			}
			return null;
		}
		catch (SQLException e) 
		{
			e.printStackTrace();
			return null;
		}
	}
	
	public ArrayList<Computer> readComputers()
	{
		ArrayList<Computer> listaComputer = new ArrayList<>();
		try
		{
			String query = "SELECT * FROM computer ";
			PreparedStatement prepS = db.conn.prepareStatement(query);
			
			ResultSet res = prepS.executeQuery();
			
			while (res.next()) 
			{
				int idComputer = res.getInt("idComputer");
				TypeOfComputer type = TypeOfComputer.valueOf(res.getString("typeOfComputer"));
				String brand = res.getString("brand").trim();
				String model = res.getString("model").trim();
				String serialNumber = res.getString("serialNumber").trim();
				String note = res.getString("note").trim();
				Computer computer = new Computer(idComputer, type, brand, model, serialNumber, note);
				listaComputer.add(computer);
			}
			return listaComputer;
		}
		catch (SQLException e) 
		{
			e.printStackTrace();
			return listaComputer;
		}
	}
	
}
