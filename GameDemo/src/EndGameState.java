import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.io.IOException;

public class EndGameState extends GameState {
	private final int SPACING = 20;
	private boolean active;

	@Override
	public void enter(Object memento) {
		active = true;
		if (!(memento instanceof EndStateInput))
			throw new RuntimeException("Invalid enter input");

		EndStateInput input = (EndStateInput)memento;
		FileController fc = new FileController();
		try {
			fc.saveHighScoreToFile(input.getHighScore());
		} catch (IOException e) {
			e.printStackTrace();
		}

	}
	
	@Override
	public void processKeyReleased(int aKeyCode) {
		if (aKeyCode == KeyEvent.VK_ESCAPE)
			System.exit(0);
		if (aKeyCode == KeyEvent.VK_1 || aKeyCode == KeyEvent.VK_NUMPAD1)
			active = false;
	}
	
	@Override
	public boolean isActive() { return active; }
	
	@Override
	public String next() {
		return "Welcome";
	}

	@Override
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
