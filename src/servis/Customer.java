package servis;

import db.CustomerDB;

public class Customer implements InformationAbout
{
	private int idCustomer;
	private String name;
	private String telephoneNumber;
	
	public CustomerDB customerDB = new CustomerDB();
	
	public Customer () {}
	
	public Customer(String name, String telephoneNumber) 
	{
		super();
		this.name = name;
		this.telephoneNumber = telephoneNumber;
	}

	public Customer(int idCustomer, String name, String telephoneNumber) 
	{
		super();
		this.idCustomer = idCustomer;
		this.name = name;
		this.telephoneNumber = telephoneNumber;
	}
	
	@Override
	public String toString() 
	{
		return "Customer: " + this.idCustomer + " " + this.name;
	}

	public int getIdCustomer() {return idCustomer;}
	public String getName() {return name;}
	public String getTelephoneNumber() {return telephoneNumber;}
	public void setName(String name) {this.name = name;}
	public void setTelephoneNumber(String telephoneNumber) {this.telephoneNumber = telephoneNumber;}

	@Override
	public String informationAbout() 
	{
		return "Customer: " + this.idCustomer + " " + this.name + " " + this.telephoneNumber;
	}

	@Override
	public int getIdI() {
		return idCustomer;
	}
	
}
