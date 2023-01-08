import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyEvent;

public class WelcomeState extends GameState {

	boolean active;
	int chosenLevel;
	//Level level;
	
	public void enter(Object memento) {
		active = true;
	}
	
	public Level memento() {
		return new Level(chosenLevel);
	}
	
	
	public void processKeyReleased(int aKeyCode) {
		if (aKeyCode == KeyEvent.VK_ESCAPE)
			System.exit(0);
		if (aKeyCode == KeyEvent.VK_1 || aKeyCode == KeyEvent.VK_NUMPAD1)
			chosenLevel = 1;
		if (aKeyCode == KeyEvent.VK_2 || aKeyCode == KeyEvent.VK_NUMPAD2)
			chosenLevel = 2;
		if (aKeyCode == KeyEvent.VK_3 || aKeyCode == KeyEvent.VK_NUMPAD3)
			chosenLevel = 3;
		active = false;
	}
	
	public boolean isActive() { return active; }
	
	public String next() {
		//System.out.println(memento().getLevel());
		return "Play";
	}
	

	public void render(GameFrameBuffer aGameFrameBuffer) {
		Graphics g = aGameFrameBuffer.graphics();
		
		/*String text = "PRESS ANY KEY TO PLAY";
		int textWidth = g.getFontMetrics().stringWidth(text);
		g.setColor(Color.white);
		g.drawString(text, (aGameFrameBuffer.getWidth()-textWidth)/2, aGameFrameBuffer.getHeight()/2);*/
		
		g.setColor(Color.white);
		
		String text1 = "WELCOME TO SNAKE GAME";
		int textWidth1 = g.getFontMetrics().stringWidth(text1);
		g.drawString(text1, (aGameFrameBuffer.getWidth()-textWidth1)/2, aGameFrameBuffer.getHeight()/2 - 60);
		
		String text2 = "CHOOSE LEVEL:";
		int textWidth2 = g.getFontMetrics().stringWidth(text2);
		g.drawString(text2, (aGameFrameBuffer.getWidth()-textWidth2)/2, aGameFrameBuffer.getHeight()/2 - 20);
		
		String text3 = "1 - EASY";
		int textWidth3 = g.getFontMetrics().stringWidth(text3);
		g.drawString(text3, (aGameFrameBuffer.getWidth()-textWidth3)/2, aGameFrameBuffer.getHeight()/2);
		
		String text4 = "2 - MEDIUM";
		int textWidth4 = g.getFontMetrics().stringWidth(text4);
		g.drawString(text4, (aGameFrameBuffer.getWidth()-textWidth4)/2, aGameFrameBuffer.getHeight()/2 + 20);
		
		String text5 = "3 - HARD";
		int textWidth5 = g.getFontMetrics().stringWidth(text3);
		g.drawString(text5, (aGameFrameBuffer.getWidth()-textWidth5)/2, aGameFrameBuffer.getHeight()/2 + 40);

	}
}
