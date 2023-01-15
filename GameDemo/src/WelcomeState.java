import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyEvent;

public class WelcomeState extends GameState {
	private boolean active;
	private int chosenLevel;

	private final int DEFAULT_SPACING = 20;
	private final String WELCOME_TEXT = "WELCOME TO SNAKE GAME";
	private final String LEVEL_TEXT = "CHOOSE LEVEL:";
	private final String LEVEL_1_TEXT = "1 - EASY";
	private final String LEVEL_2_TEXT = "2 - MEDIUM";
	private final String LEVEL_3_TEXT = "3 - HARD";

	@Override
	public void enter(Object memento) {
		active = true;
	}

	@Override
	public Object memento() {
		int lives = 3;
		return new GameStateInput(chosenLevel, lives);
	}
	
	@Override
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

	@Override
	public boolean isActive() { return active; }

	@Override
	public String next() { return "Play"; }
	
	@Override
	public void render(GameFrameBuffer aGameFrameBuffer) {
		Graphics g = aGameFrameBuffer.graphics();

		
		g.setColor(Color.white);
		// TODO: upgrade to using constants
		int textWidth1 = g.getFontMetrics().stringWidth(WELCOME_TEXT);
		g.drawString(WELCOME_TEXT, (aGameFrameBuffer.getWidth()-textWidth1) / 2,
				aGameFrameBuffer.getHeight() / 2 - (DEFAULT_SPACING * 3));
		
		int textWidth2 = g.getFontMetrics().stringWidth(LEVEL_TEXT);
		g.drawString(LEVEL_TEXT, (aGameFrameBuffer.getWidth()-textWidth2) / 2,
				aGameFrameBuffer.getHeight() / 2 - DEFAULT_SPACING);
		
		int textWidth3 = g.getFontMetrics().stringWidth(LEVEL_1_TEXT);
		g.drawString(LEVEL_1_TEXT, (aGameFrameBuffer.getWidth()-textWidth3) / 2, aGameFrameBuffer.getHeight() / 2);
		
		int textWidth4 = g.getFontMetrics().stringWidth(LEVEL_2_TEXT);
		g.drawString(LEVEL_2_TEXT, (aGameFrameBuffer.getWidth()-textWidth4) / 2,
				aGameFrameBuffer.getHeight() / 2 + DEFAULT_SPACING);
		
		int textWidth5 = g.getFontMetrics().stringWidth(LEVEL_3_TEXT);
		g.drawString(LEVEL_3_TEXT, (aGameFrameBuffer.getWidth()-textWidth5) / 2,
				aGameFrameBuffer.getHeight() / 2 + (DEFAULT_SPACING * 2));
	}
}
