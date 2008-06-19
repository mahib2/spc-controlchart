package math;

public class BetaFunction {

	/**
	 * Para valores inteiros
	 * @param number
	 * @return
	 */
	
	public static double calculate(int number)
	{
		return BetaFunction.calculate(number,1);
	}
	
	/**
	 * So esta preparado para algum valor sobre 2, ou valores inteiros.
	 * @param fraction_p1
	 * @param fraction_p2
	 * @return
	 */
	public static double calculate(int fraction_p1, int fraction_p2)
	{
		/***
		 * Ver primeiro se a divisao e exata
		 */
		int resto = fraction_p1 % fraction_p2;
		
		if(resto==0)
		{
			fraction_p1 = fraction_p1/fraction_p2;
			fraction_p2 = 1;
		}
			
		/**
		 * Calculo da funcao beta
		 */	
		
		if((fraction_p1==1)&&(fraction_p2==2))
		{
			return Math.sqrt(Math.PI);			
		}
		if((fraction_p1>2)&&(fraction_p2==2))
		{
			int fraction_p1_new = fraction_p1 - 2;
			double p1_double = (double)fraction_p1_new;
			double p2_double = (double)fraction_p2;
			return BetaFunction.calculate(fraction_p1_new,fraction_p2)*(p1_double/p2_double);
		}
		if(fraction_p2==1)
		{
			return Factorial.calculate(fraction_p1 - 1);
		}
		//Ver melhor isso aqui
		return 0;
			
	}
	
	
	public static void main(String[] args) 
	{
		System.out.println(BetaFunction.calculate(3,2));

	}

}
