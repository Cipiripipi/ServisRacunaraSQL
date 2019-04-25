package GUI.Computer;

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

import servis.Computer;

@SuppressWarnings("serial")
public class AddComputerGUI extends JFrame 
{

	JButton add = new JButton("add computer");
	Computer comp;
	ComputerJPanel compJp;

	public AddComputerGUI () 
	{
		super("ADD NEW COMPUTER");
		setWindowAddComputer();
		this.compJp = new ComputerJPanel();
		setLabelAndField();
		setButton(add);
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
	
	private void setButton (JButton add)
	{
		add.addActionListener(new ActionListener() 
		{
			@Override
			public void actionPerformed(ActionEvent e) 
			{
				if (compJp.typeOfComputer.getSelectedValue() == null)
				{
					JOptionPane.showMessageDialog(new JFrame(), "You select type of computer!", "Error", JOptionPane.ERROR_MESSAGE);
					compJp.typeOfComputer.requestFocusInWindow();
				}
				else if (compJp.brand.getText().isEmpty())
				{
					JOptionPane.showMessageDialog(new JFrame(), "You don't input brand!", "Error", JOptionPane.ERROR_MESSAGE);
					compJp.brand.requestFocusInWindow();
				}
				else if (compJp.model.getText().isEmpty())
				{
					JOptionPane.showMessageDialog(new JFrame(), "You don't input model!", "Error", JOptionPane.ERROR_MESSAGE);
					compJp.model.requestFocusInWindow();
				}
				else if (compJp.serialNumber.getText().isEmpty())
				{
					JOptionPane.showMessageDialog(new JFrame(), "You don't input serial number!", "Error", JOptionPane.ERROR_MESSAGE);
					compJp.serialNumber.requestFocusInWindow();
				}
				else
				{
					comp = new Computer(compJp.typeOfComputer.getSelectedValue(), compJp.brand.getText(), compJp.model.getText(), compJp.serialNumber.getText(), compJp.note.getText());
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
	
	public void setLabelAndField ()
	{
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.ipadx = 5;
		gbc.ipady = 5;
		
		gbc.gridx = 1;
		gbc.gridy = 6;
		gbc.fill = GridBagConstraints.CENTER;
		compJp.panel.add(add, gbc);
		getContentPane().add(compJp.panel, BorderLayout.NORTH);
		pack();
	}

	public Computer getComp() {return comp;}
}
