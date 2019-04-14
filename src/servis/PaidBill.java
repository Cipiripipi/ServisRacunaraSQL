package servis;

public enum PaidBill 
{
	NULL 
	{
		@Override
		public String toString() 
		{
			return "";
		}
	},
	PAID 
	{
		@Override
		public String toString() 
		{
			return "PAID";
		}
	},
	NOT_PAID 
	{
		@Override
		public String toString() 
		{
			return "NOT PAID";
		}
	},
}
