
public class Queue <T extends Comparable<T>> {

	SLL<T> s = new SLL<T>();

	/**
	 * Add an element e of type T to the tail of the queue.
	 * 
	 * @param e the element to enqueue
	 */
	public void enqueue(T e) {
		// elements go in to the tail of the queue and out the head. Easier coding.
		s.addTail(e);
	}

	/**
	 * Remove element from the head of the queue and return a pointer to it.
	 * 
	 * @return a pointer to the first element in the queue, null if the queue is
	 *         empty.
	 */
	public T dequeue() {
		return s.deleteHead();
	}

	/**
	 * Return a pointer to the first element on the queue, without removing it.
	 * 
	 * @return a pointer to the first element on the queue, null if the queue is
	 *         empty.
	 */
	public T peek() {
		return s.get(0);
	}

	/**
	 * Return true if the queue is empty, false otherwise.
	 * 
	 * @return boolean true if stack is empty.
	 */
	public boolean isEmpty() {
		return (s.size() == 0);
	}

	/**
	 * Return the number of elements in the queue.
	 * 
	 * @return int
	 */
	public int size() {
		return s.size();
	}
}
