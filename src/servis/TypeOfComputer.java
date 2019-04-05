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
	}
}
