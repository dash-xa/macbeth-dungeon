//import com.sun.org.apache.xpath.internal.SourceTree;

import javax.imageio.ImageIO;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Player {

	private double x; // player x
	private double y; // player y

	private int width; // player width
	private int height; // player height

	private long firingTimer; // firing timer
	private long firingDelay; // firing delay
	private long changeSpriteDownDelay; // change sprite delay
	private long changeSpriteTimer; // change sprite timer
	private long recoveryTimer; // recovertimer once hit

	private double dx; // speed in x direction
	private double dy; // speed in y direction
	private double ACCELERATION; // acceleration
	private double MAX_SPEED; // max speed
	private double FRICTION; // friction

	private boolean left; // moving left
	private boolean right; // moving right
	private boolean up; // moving up
	private boolean down; // moving down
	private boolean changeStage; // moving stage
	private boolean firing; // firing
	private boolean recovering; // recovering

	private boolean facingLeft; // facing left
	private boolean facingRight; // facing right
	private boolean facingUp; // facing up
	private boolean facingDown; // facing down

	private int topLeft; // top left collision
	private int topRight; // top right collision
	private int bottomLeft; // bottom left collision
	private int bottomRight; // bottom right collision
	private int lives; // lives

	private TileMap tileMap; // this tilemap
	private TileMap newTileMap; // new changed tilemap

	private World world; // world in which player is in

	// images for animation

	// character
	BufferedImage[] leftImage = new BufferedImage[4];
	BufferedImage[] rightImage = new BufferedImage[4];
	BufferedImage[] upImage = new BufferedImage[4];
	BufferedImage[] downImage = new BufferedImage[4];

	// boss
	BufferedImage[] bossLeftImage = new BufferedImage[4];
	BufferedImage[] bossRightImage = new BufferedImage[4];
	BufferedImage[] bossUpImage = new BufferedImage[4];
	BufferedImage[] bossDownImage = new BufferedImage[4];

	// heart images
	BufferedImage heart;

	private int current = 0; // current frame
	private boolean initialized = false; // initialization
	private int frame = 0; // frames

	public boolean isRecovering() {
		return recovering;
	} // isRecovering

	public void setRecovering(boolean recovering) {
		this.recovering = recovering;
	} // setRecovering

	public TileMap getTileMap() {
		return tileMap;
	}

	public Player(World worldInput) {

		// set attributes
		this.x = GamePanel.WIDTH / 2;
		this.y = GamePanel.HEIGHT / 2;
		this.world = worldInput;
		this.tileMap = world.get(world.getPlayerY(), world.getPlayerX());
		this.newTileMap = null;
		this.width = 25;
		this.height = 44;
		this.ACCELERATION = 0.7;
		this.MAX_SPEED = 6;
		this.FRICTION = 0.35;
		this.dx = 0;
		this.dy = 0;
		this.lives = 8;
		this.firing = false;
		this.firingTimer = System.nanoTime();
		this.changeSpriteDownDelay = 1200;
		this.changeSpriteTimer = System.nanoTime();
		this.firingDelay = 400;
		this.facingDown = true;
		this.recovering = false;
		this.recoveryTimer = 0;

	} // Player

	public void setLeft(boolean b) {
		this.left = b;
	} // setLeft

	public void setRight(boolean b) {
		this.right = b;
	} // setRight

	public void setUp(boolean b) {
		this.up = b;
	} // setUp

	public void setDown(boolean b) {
		this.down = b;
	} // setDown

	public void setFacingLeft(boolean b) {
		this.facingLeft = b;
	} // setFacingLeft

	public void setFacingRight(boolean b) {
		this.facingRight = b;
	} // setFacingRight

	public void setFacingUp(boolean b) {
		this.facingUp = b;
	} // setFacingUp

	public void setFacingDown(boolean b) {
		this.facingDown = b;
	} // setFacingDown

	public boolean isFacingLeft() {
		return facingLeft;
	} // isFacingLeft

	public boolean isFacingRight() {
		return facingRight;
	} // isFacingRight

	public boolean isFacingUp() {
		return facingUp;
	} // isFacingUp

	public boolean isFacingDown() {
		return facingDown;
	} // isFacingDown

	public boolean isLeft() {
		return left;
	} // isLeft

	public boolean isRight() {
		return right;
	} // isRight

	public boolean isUp() {
		return up;
	} // isUp

	public boolean isDown() {
		return down;
	} // isDown

	public void setFiring(boolean firing) {
		this.firing = firing;
	} // setFiring

	public double getX() {
		return x;
	} // getX

	public double getY() {
		return y;
	} // getY

	public int getLives() {
		return lives;
	} //getLives

	public int getWidth() {
		return width;
	} // getWidth

	public int getHeight() {
		return height;
	} // getHeight

	public double getDx() {
		return dx;
	}

	public double getDy() {
		return dy;
	}

	//updates character
	public void update() {
		Typewriter t = tileMap.getTypewriter();
		if (t != null && t.isAnimate()) {
			return;
		}
		if (!this.changeStage) {

			if (left) {
				dx -= ACCELERATION;
				if (dx < -MAX_SPEED) {
					dx = -MAX_SPEED;
				} // if
			} else if (right) {
				dx += ACCELERATION;
				if (dx > MAX_SPEED) {
					dx = MAX_SPEED;
				} // if
			} else {
				if (dx > 0) {
					dx -= FRICTION;
					if (dx < 0) {
						dx = 0;
					}
				} else if (dx < 0) {
					dx += FRICTION;
					if (dx > 0) {
						dx = 0;
					} // if
				} // else if
			} // else

			if (up) {
				dy -= ACCELERATION;
				if (dy < -MAX_SPEED) {
					dy = -MAX_SPEED;
				} // if
			} else if (down) {
				dy += ACCELERATION;

				if (dy > MAX_SPEED) {
					dy = MAX_SPEED;
				} // if
			} else {
				if (dy > 0) {
					dy -= FRICTION;
					if (dy < 0) {
						dy = 0;
					}
				} else if (dy < 0) {
					dy += FRICTION;
					if (dy > 0) {
						dy = 0;
					} //if
				} // else if
			} // else
		} // if

		double toX = x + dx;
		double toY = y + dy;

		// collision
		calculateCorners(x, toY);
		if (dy < 0) {
			if (topLeft == 1 || topRight == 1 || topLeft == 5 || topRight == 5 || topLeft == 12 || topRight == 12 || topLeft == 14 || topRight == 14 || changeStage) {
				dy = 0;
			} else if (topLeft == 4 || topRight == 4) {
				this.changeStage = true;

				world.setPlayerY(world.getPlayerY() - 1);
				this.newTileMap = world.get(world.getPlayerY(), world.getPlayerX());
				this.newTileMap.setMoveToStageUp(true);
				this.newTileMap.setLocationChange();
				this.newTileMap.setChangeStageNew(true);

				this.tileMap.setMoveToStageUp(true);
				this.tileMap.setChangeStageCurrent(true);

				this.y = 925;
				this.tileMap = this.newTileMap;

			} // else if
		} // if

		if (dy > 0) {
			if (bottomRight == 1 || bottomLeft == 1 || bottomRight == 5 || bottomLeft == 5 || bottomRight == 12 || bottomLeft == 12 || bottomRight == 14 || bottomLeft == 14 || changeStage) {
				dy = 0;
			} else if (bottomRight == 8 || bottomLeft == 8) {
				this.changeStage = true;

				world.setPlayerY(world.getPlayerY() + 1);
				this.newTileMap = world.get(world.getPlayerY(), world.getPlayerX());
				this.newTileMap.setMoveToStageDown(true);
				this.newTileMap.setLocationChange();
				this.newTileMap.setChangeStageNew(true);

				this.tileMap.setMoveToStageDown(true);
				this.tileMap.setChangeStageCurrent(true);

				this.y = 75;
				this.tileMap = this.newTileMap;

			} // else if
		} // if

		calculateCorners(toX, y);
		if (dx < 0) {
			if (topLeft == 1 || bottomLeft == 1 || topLeft == 5 || bottomLeft == 5 || topLeft == 12 || bottomLeft == 12 || topLeft == 14 || bottomLeft == 14 || changeStage) {
				dx = 0;
			} else if (topLeft == 2 || bottomLeft == 2) {
				this.changeStage = true;

				world.setPlayerX(world.getPlayerX() - 1);
				this.newTileMap = world.get(world.getPlayerY(), world.getPlayerX());
				this.newTileMap.setMoveToStageLeft(true);
				this.newTileMap.setLocationChange();
				this.newTileMap.setChangeStageNew(true);

				this.tileMap.setMoveToStageLeft(true);
				this.tileMap.setChangeStageCurrent(true);

				this.x = 925;
				this.tileMap = this.newTileMap;
			} // else if
		} // if

		if (dx > 0) {
			if (topRight == 1 || bottomRight == 1 || topRight == 5 || bottomRight == 5 || topRight == 12 || bottomRight == 12 || topRight == 14 || bottomRight == 14 || changeStage) {
				dx = 0;
			} else if (topRight == 6 || bottomRight == 6) {
				this.changeStage = true;
				world.setPlayerX(world.getPlayerX() + 1);
				this.newTileMap = world.get(world.getPlayerY(), world.getPlayerX());
				this.newTileMap.setMoveToStageRight(true);
				this.newTileMap.setLocationChange();
				this.newTileMap.setChangeStageNew(true);

				this.tileMap.setMoveToStageRight(true);
				this.tileMap.setChangeStageCurrent(true);

				this.x = 75;
				this.tileMap = this.newTileMap;
			} // else if
		} // if

		if (changeStage) {
			GamePanel.shotsPlayer.clear();
			GamePanel.shotsEnemy.clear();
			GamePanel.enemies.clear();
		} // if

		// once tile is changed
		if (this.tileMap.getWorldXPos() == 4 && this.tileMap.getWorldYPos() == 3 && GamePanel.enemies.isEmpty() && !this.tileMap.isCleared()) {
			GamePanel.enemies.add(new Enemy(0, 1, this.tileMap, this, 500, 100));
		}

		if (this.newTileMap != null && this.newTileMap.isChanged()) {

			Typewriter type = newTileMap.getTypewriter();

			if (t != null && !t.isAnimate()) {
				t.start();
			}

			this.changeStage = false;
			this.dy = 0;
			this.dx = 0;
			this.ACCELERATION = 0.7;
			this.MAX_SPEED = 6;
			this.FRICTION = 0.35;
			this.newTileMap.setChanged(false);
			world.setLastPlayerX(world.getPlayerX());
			world.setLastPlayerY(world.getPlayerY());
			GamePanel.shotsPlayer.clear();
			GamePanel.shotsEnemy.clear();

			// create enemies
			if (world.getLastPlayerX() == 4 && world.getLastPlayerY() == 2) {
				GamePanel.enemies.add(new Enemy(9, 0, this.tileMap, this, 500, 500));
			} else if (world.getLastPlayerX() == 4 && world.getLastPlayerY() == 1) {
				GamePanel.enemies.add(new Enemy(8, 1, this.tileMap, this, 500, 500));
			}

			System.out.println("x: " + world.getLastPlayerX() + ", y: " + world.getLastPlayerY());

			if (!this.tileMap.isCleared()) {
                /*
        0 - knight
        1 - duncan
        2 - banquo
        3 - banquo ghost
        4 - lady macduff
        5 - macduff's son
        6 - young siward
        7 - macduff
        8 - lady macbeth
        */
				if (world.getLastPlayerX() == 3 && world.getLastPlayerY() == 1) {
					// duncan
					GamePanel.enemies.add(new Enemy(1, 1, this.tileMap, this, 500, 500));
				} else if (world.getLastPlayerX() == 2 && world.getLastPlayerY() == 1) {
					// banquo
					GamePanel.enemies.add(new Enemy(2, 1, this.tileMap, this, 900, 900));

				} else if (world.getLastPlayerX() == 2 && world.getLastPlayerY() == 0) {
					// banquo ghost
					GamePanel.enemies.add(new Enemy(3, 1, this.tileMap, this, 900, 900));

				} else if (world.getLastPlayerX() == 1 && world.getLastPlayerY() == 0) {
					// lady macduff
					GamePanel.enemies.add(new Enemy(4, 1, this.tileMap, this, 900, 900));

				} else if (world.getLastPlayerX() == 0 && world.getLastPlayerY() == 0) {
					// young siward
					GamePanel.enemies.add(new Enemy(6, 1, this.tileMap, this, 900, 900));
				} else if (world.getLastPlayerX() == 0 && world.getLastPlayerY() == 1) {

					GamePanel.enemies.add(new Enemy(7, 5, this.tileMap, this, 900, 900));
				}
			}


		} // if

		if (tileMap.isCleared() && tileMap.isEntranceCreated() == false) {
			if (tileMap.isHasEnemies()) {
				tileMap.createEntrances(world);
			} else {
			}
		}

		x += dx;
		y += dy;

		// fire shots
		if (firing) {
			long elapsed = (System.nanoTime() - firingTimer) / 1000000;
			if (elapsed > firingDelay) {
				GamePanel.shotsPlayer.add(new Shot(this, x, y));
				firingTimer = System.nanoTime();
				sound("player_shoot.wav");
				System.out.println("player shoot");
				changeSpriteTimer = System.nanoTime();
			} // if
		} // if

		// change sprite
		long elapsedChangeSprite = (System.nanoTime() - changeSpriteTimer) / 1000000;
		if (elapsedChangeSprite > changeSpriteDownDelay && !firing) {
			this.facingDown = true;
			this.facingUp = false;
			this.facingRight = false;
			this.facingLeft = false;
			changeSpriteTimer = System.nanoTime();
		} // if

		// recover from hit
		long elapsedRecovery = (System.nanoTime() - recoveryTimer) / 1000000;
		if (elapsedRecovery > 2000) {
			recovering = false;
			recoveryTimer = 0;
			changeSpriteTimer = System.nanoTime();
		} // if

		// collision for enemies
		for (int j = 0; j < GamePanel.enemies.size(); j++) {
			Enemy enemy = GamePanel.enemies.get(j);

			double ex = enemy.getX();
			double ey = enemy.getY();

			if (!this.recovering && enemy.isCanShoot()) {
				if (((this.x + width) - ex > 0) && (this.x - (ex + enemy.getWidth()) < 0) && ((this.y + this.height) - ey > 0)
						&& (this.y - (ey + enemy.getHieght()) < 0)) {
					loseLife();
					sound("jab.wav");
					System.out.println("jab called");
				} // if
			} // if
		} // for

		for (int i = 0; i < GamePanel.shotsPlayer.size(); i++) {
			Shot shot = GamePanel.shotsPlayer.get(i);

			int shotX = (int) (shot.getX());
			int shotY = (int) (shot.getY());

			int value = tileMap.getTile(tileMap.getRowTile(shotY), tileMap.getColTile(shotX));
			if (value == 1 || value == 2 || value == 4 || value == 5 || value == 6 || value == 8 || value == 12 || value == 14) {
				GamePanel.shotsPlayer.remove(i);
			} // if

			for (int j = 0; j < GamePanel.enemies.size(); j++) {
				Enemy enemy = GamePanel.enemies.get(j);

				double ex = enemy.getX();
				double ey = enemy.getY();

				if (!enemy.isLadyMacbeth() && !enemy.isWitches()) {
					if (((shotX + shot.getRadius()) - ex > 0) && (shotX - (ex + enemy.getWidth()) < 0) && ((shotY + shot.getRadius()) - ey > 0)
							&& (shotY - (ey + enemy.getHieght()) < 0)) {
						GamePanel.shotsPlayer.remove(shot);
						enemy.loseHealth();
						sound("hit.wav");
						System.out.println("hit called");
					} // if

				}

			} // for

		} // for

	} // update

	public void run() {
		if (frame / 10 >= 1) {
			current++;
			if (current >= 4) {
				current = 0;
			} // if
			frame = 0;
		} // if

	} //run

	public void init() {
		for (int i = 0; i < 4; i++) {
			try {
				rightImage[i] = ImageIO.read(new File("src/sprites/mc/" + (i + 1) + ".png"));
			} catch (IOException e) {
				System.out.println("IOException");
			} // catch
		} // ofor
		for (int i = 0; i < 4; i++) {
			try {
				upImage[i] = ImageIO.read(new File("src/sprites/mc/" + (i + 5) + ".png"));
			} catch (IOException e) {
				System.out.println("IOException");
			} // catch
		} //for
		for (int i = 0; i < 4; i++) {
			try {
				downImage[i] = ImageIO.read(new File("src/sprites/mc/" + (i + 9) + ".png"));
			} catch (IOException e) {
				System.out.println("IOException");
			} // catch
		} // for
		for (int i = 0; i < 4; i++) {
			try {
				leftImage[i] = ImageIO.read(new File("src/sprites/mc/" + (i + 13) + ".png"));
			} catch (IOException e) {
				System.out.println("IOException");
			} // catch
		} // for

		try {
			this.heart = ImageIO.read(new File("src/sprites/mc/heart.png"));
		} catch (IOException e) {
			System.out.println("IOException");

		} // catch

	} // init


	public void draw(Graphics2D g) {
		int tileX = tileMap.getX();
		int tileY = tileMap.getY();
		frame++;

		if (!initialized) {
			init();
		}

		initialized = true;
		run();
		//stop();

		if (left) {
			g.drawImage(leftImage[current], (int) (tileX + x - width / 2), (int) (tileY + y - height / 2), null);
		} else if (right) {
			g.drawImage(rightImage[current], (int) (tileX + x - width / 2), (int) (tileY + y - height / 2), null);
		} else if (up) {
			g.drawImage(upImage[current], (int) (tileX + x - width / 2), (int) (tileY + y - height / 2), null);
		} else if (down) {
			g.drawImage(downImage[current], (int) (tileX + x - width / 2), (int) (tileY + y - height / 2), null);
		} else if (facingLeft) {
			g.drawImage(leftImage[0], (int) (tileX + x - width / 2), (int) (tileY + y - height / 2), null);
		} else if (facingRight) {
			g.drawImage(rightImage[0], (int) (tileX + x - width / 2), (int) (tileY + y - height / 2), null);
		} else if (facingUp) {
			g.drawImage(upImage[0], (int) (tileX + x - width / 2), (int) (tileY + y - height / 2), null);
		} else if (facingDown) {
			g.drawImage(downImage[0], (int) (tileX + x - width / 2), (int) (tileY + y - height / 2), null);
		} else {
			this.facingDown = true;
			this.facingLeft = false;
			this.facingRight = false;
			this.facingUp = false;
		} // else

		for (int i = 0; i < lives; i++) {
			g.drawImage(heart, 50 * (i + 2), 75, null);
		}
	}

	private void calculateCorners(double x, double y) {
		int leftTile = tileMap.getColTile((int) (x - width / 2));
		int rightTile = tileMap.getColTile((int) (x + width / 2));
		int topTile = tileMap.getRowTile((int) (y - height / 2));
		int bottomTile = tileMap.getRowTile((int) (y + height / 2));

		topLeft = tileMap.getTile(topTile, leftTile);
		topRight = tileMap.getTile(topTile, rightTile);
		bottomLeft = tileMap.getTile(bottomTile, leftTile);
		bottomRight = tileMap.getTile(bottomTile, rightTile);
	}

	public void setNewTileMap(TileMap map) {
		this.tileMap = map;
	}

	public void setChangeStage(boolean changeStage) {
		this.changeStage = changeStage;
	}

	public void loseLife() {
		this.lives--;
		this.recovering = true;
		this.recoveryTimer = System.nanoTime();
	}

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
}