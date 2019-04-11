package GUI.Serviser;

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

import servis.Serviser;

@SuppressWarnings("serial")
public class AddServiserGUI extends JFrame 
{
	JTextField name = new JTextField(20);
	JTextField password = new JTextField(20);
	JTextField salary = new JTextField(20);
	JButton add = new JButton("add serviser");
	
	public AddServiserGUI()
	{
		super("ADD NEW SERVISER");
		setWindowAddServiser();
		setLabelAndField();
		setButton();
	}

	private void setWindowAddServiser() 
	{
		Toolkit tk = this.getToolkit();
		Dimension dim = tk.getScreenSize();
		setSize(150, 100);
		setLocation(dim.width/3, dim.height/3);
		setMinimumSize(new Dimension(150, 100));
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
				else if (password.getText().isEmpty())
				{
					JOptionPane.showMessageDialog(new JFrame(), "You don't input password!", "Error", JOptionPane.ERROR_MESSAGE);
					password.requestFocusInWindow();
				}
				else if (salary.getText().isEmpty())
				{
					JOptionPane.showMessageDialog(new JFrame(), "You don't input salary!", "Error", JOptionPane.ERROR_MESSAGE);
					salary.requestFocusInWindow();
				}
				else if (!(salary.getText().matches("^[0-9]*$"))) 
				{
					JOptionPane.showMessageDialog(new JFrame(), "You must input only number in salary field!", "Error", JOptionPane.ERROR_MESSAGE);
					salary.requestFocusInWindow();
				}
				else
				{
					Serviser s = new Serviser(name.getText(), password.getText(), Integer.parseInt(salary.getText()));
					new Serviser().serviserDB.addServiser(s);
					System.out.println("Serviser je ubacen!");
					JFrame ok = new JFrame();
					int r = JOptionPane.showConfirmDialog(ok, "Do you want add another serviser?", "You succesful add serviser", JOptionPane.OK_OPTION);
					if (r != JOptionPane.OK_OPTION)
						dispose();
				}
			}
		});
		container.add(add);
		panel.add(container, BorderLayout.CENTER);
		getContentPane().add(panel);
		pack();
	}
	
	private void setLabelAndField ()
	{
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.ipadx = 5;
		gbc.ipady = 3;
		
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
		
		JLabel passL = new JLabel("Password: ");
		gbc.gridx = 0;
		gbc.gridy = 1;
		gbc.fill = GridBagConstraints.HORIZONTAL;
		panel.add(passL, gbc);
		
		gbc.gridx = 1;
		gbc.gridy = 1;
		gbc.fill = GridBagConstraints.CENTER;
		panel.add(password, gbc);
		
		JLabel salaryL = new JLabel("Salary: ");
		gbc.gridx = 0;
		gbc.gridy = 2;
		gbc.fill = GridBagConstraints.HORIZONTAL;
		panel.add(salaryL, gbc);
		
		gbc.gridx = 1;
		gbc.gridy = 2;
		gbc.fill = GridBagConstraints.CENTER;
		panel.add(salary, gbc);
		
		getContentPane().add(panel);
		pack();
	}
}
