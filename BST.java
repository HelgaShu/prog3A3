import java.util.Comparator;

import java.util.Iterator;

public class BST<T extends Comparable<T>> {
	class BSTNode implements Comparable<BSTNode> {
		private T data;
		private BSTNode left;
		private BSTNode right;

		public BSTNode(T d) {
			setLeft(null);
			setRight(null);
			setData(d);
		}

		public T getData() {
			return data;
		}

		public void setData(T d) {
			data = d;
		}

		public void setLeft(BSTNode l) {
			left = l;
		}

		public void setRight(BSTNode r) {
			right = r;
		}

		public BSTNode getLeft() {
			return left;
		}

		public BSTNode getRight() {
			return right;
		}

		public boolean isLeaf() {
			return (getLeft() == null) && (getRight() == null);
		}

		public int compareTo(BSTNode o) {
			return this.getData().compareTo(o.getData());
		}

	}

	// The different traversal types.
	public static final int INORDER = 0;
	public static final int PREORDER = 1;
	public static final int POSTORDER = 2;
	public static final int LEVELORDER = 3;

	private BSTNode root;
	private int size;

	public BST() {
		root = null;
		size = 0;
	}

	/**
	 * Return true if element d is present in the tree.
	 */
	public T find(T d) {
		return find(d, root);
	}

	/**
	 * Add element d to the tree.
	 */
	public void add(T d) {
		BSTNode n = new BSTNode(d);
		if (root == null) {
			size++;
			root = n;
		} else {
			add(root, n);
		}
	}

	/**
	 * Return the height of the tree.
	 */
	public int height() {
		return height(root);
	}

	/**
	 * Return the number of nodes in the tree.
	 */
	public int size() {
		return size;
	}

	public void inOrder() {
		traverse(root, INORDER);
	}

	public void postOrder() {
		traverse(root, POSTORDER);
	}

	public void preOrder() {
		traverse(root, PREORDER);
	}

	public void levelOrder() {
		traverse(root, LEVELORDER);
	}

	// Private methods.

	private T find(T d, BSTNode r) {
		if (r == null)
			return null;
		int c = d.compareTo(r.getData());
		if (c == 0)
			return r.getData();
		else if (c < 0)
			return find(d, r.getLeft());
		else
			return find(d, r.getRight());
	}

	/* Do the actual add of node n to tree rooted at r */
	private void add(BSTNode r, BSTNode n) {
		int c = n.compareTo(r);
		if (c < 0) {
			// the element to be added is less than the root
			if (r.getLeft() == null) {
				// there is no left node so
				// we can add it here
				r.setLeft(n);
				size++;
			} else {
				// add it to the left sub-tree
				add(r.getLeft(), n);
			}
		} else if (c > 0) {
			// the element to be added is greater than the root
			if (r.getRight() == null) {
				// there is no right node so
				// we can add it here
				r.setRight(n);
				size++;
			} else {
				// add it to the right sub-tree
				add(r.getRight(), n);
			}
		}
	}

	/* Implement a height method. */
	private int height(BSTNode r) {
		int h = -1;
		if (r != null) {
			int rh = height(r.getRight());
			int lh = height(r.getLeft());
			h = (rh > lh ? 1 + rh : 1 + lh);
		}
		return h;
	}

	/*
	 * Traverse the tree. travtype determines the type of traversal to perform.
	 */
	private void traverse(BSTNode r, int travType) {
		if (r != null) {
			switch (travType) {
			case INORDER:
				traverse(r.getLeft(), travType);
				visit(r);
				traverse(r.getRight(), travType);
				break;
			case PREORDER:
				visit(r);
				traverse(r.getLeft(), travType);
				traverse(r.getRight(), travType);
				break;
			case POSTORDER:
				traverse(r.getLeft(), travType);
				traverse(r.getRight(), travType);
				visit(r);
				break;
			case LEVELORDER:
				levelOrder(r);
				break;
			}
		}
	}

	private void visit(BSTNode r) {
		if (r != null)
			System.out.println(r.getData());
	}

	/* traverse the subtree r using level order. */
	private void levelOrder(BSTNode r) {
		BSTNode currNode = r;
		Queue<BSTNode> q = new Queue<BSTNode>();

		q.enqueue(currNode);

		while (!q.isEmpty()) {
			currNode = q.dequeue();
			visit(currNode);
			if (currNode.getLeft() != null)
				q.enqueue(currNode.getLeft());
			if (currNode.getRight() != null)
				q.enqueue(currNode.getRight());
		}
	}

	public boolean remove(T other) {

        BSTNode toRemove = new BSTNode(other);
        BSTNode focus = root;
        BSTNode parent = root;

        boolean isItALeftChild = true;

        if (isNaturalOrder()) {
            while (focus.compareTo(toRemove) != 0) {

                parent = focus;
    
                // To find out if we go left or right
                if (focus.compareTo(toRemove) > 0) {
                    isItALeftChild = true;
                    focus = focus.getLeft();
                } else {
                    isItALeftChild = false;
                    focus = focus.getRight();
                }
                // If node is not found false
                if (focus == null) {
                    return false;
                }
    
            }
        }
        else {
            while (comparator.compare(focus.getData(), toRemove.getData()) != 0) {

                parent = focus;
    
                // To find out if we go left or right
                if (comparator.compare(focus.getData(), toRemove.getData()) < 0) {
                    isItALeftChild = true;
                    focus = focus.getLeft();
                } else {
                    isItALeftChild = false;
                    focus = focus.getRight();
                }
                // If node is not found false
                if (focus == null) {
                    return false;
                }
            }
        }
        

        // Case of which node has no children
        if (focus.getLeft() == null && focus.getRight() == null) {

            // if it is just simply a root, we delete it
            if (focus == root) {
                root = null;
            }
            // If it was marked as a left child
            // of the parent, remove it from its parent's field
            else if (isItALeftChild) {
                parent.setLeft(null);
            }
            // opposite for right
            else {
                parent.setRight(null);
            }
        }

        // If no right child
        else if (focus.getRight() == null) {

            if (focus == root) {
                root = focus.getLeft();
            }
            // If focus Node was on the left of parent
            // move the focus Nodes left child up to the
            // parent node
            else if (isItALeftChild) {
                parent.setLeft(focus.getLeft());
            }
            else {
                parent.setRight(focus.getLeft());
            }

        }

        // If no left child
        else if (focus.getLeft() == null) {

            if (focus == root) {
                root = focus.getRight();
            }
            // If focus Node was on the left of parent
            // move the focus Nodes right child up to the
            // parent node
            else if (isItALeftChild) {
                parent.setLeft(focus.getRight());
            }
            else {
                parent.setRight(focus.getRight());
            }
        }

        // Case of two children we need to find a replacement node with a helper method

        else {

            BSTNode replacement = getReplacementNode(focus);

            // If the focus is root replace root
            // with the replacement
            if (focus == root) {
                root = replacement;
            }
            // If the deleted node was a left child
            // make the replacement the left child
            else if (isItALeftChild) {
                parent.setLeft(replacement);
            }
            // we do the opposite for right child
            else {
                parent.setLeft(replacement);
            }
            replacement.setLeft(focus.getLeft());
        }  

        return true;
    }
}
