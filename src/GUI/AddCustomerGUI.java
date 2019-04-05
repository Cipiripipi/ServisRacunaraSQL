package GUI;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import servis.Customer;

@SuppressWarnings("serial")
public class AddCustomerGUI extends JFrame 
{
	JTextField name = new JTextField(20);
	JTextField telephoneNumber = new JTextField(20);
	JButton add = new JButton("add customer");
	
	public AddCustomerGUI()
	{
		super("ADD NEW CUSTOMER");
		setWindowAddCustomer();
		setLabelAndField();
		setButton();
	}

	private void setWindowAddCustomer() 
	{
		Toolkit tk = this.getToolkit();
		Dimension dim = tk.getScreenSize();
		setSize(50, 50);
		setLocation(dim.width/3, dim.height/3);
		setMinimumSize(new Dimension(50, 50));
		getContentPane().setLayout(new GridLayout(2, 1));
		setVisible(true);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
	}
	
	private void setButton ()
	{
		JPanel panel = new JPanel();
		panel.setLayout(new FlowLayout());
		Container container = new Container();
		container.setLayout(new GridLayout(1, 1));
		
		add.addActionListener(new ActionListener() 
		{
			@Override
			public void actionPerformed(ActionEvent e) 
			{
				if (name.getText().isEmpty())
				{
					JOptionPane.showMessageDialog(new JFrame(), "You don't input name!", "Error", JOptionPane.ERROR_MESSAGE);
					name.requestFocusInWindow();
				}
				else if (telephoneNumber.getText().isEmpty())
				{
					JOptionPane.showMessageDialog(new JFrame(), "You don't input telephone number!", "Error", JOptionPane.ERROR_MESSAGE);
					telephoneNumber.requestFocusInWindow();
				}
				else
				{
					Customer customer = new Customer(name.getText(), telephoneNumber.getText());
					customer.customerDB.addCustomer(customer);
					System.out.println("Customer je ubacen!");
					JFrame ok = new JFrame();
					int r = JOptionPane.showConfirmDialog(ok, "Do you want add another customer?", "You succesful add customer", JOptionPane.OK_OPTION);
					if (r != JOptionPane.OK_OPTION)
						dispose();
				}
			}
		});
		
		container.add(add);
		panel.add(container, BorderLayout.SOUTH);
		getContentPane().add(panel);
		pack();
	}
	
	private void setLabelAndField ()
	{
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.ipadx = 5;
		gbc.ipady = 5;
		
		JPanel panel = new JPanel();
		panel.setLayout(new GridBagLayout());
		gbc.insets=new Insets(5, 2, 2, 5); 
		
		JLabel nameL = new JLabel("Name: ");
		gbc.gridx = 0;
		gbc.gridy = 0;
		gbc.fill = GridBagConstraints.HORIZONTAL;
		panel.add(nameL, gbc);
		gbc.gridx = 1;
		gbc.gridy = 0;
		gbc.fill = GridBagConstraints.CENTER;
		panel.add(name, gbc);
		
		JLabel telephoneNumberL = new JLabel("Telephone number: ");
		gbc.gridx = 0;
		gbc.gridy = 1;
		gbc.fill = GridBagConstraints.HORIZONTAL;
		panel.add(telephoneNumberL, gbc);
		gbc.gridx = 1;
		gbc.gridy = 1;
		gbc.fill = GridBagConstraints.CENTER;
		panel.add(telephoneNumber, gbc);
		
		getContentPane().add(panel, BorderLayout.NORTH);
		pack();
	}
}
