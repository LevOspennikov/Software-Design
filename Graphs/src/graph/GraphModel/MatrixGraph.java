package graph.GraphModel;

import graph.DrawAPI.DrawApi;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class MatrixGraph extends Graph {
    private boolean[][] matrix;
    private static final int R = 100;
    private static final int radius = 50;
    private int CENTER_X;
    private int CENTER_Y;

    public MatrixGraph(DrawApi drawApi) {
        super(drawApi);
        CENTER_X = drawApi.getDrawingAreaWidth() / 2;
        CENTER_Y = drawApi.getDrawingAreaHeight() / 2;
    }

    public void readGraph() throws IOException {
        System.out.println("Number of vertices:");
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(reader.readLine());
        System.out.println("Fill matrix:");
        matrix = new boolean[n][n];
        for (int i = 0; i < n; i++) {
            String[] line = reader.readLine().split(" ");
            for (int j = 0; j < line.length; j++) {
                matrix[i][j] = line[j].equals("1") || line[j].equals("true");
            }
        }
    }

    private void drawEdge(int x1, int y1, int x2, int y2) {
        drawApi.drawLine(x1 + 20, y1 + 20, x2 + 20, y2 + 20);
    }

    @Override
    public void drawGraph() {
        int n = matrix.length;
        int[] x = new int[n];
        int[] y = new int[n];
        initVertex(n, x, y, CENTER_X, CENTER_Y, R, radius);
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (!matrix[i][j]) {
                    continue;
                }
                drawEdge(x[i], y[i], x[j], y[j]);
            }
        }
        drawApi.finishDrawing();
    }
}