import java.awt.event.KeyEvent;
import java.util.LinkedList;

public class Snake {
	private LinkedList<Point> snake;
	private final int SNAKE_SPACING = 10;
	private int length;
	private int screenWidth;
	private int screenHeight;
	
	public Snake(int screenWidth, int screenHeight) {
		this.length = 3;
		this.snake = new LinkedList<>();
		this.screenWidth = screenWidth;
		this.screenHeight = screenHeight;
		initSnake(this.screenWidth, this.screenHeight);
	}
	
	
	public void initSnake(int screenWidth, int screenHeight) {
		int x = SNAKE_SPACING + (int)(Math.random() * (screenWidth - (2 * SNAKE_SPACING)));
		int y = (3 * SNAKE_SPACING) + (int)(Math.random() * (screenHeight - (2 * SNAKE_SPACING)));
		for (int i = 0; i < this.length; i++) {
			Point p = new Point(x + (i * SNAKE_SPACING / 2), y);
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

		boolean result = isCoordinateHitting(snakeHeadX, appleX, apple_side_length) &&
						 isCoordinateHitting(snakeHeadY,appleY, apple_side_length);
		if (result)
			handleSnakeAteApple(lastPressed);
		return result;
	}

	public boolean isSnakeDead(int screenWidth, int screenHeight) {
		int headX = this.snake.getFirst().getX();
		int headY = this.snake.getFirst().getY();

		boolean hitSelf = false;
		for (int i = 1; i < this.length && !hitSelf; i++)
			hitSelf = this.snake.get(i).equals(new Point(headX, headY));

		return 	headX < SNAKE_SPACING || headX >= screenWidth - SNAKE_SPACING ||
				headY >= screenHeight - SNAKE_SPACING || headY < SNAKE_SPACING ||
				hitSelf;
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
		if (newTail != null)  { // sanity check - should always be true
			snake.add(newTail);
			this.length++;
		}
	}

	private boolean isCoordinateHitting(int source, int dest, int hitBoxSideLength) {
		return source <= dest + hitBoxSideLength && source >= dest - hitBoxSideLength;
	}
}
