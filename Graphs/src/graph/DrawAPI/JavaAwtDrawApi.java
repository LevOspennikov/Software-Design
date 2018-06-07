package graph.DrawAPI;

import java.awt.*;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Line2D;
import java.util.*;

public class JavaAwtDrawApi extends Frame implements DrawApi {

    private static int width;
    private static int height;

    private static java.util.List<Circle> circleList = new ArrayList<>();
    private static java.util.List<Line> lineList = new ArrayList<>();

    public JavaAwtDrawApi(int width, int height) {
        JavaAwtDrawApi.width = width;
        JavaAwtDrawApi.height = height;
        setVisible(false);
        setSize(width, height);
        setTitle("Draw graph");
    }

    @Override
    public int getDrawingAreaWidth() {
        return width;
    }

    @Override
    public int getDrawingAreaHeight() {
        return height;
    }

    @Override
    public void drawCircle(int x, int y, int r) {
        circleList.add(new Circle(x, y, r));
    }

    @Override
    public void drawLine(int x1, int y1, int x2, int y2) {
        lineList.add(new Line(x1, x2, y1, y2));
    }

    @Override
    public void finishDrawing() {
        repaint();
        setVisible(true);
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        for (Circle circle : circleList) {
            Graphics2D g2 = (Graphics2D) g;
            g2.setColor(Color.GREEN);
            g2.fill(new Ellipse2D.Double(circle.getX(), circle.getY(), circle.getR(), circle.getR()));
        }
        for (Line line : lineList) {
            Graphics2D g2 = (Graphics2D) g;
            g2.setColor(Color.GREEN);
            g2.draw(new Line2D.Double(line.getX1(), line.getY1(), line.getX2(), line.getY2()));
        }
    }
}
