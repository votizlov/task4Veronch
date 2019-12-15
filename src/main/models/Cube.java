package main.models;

import main.math.Matrix4;
import main.math.Vector3;
import main.thirdDimention.IModel;
import main.thirdDimention.PolyLine3D;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Cube implements IModel {
    private Vector3 TLF, BRN; // верхняя левая дальняя и правая нижняя ближняя

    public Cube() {
        this.TLF = new Vector3(-1/Math.sqrt(2), 1/Math.sqrt(2), -1/Math.sqrt(2));
        this.BRN = new Vector3(1/Math.sqrt(2), -1/Math.sqrt(2), 1/Math.sqrt(2)); //cos45
    }

    @Override
    public List<PolyLine3D> getLines() {
        List<PolyLine3D> lines = new ArrayList<>();
        List<Vector3> points;
        points = new ArrayList<>();
        points.addAll(Arrays.asList(new Vector3[]
                {
                        new Vector3(TLF.getX(), TLF.getY(), TLF.getZ()),
                        new Vector3(TLF.getX(), TLF.getY(), BRN.getZ()),
                        new Vector3(BRN.getX(), TLF.getY(), BRN.getZ()),
                        new Vector3(BRN.getX(), TLF.getY(), TLF.getZ())

                })); // top
        lines.add(new PolyLine3D(points, true));
        points.clear();
        points.addAll(Arrays.asList(new Vector3[]
                {
                        new Vector3(TLF.getX(), BRN.getY(), TLF.getZ()),
                        new Vector3(TLF.getX(), BRN.getY(), BRN.getZ()),
                        new Vector3(BRN.getX(), BRN.getY(), BRN.getZ()),
                        new Vector3(BRN.getX(), BRN.getY(), TLF.getZ())
                })); // bottom
        lines.add(new PolyLine3D(points, true));
        points.clear();
        points.addAll(Arrays.asList(new Vector3[]
                {
                        new Vector3(TLF.getX(), TLF.getY(), TLF.getZ()),
                        new Vector3(TLF.getX(), BRN.getY(), TLF.getZ()),
                        new Vector3(TLF.getX(), BRN.getY(), BRN.getZ()),
                        new Vector3(TLF.getX(), TLF.getY(), BRN.getZ())
                })); // left
        lines.add(new PolyLine3D(points, true));
        points.clear();
        points.addAll(Arrays.asList(new Vector3[]
                {
                        new Vector3(BRN.getX(), TLF.getY(), TLF.getZ()),
                        new Vector3(BRN.getX(), BRN.getY(), TLF.getZ()),
                        new Vector3(BRN.getX(), BRN.getY(), BRN.getZ()),
                        new Vector3(BRN.getX(), TLF.getY(), BRN.getZ())
                })); // right
        lines.add(new PolyLine3D(points, true));
        points.clear();
        points.addAll(Arrays.asList(new Vector3[]
                {
                        new Vector3(TLF.getX(), TLF.getY(), BRN.getZ()),
                        new Vector3(TLF.getX(), BRN.getY(), BRN.getZ()),
                        new Vector3(BRN.getX(), BRN.getY(), BRN.getZ()),
                        new Vector3(BRN.getX(), TLF.getY(), BRN.getZ())
                })); // front
        lines.add(new PolyLine3D(points, true));
        points.clear();
        points.addAll(Arrays.asList(new Vector3[]
                {
                        new Vector3(TLF.getX(), BRN.getY(), TLF.getZ()),
                        new Vector3(BRN.getX(), BRN.getY(), TLF.getZ()),
                        new Vector3(BRN.getX(), TLF.getY(), TLF.getZ()),
                        new Vector3(TLF.getX(), TLF.getY(), TLF.getZ())
                })); // back
        lines.add(new PolyLine3D(points, true));
        points.clear();
        return lines;
    }

    @Override
    public void transform(Matrix4 mul) {

    }
}
