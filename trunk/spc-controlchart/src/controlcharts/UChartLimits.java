/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package controlcharts;

import data.DataSetItem;
import java.util.ArrayList;
import java.util.Iterator;
import statistic.AverageStatistic;
import statistic.UStatistic;


/**
 *
 * @author saul
 */
public class UChartLimits extends GenericChartLimitsBase{
       private Double term1; 
     private Double term2;
   //  private int cont3 = 0;
     private Double Um;
     
        //  double constante;
     public UChartLimits(){
      
     }

    @Override
    protected Double calculateTerm1() {
       
		if(this.term1==null)
		{
			this.term1 = this.calculateAverageP(); 						
			
		}
		return term1; 
    }

    @Override
    protected Double calculateTerm2() {
       if(this.term2==null)
		{
                Double c= this.calculateAverageP();
                double u = this.Um;
		this.term2 =(Math.sqrt(c/u));		
		}
		return this.term2;
    }

    @Override
    public void addData(ArrayList<DataSetItem> new_data) {
       this.data.add(new_data);
    }

    @Override
    public void setSampleSize(Integer size) {
        this.sample_size = size;
		this.init(size);
    }
    private Double calculateAverageP()
	{
                
		UStatistic calculador_P = new UStatistic();
               
		ArrayList<DataSetItem> dados_para_media = new ArrayList<DataSetItem>(this.data.size());
                
		for(Iterator<ArrayList<DataSetItem>> it = this.data.iterator();it.hasNext();)
		{
                  //  cont3++;
			ArrayList<DataSetItem> amostra = it.next();
			Double desvio = calculador_P.generateStatistic(amostra);
                        Um = calculador_P.getVar1();
			DataSetItem novo_item = new DataSetItem();
			novo_item.setX(0.0);
			novo_item.setY(desvio);
			dados_para_media.add(novo_item);
		}
		AverageStatistic calculador_media = new AverageStatistic();
		Double retorno = calculador_media.generateStatistic(dados_para_media);
		return retorno;
	}
}
