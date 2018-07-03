import java.util.ArrayList;
import java.util.Collections;
import java.util.Stack;

public class Solver {
	private Node current;
	private ArrayList<Node> frontier;
	private ArrayList<Node> visited;
	private String hfunction;
	private int depth;
	private int nodeCount;
	
	public Solver(Board gameboard, String h) {
		current = new Node(gameboard, 0, null);
		frontier = new ArrayList<>();
		visited = new ArrayList<>();
		hfunction = h;
		depth = 0;
		nodeCount = 0;
	}
	
	public void Solve(String verbose) {
		if(current.getState().equals(Board.SOLUTION)) {
			System.out.println("Solution found");
			return;
		}
		boolean v = false;
		if(verbose.equalsIgnoreCase("true")) {
			v = true;
		}
		if(hfunction.equals("h1")) {
			SolveH1(v);
		}
	}
	
	private void SolveH1(boolean verbose) {
		if(current.getState().isSolvable()) {
			visited.add(current);
			//swapAll(current.getState().find(0));
			while(true) {
				if(swapAll(current.getState().find(0))) {
					if(verbose)
						printSolutionPath();
					System.out.println("Solution found");
					break;
				}
				current = frontier.get(0);
				frontier.remove(0);
				visited.add(current);
				//System.out.println(frontier.get(0).getCost() + "\n" + frontier.get(0) + "\n");
			}
		} else {
			System.out.println("Puzzle is not solvable");
		}
	}
	
	private void printSolutionPath() {
		Node n = current;
		Stack<Node> stack = new Stack<>();
		while(true) {
			stack.push(n);
			if(n.hasPrevious()) {
				n = n.getPrevious();
				depth++;
			} else {
				break;
			}
		}
		while(!stack.isEmpty()) {
			System.out.println(stack.pop() + "\n");
		}
		System.out.println("Depth: " + depth);
		System.out.println("Node count: " + nodeCount);
	}
	
	private int countIncorrect(Board gameboard) {
		int count = 0;
		for(int i = 0; i <gameboard.getBoard().length; i++) {
			if(!gameboard.isCorrectIndex(i)) {
				count++;
			}
		}
		return count;
	}
	
	private boolean swapAll(int index) {
		// copy board 4 times
		Board up = Board.copy(current.getState());
		Board down = Board.copy(current.getState());
		Board left = Board.copy(current.getState());
		Board right = Board.copy(current.getState());
		
		// swap in a direction
		// then get h function
		// then create node
		// then add to node count
		// then add node to frontier if not visited
		if(up.swapUp(index)) {
			int hUp = countIncorrect(up);
			Node nUp = new Node(up, hUp, current);
			nodeCount++;
			if(nUp.getState().equals(Board.SOLUTION)) {
				current = nUp;
				return true;
			}
			if(!visited.contains(nUp)) {
				frontier.add(nUp);
			}
		}
		
		
		if(down.swapDown(index)) {
			int hDown = countIncorrect(down);
			Node nDown = new Node(down, hDown, current);
			nodeCount++;
			if(nDown.getState().equals(Board.SOLUTION)) {
				current = nDown;
				return true;
			}
			if(!visited.contains(nDown)) {
				frontier.add(nDown);
			}
		}
		
		if(left.swapLeft(index)) {
			int hLeft = countIncorrect(left);
			Node nLeft = new Node(left, hLeft, current);
			nodeCount++;
			if(nLeft.getState().equals(Board.SOLUTION)) {
				current = nLeft;
				return true;
			}
			if(!visited.contains(nLeft)) {
				frontier.add(nLeft);
			}
		}
		
		if(right.swapRight(index)) {
			int hRight = countIncorrect(right);
			Node nRight = new Node(right, hRight, current);
			nodeCount++;
			if(nRight.getState().equals(Board.SOLUTION)) {
				current = nRight;
				return true;
			}
			if(!visited.contains(nRight)) {
				frontier.add(nRight);
			}
		}
		
		Collections.sort(frontier);
		return false;
	}
	
	public int getDepth() {
		Node n = current;
		Stack<Node> stack = new Stack<>();
		while(true) {
			stack.push(n);
			if(n.hasPrevious()) {
				n = n.getPrevious();
				depth++;
			} else {
				break;
			}
		}
		return depth;
	}
	
	public int getNodeCount() {
		return nodeCount;
	}
}
