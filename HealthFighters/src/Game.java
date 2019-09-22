import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JPanel;

public class Game extends JPanel implements KeyListener {
	
	private Obstacle[] obstacles;
	private Player[] players;
	private String[][] playerNames;
	private Stand[] stands;
	private boolean upKeyPressed, leftKeyPressed, rightKeyPressed, upKeyPressed2, 
	leftKeyPressed2, rightKeyPressed2, attackKeyPressed, attackKeyPressed2, playerWin1, playerWin2;
	private final int HEALTH = 8;
	
	public Game() {
		firstMap();
		
		playerNames = new String[2][2];
		playerNames[0][0] = "Hospital";
		playerNames[1][0] = "Evil";
		playerNames[0][1] = "StarPlat";
		playerNames[1][1] = "World";
		
		players = new Player[2];
		players[0] = new Player(700, 0, HEALTH, playerNames[0][0], playerNames[0][1]);
		players[1] = new Player(750, 0, HEALTH, playerNames[1][0], playerNames[1][1]);
		
		stands = new Stand[2];
		
		this.setFocusable(true);
        addKeyListener(this);
	}
	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		
		g.setFont(new Font("Serif", 100, 100));
		g.setColor(Color.PINK);
		
		for (int i = 0; i < stands.length; i++) {
			if (stands[i] != null) {
				stands[i].draw(g, this);
			}
		}

		for (int i = 0; i < obstacles.length; i++) {
			obstacles[i].draw(g);
		}
		
		for (int i = 0; i < players.length; i++) {
			players[i].draw(g, this);
		}
		
		g.setColor(Color.BLACK);
		
		if (playerWin1) {
			g.drawString("Epic Win! You protected the hospital!", 0, 200);
		}
		else if (playerWin2) {
			g.drawString("Not Epic! The hospital was destroyed!", 0, 200);
		}
		
		this.setFocusable(true);
        this.requestFocusInWindow();
	}
	
	public void firstMap() {
		obstacles = new Obstacle[2];
		obstacles[0] = new Obstacle(0, 700, 1550, 2000);
		obstacles[1] = new Obstacle(625, 450, 300, 50);
	}
	
	public void run() {
		while (true) {
			if (players[0].getHealth() < 0) {
				playerWin2 = true;
				repaint();
				break;
			}
			else if (players[1].getHealth() < 0) {
				playerWin1 = true;
				repaint();
				break;
			}
			//Controls
	        if (leftKeyPressed) {
	            players[0].walk(-1);
	        }
	        if (rightKeyPressed) {
	            players[0].walk(1);
	        }
	        if (upKeyPressed) {
	            players[0].jump();
	        }
	        if (attackKeyPressed) {
	        	if (stands[0] == null)
	        		stands[0] = players[0].summonStand();
	        }
	        if (leftKeyPressed2) {
	            players[1].walk(-1);
	        }
	        if (rightKeyPressed2) {
	            players[1].walk(1);
	        }
	        if (upKeyPressed2) {
	            players[1].jump();
	        }
	        if (attackKeyPressed2) {
	        	if (stands[1] == null)
	        		stands[1] = players[1].summonStand();
	        }
	        
	        boolean[] standsRemoved = new boolean[2];
	        for (int i = 0; i < players.length; i++) {
	        	players[i].updateX();
	        	players[i].updateY();
	        	if (stands[i] != null) {
	        		stands[i].forward();
	        		stands[i].upward();
	        		if (stands[i].update())
	        			standsRemoved[i] = true;
	        		else if (stands[i].getX() < -200 || stands[i].getX() > 1650)
	        			standsRemoved[i] = true;
	        	}
	        	
	        	if (i == 0) {
	        		players[i].intersectsStand(stands[1]);
	        	}
	        	else {
	        		players[i].intersectsStand(stands[0]);
	        	}
	        	
	        	for (int j = 0; j < obstacles.length; j++) {
	        		players[i].intersects(obstacles[j]);
	        	}
	        }
	        
	        repaint();
	        
	        // Delay
	        try {
	            Thread.sleep(7);
	        } catch (InterruptedException e) {
	            // TODO Auto-generated catch block
	            e.printStackTrace();
	        }
	        
	        for (int i = 0; i < players.length; i++) {
	        	if (players[i].getY() > 1000) {
	        		int currentHealth = players[i].getHealth()-1;
	        		players[i] = new Player(725, 0, currentHealth, playerNames[i][0], playerNames[i][1]);
	        		System.out.println(currentHealth);
	        	}
	        }
	        
	        for (int i = 0; i < stands.length; i++) {
	        	if (standsRemoved[i]) {
	        		stands[i] = null;
	        	}
	        }
		}
			
	}
	
	//If a keyboard key is pressed
    @Override
    public void keyPressed(KeyEvent e) {
        int key = e.getKeyCode();
        if (key == KeyEvent.VK_A) {
            leftKeyPressed = true;
        }
        if (key == KeyEvent.VK_D) {
            rightKeyPressed = true;
        }
        if (key == KeyEvent.VK_W) {
            upKeyPressed = true;
        }
        if (key == KeyEvent.VK_SHIFT) {
        	attackKeyPressed = true;
        }
        if (key == KeyEvent.VK_J) {
            leftKeyPressed2 = true;
        }
        if (key == KeyEvent.VK_L) {
            rightKeyPressed2 = true;
        }
        if (key == KeyEvent.VK_I) {
            upKeyPressed2 = true;
        }
        if (key == KeyEvent.VK_CONTROL) {
        	attackKeyPressed2 = true;
        }
    }

    //When the keyboard key is released
    @Override
    public void keyReleased(KeyEvent e) {
        int key = e.getKeyCode();
        if (key == KeyEvent.VK_A) {
            leftKeyPressed = false;
        }
        if (key == KeyEvent.VK_D) {
            rightKeyPressed = false;
        }
        if (key == KeyEvent.VK_W) {
            upKeyPressed = false;
        }
        if (key == KeyEvent.VK_SHIFT) {
        	attackKeyPressed = false;
        }
        if (key == KeyEvent.VK_J) {
            leftKeyPressed2 = false;
        }
        if (key == KeyEvent.VK_L) {
            rightKeyPressed2 = false;
        }
        if (key == KeyEvent.VK_I) {
            upKeyPressed2 = false;
        }
        if (key == KeyEvent.VK_CONTROL) {
        	attackKeyPressed2 = false;
        }
    }

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}
	
}
