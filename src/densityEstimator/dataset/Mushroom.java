package densityEstimator.dataset;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import densityEstimator.data.Data;

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
		List<Map<Character, Integer>> charToIntMap;
		charToIntMap = new ArrayList<Map<Character, Integer>>();
		File file = new File("data/mushroom.dat");
        BufferedReader br = null;
        String sCurrentLine = "";
        br = new BufferedReader(new FileReader(file));
        while ((sCurrentLine = br.readLine()) != null){
        	String[] strs = sCurrentLine.split(",");
        	
        }
        
        return null;
	}
}
