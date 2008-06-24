package statistic;

import java.io.File;
import java.util.ArrayList;

import org.apache.commons.math.stat.regression.*;

import javax.swing.JFileChooser;

import data.DataSetCsvIterator;
import data.DataSetException;
import data.DataSetItem;
import data.DataSetIterator;
import data.types.DataConverter;
import data.types.DoubleDataConverter;

public class RegressionStatistic implements GenericStatistic 
{
	SimpleRegression regressao = new SimpleRegression();
	
	/**
	 * Deve-se evitar usar ese contrutor, pois se usado, o obejto não terá nenhuma funcionalidade de regressao
	 */
	public RegressionStatistic()
	{
		
	}
	
	public RegressionStatistic(DataSetIterator data_set) throws DataSetException
	{
		//DataConverter conversor_long = new DoubleDataConverter();
        //DataSetIterator data_set = new DataSetCsvIterator(arquivo,conversor_long,false,null);
        int cont = 0;
        
        Integer tamanho_amostra = null;
        while(!data_set.isEmpty())
        {
        	cont++;
        	ArrayList<DataSetItem> item = data_set.next();
        	int tamanho = item.size();
        	if(tamanho_amostra==null)
        	{
        		double x = 0;
        		double y = 0;
        		for(int cont2=0;(cont2<2 && cont2<tamanho);cont2++)
        		{
        			DataSetItem sample_part = item.get(cont2);
        			if(cont2==0)
        			{
        				x = sample_part.getY();
        			}
        			else
        			{
        				y = sample_part.getY();
        			}
        		}//fim-for
        		this.regressao.addData(x, y);
        	}//fim-if
        }//fim-while
	}//fim-RegressionStatistic
	
	
	
	public SimpleRegression getRegressao() {
		return this.regressao;
	}

	/**
	 * Esse metodo assume que so tem 1 elemento no array
	 * pega o x e joga na função para calcular o Y
	 */
	
	public Double generateStatistic(ArrayList<DataSetItem> sample) 
	{	
		if(sample!=null && this.regressao!=null)
		{
			if(sample.size()>0)
			{
				Double parametro = sample.get(0).getX();
				return this.regressao.predict(parametro);
			}
		}
		return new Double(0);
	}

	public static void main(String[] args) throws DataSetException 
	{
		/*JFileChooser chooser = new JFileChooser();
		chooser.showOpenDialog(null);*/
		//File arquivo = chooser.getSelectedFile();
		File arquivo = new File("dados_teste.csv");

		if(arquivo==null)
		{
			System.out.println("Arquivo null");;
		}
		DataConverter conversor_long = new DoubleDataConverter();
		DataSetIterator data_set = new DataSetCsvIterator(arquivo,conversor_long,false,null);
		GenericStatistic statistica_teste = new RegressionStatistic(data_set);
		int cont = 0;
		DataSetIterator data_set2 = new DataSetCsvIterator(arquivo,conversor_long,false,null);
		while(!data_set.isEmpty())
		{
			cont++;
			ArrayList<DataSetItem> item = data_set.next();
			ArrayList<DataSetItem> array_parametro = new ArrayList<DataSetItem>(1);
			DataSetItem item_parametro = new DataSetItem();
			item_parametro.setX(new Double(cont));
			array_parametro.add(item_parametro);
			Number estatistica = statistica_teste.generateStatistic(array_parametro);
			System.out.println("Regressão: "+estatistica);

		}
	}
}	

