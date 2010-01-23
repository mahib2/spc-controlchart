package constants;

import java.util.HashMap;

public class D4 
{
	private static HashMap<Integer, Double> tabela;
	
	public static double calculate(int tamanho_amostra)
	{
		if(tamanho_amostra<2)
		{
			return 0;
		}
		Double retorno = D4.getTableValue(tamanho_amostra);
		if(retorno==null)
		{
			return 0;
		}
		return retorno;
	}
	
	private static Double getTableValue(Integer valor)
	{
		if(D4.tabela == null)
		{
			D4.initTable();
		}
		return D4.tabela.get(valor);
	}
	
	private static void initTable()
	{
		//atualizar esse valores de D3 para D4
		D4.tabela = new HashMap<Integer, Double>(50);
		D4.tabela.put(2,3.267);
		D4.tabela.put(3,2.575);
		D4.tabela.put(4,2.282);
		D4.tabela.put(5,2.115);
		D4.tabela.put(6,2.004);
		D4.tabela.put(7,1.924);
		D4.tabela.put(8,1.864);
		D4.tabela.put(9,1.816);
		D4.tabela.put(10,1.777);
		D4.tabela.put(11,1.744);
		D4.tabela.put(12,1.717);
		D4.tabela.put(13,1.693);
		D4.tabela.put(14,1.672);
		D4.tabela.put(15,1.653);
		D4.tabela.put(16,1.637);
		D4.tabela.put(17,1.622);
		D4.tabela.put(18,1.609);
		D4.tabela.put(19,1.596);
		D4.tabela.put(20,1.585);
		D4.tabela.put(21,1.575);
		D4.tabela.put(22,1.565);
		D4.tabela.put(23,1.557);
		D4.tabela.put(24,1.548);
		D4.tabela.put(25,1.541);
	}
}
