/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package indices;

import constants.Cn;
import controlcharts.GenericChartLimits;
import data.DataSetCsvIterator;
import data.DataSetException;
import data.DataSetItem;
import data.DataSetIterate;
import java.io.File;
import java.util.ArrayList;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import statistic.GenericStatistic;
import types.DataConverter;
import types.DoubleDataConverter;

/**
 *
 * @author saul
 */
public class IndicesDFuncao extends IndiceS implements IndiceInter{
    Double LSE, LIE;
    public IndicesDFuncao(Double constante_LSE, Double constante_LIE){
    this.LIE = constante_LIE;
    this.LSE = constante_LSE;
    }
    
    //@Override
    /* public void coloca(double lsc,double lc,double lic,double lia,double lsa){
     LSC= lsc;
     LC=lc;
     LIC=lic;
    LIA=lia;
    LSA=lsa;
    }*/
   // @Override
     public void acha(GenericStatistic statistic, File arquivo,  GenericStatistic mediaS) throws DataSetException
	{
		//DefaultCategoryDataset dataset = new DefaultCategoryDataset();
		if(arquivo==null)
		{
			//return null;
                    JFrame frameMsg = new JFrame("JOptionPane showMessageDialog example");

    // show a joptionpane dialog using showMessageDialog
        JOptionPane.showMessageDialog(frameMsg,"Arquivo Vazio");
        System.exit(0);
		}

		DataConverter conversor_long = new DoubleDataConverter();
		DataSetIterate data_set = new DataSetCsvIterator(arquivo,conversor_long,false,null);
		int cont = 0;
                double est_Med=0.0;
                double med_Med=0.0;

		//Integer tamanho_amostra = null;
		while(!data_set.isEmpty())
		{
			cont++;
			ArrayList<DataSetItem> item = data_set.next();
			GenericStatistic statistica_teste = statistic;
                        GenericStatistic media_teste = mediaS;
			double estatistica = statistica_teste.generateStatistic(item);
                       double mediaEs = media_teste.generateStatistic(item);
                        est_Med = est_Med+estatistica;
                       med_Med = med_Med+mediaEs;
			/*if(tamanho_amostra==null)
			{
				tamanho_amostra = item.size();
				limites.setSampleSize(tamanho_amostra);
			}
			limites.addData(item);*/
		}
                cn = Cn.calculate(cont);
                TamanhoAmostral=cont;
               media_DFuncao = est_Med/TamanhoAmostral;
      
              media_X = med_Med/TamanhoAmostral;

		/*for(int cont2=1;cont2<=cont;cont2++)
		{
			Double lc = limites.calculateCentralLine(new Double(cont2));
			Double lsc = limites.calculateUpperControlLimit(new Double(cont2));
			Double lic = limites.calculateLowerControlLimit(new Double(cont2));
			Double lia = limites.calculateLowerAdvertenceLimit(new Double(cont2));
			Double lsa = limites.calculateUpperAdvertenceLimit(new Double(cont2));
                        //TESTANDO LIC=0.0
                        if(lic<0){
                            lic=0.0;
                        }
			coloca(lsc,lc,lic,lia,lsa);
		}*/
		
}
    @Override
     public void calculaIndices(){
      //   LSE = LSC*Math.sqrt(TamanhoAmostral);
       //  LIE = LIC*Math.sqrt(TamanhoAmostral);
         d= (LSE - LIE)/2;
         M= (LSE + LIE)/2;
        /* Cp = (LSE-LIE)/(6*(media_DFuncao/cn));
         Cpu = (LSE-media_X)/(3*(media_DFuncao/cn));
         Cpl = (media_X - LIE)/(3*(media_DFuncao/cn));
         Cpk = (d - Math.abs(media_X - M))/(3*(media_DFuncao/cn));
         */
         Cp = (LSE-LIE)/(6*(media_DFuncao));
         Cpu = (LSE-media_X)/(3*(media_DFuncao));
         Cpl = (media_X - LIE)/(3*(media_DFuncao));
         Cpk = (d - Math.abs(media_X - M))/(3*(media_DFuncao));
                 }
}
