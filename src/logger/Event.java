package logger;

/**
 * Created by Amemiya on 5/2/15.
 */
public class Event {
    private double clock;
    private String name;

    Event(String name, double timeConsumption) {
        this.name = name;
        this.clock = timeConsumption;
    }

    public double getClock() {
        return this.clock;
    }

    public String toString() {
        return this.getClock()+" : "+this.name+'\n';
    }


}
