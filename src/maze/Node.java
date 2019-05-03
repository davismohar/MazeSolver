package maze;
/**
 * This can act as a 2d linked list where each node is linked 
 * above, below, left, and right. Then the path from the entrance to the exit can be found
 * hasBeenVisited is only relevant for solving, as we need to keep track of where we have been.
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
	boolean hasBeenVisited;
	
	public Node(int x, int y){
		this.x = x;
		this.y = y;
		above = null;
		below = null;
		left = null;
		right = null;
		hasBeenVisited = false;
	}
	
	public void addAbove(Node node) {
		this.above = node;
	}
	
	public void addBelow(Node node) {
		this.below = node;
	}
	
	public void addLeft(Node node) {
		this.left = node;
	}
	
	public void addRight(Node node) {
		this.right = node;
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
	
	public boolean hasBeenVisted() {
		return hasBeenVisited;
	}
	
	public void visit() {
		this.hasBeenVisited = true;
	}
	
	public void resetVisit() {
		this.hasBeenVisited = false;
	}
	
	//if any of the neighbors are still availible
	public boolean hasUnvisitedNeighbors() {
		if(this.above != null && !this.above.hasBeenVisited) {
			return true;
		}
		if(this.below != null && !this.below.hasBeenVisited) {
			return true;
		}
		if(this.right != null && !this.right.hasBeenVisited) {
			return true;
		}
		if(this.left != null && !this.left.hasBeenVisited) {
			return true;
		}
		return false;
	}
	
}
