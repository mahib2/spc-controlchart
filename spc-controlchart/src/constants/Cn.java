package constants;

import math.BetaFunction;

public class Cn 
{
	public static double calculate(int tamanho_amostra)
	{
		if(tamanho_amostra<2)
		{
			return 0;
		}
		double parte_1 = Math.pow((2/((double)(tamanho_amostra-1))),0.5);
		double parte_2 = (BetaFunction.calculate(tamanho_amostra,2)/ BetaFunction.calculate((tamanho_amostra-1),2));
		return parte_1*parte_2;
	}
	
	public static void main(String[] args) 
	{
		System.out.println(Cn.calculate(9));

	}

}
