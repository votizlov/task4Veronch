package main.thirdDimention;

import main.math.Vector3;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;

public class Scene {
    public List<IModel> models = new ArrayList<>();
    public double aaa = 50;
    public BufferedImage drawScene(ScreenConverter sc, Camera c) {
        BufferedImage bi = new BufferedImage(sc.getWs(), sc.getHs(), BufferedImage.TYPE_INT_RGB);
        Graphics2D g = (Graphics2D) bi.getGraphics();
        /**/
        ArrayList<PolyLine3D> lines = new ArrayList<>();
        if (models != null) {
            for (IModel m : models) {
                if (m.getLines() != null) {
                    for (PolyLine3D pl : m.getLines()) {
                        List<Vector3> vv = new LinkedList<>();
                        for (Vector3 v : pl.getPoints()) {
                            //изменяем этот вектор через матрицу поворота, передавая параметр того, на скок поворачиваем
                            //через таймеры, (у нас в методе Matrix4.rotate(сюда этот угол кидаем)
                            vv.add(c.w2s(v));
                        }
                        lines.add(new PolyLine3D(vv, pl.isClosed()));
                    }
                }
            }
        }
        lines.sort(new Comparator<PolyLine3D>() {
            @Override
            public int compare(PolyLine3D o1, PolyLine3D o2) {
                return (int) Math.signum(o1.avdZ() - o2.avdZ());
            }
        });

        g.setColor(Color.cyan);
        g.fillRect(0, 0, bi.getWidth(), bi.getHeight());
        g.setColor(Color.WHITE);
        //g.drawOval(125, 125, 250, 250);
        for (PolyLine3D pl : lines) {
            if (pl.isClosed()) {
                g.setColor(Color.black);
                List<ScreenPoint> points = new ArrayList<>();
                for (int i = 0; i < pl.getPoints().size(); i++) {
                    points.add(sc.r2s(pl.getPoints().get(i)));
                    if (i > 0) {
                        g.drawLine(sc.r2s(pl.getPoints().get(i)).getI(), sc.r2s(pl.getPoints().get(i)).getJ(),
                                sc.r2s(pl.getPoints().get(i - 1)).getI(), sc.r2s(pl.getPoints().get(i - 1)).getJ());
                    }
                }
                //g.setColor(new Color(12, 79, 80, 75));
                g.setColor(new Color(12, 79, 80, 85));
                if (points.size() == 4) {
                    g.fillPolygon(new int[]{points.get(0).getI(), points.get(1).getI(), points.get(2).getI(), points.get(3).getI()},
                            new int[]{points.get(0).getJ(), points.get(1).getJ(), points.get(2).getJ(), points.get(3).getJ()}, 4);
                } else if (points.size() == 3) {
                    g.fillPolygon(new int[]{points.get(0).getI(), points.get(1).getI(), points.get(2).getI()},
                            new int[]{points.get(0).getJ(), points.get(1).getJ(), points.get(2).getJ()}, 3);
                }
            } else {
                g.setColor(Color.WHITE);
                if (pl.getPoints().size() == 1) {
                    g.drawLine(sc.r2s(pl.getPoints().get(0)).getI(), sc.r2s(pl.getPoints().get(0)).getJ(),
                            sc.r2s(pl.getPoints().get(0)).getI(), sc.r2s(pl.getPoints().get(0)).getJ());
                } else {
                    ScreenPoint last = sc.r2s(pl.getPoints().get(0));
                    for (int i = 1; i < pl.getPoints().size(); i++) {
                        ScreenPoint np = sc.r2s(pl.getPoints().get(i));
                        g.drawLine(last.getI(), last.getJ(), np.getI(), np.getJ());
                        last = np;
                    }

                    if (pl.isClosed()) {
                        ScreenPoint np = sc.r2s(pl.getPoints().get(0));
                        g.drawLine(last.getI(), last.getJ(), np.getI(), np.getJ());
                    }
                }
            }
        }

        /**/
        g.dispose();
        return bi;
    }


}
