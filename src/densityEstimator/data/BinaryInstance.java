package densityEstimator.data;

import java.util.List;

/**
 * The class implements binary visible units. 
 * @author wenzhe
 *
 */
public class BinaryInstance extends Instance{
    private List<Byte> features;
    
    public BinaryInstance(List<Byte> features){
        this.features = features;
    }
    
    public List<Byte> getFeatures(){
        return features;
    }
    
    @Override
    public String toString(){
        StringBuffer stBuff = new StringBuffer();
        stBuff.append("[");
        for (int i = 0; i < size(); i++){
            stBuff.append(features.get(i).toString());
            stBuff.append(",");
        }
        stBuff.append("]");
        
        return stBuff.toString();
    }

    @Override
    public int size() {
        return features.size();
    }
}

