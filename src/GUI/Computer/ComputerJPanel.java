package GUI.Computer;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import servis.TypeOfComputer;

@SuppressWarnings("serial")
public class ComputerJPanel extends JPanel
{
	
	JList<TypeOfComputer> typeOfComputer = new JList<>();
	JTextField brand = new JTextField(20);
	JTextField model = new JTextField(20);
	JTextField serialNumber = new JTextField(20);
	JTextArea note = new JTextArea();
	JPanel panel = new JPanel();
	
	public ComputerJPanel ()
	{
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.ipadx = 5;
		gbc.ipady = 5;
				
		panel.setLayout(new GridBagLayout());
		gbc.insets=new Insets(5, 2, 2, 5); 
		
		JLabel type = new JLabel("Pick type of computer: ");
		gbc.gridx = 0;
		gbc.gridy = 1;
		gbc.fill = GridBagConstraints.HORIZONTAL;
		panel.add(type, gbc);
		gbc.gridx = 1;
		gbc.gridy = 1;
		gbc.fill = GridBagConstraints.CENTER;
		typeOfComputer.setListData(TypeOfComputer.values());
		panel.add(typeOfComputer, gbc);
		
		JLabel brandL = new JLabel("Brand: ");
		gbc.gridx = 0;
		gbc.gridy = 2;
		gbc.fill = GridBagConstraints.HORIZONTAL;
		panel.add(brandL, gbc);
		gbc.gridx = 1;
		gbc.gridy = 2;
		gbc.fill = GridBagConstraints.CENTER;
		panel.add(brand, gbc);
		
		JLabel modelL = new JLabel("Model: ");
		gbc.gridx = 0;
		gbc.gridy = 3;
		gbc.fill = GridBagConstraints.HORIZONTAL;
		panel.add(modelL, gbc);
		gbc.gridx = 1;
		gbc.gridy = 3;
		gbc.fill = GridBagConstraints.CENTER;
		panel.add(model, gbc);
		
		JLabel serialNumberL = new JLabel("Serial number: ");
		gbc.gridx = 0;
		gbc.gridy = 4;
		gbc.fill = GridBagConstraints.HORIZONTAL;
		panel.add(serialNumberL, gbc);
		gbc.gridx = 1;
		gbc.gridy = 4;
		gbc.fill = GridBagConstraints.CENTER;
		panel.add(serialNumber, gbc);
		
		JLabel noteL = new JLabel("Note: ");
		gbc.gridx = 0;
		gbc.gridy = 5;
		gbc.fill = GridBagConstraints.HORIZONTAL;
		panel.add(noteL, gbc);
		gbc.gridx = 1;
		gbc.gridy = 5;
		gbc.fill = GridBagConstraints.CENTER;
		note.setPreferredSize(new Dimension(220, 50));
		panel.add(note, gbc);
	}

	public JList<TypeOfComputer> getTypeOfComputer() {return typeOfComputer;}
	public JTextField getBrand() {return brand;}
	public JTextField getModel() {return model;}
	public JTextField getSerialNumber() {return serialNumber;}
	public JTextArea getNote() {return note;}
	public JPanel getPanel() {return panel;}

}
