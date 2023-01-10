package main.sprites;

import main.Point;

import java.util.Random;

public class Apple {
	private Point location;
	private int appleSize;
	
	public Apple() {
        Random rand = new Random();
		int Xmin = 20;
        int Xmax = 620;
        int Ymin = 50;
        int Ymax = 420;
        int scale = 10;
        int x = (rand.nextInt((Xmax - Xmin) + 1) + Xmin) / scale * scale;
        int y = (rand.nextInt((Ymax - Ymin) + 1) + Ymin) / scale * scale;
		//int x = 10 + (int)(Math.random() * 500);
		//int y = 50 + (int)(Math.random() * 380);
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

	
	
	
	
	
}
