package GUI;

import java.util.ArrayList;

import javax.swing.JComboBox;

import servis.Bill;
import servis.Computer;
import servis.Customer;
import servis.RecordOfServis;
import servis.Serviser;
import servis.TypeOfComputer;

public class LoadInCompoBox 
{
	public static JComboBox<Computer> loadComputer(ArrayList<Computer> listComputers)
	{
		JComboBox<Computer> listComputerCB = new JComboBox<>();
		listComputers.add(0, null);
		for (Computer c : listComputers) 
			listComputerCB.addItem(c);
		return listComputerCB;
	}
	public static JComboBox<Customer> loadCustomer(ArrayList<Customer> listCustomers)
	{
		JComboBox<Customer> listCustomerCB = new JComboBox<>();
		listCustomers.add(0, null);
		for (Customer c : listCustomers) 
			listCustomerCB.addItem(c);
		return listCustomerCB;
	}
	public static JComboBox<Serviser> loadServiser(ArrayList<Serviser> listServiser)
	{
		JComboBox<Serviser> listServiserCB = new JComboBox<>();
		listServiser.add(0, null);
		for (Serviser s : listServiser) 
			listServiserCB.addItem(s);
		return listServiserCB;
	}
	public static JComboBox<Bill> loadBill(ArrayList<Bill> listBill)
	{
		JComboBox<Bill> listBillCB = new JComboBox<>();
		listBill.add(0, null);
		for (Bill b : listBill) 
			listBillCB.addItem(b);
		return listBillCB;
	}
	
	public static JComboBox<RecordOfServis> loadRecordOfServis(ArrayList<RecordOfServis> listRecord)
	{
		JComboBox<RecordOfServis> listRecordCB = new JComboBox<>();
		listRecord.add(0, null);
		for (RecordOfServis r : listRecord) 
			listRecordCB.addItem(r);
		return listRecordCB;
	}
	
	public static JComboBox<TypeOfComputer> loadTypeOfComputer ()
	{
		JComboBox<TypeOfComputer> listTypeCB = new JComboBox<>(TypeOfComputer.values());
		return listTypeCB;
	}
	
}
