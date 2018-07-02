import java.util.ArrayList;

public class Solver {
	Board gameboard;
	ArrayList<Node> frontier;
	ArrayList<Node> visited;
	String hfunction;
	
	public Solver(Board gameboard, String h) {
		this.gameboard = gameboard;
		frontier = new ArrayList<>();
		visited = new ArrayList<>();
		hfunction = h;
	}
	
	public void Solve(String verbose) {
		if(hfunction.equals("h1")) {
			
		}
	}
	
	private void SolveH1() {
		
	}
}
