package graph;


import graph.DrawAPI.DrawApi;
import graph.DrawAPI.JavaAwtDrawApi;
import graph.DrawAPI.JavaFxDrawApi;
import graph.GraphModel.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by ospen on 1/24/2018.
 */
public class Main {
    public static void main(String[] args) throws IOException, Exception {
        List<String> arguments = new ArrayList<>(Arrays.asList(args));
        DrawApi api;
        if (arguments.contains("javafx")) {
            api = new JavaFxDrawApi(600, 400);
        } else if (arguments.contains("javaAwt")) {
            api = new JavaAwtDrawApi(600, 400);
        } else {
            throw new Exception("Wrong Args");
        }
        Graph graph;
        if (arguments.contains("matrix")) {
            graph = new MatrixGraph(api);
        } else if (arguments.contains("edgelist")) {
            graph = new EdgeListGraph(api);
        } else {
            throw new Exception("Wrong Args");
        }
        graph.readGraph();
        graph.drawGraph();
    }
}
