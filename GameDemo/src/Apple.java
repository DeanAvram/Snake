public class Apple {
	private Point location;
	private int appleSize;
	
	public Apple() {
		int x = 10 + (int)(Math.random() * 500);
		int y = 10 + (int)(Math.random() * 400);
		this.location = new Point(x, y);
		this.appleSize = 10;
	}

	public Point getLocation() {
		return location;
	}

	public void setLocation(Point location) {
		this.location = location;
	}

	public int getAppleSize() {
		return appleSize;
	}

	public void setAppleSize(int appleSize) { this.appleSize = appleSize; }
}
