package josephring;

/**
 * 双向循环链 解决 约瑟夫环问题
 *
 * @author keno
 */
public class DoubleCycleLink<E> {

    public static void main(String[] args) {
        DoubleCycleLink<Integer> doubleCycleLink = new DoubleCycleLink<>(1);
        doubleCycleLink.addLast(2);
        doubleCycleLink.addLast(3);
        doubleCycleLink.addLast(4);
        doubleCycleLink.addLast(5);
        Node<Integer> node = doubleCycleLink.getNode(0);
        Node<Integer> survivor = doubleCycleLink.deleteLoop(node, 2);
        System.out.println(survivor.getItem());
    }

    public Node<E> deleteLoop(Node<E> cur, int skip) {
        int i = 0;
        while (i < skip) {
            cur = cur.next;
            i++;
        }
        if (cur == null) {
            return null;
        }
        Node<E> nextStart = cur.next;
        cur.prev.next = cur.next;
        cur.next.prev = cur.prev;
        cur = null;
        size--;
        if (nextStart.next.getItem() == nextStart.prev.getItem()) {
            return nextStart;
        }
        return deleteLoop(nextStart, skip);
    }

    private int size;
    private Node<E> head;

    public DoubleCycleLink(E e) {
        head = new Node<>(null, e, null);
        head.prev = head.next = head;
        size = 1;
    }

    public int getSize() {
        return this.size;
    }

    public boolean isEmpty() {
        return this.size == 0;
    }

    public void validIndex(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException();
        }
    }

    public Node<E> getNode(int index) {
        validIndex(index);
        Node<E> cur = head;
        for (int i = 0; i < index; i++) {
            cur = cur.next;
        }
        return cur;
    }

    public E get(int index) {
        return this.getNode(index).item;
    }

    public E getFirst() {
        return this.getNode(0).item;
    }

    public E getLast() {
        return this.getNode(size - 1).item;
    }

    public void insert(int index, E value) {
        if (size == 0) {
            Node<E> cur = new Node<>(head, value, null);
            head.next.prev = cur;
            head.next = cur;
        } else {
            Node<E> node = getNode(index);
            Node<E> cur = new Node<>(node.next, value, node.prev);
            head.next.prev = cur;
            head.next = cur;
        }
        size++;
    }

    public void addFirst(E value) {
        this.insert(0, value);
    }

    public void addLast(E value) {
        Node<E> cur = new Node<>(head.prev, value, head);
        head.prev.next = cur;
        head.prev = cur;
        size++;
    }

    public Node<E> deleteGetNext(int index) {
        validIndex(index);
        Node<E> cur = getNode(index);
        Node<E> temp = cur.next;
        cur.prev.next = cur.next;
        cur.next.prev = cur.prev;
        size--;
        cur = null;
        return temp;
    }

    public void delete(int index) {
        validIndex(index);
        Node<E> cur = getNode(index);
        cur.prev.next = cur.next;
        cur.next.prev = cur.prev;
        size--;
        cur = null;
    }

    public void deleteFirst() {
        this.delete(0);
    }

    public void deleteLast() {
        this.delete(size - 1);
    }


    private static class Node<E> {
        E item;
        Node<E> next;
        Node<E> prev;

        public E getItem() {
            return item;
        }

        Node(Node<E> prev, E element, Node<E> next) {
            this.item = element;
            this.next = next;
            this.prev = prev;
        }
    }
}
