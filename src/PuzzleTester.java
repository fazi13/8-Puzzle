import java.util.ArrayList;
import java.util.Scanner;

public class PuzzleTester {

	public static void main(String[] args) {
		/*Board gameboard = new Board();
		Scanner kb = new Scanner(System.in);
		System.out.print("Options: input board(i), random board(r): ");
		String option = kb.nextLine().trim();
		if(option.equalsIgnoreCase("i")) {
			System.out.println("Input gameboard: ");
			String input = "";
			while(true) {
				String line = kb.nextLine().trim();
				if(line.equals("")) {
					break;
				}
				input += line + " ";
			}
			gameboard = new Board(input);
		} else if(option.equalsIgnoreCase("r")) {
			gameboard.random();
			System.out.println(gameboard);
		}*/
		
		//int[] gb = {1, 2, 3, 4, 0, 5, 8, 6, 7};
		//Board gameboard = new Board(gb);
		//Solver solve = new Solver(gameboard, "h1");
		//solve.Solve("true");
		//kb.close();
		int runs = 10;
		ArrayList<Board> boardList = new ArrayList<Board>();
		ArrayList<Solver> solverList = new ArrayList<Solver>();
		for(int i = 0; i < runs; i++) {
			Board gb = new Board();
			gb.random();
			boardList.add(Board.copy(gb));
		}
		for(int i = 0; i < runs; i++) {
			Solver solve = new Solver(boardList.get(i), "h1");
			solve.Solve("false");
			solverList.add(solve);
		}
		for(int i = 0; i < runs; i++) {
			System.out.println(solverList.get(i).getDepth() + ", "+ solverList.get(i).getNodeCount());
		}
	}	
}
