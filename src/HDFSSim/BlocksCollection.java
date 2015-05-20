package hdfssim;

import eduni.simjava.Sim_system;

import java.util.HashSet;

/**
 * Created by Amemiya on 4/28/15.
 * Block collection class take the place of block report which serves as a important part of real hadoop system
 */
public class BlocksCollection {
    private HashSet<Long> allBlocks = new HashSet<Long>(0);

    public boolean contains(long id) {
        return allBlocks.contains(new Long(id));
    }

    public void put(long id) {
        allBlocks.add(new Long(id));
    }

    public long requestNewID() {
        long id = 0;
        while ((id < Math.pow(10, 14)) || (this.contains(id))){
            id = (long) ((Math.random() * Math.pow(10, 15) + Math.pow(10, 14)) % Math.pow(10, 15));
        }

        this.put(id);
        return id;
    }

    public void clean() {
        allBlocks = new HashSet<Long>(0);
    }
}
