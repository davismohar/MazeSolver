package maze;

import java.util.ArrayList;
import java.util.Stack;

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
	private Stack<Node> stack;

	public Maze(int width, int height) {
		maze = new byte[width][height];
		this.width = width;
		this.height = height;
		nodes = new ArrayList<Node>();
		stack = new Stack<Node>();
	}

	public Node findConnectionAbove(Node node) {

		int x = node.getX();
		for (int y = node.getY() - 1; y >= 0; y--) {
			if (this.getPoint(x, y) == 1) {
				return null;
			}
			if (this.nodeAt(x, y) != null) {
				return this.nodeAt(x, y);
			}
		}
		return null;

	}

	public Node findConnectionBelow(Node node) {
		int x = node.getX();
		for (int y = node.getY() + 1; y < this.getHeight(); y++) {
			if (this.getPoint(x, y) == 1) {
				return null;
			}
			if (this.nodeAt(x, y) != null) {
				return this.nodeAt(x, y);
			}
		}
		return null;
	}

	public Node findConnectionLeft(Node node) {
		int y = node.getY();
		for (int x = node.getX() - 1; x >= 0; x--) {
			if (this.getPoint(x, y) == 1) {
				return null;
			}
			if (this.nodeAt(x, y) != null) {
				return this.nodeAt(x, y);
			}
		}
		return null;
	}

	public Node findConnectionRight(Node node) {
		int y = node.getY();
		for (int x = node.getX() + 1; x < this.getWidth(); x++) {
			if (this.getPoint(x, y) == 1) {
				return null;
			}
			if (this.nodeAt(x, y) != null) {
				return this.nodeAt(x, y);
			}
		}
		return null;
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

	public Node nodeAt(int x, int y) {
		if (getPoint(x, y) == 1) {
			return null;
		}
		for (int i = 0; i < nodes.size(); i++) {
			if (nodes.get(i).getX() == x && nodes.get(i).getY() == y) {
				return nodes.get(i);
			}
		}
		return null;
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
		// TODO: see if this can be recursive
		// So getRight of (3,1) returns (8,1) instead of (5,1)
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

	public void connectNodes() {
		for (int i = 0; i < nodes.size(); i++) {
			nodes.get(i).addAbove(this.findConnectionAbove(nodes.get(i)));
			nodes.get(i).addBelow(this.findConnectionBelow(nodes.get(i)));
			nodes.get(i).addLeft(this.findConnectionLeft(nodes.get(i)));
			nodes.get(i).addRight(this.findConnectionRight(nodes.get(i)));
		}
	}

	/**
	 * This starts at the entrance, and finds a path to the end. Adding all nodes on
	 * the path to the solvePath arraylist
	 */
	public void solve(Node currentNode) {
		if (!stack.contains(currentNode)) {
			stack.push(currentNode);
		}
		currentNode.visit();
		if(currentNode.equals(nodes.get(nodes.size() - 1))) {
			System.out.print("Solution: ");
			System.out.println(stack.toString());
		}
		else if(currentNode.hasUnvisitedNeighbors()) {
			if(currentNode.right != null && !currentNode.right.hasBeenVisited) {
				stack.push(currentNode.right);
			}
			else if(currentNode.left != null && !currentNode.left.hasBeenVisited) {
				stack.push(currentNode.left);
			}
			else if(currentNode.above != null && !currentNode.above.hasBeenVisited) {
				stack.push(currentNode.above);
			}
			else {
				stack.push(currentNode.below);
			}
			solve(stack.peek());
		}
		else {
			stack.pop();
			solve(stack.peek());
		}
		/*
		solutionList.add(currentNode);
		System.out.println("Visiting" + currentNode.toString());
		// checks to see if currentNode is equal to the exit node(the last node in the
		// list of nodes)
		if (currentNode.equals(nodes.get(nodes.size() - 1))) {
			solutionList.add(currentNode);
			return true;
		} else if (currentNode.left != null && !currentNode.left.hasBeenVisited) {
			return solve(currentNode.left);

		} else if (currentNode.right != null && !currentNode.right.hasBeenVisited) {
			return solve(currentNode.right);

		} else if (currentNode.above != null && !currentNode.above.hasBeenVisited) {
			return solve(currentNode.above);

		} else if (currentNode.below != null && !currentNode.below.hasBeenVisited) {
			return solve(currentNode.below);

		} else {
			return false;
		}
		*/
	}

	public void printSolution() {
		System.out.println("Solution: ");
		//System.out.print(solutionList.toString());
	}

	public void clearVisits() {
		for (int i = 0; i < nodes.size(); i++) {
			nodes.get(i).resetVisit();
		}
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
