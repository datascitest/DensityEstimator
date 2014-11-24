package densityEstimator.data;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Class represents data set, contain the instances. 
 * @author wenzhe
 *
 */
public class Data {
    private final static Logger logger = Logger.getLogger(Data.class.getName()); 
    
    public List<Instance> instances;
    public int dimension;
    public int numOfInstances;
    
    public Data(List<Instance> instances){
        this.instances = instances;
        init();
    }
   
    public Data(){}
    
    public void setInstances(List<Instance> instances){
        this.instances = instances;
        init();
    }
    
    private void init(){
        numOfInstances = instances.size();
        if (numOfInstances < 1){
            String errorMsg = "It is empty data set, please check input file!";
            logger.log(Level.WARNING, errorMsg);
            System.out.println(errorMsg);
            return;
        }
        
        dimension = instances.get(0).size();
        if (dimension < 1){
            String errorMsg = "The dimension is 0, please check input file!";
            logger.log(Level.WARNING, errorMsg);
            System.out.println(errorMsg);
            return;
        }
    }
}
