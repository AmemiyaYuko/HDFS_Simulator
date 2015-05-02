package tracer;

import java.util.ArrayList;

/**
 * Created by Amemiya on 5/2/15.
 */

// Every single query request has its own Tracer.
public class Tracer {
    private ArrayList<Event> events=new ArrayList<Event>();
    private ArrayList<Long> singleEventConsumption=new ArrayList<Long>();
    private long totalConsumption=0;
    private int lastEventID=-1;
    private int entityID;
    Tracer(int entityID){
        this.entityID=entityID;
    }
    public void newEvent(){
        events.add(new Event(this.entityID,++lastEventID));
        events.get(lastEventID).start();
    }
    public void finish(){
        events.get(lastEventID).end();
        singleEventConsumption.add(new Long(events.get(lastEventID).consumption()));
        totalConsumption+=events.get(lastEventID).consumption();
    }
}
