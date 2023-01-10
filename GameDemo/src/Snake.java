package main.sprites;

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
}
