public class GraphNode <T> {
	
	private int inDegree = 0;
	private T key;
	
	public GraphNode(T key) {
		this.key = key;
	}
	
	public T getKey() {
		return this.key;
	}
	
	public int getInDegree() {
		return this.inDegree;
	}
	
	public void incInDegree() {
		this.inDegree++;
	}
	
	public void decInDegree() {
		this.inDegree--;
	}
	
	@Override
	public String toString() {
		return String.valueOf(key);
	}
}
