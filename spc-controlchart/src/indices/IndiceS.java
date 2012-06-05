/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package indices;

/**
 *
 * @author saul
 */
public class IndiceS{
    double Cp,Cpu,Cpl,Cpk;
   // double LSC,LC,LIC,LIA,LSA;
    int TamanhoAmostral;
    
   // double LSE,LIE;
    double media_DFuncao;
    double media_X;
    double M, d, cn;
    public double getCp(){
         return Cp;
     }
     public double getCpu(){
         return Cpu;
     }
     public double getCpl(){
         return Cpl;
     }
     //public double getLSC(){
     //    return LSC;
    // }
     public double getCn(){
         return cn;
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
     }
     public double getMedia(){
        return media_DFuncao;
     }
      public double getMediaMed(){
        return media_X;
     }
}
