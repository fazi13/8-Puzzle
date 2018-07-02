import java.util.Scanner;

public class PuzzleTester {

	public static void main(String[] args) {
		
		Scanner kb = new Scanner(System.in);
		System.out.println("Input gameboard: ");
		String input = "";
		while(true) {
			String line = kb.nextLine();
			if(line.equals("")) {
				break;
			}
			input += line + " ";
		}
		kb.close();
		Board gameboard = new Board(input);
		
		//int[] gb = {1, 2, 3, 4, 0, 5, 8, 6, 7};
		//Board gameboard = new Board(gb);
		System.out.println(gameboard);
		System.out.println(gameboard.isSolvable());
	}

}
