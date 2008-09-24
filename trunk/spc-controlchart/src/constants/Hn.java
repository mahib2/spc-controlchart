package constants;

import math.BetaFunction;

public class Hn 
{
	public static double calculate(int tamanho_amostra)
	{
		if(tamanho_amostra<2)
		{
			return 0;
		}
		double parte_1 = Math.PI;
		double parte_2 = (2 * tamanho_amostra);
		return Math.sqrt(parte_1/parte_2);
	}
	
	public static void main(String[] args) 
	{
		System.out.println(Hn.calculate(2));

	}

}
