package densityEstimator.NADE;

import java.io.File;

import densityEstimator.common.DensityEstimator;


/**
 * Abstract class for NADE. The basic class contains all 
 * the mandatory data and operations. 
 * 
 * @author wenzhe
 *
 */
public abstract class NADE implements DensityEstimator{
    
    protected int hSize;      // number of hidden units
    protected int vSize;      // number of visual units
    protected double[] hBias; // bias parameters for hidden units
    protected double[] vBias; // bias parameters for visual units
    
    /**
     * Weight matrix, which is D by H dimension
     * where H is the number of hidden units, and D
     * is the size of visual units. This weight matrix connects
     * hidden layer and visual layer
     */
    protected double[][] W;
    
    /**
     * Weight matrix, which is D by H dimensional. This weight 
     * matrix connects hidden layer to output layer. 
     */
    protected double[][] V;
    
    protected int maxIterations = 500;
    
    /**
     * If untiedWeights == true, then we use different weight matrix
     * for visual-hidden and hidden-output
     */
    protected boolean untiedWeights = true;
    
    /**
     * Initialize all the parameters. 
     */
    public void init(){
        hBias = new double[hSize];
        for (int i = 0; i < hSize; i++)
            hBias[i] = Math.random();
        
        vBias = new double[vSize];
        for (int i = 0; i < vSize; i++)
            vBias[i] = Math.random();
        
        W = new double[vSize][hSize];
        for (int i = 0; i < vSize; i++){
            for (int j = 0; j < hSize; j++){
                W[i][j] = Math.random();
            }
        }
        
        if (untiedWeights == true){
            V = new double[vSize][hSize];
            for (int i = 0; i < vSize; i++){
                for (int j = 0; j < hSize; j++){
                    V[i][j] = Math.random();
                }
            }
        }
    } // end of init
    
    /**
     * Save parameters to the file. 
     */
    public abstract void saveParameters();
    
    /**
     * Load parameters from the file. 
     */
    public abstract void loadParameters(File file);
    
    public double[][] getW(){
        return W;
    }
    
    public double[][] getV(){
        return V;
    }
    
    public int getHiddenSize(){
        return hSize;
    }
    
    public int getVisualHize(){
        return vSize;
    }
    
    public void setMaxIterations(int maxIterations){
        this.maxIterations = maxIterations;
    }
    
    public int getMaxIterations(){
        return maxIterations;
    }
    
    public void setUntiedWeight(){
        this.untiedWeights = true;
    }
    
    public void setTiedWeight(){
        this.untiedWeights = false;
    }  
}
