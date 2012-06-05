/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package statistic;

import controlcharts.ZiPChartLimits;
import data.DataSetCsvIterator;
import data.DataSetException;
import data.DataSetItem;
import data.DataSetIterate;
import java.io.File;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFileChooser;
import types.DataConverter;
import types.DoubleDataConverter;

/**
 *
 * @author saul
 */
public class ZiPStatistic implements GenericStatistic {
   //     private Double constante;
	private ZiPChartLimits chartLimits;
     //  protected ArrayList<ArrayList<DataSetItem>> data;
        private double var1;
        private double p;
       private double pi;
        private double Zi;

        public ZiPStatistic(ZiPChartLimits limites)  
	{
            this.chartLimits = limites;
	}
            
            
	
	/*public ZiPStatistic(DataSetIterate data_set) throws DataSetException
	{
        	//DataConverter conversor_long = new DoubleDataConverter();
        //DataSetIterator data_set = new DataSetCsvIterator(arquivo,conversor_long,false,null);
        
        int cont = 0;
        VPStatistic calculador_P = new VPStatistic();
        Integer tamanho_amostra = null;
        ArrayList<DataSetItem> dados_para_media = new ArrayList<DataSetItem>(this.data.size());
        while(!data_set.isEmpty())
        {
        	cont++;
        	ArrayList<DataSetItem> item = data_set.next();
                Double desvio = calculador_P.generateStatistic(item);
		DataSetItem novo_item = new DataSetItem();
		novo_item.setX(0.0);
		novo_item.setY(desvio);
		dados_para_media.add(novo_item);
        	
        }//fim-while
              AverageStatistic calculador_media = new AverageStatistic();
                pi = calculador_media.generateStatistic(dados_para_media);
                 //return retorno1;
        }*/
       
	public Double generateStatistic(ArrayList<DataSetItem> sample) 
	{
		if(sample==null)
		{
			return null;
		}        
        //for(Iterator<DataSetItem> it = sample.iterator();it.hasNext();)
                int tamanho = sample.size();
		//AverageStatistic gerador_media = new AverageStatistic();
		//Double media = gerador_media.generateStatistic(sample);
		Double soma_p = (double)0;
		for(int cont=0;cont<tamanho;cont++)
		{
			DataSetItem sample_part = sample.get(cont);
			Double temp = sample_part.getY();
                        if(cont==0){
                         var1= temp;     
                        }
                        else{
			soma_p += temp;
                        }
		}
                p = soma_p/var1;
              // ZiPChartLimits novo = new ZiPChartLimits();
                //this.pi = novo.calculateAverageP();
           //   pi = this.chartLimits.getAverageP();
                pi = this.chartLimits.calculateAverageP();
               
               Zi = ((p-pi)/Math.sqrt(((pi*(1-pi))/var1)));
		//return ((p-pi)/Math.sqrt(((pi*(1-pi))/var1)));
                return Zi;
}
      /* public void pegaPora() throws DataSetException 
	{
		JFileChooser chooser = new JFileChooser();
		chooser.showOpenDialog(null);
		File arquivo = chooser.getSelectedFile();

		if(arquivo==null)
		{
			System.out.println("Arquivo null");;
		}
		DataConverter conversor_long = new DoubleDataConverter();
		DataSetIterate data_set = new DataSetCsvIterator(arquivo,conversor_long,false,null);
                ZiPStatistic statistica_teste = new ZiPStatistic(data_set);
               
}*/
}
