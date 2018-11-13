package maze;
/**
 * This can act as a 2d linked list where each node is linked 
 * above, below, left, and right. Then the path from the entrance to the exit can be found
 * 
 *
 */
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
	
	public void findConnections(Maze maze) {
		findLeft(maze);
		findRight(maze);
		findAbove(maze);
		findBelow(maze);
	}
	
	public Node findAbove(Maze maze) {
		Node node = null;
		int currY = this.getY();
		while (currY > 0) {
			if (maze.getPoint(this.getX(), currY - 1) == 1 && currY != this.getY()) {
				return new Node(this.getX(), currY);
			}
			currY --;
		}
		return node;
		
	}
	
	public Node findBelow(Maze maze) {
		Node node = null;
		int currY = this.getY();
		while (currY < maze.getHeight() - 1) {
			if (maze.getPoint(this.getX(), currY + 1) == 1 && currY != this.getY()) {
				return new Node(this.getX(), currY);
			}
			currY ++;
		}
		return node;
		
	}
	
	public Node findLeft(Maze maze) {
		Node node = null;
		int currX = this.getX();
		while (currX > 0) {
			if (maze.getPoint(currX - 1, this.getY()) == 1 && currX != this.getX()) {
				return new Node(currX, this.getY());
			}
			currX --;
		}
		return node;
		
	}
	
	public Node findRight(Maze maze) {
		Node node = null;
		int currX = this.getY();
		while (currX < maze.getWidth() - 1) {
			if (maze.getPoint(currX + 1, this.getY()) == 1 && currX != this.getX()) {
				return new Node(currX, this.getY());
			}
			currX ++;
		}
		return node;
		
	}
	
	
	
	public Node getAbove() {
		return this.above;
	}
	
	public Node getBelow() {
		return this.below;
	}
	
	public Node getLeft() {
		return this.left;
	}
	
	public Node getRight() {
		return this.right;
	}
	
	public int getX() {
		return this.x;
	}
	
	public int getY() {
		return this.y;
	}
	
	public boolean equals(Node node) {
		if (node == null) {
			return false;
		}
		return(this.getX() == node.getX() && this.getY() == node.getY());
	}
	
	public String toString() {
		return new String("[" + (this.getX()) + ", " + (this.getY())+"]");
	}
	
}
