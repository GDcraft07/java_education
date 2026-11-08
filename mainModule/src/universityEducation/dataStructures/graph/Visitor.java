package universityEducation.dataStructures.graph;

public interface Visitor {
    void enter(int index);
    void exit(int index);
    void backward(int from, int to);
    void forward(int from, int to);
}
