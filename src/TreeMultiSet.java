public class TreeMultiSet<T> implements MultiSet<T>{

    private Tree<T> tree = new Tree<T>(null);

    @Override
    public boolean add(T item) {
        tree.insert(item);
        return true;
    }

    @Override
    public void remove(T item) {
        tree.deleteItem(item);
    }

    @Override
    public boolean contains(T item) {
        return tree.contains(item);
    }

    @Override
    public boolean isEmpty() {
        return tree.isEmpty();
    }

    @Override
    public int count(T item) {
        return tree.count(item);
    }

    @Override
    public int size() {
        return tree.size();
    }
}
