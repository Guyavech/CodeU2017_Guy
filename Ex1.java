//Please review my code!
//Ex1

import java.util.HashMap;

public class Ex1 {

	public static void main(String[] args) {
		System.out.println("Hello CodeU 2017! :)");
	}
	
	/**
	 * This method checks if two strings are a permutation of each other
	 * The Method uses Hash Maps to check (O(n) overall) and doesn't use sorting (O(n log n))
	 * It uses a helper method named "charactersHistogram"
	 * @param str1 - first string to check
	 * @param str2 - second string to check
	 * @return true if holds, otherwise false.
	 */
	public static boolean isPermutation(String str1, String str2) {
		
		if (str1 == null || str2 == null || (str1.length() != str2.length())) {
			return false;
		}
	
		return charactersHistogram(str1).equals(charactersHistogram(str2));
	}
	
	/**
	 * This function creates a histogram of characters in a string
	 * @param s - the string to evaluate
	 * @return the string's histogram
	 */
	private static HashMap<Character, Integer> charactersHistogram(String s) {
		HashMap<Character, Integer> histogram = new HashMap<>();
		
		//iterate over the string and create the histogram
		for (int i = 0; i < s.length(); i++) {
			char c = s.toLowerCase().charAt(i);
			
			if (histogram.containsKey(c)) {
				histogram.put(c, histogram.get(c) + 1);
			} else {
				histogram.put(c, 1);
			}
		}
		
		return histogram;
	}
	
	/**
	 * This method retrieve the k-th to last element of a SinglyLinkedList object.
	 * It returns the k-th to last element in O(N), in one iteration,
	 * without having to calculate the size of the list (because input is valid).
	 * @param list - a SinglyLinkedList object (implemented below)
	 * @param k - k-th to last element
	 * @return requested Node element ASSUMING k IS IN BOUNDS AS INSTRUCTED (0 <= k < list's size)
	 */
	public static Node kthToLast(SinglyLinkedList list, int k) {
		
		Node leftPointer = list.getHead();
		Node rightPointer = list.getHead();
	
		//create a gap of k elements between two pointers
		for (int i = 0; i < k; i++) {
			rightPointer = rightPointer.getNext();
		}
		
		//advance both pointers until rightPointer goes out of bounds
		while(rightPointer.hasNext()) {
			rightPointer = rightPointer.getNext();
			leftPointer = leftPointer.getNext();
		}
		
		return leftPointer;
	}
}

/**
 * @author Guy Nir
 * A Node class to work with the SimplyLinkedList class below
 */
class Node {
	private int data;
	private Node next;
	
	public Node(int data) {
		this.data = data;
	}
	
	public int getData() {
		return this.data;
	}
	
	public void setData(int data) {
		this.data = data;
	}
	
	public Node getNext() {
		return next;
	}
	
	public void setNext(Node node) {
		this.next = node;
	}
	
	public boolean hasNext() {
		return (this.next != null);
	}
	
	@Override
	public String toString() {
		return String.valueOf(this.data);
	}
}

/**
 * @author Guy Nir
 * A SimplyLinkedList class that represents a simply linked list
 * Insertion is done to the head of the list.
 */
class SinglyLinkedList {
	private Node head;
	
	public void insertAtHead(int data) {
		Node newNode = new Node(data);
		newNode.setNext(this.head);
		this.head = newNode;
	}
	
	public Node getHead() {
		return this.head;
	}
	
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder("{");
		Node current = this.head;
		
		while(current != null) {
			sb.append(current.getData());
			sb.append(" ");
			current = current.getNext();
		}
		
		sb.append("}");
		return sb.toString();
	}
}
