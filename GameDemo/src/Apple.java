
import java.util.Random;

public class Apple {
	
	private int screenWidth;
	private int screenHeight;
	
	private final int  X_MIN = 20;
	private final int Y_MIN = 80;
	private final int APPLE_SIZE = 10;
	private Point location;

	public Apple(int screenWidth, int screenHeight) {
        Random rand = new Random();
        this.screenWidth = screenWidth;
        this.screenHeight = screenHeight;
        int X_MAX = this.screenWidth - (2 * APPLE_SIZE);
        int Y_MAX = this.screenHeight - (2 * APPLE_SIZE);
        int x = (rand.nextInt((X_MAX - X_MIN) + 1) + X_MIN) / APPLE_SIZE * APPLE_SIZE;
        int y = (rand.nextInt((Y_MAX - Y_MIN) + 1) + Y_MIN) / APPLE_SIZE * APPLE_SIZE;
		this.location = new Point(x, y);
	}

	public Point getLocation() {
		return location;
	}

	public void setLocation(Point location) {
		this.location = location;
	}

	public int getAppleSize() {
		return APPLE_SIZE;
	}
}
