package GUI.RecordOfService;

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
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import GUI.LoadInCompoBox;
import servis.Bill;
import servis.Computer;
import servis.Customer;
import servis.MethodsForROS;
import servis.RecordOfServis;
import servis.Serviser;
import servis.StatusOfServis;

@SuppressWarnings("serial")
public class UpdateRecordOfService extends JFrame 
{

	private JComboBox<?> listChoise;
	private JPanel jp;
	private JButton update = new JButton("UPDATE");
	private RecordOfServis recordOfService = null;
	
	public UpdateRecordOfService()
	{
		super("UPDATE RECORD OF SERVICE");
		this.listChoise = LoadInCompoBox.loadRecordOfServis(new RecordOfServis().recordOfServisDB.readAllRecordsOfServis());
		this.jp = new RecordOfServiceJPanel();
		setWindow();
		setListChoise();
		setLabelAndField();
		setButton();
	}
	private void setWindow() 
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
	
	private void setLabelAndField () 
	{
		getContentPane().add(((RecordOfServiceJPanel) jp).getPanel(), BorderLayout.CENTER);
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
				String infoBill = new MethodsForROS().changeStatusOfServis(recordOfService, ((StatusOfServis)((RecordOfServiceJPanel)jp).status.getSelectedItem()));
				if (infoBill.equalsIgnoreCase("Service for this device isn't paid, you cant't set status to TakenOver!!") == false)
				{
					RecordOfServis recordOfServiceUpdate  = new RecordOfServis(recordOfService.getIdRecordOfServis(), 
							((StatusOfServis)((RecordOfServiceJPanel)jp).status.getSelectedItem()),
							((Computer)((RecordOfServiceJPanel)jp).computer.getSelectedItem()),((Customer)((RecordOfServiceJPanel)jp).customer.getSelectedItem()),
							((Serviser)((RecordOfServiceJPanel)jp).serviser.getSelectedItem()), ((RecordOfServiceJPanel)jp).getNote().getText(), 
							((Bill)((RecordOfServiceJPanel)jp).bill.getSelectedItem()), recordOfService.getDateOfReciept(), recordOfService.getDateOfReturn());
					
					new RecordOfServis().recordOfServisDB.updateRecordOfServis(recordOfServiceUpdate);	
				}
					
				JOptionPane.showMessageDialog(new JFrame(), infoBill, "Status", JOptionPane.INFORMATION_MESSAGE);
			}
		});
		
		((RecordOfServiceJPanel) jp).getPanel().add(update,gbc);
		getContentPane().add(((RecordOfServiceJPanel) jp).getPanel(), BorderLayout.SOUTH);
		pack();
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
					recordOfService = (RecordOfServis) listChoise.getItemAt(listChoise.getSelectedIndex());
					
					((RecordOfServiceJPanel) jp).getStatus().setSelectedItem(recordOfService.getStatusOfServis());
					((RecordOfServiceJPanel) jp).getComputer().getModel().setSelectedItem(recordOfService.getComputer());
					((RecordOfServiceJPanel) jp).getCustomer().getModel().setSelectedItem(recordOfService.getCustomer());
					((RecordOfServiceJPanel) jp).getServiser().getModel().setSelectedItem(recordOfService.getServiser());
					((RecordOfServiceJPanel) jp).getBill().getModel().setSelectedItem(recordOfService.getBill());
					((RecordOfServiceJPanel) jp).getNote().setText(recordOfService.getNoteOfDefect());
				}
			}
		});
		
				((RecordOfServiceJPanel) jp).getPanel().add(listChoise,gbc);
		getContentPane().add(((RecordOfServiceJPanel) jp).getPanel(), BorderLayout.NORTH);
		pack();
	}
}
