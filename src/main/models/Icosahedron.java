package main.models;

import com.sun.scenario.effect.light.SpotLight;
import main.math.Matrix4;
import main.math.Matrix4Factories;
import main.math.Vector3;
import main.math.Vector4;
import main.thirdDimention.IModel;
import main.thirdDimention.PolyLine3D;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class Icosahedron implements IModel {
    private Vector3 center;
    private double r;
    List<PolyLine3D> lines;

    public Icosahedron(Vector3 center, double r) {
        this.center = center;
        this.r = r;
        this.lines = getLines();
    }

    @Override
    public List<PolyLine3D> getLines() {
        if (lines == null) {
            lines = new ArrayList<>();
            Vector3 top = new Vector3(0, r, 0);
            Vector3 bottom = new Vector3(0, -r, 0);
            List<Vector3> topCircle = new Pentagon(
                    new Vector3(0, r * 2 / 3, 0),
                    Matrix4Factories.rotationXYZ(26.57, Matrix4Factories.Axis.Z)
                            .mul(new Vector4(top, 1)).asVector3(),
                    new Vector3(0, 1, 0),5).lines.get(0).getPoints();
            List<Vector3> bottomCircle = new Pentagon(
                    new Vector3(0, -r * 2 / 3, 0),
                    Matrix4Factories.rotationXYZ(26.57, Matrix4Factories.Axis.Z)
                            .mul(new Vector4(bottom, 1)).asVector3(),
                    new Vector3(0, 1, 0),5).lines.get(0).getPoints();
            for(int i = 0;i<4;i++){
                lines.add(new PolyLine3D(Arrays.asList(
                        topCircle.get(i),
                        topCircle.get(i+1),
                        bottom,
                        topCircle.get(i)
                ),true));
            }
            for(int i = 0;i<4;i++){
                lines.add(new PolyLine3D(Arrays.asList(
                        bottomCircle.get(i),
                        bottomCircle.get(i+1),
                        top,
                        bottomCircle.get(i)
                ),true));
            }
            lines.addAll(connectCircles(topCircle,bottomCircle));
            lines.add(new PolyLine3D(Arrays.asList(top, bottom), true));
            lines.add(new PolyLine3D(topCircle, true));
            lines.add(new PolyLine3D(bottomCircle, true));
        }
        return lines;
    }

    private LinkedList<PolyLine3D> connectCircles(List<Vector3> t, List<Vector3> t1) {
        LinkedList<PolyLine3D> line = new LinkedList<>();
        int j = 2;
        for (int i = 0; i < t.size(); i++) {
            line.add(new PolyLine3D(Arrays.asList(
                    t.get(i),
                    t1.get(j)), true));
            j++;
            if (j==5)
                j=0;
        }
        j = 2;
        for (int i = 0; i < t.size(); i++) {
            line.add(new PolyLine3D(Arrays.asList(
                    t1.get(i),
                    t.get(j)), true));
            j++;
            if (j==5)
                j=0;
        }
        return line;
    }

    @Override
    public void transform(Matrix4 mul) {
        List<PolyLine3D> line3DS = new ArrayList<>();
        for (PolyLine3D line3D : lines
        ) {
            LinkedList<Vector3> t = new LinkedList<>();
            for (Vector3 v : line3D.getPoints()
            ) {
                t.add(mul.mul(new Vector4(v, 1)).asVector3());
            }
            line3DS.add(new PolyLine3D(t, true));
        }
        this.lines = line3DS;
    }
}
