package servis;

import java.sql.Date;
import java.time.LocalDate;
import db.RecordOfServisDB;

public class RecordOfServis
{
	private int idRecordOfServis;
	private StatusOfServis statusOfServis;
	private Computer computer;
	private Customer customer;
	private Serviser serviser;
	private String noteOfDefect;
	private Bill bill;
	private Date dateOfReciept;
	private Date dateOfReturn;
	
	public RecordOfServisDB recordOfServisDB = new RecordOfServisDB();
	public MethodsForROS pickMethod = new MethodsForROS();
	
	public RecordOfServis() {}
	
	public RecordOfServis(Computer computer, Customer customer, String noteOfDefect) 
	{
		super();
		this.statusOfServis = StatusOfServis.Reception;
		this.computer = computer;
		this.customer = customer;
		this.serviser = null;
		this.noteOfDefect = noteOfDefect;
		this.bill = null;
		this.dateOfReciept = Date.valueOf(LocalDate.now());
		this.dateOfReturn = null;
	} 

	public RecordOfServis(Computer computer, Customer customer, Serviser serviser, Bill bill, String noteOfDefect) 
	{
		super();
		this.statusOfServis = StatusOfServis.Reception;
		this.computer = computer;
		this.customer = customer;
		this.serviser = serviser;
		this.noteOfDefect = noteOfDefect;
		this.bill = bill;
		this.dateOfReciept = Date.valueOf(LocalDate.now());
		this.dateOfReturn = null;
	}
	
	public RecordOfServis(StatusOfServis statusOfservis, Computer computer, Customer customer, Serviser serviser, Bill bill, String noteOfDefect) 
	{
		super();
		this.statusOfServis = statusOfservis;
		this.computer = computer;
		this.customer = customer;
		this.serviser = serviser;
		this.noteOfDefect = noteOfDefect;
		this.bill = bill;
		this.dateOfReciept = Date.valueOf(LocalDate.now());
		this.dateOfReturn = null;
	}
	
	public RecordOfServis(int idRecordOfServis, StatusOfServis statusOfServis, Computer computer, Customer customer,
			Serviser serviser, String noteOfDefect, Bill bill, Date dateOfReciept, Date dateOfReturn) 
	{
		super();
		this.idRecordOfServis = idRecordOfServis;
		this.statusOfServis = statusOfServis;
		this.computer = computer;
		this.customer = customer;
		this.serviser = serviser;
		this.noteOfDefect = noteOfDefect;
		this.bill = bill;
		this.dateOfReciept = dateOfReciept;
		this.dateOfReturn = dateOfReturn;
	}
	
	@Override
	public String toString() 
	{
		String text = "---------------------\n";
		text += "ID number: " + this.idRecordOfServis + ", status of device is " + this.statusOfServis.toString() + "\n";
		text += this.customer + "\n";
		text += this.computer + "\n";
		if (this.serviser != null)
			text += this.serviser + "\n";
		else
			text += "Serviser isn't assigned\n";
		if (this.bill != null)
			text += this.bill + "\n";
		else
			text += "Bill isn't created yet\n";
		text += "Date of reciept - " + this.dateOfReciept + "\n";
		if (this.dateOfReturn != null)
			text += "Date of return - " + this.dateOfReturn + "\n";
		text += "Note for computer:\n" + this.noteOfDefect + "\n";
		return text;
	}

	public StatusOfServis getStatusOfServis() {return statusOfServis;}
	public void setStatusOfServis(StatusOfServis statusOfServis) {this.statusOfServis = statusOfServis;}
	public Serviser getServiser() {return serviser;}
	public void setServiser(Serviser serviser) {this.serviser = serviser;}
	public String getNoteOfDefect() {return noteOfDefect;}
	public void setNoteOfDefect(String noteOfDefect) {this.noteOfDefect = noteOfDefect;}
	public Bill getBill() {return bill;}
	public void setBill(Bill bill) {this.bill = bill;}
	public Date getDateOfReturn() {return dateOfReturn;}
	public void setDateOfReturn(Date dateOfReturn) {this.dateOfReturn = dateOfReturn;}
	public int getIdRecordOfServis() {return idRecordOfServis;}
	public Computer getComputer() {return computer;}
	public Customer getCustomer() {return customer;}
	public Date getDateOfReciept() {return dateOfReciept;}
	
}
