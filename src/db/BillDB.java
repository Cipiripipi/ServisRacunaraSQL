package db;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import servis.Bill;

public class BillDB 
{
	Database db = Database.getKonekcijaNaBazu();
	
	public void addBill (Bill bill)
	{
		try 
		{
			String query = "INSERT INTO bill (note, priceOfServis, isPaid) VALUES (?, ?, ?)";
			PreparedStatement prepS = db.conn.prepareStatement(query);
			prepS.setString(1, bill.getNote());
			prepS.setDouble(2, bill.getPriceOfServis());
			prepS.setBoolean(3, bill.isPaid());
			prepS.executeUpdate();
			prepS.close();
		} 
		catch (SQLException e) 
		{
			e.printStackTrace();
		}
	}
	
	public void updateBill (Bill bill)
	{
		try 
		{
			String query = "UPDATE bill SET note = ?, priceOfServis = ?, isPaid = ? WHERE idBill = ?";
			PreparedStatement prepS = db.conn.prepareStatement(query);
			prepS.setString(1, bill.getNote());
			prepS.setDouble(2, bill.getPriceOfServis());
			prepS.setBoolean(3, bill.isPaid());
			prepS.setInt(4, bill.getIdBill());
			prepS.executeUpdate();
			prepS.close();
		} 
		catch (SQLException e) 
		{
			e.printStackTrace();
		}
	}
	
	public void deleteBill (Bill bill)
	{
		try 
		{
			String query = "DELETE FROM bill WHERE idBill = ?";
			PreparedStatement prepS = db.conn.prepareStatement(query);
			prepS.setInt(1, bill.getIdBill());
			prepS.executeUpdate();
			prepS.close();
		} 
		catch (SQLException e) 
		{
			e.printStackTrace();
		}
	}
	
	public Bill readBill(int idBill)
	{
		try
		{
			String query = "SELECT * FROM bill WHERE idBill = ?";
			PreparedStatement prepS = db.conn.prepareStatement(query);
			prepS.setInt(1, idBill);
			
			ResultSet res = prepS.executeQuery();
			
			if (res.next()) 
			{
				String note = res.getString("note").trim();
				double priceOfServis = res.getDouble("priceOfServis");
				boolean isPaid = res.getBoolean("isPaid");
				Bill bill = new Bill(idBill, note, priceOfServis, isPaid);
				return bill;
			}
			return null;
		}
		catch (SQLException e) 
		{
			e.printStackTrace();
			return null;
		}
	}
	/**
	 * 
	 * @param isPaid ALL for all, TRUE for paid and FALSE for not paid
	 * @return
	 */
	public ArrayList<Bill> readBills(String paid)
	{
		String query = "";
		ArrayList<Bill> listaBill = new ArrayList<>();
		if (paid.equalsIgnoreCase("true"))
			 query = "SELECT * FROM bill WHERE isPaid = true";
		else if (paid.equalsIgnoreCase("false"))
			query = "SELECT * FROM bill WHERE isPaid = false";
		else
			query = "SELECT * FROM bill";
		try
		{
			PreparedStatement prepS = db.conn.prepareStatement(query);
			
			ResultSet res = prepS.executeQuery();
			
			while (res.next()) 
			{
				int idBill = res.getInt("idBill");
				String note = res.getString("note").trim();
				double priceOfServis = res.getDouble("priceOfServis");
				boolean isPaid = res.getBoolean("isPaid");
				Bill bill = new Bill(idBill, note, priceOfServis, isPaid);
				listaBill.add(bill);
			}
			return listaBill;
		}
		catch (SQLException e) 
		{
			e.printStackTrace();
			return listaBill;
		}
	}
	/**
	 * 
	 * @param paid ALL for all, TRUE for paid and FALSE for not paid
	 * @param price total price
	 * @param operators >= for higher, <= for lower, = for equals
	 * @return
	 */
	public ArrayList<Bill> readBillsWherePriceIs(String paid, double price, String operators)
	{
		String query = "";
		ArrayList<Bill> listaBill = new ArrayList<>();
		
		if (paid.equalsIgnoreCase("true"))
		{
			if (operators.equalsIgnoreCase("<="))
				query = "SELECT * FROM bill WHERE isPaid = true AND priceOfServis <= " + price ;
			else if (operators.equalsIgnoreCase(">="))
				query = "SELECT * FROM bill WHERE isPaid = true AND priceOfServis >= " + price;
			else
				query = "SELECT * FROM bill WHERE isPaid = true AND priceOfServis = " + price;
		}
		else if (paid.equalsIgnoreCase("false"))
		{
			if (operators.equalsIgnoreCase("<="))
				query = "SELECT * FROM bill WHERE isPaid = false AND priceOfServis <= " + price ;
			else if (operators.equalsIgnoreCase(">="))
				query = "SELECT * FROM bill WHERE isPaid = false AND priceOfServis >= " + price;
			else
				query = "SELECT * FROM bill WHERE isPaid = false AND priceOfServis = " + price;
		}
		else
		{
			if (operators.equalsIgnoreCase("<="))
				query = "SELECT * FROM bill WHERE priceOfServis <= " + price ;
			else if (operators.equalsIgnoreCase(">="))
				query = "SELECT * FROM bill WHERE priceOfServis >= " + price;
			else
				query = "SELECT * FROM bill WHERE priceOfServis = " + price;
		}
		
		try
		{
			PreparedStatement prepS = db.conn.prepareStatement(query);
			
			ResultSet res = prepS.executeQuery();
			
			while (res.next()) 
			{
				int idBill = res.getInt("idBill");
				String note = res.getString("note").trim();
				double priceOfServis = res.getDouble("priceOfServis");
				boolean isPaid = res.getBoolean("isPaid");
				Bill bill = new Bill(idBill, note, priceOfServis, isPaid);
				listaBill.add(bill);
			}
			return listaBill;
		}
		catch (SQLException e) 
		{
			e.printStackTrace();
			return listaBill;
		}
	}
	
}
