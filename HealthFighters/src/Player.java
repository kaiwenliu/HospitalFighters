import java.awt.Image;
import java.awt.Rectangle;

import javax.swing.ImageIcon;

/*
 * Class: Player
 */

public class Player extends MovingImage {

    // == FIELDS ==
    private int health;
    private double xMovement, yMovement;
    private boolean jumped;
    private Rectangle bound;
    private Image standImage;

    // == CONSTRUCTOR ==
    public Player(int xPos, int yPos, int health, String face, String stand) { //String voice
        super(Player.class.getResource("/assets/faces/" + face + ".png"), xPos, yPos, 100, 100);
        standImage = (new ImageIcon(Player.class.getResource("/assets/stands/" + stand + ".png"))).getImage();
        bound = new Rectangle(xPos, yPos, 100, 100);
        xMovement = 0;
        yMovement = 0;
        jumped = false;
        this.health = health;
    }

    // == METHODS ==

    //Walking
    public void walk(int dir) {
        if (Math.abs(xMovement) < 8)
            xMovement += dir;
    }

    //Jumping
    public void jump() {
    	if (!jumped) {
    		yMovement = -20;
    		jumped = true;
    	}
    }

    public Stand summonStand() {
    	return new Stand(x, y, standImage, xMovement, yMovement);
    }
    
    /*
    //Moves the player
    public void update() {
        xMovement *= 0.9;
        yMovement += 1;
        bound.setLocation((int) (getX() + xMovement), (int) (getY() + yMovement));
        moveByAmount((int) xMovement, (int) yMovement);
    }
    */

    public void updateX() {
    	xMovement *= 0.9;
    	bound.setLocation((int) (getX() + xMovement), (int) (getY()));
        moveByAmount((int) xMovement, 0);
    }
    
    public void updateY() {
    	yMovement += 0.5;
    	bound.setLocation((int) (getX()), (int) (getY() + yMovement));
        moveByAmount(0, (int) yMovement);
    }
    
    public void intersects(Obstacle obstacle) {
    	if (obstacle.getBounds().intersects(bound)) {
    		yMovement = -1;
    		jumped = false;
    	}
    }
    
    public void intersectsStand(Stand stand) {
    	if (stand != null && stand.getBounds().intersects(bound)) {
    		xMovement += stand.getXPower();
    		yMovement -= stand.getYPower();
    	}
    }
    
    //Getters and Setters for health
    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
    }
}