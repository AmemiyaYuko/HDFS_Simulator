package logger;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by Amemiya on 5/9/15.
 */
public class Logger {
    public static ArrayList<Track> tracks = new ArrayList<Track>();
    private static int lastTrackID = -1;
    private static int numOfRequests;

    public static int newTrack(String name, double startClock) {
        lastTrackID++;
        tracks.add(new Track(name, startClock));
        return lastTrackID;
    }

    private static boolean deleteDir(File dir) {
        if (dir.isDirectory()) {
            String[] children = dir.list();
            for (int i = 0; i < children.length; i++) {
                boolean success = deleteDir(new File(dir, children[i]));
                if (!success) {
                    return false;
                }
            }
        }
        return true;
    }

    public static int getLastTrackID() {
        return lastTrackID;
    }

    public static void output() throws IOException {
        for (int i = 0; i < tracks.size(); i++)
            tracks.get(i).output();
    }

    public static int getNumberOfRequests() {
        return numOfRequests;
    }

    public static void setNumberOfRequests(int number) {
        numOfRequests = number;
    }

    public static void newEvent(int trackID, String name, double clock) {
        tracks.get(trackID).newEvent(name, clock);
    }

}
