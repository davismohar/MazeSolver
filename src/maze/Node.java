package maze;

public class Node {
	Node above = null;
	Node below = null;
	Node left = null;
	Node right = null;
	int x;
	int y;
	
	public Node(int x, int y){
		this.x = x;
		this.y = y;
	}
	
}
