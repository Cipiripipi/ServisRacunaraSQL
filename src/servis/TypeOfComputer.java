package servis;

public enum TypeOfComputer 
{
	NULL 
	{
		@Override
		public String toString() 
		{
			return "";
		}
	},
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
