package main.models;

import main.math.Matrix4;
import main.math.Vector3;
import main.math.Vector4;
import main.thirdDimention.IModel;
import main.thirdDimention.PolyLine3D;

import java.util.ArrayList;
import java.util.List;

public class Line3D implements IModel {
    private Vector3 p1, p2;

    public Line3D(Vector3 p1, Vector3 p2) {
        this.p1 = p1;
        this.p2 = p2;
    }

    public Line3D(PolyLine3D p){
        this.p1 = p.getPoints().get(0);
        this.p2 = p.getPoints().get(0);
    }

    public Vector3 getP1() {
        return p1;
    }

    public void setP1(Vector3 p1) {
        this.p1 = p1;
    }

    public Vector3 getP2() {
        return p2;
    }

    public void setP2(Vector3 p2) {
        this.p2 = p2;
    }

    @Override
    public List<PolyLine3D> getLines() {
        List<Vector3> points = new ArrayList<>();
        points.add(p1);
        points.add(p2);
        List<PolyLine3D> lines = new ArrayList<>();
        lines.add(new PolyLine3D(points, false));
        return lines;
    }

    @Override
    public void transform(Matrix4 mul) {

    }
}
