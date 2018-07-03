
public class Node implements Comparable<Node>{
	private Board state;
	private int f;
	private Node previous;
	private int steps;
	
	public Node() {
		state = new Board();
		f = 0;
		previous = new Node();
		steps = 0;
	}
	
	public Node(Board b, int h, Node n) {
		state = b;
		previous = n;
		if(n == null) {
			steps = 0;
		} else {
			steps = n.getSteps() + 1;
		}
		f = h + steps;
	}
	
	public Board getState() {
		return state;
	}
	
	public int getCost() {
		return f;
	}
	
	public int getSteps() {
		return steps;
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
