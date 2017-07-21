
public class TreeNode<T>  {
	
	private T data;
	private TreeNode<T> left;
	private TreeNode<T> right;
	
	public TreeNode(T data) {
		this.data = data;
	}

	public T getData() {
		return this.data;
	}
	
	public TreeNode<T> getLeft() {
		return this.left;
	}
	
	public TreeNode<T> getRight() {
		return this.right;
	}
	
	public void setLeft(T value) {
		this.left = new TreeNode<T>(value);
	}
	
	public void setRight(T value) {
		this.right = new TreeNode<T>(value);
	}
	
	public void setLeft(TreeNode<T> node) {
		this.left = node;
	}
	
	public void setRight(TreeNode<T> node) {
		this.left = node;
	}
	
	/**
	 * This method insert a given key as a random leaf in the tree
	 * @param key
	 */
	public void insertRandomly(T key) {
		
		boolean hasRight = (this.right != null);
		boolean hasLeft = (this.left != null);
		
		boolean goLeft = Math.random() < 0.5;
		boolean createNew = Math.random() < 0.5;
		
		if (!hasRight && !hasLeft) {
			if (goLeft) {
				left = new TreeNode<T>(key);
			} else {
				right = new TreeNode<T>(key);
			}
			return;
		}
		
		if (hasRight && hasLeft) {
			if (goLeft) {
				this.left.insertRandomly(key);
			} else {
				this.right.insertRandomly(key);
			}
			return;
		}
		
		if (hasLeft && !hasRight)
		{
			if (createNew) {
				right = new TreeNode<T>(key);
			} else {
				left.insertRandomly(key);
			}
			return;
		}
		
		if (!hasLeft && hasRight)
		{
			if (createNew) {
				left = new TreeNode<T>(key);
			} else {
				right.insertRandomly(key);
			}
			return;
		}
	}
	
	/**
	 * Checks if a given node exists in the tree
	 * @param node - node to check
	 * @return true if exists, false otherwise
	 */
	public boolean exists(TreeNode<?> node) {
		if (this.data == node.getData()) {
			return true;
		}
		
		boolean leftExists = (left != null);
		boolean rightExists = (right != null);
		
		if (leftExists && rightExists) {
			return (left.exists(node) || right.exists(node));
		}
		
		if (leftExists && !rightExists) {
			return left.exists(node);
		}
		
		if (!leftExists && rightExists) {
			return right.exists(node);
		}
		
		return false;
	}
	
	/**
	 * Checks if a given value exists in the tree
	 * @param value - value to check
	 * @return true if exists, false otherwise
	 * @return
	 */
	public boolean exists(T value) {
		if (this.data == value) {
			return true;
		}
		
		boolean leftExists = (left != null);
		boolean rightExists = (right != null);
		
		if (leftExists && rightExists) {
			return (left.exists(value) || right.exists(value));
		}
		
		if (leftExists && !rightExists) {
			return left.exists(value);
		}
		
		if (!leftExists && rightExists) {
			return right.exists(value);
		}
		
		return false;
	}

	/**
	 * Returns the Node that holds a given value
	 * @param value - value to look for
	 * @returnReturns the Node that holds the given value
	 */
	public TreeNode<T> find(T value) {
		if (this.data == value) {
			return this;
		}
		
		boolean hasLeft = (left != null);
		boolean hasRight = (right != null);
		
		if (hasLeft && this.left.exists(value)) {
			return left.find(value);
		}
		
		if (hasRight) {
			return right.find(value);
		}
		
		return null;
	}
	
	@Override
	public String toString() {
		return this.data.toString();
	}
	
}
