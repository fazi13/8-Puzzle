import java.util.Collections;
import java.util.Random;

public class Board {
	private int[] board;
	private final static int[] SOL_INTS = {0, 1, 2, 3, 4, 5, 6, 7, 8};
	public static final Board SOLUTION = new Board(SOL_INTS);
	
	public Board() {
		board = new int[9];
	}
	
	public Board(String input) {
		board = new int[9];
		String regex = "";
		if(input.contains(" ")) {
			regex = " ";
		}
		String[] s = input.split(regex);
		for(int i = 0; i < board.length; i++) {
			board[i] = Integer.parseInt(s[i]);
		}
	}
	
	public Board(int[] input) {
		board = new int[9];
		for(int i = 0; i < board.length; i++) {
			board[i] = input[i];
		}
	}
	
	//returns board array
	public int[] getBoard() {
		return board;
	}
	
	public void setBoard(int[] ints) {
		for(int i = 0; i < board.length; i++) {
			board[i] = ints[i];
		}
	}
	
	public void setBoard(String s) {
		String regex = "";
		if(s.contains(" ")) {
			regex = " ";
		}
		String[] split = s.split(regex);
		for(int i = 0; i < board.length; i++) {
			board[i] = Integer.parseInt(split[i]);
		}
	}
	
	//swaps index of array if one tile is 0
	private void swap(int index1, int index2) {
		if(board[index1] != 0 && board[index2] != 0) {
			//System.out.println("Error, must swap with empty(0) space: " + board[index1] + ", " + board[index2]);
		} else {
			int temp = board[index1];
			board[index1] = board[index2];
			board[index2] = temp;
		}
	}
	
	//swaps with blank tile
	public boolean swapLeft(int index) {
		if(index%3 == 0) {
			//System.out.println("Error, index is at left most: " + index);
			return false;
		} else {
			swap(index, index-1);
			return true;
		}
	}
	
	public boolean swapRight(int index) {
		if(index%3 == 2) {
			//System.out.println("Error, index is at right most: " + index);
			return false;
		} else {
			swap(index, index+1);
			return true;
		}
	}
	
	public boolean swapUp(int index) {
		if(index < 3) {
			//System.out.println("Error, index is at top most: " + index);
			return false;
		} else {
			swap(index, index-3);
			return true;
		}
	}
	
	public boolean swapDown(int index) {
		if(index > 5) {
			//System.out.println("Error, index is at bottom most: " + index);
			return false;
		} else {
			swap(index, index+3);
			return true;
		}
	}
	
	//checks if number at index is correct
	public boolean isCorrectIndex(int i) {
		return board[i] == SOLUTION.getBoard()[i];
	}
	
	//checks if board is solvable
	public boolean isSolvable() {
		if(this.equals(SOLUTION)) {
			return true;
		}
		int count = 0;
		
		//count inversions for each board tile
		for(int i = 0; i < board.length; i++) {
			count += invCount(i);
		}
		//System.out.println("Inversion count: " + count);
		
		//check if count is even or odd
		if(count%2 == 0) {
			return true;
		}
		return false;
	}
	
	//counts number of inversion for given tile
	private int invCount(int i) {
		int count = 0;
		int num = board[i];
		
		/*//ignore blank tile
		if(num == 0) {
			return 0;
		}*/
		
		/*//no need to count
		if(isCorrectIndex(i)) {
			return 0;
		}*/
		
		//start counting and ignore 0
		for(; i < board.length; i++) {
			if(board[i] < num && board[i] != 0) {
				count++;
			}
		}
		return count;
	}
	
	public int find(int num) {
		for(int i = 0; i < board.length; i++) {
			if(num == board[i]) {
				return i;
			}
		}
		return -1;
	}
	
	public void random() {
		board = SOL_INTS;
		Random random = new Random();
		int count = board.length;
		for(int i = count; i > 1; i--) {
			swap(i-1, random.nextInt(i));
		}
	}
	
	//checks if 2 boards are equal
	public boolean equals(Object o) {
		if(this == o) {
			return true;
		}
		if(o == null) {
			return false;
		}
		if(getClass() == o.getClass()) {
			Board board2 = (Board) o;
			for(int i = 0; i < board.length; i++) {
				if(board[i] != board2.getBoard()[i]) {
					return false;
				}
			}
			return true;
		}
		return false;
	}
	
	//prints board in 3x3
	public String toString() {
		String output = "";
		for(int i = 0; i < board.length; i++) {
			if(i > 0 && i%3 == 0) {
				output += "\n";
			}
			output += board[i] + " ";
		}
		return output;
	}
	
	public static Board copy(Board b) {
		int[] temp = new int[b.getBoard().length];
		for(int i = 0; i < temp.length; i++) {
			temp[i] = b.getBoard()[i];
		}
		return new Board(temp);
	}
}
