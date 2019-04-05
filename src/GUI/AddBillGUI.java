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

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import servis.Bill;

@SuppressWarnings("serial")
public class AddBillGUI extends JFrame 
{
	JTextArea note = new JTextArea();
	JTextField price = new JTextField(20);
	JComboBox<String> paid = new JComboBox<>();
	
	JButton add = new JButton("add bill");
	
	public AddBillGUI() 
	{
		super("ADD NEW BILL");
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
				
				if (note.getText().isEmpty())
				{
					JOptionPane.showMessageDialog(new JFrame(), "You don't input note!", "Error", JOptionPane.ERROR_MESSAGE);
					note.requestFocusInWindow();
				}
				else if (price.getText().isEmpty())
				{
					JOptionPane.showMessageDialog(new JFrame(), "You don't input price!", "Error", JOptionPane.ERROR_MESSAGE);
					price.requestFocusInWindow();
				}
				else if (!(price.getText().matches("^[0-9]*$"))) 
				{
					JOptionPane.showMessageDialog(new JFrame(), "You must input only number in price field!", "Error", JOptionPane.ERROR_MESSAGE);
					price.requestFocusInWindow();
				}
				else
				{
					boolean p;
					if (paid.getSelectedItem().toString().equalsIgnoreCase("Paid"))
						p = true;
					else
						p = false;
					Bill bill = new Bill(note.getText(), Integer.parseInt(price.getText()), p);
					bill.billDB.addBill(bill);
					System.out.println("Bill je ubacen!");
					JFrame ok = new JFrame();
					int r = JOptionPane.showConfirmDialog(ok, "Do you want add another bill?", "You succesful add bill", JOptionPane.OK_OPTION);
					if (r != JOptionPane.OK_OPTION)
						dispose();
				}
			}
		});
	}
	
	private void setLabelAndField ()
	{
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.ipadx = 5;
		gbc.ipady = 5;
		
		JPanel panel = new JPanel();
		panel.setLayout(new GridBagLayout());
		gbc.insets=new Insets(5, 2, 2, 5); 
		
		JLabel noteL = new JLabel("Note: ");
		gbc.gridx = 0;
		gbc.gridy = 0;
		gbc.fill = GridBagConstraints.HORIZONTAL;
		panel.add(noteL, gbc);
		gbc.gridx = 1;
		gbc.gridy = 0;
		gbc.fill = GridBagConstraints.CENTER;
		note.setPreferredSize(new Dimension(220, 50));
		panel.add(note, gbc);
		
		JLabel priceL = new JLabel("Price: ");
		gbc.gridx = 0;
		gbc.gridy = 1;
		gbc.fill = GridBagConstraints.HORIZONTAL;
		panel.add(priceL, gbc);
		gbc.gridx = 1;
		gbc.gridy = 1;
		gbc.fill = GridBagConstraints.CENTER;
		panel.add(price, gbc);
		
		JLabel paidL = new JLabel("Paid: ");
		gbc.gridx = 0;
		gbc.gridy = 2;
		gbc.fill = GridBagConstraints.HORIZONTAL;
		panel.add(paidL, gbc);
		gbc.gridx = 1;
		gbc.gridy = 2;
		gbc.fill = GridBagConstraints.CENTER;
		paid.addItem("Paid");
		paid.addItem("Not paid");
		panel.add(paid, gbc);
		
		gbc.gridx = 1;
		gbc.gridy = 3;
		gbc.fill = GridBagConstraints.CENTER;
		panel.add(add, gbc);
		
		getContentPane().add(panel, BorderLayout.NORTH);
		pack();
	}
}
