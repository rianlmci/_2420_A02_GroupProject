package a02;

import java.util.NoSuchElementException;

/**
 * Linked List that stores the elements in sorted order based on the 
 * implementation  of Comparable<Item>.
 * The elements are internally stored in a doubly-linked list, and 
 * null elements are not allowed.
 * 
 * @author CSIS Starter Code + ..... (Eric R. and Rianna M.)
 *  
 * @param <Item> type of elements stored in the sorted list.
 */
public class SortedList<Item extends Comparable<Item>> {
	//front of the list
	private Node front;
	//back of the list
	private Node rear;
	//how much the list is currently holding
	private int size;
	
	/**
	 * Represents a node in a double linked list.
	 */
	private class Node {
		private Item item;

		private Node next; //links to the next node

		private Node previous; //links to the previous node

		Node(Item item){ this.item = item;}

	}
	
	/**
	 * Determines whether the list is empty.
	 * 
	 * @return true if there are no elements in the list and false otherwise
	 */
	public boolean isEmpty() {return size() == 0;}
	
	/**
	 * Determines how many elements are in the list.
	 * 
	 * @return number of elements in the list
	 */
	public int size(){return this.size;}

	/**
	 * Adds <code>item</code> to the list while maintaining the list's sorted order.
	 * 
	 * @param item element to add to the sorted list
	 * @throws NullPointerException if the specified element is null
	 */
	public void insert(Item item) {
		//Checks for exceptions before work is done (null item)
		if (item == null){
			throw new NullPointerException("Item to insert can not be null.");
		}
		Node nodeToInsert = new Node(item);
		Node current = front;

		if (size() == 0){
			front = nodeToInsert;
			rear = nodeToInsert;
			size++;
			return;
		}

		//Bug catch: assigns a new front node if new item is smaller than current front's item.
		else if(front.item.compareTo(item) == 1){
			nodeToInsert.next = front;
			front.previous= nodeToInsert;
			front = nodeToInsert;
			size++;
			return;
		}

		while(current.next != null && current.next.item.compareTo(item) <= 0){
			current = current.next;
		}

		nodeToInsert.next = current.next;
		current.next = nodeToInsert;
		nodeToInsert.previous = current;

		if (nodeToInsert.next != null){
			nodeToInsert.next.previous = nodeToInsert;
		} else {
			rear = nodeToInsert;
		}
		size++;
	}

	/**
	 * Deletes the element on the specified <code>index</code> 
	 * and returns the value of the deleted element.
	 * 
	 * @param index position of the element that needs to be deleted
	 * @return the deleted item
	 * @throws NoSuchElementException if the method is called on an empty list
	 * @throws IndexOutOfBoundsException if the specified index is not in the range [0, n)
	 */
	public Item delete(int index) {

		//Checks for exceptions before work is done (empty list)
		if(isEmpty()){throw new NoSuchElementException("Cannot delete from an empty list.");}

		//Checks for exceptions before work is done (bad index)
		if(index < 0 || index > (size - 1)){
			throw new IndexOutOfBoundsException("This is not a valid index.");
		}

		Node current = front;
		Item currentItem = current.item;

		//if element is at the start, do this..
		if(index == 0){
			front = current.next;
			size --;
			return currentItem;
		}

		//if element is in the middle, do this..
		for (int i = 0; i < index; i++) {
			current = current.next;
		}


		//element is in the middle, continued...
		if(index != (size - 1)){
		current.previous.next = current.next;
		current.next.previous = current.previous;
		}

		//else if we are deleting the end element, do this.. (updates rear).
		else{
			current.previous.next = null;
			rear = current.previous;
		}
		size --;
		return currentItem;
	}
	
