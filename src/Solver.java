import java.util.ArrayList;
import java.util.Collections;
import java.util.Stack;

public class Solver {
	private Node current;
	private ArrayList<Node> frontier;
	private ArrayList<Node> visited;
	private int depth;
	private int nodeCount;
	
	public Solver(Board gameboard) {
		current = new Node(gameboard, 0, null);
		frontier = new ArrayList<>();
		visited = new ArrayList<>();
		depth = 0;
		nodeCount = 0;
	}
	
	// verbose will print solution path, string h chooses which heuristic 
	public void Solve(boolean verbose, String h) {
		// check if puzzle is solvable
		if(current.getState().isSolvable()) {
			// add initial node to visited
			visited.add(current);
			// check if goal state
			if(current.getState().equals(Board.SOLUTION)) {
				getSolutionPath(verbose);
				System.out.println("Solution found");
				return;
			}
			// check all other nodes
			while(true) {
				// returns true if solution found
				if(swapAll(current.getState().find(0), h)) {
					getSolutionPath(verbose);
					break;
				}
				current = frontier.get(0);
				frontier.remove(0);
				visited.add(current);
			}
		} else {
			if(verbose)
				System.out.println(current.getState());
			System.out.println("Puzzle is not solvable");
		}
	}
	
	private void getSolutionPath(boolean print) {
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
		if(print) {
			while(!stack.isEmpty()) {
				System.out.println(stack.pop() + "\n");
			}
			System.out.println("Solution found");
			System.out.println("Depth: " + depth);
			System.out.println("Node count: " + nodeCount);
		}
		System.out.println(depth + ", " + nodeCount);
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
	
	private int sumDistToGoal(Board gameboard) {
		int sum = 0;
		for(int i = 0; i < gameboard.getBoard().length; i++) {
			sum += gameboard.distToGoal(i);
		}
		return sum;
	}
	
	private boolean swapAll(int index, String h) {
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
		
		// swap up
		if(up.swapUp(index)) {
			int hUp = 0;
			if(h.equalsIgnoreCase("h1"))
				hUp = countIncorrect(up);
			else if(h.equalsIgnoreCase("h2"))
				hUp = sumDistToGoal(up);
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
		
		
		// swap down
		if(down.swapDown(index)) {
			int hDown = 0;
			if(h.equalsIgnoreCase("h1"))
				hDown = countIncorrect(down);
			else if(h.equalsIgnoreCase("h2"))
				hDown = sumDistToGoal(down);
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
		
		
		// swap left
		if(left.swapLeft(index)) {
			int hLeft = 0;
			if(h.equalsIgnoreCase("h1"))
				hLeft = countIncorrect(left);
			else if(h.equalsIgnoreCase("h2"))
				hLeft = sumDistToGoal(left);
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
		
		// swap right
		if(right.swapRight(index)) {
			int hRight = 0;
			if(h.equalsIgnoreCase("h1"))
				hRight = countIncorrect(right);
			else if(h.equalsIgnoreCase("h2"))
				hRight = sumDistToGoal(right);
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
