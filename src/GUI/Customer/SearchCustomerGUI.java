package GUI.Customer;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import GUI.SearchGUI;
import servis.Customer;

@SuppressWarnings("serial")
public class SearchCustomerGUI extends SearchGUI 
{
	public SearchCustomerGUI() 
	{
		super.setTitle("Search customer");
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
					Customer customer = new Customer().customerDB.readCustomer(Integer.parseInt(idTF.getText()));
					if (customer == null)
						text = "Customer with this ID dont exist!";
					else
						text = customer.informationAbout();
					result.setText(text);
				}
			}
		});
		super.setButton();
	}
}
