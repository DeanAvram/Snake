import java.awt.Color;
import java.awt.Graphics;
import java.awt.Polygon;
import java.awt.event.KeyEvent;

public class PlayState extends GameState {

	boolean active;
	float snakeX;
	float snakeY;
	float appleX;
	float appleY;
	int snakeSize = 3;
	int lastPressed = 0;
	float deltaTimeAverage;
	int level;
	
	String message;
	
	public PlayState() {
	}
	
	public void enter(Object memento) {
		active = true;
		Level l = (Level)memento;
		level = l.getLevel();
		deltaTimeAverage = 0;
		snakeX = 10 + (int)(Math.random() * 500);
		snakeY = 10 + (int)(Math.random() * 400);
		appleX = 10 + (int)(Math.random() * 500);
		appleY = 10 + (int)(Math.random() * 400);
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
		if (aKeyCode == KeyEvent.VK_ESCAPE)
			System.exit(0);
		
		if (aKeyCode == KeyEvent.VK_LEFT) 
			snakeX -= 5;
		else if (aKeyCode == KeyEvent.VK_RIGHT) 
			snakeX += 5;
		else if (aKeyCode == KeyEvent.VK_UP) 
			snakeY -=  5;
		else if (aKeyCode == KeyEvent.VK_DOWN) 
			snakeY += 5;
		lastPressed = aKeyCode;
	}
	
	public void update(long deltaTime) {
		deltaTimeAverage = deltaTimeAverage * level * 0.9f + 0.1f*(float)deltaTime;
		
		if (lastPressed == KeyEvent.VK_LEFT) 
			snakeX = snakeX - 0.1f*deltaTime * level;
		if (lastPressed == KeyEvent.VK_RIGHT) 
			snakeX = snakeX + 0.1f*deltaTime * level;
		if (lastPressed == KeyEvent.VK_UP) 
			snakeY = snakeY - 0.1f*deltaTime * level;
		if (lastPressed == KeyEvent.VK_DOWN) 
			snakeY = snakeY + 0.1f*deltaTime * level;
			
		if (snakeX >= 640 || snakeX <= 0 || snakeY <= 0 || snakeY >= 480) {
			active = false;
		}
	}
	
	public boolean isActive() { return active; }
	
	public String next() {
		return "End";
	}

	public void render(GameFrameBuffer aGameFrameBuffer) {
		Graphics g = aGameFrameBuffer.graphics();
		
		g.setColor(Color.white);
		for (int i = 0; i < snakeSize; i++){
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
		g.setColor(Color.red);
		g.fillOval((int)appleX, (int)appleY, 10, 10);
		g.drawOval((int)appleX , (int)appleY, 10, 10);
	

	}

}
