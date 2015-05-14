package hdfssim;

import eduni.simjava.Sim_event;
import eduni.simjava.Sim_predicate;

/**
 * Created by Amemiya on 4/28/15.
 */
public class HDFSSimTags{
    public static final int END_OF_SIMULATION=-1;
    public static final int BASE=1000;
    public static final int READ_REPLICA=BASE+1;
    public static final int READ_REPLICA_FIN=BASE+2;
    public static final int WRITE_REPLICA=BASE+3;
    public static final int WRITE_REPLICA_FIN=BASE+4;

}
