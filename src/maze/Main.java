package maze;

import java.io.FileNotFoundException;

public class Main {
	public static void main(String args[]) {
		//enter path of image here
		String imageFileName = "tiny.png";
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
		System.out.println(maze.toString());
		
	}
}
