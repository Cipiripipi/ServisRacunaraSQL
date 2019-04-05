package app;

import GUI.ServiceGUI;

public class App {

	public static void main(String[] args) 
	{
		ServiceGUI gui = new ServiceGUI();
		System.out.println(gui.getClass().getSimpleName());
	}

}
