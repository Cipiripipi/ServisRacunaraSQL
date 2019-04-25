package GUI.RecordOfService;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;

import GUI.LoadInCompoBox;
import GUI.Bill.AddBillGUI;
import GUI.Computer.AddComputerGUI;
import GUI.Customer.AddCustomerGUI;
import db.UtilDB;
import servis.Bill;
import servis.Computer;
import servis.Customer;
import servis.Serviser;
import servis.StatusOfServis;

@SuppressWarnings("serial")
public class RecordOfServiceJPanel extends JPanel 
{
	JComboBox<StatusOfServis> status = new JComboBox<>();
	JComboBox<Computer> computer = LoadInCompoBox.loadComputer(new Computer().computerDB.readComputers());
	JComboBox<Customer> customer = LoadInCompoBox.loadCustomer(new Customer().customerDB.readCustomer());
	JComboBox<Serviser> serviser = LoadInCompoBox.loadServiser(new Serviser().serviserDB.readServisers());
	JComboBox<Bill> bill = LoadInCompoBox.loadBill(new Bill().billDB.readBillsPaid("ALL"));
	JTextArea note = new JTextArea();
	
	JButton addComputer = new JButton("add new computer");
	JButton addCustomer = new JButton("add new customer");
	JButton addBill = new JButton("      add new bill       ");
	
	boolean addNewComputer = false;
	boolean addNewCustomer = false;
	boolean addNewBill = false;
	
	JPanel panel = new JPanel();
	
