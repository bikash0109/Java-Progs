// A node to store a value and refer to next node.
public class Node<T> {
    public T value;
    public Node<T> next;

    public Node(T value) {
        this.value = value;
    }
}