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
    private long totalConsumption = 0;
    private int lastEventID = -1;
    private int entityID;
    private String name;

    Track(String name) {
        this.name = name;
    }

    public void newEvent(String name, double timeConsumption) {
        lastEventID++;
        events.add(new Event(name, timeConsumption));
        totalConsumption += events.get(lastEventID).getConsumption();
    }

    public void output() throws IOException {
        File file = new File(entityID + ".log");
        if (!file.exists()) {
            file.createNewFile();
        }
        FileWriter fileWriter = new FileWriter(file.getName(), true);
        BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
        bufferedWriter.write("Task: \"" + name + "\" :\n");
        for (int i = 0; i < events.size(); i++)
            bufferedWriter.write(events.get(i).toString());
        bufferedWriter.write("----------\nTime Consumption:" + totalConsumption);
        bufferedWriter.close();
        fileWriter.close();

    }
}
