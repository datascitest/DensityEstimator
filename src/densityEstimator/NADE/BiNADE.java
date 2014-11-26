package densityEstimator.NADE;

import java.io.File;
import java.util.List;

import densityEstimator.data.BinaryInstance;
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
    
    private double learningRate = 0.001;
    
    
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
        double[] a = new double[hSize];
        
        double[] grad_vBias = new double[vSize];
        double[] grad_hBias = new double[hSize];
        double[][] grad_W = new double[vSize][hSize];
        double[][] grad_V = new double[vSize][hSize];
        
        for (int i = vSize-1; i >= 0; i--){
        	double v_i = ((BinaryInstance)(instance)).getFeatures().get(i);
        	grad_vBias[i] = Utils.sigmoid(vBias[i] + ) - v_i;
        }
        
        
    }

    @Override
    public double avgLog(Data data) {
        double result = 0.0;
        for (Instance instance : data.instances){
            result += evaluate(instance);
        }
        
        return result * 1.0/(data.instances.size());
    }

    /**
     * Compute the log probability of instance
     */
    @Override
    public double evaluate(Instance instance) {
        // TODO Auto-generated method stub
        double[] a = hBias.clone();
        double p = 0;
        for (int i = 0; i < vSize; i++){
            double[] h = Utils.sigmoid(a);
            double tmp = Utils.sigmoid(vBias[i] + Utils.multiply(V[i], h));
            byte v_i = ((BinaryInstance)instance).getFeatures().get(i);
            p += v_i * Math.log(tmp) + (1-v_i) * Math.log(1-tmp);
            a = Utils.add(a, Utils.multiply(W[i], v_i));
        }
        
        return p;
    }

    @Override
    public void saveParameters() {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void loadParameters(File file) {
        // TODO Auto-generated method stub
        
    }
    
    public double getLearningRate(){
    	return learningRate;
    }
    
    
    public void setLearningRate(double learningRate){
    	this.learningRate = learningRate;
    }
}
