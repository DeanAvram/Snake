package gameObjects;
import java.util.LinkedList;
import java.util.Random;

public class Snake {
	
	private LinkedList<Point> snake;
	private int snakeSpacing = 10;
	private int size;
	
	public Snake() {
		this.size = 3;
		this.snake = new LinkedList<Point>();
		initSnake();
	}
	
	public void initSnake() {
		Random rand = new Random();
		int Xmin = 20;
        int Xmax = 620;
        int Ymin = 50;
        int Ymax = 460;
        int scale = 10;
        int x = (rand.nextInt((Xmax - Xmin) + 1) + Xmin) / scale * scale;
        int y = (rand.nextInt((Ymax - Ymin) + 1) + Ymin) / scale * scale;
		for (int i = 0; i < this.size; i++) {
			Point p = new Point(x + (i * snakeSpacing), y);
			this.snake.add(p);
		}
	}

	public LinkedList<Point> getSnake() {
		return snake;
	}

	public void setSnake(LinkedList<Point> snake) {
		this.snake = snake;
	}

	public int getSnakeSpacing() {
		return snakeSpacing;
	}

	public void setSnakeSpacing(int snakeSpacing) {
		this.snakeSpacing = snakeSpacing;
	}

	public int getSize() {
		return size;
	}

	public void setSize(int size) {
		this.size = size;
	}
	
	public void snakeMoved(Point newFirst) {
		this.snake.add(0, newFirst);
		this.snake.removeLast();
	}
	
	
	

}
