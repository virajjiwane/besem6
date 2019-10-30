import java.util.*;

public class Main{

	static int dimensions;
	static int[] board;
	static int steps=0;
	public static void main(String[] arg){
		System.out.println("Enter dimension: ");
		Scanner scan = new Scanner(System.in);
		dimensions = scan.nextInt();
		if (dimensions<=3) {
		   System.out.println("Invalid dimension");
		   return;
		}

		board = generateBoard();
		displayBoard();
		while(findHeuristic(board)!=0){
			nextBoard();
			displayBoard();
		}

		System.out.println("");

		int[][] map = new int[dimensions][dimensions];
		for(int i=0;i<dimensions;i++){
			map[i][board[i]] = 1;
		}
		for(int i=0;i<dimensions;i++){
			for(int j=0;j<dimensions;j++){
				System.out.print(map[i][j]+" ");
			}
			System.out.println("");
		}

	}
	
	public static int[] generateBoard(){
		Random random = new Random();
		int[] board = new int[dimensions];
		for(int i=0;i<dimensions;i++){
			board[i] = random.nextInt(dimensions);
		}
		return board;
	}
	
	public static void displayBoard(){
		System.out.print("Board: ");
		for(int i=0;i<dimensions;i++){
			System.out.print("("+(i+1)+", "+(board[i])+") ");			
		}
		System.out.println("Heuristic: "+findHeuristic(board)+" Steps: "+steps);		
	}
	
	public static int findHeuristic(int[] board){
		int heuristic = 0;
		for(int i=0;i<dimensions;i++){
			for(int j=i+1;j<dimensions;j++){
				if(board[i]==board[j] || Math.abs(i-j) == Math.abs(board[i]-board[j]))
					heuristic++;
			}
		}
		return heuristic;
	}
	
	public static void nextBoard(){
		steps++;
		int[] temp = board.clone();
		for(int i=0;i<dimensions;i++){
			temp = board.clone();
			temp[i] = (temp[i]+1)%dimensions;
			if(findHeuristic(temp)<findHeuristic(board))
				break;
		}
		if(findHeuristic(temp)>=findHeuristic(board))
			board = generateBoard();
		else
			board = temp;
	}

}
























