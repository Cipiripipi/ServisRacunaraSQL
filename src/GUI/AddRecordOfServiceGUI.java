package GUI;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;

import servis.Bill;
import servis.Computer;
import servis.Customer;
import servis.RecordOfServis;
import servis.Serviser;
import servis.StatusOfServis;

@SuppressWarnings("serial")
public class AddRecordOfServiceGUI extends JFrame 
{
	JComboBox<StatusOfServis> status = new JComboBox<>();
	JComboBox<Computer> computer = new JComboBox<>();
	JComboBox<Customer> customer = new JComboBox<>();
	JComboBox<Serviser> serviser = new JComboBox<>();
	JComboBox<Bill> bill = new JComboBox<>();
	JTextArea note = new JTextArea();
	
	JButton add = new JButton("add record of service");
	JButton addComputer = new JButton("add new computer");
	JButton addCustomer = new JButton("add new customer");
	JButton addBill = new JButton("      add new bill       ");

	public AddRecordOfServiceGUI()
	{
		super("Record of service");
		setWindowAddRecord();
		setLabelAndField();
		setButton();
	}
	
	private void setWindowAddRecord() 
	{
		Toolkit tk = this.getToolkit();
		Dimension dim = tk.getScreenSize();
		setSize(150, 100);
		setLocation(dim.width/3, dim.height/3);
		setMinimumSize(new Dimension(150, 100));
		getContentPane().setLayout(new GridLayout(1, 1));
		setVisible(true);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
	}
	
	private void setButton ()
	{
		add.addActionListener(new ActionListener() 
		{
			@Override
			public void actionPerformed(ActionEvent e) 
			{
				if (computer.getSelectedItem() == null)
				{
					JOptionPane.showMessageDialog(new JFrame(), "You don't select computer!", "Error", JOptionPane.ERROR_MESSAGE);
					computer.requestFocusInWindow();
				}
				else if (customer.getSelectedItem() == null)
				{
					JOptionPane.showMessageDialog(new JFrame(), "You don't select customer!", "Error", JOptionPane.ERROR_MESSAGE);
					customer.requestFocusInWindow();
				}
				else if (note.getText().isEmpty())
				{
					JOptionPane.showMessageDialog(new JFrame(), "You don't input note!", "Error", JOptionPane.ERROR_MESSAGE);
					note.requestFocusInWindow();
				}
				else
				{
					RecordOfServis ros = new RecordOfServis((StatusOfServis)status.getSelectedItem(), (Computer)computer.getSelectedItem(), (Customer)customer.getSelectedItem(), (Serviser)serviser.getSelectedItem(), (Bill)bill.getSelectedItem(), note.getText());
					ros.recordOfServisDB.addRecordOfServis(ros);
					System.out.println("Record je ubacen!");
					JFrame ok = new JFrame();
					int r = JOptionPane.showConfirmDialog(ok, "Do you want add another record of servis?", "You succesful add record of servis", JOptionPane.OK_OPTION);
					if (r != JOptionPane.OK_OPTION)
						dispose();
				}
			}
		});
	}
	
