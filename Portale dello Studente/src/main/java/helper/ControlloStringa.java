package helper;

public class ControlloStringa {
	
	public static boolean seCfu(String s)	{
		if(s.isEmpty())
			return false;
		try {
			if(s.charAt(0)=='0')
				return false;
			Integer.valueOf(s);
		}
		catch(NumberFormatException e)	{
			return false;
		}
		return true;
	}
	
	public static boolean seMatricola(String s)	{
		if(s.isEmpty())
			return false;
		try {
			Integer.valueOf(s);
		}
		catch(NumberFormatException e)	{
			return false;
		}
		return true;
	}

}

