package app;

import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;

import servis.Bill;
import servis.Computer;
import servis.Komitent;
import servis.MethodsForROS;
import servis.RecordOfServis;
import servis.Serviser;
import servis.StatusOfServis;
import servis.TypeOfComputer;
import util.ArrayListRecordOfServis;

@SuppressWarnings("unused")
public class App {

	public static void main(String[] args) 
	{
		//kreiranje
//		Computer comp = new Computer(TypeOfComputer.Desktop, "Simens", "S110", "54646-564", "");
//		Komitent komitent = new Komitent("Miki", "065/68-895-568");
//		Bill bill = new Bill("Ocistiti od virusa", 2000, false);
		//ucitavanje istih
		Computer comp = new Computer().computerDB.readComputer(16);
		Komitent komitent = new Komitent().komitentDB.readKomitent(13);
		Bill bill = new Bill().billDB.readBill(12);
		Serviser serviser = new Serviser().serviserDB.readServiser(5);
		
//		RecordOfServis ros = new RecordOfServis().recordOfServisDB.readRecordOfServis(22);
//		ros.pickMethod.changeStatusOfServis(ros, StatusOfServis.Finished);
		
		
		//svi servisi
		ArrayListRecordOfServis<RecordOfServis> list = new RecordOfServis().recordOfServisDB.readAllRecordsOfServis();
		list.sort(ArrayListRecordOfServis.sortByServicePrice);
		list.print();
		
		//placeni servisi
		//ArrayListRecordOfServis<RecordOfServis> list = new RecordOfServis().recordOfServisDB.readRecordsOfServisPaid(true);
		//list.print();
	}

}
