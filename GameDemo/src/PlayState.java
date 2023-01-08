import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.util.Iterator;
import java.util.LinkedList;

public class PlayState extends GameState {

	boolean active;
	Snake snake;
	Apple apple;
	int lastPressed = 0;
	float deltaTimeAverage;
	int level;
	int lives = 3;
	
	String message;
	
	public PlayState() {
	}
	
	public void enter(Object memento) {
		active = true;
		Level l = (Level)memento;
		level = l.getLevel();
		deltaTimeAverage = 0;
		snake = new Snake();
		apple = new Apple();
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
		lastPressed = aKeyCode;
	}
	
	public void update(long deltaTime) {
		//int speed = 1;
		deltaTimeAverage = deltaTimeAverage * level * 0.9f + 0.001f*(float)deltaTime;
		Point newFirst = new Point(snake.getSnake().getFirst());
		Iterator<Point> it = snake.getSnake().iterator();
		/*if (lastPressed == KeyEvent.VK_LEFT) {
			//int newX = (int)(newFirst.getX() + deltaTime * 0.001f * level); 
			//newFirst.setX((newX));
			//snake.add(0, newFirst);
			//snake.removeLast();
			 while(it.hasNext()){
				 Point p = it.next();
				 int newX = (int)(newFirst.getX() + deltaTime * 0.001f * level); 
				 p.setX(newX);
				 //p.setX(p.getX() - (int)(0.1f*deltaTime * level));
			}
		}*/
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
	
	public void drawWaitToNewGame(Graphics g) {
		
		g.drawString("Thw next game will start soon", 100, 100);
		
	}

	public void render(GameFrameBuffer aGameFrameBuffer) {
		Graphics g = aGameFrameBuffer.graphics();
		
		//if (lives != 3)
		//	drawWaitToNewGame(g);
		
		//String text = "LIVES";
		//int textWidth3 = g.getFontMetrics().stringWidth(text);
		/*g.drawString("LIVES", 260, 20);
		g.setColor(Color.RED);
		for (int i = 0; i < lives; i++) {
			g.fillOval(300 + (20*i), 7, 15, 15);

		}*/

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

}
