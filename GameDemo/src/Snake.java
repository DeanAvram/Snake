import java.awt.event.KeyEvent;
import java.util.LinkedList;

public class Snake {
	private LinkedList<Point> snake;
	private final int SNAKE_SPACING = 10;
	private int length;
	
	public Snake() {
		this.length = 3;
		this.snake = new LinkedList<>();
		initSnake();
	}
	
	public void initSnake() {
		int x = SNAKE_SPACING + (int)(Math.random() * 500);
		int y = SNAKE_SPACING + (int)(Math.random() * 400);
		for (int i = 0; i < this.length; i++) {
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

	public int getLength() {
		return length;
	}

	public void setLength(int length) {
		this.length = length;
	}
	
	public void move(Point newHead) {
		this.snake.removeLast();
		this.snake.addFirst(newHead);
	}
	public boolean isSnakeAteApple(Apple apple, int lastPressed) {
		int snakeHeadX = this.snake.getFirst().getX();
		int snakeHeadY = this.snake.getFirst().getY();
		int appleX = apple.getLocation().getX();
		int appleY = apple.getLocation().getY();
		int apple_side_length = apple.getAppleSize();

		boolean isXHitting = snakeHeadX <= appleX + apple_side_length && snakeHeadX >= appleX - apple_side_length;
		boolean isYHitting = snakeHeadY <= appleY + apple_side_length && snakeHeadY >= appleY - apple_side_length;
		boolean result = isXHitting && isYHitting;
		if (result)
			handleSnakeAteApple(lastPressed);
		return result;
	}

	private void handleSnakeAteApple(int lastPressed) {
		Point newTail = null;
		int tailX = snake.getLast().getX(), tailY = snake.getLast().getY();
		switch (lastPressed) {
			case(KeyEvent.VK_UP) -> newTail = new Point(tailX, tailY + SNAKE_SPACING);
			case(KeyEvent.VK_DOWN) -> newTail = new Point(tailX, tailY - SNAKE_SPACING);
			case(KeyEvent.VK_LEFT) -> newTail = new Point(tailX - SNAKE_SPACING, tailY);
			case(KeyEvent.VK_RIGHT) -> newTail = new Point(tailX + SNAKE_SPACING, tailY);
		}
		if (newTail != null) // sanity check - should always be true
			snake.add(newTail);
	}
}
