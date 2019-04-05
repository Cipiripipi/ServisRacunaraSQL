package GUI;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import servis.RecordOfServis;

@SuppressWarnings("serial")
public class SearchRecordOfServisGUI extends SearchGUI 
{
	public SearchRecordOfServisGUI() 
	{
		super.setTitle("Search record of service");
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
					RecordOfServis record = new RecordOfServis().recordOfServisDB.readRecordOfServis(Integer.parseInt(idTF.getText()));
					if (record == null)
						text = "Record of service with this ID dont exist!";
					else
						text = record.toString();
					result.setText(text);
				}
			}
		});
		super.setButton();
	}
}
