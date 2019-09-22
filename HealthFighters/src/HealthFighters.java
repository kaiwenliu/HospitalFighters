import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Label;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.SwingConstants;

public class HealthFighters extends JPanel implements ActionListener {
	
	// ==FIELDS==
    private static final int DRAWING_WIDTH = 1000; //Width of Window
    private static final int DRAWING_HEIGHT = 750; //Height of Window
	private static final Color BACKGROUND = Color.CYAN;
	private static JButton play;
	private static JButton faces;
	private static JButton voices;
	private static Label title;
	private static JFrame window;
	
	private final Font MENU_FONT = new Font("SansSerif", Font.PLAIN, 100);
	
	public HealthFighters() {
		title = new Label();
		title.setFont(MENU_FONT);
		title.setText("Welcome to HealthFighters!");
		
		play = new JButton("Play!");
		faces = new JButton("Health!");
		voices = new JButton("Soon!");
		
		play.setFont(MENU_FONT);
		play.setPreferredSize(new Dimension(500,250));
		play.addActionListener(this);
		
		faces.setFont(MENU_FONT);
		faces.setPreferredSize(new Dimension(500,250));
		faces.addActionListener(this);
		
		voices.setFont(MENU_FONT);
		voices.setPreferredSize(new Dimension(500,250));
		voices.addActionListener(this);
		
		this.add(title);
		this.add(Box.createVerticalStrut(500));
		this.add(play);
		this.add(faces);
		this.add(voices);
	}
	
	public static void main(String[] args) {
		//ImageIcon img = new ImageIcon(Main.class.getResource("/assets/Icon.png"));
		
		 //Window
        window = new JFrame("HealthFighters");
        window.setBounds(150, 0, DRAWING_WIDTH, DRAWING_HEIGHT);
        window.setExtendedState(window.getExtendedState() | JFrame.MAXIMIZED_BOTH);
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        //Panel
        HealthFighters panel = new HealthFighters();
        panel.setBackground(BACKGROUND);
        Container c = window.getContentPane();
        c.add(panel);

        //window.setIconImage(img.getImage());
        window.setVisible(true);
	}


	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource().equals(play)) {
			Game panel = new Game();
			panel.setBackground(BACKGROUND);
			Container c = window.getContentPane();
			window.setVisible(false);
			c.removeAll();
			c.add(panel);
			window.setVisible(true);
			new Thread(new Runnable() {
				@Override
				public void run() {
					panel.run();
				}
			}).start();
			
		}
		else if (e.getSource().equals(faces)) {
			
		}
		else if (e.getSource().equals(voices)) {
			
		}
	}
}
