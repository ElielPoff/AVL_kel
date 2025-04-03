class AVLNode<T extends Comparable<T>>{
    private AVLNode<T> left;
    private AVLNode<T> right;
    private int fatBal;
    private T info;

    AVLNode(T info){
        this.info = info;
    }

    void setLeft(AVLNode<T> node) { this.left = node; }
    void setRight(AVLNode<T> node) { this.right = node; }
    void setFatBal(int fb) { this.fatBal = fb; }
    void setInfo(T info) { this.info = info;}

    AVLNode<T> getLeft() { return this.left; }
    AVLNode<T> getRight() { return this.right; }
    int getFatBal() { return this.fatBal; }
    T getInfo() { return this.info; }

}