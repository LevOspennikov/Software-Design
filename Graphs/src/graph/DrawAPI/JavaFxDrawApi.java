package graph.DrawAPI;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.List;

public class JavaFxDrawApi extends Application implements DrawApi {
    private static int width;
    private static int height;

    private static List<Circle> circleList = new ArrayList<>();
    private static List<Line> lineList = new ArrayList<>();


    public JavaFxDrawApi(int width, int height) {
        JavaFxDrawApi.width = width;
        JavaFxDrawApi.height = height;
    }

    public JavaFxDrawApi() {}

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
        launch();
    }

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Drawing graph");
        Group root = new Group();
        Canvas canvas = new Canvas(width, height);
        GraphicsContext gc = canvas.getGraphicsContext2D();
        gc.setFill(Color.GREEN);
        for (Circle circle : circleList) {
            gc.fillOval(circle.getX(), circle.getY(), circle.getR(), circle.getR());
        }
        circleList = new ArrayList<>();
        for (Line line : lineList) {
            gc.strokeLine(line.getX1(), line.getY1(), line.getX2(), line.getY2());
        }
        lineList = new ArrayList<>();
        root.getChildren().add(canvas);
        primaryStage.setScene(new Scene(root));
        primaryStage.show();
    }
}
