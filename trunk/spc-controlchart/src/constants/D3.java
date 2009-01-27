package constants;

import java.util.HashMap;

public class D3 
{
	private static HashMap<Integer, Double> tabela;
	
	public static double calculate(int tamanho_amostra)
	{
		if(tamanho_amostra<2)
		{
			return 0;
		}
		Double retorno = D3.getTableValue(tamanho_amostra);
		if(retorno==null)
		{
			return 0;
		}
		return retorno;
	}
	
	private static Double getTableValue(Integer valor)
	{
		if(D3.tabela == null)
		{
			D3.initTable();
		}
		return D3.tabela.get(valor);
	}
	
	private static void initTable()
	{
		D3.tabela = new HashMap<Integer, Double>(50);
		D3.tabela.put(2,0.853);
		D3.tabela.put(3,0.888);
		D3.tabela.put(4,0.880);
		D3.tabela.put(5,0.864);
		D3.tabela.put(6,0.848);
		D3.tabela.put(7,0.833);
		D3.tabela.put(8,0.820);
		D3.tabela.put(9,0.808);
		D3.tabela.put(10,0.797);
		D3.tabela.put(11,0.787);
		D3.tabela.put(12,0.778);
		D3.tabela.put(13,0.770);
		D3.tabela.put(14,0.763);
		D3.tabela.put(15,0.756);
		D3.tabela.put(16,0.750);
		D3.tabela.put(17,0.744);
		D3.tabela.put(18,0.739);
		D3.tabela.put(19,0.733);
		D3.tabela.put(20,0.729);
		D3.tabela.put(21,0.724);
		D3.tabela.put(22,0.720);
		D3.tabela.put(23,0.716);
		D3.tabela.put(24,0.712);
		D3.tabela.put(25,0.708);
	}

}
