import java.util.ArrayList;

public class PuzzleTesterRandom {

	public static void main(String[] args) {
		ArrayList<Board> boardList = new ArrayList<>();
		for(int i = 0; i < 100; i++) {
			Board gb = new Board();
			gb.random();
			boardList.add(Board.copy(gb));
		}
		long startTime = System.nanoTime();
		for(int i = 0; i < boardList.size(); i++) {
			Solver s = new Solver(boardList.get(i));
			s.Solve(false, "h1");
		}
		long endTime = System.nanoTime();
		System.out.println((endTime - startTime)/100.);
		System.out.println("h2");
		startTime = System.nanoTime();
		for(int i = 0; i < boardList.size(); i++) {
			Solver s = new Solver(boardList.get(i));
			s.Solve(false, "h2");
		}
		endTime = System.nanoTime();
		System.out.println((endTime - startTime)/100.);
	}

}