	/**
	 * Updates the element on the specified <code>index</code> by replacing it with <code>item</code>
	 * and moves the updated node as needed to restore the sorted order of the list.
	 * <p>
	 * Examples:<br/>
	 * Given the list 10-20-30-40-50 <br/>
	 * a) updating index 2 with 33 results in 10-20-33-40-50 <br/>
	 * b) updating index 3 with 15 results in 10-15-20-30-50 <br/>
	 * c) updating index 1 with 60 results in 10-30-40-50-60 <br/>
	 * @author Rianna
	 * @param index position of the element that needs to be updated
	 * @param item new value of the updated element
	 * @throws IndexOutOfBoundsException if the specified index is not in the range [0, n)
	 * @throws NoSuchElementException if this list is empty
	 * @throws NullPointerException if this list is not empty and the item passed to the method is null
	 */
	public void update(int index, Item item) {

		//Checks for exceptions before work is done (empty list)
		if(isEmpty()){throw new NoSuchElementException("Cannot update an empty list.");}

		//Checks for exceptions before work is done (bad index)
		if(index < 0 || index > (size - 1)){
			throw new IndexOutOfBoundsException("This is not a valid index.");
		}
		//Checks for exceptions before work is done (null item)
		if(item == null){
			throw new NullPointerException("You cannot insert a null item into the list.");
		}
		delete(index);
		insert(item);
	}

	
	/**
	 * Builds a string that includes all the list elements in order. 
	 * Each element is followed by a single space.
	 * If this list is empty, an empty string is returned.
	 * @author Rianna
	 * @return a string representation of the sorted list
	 */
	@Override
	public String toString() {
		StringBuilder builtString = new StringBuilder();
		Node current = front;
		Node next;
		Node previous = null;
		while (current != null) {
			builtString.append(current.item + " ");
			current = current.next;
		}
		return builtString.toString();
	}
	
	// = = = Optional Test Client = = = //
	
	public static void main(String[] args) {
		/*
		SortedList mySortedList = new SortedList();
		System.out.println();
		System.out.println("= = = = DELETE AND INSERT TESTS = = = = ");
		System.out.println();
		System.out.println("My sorted list: " + mySortedList);
		mySortedList.insert(10);
		mySortedList.insert(20);
		mySortedList.insert(30);
		mySortedList.insert(40);
		mySortedList.insert(50);
		System.out.println("My sorted list: " + mySortedList);
		System.out.println("My sorted list head: " + mySortedList.front.item);
		System.out.println("My sorted list rear: " + mySortedList.rear.item);
		mySortedList.delete(0);
		mySortedList.delete(2);
		System.out.println("My sorted list updated head: " + mySortedList.front.item);
		System.out.println("My sorted list updated rear: " + mySortedList.rear.item);
		System.out.println("My sorted list: " + mySortedList);
		mySortedList.insert(-2);
		System.out.println("My sorted list: " + mySortedList);
		System.out.println("My sorted list updated head: " + mySortedList.front.item);
		System.out.println("My sorted list updated rear: " + mySortedList.rear.item);
		mySortedList.insert(60);
		System.out.println("My sorted list: " + mySortedList);
		System.out.println("My sorted list updated head: " + mySortedList.front.item);
		System.out.println("My sorted list updated rear: " + mySortedList.rear.item);
		System.out.println();
		System.out.println("= = = = UPDATE TESTS = = = = ");
		System.out.println();
		mySortedList.update(0,10);
		System.out.println("My sorted list: " + mySortedList);
		System.out.println("My sorted list updated head: " + mySortedList.front.item);
		System.out.println("My sorted list updated rear: " + mySortedList.rear.item);
		mySortedList.update(1,15);
		System.out.println("My sorted list: " + mySortedList);
		System.out.println("My sorted list updated head: " + mySortedList.front.item);
		System.out.println("My sorted list updated rear: " + mySortedList.rear.item);
		mySortedList.update(4,45);
		System.out.println("My sorted list: " + mySortedList);
		System.out.println("My sorted list updated head: " + mySortedList.front.item);
		System.out.println("My sorted list updated rear: " + mySortedList.rear.item);
		 */
	}

}
