import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class PuzzleTester {

	public static void main(String[] args) throws FileNotFoundException {
		Board gameboard = new Board();
		Scanner kb = new Scanner(System.in);
		System.out.print("Options: input board(i), random board(r), read from file(f): ");
		String option = kb.nextLine().trim();
		ArrayList<Board> input = new ArrayList<>();
		if(option.equalsIgnoreCase("i")) {
			System.out.println("Input gameboard: ");
			String s = "";
			while(true) {
				String line = kb.nextLine().trim();
				if(line.equals("")) {
					break;
				}
				s += line + " ";
			}
			input.add(new Board(s.trim()));
		} else if(option.equalsIgnoreCase("r")) {
			gameboard.random();
			input.add(gameboard);
		} else if(option.equalsIgnoreCase("f")) {
			System.out.print("filename: ");
			File f = new File(kb.nextLine().trim());
			Scanner reader = new Scanner(f);
			while(reader.hasNextLine()) {
				String in = reader.nextLine();
				if(!in.contains("Depth"))
					input.add(new Board(in.trim()));
			}
			reader.close();
		}
		boolean verbose = false;
		String h = "";
		System.out.print("verbose (y/n): ");
		option = kb.nextLine().trim();
		if(option.equalsIgnoreCase("y")) {
			verbose = true;
		}
		System.out.print("heuristic (h1/h2): ");
		h = kb.nextLine().trim();
		for(int i = 0; i < input.size(); i++) {
			gameboard = input.get(i);
			Solver s = new Solver(gameboard);
			s.Solve(verbose, h);
		}
		System.out.println("Closing program..");
		kb.close();
	}	
}
