package maze;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;

import javax.imageio.ImageIO;

/**
 * This is in charge of loading the image and transforming it into a maze object
 * 
 *
 */
public class MazeProccessor {

	private BufferedImage img = null;

	public MazeProccessor(String filename) throws FileNotFoundException {
		img = getImage(filename);
	}

	public BufferedImage getImage(String filename) throws FileNotFoundException {
		try {
			return (ImageIO.read(new File(filename)));
		} catch (Exception e) {
			throw new FileNotFoundException("File does not exist");

		}

	}

	public Maze createMaze() {
		Maze maze = new Maze(img.getWidth(), img.getHeight());
		// checks if each pixel is black or white and places an open space(0) or a
		// wall(1) in each corresponding part of the maze
		for (int i = 0; i < img.getWidth(); i++) {
			for (int j = 0; j < img.getHeight(); j++) {
				int color = img.getRGB(i, j);

				int blue = color & 0x000000ff;

				if (blue == 0) {
					maze.addWall(i, j);
					
				} else if (blue == 255) {
					maze.addPath(i, j);
					
				}

			}
		}

		return maze;
	}
}
