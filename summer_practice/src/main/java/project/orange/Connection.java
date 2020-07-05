package project.orange;

public class Connection {
    private final int edgeWeight;
    private int pathLength;
    private String path;

    public Connection(int weight) {
        pathLength = edgeWeight = weight;
    }

    public int getWeight() {
        return edgeWeight;
    }

    public int getPathLength() {
        return pathLength;
    }

    public String getPath() {
        return path;
    }

    public void changePath(int pathLength, String path) {
        this.pathLength = pathLength;
        this.path = path;
    }
}