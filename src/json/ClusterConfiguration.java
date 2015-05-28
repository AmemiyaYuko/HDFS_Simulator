package json;

import java.util.ArrayList;

/**
 * Created by Amemiya on 4/25/15.
 */
public class ClusterConfiguration {
    private ArrayList<DataNodeConfiguration> dataNodeConfigurations = new ArrayList<DataNodeConfiguration>();

    public ClusterConfiguration(String dataNodeConfigFileName) {
        JsonReader jr = new JsonReader(dataNodeConfigFileName);
        for (int i = 0; i < jr.size(); i++) {
            DataNodeConfiguration dc = new DataNodeConfiguration(jr.getConfig(i));
            dataNodeConfigurations.add(dc);
        }

    }

    public ArrayList<DataNodeConfiguration> getDataNodeConfigurations() {
        return dataNodeConfigurations;
    }

    public void setDataNodeConfigurations(ArrayList<DataNodeConfiguration> dataNodeConfigurations) {
        this.dataNodeConfigurations = dataNodeConfigurations;
    }

    public DataNodeConfiguration getDataNodeConfiguration(int i) {
        return dataNodeConfigurations.get(i);
    }

    public int getSize() {
        return dataNodeConfigurations.size();
    }

}
