package util;

import java.util.ArrayList;
import java.util.Comparator;

import servis.RecordOfServis;

@SuppressWarnings("serial")
public class ArrayListRecordOfServis<E> extends ArrayList<E> 
{
	
	public void print()
	{
		int n = 1;
		for (int i = 0; i < this.size(); i++)
			System.out.println("Record of servis: " + n++ +"/" + this.size() + "\n" + this.get(i));
		
	}
	
	//sort po datumu, po statusu, po tipu racunara, po iznosu racuna
	public static Comparator<RecordOfServis> sortByDateOfReciept = new Comparator<RecordOfServis>() 
	{
		@Override
		public int compare(RecordOfServis o1, RecordOfServis o2) 
		{
			return o1.getDateOfReciept().compareTo(o2.getDateOfReciept());
		}
	};
	
	public static Comparator<RecordOfServis> sortByDateOfReturn = new Comparator<RecordOfServis>() 
	{
		@Override
		public int compare(RecordOfServis o1, RecordOfServis o2) 
		{
			return o1.getDateOfReturn().compareTo(o2.getDateOfReturn());
		}
	};
	
	public static Comparator<RecordOfServis> sortByStatus = new Comparator<RecordOfServis>() 
	{
		@Override
		public int compare(RecordOfServis o1, RecordOfServis o2) 
		{
			return o1.getStatusOfServis().compareTo(o2.getStatusOfServis());
		}
	};
	
	public static Comparator<RecordOfServis> sortByTypeOfComputer = new Comparator<RecordOfServis>() 
	{
		@Override
		public int compare(RecordOfServis o1, RecordOfServis o2) 
		{
			return o1.getComputer().getTypeOfComputer().compareTo(o2.getComputer().getTypeOfComputer());
		}
	};
	
	public static Comparator<RecordOfServis> sortByServicePrice = new Comparator<RecordOfServis>() 
	{
		@Override
		public int compare(RecordOfServis o1, RecordOfServis o2) 
		{
			Integer int1 = (int) o1.getBill().getPriceOfServis();
			Integer int2 = (int) o2.getBill().getPriceOfServis();
			return int1.compareTo(int2);
		}
	};
}
