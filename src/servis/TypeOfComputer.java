package servis;

public enum TypeOfComputer 
{
	Desktop 
	{
		@Override
		public String toString() 
		{
			return "Desktop";
		}
	},
	Laptop
	{
		@Override
		public String toString() 
		{
			return "Laptop";
		}
	},
	//namerno nije ubaceno u bazu
	Tablet
	{
		@Override
		public String toString() 
		{
			return "Tablet";
		}
	}
}
