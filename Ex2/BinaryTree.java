
public class BinaryTree<T> {

	private TreeNode<T> root;
	
	public BinaryTree () {
		 this.root = null;
	}
	
	public BinaryTree (T value) {
		if (root == null) {
			root = new TreeNode<T>(value);
		}
		root.insertRandomly(value);
	}
	
	public TreeNode<T> getRoot() {
		return this.root;
	}

	public boolean exists(TreeNode<?> value) {
		if (root != null) {
			return root.exists(value);
		}
		
		return false;
	}
	
	public boolean exists(T value) {
		if (root != null) {
			return root.exists(value);
		}
		
		return false;
	}
	
	/**
	 * This method insert a given key as a random leaf in the tree
	 * @param key
	 */
	public void insertRandomly(T value) {
		if (this.exists(value)) {
			System.out.println("Keys must be unique!");
			return;
		}
		
		if (root == null) {
			root = new TreeNode<T>(value);
		} else {
			root.insertRandomly(value);
		}
	}
	
	/**
	 * This method allow to create a new node under a specific node,
	 * and decide whether it's a right or left child
	 * @param newKey - the new key to be inserted
	 * @param parent - the node under which we create the new child
	 * @param isLeft - if true, make it a left child, right otherwise
	 */
	public void insertUnderParent(T newKey, T parent, boolean isLeft) {
		if (this.root == null) {
			this.root = new TreeNode<T>(newKey);
			return;
		}
		
		if (this.root.exists(newKey)) {
			System.out.println("Keys must be unique!");
			return;
		}
		
		if (!this.root.exists(parent)) {
			System.out.println("The parent value is not in the tree.");
			return;
		}
		
		TreeNode<T> insertUnderMe = this.root.find(parent);
		if (insertUnderMe == null) {
			System.out.println(parent.toString() +" is null");
		}
		
		if (isLeft) {
			if(insertUnderMe.getLeft() == null) {
				insertUnderMe.setLeft(newKey);
			} else {
				System.out.println("Key " + parent + " already has a left child!");
				return;
			}
		} else {
			if(insertUnderMe.getRight() == null) {
				insertUnderMe.setRight(newKey);
			} else {
				System.out.println("Key " + parent + " already has a right child!");
				return;
			}
		}
	}
	
	/**
	 * Returns the Node that holds a given value
	 * @param value - value to look for
	 * @returnReturns the Node that holds the given value
	 */
	public TreeNode<T> find(T value) {
		if (this.root.getData() == value) {
			return this.root;
		} else {
			return root.find(value);
		}
	}
	
	/**
	 * This recursive function prints the tree!
	 * @param node - node to print
	 * @param prefix - leading spaces and "|" character
	 * @param isTail - to determine right/left marking
	 * @param sb - accumulated result of printing
	 * @return
	 */
	public StringBuilder printTree(TreeNode<T> node, StringBuilder prefix, boolean isTail, StringBuilder sb) {
	    if(node.getRight() !=null) {
	        printTree(node.getRight(), new StringBuilder().append(prefix).append(isTail ? "│   " : "    "), false, sb);
	    }
	    sb.append(prefix).append(isTail ? "└── " : "┌── ").append(node.toString()).append("\n");
	    if(node.getLeft() != null) {
	        printTree(node.getLeft(), new StringBuilder().append(prefix).append(isTail ? "    " : "│   "), true, sb);
	    }
	    return sb;
	}

	/**
	 * initiates the recursive tree printing method
	 */
	public void printTree() {
		System.out.println("This is the tree diagram:");
	    System.out.println(printTree(this.root, new StringBuilder(), true, new StringBuilder()).toString());
	}
}

