package universityEducation.dataStructures.graph;

import java.util.*;

public class Graph {
    private final List<Integer>[] lists;
    private final int size;

    public Graph(int size) {
        this.size = size;
        this.lists = new List[this.size];

        for (int i = 0; i < this.size; i++) {
            lists[i] = new ArrayList<>();
        }
    }

    public int getSize() {
        return this.size;
    }

    public List<Integer> list(int index) {
        Objects.checkIndex(index, this.size);
        return this.lists[index];
    }

    public Graph addArc(int from, int to) {
        Objects.checkIndex(from, this.size);
        Objects.checkIndex(to, this.size);
        this.lists[from].add(to);

        return this;
    }

    public Graph addEdge(int from, int to) {
        this.addArc(from, to);
        return this.addArc(to, from);
    }

    public Graph removeArc(int from, int to) {
        Objects.checkIndex(from, this.size);
        Objects.checkIndex(to, this.size);
        this.lists[from].remove(Integer.valueOf(from));

        return this;
    }

    public Graph removeEdge(int from, int to) {
        this.removeArc(from, to);
        return this.removeArc(from, to);
    }

    public int[] dfs(Visitor visitor) {
        boolean[] passed = new boolean[this.size];
        int[] tree = new int[this.size];

        for (int i = 0; i < this.size; i++) {
            if (!passed[i]) {
                tree[i] = i;
                dfs(i, passed, tree, visitor);
            }
        }

        return tree;
    }

    public int[] dfs(int start, Visitor visitor) {
        Objects.checkIndex(start, this.size);
        boolean[] passed = new boolean[this.size];
        int[] tree = new int[this.size];

        for (int i = 0; i < this.size; i++) {
            tree[i] = -1;
        }

        tree[start] = start;
        this.dfs(start, passed, tree, visitor);
        return tree;
    }

    private void dfs(int current, boolean[] passed, int[] tree, Visitor visitor) {
        passed[current] = true;
        visitor.enter(current);

        for (int next: this.list(current)) {
            if (passed[next]) {
                visitor.backward(current, next);
            }

            else {
                tree[next] = current;
                visitor.forward(current, next);
                this.dfs(next, passed, tree, visitor);
            }
        }

        visitor.exit(current);
    }

    public int[] bfs(int start, Visitor visitor) {
        Objects.checkIndex(start, this.size);
        boolean[] passed = new boolean[this.size];
        int[] tree = new int[this.size];

        for (int i = 0; i < this.size; i++) {
            tree[i] = -1;
        }

        tree[start] = start;
        Queue<Integer> queue = new ArrayDeque<>();
        queue.offer(start);
        passed[start] = true;
        visitor.enter(start);

        while (!queue.isEmpty()) {
            int next = queue.poll();

            for (int vertex: this.list(next)) {
                if (passed[vertex]) {
                    visitor.backward(next, vertex);
                }

                else {
                    queue.offer(vertex);
                    tree[vertex] = next;
                    passed[vertex] = true;
                    visitor.forward(next, vertex);
                    visitor.enter(vertex);
                }
            }
            visitor.exit(next);
        }

        return tree;
    }
}
