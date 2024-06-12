package com.Abstractlist;


import java.util.AbstractList;
import java.util.Arrays;

	public class OrderList<E> extends AbstractList<E> 
	{
	    private static final int DEFAULT_CAPACITY = 10;
	    private Object[] elements;
	    private int size = 0;

	    public OrderList()
	    {
	        elements = new Object[DEFAULT_CAPACITY];
	    }

	    @Override
	    public E get(int index)
	    {
	        if (index >= size || index < 0) 
	        {
	            throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);
	        }
	        return (E) elements[index];
	    }

	    @Override
	    public boolean add(E e) 
	    {
	        if (size == elements.length) 
	        {
	            elements = Arrays.copyOf(elements, size * 2);
	        }
	        elements[size++] = e;
	        return true;
	    }

	    @Override
	    public E remove(int index)
	    {
	        if (index >= size || index < 0)
	        {
	            throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);
	        }
	        E oldValue = (E) elements[index];
	        int numMoved = size - index - 1;
	        if (numMoved > 0) 
	        {
	            System.arraycopy(elements, index + 1, elements, index, numMoved);
	        }
	        elements[--size] = null; 
	        return oldValue;
	    }

	    @Override
	    public int size() 
	    {
	        return size;
	    }
	    public static void main(String[] args)
	    {
	        OrderList<String> orders = new OrderList<>();
	        orders.add("mobile");
	        orders.add("Tv");
	        orders.add("Laptop");

	        System.out.println("Orders: " + orders);

	        orders.remove(1);
	        System.out.println("After removal: " + orders);

	        System.out.println("Order at index 0: " + orders.get(0));
	    }
	}


