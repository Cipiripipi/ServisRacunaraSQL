package db;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import servis.Bill;
import servis.TypeOfComputer;
import util.BillPaid;
import util.MyArrayList;

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
	private MyArrayList<Bill> readBills(String query)
	{
		MyArrayList<Bill> listaBill = new MyArrayList<>();
		
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
	 * @param isPaid ALL for all, TRUE for paid and FALSE for not paid
	 * @return
	 */
	public MyArrayList<Bill> readBillsPaid(String paid)
	{
		String query = "";
		
		if (paid.equalsIgnoreCase("true"))
			 query = "SELECT * FROM bill WHERE isPaid = true";
		else if (paid.equalsIgnoreCase("false"))
			query = "SELECT * FROM bill WHERE isPaid = false";
		else
			query = "SELECT * FROM bill";
		MyArrayList<Bill> listaBill = this.readBills(query);
		return listaBill;
	}
	
	/**
	 * 
	 * @param paid ALL for all, TRUE for paid and FALSE for not paid
	 * @param price total price
	 * @param operators >= for higher, <= for lower, = for equals
	 * @return
	 */
	public MyArrayList<Bill> readBillsWherePriceIs(String paid, double price, String operators)
	{
		String query = "";
		
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
		MyArrayList<Bill> listaBill = this.readBills(query);
		return listaBill;
	}
	
	private void printResult (String query)
	{
		PreparedStatement prepS;
		try 
		{
			prepS = db.conn.prepareStatement(query);
			ResultSet res = prepS.executeQuery();
			if (res.next())
				System.out.println(res.getDouble("newField") + System.lineSeparator());
			
		} 
		catch (SQLException e) 
		{
			
			e.printStackTrace();
		}
	}
	
	public void printAvgPrice ()
	{
		System.out.print("AVG value for price of service is: ");
		printResult("SELECT idBill, priceOfServis, isPaid, AVG(priceOfServis) AS newField FROM bill");
	}
	
	public MyArrayList<Bill> printSumIsPaid (boolean paid)
	{
		String query1 = "";
		String query2 = "";
		if (paid == true)
		{
			query1 = "SELECT * , SUM(priceOfServis) AS newField FROM bill WHERE isPaid = true";
			query2 = "SELECT * FROM bill WHERE isPaid = true";
		}
		else
		{
			query1= "SELECT * , SUM(priceOfServis) AS newField FROM bill WHERE isPaid = false";
			query2 = "SELECT * FROM bill WHERE isPaid = false";
		}
		MyArrayList<Bill> list = this.readBills(query2);
		System.out.print("Sum of " + BillPaid.isPaid(paid) + " is ");
		this.printResult(query1);
		
		return list;
	}
	
	public MyArrayList<Bill> printSumByComputerType (TypeOfComputer typeOfComputer)
	{
		String query1 = "";
		String query2 = "";
		
		if (typeOfComputer == TypeOfComputer.Desktop)
		{
			query1 = "SELECT * , SUM(priceOfServis) AS newField FROM bill, recordofservis, computer WHERE bill.idBill = recordofservis.billId AND computer.idComputer = recordofservis.computerId AND computer.typeOfComputer = 'Desktop'";
			query2 = "SELECT * FROM bill, recordofservis, computer WHERE bill.idBill = recordofservis.billId AND computer.idComputer = recordofservis.computerId AND computer.typeOfComputer = 'Desktop'";
		}
		else
		{
			query1 = "SELECT * , SUM(priceOfServis) AS newField FROM bill, recordofservis, computer WHERE bill.idBill = recordofservis.billId AND computer.idComputer = recordofservis.computerId AND computer.typeOfComputer = 'Laptop'";
			query2 = "SELECT * FROM bill, recordofservis, computer WHERE bill.idBill = recordofservis.billId AND computer.idComputer = recordofservis.computerId AND computer.typeOfComputer = 'Laptop'";
		}
		MyArrayList<Bill> list = this.readBills(query2);
		System.out.print("Sum of " + typeOfComputer.toString() + " on service is ");
		this.printResult(query1);
		return list;
	}
	
	/**
	 * @param paid ALL for all, TRUE for paid and FALSE for not paid
	 * @param date
	 * @param operators >= for higher, <= for lower, = for equals
	 * @return
	 */
	public MyArrayList<Bill> printSumByDate (String paid, Date date)
	{
		MyArrayList<Bill> list = new MyArrayList<>();
		String query1 = "";
		String query2 = "";
		
		if (paid.equalsIgnoreCase("true"))
		{
			query1 = "select * , SUM(priceOfServis) AS newField from bill INNER JOIN recordofservis ON bill.idBill = recordofservis.billId WHERE bill.isPaid = true AND recordofservis.dateOfReciept = '"+ date + "'";
			query2 = "select * from bill INNER JOIN recordofservis ON bill.idBill = recordofservis.billId WHERE bill.isPaid = true AND recordofservis.dateOfReciept = '"+ date + "'";
		}
		else if (paid.equalsIgnoreCase("false"))
		{
			query1 = "select * , SUM(priceOfServis) AS newField from bill INNER JOIN recordofservis ON bill.idBill = recordofservis.billId WHERE bill.isPaid = false AND recordofservis.dateOfReciept = '"+ date + "'";
			query2 = "select * from bill INNER JOIN recordofservis ON bill.idBill = recordofservis.billId WHERE bill.isPaid = false AND recordofservis.dateOfReciept = '"+ date + "'";
		}
		else
		{
			query1 = "select * , SUM(priceOfServis) AS newField from bill INNER JOIN recordofservis ON bill.idBill = recordofservis.billId WHERE recordofservis.dateOfReciept = '"+ date + "'";
			query2 = "select * from bill INNER JOIN recordofservis ON bill.idBill = recordofservis.billId WHERE recordofservis.dateOfReciept = '"+ date + "'";
		}
		list = this.readBills(query2);
		if (paid.equalsIgnoreCase("true"))
		{
			System.out.print("Sum of paid bill for date " + date + " is ");
			this.printResult(query1);
		}
		else if (paid.equalsIgnoreCase("false"))
		{
			System.out.print("Sum of not paid bill for date " + date + " is ");
			this.printResult(query1);
		}
		else 
		{
			System.out.print("Sum of bill for date " + date + " is ");
			this.printResult(query1);
		}
		return list;
	}
	
	
}
