package tracer;

/**
 * Created by Amemiya on 5/2/15.
 */
public class Event implements Comparable<Event>{
    private int entityID;
    private int eventID;
    private long startTime;
    private long endTime;
    Event(int entityID, int eventID){
        this.entityID=entityID;
        this.eventID=eventID;
    }
    public void start(){
        startTime=System.nanoTime();
    }
    public void end(){
        endTime=System.nanoTime();
    }
    public long consumption(){
        return endTime-startTime;
    }

    public int getEntityID() {
        return entityID;
    }

    public int getEventID() {
        return eventID;
    }

    @Override
    public int compareTo(Event o) {
        if ((this.entityID==o.getEntityID())&&(this.eventID==o.getEventID())) return 1;
        return 0;
    }


}
