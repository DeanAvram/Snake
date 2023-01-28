import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyEvent;

public class PlayState extends GameState {
	private Snake snake;
	private Apple apple;
	private boolean isActive;
	private int level;
	private int lives;
	private int highScore;
	private int score;
	private int lastKeyPressed;
	private int screenWidth;
	private int screenHeight;

	private final int DEFAULT_SPACING = 20;
	private final int DEFALUT_SIZE = 12;
	private final int LIVES_BASE_POSITION = 295;

	public PlayState() {}

	@Override
	public Object memento() {
		return new EndStateInput(highScore);
	}
	
	@Override
	public void enter(Object memento) {
		if (!(memento instanceof GameStateInput))
			throw new RuntimeException("Invalid enter input");

		GameStateInput input = (GameStateInput)memento;
		level = input.getLevel();
		lives = input.getLives();
		highScore = input.getHighScore();
		screenWidth = input.getScreeenWidth();
		screenHeight = input.getScreenHeight();
		isActive = lives > 0;
		score = 0;
		lastKeyPressed = 0;
		snake = new Snake(screenWidth, screenHeight);
		apple = new Apple(screenWidth, screenHeight);
	}

	@Override
	public void processKeyPressed(int aKeyCode) {}

	@Override
	public void processKeyReleased(int aKeyCode) {
		if (aKeyCode == KeyEvent.VK_ESCAPE)
			isActive = false;

		if (aKeyCode == KeyEvent.VK_RIGHT && lastKeyPressed == 0)
			return;

		Point newHead = new Point(snake.getSnake().getFirst());
		if (aKeyCode == KeyEvent.VK_LEFT && lastKeyPressed != KeyEvent.VK_RIGHT) {
			newHead.setX(newHead.getX() - snake.getSnakeSpacing());
			lastKeyPressed = aKeyCode;
		}
		else if (aKeyCode == KeyEvent.VK_RIGHT && lastKeyPressed != KeyEvent.VK_LEFT) {
			newHead.setX(newHead.getX() + snake.getSnakeSpacing());
			lastKeyPressed = aKeyCode;
		}
		else if (aKeyCode == KeyEvent.VK_UP && lastKeyPressed != KeyEvent.VK_DOWN) {
			newHead.setY(newHead.getY() - snake.getSnakeSpacing());
			lastKeyPressed = aKeyCode;
		}
		else if (aKeyCode == KeyEvent.VK_DOWN && lastKeyPressed != KeyEvent.VK_UP) {
			newHead.setY(newHead.getY() + snake.getSnakeSpacing());
			lastKeyPressed = aKeyCode;
		}
	}
	
	@Override
	public void update(long deltaTime) {
		if (this.snake.isSnakeDead(screenWidth, screenHeight)) {
			handleHighScore();
			handleSnakeDead();
		}

		if (this.snake.isSnakeAteApple(this.apple, lastKeyPressed)) {
			score++;
			if (score > highScore)
				highScore = score;
			do {
				apple = new Apple(screenWidth, screenHeight); // generate new apple and check if it is not on the snake body
			} while(this.snake.getSnake().contains(apple.getLocation()));
		}

		float deltaPos = level + deltaTime * 0.2f;
		Point snakeHead = this.snake.getSnake().getFirst();
		int newX = snakeHead.getX(), newY = snakeHead.getY();
		switch (lastKeyPressed) {
			case(KeyEvent.VK_LEFT) -> newX = (int)(snakeHead.getX() - deltaPos);
			case(KeyEvent.VK_RIGHT) -> newX = (int)(snakeHead.getX() + deltaPos);
			case(KeyEvent.VK_UP) -> newY = (int)(snakeHead.getY() - deltaPos);
			case(KeyEvent.VK_DOWN) -> newY = (int)(snakeHead.getY() + deltaPos);
			default -> { return; }
		}
		this.snake.move(new Point(newX, newY));
	}
	
	@Override
	public boolean isActive() { return isActive; }
	
	@Override
	public String next() { return "End"; }

	@Override
	public void render(GameFrameBuffer aGameFrameBuffer) {
		Graphics g = aGameFrameBuffer.graphics();
		drawLivesMeter(aGameFrameBuffer);
		drawScore(aGameFrameBuffer, score);
		drawSnakeSprite(g);
		drawAppleSprite(g);
		drawHighScore(aGameFrameBuffer, highScore);
	}

	private void drawAppleSprite(Graphics g) {
		int apple_side_length = this.apple.getAppleSize();
		g.setColor(Color.red);
		g.fillOval(apple.getLocation().getX(), apple.getLocation().getY(), apple_side_length, apple_side_length);
	}

	private void drawSnakeSprite(Graphics g) {
		int snake_link_size = this.snake.getSnakeSpacing();
		g.setColor(Color.white);
		for (Point p: this.snake.getSnake())
			g.fillOval(p.getX(), p.getY(), snake_link_size, snake_link_size);
	}

	private void drawLivesMeter(GameFrameBuffer aGameFrameBuffer) {
		Graphics g = aGameFrameBuffer.graphics();
		g.setColor(Color.white);
		String text = "LIVES";
		int textWidth = g.getFontMetrics().stringWidth(text);
		g.drawString(text, (aGameFrameBuffer.getWidth() - textWidth) / 2, DEFAULT_SPACING);
		g.setColor(Color.RED);
		int y = DEFAULT_SPACING + 10, width = DEFALUT_SIZE, height = DEFALUT_SIZE;

		for (int i = 0; i < lives; i++) {
			int x = LIVES_BASE_POSITION + (DEFAULT_SPACING * i);
			int[] triangleX = {
		            x - 2 * width / 18,
		            x + width + 2 * width / 18,
		            (x - 2 * width / 18 + x + width + 2 * width / 18) / 2
			};
		    int[] triangleY = {
		            y + height - 2 * height / 3,
		            y + height - 2 * height / 3,
		            y + height
			};
		    g.fillOval(
		            x - width / 12,
		            y,
		            width / 2 + width / 6,
		            height / 2);
		    g.fillOval(
		            x + width / 2 - width / 12,
		            y,
		            width / 2 + width / 6,
		            height / 2);
		    g.fillPolygon(triangleX, triangleY, triangleX.length);
		}
	}

	private void drawScore(GameFrameBuffer aGameFrameBuffer, int score) {
		Graphics g = aGameFrameBuffer.graphics();

		g.setColor(Color.white);
		String text = "SCORE: " + score;
		int textWidth = g.getFontMetrics().stringWidth(text);
		g.drawString(text, (aGameFrameBuffer.getWidth()-textWidth) / 2, DEFAULT_SPACING * 3);
	}
	
	private void drawHighScore(GameFrameBuffer aGameFrameBuffer, int score) {
		Graphics g = aGameFrameBuffer.graphics();

		g.setColor(Color.white);
		String text = "HIGH SCORE: " + score;
		int textWidth = g.getFontMetrics().stringWidth(text);
		g.drawString(text, (aGameFrameBuffer.getWidth()-textWidth) / 2, DEFAULT_SPACING * 4);
	}

	private void handleSnakeDead() { enter(new GameStateInput(level, --lives, highScore, screenWidth, screenHeight));	}
	
	private void handleHighScore() {
		if (score > highScore)
			highScore = score;
	}
}
