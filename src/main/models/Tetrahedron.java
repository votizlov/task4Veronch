package main.models;

import main.math.Matrix4;
import main.math.Vector3;
import main.thirdDimention.IModel;
import main.thirdDimention.PolyLine3D;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Tetrahedron implements IModel {
    private Vector3 p1, p2, p3, pTop;

    public Tetrahedron() {
        this.p1 = new Vector3(0, (float)-Math.sqrt(2) / 3, (float)(-2 * Math.sqrt(2) / 3));
        this.p2 = new Vector3((float)-Math.sqrt(6) / 3, (float)-Math.sqrt(2) / 3, (float) Math.sqrt(2) / 3);
        this.p3 = new Vector3((float) Math.sqrt(6) / 3, (float)-Math.sqrt(2) / 3, (float) Math.sqrt(2) / 3);
        this.pTop = new Vector3(0, 1, 0);
    }

    @Override
    public List<PolyLine3D> getLines() {
        List<PolyLine3D> lines = new ArrayList<>();
        List<Vector3> points;
        points = new ArrayList<>();
        points.addAll(Arrays.asList(new Vector3[]
                {
                        p1, p2, p3
                })); // bottom
        lines.add(new PolyLine3D(points, true));
        points.clear();
        points.addAll(Arrays.asList(new Vector3[]
                {
                        p1, p2, pTop
                })); // left
        lines.add(new PolyLine3D(points, true));
        points.clear();
        points.addAll(Arrays.asList(new Vector3[]
                {
                        p3, p1, pTop
                })); // right
        lines.add(new PolyLine3D(points, true));
        points.clear();
        points.addAll(Arrays.asList(new Vector3[]
                {
                        p2, p3, pTop
                })); // back
        lines.add(new PolyLine3D(points, true));
        points.clear();
        return lines;
    }

    @Override
    public void rotate(Matrix4 mul) {

    }
}
