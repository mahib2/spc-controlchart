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
public class nPStatistic implements GenericStatistic{
    
	private double var2;
	public nPStatistic()
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
		
		Double soma_p = (double)0;
		for(int cont=0;cont<tamanho;cont++)
		{
			DataSetItem sample_part = sample.get(cont);
			Double temp = sample_part.getY();
			 if(cont==0){
                         var2= temp;     
                        }
                        else{
			soma_p += temp;
                        }
		}
		
		return soma_p;
		
}
        public double getVar2(){
            return var2;
        }
    
}
