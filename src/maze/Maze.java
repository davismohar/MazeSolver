package maze;


public class Maze {
	private byte[][] maze;
	
	public Maze(int width, int height) {
		maze = new byte[width][height];
	}
	
	public Node findEntrance() {
		//finds the entrance at the top of the screen.(should be the only white space)
		Node entrance = null;
		return entrance;
	}
	
	public Node findExit() {
		//finds the exit at the bottom of the screen. (should be the only white spot in the final row})
		Node exit = null;
		return exit;
	}
	
	public void findIntersections() {
		//scans row by row to find all the intersections
	}
	
}
