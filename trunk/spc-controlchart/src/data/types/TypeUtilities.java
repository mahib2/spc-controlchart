package data.types;

public class TypeUtilities 
{
	public static Double sumNumbers(Double number1, Double number2)
	{
		
		if(number1==null)
		{
			number1 = new Double(0);
		}
		if(number2==null)
		{
			number2 = new Double(0);
		}
		Double numero1 = (Double)number1;
		Double numero2 = (Double)number2;
		return numero1+numero2;
	}
	
	public static Double divideNumbers(Double number1, Double number2)
	{
		
		if(number1==null)
		{
			number1 = new Double(0);
		}
		if(number1==null)
		{
			number2 = new Double(0);
		}
		Double numero1 = (Double)number1;
		Double numero2 = (Double)number2;
		return numero1/numero2;
		
	}
	
}
