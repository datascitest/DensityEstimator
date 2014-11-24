package densityEstimator.NADE;

import java.io.File;
import java.util.List;

import densityEstimator.data.Data;
import densityEstimator.data.Instance;
import densityEstimator.utility.Utils;

public class BiNADE extends NADE{
    
    public Data trainData;
    public Data testData;
    
    // stores the change of average log likelihood
    private List<Double> avgLogHistory;
    
    /**
     * Defines the evaluation frequency. It decides how many 
     * number of iterations we need to do before we evalute the log
     * likelihood of the data. This is used to monitor 
     * the convergence. 
     */
    private int evaluteFrequency = 10;
    
    
    @Override
    public void learn() {
        // either reach iterations or already converged
        for (int itr = 0; itr < maxIterations; itr++){
            if (itr % evaluteFrequency == 0){
                // evalute on the whole data set and record the avg log likelihood
                double avgLogLikelihood = avgLog(trainData);
                avgLogHistory.add(avgLogLikelihood);
                System.out.println(itr + "iteration: " + avgLogLikelihood);
            }
            
            // run SGD on each instance. 
            for (Instance instance : trainData.instances){
                update(instance);
            }
        }
    }

    @Override
    public void update(Instance instance) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public double avgLog(Data data) {
        double result = 0.0;
        for (Instance instance : data.instances){
            result += evaluate(instance);
        }
        
        return result * 1.0/(data.instances.size());
    }

    @Override
    public double evaluate(Instance instance) {
        // TODO Auto-generated method stub
        double[] a = hBias.clone();
        double p = 1;
        for (int i = 0; i < vSize; i++){
            double[] h = Utils.sigmoid(a);
        }
        
        return 0;
    }

    @Override
    public void saveParameters() {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void loadParameters(File file) {
        // TODO Auto-generated method stub
        
    }
    
}
