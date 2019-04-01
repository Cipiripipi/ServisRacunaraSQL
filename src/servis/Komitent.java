package servis;

import db.KomitentDB;

public class Komitent 
{
	private int idKomitent;
	private String name;
	private String telephoneNumber;
	
	public KomitentDB komitentDB = new KomitentDB();
	
	public Komitent () {}
	
	public Komitent(String name, String telephoneNumber) 
	{
		super();
		this.name = name;
		this.telephoneNumber = telephoneNumber;
		komitentDB.addKomitent(this);
	}

	public Komitent(int idKomitent, String name, String telephoneNumber) 
	{
		super();
		this.idKomitent = idKomitent;
		this.name = name;
		this.telephoneNumber = telephoneNumber;
	}
	
	@Override
	public String toString() 
	{
		return "Komitent: " + this.idKomitent + " " + this.name + " " + this.telephoneNumber;
	}

	public int getIdKomitent() {return idKomitent;}
	public String getName() {return name;}
	public String getTelephoneNumber() {return telephoneNumber;}
	public void setName(String name) {this.name = name;}
	public void setTelephoneNumber(String telephoneNumber) {this.telephoneNumber = telephoneNumber;}
	
}
