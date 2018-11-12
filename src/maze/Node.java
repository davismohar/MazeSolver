package maze;

public class Node {
	Node above;
	Node below;
	Node left;
	Node right;
	int x;
	int y;
	
	public Node(int x, int y){
		this.x = x;
		this.y = y;
		above = null;
		below = null;
		left = null;
		right = null;
	}
	
	public void findConnections() {
		//looks for nodes to connect in each direction
	}
	
}
