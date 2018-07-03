
public class Node implements Comparable<Node>{
	private Board state;
	private int f;
	Node previous;
	
	public Node() {
		state = new Board();
		f = 0;
		previous = new Node();
	}
	
	public Node(Board b, int f, Node n) {
		state = b;
		this.f = f;
		previous = n;
	}
	
	public Board getState() {
		return state;
	}
	
	public int getCost() {
		return f;
	}
	
	public Node getPrevious() {
		return previous;
	}
	
	public boolean hasPrevious() {
		return previous != null;
	}
	
	public boolean equals(Object o) {
		if(this == o) {
			return true;
		}
		if(o == null) {
			return false;
		}
		if(getClass() == o.getClass()) {
			return state.equals(((Node) o).getState());
		}
		return false;
	}

	@Override
	public int compareTo(Node o) {
		if(f > ((Node) o).getCost()) {
			return 1;
		}
		if(f < ((Node) o).getCost()) {
			return -1;
		}
		return 0;
	}
	
	public String toString() {
		return state.toString();
	}
}
