package densityEstimator.utility;

import java.util.ArrayList;
import java.util.List;

import densityEstimator.data.Data;
import densityEstimator.data.Instance;

public class DataSetUtils {
    
    private List<Instance> trainInstances;
    private List<Instance> testInstances;
    
    /**
     * split the data set into training and testing set
     * @param inputData
     * @param ratio         ratio of test data
     */
    public DataSetUtils(Data inputData, double ratio){
        trainInstances = new ArrayList<Instance>();
        testInstances = new ArrayList<Instance>();
        
        List<Instance> instances = inputData.instances;
        for (Instance instance : instances){
            double rand = Math.random();
            if (rand < ratio){
                testInstances.add(instance);
            }else{
                trainInstances.add(instance);
            }
        } 
    }
    
    public Data getTrainData(){
        Data trainData = new Data();
        trainData.setInstances(trainInstances);
        return trainData;
    }
    
    public Data getTestData(){
        Data testData = new Data();
        testData.setInstances(testInstances);
        return testData;
    }
}
