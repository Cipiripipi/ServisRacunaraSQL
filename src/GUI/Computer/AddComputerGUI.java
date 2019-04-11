package GUI.Computer;

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
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import servis.Computer;
import servis.TypeOfComputer;

@SuppressWarnings("serial")
public class AddComputerGUI extends JFrame 
{
	JList<TypeOfComputer> typeOfComputer = new JList<>();
	JTextField brand = new JTextField(20);
	JTextField model = new JTextField(20);
	JTextField serialNumber = new JTextField(20);
	JTextArea note = new JTextArea();
	JButton add = new JButton("add computer");
	Computer comp;

	public AddComputerGUI () 
	{
		super("ADD NEW COMPUTER");
		setWindowAddComputer();
		setLabelAndField();
		setButton();
	}
	
	private void setWindowAddComputer() 
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
				if (typeOfComputer.getSelectedValue() == null)
				{
					JOptionPane.showMessageDialog(new JFrame(), "You select type of computer!", "Error", JOptionPane.ERROR_MESSAGE);
					typeOfComputer.requestFocusInWindow();
				}
				else if (brand.getText().isEmpty())
				{
					JOptionPane.showMessageDialog(new JFrame(), "You don't input brand!", "Error", JOptionPane.ERROR_MESSAGE);
					brand.requestFocusInWindow();
				}
				else if (model.getText().isEmpty())
				{
					JOptionPane.showMessageDialog(new JFrame(), "You don't input model!", "Error", JOptionPane.ERROR_MESSAGE);
					model.requestFocusInWindow();
				}
				else if (serialNumber.getText().isEmpty())
				{
					JOptionPane.showMessageDialog(new JFrame(), "You don't input serial number!", "Error", JOptionPane.ERROR_MESSAGE);
					serialNumber.requestFocusInWindow();
				}
				else
				{
					comp = new Computer(typeOfComputer.getSelectedValue(), brand.getText(), model.getText(), serialNumber.getText(), note.getText());
					comp.computerDB.addComputer(comp);
					System.out.println("Computer je ubacen!");
					JFrame ok = new JFrame();
					int r = JOptionPane.showConfirmDialog(ok, "Do you want add another computer?", "You succesful add computer", JOptionPane.OK_OPTION);
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
		
		JLabel type = new JLabel("Pick type of computer: ");
		gbc.gridx = 0;
		gbc.gridy = 0;
		gbc.fill = GridBagConstraints.HORIZONTAL;
		panel.add(type, gbc);
		gbc.gridx = 1;
		gbc.gridy = 0;
		gbc.fill = GridBagConstraints.CENTER;
		typeOfComputer.setListData(TypeOfComputer.values());
		panel.add(typeOfComputer, gbc);
		
		JLabel brandL = new JLabel("Brand: ");
		gbc.gridx = 0;
		gbc.gridy = 1;
		gbc.fill = GridBagConstraints.HORIZONTAL;
		panel.add(brandL, gbc);
		gbc.gridx = 1;
		gbc.gridy = 1;
		gbc.fill = GridBagConstraints.CENTER;
		panel.add(brand, gbc);
		
		JLabel modelL = new JLabel("Model: ");
		gbc.gridx = 0;
		gbc.gridy = 2;
		gbc.fill = GridBagConstraints.HORIZONTAL;
		panel.add(modelL, gbc);
		gbc.gridx = 1;
		gbc.gridy = 2;
		gbc.fill = GridBagConstraints.CENTER;
		panel.add(model, gbc);
		
		JLabel serialNumberL = new JLabel("Serial number: ");
		gbc.gridx = 0;
		gbc.gridy = 3;
		gbc.fill = GridBagConstraints.HORIZONTAL;
		panel.add(serialNumberL, gbc);
		gbc.gridx = 1;
		gbc.gridy = 3;
		gbc.fill = GridBagConstraints.CENTER;
		panel.add(serialNumber, gbc);
		
		JLabel noteL = new JLabel("Note: ");
		gbc.gridx = 0;
		gbc.gridy = 4;
		gbc.fill = GridBagConstraints.HORIZONTAL;
		panel.add(noteL, gbc);
		gbc.gridx = 1;
		gbc.gridy = 4;
		gbc.fill = GridBagConstraints.CENTER;
		note.setPreferredSize(new Dimension(220, 50));
		panel.add(note, gbc);
		
		gbc.gridx = 1;
		gbc.gridy = 5;
		gbc.fill = GridBagConstraints.CENTER;
		panel.add(add, gbc);
		
		getContentPane().add(panel, BorderLayout.NORTH);
		pack();
	}

	public Computer getComp() {
		return comp;
	}
	
	

}
