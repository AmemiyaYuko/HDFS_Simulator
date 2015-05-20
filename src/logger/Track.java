package logger;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by Amemiya on 5/2/15.
 */

// Every single query request has its own Tracer.
public class Track {
    private ArrayList<Event> events = new ArrayList<Event>();
    private long lastClock = 0;
    private int lastEventID = -1;
    private double startClock;
    private String name;

    Track(String name,double startClock) {
        this.name = name;
        this.startClock=startClock;
    }

    public void newEvent(String name, double clock) {
        lastEventID++;
        events.add(new Event(name, clock));
        lastClock += events.get(lastEventID).getClock();
    }

    public void output() throws IOException {
        File file = new File(name + ".log");
        if (!file.exists()) {
            file.createNewFile();
        }
        FileWriter fileWriter = new FileWriter(file.getName(), true);
        BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
        bufferedWriter.write("Task: \"" + name + "\" :\n");
        bufferedWriter.write("Start at : "+this.startClock+". End at : "+this.lastClock+'\n');
        for (int i = 0; i < events.size(); i++)
            bufferedWriter.write(events.get(i).toString());
        bufferedWriter.write("----------\nTime Consumption:" + String.valueOf(this.lastClock-this.startClock));
        bufferedWriter.close();
        fileWriter.close();

    }
}
