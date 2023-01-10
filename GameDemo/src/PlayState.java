import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.util.Iterator;

import gameObjects.Apple;
import gameObjects.Point;
import gameObjects.Snake;


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
	
	String message;
	
	public PlayState() {
	}
	
	public void enter(Object memento) {
		active = true;
		//Level l = (Level)memento;
		//level = l.getLevel();
		input = (GameStateInput)memento;
		level = input.getLevel();
		lives = input.getLives();
		score = 0;
		if (lives == 0)
			active = false;
		deltaTimeAverage = 0;
		snake = new Snake();
		apple = new Apple();
		System.out.println(snake.getSnake().getFirst().getX() + ", " + snake.getSnake().getFirst().getY());
		//System.out.println("Apple x:" + apple.getLocation().getX() + " Apple y: " + apple.getLocation().getY());
		/*snake = new LinkedList<>();
		int x = 10 + (int)(Math.random() * 500);
		int y = 10 + (int)(Math.random() * 400);
		Point p1 = new Point(x, y);
		Point p2 = new Point(x + snakeSpacing, y);
		Point p3 = new Point(x + (2 * snakeSpacing), y);
		snake.add(p1);
		snake.add(p2);
		snake.add(p3);*/
		//snake.addAll(Collections.list({p1, p2, p3}));
		
		//snakeX = 10 + (int)(Math.random() * 500);
		//snakeY = 10 + (int)(Math.random() * 400);
		//appleX = 10 + (int)(Math.random() * 500);
		//appleY = 10 + (int)(Math.random() * 400);
		//remainingLives = lives;
		//System.out.println(remainingLives);
	}
	
	
	public void processKeyPressed(int aKeyCode) {
		/*if (aKeyCode == KeyEvent.VK_LEFT) 
			snakeX -= 5;
		else if (aKeyCode == KeyEvent.VK_RIGHT) 
			snakeX += 5;
		else if (aKeyCode == KeyEvent.VK_UP) 
			snakeY -=  5;
		else if (aKeyCode == KeyEvent.VK_DOWN) 
			snakeY += 5;
		else
			return;*/
		//lastPressed = aKeyCode;
	}

	public void processKeyReleased(int aKeyCode) {
		Point newFirst = new Point(snake.getSnake().getFirst());
		if (aKeyCode == KeyEvent.VK_ESCAPE)
			System.exit(0);
		
		if (aKeyCode == KeyEvent.VK_LEFT) {
			newFirst.setX(newFirst.getX() - snake.getSnakeSpacing());
			//snake.snakeMoved(newFirst);
			//snake.add(0, newFirst);
			//snake.removeLast();
		}
		else if (aKeyCode == KeyEvent.VK_RIGHT) {
			newFirst.setX(newFirst.getX() + snake.getSnakeSpacing());
			//snake.snakeMoved(newFirst);
			//snake.add(0, newFirst);
			//snake.removeLast();
		}
		else if (aKeyCode == KeyEvent.VK_UP) {
			newFirst.setY(newFirst.getY() - snake.getSnakeSpacing());
			//snake.snakeMoved(newFirst);
			//snake.add(0, newFirst);
			//snake.removeLast();
		}
		else if (aKeyCode == KeyEvent.VK_DOWN) {
			newFirst.setY(newFirst.getY() + snake.getSnakeSpacing());
			//snake.snakeMoved(newFirst);
			//snake.add(0, newFirst);
			//snake.removeLast();
		}
		snake.snakeMoved(newFirst);
		//System.out.println("First: " + snake.getSnake().getFirst().getX() + ", " + snake.getSnake().getFirst().getY());
		//System.out.println("Last: " + snake.getSnake().getLast().getX() + ", " + snake.getSnake().getLast().getY());
		lastPressed = aKeyCode;
	}
	
	public void update(long deltaTime) {
		//int speed = 1;
		 	if (isSnakeHitWall())
		 		snakeHitWall();
		 	if (isSnakeAteApple()) {
		 		snakeAteApple();
		 		score++;
		 		apple = new Apple();
		 	}
			//return;
			//System.out.println(lives);
			//return;
			/*if (lives == 0)
				active = false;
			else {
				input = new GameStateInput(level, lives);
			}*/
		
		
		//if 
		
		deltaTimeAverage = deltaTimeAverage * level * 0.9f + 0.001f*(float)deltaTime;
		Point newFirst = new Point(snake.getSnake().getFirst());
		Iterator<Point> it = snake.getSnake().iterator();
		if (lastPressed == KeyEvent.VK_LEFT) {
			//int newX = (int)(newFirst.getX() + deltaTime * 0.001f * level); 
			//int newX = newFirst.getX() - 2;
			//newFirst.setX(newX);
			//snake.snakeMoved(newFirst);
		
			//newFirst.setX((newX));
			//snake.add(0, newFirst);
			//snake.removeLast();
			 /*while(it.hasNext()){
				 Point p = it.next();
				 //int newX = (int)(newFirst.getX() + deltaTime * 0.001f * level); 
				 int newX = newFirst.getX() - 1;
				 p.setX(newX);
				 //p.setX(p.getX() - (int)(0.1f*deltaTime * level));
			}*/
		}
			/*int x = newFirst.getX() - level - snakeSize;
			x = x / 2;
			//x += snakeSpacing;
			newFirst.setX(x);
			snake.add(0, newFirst);
			snake.removeLast();*/
		/*	
		}
		if (lastPressed == KeyEvent.VK_RIGHT) {
			while(it.hasNext()){
				 Point p = it.next();
				 p.setX(p.getX() + (int)(0.1f*deltaTime * level));
			 }
		}
		if (lastPressed == KeyEvent.VK_UP){
			while(it.hasNext()){
				 Point p = it.next();
				 p.setY(p.getY() - (int)(0.1f*deltaTime * level));
			 }
		}
		if (lastPressed == KeyEvent.VK_DOWN) {
			while(it.hasNext()){
				 Point p = it.next();
				 p.setY(p.getY() + (int)(0.1f*deltaTime * level));
			 }
		}
			
		if (snakeX >= 640 || snakeX <= 0 || snakeY <= 0 || snakeY >= 480) {
			//System.out.println(lives);
			//return;
			//Level l = new Level(level);
			//enter(l);
			//active = false;
		}
		
		//if (lives == 0)
		//	active = false;
		*/
	}
	
	public boolean isActive() { return active; }
	
	public String next() {
		return "End";
	}
	
	
	
	public void render(GameFrameBuffer aGameFrameBuffer) {
		Graphics g = aGameFrameBuffer.graphics();
		
		//if (lives != 3)
		//	drawWaitToNewGame(g);
		
		drawLives(aGameFrameBuffer, g, lives);
		drawScore(aGameFrameBuffer, g, score);
		
		//paintComponent(g);
		g.setColor(Color.white);
		Iterator<Point> it = snake.getSnake().iterator();
		 while(it.hasNext()){
			 Point p = it.next();
			 g.fillOval(p.getX(), p.getY(), snake.getSnakeSpacing(), snake.getSnakeSpacing());
		 }
		
		g.setColor(Color.red);
		g.fillOval(apple.getLocation().getX(), apple.getLocation().getY(), apple.getAppleSize(), apple.getAppleSize());
		/*for (int i = 0; i < snakeSize; i++){
			if (lastPressed == KeyEvent.VK_UP || lastPressed == KeyEvent.VK_DOWN) {
				if (i == 0) {
					g.setColor(Color.BLUE);
				}
				else {
					g.setColor(Color.WHITE);
				}
				g.drawOval((int)snakeX, (int)(snakeY + (i * 10)), 10, 10);
				g.fillOval((int)snakeX, (int)(snakeY + (i * 10)), 10, 10);

			}
			else {
				g.drawOval((int)(snakeX + (i * 10)), (int)(snakeY), 10, 10);
				g.fillOval((int)(snakeX + (i * 10)), (int)(snakeY), 10, 10);
			}
				
		}
		
	*/

	}
	
	public void drawLives(GameFrameBuffer aGameFrameBuffer, Graphics g, int lives) {
        //super.paintComponent(g);
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
    }
	
	public void drawScore(GameFrameBuffer aGameFrameBuffer, Graphics g, int score) {
		g.setColor(Color.white);
		String text = "SCORE: " + String.valueOf(score);
		int textWidth = g.getFontMetrics().stringWidth(text);
		g.drawString(text, (aGameFrameBuffer.getWidth()-textWidth)/2, 60);
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
