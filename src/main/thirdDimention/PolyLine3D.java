package main.thirdDimention;

import main.math.Vector3;

import java.util.ArrayList;
import java.util.List;

public class PolyLine3D {
    private List<Vector3> points;
    private boolean closed;

    public PolyLine3D(List<Vector3> p, boolean closed) {
        points = new ArrayList<>(p);
        this.closed = closed;
    }

    public boolean isClosed(){
        return closed;
    }

    public List<Vector3> getPoints() {
        return points;
    }

    public float avgZ() {
        if (points == null || points.size() == 0)
            return 0;
        float sum = 0;
        for (Vector3 v : points)
            sum += v.getZ();
        return sum / points.size();
    }
}
