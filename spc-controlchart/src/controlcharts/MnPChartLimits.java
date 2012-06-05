/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package controlcharts;

import data.DataSetItem;
import java.util.ArrayList;
import java.util.Iterator;
import statistic.AverageNGP;
import statistic.AverageStatistic;
import statistic.VPStatistic;

/**
 *
 * @author saul
 */
public class MnPChartLimits extends GenericChartLimitsBase{
     private Double term1; 
     private Double term2;
   //  private int cont3 = 0;
     
  //   private double constante;
     
     public MnPChartLimits(){
         
     }

    @Override
    protected Double calculateTerm1() {
       
		if(this.term1==null)
		{
			this.term1 = this.calculateAverageP()*this.calculateAverageNMP(); 						
			
		}
		return term1; 
    }

    @Override
    protected Double calculateTerm2() {
       if(this.term2==null)
		{
		Double Pbar = this.calculateAverageP();
                double Nbar = this.calculateAverageNMP();
		this.term2 =((Math.sqrt((Pbar*Nbar*(1-Pbar)))));		
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
                //PStatistic calculador_P = new PStatistic(true);
		VPStatistic calculador_P = new VPStatistic();
		ArrayList<DataSetItem> dados_para_media = new ArrayList<DataSetItem>(this.data.size());
		for(Iterator<ArrayList<DataSetItem>> it = this.data.iterator();it.hasNext();)
		{
                  //  cont3++;
			ArrayList<DataSetItem> amostra = it.next();
			Double desvio = calculador_P.generateStatistic(amostra);
			DataSetItem novo_item = new DataSetItem();
			novo_item.setX(0.0);
			novo_item.setY(desvio);
			dados_para_media.add(novo_item);
       
		}
		AverageStatistic calculador_media = new AverageStatistic();
		Double retorno = calculador_media.generateStatistic(dados_para_media);
		return retorno;
	}
     private Double calculateAverageNMP()
	{
                //PStatistic calculador_P = new PStatistic(true);
		AverageNGP calculador_MNP = new AverageNGP();
                ArrayList<DataSetItem> dados_para_media2 = new ArrayList<DataSetItem>(this.data.size());
		for(Iterator<ArrayList<DataSetItem>> it = this.data.iterator();it.hasNext();)
		{
                  //  cont3++;
			ArrayList<DataSetItem> amostra = it.next();
                        Double desvio2 = calculador_MNP.generateStatistic(amostra);
                        DataSetItem novo_item2 = new DataSetItem();
			novo_item2.setX(0.0);
			novo_item2.setY(desvio2);
			dados_para_media2.add(novo_item2);
		}
		AverageStatistic calculador_media2 = new AverageStatistic();
		Double retorno = calculador_media2.generateStatistic(dados_para_media2);
		return retorno;
	}
    
}
