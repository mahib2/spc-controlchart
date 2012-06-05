/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package controlcharts;

import data.DataSetItem;
import java.util.ArrayList;
import java.util.Iterator;
import statistic.AverageStatistic;
import statistic.VarianceStatistic;

/**
 *
 * @author saul
 */
public class VarianceChartLimits extends GenericChartLimitsBase{
     private Double term1; 
     private Double term2;
     
     public VarianceChartLimits(){
     
     }

    @Override
    protected Double calculateTerm1() {
       
		if(this.term1==null)
		{
			this.term1 = this.calculateAverageVariance(); 						
			
		}
		return term1; 
    }

    @Override
    protected Double calculateTerm2() {
       if(this.term2==null)
		{
                double n = (double)this.sample_size;
		Double S_barra2 = this.calculateAverageVariance();
		this.term2 =((S_barra2)*(Math.sqrt((2)/(n-1))));		
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
    private Double calculateAverageVariance()
	{
		VarianceStatistic calculador_variance = new VarianceStatistic(true);
		ArrayList<DataSetItem> dados_para_media = new ArrayList<DataSetItem>(this.data.size());
		for(Iterator<ArrayList<DataSetItem>> it = this.data.iterator();it.hasNext();)
		{
			ArrayList<DataSetItem> amostra = it.next();
			Double desvio = calculador_variance.generateStatistic(amostra);
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
