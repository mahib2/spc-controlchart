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
import statistic.VPStatistic;

/**
 *
 * @author saul
 */
public class VarPChartLimits implements GenericChartLimits {
     protected ArrayList<ArrayList<DataSetItem>> data;
	protected Integer sample_size;
        protected Double ucl;
	protected Double lcl;
	protected Double ual;
	protected Double lal;
	protected Double cl;
        protected double retorno1;
	
	public VarPChartLimits(Integer size)
	{
		this.init(size);
	}
	
	public VarPChartLimits()
	{
		
	}
	
	protected void init(int size)
	{
		this.sample_size = size;
		this.data = new ArrayList<ArrayList<DataSetItem>>(size);
	}

	public final Double calculateCentralLine(Double x) 
	{
		//if(this.cl==null)
		//{
			this.cl = this.calculateAverageP();
		//}
		return this.cl;
	}

	public final Double calculateUpperControlLimit(Double x) 
	{
                int y = x.intValue();
		//if(this.ucl==null)
		//{
			this.ucl = this.calculateAverageP() + (3.0 * this.calculateTerm2P(y));
		//}
		return this.ucl;
	}

	public final Double calculateLowerControlLimit(Double x) 
	{
                int y = x.intValue();
		//if(this.lcl==null)
		//{
			this.lcl = this.calculateAverageP() - (3.0 * this.calculateTerm2P(y));
		//}
		return this.lcl;
	}

	public final Double calculateUpperAdvertenceLimit(Double x) 
	{
                int y = x.intValue();
		//if(this.ual==null)
		//{
			this.ual = this.calculateAverageP() + (1.5 * this.calculateTerm2P(y));
		//}
		return this.ual;
	}

	public final Double calculateLowerAdvertenceLimit(Double x) 
	{
                int y = x.intValue();
		//if(this.lal==null)
		//{
			this.lal = this.calculateAverageP() - (1.5 * this.calculateTerm2P(y));
		//}
		return this.lal;
	}
	
	public void setSampleSize(Integer size) 
	{
		this.sample_size = size;
		this.init(size);
		
	}

	public Integer getSample_size() {
		return sample_size;
	}
          public void addData(ArrayList<DataSetItem> new_data) {
            this.data.add(new_data);
          }
        /*public double getRetorno1(){
            return retorno1;
        }*/
      
        public double calculateAverageP()
	{
                //PStatistic calculador_P = new PStatistic(true);
		PStatistic calculador_P = new PStatistic();
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
		// this.retorno1 = calculador_media.generateStatistic(dados_para_media);
                 return calculador_media.generateStatistic(dados_para_media);
        }
        public double calculateTerm2P(int x){
            int cont3=0;
            double vart1 = 0;
            PStatistic Cal_P =  new PStatistic();
            ArrayList<DataSetItem> dados_para_media = new ArrayList<DataSetItem>(this.data.size());
		for(Iterator<ArrayList<DataSetItem>> it = this.data.iterator();it.hasNext();)
		{
                    cont3++;
                    ArrayList<DataSetItem> amostra = it.next();
                    if(cont3==x){
			//ArrayList<DataSetItem> amostra = it.next();
			Double desvio = Cal_P.generateStatistic(amostra);
                        vart1 = (double)Cal_P.getVar1();
			/*DataSetItem novo_item = new DataSetItem();
			novo_item.setX(0.0);
			novo_item.setY(desvio);
			dados_para_media.add(novo_item);*/
                    }
		}
                double result = Math.sqrt((this.calculateAverageP()*(1-this.calculateAverageP()))/vart1);
                return Math.sqrt((this.calculateAverageP()*(1-this.calculateAverageP()))/vart1);
        }
}
