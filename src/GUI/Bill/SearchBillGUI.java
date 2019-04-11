package GUI.Bill;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import GUI.SearchGUI;
import servis.Bill;

@SuppressWarnings("serial")
public class SearchBillGUI extends SearchGUI 
{
	public SearchBillGUI() 
	{
		super.setTitle("Search bill");
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
					Bill bill = new Bill().billDB.readBill(Integer.parseInt(idTF.getText()));
					if (bill == null)
						text = "Bill with this ID dont exist!";
					else
						text = bill.informationAbout();
					result.setText(text);
				}
			}
		});
		super.setButton();
	}
}
