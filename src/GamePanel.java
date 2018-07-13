import javax.imageio.ImageIO;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;

public class GamePanel extends JPanel implements Runnable, KeyListener {

	public static final int WIDTH = 1000;
	public static final int HEIGHT = 1000;

	private Thread thread;
	private boolean gameRunning;

	private BufferedImage image;
	private Graphics2D g;

	private final int FPS = 60;
	private double averageFPS;


	private World world;
	private Player player;
	public static ArrayList<Shot> shotsPlayer;
	public static ArrayList<Shot> shotsEnemy;
	public static ArrayList<Enemy> enemies;

	private boolean waitingForKeypress = true;
	private boolean waitingForKeypress2 = true;
	private boolean showCredits = false;
	private static boolean ver;

	private BufferedImage title = null;

	public GamePanel() {

		super();
		setPreferredSize(new Dimension(WIDTH, HEIGHT));
		setFocusable(true);
		requestFocus();
	}

	@Override
	public void addNotify() {
		super.addNotify(); // notifies jpanel is done loading

		if (thread == null) {
			thread = new Thread(this);
			thread.start();
		} // if

		addKeyListener(this);
	} //addNotify

	//@Override
	public void run() {

		init();

		long startTime;
		long URDTimeMilliseconds;
		long waitTime;
		long totalTime = 0;
		long targetTime = 1000 / FPS;

		int frameCount = 0;
		int maxFrameCount = 60;

		// game loop
		while (gameRunning) {
			startTime = System.nanoTime();

			gameUpdate();
			gameRender();
			gameDraw();


			URDTimeMilliseconds = (System.nanoTime() - startTime) / 1000000;
			waitTime = targetTime - URDTimeMilliseconds;

			try {
				Thread.sleep(waitTime);
			} catch (Exception e) {

			} // catch

			totalTime += System.nanoTime() - startTime;
			frameCount++;

			if (frameCount == maxFrameCount) {
				averageFPS = (double) 1000 / ((totalTime / frameCount) / 1000000);
				frameCount = 0;
				totalTime = 0;
			}
		} // while
	} // run

	public void sound(String str) {
		try {
			AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(this.getClass().getResource(str));
			Clip clip = AudioSystem.getClip();
			clip.open(audioInputStream);
			clip.start();
			if (str.equals("soundtrack.wav"))
				clip.loop(Clip.LOOP_CONTINUOUSLY);
			// If you want the sound to loop infinitely, then put: clip.loop(Clip.LOOP_CONTINUOUSLY);
			// If you want to stop the sound, then use clip.stop();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	private void init() {
		sound("soundtrack.wav");
		gameRunning = true;

		image = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_RGB);
		g = (Graphics2D) image.getGraphics();

		world = new World();
		player = new Player(world);

		enemies = new ArrayList<Enemy>();
		shotsPlayer = new ArrayList<Shot>();
		shotsEnemy = new ArrayList<Shot>();

		LinkedList<String> l = new LinkedList<>();
		l.add("To be or not to be that is the question asdfsaf dsafsafsafsaf saf saf safdsa fsaf");

	} // init

	private void gameUpdate() {
		world.update();
		player.update();
		g.setColor(Color.RED);

		for (int i = 0; i < shotsPlayer.size(); i++) {
			boolean remove = shotsPlayer.get(i).update();
			if (remove) {
				shotsPlayer.remove(i);
				i--;
			}
		}

		for (int i = 0; i < shotsEnemy.size(); i++) {
			boolean remove = shotsEnemy.get(i).update();
			if (remove) {
				shotsEnemy.remove(i);
				i--;
			}
		}


		for (int i = 0; i < enemies.size(); i++) {
			enemies.get(i).update();
		}


		if (player.getLives() == 0) {
			ver = true;
		}


	} // gameUpdate

	private void gameRender() {
		world.draw(g);
		player.draw(g);
		//g.drawString("FPS: " + averageFPS, 500, 100);
//		for (int i = 0; i < shotsPlayer.size(); i++) {
//			shotsPlayer.get(i).draw(g);
//		}
		shotsPlayer.forEach(b -> b.draw(g));
		shotsEnemy.forEach(b -> b.draw(g));
		enemies.forEach(b -> b.draw(g));
//		for (int i = 0; i < shotsEnemy.size(); i++) {
//			shotsEnemy.get(i).draw(g);
//		}

//		for (int i = 0; i < enemies.size(); i++) {
//			enemies.get(i).draw(g);
//		}
//		System.out.println("wtfff");
		if (waitingForKeypress) {
			try {
				title = ImageIO.read(new File("src/sprites/title.png"));
			} catch (IOException e) {
				System.out.println("IOException");
			}

			g.drawImage(title, 0, 0, null);
		}

		if (waitingForKeypress2 && !waitingForKeypress) {
			try {
				title = ImageIO.read(new File("src/sprites/instructions.png"));
			} catch (IOException e) {
				System.out.println("IOException");
			}

			g.drawImage(title, 0, 0, null);
		}

		if (showCredits) {
			try {
				title = ImageIO.read(new File("src/sprites/credits.png"));
			} catch (IOException e) {
				System.out.println("IOException");
			}

			g.drawImage(title, 0, 0, null);
		}

		if (ver) {
			try {
				title = ImageIO.read(new File("src/sprites/credits.png"));
			} catch (IOException e) {
				System.out.println("IOException");
			}
			g.drawImage(title, 0, 0, null);

		}
		g.setColor(Color.red);

	} // gameRender

