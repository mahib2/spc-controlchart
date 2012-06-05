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
public class VarnPChartLimits implements GenericChartLimits {
     protected ArrayList<ArrayList<DataSetItem>> data;
	protected Integer sample_size;
        protected Double ucl;
	protected Double lcl;
	protected Double ual;
	protected Double lal;
	protected Double cl;
     //   protected double Am3;
	
	public VarnPChartLimits(Integer size)
	{
		this.init(size);
	}
	
	public VarnPChartLimits()
	{
		
	}
	
	protected void init(int size)
	{
		this.sample_size = size;
		this.data = new ArrayList<ArrayList<DataSetItem>>(size);
	}

	public final Double calculateCentralLine(Double x) 
	{
             int y = x.intValue();
		//if(this.cl==null)
		//{
			this.cl = this.calculateTerm1P(y);
		//}
		return this.cl;
	}

	public final Double calculateUpperControlLimit(Double x) 
	{
                int y = x.intValue();

		this.ucl = this.calculateTerm1P2(y) + (3.0 * this.calculateTerm2P(y));
		//}
		return this.ucl;
	}

	public final Double calculateLowerControlLimit(Double x) 
	{
                int y = x.intValue();
		//if(this.lcl==null)
		//{
			this.lcl = this.calculateTerm1P2(y) - (3.0 * this.calculateTerm2P(y));
		//}
		return this.lcl;
	}

	public final Double calculateUpperAdvertenceLimit(Double x) 
	{
                int y = x.intValue();
		//if(this.ual==null)
		//{
			this.ual = this.cl*this.calculateAverageP() + (1.5 * Math.sqrt(this.cl*this.calculateTerm2P(y)*(1-this.calculateTerm2P(y))));
		//}
		return this.ual;
	}

	public final Double calculateLowerAdvertenceLimit(Double x) 
	{
                int y = x.intValue();
		//if(this.lal==null)
		//{
			//this.lal = this.calculateAverageP() - (1.5 * this.calculateTerm2P(y));
                this.lal = null;
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
            nPStatistic Cal_P =  new nPStatistic();
            ArrayList<DataSetItem> dados_para_media = new ArrayList<DataSetItem>(this.data.size());
		for(Iterator<ArrayList<DataSetItem>> it = this.data.iterator();it.hasNext();)
		{
                    cont3++;
                    ArrayList<DataSetItem> amostra = it.next();
                    if(cont3==x){
			//ArrayList<DataSetItem> amostra = it.next();
			Double desvio = Cal_P.generateStatistic(amostra);
                        vart1 = (double)Cal_P.getVar2();
			/*DataSetItem novo_item = new DataSetItem();
			novo_item.setX(0.0);
			novo_item.setY(desvio);
			dados_para_media.add(novo_item);*/
                    }
		}
                double result = Math.sqrt((this.calculateAverageP()*(1-this.calculateAverageP()))/vart1);
                return Math.sqrt(vart1*this.calculateAverageP()*(1-this.calculateAverageP()));
        }
        public double calculateTerm1P(int x){
        int cont4=0;
        double vart2=0;
        double desvio3 = 0;
        nPStatistic cal_P = new nPStatistic();
         ArrayList<DataSetItem> dados_para_media = new ArrayList<DataSetItem>(this.data.size());
		for(Iterator<ArrayList<DataSetItem>> it = this.data.iterator();it.hasNext();)
		{
                    cont4++;
                    ArrayList<DataSetItem> amostra = it.next();
                    if(cont4==x){
			//ArrayList<DataSetItem> amostra = it.next();
			desvio3 =(double) cal_P.generateStatistic(amostra);
                        vart2 = (double)cal_P.getVar2();
			/*DataSetItem novo_item = new DataSetItem();
			novo_item.setX(0.0);
			novo_item.setY(desvio);
			dados_para_media.add(novo_item);*/
                    }
		}
        return (desvio3 *this.calculateAverageP());
        }
        public double calculateTerm1P2(int x){
        int cont4=0;
        double vart2=0;
        double desvio3 = 0;
        nPStatistic cal_P = new nPStatistic();
         ArrayList<DataSetItem> dados_para_media = new ArrayList<DataSetItem>(this.data.size());
		for(Iterator<ArrayList<DataSetItem>> it = this.data.iterator();it.hasNext();)
		{
                    cont4++;
                    ArrayList<DataSetItem> amostra = it.next();
                    if(cont4==x){
			//ArrayList<DataSetItem> amostra = it.next();
			desvio3 =(double) cal_P.generateStatistic(amostra);
                        vart2 = (double)cal_P.getVar2();
			/*DataSetItem novo_item = new DataSetItem();
			novo_item.setX(0.0);
			novo_item.setY(desvio);
			dados_para_media.add(novo_item);*/
                    }
		}
        return (vart2 *this.calculateAverageP());
        }
}
