public class Move {
	private int from;
	private int to;
	
	public Move(int from, int to) {
		this.from = from;
		this.to = to;
	}
	
	public int getFrom() {
		return this.from;
	}
	
	public int getTo() {
		return this.to;
	}
	
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("move from ");
		sb.append(from);
		sb.append(" to ");
		sb.append(to);
		return sb.toString();
	}
}
