package math;

public class Factorial 
{
	public static double calculate(int numero)
	{
		if(numero<0)
		{
			return 1;
		}
		else
		{
			if(numero==0)
			{
				return 1;
			}
			else
			{
				return Factorial.calculate(numero-1)*numero;
			}
		}
	}
}
