
import java.util.NoSuchElementException;

public class MyLinkedList<T> implements ListNew<T> {
    private int size = 0;
    private Node<T> first;
    private Node<T> last;


   MyLinkedList() {
        last = first = null;
    }

    public boolean add(T value) {
        Node<T> newElement = new Node<T>(value);
        if (first == null) {
            first = newElement;
            last = newElement;
            size = 1;
        } else {
            last.setNext(newElement);
            last = newElement;
            size++;
        }
        return true;
    }

    public T remove() {
        if (first == null) {
            throw new NoSuchElementException();
        } else {
            Node<T> current = first;
            if (current.getNext() != null) {
                if (current.getNext().getNext() != null) {
                    for (; current.getNext().getNext() != null; ) {
                        current = current.getNext();
                    }
                    last = current;
                    current = current.getNext();
                    last.setNext(null);

                } else {
                    first.setNext(null);
                    last = first;
                }
            } else {
                first = null;
                last = null;
            }
            size--;
            return current.getValue();
        }
    }

    public T remove(int index) {
        Node<T> current = first;
        if (index >= size || index < 0) {
            throw new IndexOutOfBoundsException();
        } else if (index == 0) {
            first = current.getNext();
            size--;
            return current.getValue();
        } else if (index == size - 1) {
            return remove();
        } else {
            for (int i = 0; i < index - 1; i++) {
                current = current.getNext();
            }
            Node<T> returnElement = current.getNext();
            current.setNext(returnElement.getNext());
            size--;
            return returnElement.getValue();
        }
    }

    public T get(int index) {
        Node<T> current = first;
        if (index >= size || index < 0) {
            throw new IndexOutOfBoundsException();
        } else if (index == size - 1) {
            return getLast();
        } else {
            for (int i = 0; i < index; i++) {
                current = current.getNext();
            }
            return current.getValue();
        }
    }

    public T getLast() {
        if (last != null) {
            return last.getValue();
        } else {
            throw new NoSuchElementException();
        }
    }

    public T getFirst() {
        if (first != null) {
            return first.getValue();
        } else {
            throw new NoSuchElementException();
        }
    }

    public int getSize() {
        return size;
    }

    @Override
    public String toString() {
        String out = new String("[");
        Node<T> current = first;
        for (int i = 0; i < size - 1; i++) {
            out += current.getValue().toString() + ",";
            current = current.getNext();
        }
        out += current.getValue().toString();
        return out + "]";
    }


    @Override
    public void clear() {
        first = last = null;
        size = 0;
    }

    @Override
    public T removeFirst() {
        return remove(0);
    }

    class Node<T> {
        private T value;
        private Node next;

        Node(T value) {
            this.value = value;
            this.next = null;
        }

        public Node<T> getNext() {
            return next;
        }

        public void setNext(Node next) {
            this.next = next;
        }

        public T getValue() {
            return value;
        }

    }

}

