import java.util.NoSuchElementException;

public class LinkedList<T extends Comparable<T>> {
    private Node<T> head;
    private Node<T> tail;
    private int count;
    // region milestone1
    public LinkedList(){
        this.head = null;
        this.tail = null;
        this.count = 0;
    }
    public void addFirst(T element){
        Node<T> firstNode = new Node<>(element);
        if (isEmpty()){
            this.head = firstNode;
            this.tail = firstNode;
        }
        else {
            firstNode.setNext(this.head);
            this.head.setPrevious(firstNode);
            this.head = firstNode;
        }
        count ++;
    }

    public void clear() {
        this.tail = null;
        this.head = null;
        this.count = 0;
    }

    public boolean isEmpty() {
        return  this.count == 0;
    }

    public Node<T> getHead(){
        return this.head;
    }

    public Node<T> getTail(){
        return this.tail;
    }

    public T getFirst(){
        validation();
        return this.head.getElement();
    }

    public T getLast(){
        this.validation();
        return this.tail.getElement();
    }

    public int getSize(){
        return count;
    }

    public T setFirst(T element){
        this.validation();

        T replaced = this.head.getElement();
        this.head.setElement(element);
        return replaced;
    }

    public T setLast(T element){
        this.validation();

        T replaced = this.tail.getElement();
        this.tail.setElement(element);
        return replaced;
    }

    public void addLast(T element){
        Node<T> lastNode = new Node<>(element);
        if (this.head == null && this.tail ==null){
            this.head = lastNode;
        }
        else{
            lastNode.setPrevious(this.tail);
            this.tail.setNext(lastNode);
        }
        this.tail = lastNode;

        count ++;
    }
    //endregion

    // region milestone2

    // remove the head node
    public T removeFirst() {
        this.validation();

        T removed_element = this.head.getElement();

        if (this.count == 1) clear();
        else{
            Node<T> new_head = this.head.getNext();
            this.head = new_head;
            new_head.setPrevious(null);
            this.count--;
        }

        return removed_element;
    }
    // remove the tail node
    public T removeLast() {
        this.validation();

        T removed_element = this.tail.getElement();

        if (this.count == 1) clear();
        else{
            Node<T> new_tail = this.tail.getPrevious();
            this.tail = new_tail;
            new_tail.setNext(null);
            this.count--;
        }

        return removed_element;
    }

    // Return the element at the position.
    public T get(int i) { return getNodeByPosition(i).getElement(); }

    // Add new element after the node at the provided position.
    public void addAfter(T element, int i) {
        Node<T> currentNode = getNodeByPosition(i);

        if (i == this.getSize()){
            addLast(element);
        }
        else {
            addBetween(element, currentNode, currentNode.getNext());
        }
    }

    // Add new element before the node at the provided position.
    public void addBefore(T element, int i) {
        Node<T> currentNode = getNodeByPosition(i);

        if (i == 1){
            addFirst(element);
        }
        else {
            addBetween(element, currentNode.getPrevious(), currentNode);
        }

    }

    // Remove the node at the numeric position specified
    public T remove(int i) {
        Node<T> NodeNeedToRemove = getNodeByPosition(i);
        T removed_element = NodeNeedToRemove.getElement();

        removeBetween(NodeNeedToRemove.getPrevious(), NodeNeedToRemove.getNext());

        return  removed_element;
    }

    // Change specified node to element in parameter
    public T set(T element, int i) {
        Node<T> currentNode = getNodeByPosition(i);
        T removed_element = currentNode.getElement();
        currentNode.setElement(element);

        return  removed_element;
    }
    // endregion

    // region milestone3

    //Return the element in the node containing the element specified (does not use position!)
    public T get(T element) {
        return getNodeByElement(element).getElement();
    }

    // Add new element after the node containing the ‘oldelement’ specified (does not use position!)
    public void addAfter(T element, T oldElement) {
        Node<T> oldNode = getNodeByElement(oldElement);
        addBetween(element, oldNode, oldNode.getNext());
    }

