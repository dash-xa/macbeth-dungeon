//import java.awt.*;

import java.awt.*;

// interacts with tile maps
public class World {


	private ShakeSpeareWorld worlds; // contains all world data

	private TileMap current;
	private TileMap lastMap;

	// index of player in ShakeSpeareWorld
	private int playerX;
	private int playerY;
	private int lastPlayerX;
	private int lastPlayerY;
	private int worldLength;

    public int getWorldLength() {
        return worldLength;
    }

    public World() {
		//world
		worlds = new ShakeSpeareWorld();
		playerX = 4; playerY = 3;
		current = worlds.get(playerY, playerX);
		lastPlayerX = playerX;
		lastPlayerY = playerY;
		worldLength = worlds.getWorldLength();

	}

	// used on stage changes
	public void update() {
		if (lastPlayerX != playerX || lastPlayerY != playerY) {
			lastMap = worlds.get(lastPlayerY, lastPlayerX);
			current = worlds.get(playerY, playerX);
			lastMap.update();
			current.update();
            System.out.println("update all");
        }
	}

	public void draw(Graphics2D g) {
		if (lastPlayerX != playerX || lastPlayerY != playerY) {
			lastMap = worlds.get(lastPlayerY, lastPlayerX);
			lastMap.draw(g);
//            System.out.println("last map draw");
        }
        current.draw(g);
	} // draw
	public void advanceDialogue() {
		if(current.getTypewriter() != null &&   current.getTypewriter().isAnimate())
			current.advanceDialogue();
	}
	public TileMap get(int y, int x) {
		return worlds.get(y, x);
	}
	public TileMap getCurrent() {
		return current;
	}

	public int getPlayerX() {
		return playerX;
	}

	public void setPlayerX(int playerX) {
		this.playerX = playerX;
	}

	public int getPlayerY() {
		return playerY;
	}

	public void setPlayerY(int playerY) {
		this.playerY = playerY;
	}

	public int getLastPlayerX() {
		return lastPlayerX;
	}

	public void setLastPlayerX(int lastPlayerX) {
		this.lastPlayerX = lastPlayerX;
	}

	public int getLastPlayerY() {
		return lastPlayerY;
	}

	public void setLastPlayerY(int lastPlayerY) {
		this.lastPlayerY = lastPlayerY;
	}
} // World

