import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;

public class Graph <T> {
	//A mapping between a type and its representation as GraphNode object
	private HashMap<T, GraphNode<T>> nodes = new HashMap<>();
	
	//Adjacency set for each GraphNode object
	private HashMap<GraphNode<T>, HashSet<GraphNode<T>>> edges = new HashMap<>();	
	
	/**
	 * Inserts a node to the nodes set in case it wasn't inserted before.
	 * @param key The key of the node. 
	 */
	public void insertNode(T key) {
		if (!nodes.containsKey(key)) {
			GraphNode<T> node = new GraphNode<>(key);
			nodes.put(key, node);
			edges.put(node, new HashSet<>());
		}
	}

	/**
	 * Inserts an edge to the graph, only if it's unique.
	 * @param source The key of the Source node
	 * @param destination The key of the Destination node.
	 */
	public void insertEdge(T source, T destination) {
		this.insertNode(source);
		this.insertNode(destination);
		
		GraphNode<T> sourceNode = this.nodes.get(source);
		GraphNode<T> destinationNode = this.nodes.get(destination);
		
		//Make sure edge is unique.
		if(edges.containsKey(sourceNode) && edges.get(sourceNode).contains(destinationNode)) {
			return;
		}
		
		edges.get(sourceNode).add(destinationNode);
		destinationNode.incInDegree();
	}

	public GraphNode<T> getNode(T key) {
		return this.nodes.get(key);
	}

	/**
	 * 
	 * @return A possible topological sorting of this graph, assuming DAG, using queue.
	 */
	public ArrayList<T> topologicSort() {
		ArrayList<T> topoSort = new ArrayList<>();
		
		//Clone data of edges and nodes
		HashMap<T, GraphNode<T>> topoNodes = (HashMap<T, GraphNode<T>>) this.nodes.clone();	
		HashMap<GraphNode<T>, HashSet<GraphNode<T>>> topoEdges = (HashMap<GraphNode<T>, HashSet<GraphNode<T>>>) this.edges.clone();	
		
		int nodesOfZeroInDegree = 0;
		
		//Finish when all nodes have been processed
		while (nodesOfZeroInDegree < topoNodes.size()) {
		
			Queue<GraphNode<T>> queue = new LinkedList<>();
			
			//Add every 0 inDegree node to the queue
			for(GraphNode<T> node : topoNodes.values()) {
				if (node.getInDegree() == 0) {
					queue.add(node);
					nodesOfZeroInDegree++;
				}
			}
			
			//Poll from the queue a node and decrease its destination nodes inDegree by one
			while (!queue.isEmpty()) {
				GraphNode<T> currentNode = queue.poll();
				topoSort.add(currentNode.getKey());
				for (GraphNode<T> out : topoEdges.get(currentNode)) {
					out.decInDegree();				
				}
				currentNode.decInDegree();	
			}
		}

		return topoSort;
	}
}

