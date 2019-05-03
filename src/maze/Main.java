package maze;

import java.io.FileNotFoundException;

public class Main {
	public static void main(String args[]) {
		
		// enter path of image here
		String imageFileName = "/Users/Davis/Documents/GitHub/MazeSolver/src/maze/tiny.png";
		MazeProccessor proccessor = null;
		try {
			proccessor = new MazeProccessor(imageFileName);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		if (proccessor == null) {
			System.exit(0);
		}
		Maze maze = proccessor.createMaze();

		maze.findNodes();
		
		Node entrance = maze.getNodes().get(0);
		System.out.println(maze.toString());

		maze.connectNodes();

		maze.solve(entrance);
		
	}
	
}
