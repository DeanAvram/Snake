import java.util.LinkedList;

public class Snake {
	
	private LinkedList<Point> snake;
	private final int SNAKE_SPACING = 10;
	private int size;
	
	public Snake() {
		this.size = 3;
		this.snake = new LinkedList<Point>();
		initSnake();
	}
	
	public void initSnake() {
		int x = 10 + (int)(Math.random() * 500);
		int y = 10 + (int)(Math.random() * 400);
		for (int i = 0; i < this.size; i++) {
			Point p = new Point(x + (i * SNAKE_SPACING), y);
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
		return SNAKE_SPACING;
	}

	public int getSize() {
		return size;
	}

	public void setSize(int size) {
		this.size = size;
	}
	
	public void snakeMoved(Point newHead) {
		this.snake.add(0, newHead);
		this.snake.removeLast();
	}
}
