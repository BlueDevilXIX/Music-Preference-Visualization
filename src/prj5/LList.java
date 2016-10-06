package prj5;

import java.util.Arrays;
import java.util.Iterator;
import java.util.NoSuchElementException;

import list.ListInterface;

/**
 * Linked list class
 * 
 * @author Randy Liang, Connor Smedley, and Garrett Novak
 * @version 2015.11.20
 *
 * @param <T>
 */
public class LList<T> implements ListInterface<T> {
    private Node firstNode;
    private int size;

    /**
     * Constructor
     */
    public LList() {
        size = 0;
        firstNode = null;
    }

    @Override
    public void add(T entry) {
        if (entry == null) {
            throw new IllegalArgumentException();
        }
        Node entryNode = new Node(entry);
        if (!isEmpty()) {
            entryNode.next = firstNode;
        }
        firstNode = entryNode;
        size++;
    }

    @Override
    public void add(int index, T entry) {
        if (index >= 0 && index <= size + 1) {
            Node newEntry = new Node(entry);
            if (index == 0) {
                add(entry);
            } 
            else {
                Node nodeBefore = getNodeAt(index - 1);
                Node nodeAfter = nodeBefore.getNext();
                nodeBefore.next = newEntry;
                newEntry.next = nodeAfter;
                size++;
            }
        } 
        else {
            throw new IndexOutOfBoundsException();
        }
    }

    @Override
    public void clear() {
        firstNode = null;
        size = 0;
    }

    @Override
    public boolean contains(T entry) {
        if (entry == null || isEmpty()) {
            return false;
        }
        Node tempNode = firstNode;
        while (tempNode != null) {
            if (tempNode.data.equals(entry)) {
                return true;
            }
            if (tempNode.hasNext()) {
                tempNode = tempNode.getNext();
            } 
            else {
                tempNode = null;
            }
        }
        return false;
    }

    @Override
    public T getEntry(int index) {
        if (index >= size || index < 0) {
            return null;
        }
        Node tempNode = this.getNodeAt(index);
        return tempNode.data;
    }

    @Override
    public int getLength() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public T remove(int index) {
        if (getEntry(index) == null) {
            throw new IndexOutOfBoundsException();
        }
        if (index != 0) {
            Node previousNode = this.getNodeAt(index - 1);
            Node tempNode = previousNode.getNext();
            T storage = tempNode.getData();
            previousNode.next = tempNode.getNext();
            tempNode = null;
            size--;
            return storage;
        } 
        else {
            T obj = firstNode.getData();
            firstNode = null;
            size--;
            return obj;
        }
    }

    /**
     * Removes an entry
     * 
     * @param entry
     *            the entry being removed
     * @return null
     */
    public T remove(T entry) {
        if (!this.contains(entry)) {
            return null;
        }
        if (getLength() == 1) {
            T obj = firstNode.getData();
            firstNode = null;
            size--;
            return obj;
        }
        if (entry.equals(firstNode.getData())) {
            T obj = firstNode.getData();
            firstNode = firstNode.getNext();
            size--;
            return obj;
        }
        Node tempNode = firstNode.getNext();
        Node previousNode = firstNode;
        T removed = null;
        while (tempNode != null) {
            if (tempNode.data.equals(entry)) {
                previousNode.next = tempNode.getNext();
                removed = entry;
                tempNode = null;
            } 
            else if (tempNode.hasNext()) {
                previousNode = tempNode;
                tempNode = tempNode.getNext();
            } 
            //else {
                //tempNode = null;
            //}
        }
        size--;
        return removed;
    }

    @Override
    public T replace(int index, T entry) {
        if (index >= size || index < 0)
        {
            throw new IndexOutOfBoundsException();
        }
        Node tempNode = getNodeAt(index);
        T formerEntry = tempNode.getData();
        tempNode.data = entry;
        return formerEntry;
    }

    @Override
    public Object[] toArray() {
        Object[] endResault = new Object[size];
        Node tempNode = firstNode;
        for (int i = 0; i < size; i++) {
            endResault[i] = tempNode.getData();
            tempNode = tempNode.getNext();
        }
        return endResault;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }

        if (obj == this) {
            return true;
        }

        if (obj.getClass() != this.getClass()) {
            return false;
        }

        @SuppressWarnings("unchecked")
        LList<T> temp = (LList<T>) obj;
        return Arrays.equals(this.toArray(), temp.toArray());
    }

    /**
     * To string method
     * 
     * @return the appended string
     */
    public String toString() {
        StringBuilder retStr = new StringBuilder("[");
        Node tempNode = firstNode;
        while (tempNode != null) {
            retStr.append(tempNode.getData().toString());
            tempNode = tempNode.getNext();
        }
        retStr.append("]");
        return retStr.toString();
    }

    /**
     * To string with params
     * 
     * @param type
     *            the type of string
     * @return the appended string
     */
    public String toStringMod(String type) {
        StringBuilder retStr = new StringBuilder();
        Node tempNode = firstNode;
        while (tempNode != null) {
            retStr.append(((Song) tempNode.getData()).toString(type));
            tempNode = tempNode.getNext();
            if (tempNode != null) {
                retStr.append("\n");
            }
        }
        return retStr.toString();
    }

    /**
     * Gets node
     * 
     * @param index
     *            the position
     * @return tempNode
     */
    private Node getNodeAt(int index) {
        Node tempNode = firstNode;
        for (int i = 0; i < index; i++) {
            tempNode = tempNode.getNext();
        }
        return tempNode;
    }

    /**
     * Depends on the private iterator class
     * 
     * @return new LListIterator()
     */
    public Iterator<T> iterator() {
        return new LListIterator();
    }

    /**
     * Private iterator class
     * 
     * @author Randy Liang, Connor Smedley, and Garrett Novak
     * @version 2015.11.20
     *
     */
    private class LListIterator implements Iterator<T> {
        private Node nextNode;

        /**
         * Default constructor
         */
        public LListIterator() {
            nextNode = firstNode;
        }

        @Override
        public boolean hasNext() {
            return nextNode != null;
        }

        @Override
        public T next() {
            if (hasNext()) {
                Node returnNode = nextNode;
                nextNode = nextNode.getNext();
                return returnNode.getData();
            } 
            else {
                throw new NoSuchElementException();
            }
        }
    }

    /**
     * Private Node class
     * 
     * @author Randy Liang, Connor Smedley, and Garrett Novak
     * @version 2015.11.20
     */
    private class Node {
        private Node next;
        private T data;

        /**
         * Constructor
         * 
         * @param entry
         *            the entry
         */
        private Node(T entry) {
            next = null;
            data = entry;
        }

        /**
         * Gets the next node
         * 
         * @return next
         */
        private Node getNext() {
            return next;
        }

        /**
         * Checks if there is a successive node
         * 
         * @return next not equal to null
         */
        private boolean hasNext() {
            return next != null;
        }

        /**
         * Gets the data
         * 
         * @return data
         */
        private T getData() {
            return data;
        }
    }
}