public class GameStateInput {
	private int level;
	private int lives;
	private int highScore;
	private int screeenWidth;
	private int screenHeight;
	
	public GameStateInput(int level, int lives, int highScore, int screeenWidth, int screenHeight) {
		this.level = level;
		this.lives = lives;
		this.highScore = highScore;
		this.screeenWidth = screeenWidth;
		this.screenHeight = screenHeight;
	}

	public int getLevel() {
		return level;
	}

	public void setLevel(int level) {
		this.level = level;
	}

	public int getLives() {
		return lives;
	}

	public void setLives(int lives) {
		this.lives = lives;
	}

	public int getHighScore() {
		return highScore;
	}

	public void setHighScore(int highScore) {
		this.highScore = highScore;
	}
	
	public int getScreeenWidth() {
		return screeenWidth;
	}

	public void setScreeenWidth(int screeenWidth) {
		this.screeenWidth = screeenWidth;
	}

	public int getScreenHeight() {
		return screenHeight;
	}

	public void setScreenHeight(int screenHeight) {
		this.screenHeight = screenHeight;
	}
}
