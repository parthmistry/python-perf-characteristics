package posts.parthmistry.javasamples.utils;

public class ElapsedTimeMonitor {

    private final long startTime;

    public ElapsedTimeMonitor() {
        this.startTime = System.currentTimeMillis();
    }

    public long getElapsedTimeMillis() {
        return System.currentTimeMillis() - this.startTime;
    }

}
