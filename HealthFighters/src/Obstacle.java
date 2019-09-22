import java.awt.Graphics;
import java.awt.Rectangle;

import javax.swing.JPanel;

public class Obstacle {
	
	private int x, y, width, height;
	private Rectangle bound;
	
	public Obstacle(int x, int y, int width, int height) {
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		bound = new Rectangle(x, y, width, 1);
	}
	
	public void draw(Graphics g) {
		g.fillRect(x, y, width, height);
	}
	
	public Rectangle getBounds() {
		return bound;
	}
}
