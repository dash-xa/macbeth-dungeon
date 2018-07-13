import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class TileMap {

	// location
	private int xStartDrawTileMap;
	private int yStartDrawTileMap;

	private int tileSize;

	// node map
	private Node[][] nodeMap;

	// map properties
	private int mapWidth;
	private int mapHeight;

	public ArrayList<Enemy> enemies;

	private boolean isCleared;

	// change stage
	private boolean changed;
	private boolean changeStageNew;
	private boolean changeStageCurrent;
	private boolean moveToStageLeft;
	private boolean moveToStageRight;
	private boolean moveToStageUp;
	private boolean moveToStageDown;
	private boolean hasEnemies;
	private boolean entranceCreated;

	private int worldXPos;
	private int worldYPos;

	private double CHANGE_STAGE_SPEED = 30;

	// images
	private BufferedImage tile = null;
	private BufferedImage floor = null;
	private BufferedImage door1 = null;
	private BufferedImage door2 = null;
	private BufferedImage door3 = null;
	private BufferedImage door4 = null;
	private BufferedImage grass = null;
	private BufferedImage tree = null;
	private BufferedImage castlefloor = null;
	private BufferedImage castlewall = null;
    private BufferedImage macduffcastlefloor = null;
    private BufferedImage macduffcastlewall = null;
	private BufferedImage darkGrass = null;
	private BufferedImage darkTree = null;

	// counter
	private int counter = 0;

	private Typewriter typewriter;

	public Node[][] getNodeMap() {
		return nodeMap;
	}

    public void setHasEnemies(boolean hasEnemies) {
        this.hasEnemies = hasEnemies;
    }

    public TileMap(int[][] array) {

		// set attributes
		this.nodeMap = arrayToNode(array);

		this.xStartDrawTileMap = 0;
		this.yStartDrawTileMap = 0;
		this.mapHeight = 20;
		this.mapWidth = 20;
		this.tileSize = 50;
		this.moveToStageLeft = false;
		this.moveToStageRight = false;
		this.moveToStageUp = false;
		this.moveToStageDown = false;
		this.changeStageNew = false;
		this.changeStageCurrent = false;
		this.changed = false;
		this.enemies = new ArrayList<>();
		this.isCleared = false;
        this.hasEnemies = false;
        this.entranceCreated = false;

	} // TileMap

	public TileMap(int[][] array, Typewriter t) {
		this(array);
		typewriter = t;
	}
	public TileMap(int[][] array, Dialogue[] d) {
		this(array);
		typewriter = new Typewriter(d);
	}

	public void update() {

		if (this.changeStageNew) {

			if (this.moveToStageLeft) {
				this.xStartDrawTileMap += 30;

				if (this.xStartDrawTileMap > 0) {
					this.xStartDrawTileMap = 0;
					resetStageChange();
					this.changeStageNew = false;
					this.changed = true;

				}

			} else if (this.moveToStageRight) {
				this.xStartDrawTileMap -= 30;

				if (this.xStartDrawTileMap < 0) {
					this.xStartDrawTileMap = 0;
					resetStageChange();
					this.changeStageNew = false;
					this.changed = true;

				}
			}

			if (this.moveToStageUp) {

				this.yStartDrawTileMap += 30;
				if (this.yStartDrawTileMap > 0) {
					this.yStartDrawTileMap = 0;
					resetStageChange();
					this.changeStageNew = false;
					this.changed = true;
				}

			} else if (this.moveToStageDown) {
				this.yStartDrawTileMap -= 30;

				if (this.yStartDrawTileMap < 0) {
					this.yStartDrawTileMap = 0;
					resetStageChange();
					this.changeStageNew = false;
					this.changed = true;

				}
			}
		}
		// stage is being changed
		if (this.changeStageCurrent) {
			if (this.moveToStageLeft) {
				this.xStartDrawTileMap += 30;

				if (this.xStartDrawTileMap > 1000) {
					this.xStartDrawTileMap = 1000;
					resetStageChange();
					this.changeStageCurrent = false;

				}

			} else if (this.moveToStageRight) {
				this.xStartDrawTileMap -= 30;

				if (this.xStartDrawTileMap < -1000) {
					this.xStartDrawTileMap = -1000;
					resetStageChange();
					this.changeStageCurrent = false;

				}
			}

			if (this.moveToStageUp) {
				this.yStartDrawTileMap += CHANGE_STAGE_SPEED;
				if (this.yStartDrawTileMap > 1000) {
					this.yStartDrawTileMap = 1000;
					resetStageChange();
					this.changeStageCurrent = false;

				}

			} else if (this.moveToStageDown) {
				this.yStartDrawTileMap -= 30;

				if (this.yStartDrawTileMap < -1000) {
					this.yStartDrawTileMap = -1000;
					resetStageChange();
					this.changeStageCurrent = false;
				}
			}
		}


	} // update

	public void draw(Graphics2D g) {

		while (counter == 0) {
			try {
				tile = ImageIO.read(new File("src/sprites/tile.png"));
			} catch (IOException e) {
				System.out.println("IOException");
			}

			try {
				floor = ImageIO.read(new File("src/sprites/brick.png"));
			} catch (IOException e) {
				System.out.println("IOException");
			}

			try {
				door1 = ImageIO.read(new File("src/sprites/door1.png"));
			} catch (IOException e) {
				System.out.println("IOException");
			}
			try {
				door2 = ImageIO.read(new File("src/sprites/door2.png"));
			} catch (IOException e) {
				System.out.println("IOException");
			}
			try {
				door3 = ImageIO.read(new File("src/sprites/door3.png"));
			} catch (IOException e) {
				System.out.println("IOException");
			}
			try {
				door4 = ImageIO.read(new File("src/sprites/door4.png"));
			} catch (IOException e) {
				System.out.println("IOException");
			}
			try {
				grass = ImageIO.read(new File("src/sprites/grass.png"));
			} catch (IOException e) {
				System.out.println("IOException");
			}
			try {
				tree = ImageIO.read(new File("src/sprites/tree.png"));
			} catch (IOException e) {
				System.out.println("IOException");
			}
			try {
				castlefloor = ImageIO.read(new File("src/sprites/castlefloor.png"));
			} catch (IOException e) {
				System.out.println("IOException");
			}
			try {
				castlewall = ImageIO.read(new File("src/sprites/castlewall.png"));
			} catch (IOException e) {
				System.out.println("IOException");
			}
			try {
				darkGrass = ImageIO.read(new File("src/sprites/darkgrass.png"));
			} catch (IOException e) {
				System.out.println("IOException");
			}
			try {
				darkTree = ImageIO.read(new File("src/sprites/darktree.png"));
			} catch (IOException e) {
				System.out.println("IOException");
			}
			counter++;
		}


		for (int row = 0; row < this.mapHeight; row++) {
			for (int col = 0; col < this.mapWidth; col++) {
				int rc = this.nodeMap[row][col].getType();
				g.setColor(Color.BLACK);

				// draw background
				if (rc == 0)
					g.drawImage(floor, xStartDrawTileMap + col * tileSize, yStartDrawTileMap + row * tileSize, null);
				//Horizontal Wall
				if (rc == 1)
					g.drawImage(tile, xStartDrawTileMap + col * tileSize, yStartDrawTileMap + row * tileSize, null);
				if (rc == 2)
					g.drawImage(door4, xStartDrawTileMap + col * tileSize, yStartDrawTileMap + row * tileSize, null);
				if (rc == 3)
					g.drawImage(grass, xStartDrawTileMap + col * tileSize, yStartDrawTileMap + row * tileSize, null);
				if (rc == 4)
					g.drawImage(door1, xStartDrawTileMap + col * tileSize, yStartDrawTileMap + row * tileSize, null);
				if (rc == 5)
					g.drawImage(tree, xStartDrawTileMap + col * tileSize, yStartDrawTileMap + row * tileSize, null);
				if (rc == 6)
					g.drawImage(door2, xStartDrawTileMap + col * tileSize, yStartDrawTileMap + row * tileSize, null);
				if (rc == 8)
					g.drawImage(door3, xStartDrawTileMap + col * tileSize, yStartDrawTileMap + row * tileSize, null);
				if (rc == 11)
					g.drawImage(castlefloor, xStartDrawTileMap + col * tileSize, yStartDrawTileMap + row * tileSize, null);
				if (rc == 12)
					g.drawImage(castlewall, xStartDrawTileMap + col * tileSize, yStartDrawTileMap + row * tileSize, null);
				if (rc == 13)
					g.drawImage(darkGrass, xStartDrawTileMap + col * tileSize, yStartDrawTileMap + row * tileSize, null);
				if (rc == 14)
					g.drawImage(darkTree, xStartDrawTileMap + col * tileSize, yStartDrawTileMap + row * tileSize, null);
				if (rc == 7) {

					g.setColor(Color.white);
					g.setFont(new Font("TimesRoman", Font.PLAIN, 28));
					g.drawString("Act Example Scene Example", xStartDrawTileMap + 5 * tileSize, yStartDrawTileMap + row * tileSize );

				}
				if (rc == 9) {

                    g.drawImage(macduffcastlefloor, xStartDrawTileMap + col * tileSize, yStartDrawTileMap + row * tileSize, null);


                }

				if (rc == 10) {

                    g.drawImage(macduffcastlewall, xStartDrawTileMap + col * tileSize, yStartDrawTileMap + row * tileSize, null);


                }
			}
		}
		if (typewriter != null && typewriter.isAnimate()) // remove
			typewriter.draw(g);
	} // draw

	public void advanceDialogue() {
		typewriter.advance();
	}

	public Node[][] arrayToNode(int[][] array) {
		int size = array.length;

		Node[][] nodeMap = new Node[size][size];
		for (int row = 0; row < array.length; row++) {
			for (int col = 0; col < array[0].length; col++) {
				nodeMap[row][col] = new Node(array[row][col], row, col);
			}
		}

		for (int row = 0; row < array.length; row++) {
			for (int col = 0; col < array[0].length; col++) {
				nodeMap[row][col].addNeighbors(nodeMap);
			}
		}

		return nodeMap;
	}

	public Node[][] getMap() {
		return this.nodeMap;
	}

	public int getX() {
		return xStartDrawTileMap;
	}

	public int getY() {
		return yStartDrawTileMap;
	}

	public int getColTile(int xStartDrawTileMap) {
		return xStartDrawTileMap / tileSize;
	}

	public int getRowTile(int yStartDrawTileMap) {
		return yStartDrawTileMap / tileSize;
	}

	public int getTile(int row, int column) {
		return nodeMap[row][column].getType();
	}

	public Typewriter getTypewriter() {
		return typewriter;
	}

	public int getTileSize() {
		return tileSize;
	}

	public void setX(int xStartDrawTileMap) {
		this.xStartDrawTileMap = xStartDrawTileMap;
	}

	public void setY(int yStartDrawTileMap) {
		this.yStartDrawTileMap = yStartDrawTileMap;
	}

	public int getWorldXPos() {
		return worldXPos;
	}

	public void setWorldXPos(int worldXPos) {
		this.worldXPos = worldXPos;
	}

	public int getWorldYPos() {
		return worldYPos;
	}

	public void setWorldYPos(int worldYPos) {
		this.worldYPos = worldYPos;
	}

	public void setWorldPos(int xStartDrawTileMap, int yStartDrawTileMap) {
		this.worldXPos = xStartDrawTileMap;
		this.worldYPos = yStartDrawTileMap;
	}

	public void setLocationChange() {

		if (this.moveToStageRight) {
			this.xStartDrawTileMap = 1000;

		} else if (this.moveToStageLeft) {
			this.xStartDrawTileMap = -1000;
		}

		if (this.moveToStageDown) {
			this.yStartDrawTileMap = 1000;

		} else if (this.moveToStageUp) {
			this.yStartDrawTileMap = -1000;
		}

	}

	public void resetStageChange() {
		this.moveToStageUp = false;
		this.moveToStageDown = false;
		this.moveToStageLeft = false;
		this.moveToStageRight = false;
	}

	public void setTypewriter(Typewriter typewriter) {
		this.typewriter = typewriter;
	}

	public void setChangeStageNew(boolean changeStageNew) {
		this.changeStageNew = changeStageNew;
	}

	public void setChangeStageCurrent(boolean changeStageCurrent) {
		this.changeStageCurrent = changeStageCurrent;
	}

	public void setMoveToStageLeft(boolean moveToStageLeft) {
		this.moveToStageLeft = moveToStageLeft;
	}

	public void setMoveToStageRight(boolean moveToStageRight) {
		this.moveToStageRight = moveToStageRight;
	}

	public void setMoveToStageUp(boolean moveToStageUp) {
		this.moveToStageUp = moveToStageUp;
	}

	public void setMoveToStageDown(boolean moveToStageDown) {
		this.moveToStageDown = moveToStageDown;
	}

	public boolean isChanged() {
		return changed;
	}

	public boolean isChangeStageCurrent() {
		return this.changeStageCurrent;
	}

	public void setChanged(boolean changed) {
		this.changed = changed;
	}

	public double getCHANGE_STAGE_SPEED() {
		return CHANGE_STAGE_SPEED;
	}

	public Node getNode(int locationY, int locationX) {
		return this.getNodeMap()[locationY][locationX];
	}

	public boolean isCleared() {
		return isCleared;
	}

	public void setCleared(boolean cleared) {
		isCleared = cleared;
	}

    public boolean isHasEnemies() {
        return hasEnemies;
    }

    public void createEntranceLeft(){
	    this.nodeMap[9][0].setType(2);
	    this.nodeMap[10][0].setType(2);
    }
    public void createEntranceRight(){
        this.nodeMap[9][19].setType(6);
        this.nodeMap[10][19].setType(6);
    }

    public void createEntranceTop(){
        this.nodeMap[0][9].setType(4);
        this.nodeMap[0][10].setType(4);
    }

    public void createEntranceBottom(){
        this.nodeMap[19][9].setType(8);
        this.nodeMap[19][10].setType(8);
    }

    public boolean isEntranceCreated() {
        return entranceCreated;
    }

    public void setEntranceCreated(boolean entranceCreated) {
        this.entranceCreated = entranceCreated;
    }

    public void createEntrances(World world){
        if (this.getWorldYPos() -1 >= 0 && world.get(this.getWorldYPos() - 1, this.getWorldXPos()) != null ){
            createEntranceTop();
            entranceCreated = true;
        }

        if ( this.getWorldYPos() + 1 < world.getWorldLength() && world.get(this.getWorldYPos() + 1, this.getWorldXPos()) != null ){
            createEntranceBottom();
            entranceCreated = true;

        }

        if (this.getWorldXPos() + 1 < world.getWorldLength() && world.get(this.getWorldYPos(), this.getWorldXPos() + 1) != null ){
            createEntranceRight();
            entranceCreated = true;
        }

        if (this.getWorldXPos() - 1 >= 0 && world.get(this.getWorldYPos(), this.getWorldXPos() - 1) != null ){

            createEntranceLeft();
            entranceCreated = true;

        }
    }
} // TileMap
