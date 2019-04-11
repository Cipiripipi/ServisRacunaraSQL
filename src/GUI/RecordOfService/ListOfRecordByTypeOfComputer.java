package GUI.RecordOfService;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import GUI.LoadInCompoBox;
import servis.RecordOfServis;
import servis.Serviser;
import servis.TypeOfComputer;

@SuppressWarnings("serial")
public class ListOfRecordByTypeOfComputer extends JFrame 
{
	Serviser s = null;
	private JTextArea result = new JTextArea();
	private JComboBox<TypeOfComputer> listType = LoadInCompoBox.loadTypeOfComputer();
	private JButton showAll = new JButton("SHOW ALL");
	private JComboBox<RecordOfServis> listResult = new JComboBox<>();
	
	
	public ListOfRecordByTypeOfComputer() 
	{
		super("List of record by Serviser");
		setWindowListOfRecordBy();
		setLabelAndField();
	}
	
	private void setWindowListOfRecordBy() 
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
	
	private void setLabelAndField ()
	{
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.ipadx = 5;
		gbc.ipady = 5;
		
		JPanel panel = new JPanel();
		panel.setLayout(new GridBagLayout());
		gbc.insets = new Insets(10, 5, 10, 5); 
		
		gbc.gridx = 0;
		gbc.gridy = 0;
		gbc.fill = GridBagConstraints.HORIZONTAL;
		listType.addActionListener(new ActionListener() 
		{
			@Override
			public void actionPerformed(ActionEvent e) 
			{
				
				listResult.removeAllItems();
				if (listType.getSelectedItem() != null)
				{
					TypeOfComputer tof = (TypeOfComputer) listType.getSelectedItem();
					ArrayList<RecordOfServis> lists = new RecordOfServis().recordOfServisDB.readRecordsOfServisByTypeOfComputer(tof);
					lists.add(0, null);
					for (RecordOfServis ros : lists) 
						listResult.addItem(ros);
				}
			}
		});
		
		panel.add(listType, gbc);
		
		gbc.gridx = 0;
		gbc.gridy = 1;
		gbc.fill = GridBagConstraints.HORIZONTAL;
		listResult.addActionListener(new ActionListener() 
		{
			@Override
			public void actionPerformed(ActionEvent e) 
			{
				if (listResult.getSelectedItem() != null)
					result.setText(listResult.getItemAt(listResult.getSelectedIndex()).informationAbout());
				else
					result.setText("");
			}
		});
		
		panel.add(listResult, gbc);
		
		gbc.gridx = 0;
		gbc.gridy = 2;
		gbc.fill = GridBagConstraints.HORIZONTAL;
		gbc.insets = new Insets(10, 5, 10, 5); 
		result.setMinimumSize(new Dimension(450, 300));
		result.setEditable(false);
		
		JScrollPane scroll = new JScrollPane(result);
		scroll.getViewport().setPreferredSize(new Dimension(450, 300));
        scroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        panel.add(scroll, gbc);
		
		gbc.gridx = 0;
		gbc.gridy = 3;
		gbc.fill = GridBagConstraints.HORIZONTAL;
		gbc.insets = new Insets(10, 150, 10, 150); 
		showAll.setPreferredSize(new Dimension(40, 40));
		showAll.addActionListener(new ActionListener() 
		{
			@Override
			public void actionPerformed(ActionEvent e) 
			{
				String all = "";
				for (int i = 0; i < listResult.getItemCount(); i++) 
				{
					if (listResult.getItemAt(i) != null)
						all += listResult.getItemAt(i).informationAbout() + "\n";
				}
			result.setText(all);	
			}
		});
		panel.add(showAll, gbc);
		
		getContentPane().add(panel, BorderLayout.NORTH);
		pack();
	}
}
