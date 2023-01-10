

public class GameStateInput {
	
	private int level;
	private int lives;
	
	public GameStateInput(int level, int lives) {
		this.level = level;
		this.lives = lives;
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
	
	

}
