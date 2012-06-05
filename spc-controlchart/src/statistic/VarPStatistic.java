/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package statistic;

import data.DataSetItem;
import java.util.ArrayList;

/**
 *
 * @author saul
 */
public class VarPStatistic implements GenericStatistic{
     private double var1;
	public VarPStatistic()
	{
				
	}
        
    
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
		
		return soma_p/var1;
}
        public double getVar1(){
            
        return var1;
        }
}
