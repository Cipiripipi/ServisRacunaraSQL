package servis;

import db.BillDB;
import util.BillPaid;
import lombok.*;
public class Bill 
{
	@Getter @Setter private int idBill;
	@Getter @Setter private String note;
	@Getter @Setter private double priceOfServis;
	@Getter @Setter private boolean isPaid;
	
	public BillDB billDB = new BillDB();
	
	public Bill() {}

	public Bill(String note, double priceOfServis, boolean isPaid) 
	{
		super();
		this.note = note;
		this.priceOfServis = priceOfServis;
		this.isPaid = isPaid;
		billDB.addBill(this);
	}

	public Bill(int idBill, String note, double priceOfServis, boolean isPaid) 
	{
		super();
		this.idBill = idBill;
		this.note = note;
		this.priceOfServis = priceOfServis;
		this.isPaid = isPaid;
	}
	
	@Override
	public String toString() 
	{
		return "ID bill: " + this.idBill + ", Price of servis is " + this.priceOfServis + ", " + BillPaid.isPaid(this.isPaid) + "\nNote for bill: " + this.note;
	}

	public String getNote() {return note;}
	public void setNote(String note) {this.note = note;}
	public double getPriceOfServis() {return priceOfServis;}
	public void setPriceOfServis(double priceOfServis) {this.priceOfServis = priceOfServis;}
	public boolean isPaid() {return isPaid;}
	public void setPaid(boolean isPaid) {this.isPaid = isPaid;}
	public int getIdBill() {return idBill;}
	
}
