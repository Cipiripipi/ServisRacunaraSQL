package servis;

import java.time.LocalDate;

public class MethodsForROS
{
	/**
	 * 
	 * @param recordOfServis
	 * @param statusOfServis 
	 * @return change status for recordOfServis and return feedback. You can set devices to TakenOver only if service is paid!
	 */
	public String changeStatusOfServis (RecordOfServis recordOfServis, StatusOfServis statusOfServis)
	{
		String text = "";
		
		if (statusOfServis == StatusOfServis.TakenOver)
			if (recordOfServis.getBill() != null && recordOfServis.getBill().isPaid() == true)
			{
				recordOfServis.setDateOfReturn(java.sql.Date.valueOf(LocalDate.now()));
				text = "Devices status is set to TakenOver";
			}
			else
				text = "Service for this device isn't paid, you cant't set status to TakenOver!!";
		else
		{
			recordOfServis.setStatusOfServis(statusOfServis);
			text = "Devices status is set to " + statusOfServis.toString();
		}
		recordOfServis.recordOfServisDB.updateRecordOfServis(recordOfServis);
		return text;		
	}
	
	/**
	 * 
	 * @param recordOfServis
	 * @param serviser
	 * @return
	 */
	public String addServiserForRecordOfServis(RecordOfServis recordOfServis, Serviser serviser)
	{
		recordOfServis.setServiser(serviser);
		recordOfServis.recordOfServisDB.updateRecordOfServis(recordOfServis);
		return "You add serviser";
	}
	
	/**
	 * 
	 * @param recordOfServis
	 * @param newNoteOfDefect
	 * @return
	 */
	public String changeNoteOfDefect (RecordOfServis recordOfServis, String newNoteOfDefect)
	{
		String text = "";
		String note = recordOfServis.getNoteOfDefect() + "\n" + newNoteOfDefect;
		recordOfServis.setNoteOfDefect(note);
		text = "You change note of defect";
		recordOfServis.recordOfServisDB.updateRecordOfServis(recordOfServis);
		return text;
	}
	
	/**
	 * 
	 * @param recordOfServis
	 * @param bill
	 * @return
	 */
	public String addBill(RecordOfServis recordOfServis, Bill bill)
	{
		String text = "";
		
		if (recordOfServis.getBill() == null)
		{
			recordOfServis.setBill(bill);
			recordOfServis.recordOfServisDB.updateRecordOfServis(recordOfServis);
			text = "Bill is added to RecordOfServis";
		}
		else
			text = "This RecordOfServis already have bill, please change that bill";
		
		return text;
	}
	
	/**
	 * 
	 * @param recordOfServis
	 * @param newBillNote
	 * @return
	 */
	public String changeBillNote(RecordOfServis recordOfServis, String newBillNote)
	{
		String text = "";
		String note = recordOfServis.getBill().getNote() + "\n" + newBillNote;
		recordOfServis.getBill().setNote(note);
		text = "You change bill note";
		recordOfServis.getBill().billDB.updateBill(recordOfServis.getBill());
		return text;
	}
	
	/**
	 * 
	 * @param recordOfServis
	 * @param price
	 * @return
	 */
	public String setBillPrice(RecordOfServis recordOfServis, double price)
	{
		String text = "";
		if(recordOfServis.getBill() != null)
		{
			recordOfServis.getBill().setPriceOfServis(price);
			text = "You set bill price to " + price;
			recordOfServis.getBill().billDB.updateBill(recordOfServis.getBill());
		}
		else
			text = "This RecordOfServis doesn't have added bill. You must first add bill!";
		
		return text;
	}
	
	/**
	 * 
	 * @param recordOfServis
	 * @return
	 */
	public String setBillToPaid(RecordOfServis recordOfServis)
	{
		String text = "";
		recordOfServis.getBill().setPaid(true);
		text = "You set this bill to paid";
		recordOfServis.getBill().billDB.updateBill(recordOfServis.getBill());
		return text;
	}
}
