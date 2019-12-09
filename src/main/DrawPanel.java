package main;

import main.math.Matrix4Factories;
import main.math.Vector3;
import main.math.Vector4;
import main.models.Icosahedron;
import main.models.Line3D;
import main.thirdDimention.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class DrawPanel extends JPanel implements MouseMotionListener, MouseListener, MouseWheelListener, KeyListener {
    private ScreenConverter sc;
    private Camera camera;
    private Scene scene;
    private IModel tgtModel;


    public DrawPanel() {
        super();
        sc = new ScreenConverter(-2, 2, 4, 4, 500, 500);
        camera = new Camera();
        scene = new Scene();

        scene.models.add(new Line3D(new Vector3(0, 0, 0), new Vector3(0, 0, 1)));
        scene.models.add(new Line3D(new Vector3(0, 0, 0), new Vector3(0, 1, 0)));
        scene.models.add(new Line3D(new Vector3(0, 0, 0), new Vector3(1, 0, 0)));

       // scene.models.add(new Cube());
        //scene.models.add(new Tetrahedron());
        //scene.models.add(new Octahedron());
        tgtModel = new Icosahedron();
        scene.models.add(tgtModel);

        addMouseListener(this);
        addMouseMotionListener(this);
        addKeyListener(this);
        addMouseWheelListener(this);
    }

    @Override
    public void paint(Graphics g) {
        g.drawImage(scene.drawScene(sc, camera), 0, 0, null);
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {
    }

    @Override
    public void keyReleased(KeyEvent e) {
    }    /*Здесь запоминаем последнее положение мыши, для которого обрабатывали событие*/
    private Point last;
    /*Флаг, фиксирующий, зажата ли сейчас левая кнопка мыши*/
    private boolean leftFlag = false;
    /*Флаг, фиксирующий, зажата ли сейчас правая кнопка мыши*/
    private boolean rightFlag = false;
    /*Флаг, фиксирующий, зажата ли сейчас средняя кнопка мыши*/
    private boolean middleFlag = false;
    @Override
    public void mouseDragged(MouseEvent mouseEvent) {
        Point current = mouseEvent.getPoint();
        if (last != null) {
            /*Вычисляем разницу в пикселях*/
            int dx = current.x - last.x;
            int dy = current.y - last.y;
            /*Если двигаем с зажатой левой кнопкой мыши, то вращаем камеру*/
            if (leftFlag) {
                if(!mouseEvent.isShiftDown()) {
                    double da = dx * Math.PI / 180;
                    double db = dy * Math.PI / 280;
                    camera.modifyRotate(
                            Matrix4Factories.rotationXYZ(da, Matrix4Factories.Axis.Y)
                                    .mul(
                                            Matrix4Factories.rotationXYZ(db, Matrix4Factories.Axis.X)
                                    )
                    );
                } else {
                    double da = dx * Math.PI / 180;
                    double db = dy * Math.PI / 280;
                    tgtModel.rotate(
                            Matrix4Factories.rotationXYZ(da, Matrix4Factories.Axis.Y)
                                    .mul(
                                            Matrix4Factories.rotationXYZ(db, Matrix4Factories.Axis.X)
                                    ));
                }
            }
            /*Если двигаем с зажатой правой кнопкой мыши, то перемещаем камеру вдоль осей X и Y*/
            if (rightFlag) {
                Vector4 zero = new Vector4(sc.s2r(new ScreenPoint(0, 0)),0f);
                Vector4 cur = new Vector4(sc.s2r(new ScreenPoint(dx, dy)), 0f);

                /*Вектор смещения в реальных координатах с точки зрения камеры*/
                Vector3 delta = cur.add(zero.mul(-1)).asVector3();
                camera.modifyTranslate(Matrix4Factories.translation(delta));
            }
            /* Если двигаем с зажатой средней кнопкой мыши, то перемещаем камеру
             * вдоль оси Z на расстояние равное изменению положения мыши в реальных координатах.
             * Направление выбирается положительное при движении вверх.
             */
            if (middleFlag && dy != 0) {
                Vector4 zero = new Vector4(sc.s2r(new ScreenPoint(0, 0)), 0);
                Vector4 cur = new Vector4(sc.s2r(new ScreenPoint(dx, dy)), 0);
                /*Длина вектор смещения в реальных координатах с точки зрения камеры*/
                float length = cur.add(zero.mul(-1)).asVector3().length();
                if (dy < 0)
                    length = -length;
                //System.out.println(length); todo чтоб не потерять важный дебаг
                camera.modifyTranslate(Matrix4Factories.translation(0, 0, length));
            }
        }
        last = current;
        repaint(); /*Оповещаем всех, что мы изменили камеру и её надо перерисовать*/
    }

    @Override
    public void mouseMoved(MouseEvent mouseEvent) {
        /*
        ScreenPoint m = new ScreenPoint(mouseEvent.getX(), mouseEvent.getY());
        l.setP2(sc.s2r(m));
        repaint();
         */
    }


    @Override
    public void mouseClicked(MouseEvent mouseEvent) {
    }

    @Override
    public void mousePressed(MouseEvent mouseEvent) {

        /*Устанавливаем флаги кнопок мыши*/
        if (SwingUtilities.isLeftMouseButton(mouseEvent))
            leftFlag = true;
        if (SwingUtilities.isRightMouseButton(mouseEvent))
            rightFlag = true;
        if (SwingUtilities.isMiddleMouseButton(mouseEvent))
            middleFlag = true;
        last = mouseEvent.getPoint();
    }

    @Override
    public void mouseReleased(MouseEvent mouseEvent) {
        /*Снимаем флаги кнопок мыши*/
        if (SwingUtilities.isLeftMouseButton(mouseEvent))
            leftFlag = false;
        if (SwingUtilities.isRightMouseButton(mouseEvent))
            rightFlag = false;
        if (SwingUtilities.isMiddleMouseButton(mouseEvent))
            middleFlag = false;

        /*Если оба сняты, то забываем точку*/
        if (!leftFlag && !rightFlag && !middleFlag)
            last = null;
    }

    @Override
    public void mouseEntered(MouseEvent mouseEvent) {

    }

    @Override
    public void mouseExited(MouseEvent mouseEvent) {

    }

    private int wsCur, hsCur;

    @Override
    public void mouseWheelMoved(MouseWheelEvent mouseWheelEvent) {
        int delta = mouseWheelEvent.getWheelRotation();
        /*Если зажат Control, то будем менять параметры перспективы, иначе - масштаба*/
        if (mouseWheelEvent.isControlDown()) {
            /*delta*5f - экспериментально подобранное число. Чем меньше, тем быстрее будет изменяться точка схода*/
            camera.modifyProjection(Matrix4Factories.centralProjection(delta*5f, Matrix4Factories.Axis.Z));
        } else {
            /*Вычислим коэффициент масштаба*/
            float factor = 1;
            float scale = delta < 0 ? 0.9f : 1.1f;
            int counter = delta < 0 ? -delta : delta;
            while (counter-- > 0)
                factor *= scale;
            camera.modifyScale(Matrix4Factories.scale(factor));
        }
        repaint();
    }


    //построить контур пересечения по точкам
}
