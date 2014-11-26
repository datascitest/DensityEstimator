package densityEstimator.NADE;

import java.util.ArrayList;
import java.util.List;

import densityEstimator.data.Data;
import densityEstimator.data.Instance;
import densityEstimator.utility.MathUtils;

/**
 * Mixture of NADE. 
 * @author wenzhe
 *
 */
public class MixNADE {
    private int numOfComponents;
    private double[] mixingRate;
    private List<NADE> mixtureModels;
    private double[][] instanceMixedRate;
    
    public Data trainData;
    public Data testData;
    
    public static final int MAX_ITERATIONS =  100;
    
    public MixNADE(int numOfComponents){
    	// create list of mixture models.
    	this.numOfComponents = numOfComponents;
    	mixtureModels = new ArrayList<NADE>(numOfComponents);
    	mixingRate = new double[numOfComponents];
    	instanceMixedRate = new double[trainData.numOfInstances][numOfComponents];
    }
    
    /**
     * some comments go here. 
     */
    public void learn(){
    	for (int itr = 0; itr < MAX_ITERATIONS; itr++){
    		/**
    		 * E-step: compute the mixing rate for each instance. 
    		 * Computed as:  p(x|m_i)/(p(x|m_1) + ... + p(x|m_n)) 
    		 */
    		for (int i = 0; i < trainData.numOfInstances; i++){
    			Instance instance =  trainData.instances.get(i);
    			double[] p = new double[numOfComponents];
    			for (int k = 0; k < numOfComponents; k++){
    				p[k] = ((BiNADE)mixtureModels.get(k)).evaluate(instance);
    			}
    			// sum over all the p
    			double sum = MathUtils.sum(p);
    			for (int k = 0; k < numOfComponents; k++){
    				instanceMixedRate[i][k] = p[k]/sum;
    			}
    		}
    		
    		/**
    		 * M-step:  run gradient descent for each components
    		 */
    		for (int i = 0; i < instanceMixedRate.length; i++){
    			for (int k = 0; k < numOfComponents; k++){
    				mixingRate[k] += instanceMixedRate[i][k];
    			}
    		}
    		for (int k = 0; k < numOfComponents; k++){
    			mixingRate[k] = mixingRate[k] / trainData.instances.size();
    		}
    		
    		for (int i = 0; i < trainData.numOfInstances; i++){
    			Instance instance =  trainData.instances.get(i);
    			for (int k = 0; k < numOfComponents; k++){
    				((BiNADE)mixtureModels.get(k)).update(instance, instanceMixedRate[i][k]);
    			}
    		}
    	}
    }
    
    public double avgLog(Data data){
        double sum = 0.0;
        for (Instance instance : data.instances){
        	sum += evaluate(instance);
        }
        
        return sum * 1.0/data.instances.size();
    }
    
    public double evaluate(Instance instance){
        double result = 0;
        for (int k = 0; k < numOfComponents; k++){
        	BiNADE model = (BiNADE) mixtureModels.get(k);
        	result += model.evaluate(instance) * mixingRate[k];
        }
        
        return result;
    }
}
