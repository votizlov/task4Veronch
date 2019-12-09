package main.thirdDimention;

import main.math.Matrix4;

import java.util.List;

public interface IModel {
    List<PolyLine3D> getLines();

    void rotate(Matrix4 mul);
}
