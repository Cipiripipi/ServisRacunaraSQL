package GUI;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

@SuppressWarnings("serial")
public class ServiceGUI extends JFrame 
{
	
	JLabel computerL = new JLabel("Computer");
	JLabel customerL = new JLabel("Customer");
	JLabel serviserL = new JLabel("Serviser");
	JLabel billL = new JLabel("Bill");
	JLabel recordOfServiceL = new JLabel("Record of service");
	//top
	JButton addComputer = new JButton("ADD COMUPTER");
	JButton addCustomer = new JButton("ADD CUSTOMER");
	JButton addServiser = new JButton("ADD SERVISER");
	JButton addBill = new JButton("ADD BILL");
	JButton updateComputer = new JButton("UPDATE COMPUTER");
	JButton updateCustomer = new JButton("UPDATE CUSTOMER");
	JButton updateServiser = new JButton("UPDATE SERVISER");
	JButton updateBill = new JButton("UPDATE BILL");
	JButton searchComputer = new JButton("SEARCH COMPUTER");
	JButton searchCustomer = new JButton("SEARCH CUSTOMER");
	JButton searchServiser = new JButton("SEARCH SERVISER");
	JButton searchBill = new JButton("SEARCH BILL");
	JButton listComputer = new JButton("LIST OF COMPUTER");
	JButton listCustomer = new JButton("LIST OF CUSTOMER");
	JButton listServiser = new JButton("LIST OF SERVISER");
	JButton listBill = new JButton("LIST OF BILL");
	//bottom
	JButton addNewRecord = new JButton("ADD NEW RECORD");
	JButton setServiser = new JButton("SET SERVISER");
	JButton setBill = new JButton("SET BILL");
	JButton searchRecord = new JButton("SEARCH RECORD");
	JButton listRecords = new JButton("LIST OF RECORDS");
	JButton changeStatus = new JButton("CHANGE STATUS");
	JButton changeNote = new JButton("CHANGE NOTE");
	JButton listRecordsByType = new JButton("LIST RECORD BY TYPE OF COMPUTER");
	JButton listRecordsByServiser = new JButton("LIST RECORD BY SERVISERS");
	
	public ServiceGUI()
	{
		super("SERVICE");
		setMainWindow();
		setTop();
		setBottom();
		implementMethods();
	}
	
	private void setMainWindow()
	{
		Toolkit tk = this.getToolkit();
		Dimension dim = tk.getScreenSize();
		setSize(700, 500);
		setLocation(dim.width/4, dim.height/4);
		setMinimumSize(new Dimension(700, 500));
		getContentPane().setLayout(new GridLayout(2, 1));
		setVisible(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
	}
	
	private void setTop()
	{
		JPanel top = new JPanel();
		top.setLayout(new FlowLayout());
		
		Container container = new Container();
		container.setLayout(new GridLayout(5, 4, 5, 5));
		
		computerL.setHorizontalAlignment(SwingConstants.CENTER);
		customerL.setHorizontalAlignment(SwingConstants.CENTER);
		serviserL.setHorizontalAlignment(SwingConstants.CENTER);
		billL.setHorizontalAlignment(SwingConstants.CENTER);
		container.add(computerL);
		container.add(customerL);
		container.add(serviserL);
		container.add(billL);
		container.add(addComputer);
		container.add(addCustomer);
		container.add(addServiser);
		container.add(addBill);
		container.add(updateComputer);
		container.add(updateCustomer);
		container.add(updateServiser);
		container.add(updateBill);
		container.add(searchComputer);
		container.add(searchCustomer);
		container.add(searchServiser);
		container.add(searchBill);
		container.add(listComputer);
		container.add(listCustomer);
		container.add(listServiser);
		container.add(listBill);
		
		top.add(container, BorderLayout.NORTH);
		getContentPane().add(top);
		pack();
		
	}
	
	private void setBottom()
	{
		JPanel bottom = new JPanel();
		bottom.setLayout(new GridLayout(5, 1, 5, 5));
		bottom.setBorder(BorderFactory.createEmptyBorder(10,20,10,20));
		Container container1 = new Container();
		container1.setLayout(new GridLayout(1, 1, 5, 5));
		
		recordOfServiceL.setHorizontalAlignment(SwingConstants.CENTER);
		recordOfServiceL.setFont(new Font("Arial", Font.BOLD, 24));
		container1.add(recordOfServiceL);
		
		Container container2 = new Container();
		container2.setLayout(new GridLayout(1, 3, 5, 5));
		container2.add(addNewRecord);
		container2.add(setServiser);
		container2.add(setBill);
		
		Container container3 = new Container();
		container3.setLayout(new GridLayout(1, 2, 5, 5));
		container3.add(searchRecord);
		container3.add(listRecords);
		
		Container container4 = new Container();
		container4.setLayout(new GridLayout(1, 2, 5, 5));
		container4.add(changeStatus);
		container4.add(changeNote);
		
		Container container5 = new Container();
		container5.setLayout(new GridLayout(1, 2, 5, 5));
		container5.add(listRecordsByType);
		container5.add(listRecordsByServiser);
		
		bottom.add(container1, BorderLayout.CENTER);
		bottom.add(container2, BorderLayout.CENTER);
		bottom.add(container3, BorderLayout.CENTER);
		bottom.add(container4, BorderLayout.CENTER);
		bottom.add(container5, BorderLayout.CENTER);
		getContentPane().add(bottom);
		pack();
	}
	
	private void implementMethods()
	{
		addServiser.addActionListener(new ActionListener() 
		{
			@Override
			public void actionPerformed(ActionEvent e) 
			{
				new AddServiserGUI();	
			}
		});
		addComputer.addActionListener(new ActionListener() 
		{
			@Override
			public void actionPerformed(ActionEvent e)
			{
				new AddComputerGUI();
			}
		});
		
		addCustomer.addActionListener(new ActionListener() 
		{
			@Override
			public void actionPerformed(ActionEvent e) 
			{
				new AddCustomerGUI();
			}
		});
		addBill.addActionListener(new ActionListener() 
		{
			@Override
			public void actionPerformed(ActionEvent e) 
			{
				new AddBillGUI();
			}
		});
		
		addNewRecord.addActionListener(new ActionListener() 
		{
			@Override
			public void actionPerformed(ActionEvent e) 
			{
				new AddRecordOfServiceGUI();
			}
		});
	}
}
