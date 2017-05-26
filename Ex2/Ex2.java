import java.util.HashSet;
import java.util.Random;
import java.util.Set;

public class Ex2 {

	public static final boolean LEFT = true;
	public static final boolean RIGHT = false;
	
	public static void main(String[] args) {
		System.out.println("This program actually prints the tree so results can be seen.");
		System.out.println("Uncomment one of these two lines in \"main\" to check a tree:");
		System.out.println("//checkWithRandomTree();");
		System.out.println("//checkWithAssignmentTree();");
		//checkWithRandomTree();
		//checkWithAssignmentTree();
	}
	
	/**
	 * This method prints the ancestors of a given node in the Binary Tree
	 * @param tree - The Binary Tree Data Structure to work on
	 * @param targetNode - the node which we would like to print its ancestors
	 */
	public static void printAncestors(BinaryTree<?> tree, TreeNode<?> targetNode) {
		if (tree == null || tree.getRoot() == null) {
			System.out.println("Tree is not initialized yet.");
			return;
		}
		
		if (targetNode == null) {
			System.out.println("Node is not initialized yet.");
			return;
		}
		
		if (!tree.exists(targetNode)) {
			System.out.println("No such node in the tree.");
			return;
		}
		
		System.out.println("Ancestors list of key " + targetNode + ":");
		
		if (tree.getRoot().getData() == targetNode.getData()) {
			System.out.println("No ancestors! you're at the root!");
			return;
		}
		
		printAncestors(tree.getRoot(), targetNode);
		System.out.println("|");
		System.out.println();
	}
	
	/**
	 * A recursive function that outputs the ancestors of a given node.
	 * The function iterates on the "next" node until it reaches the desired value
	 * and then retrace its ancestors in the correct order
	 * @param nextNode - a TreeNode to iterate on
	 * @param targetNode - the desired node to find
	 */
	public static void printAncestors(TreeNode<?> nextNode, TreeNode<?> targetNode) {
		if (nextNode.getData() == targetNode.getData()) {
			System.out.print("Key - ");
		}
		
		boolean hasRight = (nextNode.getRight() != null);
		boolean hasLeft = (nextNode.getLeft() != null);
		
		if (hasRight && nextNode.getRight().exists(targetNode)) {
			printAncestors(nextNode.getRight(), targetNode);
			System.out.print(nextNode.getData() + " - ");
			return;
		}
		
		if (hasLeft && nextNode.getLeft().exists(targetNode)) {
			printAncestors(nextNode.getLeft(), targetNode);
			System.out.print(nextNode.getData() + " - " );
		}	
	}
	
	/**
	 * This method return the lowest common ancestor node of two given nodes.
	 * In case one of them is an ancestor of the other, it'll return the first one it encounter (oldest)
	 * @param tree - the tree to operate on
	 * @param node1 first node to check
	 * @param node2 second node to check
	 * @return the lowest common ancestor of node1 and node2
	 */
	public static TreeNode<?> lowestCommonAncestor(BinaryTree<?> tree, TreeNode<?> node1, TreeNode<?> node2) {
				
		if (tree == null || node1 == null || node2 == null) {
			System.out.println("One of the parameters is not initialized correctly.");
			return null;
		}
		
		if (!tree.exists(node1) || !tree.exists(node2)) {
			System.out.println("At least one of the nodes is not in the tree.");
			return null;
		}
		
		System.out.println("Lowest common ancestors of " + node1.toString() + " and " + node2.toString() +":");
		return lowestCommonAncestor(tree.getRoot(), node1, node2);
	}
	
	/**
	 * This is a recursive function that checks every time if a candidate node
	 * is the lowest common ancestor of node1 and node2.
	 * @param candidate - Iterative node to check if lowest common ancestor of node1 and node2
	 * @param node1 first node to check
	 * @param node2 second node to check
	 * @return The first Node which node1 and node2 are not located in its same subtree (left or right)
	 */
	public static TreeNode<?> lowestCommonAncestor(TreeNode<?> candidate, TreeNode<?> node1, TreeNode<?> node2) {
		if (candidate.getData() == node1.getData() || candidate.getData() == node2.getData()) {
			return candidate;
		}
		
		if (candidate.getRight() == null && candidate.getLeft() == null) {
			return candidate;
		}
		
		boolean existsInLeftSubtree = false;
		if (candidate.getLeft() != null) {
			existsInLeftSubtree = (candidate.getLeft().exists(node1) || candidate.getLeft().exists(node2));
		}
		
		boolean existsInRightSubtree = false;
		if (candidate.getRight() != null) {
			existsInRightSubtree = (candidate.getRight().exists(node1) || candidate.getRight().exists(node2));
		}
		
		if (existsInLeftSubtree && existsInRightSubtree) {
			return candidate;
		}
		
		if (existsInLeftSubtree) {
			return lowestCommonAncestor(candidate.getLeft(), node1, node2);
		} else  {
			return lowestCommonAncestor(candidate.getRight(), node1, node2);
		}
	}
	
	/**
	 * Runs the checks given in the assignment paper,
	 * With the tree given in the assignment
	 */
	private static void checkWithAssignmentTree() {
		BinaryTree<Integer> tree = new BinaryTree<>();
		tree.insertRandomly(16);
		tree.insertUnderParent(9, 16, LEFT);
		tree.insertUnderParent(18, 16, RIGHT);
		tree.insertUnderParent(3, 9, LEFT);
		tree.insertUnderParent(14, 9, RIGHT);
		tree.insertUnderParent(1, 3, LEFT);
		tree.insertUnderParent(5, 3, RIGHT);
		tree.insertUnderParent(19, 18, RIGHT);
		tree.printTree();
		
		printAncestors(tree, new TreeNode<Integer>(5)); //3, 9, 16
		System.out.println(lowestCommonAncestor(tree, new TreeNode<Integer>(14), new TreeNode<Integer>(5))); // 9
	}
	
	/**
	 * Builds a random tree and arguments to run the checks on.
	 */
	private static void checkWithRandomTree() {
		int setSize = 20;		

		BinaryTree<Integer> tree = new BinaryTree<>();
		for (Integer i : createSet(setSize)) {
			tree.insertRandomly(i);
		}
		
		tree.printTree();
		
		Random random = new Random();
		int x = (random.nextInt(20) + 1) * 5;
		int y = (random.nextInt(20) + 1) * 5;
		int z = (random.nextInt(20) + 1) * 5;
		
		printAncestors(tree, new TreeNode<Integer>(x));
		
		TreeNode<Integer> node1 = new TreeNode<>(y);
		TreeNode<Integer> node2 = new TreeNode<>(z);
		
		System.out.println(lowestCommonAncestor(tree, node1, node2));
	}

	/**
	 * @param the set size
	 * @return a unique set of #size different Integer keys
	 */
	private static HashSet<Integer> createSet(int setSize) {
		int counter = 0;
		
		Random random = new Random();
		HashSet<Integer> set = new HashSet<Integer>();
		
		while (counter < setSize) {
			int x = (random.nextInt(20) + 1) * 5;
			if (set.contains(x)) {
				continue;
			} else {
				set.add(x);
				++counter;
			}
		}
		
		return set;
	}
}
