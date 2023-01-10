import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyEvent;

public class PlayState extends GameState {

	private final int LIFE_BOX_SIZE = 15;
	private final int LIFE_BOX_SPACING = 20;

	boolean active;
	Snake snake;
	Apple apple;
	int lastPressed = 0;
	float deltaTimeAverage;
	int level;
	int lives = 2;
	
	String message;
	
	public PlayState() {}
	
	public void enter(Object memento) {
		active = true;
		Level l = (Level)memento;
		level = l.getLevel();
		deltaTimeAverage = 0;
		snake = new Snake();
		apple = new Apple();
	}
	
	
	public void processKeyPressed(int aKeyCode) {}

	public void processKeyReleased(int aKeyCode) {
		Point newFirst = new Point(snake.getSnake().getFirst());
		if (aKeyCode == KeyEvent.VK_ESCAPE)
			System.exit(0);

		switch(aKeyCode) {
			case(KeyEvent.VK_LEFT) -> newFirst.setX(newFirst.getX() - snake.getSnakeSpacing());
			case(KeyEvent.VK_RIGHT) -> newFirst.setX(newFirst.getX() + snake.getSnakeSpacing());
			case(KeyEvent.VK_UP) -> newFirst.setY(newFirst.getY() - snake.getSnakeSpacing());
			case(KeyEvent.VK_DOWN) -> newFirst.setY(newFirst.getY() + snake.getSnakeSpacing());
		}

		snake.move(newFirst);
		lastPressed = aKeyCode;
	}
	
	public void update(long deltaTime) {
		deltaTimeAverage = deltaTimeAverage * level * 0.9f + 0.001f*(float)deltaTime;
		float deltaPos = deltaTimeAverage * this.snake.getLength();
		Point snakeHead = this.snake.getSnake().getFirst();
		if (lastPressed == KeyEvent.VK_LEFT) {
			int newX = (int)(snakeHead.getX() - deltaPos);
			this.snake.move(new Point(newX, snakeHead.getY()));
		}

		if (lastPressed == KeyEvent.VK_RIGHT) {
			int newX = (int)(snakeHead.getX() + deltaPos);
			this.snake.move(new Point(newX, snakeHead.getY()));
		}

		if (lastPressed == KeyEvent.VK_UP){
			int newY = (int)(snakeHead.getY() - deltaPos);
			this.snake.move(new Point(snakeHead.getX(), newY));
		}

		if (lastPressed == KeyEvent.VK_DOWN) {
			int newY = (int)(snakeHead.getY() + deltaPos);
			this.snake.move(new Point(snakeHead.getX(), newY));
		}
	}
	
	public boolean isActive() { return active; }
	
	public String next() { return "End"; }

	public void render(GameFrameBuffer aGameFrameBuffer) {
		if (lives != 3)
			drawWaitToNewGame(aGameFrameBuffer);

		Graphics g = aGameFrameBuffer.graphics();
		drawLivesMeter(g);
		drawSnakeSprite(g);
		drawAppleSprite(g);
	}

	private void drawAppleSprite(Graphics g) {
		int apple_link_size = this.apple.getAppleSize();
		g.setColor(Color.red);
		g.fillOval(apple.getLocation().getX(), apple.getLocation().getY(), apple_link_size, apple_link_size);
	}

	private void drawSnakeSprite(Graphics g) {
		int snake_link_size = this.snake.getSnakeSpacing();
		g.setColor(Color.white);
		for (Point p: this.snake.getSnake())
			g.fillOval(p.getX(), p.getY(), snake_link_size, snake_link_size);

	}

	private void drawLivesMeter(Graphics g) {
		String text = "LIVES";
		g.drawString("LIVES", LIFE_BOX_SPACING, LIFE_BOX_SPACING);
		g.setColor(Color.RED);
		for (int i = 0; i < lives; i++) {
			g.fillRect(LIFE_BOX_SPACING * 3 + (LIFE_BOX_SPACING * i), LIFE_BOX_SIZE - 5, LIFE_BOX_SIZE, LIFE_BOX_SIZE);
		}
	}

	private void drawWaitToNewGame(GameFrameBuffer aGameFrameBuffer) {
		Graphics g = aGameFrameBuffer.graphics();
		String text = "The next game will start soon";

		int textWidth = g.getFontMetrics().stringWidth(text);
		g.drawString(text, aGameFrameBuffer.getWidth() / 2 - textWidth / 2, aGameFrameBuffer.getHeight() / 2);
	}
}
