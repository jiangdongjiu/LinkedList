public class Node<T> {
    private T element;
    private Node<T> previousNode;
    private Node<T> nextNode;

    public Node(){
        this.element = null;
        this.nextNode = null;
        this.previousNode = null;
    }

    public Node(T element){
        this.element = element;
        this.nextNode = null;
        this.previousNode = null;
    }
    public Node(T element, Node<T> previousNode, Node<T> nextNode){
        this.element = element;
        this.nextNode = nextNode;
        this.previousNode = previousNode;
    }


    public T getElement(){
        return element;
    }

    public Node<T> getPrevious(){
        return  previousNode;
    }

    public Node<T> getNext(){
        return nextNode;
    }

    public void setElement(T element){
        this.element = element;
    }

    public void setPrevious(Node<T> previousNode){
        this.previousNode = previousNode;
    }

    public void setNext(Node<T> nextNode){ this.nextNode = nextNode; }
}
