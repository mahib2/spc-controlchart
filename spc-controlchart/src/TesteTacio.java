import java.io.File;
import java.util.ArrayList;

import javax.swing.JFileChooser;

import math.constants.Cn;

import controlcharts.GenericChartLimits;
import controlcharts.StandardDeviationChartLimits;

import statistic.AverageStatistic;
import statistic.GenericStatistic;
import statistic.StandardDeviationStatistic;

import data.DataSetCsvIterator;
import data.DataSetException;
import data.DataSetItem;
import data.DataSetIterator;
import data.types.DataConverter;
import data.types.DoubleDataConverter;

public class TesteTacio {

	/**
	 * @param args
	 * @throws DataSetException 
	 */
	public static void main(String[] args) throws DataSetException 
	{
		JFileChooser chooser = new JFileChooser();
        chooser.showOpenDialog(null);
        File arquivo = chooser.getSelectedFile();
        
        if(arquivo==null)
        {
        	return;
        }
        
        DataConverter conversor_long = new DoubleDataConverter();
        DataSetIterator data_set = new DataSetCsvIterator(arquivo,conversor_long,false,null);
        GenericChartLimits limites_controle = null;
        while(!data_set.isEmpty())
        {
        	
        	ArrayList<DataSetItem> item = data_set.next();
        	GenericStatistic statistica_teste = new StandardDeviationStatistic(true);
        	Double estatistica = statistica_teste.generateStatistic(item);
        	if(limites_controle==null)
        	{
        		System.out.print("Cn=");
        		System.out.println(Cn.calculate(item.size()));
        		limites_controle = new StandardDeviationChartLimits(item.size());
        	}
        	limites_controle.addData(item);
        	System.out.println(estatistica);
            System.out.println("------------------------");
        }
        System.out.print("LSC=");
        System.out.println(limites_controle.calculateUpperControlLimit());
        System.out.print("LSA=");
        System.out.println(limites_controle.calculateUpperAdvertenceLimit());
        System.out.print("LC=");
        System.out.println(limites_controle.calculateCentralLine());
        System.out.print("LIA=");
        System.out.println(limites_controle.calculateLowerAdvertenceLimit());
        System.out.print("LIC=");
        System.out.println(limites_controle.calculateLowerControlLimit());
	}

}
