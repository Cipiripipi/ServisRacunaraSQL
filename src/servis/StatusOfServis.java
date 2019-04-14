package servis;

public enum StatusOfServis 
{
	NULL 
	{
		@Override
		public String toString() 
		{
			return "";
		}
	},
	Reception 
	{
		@Override
		public String toString() 
		{
			return "Reception";
		}
	},
	Diagnostic
	{
		@Override
		public String toString() 
		{
			return "Diagnostic";
		}
	},
	WaitingForThePart
	{
		@Override
		public String toString() 
		{
			return "WaitingForThePart";
		}
	},
	Finished
	{
		@Override
		public String toString() 
		{
			return "Finished";
		}
	},
	TakenOver
	{
		@Override
		public String toString() 
		{
			return "TakenOver";
		}
	}
}
