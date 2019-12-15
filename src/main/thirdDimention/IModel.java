package main.thirdDimention;

import main.math.Matrix4;

import java.util.List;

public interface IModel extends Transformable {
    List<PolyLine3D> getLines();
}
