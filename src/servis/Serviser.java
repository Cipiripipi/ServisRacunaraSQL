package servis;

import db.ServiserDB;

public class Serviser 
{
	private int idServiser;
	private String name;
	private String password;
	private double salary;
	
	public ServiserDB serviserDB= new ServiserDB();
	
	public Serviser() {}
	
	public Serviser(String name, String password, double salary) 
	{
		this.name = name;
		this.password = password;
		this.salary = salary;
		serviserDB.addServiser(this);
	}
	
	public Serviser(int idServiser, String name, String password, double salary) 
	{
		super();
		this.idServiser = idServiser;
		this.name = name;
		this.password = password;
		this.salary = salary;
	}
	
	@Override
	public String toString() 
	{
		return "Serviser: " + this.idServiser + ", " + this.name;
	}

	public int getIdServiser() {return idServiser;}
	public String getName() {return name;}
	public String getPassword() {return password;}
	public double getSalary() {return salary;}
	public void setName(String name) {this.name = name;}
	public void setPassword(String password) {this.password = password;}
	public void setSalary(double salary) {this.salary = salary;}
}
