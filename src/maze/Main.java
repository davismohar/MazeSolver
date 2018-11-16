package maze;

import java.io.FileNotFoundException;
import java.util.ArrayList;

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
		System.out.println("\n==========");
		System.out.println(maze.toString());
		Node node1 = maze.getNodes().get(0);
		maze.solve();
		System.out.println(node1.getBelow());
		ArrayList<Node> nodes = maze.getNodes();
		for (int i = 0; i < maze.getNodes().size(); i++) {
			System.out.println(nodes.get(i));
			System.out.println("Above: " + nodes.get(i).getAbove());
			System.out.println("Below: " + nodes.get(i).getBelow());
			System.out.println("Left: " + nodes.get(i).getLeft());
			System.out.println("Right: " + nodes.get(i).getRight() + "\n\n");
		}

	}
}
