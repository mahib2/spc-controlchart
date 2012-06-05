/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package indices;

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
public class IndiceShow  {
    /*
    double LSC,LC,LIC,LIA,LSA;
    int TamanhoAmostral;
    
    double LSE,LIE;
    double media_DPadrao;
    double M, d;
    
    double Cp,Cpu,Cpl,Cpk;
    public IndiceShow(){
    
    }
    public void coloca(double lsc,double lc,double lic,double lia,double lsa){
     LSC= lsc;
     LC=lc;
     LIC=lic;
    LIA=lia;
    LSA=lsa;
    }
     public void acha(GenericStatistic statistic, File arquivo, GenericChartLimits limites_controle) throws DataSetException
	{
//				// create the dataset...
		DefaultCategoryDataset dataset = new DefaultCategoryDataset();


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
                double est_Med=0;

		Integer tamanho_amostra = null;
		while(!data_set.isEmpty())
		{
			cont++;
			ArrayList<DataSetItem> item = data_set.next();
			GenericStatistic statistica_teste = statistic;
			double estatistica = statistica_teste.generateStatistic(item);
                        est_Med = est_Med+estatistica;
			if(tamanho_amostra==null)
			{
				tamanho_amostra = item.size();
				limites_controle.setSampleSize(tamanho_amostra);
			}
			limites_controle.addData(item);
		}
                TamanhoAmostral=cont+1;
                media_DPadrao = est_Med/TamanhoAmostral;



		for(int cont2=1;cont2<=cont;cont2++)
		{
			Double lc = limites_controle.calculateCentralLine(new Double(cont2));
			Double lsc = limites_controle.calculateUpperControlLimit(new Double(cont2));
			Double lic = limites_controle.calculateLowerControlLimit(new Double(cont2));
			Double lia = limites_controle.calculateLowerAdvertenceLimit(new Double(cont2));
			Double lsa = limites_controle.calculateUpperAdvertenceLimit(new Double(cont2));
			coloca(lsc,lc,lic,lia,lsa);
		}
		
}
     public void calculaIndices(){
         LSE = LSC*Math.sqrt(TamanhoAmostral);
         LIE = LIC*Math.sqrt(TamanhoAmostral);
         d= (LSE - LIE)/2;
         M= (LSE + LIE)/2;
         Cp = (LSE-LIE)/(6*media_DPadrao);
         Cpu = (LSE-media_DPadrao)/(3*media_DPadrao);
         Cpl = (media_DPadrao - LIE)/(3*media_DPadrao);
         Cpk = (d - Math.abs(media_DPadrao - M))/(3*media_DPadrao);
                 }
    /* 
     public double getCp(){
         return Cp;
     }
     public double getCpu(){
         return Cpu;
     }
     public double getCpl(){
         return Cpl;
     }
     public double getLSC(){
         return LSC;
     }
     public double getLIC(){
         return LIC;
     }
     public int getTam(){
        return TamanhoAmostral;
     }
     public double getD(){
        return d;
     }
     public double getM(){
         return M;
     }
     public double getCpk(){
        return Cpk;
     }*/
}
