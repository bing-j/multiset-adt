import java.util.ArrayList;

public class Tree<T> {
    private T t;
    private T root;
    private ArrayList<Tree<T>> subtrees;

//  === Representation Invariants ===
//      - If self._root is None then self._subtrees is an empty list.
//  This setting of attributes represents an empty tree.
//
//  Note: self._subtrees may be empty when self._root is not None.
//  This setting of attributes represents a tree consisting of just one
//  node.

    public Tree(T root){
        this.root = root;
        this.subtrees = new ArrayList<>();
    }

    public Tree(T root, ArrayList<Tree<T>> subtrees){
        this.root = root;
        this.subtrees = subtrees == null ? new ArrayList<>() : subtrees;
    }

    public boolean isEmpty(){
        return root == null;
    }

    public int size(){
        if (this.isEmpty()){
            return 0;
        } else {
            int size = 1;
            for (Tree<T> subtree : subtrees){
                size += subtree.size();
            }
            return size;
        }
    }

    public int count(T item){
        if (this.isEmpty()){
            return 0;
        } else {
            int num = 0;
            if (root.equals(item)){
                num ++;
            }
            for (Tree<T> subtree: subtrees){
                num += subtree.count(item);
            }
            return num;
        }
    }

    public String toString(){
        return this.stringIndented();
    }

    private String stringIndented(){
        return this.stringIndented(0);
    }

    private String stringIndented(int depth){
        if (this.isEmpty()){
            return "";
        } else {
            String s = "   ".repeat(depth) + root + "\n";
            for (Tree<T> subtree : subtrees){
                s  += subtree.stringIndented(++depth);
            }
            return s;
        }
    }

    public double average(){
    /* Precondition:
        - This is a tree of numbers (i.e. int, long, float, or double)
     */
        if (root == null){
            return 0.0;
        } else {
            double[] results;
            try {
                results = this.averageHelper();
            } catch (Exception e) {
                System.out.println("Failed to get average; not a tree of numbers. Exiting method.");
                throw e;
            }
            return results[0] / results[1];
        }
    }

    private double[] averageHelper(){
        if (this.isEmpty()){
            return new double[]{0.0, 0.0};
        } else {
            double total = (double) root;
            double size = 1.0;
            for (Tree<T> subtree: subtrees){
                double[] totalAndSize = subtree.averageHelper();
                total += totalAndSize[0];
                size += totalAndSize[1];
            }
            return new double[]{total, size};
        }
    }

    public boolean equals(Tree<T> other){
        if (this.isEmpty() && other.isEmpty()){
            return true;
        } else if (this.isEmpty() || other.isEmpty()){
            return false;
        } else {
            if (!root.equals(other.root)){
                return false;
            }
            if (!(subtrees.size() == other.subtrees.size())){
                return false;
            }
            return subtrees.equals(other.subtrees);
        }
    }

    public boolean contains(T item) {
        if (this.isEmpty()){
            return false;
        }
        if (root.equals(item)){
            return true;
        } else {
            for (Tree<T> subtree : subtrees){
                if (subtree.contains(item)){
                    return true;
                }
            }
            return false;
        }
    }

    public ArrayList<T> leaves(){
        ArrayList<T> leaves = new ArrayList<>();
        if (this.isEmpty()){
            return leaves;
        } else if (subtrees.isEmpty()){
            leaves.add(root);
            return leaves;
        } else {
            for (Tree<T> subtree : subtrees){
                leaves.addAll(subtree.leaves());
            }
            return leaves;
        }
    }

    public boolean deleteItem(T item){
        if (this.isEmpty()){
            return false;
        } else if (root.equals(item)){
            this.deleteRoot();
            return true;
        } else {
            for (Tree<T> subtree : subtrees){
                boolean deleted = subtree.deleteItem(item);
                if (deleted && subtree.isEmpty()){
                    subtrees.remove(subtree);
                    return true;
                } else if (deleted) {
                    return true;
                }
            }
            return false;
        }
    }

    private void deleteRoot(){
        if (subtrees.isEmpty()){
            this.root = null;
        } else {
            // Promote the first subtree in this tree.
            Tree<T> chosenSubtree = subtrees.remove(0);
            root = chosenSubtree.root;
            subtrees.addAll(chosenSubtree.subtrees);
        }
    }

    public void insert (T item){
        /*
        Insert <item> into this tree using the following algorithm.

            1. If the tree is empty, <item> is the new root of the tree.
            2. If the tree has a root but no subtrees, create a
               new tree containing the item, and make this new tree a subtree
               of the original tree.
            3. Otherwise, pick a random number between 1 and 3 inclusive.
                - If the random number is 3, create a new tree containing
                  the item, and make this new tree a subtree of the original.
                - If the random number is a 1 or 2, pick one of the existing
                  subtrees at random, and *recursively insert* the new item
                  into that subtree.
         */
        if (this.isEmpty()){
            root = item;
        } else if (this.subtrees.isEmpty()){
            subtrees.add(new Tree<>(item));
        } else {
            int randInt = (int) (Math.random() * 3) + 1;
            if (randInt == 3){
                subtrees.add(new Tree<>(item));
            }
            else {
                int index = (int) (Math.random() * subtrees.size());
                this.subtrees.get(index).insert(item);
            }
        }
    }

    public boolean insertChild(T item, T parent){
        if (this.isEmpty()){
            return false;
        } else if (this.root.equals(parent)){
            subtrees.add(new Tree<>(item));
            return true;
        } else {
            for (Tree<T> subtree : subtrees){
                if (subtree.insertChild(item, parent)){
                    return true;
                }
            }
            return false;
        }
    }

}
