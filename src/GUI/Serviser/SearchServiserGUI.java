package GUI.Serviser;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import GUI.SearchGUI;
import servis.Serviser;

@SuppressWarnings("serial")
public class SearchServiserGUI extends SearchGUI 
{
	public SearchServiserGUI() 
	{
		super.setTitle("Search serviser");
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
					Serviser serviser = new Serviser().serviserDB.readServiser(Integer.parseInt(idTF.getText()));
					if (serviser == null)
						text = "Serviser with this ID dont exist!";
					else
						text = serviser.toString();
					result.setText(text);
				}
			}
		});
		super.setButton();
	}
}