    // Add new element after the node containing the ‘oldelement’ specified (does not use position!)
    public void addBefore(T element, T oldElement) {
        Node<T> oldNode = getNodeByElement(oldElement);
        addBetween(element, oldNode.getPrevious(), oldNode);
    }

    // Remove the node containing the element specified. (does not use position!)
    public T remove(T element) {
        Node<T> NodeNeedToRemove = getNodeByElement(element);

        removeBetween(NodeNeedToRemove.getPrevious(), NodeNeedToRemove.getNext());

        return  element;
    }

    // Change the element on the node containing the old element
    // with the element passed and return the old element. (does not use position!)
    public T set(T element, T oldElement) {
        getNodeByElement(oldElement).setElement(element);
        return oldElement;
    }

    // Add the element into the linked list in natural ascending order.
    public void insert(T element) {
        if (getSize() == 0) addFirst(element);
        else{
            Node<T> currentNode = this.getHead();
            for (int i = 1; i <= this.count; i++) {
                if (element.compareTo(currentNode.getElement()) <= 0) {
                    addBetween(element, currentNode.getPrevious(), currentNode);
                    break;
                }
                else if (i == this.count) {
                    addLast(element);
                    break;
                }
                currentNode = currentNode.getNext();
            }
        }
    }

    // Sort the elements into ascending order. You can use any algorithms you want to
    // achieve this (Example of an easy sorting algorithm is: bubble sort)
    public void sortAscending() {
        bubbleSort(getSize());
//        Node<T> node = head;
//        clear();
//        while(node != null) {
//            insert(node.getElement());
//            node = node.getNext();
//        }
    }

    // endregion


    // region HELPER METHODS

    // A function to implement bubble sort
    private void bubbleSort(int n)
    {
        // Base case
        if (n == 0 || n == 1)
            return;
        // One pass of bubble sort. After
        // this pass, the largest element
        // is moved (or bubbled) to end.
        Node<T> currentNode = getHead();
        for (int i = 1; i < n; i++){
            T currentElement = currentNode.getElement();
            T nextElement = currentNode.getNext().getElement();

            if (currentElement.compareTo(nextElement) > 0)
            {
                set(currentElement, nextElement);
                set(nextElement, currentElement);
            }
            currentNode = currentNode.getNext();
        }
        // Largest element is fixed,
        // recur for remaining linked list
        bubbleSort(n-1);
    }

    // Return the node at the position.
    private Node<T> getNodeByPosition(int i){
        if (!(i > 0 && i <= getSize())) throw new NoSuchElementException();

        Node<T> current_node = this.head;

        while (i > 1){
            current_node = current_node.getNext();
            i--;
        }

        return current_node;
    }

    // Get position of the element.
    private Node<T> getNodeByElement(T element) {
        this.validation();
        if (element == null) throw new NullPointerException();

        Node<T> current_node = this.head;

        while (current_node.getElement() != element){
            if (current_node.getNext() == null){
                throw new NoSuchElementException();
            }
            current_node = current_node.getNext();
        }

        return current_node;
    }

    // Generate NoSuchElementException when linked list is null
    private void validation() {
        if (this.isEmpty()){
            throw new NoSuchElementException();
        }
    }

    // Add a node between previous and next node.
    private void addBetween(T element, Node<T> previousNode, Node<T> nextNode) {
        if (nextNode == this.head){
            addFirst(element);
        }
        else if (previousNode == this.tail){
            addLast(element);
        }
        else {
            Node<T> new_node = new Node<>(element);

            new_node.setPrevious(previousNode);
            new_node.setNext(nextNode);
            nextNode.setPrevious(new_node);
            previousNode.setNext(new_node);//

            this.count++;
        }

    }

    // Remove a node between previous and next node.
    private void removeBetween(Node<T> previousNode, Node<T> nextNode) {

        if (previousNode == null){
            removeFirst();
        }
        else if (nextNode == null){
            removeLast();
        }
        else {
            previousNode.setNext(nextNode);
            nextNode.setPrevious(previousNode);
            this.count--;
        }
    }

    // endregion
}
