import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyEvent;

public class EndGameState extends GameState {
	boolean active;
	
	public void enter(Object memento) {
		active = true;
	}
	
	public void processKeyReleased(int aKeyCode) {
		if (aKeyCode == KeyEvent.VK_ESCAPE)
			System.exit(0);
		if (aKeyCode == KeyEvent.VK_1 || aKeyCode == KeyEvent.VK_NUMPAD1)
			active = false;
	}
	
	public boolean isActive() { return active; }
	
	public String next() {
		return "Welcome";
	}

	public void render(GameFrameBuffer aGameFrameBuffer) {
		Graphics g = aGameFrameBuffer.graphics();
		String text1 = "GAME OVER";
		String text2 = "PRESS 1 TO RETURN TO MAIN MENU OR ESC TO EXIT";
		int textWidth1 = g.getFontMetrics().stringWidth(text1);
		int textWidth2 = g.getFontMetrics().stringWidth(text2);
		g.setColor(Color.white);
		g.drawString(text1, (aGameFrameBuffer.getWidth() - textWidth1)/2, aGameFrameBuffer.getHeight()/2 - 20);
		g.drawString(text2, (aGameFrameBuffer.getWidth() - textWidth2)/2, aGameFrameBuffer.getHeight()/2 + 20);
	}
}
