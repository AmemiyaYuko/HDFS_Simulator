package hdfssim;

/**
 * Created by Amemiya on 5/21/15.
 */
public class ReadFileRequest {
    private String fileName;
    private double offset;

    public ReadFileRequest(String fileName, double offset) {
        this.fileName = fileName;
        this.offset = offset;
    }

    public String getFileName() {
        return fileName;
    }

    public double getOffset() {
        return offset;
    }
}
