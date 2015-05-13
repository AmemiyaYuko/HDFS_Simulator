package logger;

/**
 * Created by Amemiya on 5/2/15.
 */
public class Event {
    private double timeConsumption;
    private String name;
    Event(String name,double timeConsumption){
        this.name=name;
        this.timeConsumption=timeConsumption;
    }
    public double getConsumption(){
        return this.timeConsumption;
    }

    public String toString(){
        return "Event \""+this.name+", time consumption: "+this.getConsumption();
    }




}
