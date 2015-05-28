package hdfssim;

/**
 * Created by Amemiya on 5/21/15.
 */
public class WriteNewFileRequest {
    private String fileName;
    private double size;

    public WriteNewFileRequest(String fileName, double size) {
        this.fileName = fileName;
        this.size = size;
    }

    public String getFileName() {
        return fileName;
    }

    public double getSize() {
        return size;
    }

    @Override
    public String toString() {
        return new String("Write request: file name=" + fileName + " size=" + size + '\n');
    }
}
