package project.orange;

import javax.swing.*;
import java.util.ArrayList;

public class GraphController {
    private  Graph graph;
    GraphDrawer drawer;
    static final int maxVertices = 10;
    static final int maxWeight = 100;


    public void consoleReader(String input) {
        if (!syntaxAnalizer(input)) {
            return;
        }
        graph = new Graph(input);
        drawer = new GraphDrawer(getVertex(), getMatrix());

        getCurrentState();
    }

    public void randomGraph(String input) {
        if (input == null) { return; }
        int verNum = Integer.valueOf(input);
        graph = GraphGenerator.generateRandom(verNum);
        drawer = new GraphDrawer(getVertex(), getMatrix());
        getCurrentState();
    }

    protected boolean syntaxAnalizer(String input) {
        if (input == null) {
            return false;
        }

        ArrayList<Character> vertices = new ArrayList<>();

        String[] edges = input.split("\n");
        for (String str: edges) {
            if (!str.matches("^[a-zA-Zа-яА-ЯёЁ] [a-zA-Zа-яА-ЯёЁ] \\d+")) {
                return false;
            }
            if (!vertices.contains(str.charAt(0))){
                vertices.add(str.charAt(0));
            }
            if (!vertices.contains(str.charAt(2))){
                vertices.add(str.charAt(2));
            }
            if (vertices.size() > maxVertices){
                return false;
            }
            if (Integer.valueOf(str.substring(4)) > maxWeight || Integer.valueOf(str.substring(4)) < 1){
                return false;
            }
        }
        return true;
    }

    public void doAll() {
        if (graph == null) {
            //сообщение об ошибке?
            return;
        }


        graph.FloydWarshall();
        System.out.println("Алгоритм выполнен\n");
        getCurrentState();
    }

    public String doStep() {
        if (graph == null) {
            //сообщение об ошибке?
            return "ERROR";
        }
        String res = graph.FloydWarshallStep();
        getCurrentState();
        return res;
    }

    public void getCurrentState() {
        int[][] matrix = graph.getMatrix();
        String vertexList = graph.getVertices();

        String res = "";
        for (int i = 0; i < vertexList.length(); i++) {
            for (int j = 0; j < vertexList.length(); j++) {
                res = res + vertexList.charAt(i) + " " + vertexList.charAt(j);
                res = res + " " + matrix[i][j] + "\n";
            }
        }
        System.out.println(res);
    }

    public String getVertex(){
       return graph.getVertices();
    }

    public int[][] getMatrix(){
        return graph.getMatrix();
    }

    public String saveRes() {
        int[][] matrix = graph.getMatrix();
        String vertexList = graph.getVertices();

        String res = "";
        for (int i = 0; i < vertexList.length(); i++) {
            for (int j = 0; j < vertexList.length(); j++) {
                res = res + vertexList.charAt(i) + " " + vertexList.charAt(j);
                res = res + " " + matrix[i][j] + "\n";
            }
        }
        System.out.println(res);
        return  res;
    }

    public void drawGraph() {
        drawer.setVisile();
    }

    public JTable drawMatrix() {
        String infinitySymbol = String.valueOf(Character.toString('\u221E'));

        int[][] g =  getMatrix();
        String in = getVertex();
        Object[][] m = new Object[in.length() + 1][in.length() + 1];
        Character[] f = new Character[in.length() + 1];
        Integer e = -1;
        for(int i  = 0; i < in.length() + 1; i++){
            if(i == 0) f[i] = ' ';
            else f[i] = in.charAt(i - 1);
        }


        for(int i  = 0; i < in.length() + 1; i++){
            for(int j  = 0; j < in.length() + 1; j++){
                if(i == 0 && j == 0) m[0][0] = ' ';
                else if(i == 0) m[0][j] = in.charAt(j - 1);
                else if(j == 0) m[i][0] = in.charAt(i - 1);
                else  m[i][j] = g[i - 1][j - 1];
                if(m[i][j] == e) m[i][j] = infinitySymbol;
            }
        }

        JTable graphMatrix = new JTable(m, f);
        for(int i  = 0; i < in.length() + 1; i++){
            graphMatrix.getColumnModel().getColumn(i).setPreferredWidth(30);
        }
        return graphMatrix;
    }


}