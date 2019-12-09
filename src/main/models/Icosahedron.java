package main.models;

import main.math.Matrix4;
import main.math.Vector3;
import main.math.Vector4;
import main.thirdDimention.IModel;
import main.thirdDimention.PolyLine3D;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class Icosahedron implements IModel {
    private Vector3 top,bottom, p1, p2, p3, p4, p5;
    List<PolyLine3D> lines;

    public Icosahedron() {
        this.top = new Vector3(0, 1, 0);
        this.bottom =  new Vector3(0,-1, 0);
        this.p1 = new Vector3(-Math.cos(Math.toRadians(18)), Math.sin(Math.toRadians(30)), -Math.sin(Math.toRadians(18)));
        this.p2 = new Vector3(0, Math.sin(Math.toRadians(30)), -Math.sin(Math.toRadians(72)));
        this.p3 = new Vector3(Math.cos(Math.toRadians(18)), Math.sin(Math.toRadians(30)), -Math.sin(Math.toRadians(18)));
        this.p4 = new Vector3(Math.cos(Math.toRadians(54)), Math.sin(Math.toRadians(30)), Math.sin(Math.toRadians(54)));
        this.p5 = new Vector3(-Math.cos(Math.toRadians(54)), Math.sin(Math.toRadians(30)), Math.sin(Math.toRadians(54)));
        this.lines = getLines();
    }

    @Override
    public List<PolyLine3D> getLines() {
        if (lines == null) {
            lines = new ArrayList<>();
            List<Vector3> points;
            points = new ArrayList<>();
            points.addAll(Arrays.asList(new Vector3[]
                    {
                            p1,
                            p2,
                            top
                    })); // T1
            lines.add(new PolyLine3D(points, true));
            points.clear();
            points.addAll(Arrays.asList(new Vector3[]
                    {
                            p2,
                            p3,
                            top
                    })); // T2
            lines.add(new PolyLine3D(points, true));
            points.clear();
            points.addAll(Arrays.asList(new Vector3[]
                    {
                            p3,
                            p4,
                            top
                    })); // T3
            lines.add(new PolyLine3D(points, true));
            points.clear();
            points.addAll(Arrays.asList(new Vector3[]
                    {
                            p4,
                            p5,
                            top
                    })); // T4
            lines.add(new PolyLine3D(points, true));
            points.clear();
            points.addAll(Arrays.asList(new Vector3[]
                    {
                            p5,
                            p1,
                            top
                    })); // T5
            lines.add(new PolyLine3D(points, true));
            points.clear();
            points.addAll(Arrays.asList(new Vector3[]
                    {
                            new Vector3(-p1.getX(), -p1.getY(), -p1.getZ()),
                            new Vector3(-p2.getX(), -p2.getY(), -p2.getZ()),
                            new Vector3(top.getX(), bottom.getY(), top.getZ())
                    })); // B1
            lines.add(new PolyLine3D(points, true));
            points.clear();
            points.addAll(Arrays.asList(new Vector3[]
                    {
                            new Vector3(-p2.getX(), -p2.getY(), -p2.getZ()),
                            new Vector3(-p3.getX(), -p3.getY(), -p3.getZ()),
                            new Vector3(top.getX(), bottom.getY(), top.getZ())
                    })); // B2
            lines.add(new PolyLine3D(points, true));
            points.clear();
            points.addAll(Arrays.asList(new Vector3[]
                    {
                            new Vector3(-p3.getX(), -p3.getY(), -p3.getZ()),
                            new Vector3(-p4.getX(), -p4.getY(), -p4.getZ()),
                            new Vector3(top.getX(), bottom.getY(), top.getZ())
                    })); // B3
            lines.add(new PolyLine3D(points, true));
            points.clear();
            points.addAll(Arrays.asList(new Vector3[]
                    {
                            new Vector3(-p4.getX(), -p4.getY(), -p4.getZ()),
                            new Vector3(-p5.getX(), -p5.getY(), -p5.getZ()),
                            new Vector3(top.getX(), bottom.getY(), top.getZ())
                    })); // B4
            lines.add(new PolyLine3D(points, true));
            points.clear();
            points.addAll(Arrays.asList(new Vector3[]
                    {
                            new Vector3(-p5.getX(), -p5.getY(), -p5.getZ()),
                            new Vector3(-p1.getX(), -p1.getY(), -p1.getZ()),
                            new Vector3(top.getX(), bottom.getY(), top.getZ())
                    })); // B5
            lines.add(new PolyLine3D(points, true));
            points.clear();
            points.addAll(Arrays.asList(new Vector3[]
                    {
                            p1,
                            p2,
                            new Vector3(-p4.getX(), -p4.getY(), -p4.getZ())
                    })); // C1
            lines.add(new PolyLine3D(points, true));
            points.clear();
            points.addAll(Arrays.asList(new Vector3[]
                    {
                            new Vector3(-p4.getX(), -p4.getY(), -p4.getZ()),
                            new Vector3(-p5.getX(), -p5.getY(), -p5.getZ()),
                            p2
                    })); // C2
            lines.add(new PolyLine3D(points, true));
            points.clear();
            points.addAll(Arrays.asList(new Vector3[]
                    {
                            p2,
                            p3,
                            new Vector3(-p5.getX(), -p5.getY(), -p5.getZ())
                    })); // C3
            lines.add(new PolyLine3D(points, true));
            points.clear();
            points.addAll(Arrays.asList(new Vector3[]
                    {
                            new Vector3(-p5.getX(), -p5.getY(), -p5.getZ()),
                            new Vector3(-p1.getX(), -p1.getY(), -p1.getZ()),
                            p3
                    })); // C4
            lines.add(new PolyLine3D(points, true));
            points.clear();
            points.addAll(Arrays.asList(new Vector3[]
                    {
                            p3,
                            p4,
                            new Vector3(-p1.getX(), -p1.getY(), -p1.getZ())
                    })); // C5
            lines.add(new PolyLine3D(points, true));
            points.clear();
            points.addAll(Arrays.asList(new Vector3[]
                    {
                            new Vector3(-p1.getX(), -p1.getY(), -p1.getZ()),
                            new Vector3(-p2.getX(), -p2.getY(), -p2.getZ()),
                            p4
                    })); // C6
            lines.add(new PolyLine3D(points, true));
            points.clear();
            points.addAll(Arrays.asList(new Vector3[]
                    {
                            p4,
                            p5,
                            new Vector3(-p2.getX(), -p2.getY(), -p2.getZ())
                    })); // C7
            lines.add(new PolyLine3D(points, true));
            points.clear();
            points.addAll(Arrays.asList(new Vector3[]
                    {
                            new Vector3(-p2.getX(), -p2.getY(), -p2.getZ()),
                            new Vector3(-p3.getX(), -p3.getY(), -p3.getZ()),
                            p5
                    })); // C8
            lines.add(new PolyLine3D(points, true));
            points.clear();
            points.addAll(Arrays.asList(new Vector3[]
                    {
                            p5,
                            p1,
                            new Vector3(-p3.getX(), -p3.getY(), -p3.getZ())
                    })); // C9
            lines.add(new PolyLine3D(points, true));
            points.clear();
            points.addAll(Arrays.asList(new Vector3[]
                    {
                            new Vector3(-p3.getX(), -p3.getY(), -p3.getZ()),
                            new Vector3(-p4.getX(), -p4.getY(), -p4.getZ()),
                            p1
                    })); // C10
            lines.add(new PolyLine3D(points, true));
            points.clear();
        }
        return lines;
    }

    @Override
    public void rotate(Matrix4 mul) {
        List<PolyLine3D> line3DS = new ArrayList<>();
        for (PolyLine3D line3D:lines
             ) {
            LinkedList<Vector3> t = new LinkedList<>();
            for (Vector3 v:line3D.getPoints()
                 ) {
                t.add(mul.mul(new Vector4(v,1)).asVector3());
            }
            line3DS.add(new PolyLine3D(t,true));
        }
        this.lines = line3DS;
    }
}
