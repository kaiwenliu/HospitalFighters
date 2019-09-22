import java.awt.Image;
import java.awt.Rectangle;

public class Stand extends MovingImage{
	
	private Rectangle bound;
	private double xMovement, yMovement;
	private double initialX, initialY;
	private int xDir, yDir;
	
	public Stand(int x, int y, Image stand, double xMovement, double yMovement) {
		super(stand, x, y, 100, 100);
		bound = new Rectangle(x, y, 100, 100);
		this.xMovement = xMovement;
        this.yMovement = yMovement;
        
        initialX = xMovement;
        initialY = yMovement + 20;
        
        if (xMovement < 0) {
        	xDir = -1;
        }
        if (xMovement >= 0) {
        	xDir = 1;
        }
        
        if (yMovement < 0) {
        	yDir = -1;
        }
        if (yMovement > 0) {
        	yDir = 1;
        }
        else {
        	yDir = 0;
        }
	}
	
	public boolean update() {
        bound.setLocation((int) (x + xMovement), (int) (y + yMovement/5));
        moveByAmount((int) xMovement, (int) yMovement/5);
        
        return (xMovement > initialX + 20 && yMovement > initialY + 20);
    }
	
	public Rectangle getBounds() {
		return bound;
	}
	
	public void forward() {
        if (Math.abs(xMovement) <= initialX + 20)
            xMovement += xDir*4;
    }
	
	public void upward() {
		if (Math.abs(yMovement) <= initialY + 20) 
			yMovement += yDir*4;
	}
	
	public int getXPower() {
		return (int) xMovement;
	}
	
	public int getYPower() {
		return (int) yMovement;
	}
	
}