	public RecordOfServiceJPanel()
	{
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.ipadx = 5;
		gbc.ipady = 5;
		
		panel.setLayout(new GridBagLayout());
		gbc.insets=new Insets(5, 2, 2, 5); 
		
		//status
		JLabel statusL = new JLabel("Status: ");
		gbc.gridx = 0;
		gbc.gridy = 1;
		gbc.fill = GridBagConstraints.HORIZONTAL;
		panel.add(statusL, gbc);
		
		gbc.gridx = 1;
		gbc.gridy = 1;
		gbc.fill = GridBagConstraints.CENTER;
		status.addItem(StatusOfServis.Reception);
		status.addItem(StatusOfServis.Diagnostic);
		status.addItem(StatusOfServis.WaitingForThePart);
		status.addItem(StatusOfServis.Finished);
		status.addItem(StatusOfServis.TakenOver);
		status.setPreferredSize(new Dimension(350, 25));
		panel.add(status, gbc);
		
		//computer
		JLabel computerL = new JLabel("Computer: ");
		gbc.gridx = 0;
		gbc.gridy = 2;
		gbc.fill = GridBagConstraints.HORIZONTAL;
		panel.add(computerL, gbc);
		
		gbc.gridx = 1;
		gbc.gridy = 2;
		gbc.fill = GridBagConstraints.CENTER;
		computer.setPreferredSize(new Dimension(350, 25));
		computer.addMouseListener(new MouseListener() 
		{
			@Override
			public void mouseReleased(MouseEvent e) {}
			@Override
			public void mousePressed(MouseEvent e) 
			{
				if(addNewComputer == true)
				{
					int last = UtilDB.getLastElement("computer");
					computer.addItem(new Computer().computerDB.readComputer(last));
					addNewComputer = false;
				}
			}
			@Override
			public void mouseExited(MouseEvent e) {}
			@Override
			public void mouseEntered(MouseEvent e) {}
			@Override
			public void mouseClicked(MouseEvent e) {}
		});
		panel.add(computer, gbc);
		
		gbc.gridx = 2;
		gbc.gridy = 2;
		gbc.fill = GridBagConstraints.CENTER;
		
		addComputer.addActionListener(new ActionListener() 
		{
			@Override
			public void actionPerformed(ActionEvent e) 
			{
				new AddComputerGUI();
				addNewComputer = true;
			}
		});
		
		panel.add(addComputer, gbc);
		
		//customer
		JLabel customerL = new JLabel("Customer: ");
		gbc.gridx = 0;
		gbc.gridy = 3;
		gbc.fill = GridBagConstraints.HORIZONTAL;
		panel.add(customerL, gbc);
		
		gbc.gridx = 1;
		gbc.gridy = 3;
		gbc.fill = GridBagConstraints.CENTER;
		customer.setPreferredSize(new Dimension(350, 25));
		customer.addMouseListener(new MouseListener() 
		{
			@Override
			public void mouseReleased(MouseEvent e) {}
			@Override
			public void mousePressed(MouseEvent e) 
			{
				if (addNewCustomer == true)
				{
					int last = UtilDB.getLastElement("customer");
					customer.addItem(new Customer().customerDB.readCustomer(last));
					addNewCustomer = false;
				}
			}
			@Override
			public void mouseExited(MouseEvent e) {}
			@Override
			public void mouseEntered(MouseEvent e) {}
			@Override
			public void mouseClicked(MouseEvent e) {}
		});
		panel.add(customer, gbc);
		
		gbc.gridx = 2;
		gbc.gridy = 3;
		gbc.fill = GridBagConstraints.CENTER;
		addCustomer.addActionListener(new ActionListener() 
		{
			@Override
			public void actionPerformed(ActionEvent e) 
			{
				new AddCustomerGUI();
				addNewCustomer = true;
			}
		});
		panel.add(addCustomer, gbc);
		
		//serviser
		JLabel serviserL = new JLabel("Serviser: ");
		gbc.gridx = 0;
		gbc.gridy = 4;
		gbc.fill = GridBagConstraints.HORIZONTAL;
		panel.add(serviserL, gbc);
		
		gbc.gridx = 1;
		gbc.gridy = 4;
		gbc.fill = GridBagConstraints.CENTER;
		serviser.setPreferredSize(new Dimension(350, 25));
		panel.add(serviser, gbc);
		
		//bill
		JLabel billL = new JLabel("Bill: ");
		gbc.gridx = 0;
		gbc.gridy = 5;
		gbc.fill = GridBagConstraints.HORIZONTAL;
		panel.add(billL, gbc);
		
		gbc.gridx = 1;
		gbc.gridy = 5;
		gbc.fill = GridBagConstraints.CENTER;
		bill.setPreferredSize(new Dimension(350, 25));
		bill.addMouseListener(new MouseListener() 
		{
			@Override
			public void mouseReleased(MouseEvent e) {}
			@Override
			public void mousePressed(MouseEvent e) 
			{
				if (addNewBill == true)
				{
					int last = UtilDB.getLastElement("bill");
					bill.addItem(new Bill().billDB.readBill(last));
					addNewBill = false;
				}
			}
			@Override
			public void mouseExited(MouseEvent e) {}
			@Override
			public void mouseEntered(MouseEvent e) {}
			@Override
			public void mouseClicked(MouseEvent e) {}
		});
		panel.add(bill, gbc);
		
		gbc.gridx = 2;
		gbc.gridy = 5;
		gbc.fill = GridBagConstraints.CENTER;
		addBill.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent e) 
			{
				new AddBillGUI();
				addNewBill = true;
			}
		});
		panel.add(addBill, gbc);
		
		//note
		JLabel noteL = new JLabel("Note: ");
		gbc.gridx = 0;
		gbc.gridy = 6;
		gbc.fill = GridBagConstraints.HORIZONTAL;
		panel.add(noteL, gbc);
		
		gbc.gridx = 1;
		gbc.gridy = 6;
		gbc.fill = GridBagConstraints.CENTER;
		note.setPreferredSize(new Dimension(350, 75));
		panel.add(note, gbc);
		
	}

	public JComboBox<StatusOfServis> getStatus() {return status;}
	public JComboBox<Computer> getComputer() {return computer;}
	public JComboBox<Customer> getCustomer() {return customer;}
	public JComboBox<Serviser> getServiser() {return serviser;}
	public JComboBox<Bill> getBill() {return bill;}
	public JTextArea getNote() {return note;}
	public JPanel getPanel() {return panel;}
	
}
