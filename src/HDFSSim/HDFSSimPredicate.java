package hdfssim;

import eduni.simjava.Sim_event;
import eduni.simjava.Sim_predicate;

/**
 * Created by Amemiya on 5/14/15.
 */
public class HDFSSimPredicate extends Sim_predicate {
    private int tag;
    public HDFSSimPredicate(int i){
        this.tag=i;
    }
    @Override
    public boolean match(Sim_event sim_event) {
        return (tag==sim_event.get_tag());
    }
}
