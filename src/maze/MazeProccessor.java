package maze;
import java.awt.image.BufferedImage;
import java.io.File;

import javax.imageio.ImageIO;

public class MazeProccessor {
	
	private BufferedImage img = null;
	public MazeProccessor(String filename){
		img = getImage(filename);
	}
	
	public BufferedImage getImage(String filename){
		try{
			return(ImageIO.read(new File(filename)));
		}
		catch (Exception e){
			return null;
		}
		
	}
	public Maze createMaze(){
		Maze maze = new Maze(img.getWidth(), img.getHeight());
		//checks if each pixel is black or white and places an open space or a wall in each corresponding part of the maze
		
		
		return maze;
	}
}
