import java.io.IOException;

import densityEstimator.NADE.BiNADE;
import densityEstimator.NADE.MixNADE;
import densityEstimator.NADE.NADE;
import densityEstimator.data.Data;
import densityEstimator.dataset.Mushroom;
import densityEstimator.utility.DataSetUtils;


public class app {
    public static void main(String[] args) throws IOException{
        //Data data = SyntheticData.createSyntheticData();
        Mushroom m = new Mushroom();
        Data data = m.create();
        DataSetUtils util = new DataSetUtils(data,0.1);
        data = util.getTestData();
        System.out.println(data.getInstance(0).size());
        NADE nade = new BiNADE(100, data.getInstance(0).size(), data);
        nade.learn();
        //MixNADE mixed = new MixNADE(2, data);
        //mixed.learn();
        
    }
}
