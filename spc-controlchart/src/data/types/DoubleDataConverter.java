package data.types;

public class DoubleDataConverter implements DataConverter 
{

	public Double convertString(String string, Double valor_default)
	{
		try
		{
			return this.convertString(string);
		}
		catch (NumberFormatException exception) 
		{
			return valor_default;
		}
	}
	
	public Double convertString(String string)  throws NumberFormatException
	{
		try
		{
			return Double.parseDouble(string);
		}
		catch(NumberFormatException e)
		{
			String string2 = string.replace(',','.');
			try
			{
				return Double.parseDouble(string2);				
			}
			catch(NumberFormatException e1)
			{
				throw e;
			}
		}
	}

}
