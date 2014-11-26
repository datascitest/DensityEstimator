package densityEstimator.utility;

public class StringUtils {
	/**
	 * Convert val into binary string. 
	 * @param val		the value to be converted
	 * @param total		maximum number in this category
	 * @return
	 */
	public static String[] toBinary(int val, int total){
		String[] result = new String[total];
		for (int i = 0; i < total; i++){
			result[i] = "0";
		}
		// assume category value starts from 1. 
		result[val-1] = "1";
		
		return result;
	}
}
