package ui;
import java.io.File;
import java.util.ArrayList;

import javax.swing.JFileChooser;


import constants.Cn;
import controlcharts.GenericChartLimits;
import controlcharts.StandardDeviationChartLimits;

import statistic.AverageStatistic;
import statistic.GenericStatistic;
import statistic.StandardDeviationStatistic;
import types.DataConverter;
import types.DoubleDataConverter;

import data.DataSetCsvIterator;
import data.DataSetException;
import data.DataSetItem;
import data.DataSetIterate;

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
        DataSetIterate data_set = new DataSetCsvIterator(arquivo,conversor_long,false,null);
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
        System.out.println(limites_controle.calculateUpperControlLimit(null));
        System.out.print("LSA=");
        System.out.println(limites_controle.calculateUpperAdvertenceLimit(null));
        System.out.print("LC=");
        System.out.println(limites_controle.calculateCentralLine(null));
        System.out.print("LIA=");
        System.out.println(limites_controle.calculateLowerAdvertenceLimit(null));
        System.out.print("LIC=");
        System.out.println(limites_controle.calculateLowerControlLimit(null));
	}

}
