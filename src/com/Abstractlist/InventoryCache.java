package com.Abstractlist;

import java.util.*;

public class InventoryCache<K, V> {
    private final int capacity;
    private final Map<K, Node> cache;
    private Node head, tail;

    private class Node {
        K key;
        V value;
        Node prev, next;

        Node(K key, V value) {
            this.key = key;
            this.value = value;
        }
    }

    public InventoryCache(int capacity) {
        this.capacity = capacity;
        this.cache = new HashMap<>();
    }

    public V get(K key) {
        Node node = cache.get(key);
        if (node == null) return null;
        moveToHead(node);
        return node.value;
    }

    public void put(K key, V value) {
        Node node = cache.get(key);
        if (node == null) {
            node = new Node(key, value);
            if (cache.size() >= capacity) {
                cache.remove(tail.key);
                removeNode(tail);
            }
            addNode(node);
            cache.put(key, node);
        } else {
            node.value = value;
            moveToHead(node);
        }
    }

    private void addNode(Node node) {
        node.next = head;
        node.prev = null;
        if (head != null) head.prev = node;
        head = node;
        if (tail == null) tail = node;
    }

    private void removeNode(Node node) {
        if (node.prev != null) node.prev.next = node.next;
        else head = node.next;

        if (node.next != null) node.next.prev = node.prev;
        else tail = node.prev;
    }

    private void moveToHead(Node node) {
        removeNode(node);
        addNode(node);
    }

    public static void main(String[] args) {
        InventoryCache<Integer, String> cache = new InventoryCache<>(2);
        cache.put(1, "mobile");
        cache.put(2, "TV");

        System.out.println("Cache after adding 2 items: " + cache.get(1) + ", " + cache.get(2));

        cache.put(3, "Item3");
        System.out.println("Cache after adding Item3 (evicts Item2): " + cache.get(1) + ", " + cache.get(2) + ", " + cache.get(3));

        cache.get(1);
        cache.put(4, "Item4");
        System.out.println("Cache after accessing Item1 and adding Item4 (evicts Item3): " + cache.get(1) + ", " + cache.get(2) + ", " + cache.get(4));
    }
}
