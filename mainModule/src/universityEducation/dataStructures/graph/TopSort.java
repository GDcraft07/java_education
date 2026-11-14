package universityEducation.dataStructures.graph;

import java.util.Arrays;

public class TopSort {
    private static class TopSortVisitor extends AbstractVisitor {
        private final int[] numbers;
        private final Graph g;
        private int current;

        private TopSortVisitor(Graph g) {
            this.g = g;
            this.numbers = new int[g.getSize()];
            this.current = g.getSize();
        }

        public int[] numbers() {
            return this.numbers;
        }

        @Override
        public void backward(int from, int to) {
            if (this.numbers[to] == 0) {
                throw new IllegalArgumentException(
                        "Граф должен быть ориентированным"
                );
            }
        }

        @Override
        public void exit(int index) {
            this.numbers[index] = this.current--;
        }
    }

    public static void main(String[] args) {
        Graph g = new Graph(9)
                .addArc(0, 1)
                .addArc(0, 3)
                .addArc(6, 3)
                .addArc(6, 7)
                .addArc(3, 1)
                .addArc(3, 7)
                .addArc(1, 2)
                .addArc(1, 4)
                .addArc(7, 4)
                .addArc(7, 8)
                .addArc(4, 2)
                .addArc(4, 5)
                .addArc(2, 5)
                .addArc(8, 5);

        TopSortVisitor topSortVisitor = new TopSortVisitor(g);

        g.dfs(topSortVisitor);
        System.out.println(Arrays.toString(topSortVisitor.numbers()));
    }

}