	private void setLabelAndField ()
	{
		loadComputer();
		loadCustomer();
		loadServiser();
		loadBill();
		
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.ipadx = 5;
		gbc.ipady = 5;
		
		JPanel panel = new JPanel();
		panel.setLayout(new GridBagLayout());
		gbc.insets=new Insets(5, 2, 2, 5); 
		
		//status
		JLabel statusL = new JLabel("Status: ");
		gbc.gridx = 0;
		gbc.gridy = 0;
		gbc.fill = GridBagConstraints.HORIZONTAL;
		panel.add(statusL, gbc);
		
		gbc.gridx = 1;
		gbc.gridy = 0;
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
		gbc.gridy = 1;
		gbc.fill = GridBagConstraints.HORIZONTAL;
		panel.add(computerL, gbc);
		
		gbc.gridx = 1;
		gbc.gridy = 1;
		gbc.fill = GridBagConstraints.CENTER;
		computer.setPreferredSize(new Dimension(350, 25));
		computer.addMouseListener(new MouseListener() 
		{
			@Override
			public void mouseReleased(MouseEvent e) {
				loadComputer();
			}
			@Override
			public void mousePressed(MouseEvent e) {
				computer.removeAllItems();
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
		gbc.gridy = 1;
		gbc.fill = GridBagConstraints.CENTER;
		addComputer.addActionListener(new ActionListener() 
		{
			@Override
			public void actionPerformed(ActionEvent e) 
			{
				new AddComputerGUI();
			}
		});
		panel.add(addComputer, gbc);
		
		//customer
		JLabel customerL = new JLabel("Customer: ");
		gbc.gridx = 0;
		gbc.gridy = 2;
		gbc.fill = GridBagConstraints.HORIZONTAL;
		panel.add(customerL, gbc);
		
		gbc.gridx = 1;
		gbc.gridy = 2;
		gbc.fill = GridBagConstraints.CENTER;
		customer.setPreferredSize(new Dimension(350, 25));
		customer.addMouseListener(new MouseListener() 
		{
			@Override
			public void mouseReleased(MouseEvent e) {
				loadCustomer();
			}
			@Override
			public void mousePressed(MouseEvent e) {
				customer.removeAllItems();
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
		gbc.gridy = 2;
		gbc.fill = GridBagConstraints.CENTER;
		addCustomer.addActionListener(new ActionListener() 
		{
			@Override
			public void actionPerformed(ActionEvent e) 
			{
				new AddCustomerGUI();
			}
		});
		panel.add(addCustomer, gbc);
		
		//serviser
		JLabel serviserL = new JLabel("Serviser: ");
		gbc.gridx = 0;
		gbc.gridy = 3;
		gbc.fill = GridBagConstraints.HORIZONTAL;
		panel.add(serviserL, gbc);
		
		gbc.gridx = 1;
		gbc.gridy = 3;
		gbc.fill = GridBagConstraints.CENTER;
		serviser.setPreferredSize(new Dimension(350, 25));
		panel.add(serviser, gbc);
		
		//bill
		JLabel billL = new JLabel("Bill: ");
		gbc.gridx = 0;
		gbc.gridy = 4;
		gbc.fill = GridBagConstraints.HORIZONTAL;
		panel.add(billL, gbc);
		
		gbc.gridx = 1;
		gbc.gridy = 4;
		gbc.fill = GridBagConstraints.CENTER;
		bill.setPreferredSize(new Dimension(350, 25));
		bill.addMouseListener(new MouseListener() 
		{
			@Override
			public void mouseReleased(MouseEvent e) {
				loadBill();
			}
			@Override
			public void mousePressed(MouseEvent e) {
				bill.removeAllItems();
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
		gbc.gridy = 4;
		gbc.fill = GridBagConstraints.CENTER;
		addBill.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent e) 
			{
				new AddBillGUI();
			}
		});
		panel.add(addBill, gbc);
		
		//note
		JLabel noteL = new JLabel("Note: ");
		gbc.gridx = 0;
		gbc.gridy = 5;
		gbc.fill = GridBagConstraints.HORIZONTAL;
		panel.add(noteL, gbc);
		
		gbc.gridx = 1;
		gbc.gridy = 5;
		gbc.fill = GridBagConstraints.CENTER;
		note.setPreferredSize(new Dimension(350, 75));
		panel.add(note, gbc);
		
		//add record
		gbc.gridx = 1;
		gbc.gridy = 6;
		gbc.fill = GridBagConstraints.CENTER;
		panel.add(add, gbc);
		
		getContentPane().add(panel, BorderLayout.CENTER);
		pack();
	}
	
	private void loadComputer()
	{
		computer.addItem(null);
		ArrayList<Computer> listComputers = new Computer().computerDB.readComputers();
		for (Computer c : listComputers) 
			computer.addItem(c);
	}
	private void loadCustomer()
	{
		customer.addItem(null);
		ArrayList<Customer> listCustomers = new Customer().customerDB.readCustomer();
		for (Customer c : listCustomers) 
			customer.addItem(c);
	}
	private void loadServiser()
	{
		serviser.addItem(null);
		ArrayList<Serviser> listServiser = new Serviser().serviserDB.readServisers();
		for (Serviser s : listServiser) 
			serviser.addItem(s);
	}
	private void loadBill()
	{
		bill.addItem(null);
		ArrayList<Bill> listBill = new Bill().billDB.readBillsPaid("all");
		for (Bill b : listBill) 
			bill.addItem(b);
	}
}
