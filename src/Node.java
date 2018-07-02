
public class Node implements Comparable<Node>{
	private Board state;
	private int f;
	
	public Node() {
		state = new Board();
		f = 0;
	}
	
	public Node(Board b, int f) {
		state = b;
		this.f = f;
	}
	
	public Board getState() {
		return state;
	}
	
	public int getCost() {
		return f;
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
}
