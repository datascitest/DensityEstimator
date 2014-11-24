package densityEstimator.NADE;

import java.util.List;

import densityEstimator.data.Instance;

/**
 * Mixture of NADE. 
 * @author wenzhe
 *
 */
public class MixNADE {
    private int numOfComponents;
    private double[] pi;
    private List<NADE> models;
    private double[][] dataMixedRates;
    
    public MixNADE(){
    }
    
    public void learn(){
        
    }
    
    public double avgLog(){
        return 0;
    }
    
    public double evaluate(Instance instance){
        return 0;
    }
}
