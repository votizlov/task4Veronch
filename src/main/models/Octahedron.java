package main.models;

import main.math.Matrix4;
import main.math.Vector3;
import main.thirdDimention.IModel;
import main.thirdDimention.PolyLine3D;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Octahedron implements IModel {
    private Vector3 top, left, front;

    public Octahedron() {
        this.top = new Vector3(0,1,0);
        this.left = new Vector3(-1, 0, 0);
        this.front = new Vector3(0,0,1);
    }

    @Override
    public List<PolyLine3D> getLines() {
        List<PolyLine3D> lines = new ArrayList<>();
        List<Vector3> points;
        points = new ArrayList<>();
        points.addAll(Arrays.asList(new Vector3[]
                {
                        new Vector3(front.getX(), front.getY(), -front.getZ()),
                        top,
                        left
                })); // TLF
        lines.add(new PolyLine3D(points, true));
        points.clear();
        points.addAll(Arrays.asList(new Vector3[]
                {
                        new Vector3(front.getX(), front.getY(), -front.getZ()),
                        top,
                        new Vector3(-left.getX(), left.getY(), left.getZ())
                })); // TRF
        lines.add(new PolyLine3D(points, true));
        points.clear();
        points.addAll(Arrays.asList(new Vector3[]
                {
                        left,
                        front,
                        top
                })); // TLN
        lines.add(new PolyLine3D(points, true));
        points.clear();
        points.addAll(Arrays.asList(new Vector3[]
                {
                        new Vector3(-left.getX(), left.getY(), left.getZ()),
                        front,
                        top
                })); // TRN
        lines.add(new PolyLine3D(points, true));
        points.clear();
        points.addAll(Arrays.asList(new Vector3[]
                {
                        left,
                        new Vector3(front.getX(), front.getY(), -front.getZ()),
                        new Vector3(top.getX(), -top.getY(), top.getZ()),
                })); // BLF
        lines.add(new PolyLine3D(points, true));
        points.clear();
        points.addAll(Arrays.asList(new Vector3[]
                {
                        new Vector3(-left.getX(), left.getY(), left.getZ()),
                        new Vector3(front.getX(), front.getY(), -front.getZ()),
                        new Vector3(top.getX(), -top.getY(), top.getZ()),
                })); // BRF
        lines.add(new PolyLine3D(points, true));
        points.clear();
        points.addAll(Arrays.asList(new Vector3[]
                {
                        front,
                        new Vector3(top.getX(), -top.getY(), top.getZ()),
                        left
                })); // BLN
        lines.add(new PolyLine3D(points, true));
        points.clear();
        points.addAll(Arrays.asList(new Vector3[]
                {
                        front,
                        new Vector3(top.getX(), -top.getY(), top.getZ()),
                        new Vector3(-left.getX(), left.getY(), left.getZ())
                })); // BRN
        lines.add(new PolyLine3D(points, true));
        points.clear();
        return lines;
    }

    @Override
    public void transform(Matrix4 mul) {

    }
}
