package math.constants;

import java.util.HashMap;

public class D2 
{
	private static HashMap<Integer, Double> tabela;
	
	public static double calculate(int tamanho_amostra)
	{
		if(tamanho_amostra<2)
		{
			return 0;
		}
		Double retorno = D2.getTableValue(tamanho_amostra);
		if(retorno==null)
		{
			return 0;
		}
		return retorno;
	}
	
	private static Double getTableValue(Integer valor)
	{
		if(D2.tabela == null)
		{
			D2.initTable();
		}
		return D2.tabela.get(valor);
	}
	
	private static void initTable()
	{
		D2.tabela = new HashMap<Integer, Double>(50);
		D2.tabela.put(2,1.128);
		D2.tabela.put(3,1.693);
		D2.tabela.put(4,2.059);
		D2.tabela.put(5,2.326);
		D2.tabela.put(6,2.534);
		D2.tabela.put(7,2.704);
		D2.tabela.put(8,2.847);
		D2.tabela.put(9,2.97);
		D2.tabela.put(10,3.078);
		D2.tabela.put(11,3.173);
		D2.tabela.put(12,3.258);
		D2.tabela.put(13,3.336);
		D2.tabela.put(14,3.407);
		D2.tabela.put(15,3.472);
		D2.tabela.put(16,3.532);
		D2.tabela.put(17,3.588);
		D2.tabela.put(18,3.64);
		D2.tabela.put(19,3.689);
		D2.tabela.put(20,3.735);
		D2.tabela.put(21,3.778);
		D2.tabela.put(22,3.819);
		D2.tabela.put(23,3.858);
		D2.tabela.put(24,3.895);
		D2.tabela.put(25,3.931);
	}

}
