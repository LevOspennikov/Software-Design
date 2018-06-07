package graph.GraphModel;

import graph.DrawAPI.*;

import java.io.IOException;

abstract public class Graph {
    DrawApi drawApi;

    public Graph(DrawApi drawApi) {
        this.drawApi = drawApi;
    }

    void initVertex(int n, int[] x, int[] y, int cx, int cy, int R, int radius) {
        for (int i = 0; i < n; i++) {
            double angle = (2 * Math.PI / n) * i;
            x[i] = cx + (int) (Math.cos(angle) * R);
            y[i] = cy + (int) (Math.sin(angle) * R);
            drawApi.drawCircle(x[i], y[i], radius);
        }
    }

    public abstract void readGraph() throws IOException;
    public abstract void drawGraph();
}

