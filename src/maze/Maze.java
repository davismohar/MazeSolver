package maze;
/**
 * The maze class
 * The entrance must be at the top and the exit must be at the bottom of the maze
 * Its effectively a 2d array of binary where 1's represent a wall and 0's represent a path
 * Nodes are placed on any places where a direction change is possible
 * And then solve connects the nodes from entrance to exit
 */
public class Maze {
	private byte[][] maze;
	private int height;
	private int width;

	public Maze(int width, int height) {
		maze = new byte[width][height];
		this.width = width;
		this.height = height;
	}
	
	public int getHeight() {
		return this.height;
	}
	
	public int getWidth() {
		return this.width;
	}
	
	public byte getPoint(int x, int y) {
		//returns the value of the byte at x,y in the maze
		return maze[x][y];
	}

	private Node findEntrance() {
		// finds the entrance at the top of the screen.(should be the only white
		// space)
		Node entrance = null;
		return entrance;
	}

	private Node findExit() {
		// finds the exit at the bottom of the screen. (should be the only white
		// spot in the final row})
		Node exit = null;
		return exit;
	}

	private void findIntersections() {
		// scans row by row to find all the intersections
	}

	public void solve() {
		//finds exit, entrance, and intersections
		// prints order of directions to take (up, down, left, right)
		
		
	}
	
	public void addWall(int x, int y) {
		maze[x][y] = 1;
	}
	public void addPath(int x, int y) {
		maze[x][y] = 0;
	}
	
	public String toString() {
		//creates a visual representation of the maze
		StringBuilder builder = new StringBuilder();
		for (int j = 0; j < this.getWidth(); j++) { //y iteration
			for (int i = 0; i < this.getHeight(); i++) { //x iteration
				builder.append(getPoint(i,j));
			}
			builder.append("\n");
		}
		return builder.toString();
		
	}

}
