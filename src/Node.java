public class Node<T> {
    private T item;
    private Node<T> next;

    public Node(T item){
        this.item = item;
    }

    public void setNext(Node<T> next){
        this.next = next;
    }

    public Node<T> getNext(){
        return next;
    }

    public T getItem(){
        return item;
    }
}
