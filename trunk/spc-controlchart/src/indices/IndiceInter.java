/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package indices;

import controlcharts.GenericChartLimits;
import data.DataSetException;
import data.DataSetItem;
import java.io.File;
import java.util.ArrayList;
import statistic.GenericStatistic;

/**
 *
 * @author saul
 */
public interface IndiceInter {
    //public Double generateStatistic(ArrayList<DataSetItem> sample);
   // public void coloca(double lsc,double lc,double lic,double lia,double lsa);
    //public void acha(GenericStatistic statistic, File arquivo, GenericChartLimits limites_controle)throws DataSetException;
     public void acha(GenericStatistic statistic, File arquivo, GenericStatistic mediaS)throws DataSetException;
    public void calculaIndices();
    
}
