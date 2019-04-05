package db;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import servis.Customer;

public class CustomerDB 
{
	Database db = Database.getKonekcijaNaBazu();
	
	public void addCustomer(Customer customer)
	{
		try 
		{
			String query = "INSERT INTO customer (name, telephoneNumber) VALUES (?, ?)";
			PreparedStatement prepS = db.conn.prepareStatement(query);
			prepS.setString(1, customer.getName());
			prepS.setString(2, customer.getTelephoneNumber());
			prepS.executeUpdate();
			prepS.close();
		} 
		catch (SQLException e) 
		{
			e.printStackTrace();
		}
	}
	
	public void updateCustomer(Customer customer)
	{
		try 
		{
			String query = "UPDATE customer SET name = ?, telephoneNumber = ? WHERE idCustomer = ?";
			PreparedStatement prepS = db.conn.prepareStatement(query);
			prepS.setString(1, customer.getName());
			prepS.setString(2, customer.getTelephoneNumber());
			prepS.setInt(3, customer.getIdCustomer());
			prepS.executeUpdate();
			prepS.close();
		} 
		catch (SQLException e) 
		{
			e.printStackTrace();
		}
	}
	
	public void deleteCustomer(Customer customer)
	{
		try 
		{
			String query = "DELETE FROM customer WHERE idCustomer = ?";
			PreparedStatement prepS = db.conn.prepareStatement(query);
			prepS.setInt(1, customer.getIdCustomer());
			prepS.executeUpdate();
			prepS.close();
		} 
		catch (SQLException e) 
		{
			e.printStackTrace();
		}
	}
	
	public Customer readCustomer(int idCustomer)
	{
		try
		{
			String query = "SELECT * FROM customer WHERE idCustomer = ?";
			
			PreparedStatement prepS = db.conn.prepareStatement(query);
			prepS.setInt(1, idCustomer);
			
			ResultSet res = prepS.executeQuery();
			
			if (res.next()) 
			{
				String name = res.getString("name").trim();
				String telephoneNumber = res.getString("telephoneNumber").trim();
				Customer customer = new Customer(idCustomer, name, telephoneNumber);
				return customer;
			}
			return null;
		}
		catch (SQLException e) 
		{
			e.printStackTrace();
			return null;
		}
	}
	
	public ArrayList<Customer> readCustomer()
	{
		ArrayList<Customer> listCustomors = new ArrayList<>();
		try
		{
			String query = "SELECT * FROM customer";
			
			PreparedStatement prepS = db.conn.prepareStatement(query);
			
			ResultSet res = prepS.executeQuery();
			
			while (res.next()) 
			{
				int idKomitent = res.getInt("idCustomer");
				String name = res.getString("name").trim();
				String telephoneNumber = res.getString("telephoneNumber").trim();
				Customer customer = new Customer(idKomitent, name, telephoneNumber);
				listCustomors.add(customer);
			}
			return listCustomors;
		}
		catch (SQLException e) 
		{
			e.printStackTrace();
			return listCustomors;
		}
	}
}
