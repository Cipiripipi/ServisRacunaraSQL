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
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import servis.InformationAbout;

@SuppressWarnings("serial")
public class ListOf extends JFrame 
{
	protected JTextArea result = new JTextArea();
	protected JComboBox<? extends InformationAbout> list = new JComboBox<>();
	protected JButton showAll = new JButton("SHOW ALL");
	
	public ListOf (JComboBox<? extends InformationAbout> list)
	{
		super("List of ");
		this.list = list;
		setWindowListOf();
		setLabelAndField();
	}
	
	private void setWindowListOf() 
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
		list.addActionListener(new ActionListener() 
		{
			
			@Override
			public void actionPerformed(ActionEvent e) 
			{
				if (list.getSelectedItem() != null)
					result.setText(list.getItemAt(list.getSelectedIndex()).informationAbout());
				else
					result.setText("");
			}
		});
		panel.add(list, gbc);
		
		gbc.gridx = 0;
		gbc.gridy = 2;
		gbc.fill = GridBagConstraints.HORIZONTAL;
		gbc.insets = new Insets(10, 5, 10, 5); 
		result.setMinimumSize(new Dimension(450, 300));
		result.setEditable(false);
		//result.setPreferredSize(new Dimension(450, 300));
		//panel.add(result, gbc);
		
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
				for (int i = 0; i < list.getItemCount(); i++) 
				{
					if (list.getItemAt(i) != null)
						all += list.getItemAt(i).informationAbout() + "\n";
				}
			result.setText(all);	
			}
		});
		panel.add(showAll, gbc);
		
		getContentPane().add(panel, BorderLayout.NORTH);
		pack();
	}
}
