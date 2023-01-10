import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyEvent;

public class EndGameState extends GameState {
	private final int SPACING = 20;
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
		String title = "GAME OVER";
		String message = "PRESS 1 TO RETURN TO MAIN MENU OR ESC TO EXIT";
		int textWidth1 = g.getFontMetrics().stringWidth(title);
		int textWidth2 = g.getFontMetrics().stringWidth(message);
		g.setColor(Color.white);
		g.drawString(title, (aGameFrameBuffer.getWidth() - textWidth1)/ 2,
				aGameFrameBuffer.getHeight() / 2 - SPACING);
		g.drawString(message, (aGameFrameBuffer.getWidth() - textWidth2)/ 2,
				aGameFrameBuffer.getHeight() / 2 + SPACING);
	}
}
