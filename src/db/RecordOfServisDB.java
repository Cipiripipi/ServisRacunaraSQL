package db;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import servis.Bill;
import servis.Computer;
import servis.Customer;
import servis.RecordOfServis;
import servis.Serviser;
import servis.StatusOfServis;
import servis.TypeOfComputer;
import util.MyArrayList;

public class RecordOfServisDB 
{
	Database db = Database.getKonekcijaNaBazu();
	
	public void addRecordOfServis (RecordOfServis recordOfServis)
	{
		try 
		{
			String query = "INSERT INTO recordofservis (statusOfServis, computerId, customerId, serviserId, noteOfDefect, billId, dateOfReciept, dateOfReturn) "
					+ "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
			PreparedStatement prepS = db.conn.prepareStatement(query);
			prepS.setString(1, recordOfServis.getStatusOfServis().toString());
			prepS.setInt(2, recordOfServis.getComputer().getIdComputer());
			prepS.setInt(3, recordOfServis.getCustomer().getIdCustomer());
			if (recordOfServis.getServiser() != null)
				prepS.setInt(4, recordOfServis.getServiser().getIdServiser());
			else 
				prepS.setNull(4, Types.BIGINT);
			prepS.setString(5, recordOfServis.getNoteOfDefect());
			if (recordOfServis.getBill() != null)
				prepS.setInt(6, recordOfServis.getBill().getIdBill());
			else
				prepS.setNull(6, Types.BIGINT);
			prepS.setDate(7, recordOfServis.getDateOfReciept());
			prepS.setDate(8, recordOfServis.getDateOfReturn());
			prepS.executeUpdate();
			prepS.close();
		}
		catch (SQLException e) 
		{
			e.printStackTrace();
		}
	}
	
	public void updateRecordOfServis (RecordOfServis recordOfServis)
	{
		try
		{
			String query = "UPDATE recordofservis SET statusOfServis = ?, computerId = ?, customerId = ?, serviserId = ?, noteOfDefect = ?, billId = ?, dateOfReciept = ?, dateOfReturn = ? "
				+ "WHERE idRecordOfServis = ?";
			PreparedStatement prepS = db.conn.prepareStatement(query);
			prepS.setString(1, recordOfServis.getStatusOfServis().toString());
			prepS.setInt(2, recordOfServis.getComputer().getIdComputer());
			prepS.setInt(3, recordOfServis.getCustomer().getIdCustomer());
			if (recordOfServis.getServiser() != null)
				prepS.setInt(4, recordOfServis.getServiser().getIdServiser());
			else 
				prepS.setNull(4, Types.BIGINT);;
			prepS.setString(5, recordOfServis.getNoteOfDefect());
			if (recordOfServis.getBill() != null)
				prepS.setInt(6, recordOfServis.getBill().getIdBill());
			else
				prepS.setNull(6, Types.BIGINT);
			prepS.setDate(7, recordOfServis.getDateOfReciept());
			prepS.setDate(8, recordOfServis.getDateOfReturn());
			prepS.setInt(9, recordOfServis.getIdRecordOfServis());
			prepS.executeUpdate();
			prepS.close();
		}
		catch (SQLException e) 
		{
			e.printStackTrace();
		}
	}
	
	public void deleteRecordOfServis (RecordOfServis recordOfServis)
	{
		try 
		{
			String query = "DELETE FROM recordofservis WHERE idRecordOfServis = ?";
			PreparedStatement prepS = db.conn.prepareStatement(query);
			prepS.setInt(1, recordOfServis.getIdRecordOfServis());
			prepS.executeUpdate();
			prepS.close();
		}
		catch (SQLException e) 
		{
			e.printStackTrace();
		}
	}
	
