package GUI;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.Toolkit;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

@SuppressWarnings("serial")
public class SearchGUI extends JFrame 
{
	JLabel inputIdL = new JLabel("Input ID: ");
	JTextField idTF = new JTextField();
	JButton search = new JButton("SEARCH");
	JTextArea result = new JTextArea();
	
	public SearchGUI ()
	{
		super("Search");
		setWindowSearch();
		setLabelAndField();
		setButton();
	}
	
	private void setWindowSearch() 
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
		inputIdL.setHorizontalAlignment(SwingConstants.CENTER);
		panel.add(inputIdL, gbc);
		
		gbc.gridx = 0;
		gbc.gridy = 1;
		gbc.fill = GridBagConstraints.HORIZONTAL;
		gbc.insets = new Insets(5, 150, 5, 150); 
		idTF.setPreferredSize(new Dimension(0, 20));
		panel.add(idTF, gbc);
		
		gbc.gridx = 0;
		gbc.gridy = 2;
		gbc.fill = GridBagConstraints.HORIZONTAL;
		gbc.insets = new Insets(5, 150, 5, 150); 
		search.setPreferredSize(new Dimension(40, 40));
		panel.add(search, gbc);
		
		gbc.gridx = 0;
		gbc.gridy = 3;
		gbc.fill = GridBagConstraints.HORIZONTAL;
		gbc.insets = new Insets(10, 5, 10, 5); 
		result.setPreferredSize(new Dimension(450, 200));
		result.setEditable(false);
		panel.add(result, gbc);
		
		getContentPane().add(panel, BorderLayout.NORTH);
		pack();
	}
	
	protected void setButton () 
	{
		
	}
}
