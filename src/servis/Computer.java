package servis;

import db.ComputerDB;

public class Computer implements InformationAbout
{
	private int idComputer;
	private TypeOfComputer typeOfComputer;
	private String brand;
	private String model;
	private String serialNumber;
	private String note;
	
	public ComputerDB computerDB = new ComputerDB();
	
	public Computer() {}

	public Computer(TypeOfComputer typeOfComputer, String brand, String model, String serialNumber, String note) 
	{
		super();
		this.typeOfComputer = typeOfComputer;
		this.brand = brand;
		this.model = model;
		this.serialNumber = serialNumber;
		this.note = note;
	}

	public Computer(int idComputer, TypeOfComputer typeOfComputer, String brand, String model, String serialNumber, String note) 
	{
		super();
		this.idComputer = idComputer;
		this.typeOfComputer = typeOfComputer;
		this.brand = brand;
		this.model = model;
		this.serialNumber = serialNumber;
		this.note = note;
	}
	
	@Override
	public String toString() 
	{
		return "Computer: " + this.idComputer + " " + this.typeOfComputer + " " + this.brand + " " + this.model + " " + this.serialNumber;
	}

	public int getIdComputer() {return idComputer;}
	public TypeOfComputer getTypeOfComputer() {return typeOfComputer;}
	public String getBrand() {return brand;}
	public String getModel() {return model;}
	public String getSerialNumber() {return serialNumber;}
	public String getNote() {return note;}
	public void setTypeOfComputer(TypeOfComputer typeOfComputer) {this.typeOfComputer = typeOfComputer;}
	public void setBrand(String brand) {this.brand = brand;}
	public void setModel(String model) {this.model = model;}
	public void setSerialNumber(String serialNumber) {this.serialNumber = serialNumber;}
	public void setNote(String note) {this.note = note;}

	@Override
	public String informationAbout() 
	{
		return "Computer: " + this.idComputer + " " + this.typeOfComputer + " " + this.brand + " " + this.model + " " + this.serialNumber + " " + this.note;
	}

	@Override
	public int getIdI() {
		return idComputer;
	}

}
