package hdfssim;

import eduni.simjava.Sim_event;
import eduni.simjava.Sim_predicate;

/**
 * Created by Amemiya on 4/28/15.
 */
public class HDFSSimTags{
    public static final int END_OF_SIMULATION=-1;
    public static final int BASE=1000;
    public static final int READ_BLOCK=BASE+1;
    public static final int WRITE_BLOCK=BASE+2;
    public static final int WRITE_BLOCK_FIN=BASE+3;

}
