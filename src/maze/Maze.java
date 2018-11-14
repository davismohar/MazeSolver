package maze;

import java.util.ArrayList;

/**
 * The maze class The entrance must be at the top and the exit must be at the
 * bottom of the maze Its effectively a 2d array of binary where 1's represent a
 * wall and 0's represent a path Nodes are placed on any places where a
 * direction change is possible And then solve connects the nodes from entrance
 * to exit
 */
public class Maze {
	private byte[][] maze;
	private int height;
	private int width;
	private ArrayList<Node> nodes;

	public Maze(int width, int height) {
		maze = new byte[width][height];
		this.width = width;
		this.height = height;
		nodes = new ArrayList<Node>();
	}

	private void findConnections() {
		for (int i = 0; i < nodes.size(); i++) {
			for (int j = i + 1; j < nodes.size(); j++) {
				if (nodes.get(i).getX() == nodes.get(j).getX()) { // if the two nodes have the same x
					if (nodes.get(i).getY() > nodes.get(j).getY()) {// check above
						if (canConnectAbove(nodes.get(i), nodes.get(j))) {
							nodes.get(i).addAbove(nodes.get(j));
							nodes.get(j).addBelow(nodes.get(i));
						}
					} 
					else { // check below
						if (canConnectBelow(nodes.get(i), nodes.get(j))) {
							nodes.get(i).addBelow(nodes.get(j));
							nodes.get(j).addAbove(nodes.get(i));
						}
					}
				}
				if (nodes.get(i).getY() == nodes.get(j).getY()) { // if the two nodes have the same Y
					if (nodes.get(i).getX() > nodes.get(j).getX()) {// check left
						if (canConnectLeft(nodes.get(i), nodes.get(j))) { 
							nodes.get(i).addLeft(nodes.get(j));
							nodes.get(j).addRight(nodes.get(i));
						}
					} 
					else {// check right
						if (canConnectRight(nodes.get(i), nodes.get(j))) { 
							nodes.get(i).addRight(nodes.get(j));
							nodes.get(j).addLeft(nodes.get(i));
						}
					}
				}
			}
		}
	}

	public boolean canConnectAbove(Node node1, Node node2) {
		// if X is the same, check above
		if (node1.getX() != node2.getX()) {
			return false;
		}
		if (node1.getY() < node2.getY()) {
			return false;
		}
		int node1Y = node1.getY() - 1;
		while (node1Y > node2.getY()) {
			if (this.getPoint(node1.getX(), node1Y) == 1) {
				return false;
			}
			node1Y--;
		}
		return true;
	}

	public boolean canConnectBelow(Node node1, Node node2) {
		// if X is the same, check below
		if (node1.getX() != node2.getX()) {
			return false;
		}
		if (node1.getY() > node2.getY()) {
			return false;
		}
		int node1Y = node1.getY() + 1;
		while (node1Y < node2.getY()) {
			if (this.getPoint(node1.getX(), node1Y) == 1) {
				return false;
			}
			node1Y++;
		}
		return true;
	}

	public boolean canConnectLeft(Node node1, Node node2) {
		if (node1.getY() != node2.getY()) {
			return false;
		}
		if (node1.getX() < node2.getX()) {
			return false;
		}
		int node1X = node1.getX() - 1;
		while (node1X > node2.getX()) {
			if (this.getPoint(node1X, node1.getY()) == 1) {
				return false;
			}
			node1X--;
		}
		return true;
	}

	public boolean canConnectRight(Node node1, Node node2) {
		if (node1.getY() != node2.getY()) {
			return false;
		}
		if (node1.getX() > node2.getX()) {
			return false;
		}
		int node1X = node1.getX() + 1;
		while (node1X < node2.getX()) {
			if (this.getPoint(node1X, node1.getY()) == 1) {
				return false;
			}
			node1X++;
		}
		return true;
	}

	public int getHeight() {
		return this.height;
	}

	public int getWidth() {
		return this.width;
	}

	public byte getPoint(int x, int y) {
		// returns the value of the byte at x,y in the maze
		return maze[x][y];
	}

	private Node findEntrance() {
		// finds the entrance at the top of the screen.(should be the only white
		// space)
		Node entrance = null;
		for (int i = 0; i < this.width; i++) {
			if (getPoint(i, 0) == 0) {
				entrance = new Node(i, 0);
			}
		}
		return entrance;
	}

	private Node findExit() {
		// finds the exit at the bottom of the screen. (should be the only white
		// spot in the final row})
		Node exit = null;
		for (int i = 0; i < this.width; i++) {
			if (getPoint(i, this.height - 1) == 0) {
				exit = new Node(i, this.height - 1);
			}
		}
		return exit;
	}

	public void findNodes() {
		//TODO: it picks the farthest node in a particular direction instead of the closest
		//So getRight of (3,1) returns (8,1) instead of (5,1)
		nodes.add(findEntrance());
		for (int i = 0; i < this.getWidth(); i++) { // x iteration
			for (int j = 1; j < this.getHeight() - 1; j++) { // y iteration
				// Don't need to check the top or bottom rows because we have already found the
				// entrance and exits
				if (this.getPoint(i, j) == 0) { // if there is a path at this point
					if (i == 0 || i == this.getWidth() - 1) { // if on right or left edge of maze

						if (this.getPoint(i, j + 1) == 0 || this.getPoint(i, j - 1) == 0) { // if there is path
																							// above or below
							nodes.add(new Node(i, j));
						}

					}

					else { // if its in the middle
						if ((this.getPoint(i, j + 1) == 0 || this.getPoint(i, j - 1) == 0)
								&& (this.getPoint(i + 1, j) == 0 || this.getPoint(i - 1, j) == 0)) {
							// If there a path above or below AND there is a path to left or right
							nodes.add(new Node(i, j));
						}
					}

				}

			}

		}
		nodes.add(findExit());
		System.out.println(nodes);
	}

	public void solve() {
		// finds exit, entrance, and intersections
		// prints order of directions to take (up, down, left, right)
		System.out.println("Solving");
		this.findConnections();
		System.out.println("Solved");

	}

	public void addWall(int x, int y) {
		maze[x][y] = 1;
	}

	public void addPath(int x, int y) {
		maze[x][y] = 0;
	}

	public ArrayList<Node> getNodes() {
		return nodes;
	}

	public String toString() {
		// creates a visual representation of the maze
		StringBuilder builder = new StringBuilder();
		for (int j = 0; j < this.getWidth(); j++) { // y iteration
			for (int i = 0; i < this.getHeight(); i++) { // x iteration
				if (this.getPoint(i, j) == 1) {
					builder.append(getPoint(i, j));
				} else {
					boolean found = false;
					for (int k = 0; k < nodes.size(); k++) {
						if (j == nodes.get(k).getY() && i == nodes.get(k).getX()) {
							builder.append("N");
							found = true;

						}
					}
					if (!found) {
						builder.append(" ");
					}

				}

			}
			builder.append("\n");
		}
		return builder.toString();

	}

}
