package main.models;

import main.math.Matrix4;
import main.math.Matrix4Factories;
import main.math.Vector3;
import main.math.Vector4;
import main.thirdDimention.IModel;
import main.thirdDimention.PolyLine3D;

import java.util.LinkedList;
import java.util.List;

import static java.lang.Math.cos;
import static java.lang.Math.toRadians;

public class Circle implements IModel {
    Vector3 center, rV;
    LinkedList<PolyLine3D> lines;

    public Vector3 getCenter() {
        return center;
    }

    public Vector3 getrV() {
        return rV;
    }

    public Circle(Vector3 center, Vector3 rV, Vector3 cV, int res) {
        cV.normalize();
        this.center = center;
        this.rV = rV;
        lines = new LinkedList<>();
        LinkedList<Vector3> points = new LinkedList<>();
        Vector3 translateV = new Vector3(center, new Vector3(0, 0, 0));

        double dA = toRadians(360 / (double) res);
        Matrix4 turnM;
        Matrix4 transM = Matrix4Factories.translation(translateV);

        for (int i = 0; i < res; i++) {
            turnM = Matrix4Factories.rorationAroundVector(cV, (float) cos(dA));

            rV = turnM.mul(new Vector4(rV, 1)).asVector3();
            points.add(rV);
            //поворот на угол dA
        }
        LinkedList<Vector3> t = new LinkedList<>();
        for (int i = 0; i < points.size(); i++) {
            t.add(transM.mul(new Vector4(points.get(i),1)).asVector3());
        }

        lines.add(new PolyLine3D(t, true));
    }

    @Override
    public List<PolyLine3D> getLines() {
        return lines;
    }

    @Override
    public void transform(Matrix4 mul) {

    }
}
