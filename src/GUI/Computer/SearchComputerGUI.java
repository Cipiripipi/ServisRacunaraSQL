package GUI.Computer;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import GUI.SearchGUI;
import servis.Computer;

@SuppressWarnings("serial")
public class SearchComputerGUI extends SearchGUI 
{
	
	public SearchComputerGUI() 
	{
		super.setTitle("Search computer");
	}

	@Override
	protected void setButton() 
	{
		search.addActionListener(new ActionListener() 
		{
			@Override
			public void actionPerformed(ActionEvent e) 
			{
				String text = "";
				if (idTF.getText().isEmpty() || !(idTF.getText().matches("^[0-9]*$")))
				{
					JOptionPane.showMessageDialog(new JFrame(), "Input ID!", "Error", JOptionPane.ERROR_MESSAGE);
					idTF.requestFocusInWindow();
				}
				else
				{
					Computer comp = new Computer().computerDB.readComputer(Integer.parseInt(idTF.getText()));
					if (comp == null)
						text = "Computer with this ID dont exist!";
					else
						text = comp.informationAbout();
					result.setText(text);
				}
			}
		});
		super.setButton();
	}
}
