package universityEducation.dataStructures.graph;

import java.util.Arrays;

public class Traverse {
    private static Graph g = new Graph(9)
            .addEdge(0, 1)
            .addEdge(0, 2)
            .addEdge(0, 3)
            .addEdge(0, 4)
            .addEdge(1, 2)
            .addEdge(3, 4)
            .addEdge(4, 5)
            .addEdge(4, 6)
            .addEdge(6, 5)
            .addEdge(6, 7)
            .addEdge(8, 7)
            .addEdge(6, 8);

    private static Visitor printer = new AbstractVisitor() {
        @Override
        public void enter(int i) {
            System.out.printf("Enter: %d%n", i);
        }

        @Override
        public void exit(int i) {
            System.out.printf("Exit: %d%n", i);
        }

        @Override
        public void forward(int from, int to) {
            System.out.printf("Forward from %d to %d%n", from, to);
        }

        @Override
        public void backward(int from, int to) {
            System.out.printf("Backward from %d to %d%n", from, to);
        }
    };

    public static void main(String[] args) {
//        System.out.println(Arrays.toString(g.dfs(6, printer)));
//        System.out.println(Arrays.toString(g.bfs(6, printer)));
    }
}
