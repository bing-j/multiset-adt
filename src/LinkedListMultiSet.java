public class LinkedListMultiSet<T> implements MultiSet<T>{

    private Node<T> front;
    private int size;

//  No constructor is needed
//  (Java's default constructor behaves as desired)

    @Override
    public boolean add(T item) {
        Node<T> newNode = new Node<>(item);
        newNode.setNext(front);
        front = newNode;
        size++;
        return true;
    }

    @Override
    public void remove(T item) {
        Node<T> curr = front;
        Node<T> prev = null;
        while (curr != null){
            if (curr.getItem().equals(item)){
                size--;
                if (prev == null){
                    front = curr.getNext();
                } else {
                    prev.setNext(curr.getNext());
                }
                return;
            }
            prev = curr;
            curr = curr.getNext();
        }
    }

    @Override
    public boolean contains(T item) {
        Node<T> curr = front;
        while (curr != null){
            if (curr.getItem().equals(item)){
                return true;
            }
            curr = curr.getNext();
        }
        return false;
    }

    @Override
    public boolean isEmpty() {
        return front == null;
    }

    @Override
    public int count(T item) {
        int count = 0;
        Node<T> curr = front;
        while (curr != null){
            if (curr.getItem().equals(item)){
                count++;
            }
            curr = curr.getNext();
        }
        return count;
    }

    @Override
    public int size() {
        return size;
    }
}
