package maze;

import java.io.FileNotFoundException;

public class Main {
	public static void main(String args[]) {
		//enter path of image here
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
		System.out.println("\n==========");
		System.out.println(maze.toString());
		maze.solve();
		Node node = maze.getNodes().get(0);
		System.out.println(node.getBelow().toString());
		System.out.println(maze.getNodes().get(5).toString());
		System.out.print(maze.getNodes().get(5).getRight().toString());
		
	}
}
