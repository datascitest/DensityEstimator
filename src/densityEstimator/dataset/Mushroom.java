package densityEstimator.dataset;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import densityEstimator.data.BinaryInstance;
import densityEstimator.data.Data;
import densityEstimator.data.Instance;

/**
 * This class process the mushroom data set. 
 * UCI repository: https://archive.ics.uci.edu/ml/datasets/Mushroom
 * @author Wenzhe
 *
 */
public class Mushroom {
	public Mushroom(){
	}
	
	// return Data set. 
	public Data create() throws IOException{
		List<Map<String, Byte>> characterSet = new ArrayList<Map<String, Byte>>();
		File file = new File("data/mushroom.dat");
        BufferedReader br = null;
        String sCurrentLine = "";
        br = new BufferedReader(new FileReader(file));
        while ((sCurrentLine = br.readLine()) != null){
        	String[] chars = sCurrentLine.split(",");
        	for (int i = 1; i < chars.length; i++){
        		if (characterSet.get(i-1) == null){
        			Map<String, Byte> s = new HashMap<String, Byte>();
        			s.put(chars[i], (byte) 1);
        			characterSet.set(i-1, s);
        		}else{
        			Map<String, Byte> s = characterSet.get(i-1);
        			if (!s.containsKey(chars[i])){
        				int cnt = s.size();
        				s.put(chars[i], (byte) (cnt+1));
        				characterSet.set(i-1, s);
        			}
        		}
        	}
        }
        
        Data  data = new Data();
        List<Instance> instances  = new ArrayList<Instance>();
        List<Integer> sizes = new ArrayList<Integer>();
        br = new BufferedReader(new FileReader(file));
        while ((sCurrentLine = br.readLine()) != null){
        	String[] chars = sCurrentLine.split(",");
        	List<Byte> features = new ArrayList<Byte>();
        	for (int i = 1; i < chars.length; i++){
        		List<Byte> tmp  = generate(characterSet.get(i-1).get(chars[i]), sizes.get(i-1));
        		// add into features
        		features.addAll(tmp);
        	}
        	
        	Instance instance = new BinaryInstance(features);
        	instances.add(instance);
        }
        
        data.setInstances(instances);
        return data;
	}
	
	private List<Byte> generate(Byte str, Integer size){
		
		List<Byte> result = new ArrayList<Byte>(size);
		result.set(str-1, (byte) 1);
		return result;
	}
}
