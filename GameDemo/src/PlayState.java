import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyEvent;

public class PlayState extends GameState {
	private GameStateInput input;
	private Snake snake;
	private Apple apple;
	private boolean isActive;
	private int level;
	private int lives;
	private int score;
	private int lastKeyPressed;
	private float averageDeltaTime;

	private final int DEFAULT_SPACING = 20;
	private final int DEFALUT_SIZE = 12;
	private final int LIVES_BASE_POSITION = 295;

	public PlayState() {}
	
	public void enter(Object memento) {
		input = (GameStateInput)memento; // is this safe casting?
		level = input.getLevel();
		lives = input.getLives();
		isActive = lives > 0;
		score = 0;
		lastKeyPressed = 0;
		averageDeltaTime = 0;
		snake = new Snake();
		apple = new Apple();
	}

	public void processKeyPressed(int aKeyCode) {}

	public void processKeyReleased(int aKeyCode) {
		Point newFirst = new Point(snake.getSnake().getFirst());
		if (aKeyCode == KeyEvent.VK_ESCAPE)
			System.exit(0);

		if (aKeyCode == KeyEvent.VK_RIGHT && lastKeyPressed == 0)
			return;
		boolean update = false;
		if (aKeyCode == KeyEvent.VK_LEFT && lastKeyPressed != KeyEvent.VK_RIGHT) {
			newFirst.setX(newFirst.getX() - snake.getSnakeSpacing());
			update = true;
		}
		else if (aKeyCode == KeyEvent.VK_RIGHT && lastKeyPressed != KeyEvent.VK_LEFT) {
			newFirst.setX(newFirst.getX() + snake.getSnakeSpacing());
			update = true;
		}
		else if (aKeyCode == KeyEvent.VK_UP && lastKeyPressed != KeyEvent.VK_DOWN) {
			newFirst.setY(newFirst.getY() - snake.getSnakeSpacing());
			update = true;
		}
		else if (aKeyCode == KeyEvent.VK_DOWN && lastKeyPressed != KeyEvent.VK_UP) {
			newFirst.setY(newFirst.getY() + snake.getSnakeSpacing());
			update = true;
		}
		if (update) {
			lastKeyPressed = aKeyCode;
			snake.move(newFirst);
		}
	}
	
	public void update(long deltaTime) {
		if (this.snake.isSnakeDead(640, 480)) // TODO: see how we can get screen size dynamically.
			handleSnakeDead();

		if (this.snake.isSnakeAteApple(this.apple, lastKeyPressed)) {
			score++;
			do {
				apple = new Apple(); // generate new apple and check if it is not on the snake body
			} while(this.snake.getSnake().contains(apple.getLocation()));
		}

		averageDeltaTime = averageDeltaTime * level * 0.9f + 0.001f * deltaTime;
		float deltaPos = this.snake.getSnakeSpacing() + averageDeltaTime;
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
	
	public boolean isActive() { return isActive; }
	
	public String next() { return "End"; }

	public void render(GameFrameBuffer aGameFrameBuffer) {
		if (lives != 3)
			drawWaitToNewGame(aGameFrameBuffer);

		Graphics g = aGameFrameBuffer.graphics();
		drawLivesMeter(aGameFrameBuffer);
		drawScore(aGameFrameBuffer, score);
		drawSnakeSprite(g);
		drawAppleSprite(g);
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

	private void drawWaitToNewGame(GameFrameBuffer aGameFrameBuffer) {
		Graphics g = aGameFrameBuffer.graphics();

		String text = "The next game will start soon";
		int textWidth = g.getFontMetrics().stringWidth(text);
		g.drawString(text, aGameFrameBuffer.getWidth() / 2 - textWidth / 2, aGameFrameBuffer.getHeight() / 2);
	}

	private void handleSnakeDead() {
		lives--;
		input = new GameStateInput(level, lives);
		enter(input);
	}
}
