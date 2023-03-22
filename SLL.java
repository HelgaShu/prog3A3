
public class SLL <T extends Comparable<T>> implements Iterable<T> {

	/*
	 * Inner class Node for the linked list.
	 */
	class Node<T extends Comparable<T>> {

		private T data;
		private Node<T> next;

		/*
		 * Constructor for objects of class Node
		 */
		public Node(T d) {
			data = d;
			next = null;
		}

		public T getData() {
			return data;
		}

		public void setData(T o) {
			data = o;
		}

		public Node<T> getNext() {
			return next;
		}

		public void setNext(Node<T> n) {
			next = n;
		}

		public String toString() {
			return "Node: " + getData().toString();
		}

	} // Node

	// The head and tail of the list.
	private Node<T> head, tail;
	// Keep a size counter as it is more efficient than counting all nodes
	// when a count is needed.
	private int size;

	/** Create an new empty list. */
	public SLL() {
		head = null;
		tail = null;
		size = 0;
	}

	// Public Methods

	/**
	 * Return the number of elements in the list.
	 * 
	 * @return int number of elements in the list.
	 */
	public int size() {
		return size;
	}

	/**
	 * Empty the list.
	 */
	public void emptyList() {
		head = null;
		tail = null;
		size = 0;
	}

	/**
	 * Add a new object t, to the list. It will be added in order of the natural
	 * ordering of T.
	 * 
	 * @param t the object to add
	 */
	public void add(T t) {
		addInOrder(new Node<T>(t), null);
	}

	/**
	 * Add a new object t, to the list. It will be added in the order specified by
	 * c.
	 * 
	 * @param t the object to add
	 * @param c the comparator to use
	 */
	public void add(T t, Comparator<T> c) {
		addInOrder(new Node<T>(t), c);
	}

	/**
	 * Add a new object t to the head of the list.
	 * 
	 * @param t the object to add.
	 */
	public void addHead(T t) {
		addHead(new Node<T>(t));
	}

	/**
	 * Add a new object i to the tail of the list.
	 * 
	 * @param t the object to add.
	 */
	public void addTail(T t) {
		addTail(new Node<T>(t));
	}

	/**
	 * Delete the element at the head of the list.
	 * 
	 * @return the deleted element.
	 */
	public T deleteHead() {
		Node<T> n = delHead();
		return (n == null) ? null : n.getData();
	}

	/**
	 * Return an Iterator over this list.
	 * 
	 * @returns an iterator
	 */
	public Iterator<T> iterator() {
		Iterator<T> it = new Iterator<T>() {

			// The iterator maintains a pointer the the
			// current node, starting with the head.
			private Node<T> currNode = head;

			// The next node to return is the one
			// currNode is pointing to. As long as it
			// is not null, we have another element to return.
			public boolean hasNext() {
				return currNode != null;
			}

			// Return the next element in the list,
			// null if at the end of the list.
			public T next() {
				if (currNode != null) {
					T t = currNode.getData();
					currNode = currNode.getNext();
					return t;
				} else {
					return null;
				}
			}

			// This method is part of the interface and is used
			//
			public void remove() {
				return;
			}
		};

		return it;
	}

	/**
	 * Return true if the list contains f.
	 * 
	 * @return true if f is in the list, false otherwise.
	 */
	public boolean contains(T f) {
		return find(f) != null;
	}

	/**
	 * Return a pointer to the node in the list that is equal to f.
	 * 
	 * @return a pointer to T
	 * @param f the object to find.
	 */
	public T find(T f) {
		Node<T> curr = head;
		while (curr != null && !f.equals(curr.getData())) {
			curr = curr.getNext();
		}
		return (curr == null) ? null : curr.getData();
	}

	/**
	 * Return the ith element of the list.
	 * 
	 * @param i the element to return
	 * @return the ith element, null if there isnt one.
	 */
	public T get(int i) {
		Node<T> curr = head;
		int j = 0;
		while (curr != null && j < i) {
			curr = curr.getNext();
			j++;
		}
		return (curr == null) ? null : curr.getData();
	}

	// Private methods

	/*
	 * Add a new Node to the head of the list.
	 */
	private void addHead(Node<T> n) {
		if (head == null) // empty list
		{
			head = n;
			tail = n;
		} else {
			n.setNext(head);
			head = n;
		}
		size++;
	}

	/*
	 * Add a new Node to the tail of the list.
	 */
	private void addTail(Node<T> n) {
		if (tail == null) // list is empty
		{
			head = n;
			tail = n;
		} else {
			tail.setNext(n);
			tail = n;
		}
		size++;
	}

	/*
	 * This method does the heavy lifting for adding nodes in order. Adds n in the
	 * correct order based on c, or the natural order if c is null.
	 */
	private void addInOrder(Node<T> n, Comparator<T> c) {

		if (head == null) { // The list is empty, so add to the head.
			addHead(n);
		} else {
			if (compareNodes(n, head, c) <= 0) { // less than first item, insert here.
				addHead(n);
			} else {

				Node<T> curr = head;

				while (curr.getNext() != null && compareNodes(n, curr.getNext(), c) > 0) {
					curr = curr.getNext();
				}

				if (curr.getNext() == null) { // did not find a place so add it to the end.
					addTail(n);
				} else { // found the right place so add into the body of the list.
					n.setNext(curr.getNext());
					curr.setNext(n);
					size++;
				}
			}
		}
	}

	/*
	 * Delete the node at the head of the list and return a pointer to it.
	 */
	private Node<T> delHead() {
		Node<T> n = null;
		if (head != null) {
			n = head;
			if (head == tail)
				tail = head.getNext();
			head = head.getNext();
			size--;
		}
		return n;
	}

	/*
	 * Helper method. Compares the data in two nodes, either the comparator c or by
	 * the natural ordering.
	 */
	private int compareNodes(Node<T> n1, Node<T> n2, Comparator<T> c) {
		int r = 0;
		if (c == null)
			r = n1.getData().compareTo(n2.getData());
		else
			r = c.compare(n1.getData(), n2.getData());
		// System.err.println( "Comparator" + c.getClass().getName());
		// System.err.println( "Comparing " + n1.getData() + " to " + n2.getData() + " =
		// " + r);
		return r;
	}
}
