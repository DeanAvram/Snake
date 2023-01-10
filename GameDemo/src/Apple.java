
import java.util.Random;

public class Apple {
	private final int  X_MIN = 20;
	private final int X_MAX = 620;
	private final int Y_MIN = 50;
	private final int Y_MAX = 420;
	private final int APPLE_SIZE = 10;
	private Point location;

	public Apple() {
        Random rand = new Random();
        int scale = 10;
        int x = (rand.nextInt((X_MAX - X_MIN) + 1) + X_MIN) / scale * scale;
        int y = (rand.nextInt((Y_MAX - Y_MIN) + 1) + Y_MIN) / scale * scale;
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
