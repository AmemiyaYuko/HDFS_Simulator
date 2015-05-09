package logger;

/**
 * Created by Amemiya on 5/2/15.
 */
public class Event {
    private long startTime;
    private long endTime;
    private String name;
    Event(long startTime,long endTime,String name){
        this.startTime=startTime;
        this.endTime=endTime;
        this.name=name;
    }
    public long getConsumption(){
        return endTime-startTime;
    }

    public String toString(){
        return "Event \""+this.name+"\" started at "+this.startTime+" finished at "+this.endTime+". Total consumption: "+this.getConsumption();
    }




}
