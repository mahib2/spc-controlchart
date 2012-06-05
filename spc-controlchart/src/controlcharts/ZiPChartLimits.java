/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package controlcharts;

import data.DataSetItem;
import java.util.ArrayList;
import java.util.Iterator;
import statistic.*;

/**
 *
 * @author saul
 */
public class ZiPChartLimits implements GenericChartLimits{
    protected ArrayList<ArrayList<DataSetItem>> data;
	protected Integer sample_size;
        protected Double ucl;
	protected Double lcl;
	protected Double ual;
	protected Double lal;
	protected Double cl;
        protected double retorno1;
	
	public ZiPChartLimits(Integer size)
	{
		this.init(size);
	}
	
	public ZiPChartLimits()
	{
		
	}
	
	protected void init(int size)
	{
		this.sample_size = size;
		this.data = new ArrayList<ArrayList<DataSetItem>>(size);
	}

	public Double calculateCentralLine(Double x) 
	{
		if(this.cl==null)
		{
			this.cl = 0.0;
		}
		return this.cl;
	}

	public Double calculateLowerAdvertenceLimit(Double x) 
	{
		if(this.lal==null)
		{
			this.lal = -1.5;
		}
		return this.lal;
	}

	public Double calculateLowerControlLimit(Double x) 
	{
		if(this.lcl==null)
		{
			this.lcl = -3.0;
		}
		return this.lcl;
	}
	//calcular os valores corretos
	public Double calculateUpperAdvertenceLimit(Double x) {
		if(this.ual==null)
		{
			this.ual = 1.5;
		}
		return this.ual;
	}

	public Double calculateUpperControlLimit(Double x) 
	{
		if(this.ucl==null)
		{
			this.ucl = 3.0;
		}
		return this.ucl;
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
}
