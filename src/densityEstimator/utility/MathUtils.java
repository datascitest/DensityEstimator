package densityEstimator.utility;
import java.util.logging.Level;
import java.util.logging.Logger;


/**
 * Contains all the math operations needed for NADE class
 * @author wenzhe
 */
public class MathUtils {
    private final static Logger logger = Logger.getLogger(MathUtils.class.getName()); 
    
    public static double sigmoid(double x){
        double result = 1 / (1 + Math.exp(-x));
        if (result > 0.999995){
            result = 0.999995;
        }
        
        if (result < 0.000001){
            result = 0.000001;
        }
        
        return result;
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
    
    public static double[] add(double[] x, double y[]){
    	if (x.length < 1 || x.length != y.length){
            String errorMsg = "The input array has dimension 0 or " +
            		"two arrays do not have the same dimension";
            logger.log(Level.SEVERE, errorMsg);
            System.out.println(errorMsg);
        }
    	double[] result = new double[x.length];
    	for (int i = 0; i < x.length; i++)
    		result[i] = x[i] + y[i];
    	
    	return result;
    }
    
    public static double[] multiply(double[] x, double scalar){
    	if (x.length < 1){
    		String errorMsg = "The input array has dimension 0";
            logger.log(Level.SEVERE, errorMsg);
            System.out.println(errorMsg);
    	}
    	
    	double[] result = new double[x.length];
    	for (int i = 0; i < x.length; i++){
    		result[i] = x[i] * scalar;
    	}
    	
    	return result;
    }
    
    public static double[] substract(double scalar, double[] x){
    	double[] result = new double[x.length];
    	for (int i = 0; i < x.length; i++){
    		result[i] = scalar - x[i];
    	}
    	
    	return result;
    }
    
    public static double sum(double[] x){
    	double sum = 0; 
    	for (int i = 0; i < x.length; i++){
    		sum += x[i];
    	}
    	
    	return sum;
    }
    
}
