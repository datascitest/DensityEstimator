package densityEstimator.common;
import densityEstimator.data.Data;
import densityEstimator.data.Instance;


/**
 * All the density estimator models should implement
 * this interface. 
 * 
 * @author wenzhe
 *
 */
public interface DensityEstimator {
    /**
     * Learn the model parameters using training data. 
     */
    void learn();
    
    /**
     * This is used for stochastic gradient descent training
     * The model takes an instance each time, and do update
     * for the mode parameters. 
     * 
     * @param instance  the current instance
     */
    void update(Instance instance);
    
    /**
     * Compute the average log likelihood of the data, given 
     * the current model parameters. 
     * avg_log = 1/N * (\sum_{i=1}^{N}p(x)) 
     * 
     * @return  the average log likelihood. 
     */
    double avgLog(Data data);
    
    /**
     * Compute the log likelihood for single instance. 
     * 
     * @param instance  the current instance
     * @return          log likelihood. 
     */
    double evaluate(Instance instance);
}
