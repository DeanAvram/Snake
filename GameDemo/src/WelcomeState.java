import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyEvent;

public class WelcomeState extends GameState {
	private boolean active;
	private int chosenLevel;
	private int highScore;

	private final int DEFAULT_SPACING = 20;
	private final String WELCOME_TEXT = "WELCOME TO SNAKE GAME";
	private final String LEVEL_TEXT = "CHOOSE LEVEL:";
	private final String LEVEL_1_TEXT = "1 - EASY";
	private final String LEVEL_2_TEXT = "2 - MEDIUM";
	private final String LEVEL_3_TEXT = "3 - HARD";
	private int screenWidth;
	private int screenHeight;

	@Override
	public void enter(Object memento) {
		active = true;
	}

	@Override
	public Object memento() {
		int lives = 3;
		FileController fc = new FileController();
		highScore = Integer.parseInt(fc.getHighScoreFromFile());
		return new GameStateInput(chosenLevel, lives, highScore, screenWidth, screenHeight);
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
		active = !(chosenLevel >= 1 && chosenLevel <= 3);
		//if choseLevel is not between 1 to 3 -> active need to be true and stay in this state
	}

	@Override
	public boolean isActive() { return active; }

	@Override
	public String next() { return "Play"; }

	@Override
	public void render(GameFrameBuffer aGameFrameBuffer) {
		Graphics g = aGameFrameBuffer.graphics();
		screenWidth = aGameFrameBuffer.getWidth();
		screenHeight = aGameFrameBuffer.getHeight();
		g.setColor(Color.white);
		
		draw_string(aGameFrameBuffer, g, WELCOME_TEXT, 2);
		draw_string(aGameFrameBuffer, g, LEVEL_TEXT, 1);
		draw_string(aGameFrameBuffer, g, LEVEL_1_TEXT, 0);
		draw_string(aGameFrameBuffer, g, LEVEL_2_TEXT, -1);
		draw_string(aGameFrameBuffer, g, LEVEL_3_TEXT, -2);
	}

	private void draw_string(GameFrameBuffer aGameFrameBuffer, Graphics g, String text, int index) {
		int textWidth = g.getFontMetrics().stringWidth(text);
		g.drawString(text, (aGameFrameBuffer.getWidth()-textWidth) / 2, aGameFrameBuffer.getHeight() / 2 - (index * DEFAULT_SPACING));
	}
}
