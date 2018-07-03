import java.util.ArrayList;
import java.util.Collections;
import java.util.Stack;

public class Solver {
	Node current;
	ArrayList<Node> frontier;
	ArrayList<Node> visited;
	String hfunction;
	
	public Solver(Board gameboard, String h) {
		current = new Node(gameboard, 0, null);
		frontier = new ArrayList<>();
		visited = new ArrayList<>();
		hfunction = h;
	}
	
	public void Solve(String verbose) {
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
			swapAll(current.getState().find(0));
			while(true) {
				current = frontier.get(0);
				frontier.remove(0);
				visited.add(current);
				if(swapAll(current.getState().find(0))) {
					if(verbose)
						printSolutionPath();
					System.out.println("Solution found");
					break;
				}
				//System.out.println(frontier.get(0).getCost() + "\n" + frontier.get(0) + "\n");
			}
		} else {
			System.out.println("Puzzle is not solvable");
		}
	}
	
	private void printSolutionPath() {
		Node n = current;
		int depth = 0;
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
		
		// step cost = 1
		int step = 1;
		
		// swap in all directions
		up.swapUp(index);
		down.swapDown(index);
		left.swapLeft(index);
		right.swapRight(index);
		
		// calculate the number of incorrect tiles
		int hUp = countIncorrect(up);
		int hDown = countIncorrect(down);
		int hLeft = countIncorrect(left);
		int hRight = countIncorrect(right);
		
		// create new nodes with the state
		Node nUp = new Node(up, hUp + step, current);
		Node nDown = new Node(down, hDown + step, current);
		Node nLeft = new Node(left, hLeft + step, current);
		Node nRight = new Node(right, hRight + step, current);
		
		// check if any node contains a goal state
		if(nUp.getState().equals(Board.SOLUTION)) {
			current = nUp;
			return true;
		}
		if(nDown.getState().equals(Board.SOLUTION)) {
			current = nDown;
			return true;
		}
		if(nLeft.getState().equals(Board.SOLUTION)) {
			current = nLeft;
			return true;
		}
		if(nRight.getState().equals(Board.SOLUTION)) {
			current = nRight;
			return true;
		}
		
		// add to frontier if state has not been visited yet
		if(!visited.contains(nUp)) {
			frontier.add(nUp);
		}
		if(!visited.contains(nDown)) {
			frontier.add(nDown);
		}
		if(!visited.contains(nLeft)) {
			frontier.add(nLeft);
		}
		if(!visited.contains(nRight)) {
			frontier.add(nRight);
		}
		
		// find next state with lowest f value
		Collections.sort(frontier);
		return false;
	}
}