	public RecordOfServis readRecordOfServis (int idRecordOfServis)
	{
		try
		{
			String query = "SELECT * FROM recordofservis WHERE idRecordOfServis = ?";
			PreparedStatement prepS = db.conn.prepareStatement(query);
			prepS.setInt(1, idRecordOfServis);
			
			ResultSet res = prepS.executeQuery();
			
			if (res.next()) 
			{
				StatusOfServis statusOfServis = StatusOfServis.valueOf(res.getString("statusOfServis"));
				int computerId = res.getInt("computerId");
				int customerId = res.getInt("customerId");
				int serviserId = res.getInt("serviserId");
				String noteOfDefect = res.getString("noteOfDefect");
				int billId = res.getInt("billId");
				Date dateOfReciept = res.getDate("dateOfReciept");
				Date dateOfReturn = res.getDate("dateOfReturn");
				RecordOfServis recordOfServis = new RecordOfServis(idRecordOfServis, statusOfServis, new Computer().computerDB.readComputer(computerId), new Customer().customerDB.readCustomer(customerId)
						, new Serviser().serviserDB.readServiser(serviserId), noteOfDefect, new Bill().billDB.readBill(billId), dateOfReciept, dateOfReturn);
				return recordOfServis;
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
	 * @param query
	 * @return RecordOfServis for input query
	 */
	private MyArrayList<RecordOfServis> readRecordsOfServis (String query)
	{
		MyArrayList<RecordOfServis> listaRecordsOfServis = new MyArrayList<>();
		try
		{
			//String query = "SELECT * FROM recordofservis";
			PreparedStatement prepS = db.conn.prepareStatement(query);
			
			ResultSet res = prepS.executeQuery();
			
			while (res.next()) 
			{
				int idRecordOfServis = res.getInt("idRecordOfServis");
				StatusOfServis statusOfServis = StatusOfServis.valueOf(res.getString("statusOfServis"));
				int computerId = res.getInt("computerId");
				int customerId = res.getInt("komitentId");
				int serviserId = res.getInt("serviserId");
				String noteOfDefect = res.getString("noteOfDefect");
				int billId = res.getInt("billId");
				Date dateOfReciept = res.getDate("dateOfReciept");
				Date dateOfReturn = res.getDate("dateOfReturn");
				RecordOfServis recordOfServis = new RecordOfServis(idRecordOfServis, statusOfServis, new Computer().computerDB.readComputer(computerId), new Customer().customerDB.readCustomer(customerId)
						, new Serviser().serviserDB.readServiser(serviserId), noteOfDefect, new Bill().billDB.readBill(billId), dateOfReciept, dateOfReturn);
				listaRecordsOfServis.add(recordOfServis);
			}
			return listaRecordsOfServis;
		}
		catch (SQLException e) 
		{
			e.printStackTrace();
			return listaRecordsOfServis;
		}
	}
	
	public MyArrayList<RecordOfServis> readAllRecordsOfServis()
	{
		MyArrayList<RecordOfServis> listaRecordsOfServis = readRecordsOfServis("SELECT * FROM recordofservis");
		return listaRecordsOfServis;
	}
	
	/**
	 * 
	 * @param paid - true or false
	 * @return ArrayList<RecordOfServis>
	 */
	public MyArrayList<RecordOfServis> readRecordsOfServisPaid(Boolean paid)
	{
		String query;
		if (paid == true)
			query = "SELECT * FROM recordofservis INNER JOIN bill ON recordofservis.billId = bill.idBill WHERE bill.isPaid = true";
		else
			query = "SELECT * FROM recordofservis INNER JOIN bill ON recordofservis.billId = bill.idBill WHERE bill.isPaid = false";
		MyArrayList<RecordOfServis> listaRecordsOfServis = readRecordsOfServis(query);
		return listaRecordsOfServis;
	}
	
	public MyArrayList<RecordOfServis> readRecordsOfServisByTypeOfComputer (TypeOfComputer typeOfComputer)
	{
		String query;
		if (typeOfComputer == TypeOfComputer.Desktop)
			query = "SELECT * FROM recordofservis INNER JOIN computer ON recordofservis.computerId = computer.idComputer WHERE computer.typeOfComputer = 'Desktop'";
		else
			query = "SELECT * FROM recordofservis INNER JOIN computer ON recordofservis.computerId = computer.idComputer WHERE computer.typeOfComputer = 'Laptop'";
		MyArrayList<RecordOfServis> list = readRecordsOfServis(query);
		return list;
	}
	
	public MyArrayList<RecordOfServis> readRecordsOfServisByServiser (Serviser serviser)
	{
		String query = "SELECT * FROM recordofservis WHERE serviserId = " + serviser.getIdServiser();
		MyArrayList<RecordOfServis> list = readRecordsOfServis(query);
		return list;
	}
	
}
