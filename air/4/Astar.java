import java.util.*;
public class Astar{
	public static void main(String[] args) {
		Node start = new Node(0);
		start.initializeBoard();
		start.displayBoard();
	}
}

class Node{
	int[][] board;
	int height;
	Node parent;
	public Node(int height){
		this.height = height;
		parent = null;
		board = new int[3][3];
	}
	public void initializeBoard(){
		Scanner scan = new Scanner(System.in);
		System.out.println("Enter the board:");
		for(int i=0;i<3;i++){
			for(int j=0;j<3;j++){
				board[i][j] = scan.nextInt();
			}
		}
	}

	public void displayBoard(){
		for(int i=0;i<3;i++){
			for(int j=0;j<3;j++){
				System.out.print(" "+board[i][j]);	
			}
			System.out.println("");			
		}
		System.out.println("Heuristic: "+getHeuristic()+"\tCost: "+getCost());
		
		
	}
	
	public boolean isSame(Node otherNode){
		for(int i=0;i<3;i++){
			for(int j=0;j<3;j++){
				if(this.board[i][j]!=otherNode.board[i][j]){
					return false;
				}
			}
		}
		return true;
	}
	
	public Node getCopy(){
		Node copy = new Node(this.height);
		copy.parent = this.parent;
		copy.board = this.board;
		return copy;
	}
	
	public int getHeuristic(){
		int heuristic = 0;
		for(int i=0;i<3;i++){
			for(int j=0;j<3;j++){
				switch(board[i][j]){
					case 1:
						heuristic += (Math.abs(i-0)+Math.abs(j-0));
						break;
					case 2:
						heuristic += (Math.abs(i-0)+Math.abs(j-1));
						break;
					case 3:
						heuristic += (Math.abs(i-0)+Math.abs(j-2));
						break;
					case 4:
						heuristic += (Math.abs(i-1)+Math.abs(j-0));
						break;
					case 5:
						heuristic += (Math.abs(i-1)+Math.abs(j-1));
						break;
					case 6:
						heuristic += (Math.abs(i-1)+Math.abs(j-2));
						break;
					case 7:
						heuristic += (Math.abs(i-2)+Math.abs(j-0));
						break;
					case 8:
						heuristic += (Math.abs(i-2)+Math.abs(j-1));
						break;
					default:
						break;
				}	
			}
		}
		return heuristic;		
	}
	
	public int getCost(){
		return height + getHeuristic();
	}
	
	public List<Node> getMoves(){
		ArrayList<Node> = new ArrayList<Node>();
		int row = 0;
		int col = 0;
		for(int i=0;i<3;i++){
			for(int j=0;j<3;j++){
				if(board[i][j]==0 || board[i][j]==9){
					row = i;
					col = j;
					break;
				}
			}
		}
		
		if(row>0){
		
		}
		if(row<2){
		
		}
		if(col>0){
		
		}
		if(col<2){
		
		}
	}
}




































