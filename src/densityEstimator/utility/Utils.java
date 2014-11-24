package densityEstimator.utility;
import java.util.logging.Level;
import java.util.logging.Logger;


/**
 * Contains all the math operations needed for NADE class
 * @author wenzhe
 */
public class Utils {
    private final static Logger logger = Logger.getLogger(Utils.class.getName()); 
    
    public static double sigmoid(double x){
        return 1 / (1 + Math.exp(-x));
    }
    
    public static double[] sigmoid(double[] x){
        double[] result = new double[x.length];
        for (int i = 0; i < result.length; i++){
            result[i] = sigmoid(x[i]);
        }
        
        return result;
    }
    
    public static double multiply(double[] x, double[] y){
        if (x.length < 1 || x.length != y.length){
            String errorMsg = "The input array has dimension 0 or " +
            		"two arrays do not have the same dimension";
            logger.log(Level.SEVERE, errorMsg);
            System.out.println(errorMsg);
        }
        double dotSum = 0;
        for (int i = 0; i < x.length; i++){
            dotSum += x[i] * y[i];
        }
        
        return dotSum;
    }
}
