package GUI.RecordOfService;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import servis.Bill;
import servis.Computer;
import servis.Customer;
import servis.RecordOfServis;
import servis.Serviser;
import servis.StatusOfServis;

@SuppressWarnings("serial")
public class AddRecordOfServiceGUI extends JFrame 
{
	JButton add = new JButton("add record of service");

	RecordOfServiceJPanel rocJP;
	
	public AddRecordOfServiceGUI()
	{
		super("Record of service");
		setWindowAddRecord();
		this.rocJP = new RecordOfServiceJPanel();
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
				if (rocJP.computer.getSelectedItem() == null)
				{
					JOptionPane.showMessageDialog(new JFrame(), "You don't select computer!", "Error", JOptionPane.ERROR_MESSAGE);
					rocJP.computer.requestFocusInWindow();
				}
				else if (rocJP.customer.getSelectedItem() == null)
				{
					JOptionPane.showMessageDialog(new JFrame(), "You don't select customer!", "Error", JOptionPane.ERROR_MESSAGE);
					rocJP.customer.requestFocusInWindow();
				}
				else if (rocJP.note.getText().isEmpty())
				{
					JOptionPane.showMessageDialog(new JFrame(), "You don't input note!", "Error", JOptionPane.ERROR_MESSAGE);
					rocJP.note.requestFocusInWindow();
				}
				else
				{
					RecordOfServis ros = new RecordOfServis((StatusOfServis)rocJP.status.getSelectedItem(), (Computer)rocJP.computer.getSelectedItem(), 
							(Customer)rocJP.customer.getSelectedItem(), (Serviser)rocJP.serviser.getSelectedItem(), (Bill)rocJP.bill.getSelectedItem(), 
							rocJP.note.getText());
					
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
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.ipadx = 5;
		gbc.ipady = 5;
		
		gbc.gridx = 1;
		gbc.gridy = 7;
		gbc.fill = GridBagConstraints.CENTER;
		rocJP.panel.add(add, gbc);
		getContentPane().add(rocJP.panel, BorderLayout.NORTH);
		pack();
	}
	
}
