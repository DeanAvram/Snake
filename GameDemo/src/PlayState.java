package main;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyEvent;

import main.sprites.Apple;
import main.sprites.Snake;

public class PlayState extends GameState {
	boolean active;
	Snake snake;
	Apple apple;
	int lastPressed = 0;
	float deltaTimeAverage;
	int level;
	GameStateInput input;
	int lives;
	int score;

	public PlayState() {}
	
	public void enter(Object memento) {
		active = true;
		input = (GameStateInput)memento;
		level = input.getLevel();
		lives = input.getLives();
		score = 0;
		if (lives == 0)
			active = false;
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
		if (isSnakeHitWall())
			snakeHitWall();
		if (isSnakeAteApple()) {
			snakeAteApple();
			score++;
			apple = new Apple();
		}

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
		/*
				g.setColor(Color.white);
		String text = "LIVES";
		int textWidth = g.getFontMetrics().stringWidth(text);
		g.drawString(text, (aGameFrameBuffer.getWidth()-textWidth)/2, 20);
		g.setColor(Color.RED);
		//int x = 100;
		int y = 30;
		int width = 12;
		int height = 12;
		for (int i = 0; i < lives; i++) {
			int x = 295 + (20 * i);
			int[] triangleX = {
		            x - 2*width/18,
		            x + width + 2*width/18,
		            (x - 2*width/18 + x + width + 2*width/18)/2};
		    int[] triangleY = {
		            y + height - 2*height/3,
		            y + height - 2*height/3,
		            y + height };
		    g.fillOval(
		            x - width/12,
		            y,
		            width/2 + width/6,
		            height/2);
		    g.fillOval(
		            x + width/2 - width/12,
		            y,
		            width/2 + width/6,
		            height/2);
		    g.fillPolygon(triangleX, triangleY, triangleX.length);
		}
		 */
	}

	private void drawScore(GameFrameBuffer aGameFrameBuffer, int score) {
		Graphics g = aGameFrameBuffer.graphics();
		g.setColor(Color.white);
		String text = "SCORE: " + score;
		int textWidth = g.getFontMetrics().stringWidth(text);
		g.drawString(text, (aGameFrameBuffer.getWidth()-textWidth) / 2, 60);
	}

	private void drawWaitToNewGame(GameFrameBuffer aGameFrameBuffer) {
		Graphics g = aGameFrameBuffer.graphics();
		String text = "The next game will start soon";

		int textWidth = g.getFontMetrics().stringWidth(text);
		g.drawString(text, aGameFrameBuffer.getWidth() / 2 - textWidth / 2, aGameFrameBuffer.getHeight() / 2);
	}

	public boolean isSnakeHitWall() {
		return snake.getSnake().getFirst().getX() <= 0 || snake.getSnake().getFirst().getX() >= 640
				|| snake.getSnake().getFirst().getY() >= 480 || snake.getSnake().getFirst().getY() <= 0;
	}

	public void snakeHitWall() {
		lives--;
		input = new GameStateInput(level, lives);
		enter(input);
	}

	public boolean isSnakeAteApple() {
		return snake.getSnake().getFirst().getX() == apple.getLocation().getX() &&
				snake.getSnake().getFirst().getY() == apple.getLocation().getY();
	}

	public void snakeAteApple() {
		int lastX = snake.getSnake().getLast().getX();
		int lastY = snake.getSnake().getLast().getY();
		if (lastPressed == KeyEvent.VK_UP) {
			addDown(lastX, lastY);
		}
		else if (lastPressed == KeyEvent.VK_DOWN) {
			addUp(lastX, lastY);
		}
		else if (lastPressed == KeyEvent.VK_LEFT) {
			addRight(lastX, lastY);
		}
		else if (lastPressed == KeyEvent.VK_RIGHT) {
			addLeft(lastX, lastY);
		}

	}

	public void addDown(int lastX, int lastY) {
		Point newLast = new Point(lastX, lastY + snake.getSnakeSpacing());
		snake.getSnake().add(newLast);
	}

	public void addUp(int lastX, int lastY) {
		Point newLast = new Point(lastX, lastY - snake.getSnakeSpacing());
		snake.getSnake().add(newLast);
	}

	public void addLeft(int lastX, int lastY) {
		Point newLast = new Point(lastX - snake.getSnakeSpacing(), lastY);
		snake.getSnake().add(newLast);
	}

	public void addRight(int lastX, int lastY) {
		Point newLast = new Point(lastX + snake.getSnakeSpacing(), lastY);
		snake.getSnake().add(newLast);
	}

}
