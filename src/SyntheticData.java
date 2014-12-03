import java.util.ArrayList;
import java.util.List;

import densityEstimator.data.BinaryInstance;
import densityEstimator.data.Data;
import densityEstimator.data.Instance;


public class SyntheticData {
    public static Data createSyntheticData(){
        Data data = new Data();
        List<Instance> instances = new ArrayList<Instance>();
        
        List<Byte> features  = new ArrayList<Byte>();
        features.add((byte) 1);
        features.add((byte) 1);
        features.add((byte) 0);
        Instance instance = new BinaryInstance(features);
        instances.add(instance);
        
        features = new ArrayList<Byte>();
        features.add((byte) 1);
        features.add((byte) 0);
        features.add((byte) 0);
        instance = new BinaryInstance(features);
        instances.add(instance);
        
        features = new ArrayList<Byte>();
        features.add((byte) 1);
        features.add((byte) 1);
        features.add((byte) 1);
        instance = new BinaryInstance(features);
        
        features = new ArrayList<Byte>();
        features.add((byte) 0);
        features.add((byte) 1);
        features.add((byte) 0);
        instance = new BinaryInstance(features);
        instances.add(instance);
        
        data.setInstances(instances);
        
        return data;
    }
}
