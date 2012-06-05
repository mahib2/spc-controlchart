/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package controlcharts;

import data.DataSetItem;
import java.util.ArrayList;
import java.util.Iterator;
import statistic.AverageStatistic;
import statistic.PStatistic;
import statistic.nPStatistic;

/**
 *
 * @author saul
 */
public class nPChartLimits extends GenericChartLimitsBase{
       private Double term1; 
     private Double term2;
     private Double Pega;
   //  private int cont3 = 0;
     
     private double Am2;
     
     public nPChartLimits(){
         //this.constante=constante;
     }

    @Override
    protected Double calculateTerm1() {
       
		if(this.term1==null)
		{
                    this.Pega = this.calculateAverageP(); 
                    double n1 = this.Am2;
                   // this.term1 = n1* this.calculateAverageP(); 						
		this.term1 = n1*Pega;	
		}
		return term1; 
    }

    @Override
    protected Double calculateTerm2() {
       if(this.term2==null)
		{
               // double n = (double)this.constante;
                    double n2 = this.Am2;
		Double nP = n2*this.calculateAverageP();
                Double p= this.calculateAverageP();
		this.term2 =(Math.sqrt(nP*(1-p)));		
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
                
		PStatistic calculador_P = new PStatistic();
               
		ArrayList<DataSetItem> dados_para_media = new ArrayList<DataSetItem>(this.data.size());
                
		for(Iterator<ArrayList<DataSetItem>> it = this.data.iterator();it.hasNext();)
		{
                  //  cont3++;
			ArrayList<DataSetItem> amostra = it.next();
			Double desvio = calculador_P.generateStatistic(amostra);
                        Am2 = calculador_P.getVar1();
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
