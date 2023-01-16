public class GameStateInput {
	private int level;
	private int lives;
	private int highScore;
	
	public GameStateInput(int level, int lives, int highScore) {
		this.level = level;
		this.lives = lives;
		this.highScore = highScore;
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
	
	
}
