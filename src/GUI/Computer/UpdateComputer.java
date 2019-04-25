package GUI.Computer;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JPanel;

import GUI.LoadInCompoBox;
import servis.Computer;

@SuppressWarnings("serial")
public class UpdateComputer extends JFrame
{
	private JComboBox<?> listChoise;
	private JPanel jp;
	private JButton update = new JButton("UPDATE");
	private Computer comp = null;
	
	public UpdateComputer() 
	{
		super("UPDATE COMPUTER");
		this.listChoise = LoadInCompoBox.loadComputer(new Computer().computerDB.readComputers());
		this.jp = new ComputerJPanel();
		setWindow();
		setListChoise();
		setLabelAndField();
		setButton();
	}
	
	private void setWindow() 
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
	
	private void setListChoise ()
	{
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.ipadx = 5;
		gbc.ipady = 5;
		
		gbc.gridx = 1;
		gbc.gridy = 0;
		gbc.fill = GridBagConstraints.CENTER;
		
		listChoise.addActionListener(new ActionListener()
		{
			
			@Override
			public void actionPerformed(ActionEvent e) 
			{
				if (listChoise.getSelectedItem() != null)
				{
					comp = (Computer) listChoise.getItemAt(listChoise.getSelectedIndex());
					((ComputerJPanel) jp).getTypeOfComputer().setSelectedValue(comp.getTypeOfComputer(), false);
					((ComputerJPanel) jp).getBrand().setText(comp.getBrand());
					((ComputerJPanel) jp).getModel().setText(comp.getModel());
					((ComputerJPanel) jp).getSerialNumber().setText(comp.getSerialNumber());
					((ComputerJPanel) jp).getNote().setText(comp.getNote());
				}
			}
		});
				
		((ComputerJPanel) jp).getPanel().add(listChoise,gbc);
		getContentPane().add(((ComputerJPanel) jp).getPanel(), BorderLayout.NORTH);
		pack();
			
	}
	
	private void setLabelAndField () 
	{
		getContentPane().add(((ComputerJPanel) jp).getPanel(), BorderLayout.CENTER);
		pack();
	}
	
	private void setButton ()
	{
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.ipadx = 5;
		gbc.ipady = 5;
		
		gbc.gridx = 1;
		gbc.gridy = 10;
		gbc.fill = GridBagConstraints.CENTER;
		
		
		update.addActionListener(new ActionListener() 
		{
			@Override
			public void actionPerformed(ActionEvent e) 
			{
				Computer updateComp  = new Computer(comp.getIdComputer(), ((ComputerJPanel) jp).getTypeOfComputer().getSelectedValue(),
						((ComputerJPanel) jp).getBrand().getText(), ((ComputerJPanel) jp).getModel().getText(), ((ComputerJPanel) jp).getSerialNumber().getText()
						, ((ComputerJPanel) jp).getNote().getText());
			new Computer().computerDB.updateComputer(updateComp);			
			}
		});
		
		((ComputerJPanel) jp).getPanel().add(update,gbc);
		getContentPane().add(((ComputerJPanel) jp).getPanel(), BorderLayout.SOUTH);
		pack();
	}
}
