package json;

import HDFSSim.DataNode;

import java.util.ArrayList;

/**
 * Created by Amemiya on 4/25/15.
 */
public class ClusterConfiguration {
    private static ArrayList<DataNodeConfiguration> dataNodeConfigurations;

    public static ArrayList<DataNodeConfiguration> getDataNodeConfigurations() {
        return dataNodeConfigurations;
    }

    public static DataNodeConfiguration getDataNodeConfiguration(int i){
        return dataNodeConfigurations.get(i);
    }

    public void setDataNodeConfigurations(ArrayList<DataNodeConfiguration> dataNodeConfigurations) {
        this.dataNodeConfigurations = dataNodeConfigurations;
    }
}
