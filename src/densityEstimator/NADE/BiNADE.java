package densityEstimator.NADE;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import densityEstimator.data.BinaryInstance;
import densityEstimator.data.Data;
import densityEstimator.data.Instance;
import densityEstimator.utility.MathUtils;

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

    public BiNADE(int hSize, int vSize, Data trainData){
        this.hSize = hSize;
        this.vSize = vSize;
        this.trainData = trainData;
        avgLogHistory = new ArrayList<Double>();
        init();
    }
    
    
    
    
    @Override
    public void learn() {
        // either reach iterations or already converged
        for (int itr = 0; itr < maxIterations; itr++){
            //System.out.println("running " + itr + "st iteration");
            if (itr % evaluteFrequency == 0){
                // evalute on the whole data set and record the avg log likelihood
                double avgLogLikelihood = avgLog(trainData);
                avgLogHistory.add(avgLogLikelihood);
                System.out.println(itr + "iteration: " + avgLogLikelihood);
            }
            
            // run SGD on each instance. 
            for (Instance instance : trainData.instances){
                update(instance, 1);
            }
        }
    }

    @Override
    public void update(Instance instance, double proportion) {
    	
    	// evaluate the probability and store the temporary results
    	// this will make sure we have efficient gradient computation afterwards. 
    	double[][] H = new double[vSize][hSize];
    	double[] p_v = new double[vSize];
    	double[] tmp = hBias.clone();
    	
        for (int i = 0; i < vSize; i++){
             H[i] = MathUtils.sigmoid(tmp);
             p_v[i] = MathUtils.sigmoid(vBias[i] + MathUtils.multiply(V[i], H[i]));
             byte v_i = ((BinaryInstance)instance).getFeatures().get(i);
             tmp = MathUtils.add(tmp, MathUtils.multiply(W[i], v_i));
        }
    	
        // compute the gradient for vBais, hBias, W, V
        // since we need to use the old gradient to do repetitive
        // computation, there is no easy way to do sequential update instead
        // of batch update after we compute all the gradients. 
        double[] grad_a = new double[hSize];
        double[] grad_vBias = new double[vSize];
        double[] grad_hBias = new double[hSize];
        double[][] grad_W = new double[vSize][hSize];
        double[][] grad_V = new double[vSize][hSize];
        
        double[] h_tmp = new double[hSize];
        for (int i = vSize-1; i >= 0; i--){
        	double v_i = ((BinaryInstance)(instance)).getFeatures().get(i);
        	grad_vBias[i] = p_v[i] - v_i;
        	grad_V[i] = MathUtils.multiply(H[i], p_v[i] - v_i);
        	h_tmp = MathUtils.multiply(V[i], p_v[i] - v_i);
        	double[] tmp_result = MathUtils.multiply(h_tmp, MathUtils.multiply(H[i], MathUtils.substract(1, H[i])));
        	grad_hBias = MathUtils.add(hBias, tmp_result);
        	grad_W[i] = MathUtils.multiply(grad_a, v_i);
        	grad_a = MathUtils.add(grad_a, tmp_result);
        }
        
        // update hBias
        for (int i = 0; i < hSize; i++){
        	hBias[i] = hBias[i] - proportion * this.learningRate * grad_hBias[i];
        }
        // update vBias
        for (int i = 0; i < vSize; i++){
        	vBias[i] = vBias[i] -  proportion * this.learningRate * grad_vBias[i];
        }
        // update W and V
        for (int i = 0; i < vSize; i++){
        	for (int j = 0; j < hSize; j++){
        		W[i][j] = W[i][j] -  proportion * this.learningRate * grad_W[i][j];
        		V[i][j] = V[i][j] -  proportion * this.learningRate * grad_V[i][j];
        	}
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
        double[] a = hBias.clone();
        double p = 0;
        for (int i = 0; i < vSize; i++){
            double[] h = MathUtils.sigmoid(a);
            double tmp = MathUtils.sigmoid(vBias[i] + MathUtils.multiply(V[i], h));
            byte v_i = ((BinaryInstance)instance).getFeatures().get(i);
            p += v_i * Math.log(tmp) + (1-v_i) * Math.log(1-tmp);
            a = MathUtils.add(a, MathUtils.multiply(W[i], v_i));
        }
        
        return p;
    }
    
    /**
     * return full probability (not log)
     */
    public double evaluate1(Instance instance) {
        double[] a = hBias.clone();
        double p = 1;
        for (int i = 0; i < vSize; i++){
            double[] h = MathUtils.sigmoid(a);
            double tmp = MathUtils.sigmoid(vBias[i] + MathUtils.multiply(V[i], h));
            byte v_i = ((BinaryInstance)instance).getFeatures().get(i);
            p *= Math.pow(tmp, v_i) * Math.pow(1-tmp, 1-v_i);
            a = MathUtils.add(a, MathUtils.multiply(W[i], v_i));
        }
        
        if (p < 1e-32){
            p = 1e-32;
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
    

}