	private void gameDraw() {
		Graphics g2 = this.getGraphics();
		g2.drawImage(image, 0, 0, null);
		g2.dispose();

	} // gameDraw

	//@Override
	public void keyTyped(KeyEvent key) {

	}

	//@Override
	public void keyPressed(KeyEvent key) {

		int code = key.getKeyCode();
		if (code == KeyEvent.VK_SPACE) {
			if (code == KeyEvent.VK_SPACE && !waitingForKeypress) {
				waitingForKeypress2 = false;
				if (world.getCurrent().getTypewriter() != null)
					world.getCurrent().getTypewriter().start();
			}
			waitingForKeypress = false;
		}

		if (code == KeyEvent.VK_C) {
			showCredits = !showCredits;
		}
		if (code == KeyEvent.VK_ENTER) {
			// advance dialogue
			// advance dialogue
			Typewriter t = world.getCurrent().getTypewriter();
			if (t != null && t.isAnimate()) {
				boolean animate = t.isAnimate();
				t.advance();
				if(animate != t.isAnimate())
					if(enemies.get(0).isWitches())
						enemies.clear();
			}

		}
		if (waitingForKeypress || waitingForKeypress2) {
			return;
		}

		// respond to move left, right or fire
		if (code == KeyEvent.VK_A) {
			player.setLeft(true);

		} // if

		if (code == KeyEvent.VK_D) {
			player.setRight(true);

		} // if

		if (code == KeyEvent.VK_W) {
			player.setUp(true);

		}

		if (code == KeyEvent.VK_S) {
			player.setDown(true);

		}
		if (player.getTileMap().getTypewriter() == null || !player.getTileMap().getTypewriter().isAnimate()) {
			if (code == KeyEvent.VK_LEFT) {
				player.setFiring(true);
				player.setFacingLeft(true);
				player.setFacingDown(false);
				player.setFacingRight(false);
				player.setFacingUp(false);
			}

			if (code == KeyEvent.VK_RIGHT) {
				player.setFiring(true);

				player.setFacingRight(true);
				player.setFacingLeft(false);
				player.setFacingDown(false);
				player.setFacingUp(false);
			}

			if (code == KeyEvent.VK_UP) {
				player.setFiring(true);

				player.setFacingUp(true);
				player.setFacingLeft(false);
				player.setFacingRight(false);
				player.setFacingDown(false);
			}

			if (code == KeyEvent.VK_DOWN) {
				player.setFiring(true);

				player.setFacingDown(true);
				player.setFacingLeft(false);
				player.setFacingRight(false);
				player.setFacingUp(false);
			}
		}
		if (code == KeyEvent.VK_P) {
			for (int i = 0; i < GamePanel.enemies.size(); i++) {
				GamePanel.enemies.get(i).setShowPaths(true);
			}
		}
		if (code == KeyEvent.VK_O) {
			for (int i = 0; i < GamePanel.enemies.size(); i++) {
				GamePanel.enemies.get(i).setShowPaths(false);
			}
		}
	}

	//@Override
	public void keyReleased(KeyEvent key) {
		int code = key.getKeyCode();

		// respond to move left, right or fire
		if (code == KeyEvent.VK_A) {
			player.setLeft(false);
		} // if

		if (code == KeyEvent.VK_D) {
			player.setRight(false);
		} // if

		if (code == KeyEvent.VK_W) {
			player.setUp(false);
		}
		if (code == KeyEvent.VK_S) {
			player.setDown(false);
		}

		if (code == KeyEvent.VK_LEFT) {
			player.setFiring(false);
		}

		if (code == KeyEvent.VK_RIGHT) {
			player.setFiring(false);
		}

		if (code == KeyEvent.VK_UP) {
			player.setFiring(false);

		}

		if (code == KeyEvent.VK_DOWN) {
			player.setFiring(false);

		}


	} // keyReleased
} // GamePanel