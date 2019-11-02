import java.util.*;
public class Astar{
	public static void main(String[] args) {
		Node start = new Node(0);
		start.initializeBoard();
		start.displayBoard();
		PriorityQueue<Node> openNodes = new PriorityQueue<>(new Comparator<Node>(){
			public int compare(Node o1, Node o2){
				if(o1.getCost()!=o2.getCost())
					return o1.getCost()-o2.getCost();
				return o1.getHeuristic() - o2.getCost();
			}
		});
		
		openNodes.add(start);
		
		ArrayList<Node> closeNodes = new ArrayList<Node>();
		Stack<Node> solution = new Stack<Node>();
		
		while(openNodes.isEmpty() == false){
			Node current = openNodes.poll();
			if(current.getHeuristic()==0){
				while(current!=null){
					solution.push(current);
					current = current.parent;
				}
				break;
			}
			
			closeNodes.add(current);
			
			for(Node move:current.getMoves()){
				boolean isClosed = false;
				for(Node close : closeNodes){
					if(close.isSame(move)){
						isClosed = true;
						break;
					}
				}
				
				for(Node open : openNodes){
					if(open.isSame(move)&&(move.getCost()<open.getCost())){
						Node tempParent = open.parent;
						open.parent = move.parent;
						move.parent = tempParent;
						int height = open.height;
						open.height = move.height;
						move.height = open.height;
						
						isClosed = true;
						break;
					}
				}
				
				if(!isClosed){
					openNodes.add(move);
				}
			}
		}
		
		if(solution.isEmpty()){
			System.out.println("No solution found");		
		}else{
			while(!solution.isEmpty()){
				solution.pop().displayBoard();
				System.out.println("");			
				
			}
		}
	}
}

class Node{
	public int[][] board;
	public int height;
	public Node parent;
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
		ArrayList<Node> moves = new ArrayList<Node>();
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
			Node move = getCopy();
			move.parent = this;
			move.height = this.height+1;
			int t = move.board[row][col];
			move.board[row][col] = move.board[row-1][col];
			move.board[row-1][col] = t;
			moves.add(move);
		}
		if(row<2){
			Node move = getCopy();
			move.parent = this;
			move.height = this.height+1;
			int t = move.board[row][col];
			move.board[row][col] = move.board[row+1][col];
			move.board[row+1][col] = t;
			moves.add(move);
		}
		if(col>0){
			Node move = getCopy();
			move.parent = this;
			move.height = this.height+1;
			int t = move.board[row][col];
			move.board[row][col] = move.board[row][col-1];
			move.board[row][col-1] = t;
			moves.add(move);
		}
		if(col<2){
			Node move = getCopy();
			move.parent = this;
			move.height = this.height+1;
			int t = move.board[row][col];
			move.board[row][col] = move.board[row][col+1];
			move.board[row][col+1] = t;
			moves.add(move);
		}
		return moves;
	}
}




































