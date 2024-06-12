package com.Abstractlist;


import java.util.*;

public class DeliveryRoute<E> extends AbstractSequentialList<E> {
    private class Node {
        E data;
        Node next;
        Node prev;

        Node(E data) {
            this.data = data;
        }
    }

    private Node head;
    private Node tail;
    private int size = 0;

    private class ListIteratorImpl implements ListIterator<E> {
        private Node current = head;
        private Node lastReturned = null;
        private int index = 0;

        @Override
        public boolean hasNext() {
            return index < size;
        }

        @Override
        public E next() {
            if (!hasNext()) throw new NoSuchElementException();
            lastReturned = current;
            E data = current.data;
            current = current.next;
            index++;
            return data;
        }

        @Override
        public boolean hasPrevious() {
            return index > 0;
        }

        @Override
        public E previous() {
            if (!hasPrevious()) throw new NoSuchElementException();
            current = (current == null) ? tail : current.prev;
            lastReturned = current;
            index--;
            return current.data;
        }

        @Override
        public int nextIndex() {
            return index;
        }

        @Override
        public int previousIndex() {
            return index - 1;
        }

        @Override
        public void remove() {
            if (lastReturned == null) throw new IllegalStateException();
            Node nextNode = lastReturned.next;
            Node prevNode = lastReturned.prev;

            if (prevNode != null) {
                prevNode.next = nextNode;
            } else {
                head = nextNode;
            }

            if (nextNode != null) {
                nextNode.prev = prevNode;
            } else {
                tail = prevNode;
            }

            if (current == lastReturned) {
                current = nextNode;
            } else {
                index--;
            }

            lastReturned = null;
            size--;
        }

        @Override
        public void set(E e) {
            if (lastReturned == null) throw new IllegalStateException();
            lastReturned.data = e;
        }

        @Override
        public void add(E e) {
            throw new UnsupportedOperationException();
        }
    }

    @Override
    public ListIterator<E> listIterator(int index) {
        if (index < 0 || index > size) {
            throw new IndexOutOfBoundsException("Index: " + index);
        }
        ListIteratorImpl it = new ListIteratorImpl();
        for (int i = 0; i < index; i++) {
            it.next();
        }
        return it;
    }

    @Override
    public boolean add(E e) {
        Node newNode = new Node(e);
        if (head == null) {
            head = tail = newNode;
        } else {
            tail.next = newNode;
            newNode.prev = tail;
            tail = newNode;
        }
        size++;
        return true;
    }

    @Override
    public int size() {
        return size;
    }

    public static void main(String[] args) {
        DeliveryRoute<String> route = new DeliveryRoute<>();
        route.add("ABC");
        route.add("xyz");
        route.add("PQR");

        System.out.println("Delivery Route:");
        ListIterator<String> iterator = route.listIterator(0);
        while (iterator.hasNext()) {
            System.out.println(iterator.next());
        }

        iterator = route.listIterator(1);
        if (iterator.hasNext()) {
            iterator.next();
            iterator.remove();
        }

        System.out.println("After removal:");
        iterator = route.listIterator(0);
        while (iterator.hasNext()) {
            System.out.println(iterator.next());
        }
    }
}
