
import javax.imageio.ImageIO;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;


public class Enemy {

    private double x; // x location
    private double y; // y location

    private double dx; // x speed
    private double dy; // y speed
    private double speed; // speed

    private int health; // enemy health
    private int type; // enemy type
    private int difficulty; // enemy level/difficulty
    private int radius; // radius
    private boolean canShoot;


    private long resetPathTime; // reset time
    private long resetPathDelay; // delay
    private long firingTimer; // timer
    private long firingDelay; // delay


    public int getHieght() {
        return height;
    }

    public int getWidth() {
        return width;
    }

    private int height;
    private int width;


    private boolean initialized;
    private int currentFrame; // current frame
    private int frame; // frames

    private boolean ready; //
    private boolean dead;
    private boolean pathDone;
    private boolean showPaths;

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

    private boolean isKnight;
    private boolean isBanqou;
    private boolean isBanqouQhost;
    private boolean isDuncan;
    private boolean isLadyMacduff;
    private boolean isLadyMacbeth;
    private boolean isMacduffSon;
    private boolean isMacduff;
    private boolean isYoungSiward;
    private boolean isWitches;

    private TileMap tileMap;
    private Node startNode; // ENEMY POSITION

    private Node endNode; // PLAYER POSITION
    private Node current; // ??


    private BufferedImage[] knightLeftImage = new BufferedImage[4];
    private BufferedImage[] knightRightImage = new BufferedImage[4];
    private BufferedImage[] knightUpImage = new BufferedImage[4];
    private BufferedImage[] knightDownImage = new BufferedImage[4];

    private BufferedImage[] duncanLeftImage = new BufferedImage[6];
    private BufferedImage[] duncanRightImage = new BufferedImage[6];
    private BufferedImage[] duncanUpImage = new BufferedImage[6];
    private BufferedImage[] duncanDownImage = new BufferedImage[6];

    private BufferedImage[] banquoLeftImage = new BufferedImage[6];
    private BufferedImage[] banquoRightImage = new BufferedImage[6];
    private BufferedImage[] banquoUpImage = new BufferedImage[6];
    private BufferedImage[] banquoDownImage = new BufferedImage[6];

    private BufferedImage banquoGhost;

    private BufferedImage[] ladyMacduffLeftImage = new BufferedImage[6];
    private BufferedImage[] ladyMacduffRightImage = new BufferedImage[6];
    private BufferedImage[] ladyMacduffUpImage = new BufferedImage[6];
    private BufferedImage[] ladyMacduffDownImage = new BufferedImage[6];

    private BufferedImage[] macduffSonLeftImage = new BufferedImage[6];
    private BufferedImage[] macduffSonRightImage = new BufferedImage[6];
    private BufferedImage[] macduffSonUpImage = new BufferedImage[6];
    private BufferedImage[] macduffSonDownImage = new BufferedImage[6];

    private BufferedImage[] youngSiwardLeftImage = new BufferedImage[6];
    private BufferedImage[] youngSiwardRightImage = new BufferedImage[6];
    private BufferedImage[] youngSiwardUpImage = new BufferedImage[6];
    private BufferedImage[] youngSiwardDownImage = new BufferedImage[6];

    private BufferedImage[] macduffLeftImage = new BufferedImage[6];
    private BufferedImage[] macduffRightImage = new BufferedImage[6];
    private BufferedImage[] macduffUpImage = new BufferedImage[6];
    private BufferedImage[] macduffDownImage = new BufferedImage[6];

    private BufferedImage[] ladyMacbethLeftImage = new BufferedImage[6];
    private BufferedImage[] ladyMacbethRightImage = new BufferedImage[6];
    private BufferedImage[] ladyMacbethUpImage = new BufferedImage[6];
    private BufferedImage[] ladyMacbethDownImage = new BufferedImage[6];

    private BufferedImage witch1;
    private BufferedImage witch2;
    private BufferedImage witch3;

    private ArrayList<Node> path;
    private ArrayList<Node> lastPath;
    private ArrayList<Node> nodeNeighbors;
    private ArrayList<Node> closedSet;
    private ArrayList<Node> openSet;
    private Player player;
    private Node next;

    public boolean isShowPaths() {
        return showPaths;
    }

    public void setShowPaths(boolean showPaths) {
        this.showPaths = showPaths;
    }

    public Enemy(int type, int difficulty, TileMap tileMap, Player player, double x, double y) {

        // set attributes

        this.type = type;
        this.difficulty = difficulty;
        this.ready = false;
        this.x = x;
        this.y = y;
        this.dx = 0;
        this.dy = 0;
        this.dead = false;
        this.tileMap = tileMap;
        this.tileMap.setHasEnemies(true);
        this.player = player;
        this.startNode = tileMap.getNode((int) (this.y / 50), (int) (this.x / 50));
        this.endNode = tileMap.getNode((int) (player.getY() / 50), (int) (player.getX() / 50));
        this.closedSet = new ArrayList<>();
        this.openSet = new ArrayList<>();
        this.openSet.add(this.startNode);
        this.nodeNeighbors = new ArrayList<>();
        this.path = new ArrayList<>();
        this.pathDone = false;
        this.current = tileMap.getNode((int) (this.y / 50), (int) (this.x / 50));
        this.resetPathTime = System.nanoTime();
        this.resetPathDelay = 2000;
        this.canShoot = true;
        this.firingDelay = 1000;
        this.firingTimer = System.nanoTime();
        this.initialized = false;
        this.height = 44;
        this.width = 25;
        this.showPaths = false;
        this.lastPath = new ArrayList<>();
        isKnight = false;
        isDuncan = false;
        isBanqou = false;
        isBanqouQhost = false;
        isLadyMacduff = false;
        isMacduff = false;
        isMacduffSon = false;
        isYoungSiward = false;
        isLadyMacbeth = false;
        isWitches = false;
        currentFrame = 0;
        frame = 0;

        // enemy type
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
        9 - witches
        */

        if (type == 0) {
            isKnight = true;
        } else if (type == 1) {
            isDuncan = true;
        } else if (type == 2) {
            isBanqou = true;
        } else if (type == 3) {
            isBanqouQhost = true;
        } else if (type == 4) {
            isLadyMacduff = true;
        } else if (type == 5) {
            isMacduffSon = true;
        } else if (type == 6) {
            isYoungSiward = true;
        } else if (type == 7) {
            isMacduff = true;
        } else if (type == 8) {
            isLadyMacbeth = true;
            canShoot = false;
        } else if (type == 9){
            isWitches = true;
            canShoot = false;
        }

        if (difficulty == 0) {
            speed = 0;
            health = 2;
        } else if (difficulty == 1) {
            speed = 3;
            radius = 15;
            health = 5;
        }  else if (difficulty == 2) {
            speed = 5;
            radius = 15;
            health = 6;
        } else if (difficulty == 3) {
            speed = 6;
            radius = 15;
            health = 7;
        }else if (difficulty == 4) {
            speed = 7;
            radius = 15;
            health = 8;
        }else if (difficulty == 5) {
            speed = 10;
            radius = 15;
            health = 1000000;
        }


    }

    public int getRadius() {
        return radius;
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public boolean isCanShoot() {
        return canShoot;
    }

    public int getDifficulty() {
        return difficulty;
    }

    public int getType() {

        return type;
    }

    public void update() {
        Typewriter t = tileMap.getTypewriter();
        if (t != null && t.isAnimate()) {
            return;
        }
        // a start algorithm
        path = generatePath();

        if (pathDone) {

            if (!path.isEmpty()) {
                lastPath = path;
            }

            if (path.size() <= getPathIndex(path, startNode)) {

                this.dx = 0;
                this.dy = 0;

            } else {

                next = path.get(getPathIndex(path, startNode));

                dx = speed * Math.signum(next.getxIndex() - startNode.getxIndex());
                dy = speed * Math.signum(next.getyIndex() - startNode.getyIndex());

                if (next.getxIndex() - startNode.getxIndex() == 0) {
                    dx = 0;
                }
                if (next.getyIndex() - startNode.getyIndex() == 0) {
                    dy = 0;
                }

                if (next.compareTo(endNode) == 0) {
                    this.dx = 0;
                    this.dy = 0;

                }

            }

        } else {

            if (lastPath.size() <= getPathIndex(lastPath, startNode)) {


            } else {

                next = lastPath.get(getPathIndex(lastPath, startNode));

                dx = speed * Math.signum(next.getxIndex() - startNode.getxIndex());
                dy = speed * Math.signum(next.getyIndex() - startNode.getyIndex());

                if (next.getxIndex() - startNode.getxIndex() == 0) {
                    dx = 0;
                }
                if (next.getyIndex() - startNode.getyIndex() == 0) {
                    dy = 0;
                }

                if (next.compareTo(lastPath.get(lastPath.size() - 1)) == 0) {
                    for (int i = 0; i < openSet.size(); i++) {
                        openSet.get(i).setPrevious(null);
                    }
                    for (int i = 0; i < closedSet.size(); i++) {
                        closedSet.get(i).setPrevious(null);
                    }

                    openSet.clear();
                    closedSet.clear();
                    path.clear();
                    lastPath.clear();
                    this.dx = 0;
                    this.dy = 0;
                }


            }
        }

        // shooting
        long elapsedShooting = (System.nanoTime() - firingTimer) / 1000000;
        if (canShoot && (startNode.getxIndex() == endNode.getxIndex() || startNode.getyIndex() == endNode.getyIndex()) && elapsedShooting > firingDelay) {
            double dx = this.getX() - player.getX();
            double dy = this.getY() - player.getY();
            dx *= -1; dy *= -1;
            double d = Math.sqrt(dx * dx + dy * dy);
            firingTimer = System.nanoTime();
            GamePanel.shotsEnemy.add(new Shot(this, this.x + radius, this.y + radius, getVelocity(dx, d, 4), getVelocity(dy, d, 4)));
        }


        // hit player
        for (int i = 0; i < GamePanel.shotsEnemy.size(); i++) {
            Shot shot = GamePanel.shotsEnemy.get(i);

            int shotX = (int) (shot.getX());
            int shotY = (int) (shot.getY());
            int value = tileMap.getTile(tileMap.getRowTile(shotY + (int) shot.getDy()), tileMap.getColTile(shotX + (int) shot.getDx()));
            if (value == 1 || value == 2 || value == 4 || value == 5 || value == 6 || value == 8 || value == 12 || value == 14) {
                GamePanel.shotsEnemy.remove(i);
            }

            if (((shotX + shot.getRadius()) - player.getX() > 0) && (shotX - (player.getX() + player.getWidth()) < 0) && ((shotY + shot.getRadius()) - player.getY() > 0)
                    && (shotY - (player.getY() + player.getHeight()) < 0) && tileMap != null && tileMap.getTypewriter() != null && tileMap.getTypewriter().hasBeenActivated && !tileMap.getTypewriter().isAnimate()) {
                GamePanel.shotsEnemy.remove(shot);
                player.loseLife();
                sound("playerhit.wav");
            }
        }

        this.x += this.dx;
        this.y += this.dy;
    } // update

    private double getVelocity(double dx, double d, double v) {
        return v * dx / d;
    }

    private int getPathIndex(ArrayList<Node> path, Node current) {
        return path.indexOf(current) + 1;
    }

    private Node getNode(double x, double y) {
        return tileMap.getNode((int) (y / 50), (int) (x / 50));
    }

    private ArrayList<Node> generatePath() {

        this.startNode = tileMap.getNode((int) (this.y / 50), (int) (this.x / 50));
        this.endNode = tileMap.getNode((int) (player.getY() / 50), (int) (player.getX() / 50));

        if (this.openSet.size() < 1) {
            this.openSet.add(this.startNode);
        }

        if (this.openSet.size() > 0) {

            pathDone = false;
            int lowestF = 0;

            for (int i = 0; i < openSet.size(); i++) {
                if (openSet.get(i).getF() < openSet.get(lowestF).getF()) {
                    lowestF = i;
                }
            }

            current = openSet.get(lowestF);

            if (current.compareTo(this.endNode) == 0) {

                path = new ArrayList<>();
                while (current.getPrevious() != null) {
                    path.add(0, current);
                    current = current.getPrevious();
                }

                pathDone = true;
            }

            openSet.remove(current);
            closedSet.add(current);

            nodeNeighbors = current.getNeighbors();

            for (int i = 0; i < nodeNeighbors.size(); i++) {

                Node neighbor = nodeNeighbors.get(i);

                if (!closedSet.contains(neighbor) && !neighbor.isWall()) {
                    int tempG = current.getG() + 1;

                    if (!openSet.contains(neighbor)) {
                        neighbor.setPrevious(current);
                        neighbor.setH(heuristic(neighbor, endNode));
                        neighbor.setG(tempG);
                        neighbor.setF(neighbor.getG() + neighbor.getH());
                        openSet.add(neighbor);

                    } else if (tempG < neighbor.getG()) {
                        neighbor.setPrevious(current);
                        neighbor.setG(tempG);
                    }
                }

            }

        } else {

        }

        // check new path

        long elapsedNewPath = (System.nanoTime() - resetPathTime) / 1000000;
        if ( /*&& elapsedNewPath > resetPathDelay*/
                closedSet.contains(this.endNode) || next != null && next.compareTo(endNode) == 0) {

            /*
            for (int i = 0; i < lastPath.size(); i++){
                System.out.println(lastPath.get(i));
            }
            */

            for (int i = 0; i < openSet.size(); i++) {
                openSet.get(i).setPrevious(null);
            }
            for (int i = 0; i < closedSet.size(); i++) {
                closedSet.get(i).setPrevious(null);
            }

            /*
            for (int i = 0; i <  path.size(); i++){
                path.get(i).setPrevious(null);
            }
path.clear();
*/
            openSet.clear();
            closedSet.clear();

            pathDone = false;
            resetPathTime = System.nanoTime();
        }
        return path;
    }

    // load images
    public void init() {

        if (isKnight) {

            for (int i = 0; i < 4; i++) {
                try {
                    knightRightImage[i] = ImageIO.read(new File("src/sprites/knight/" + (i + 1) + ".png"));
                } catch (IOException e) {
                    System.out.println("IOException");
                }
            }
            for (int i = 0; i < 4; i++) {
                try {
                    knightUpImage[i] = ImageIO.read(new File("src/sprites/knight/" + (i + 5) + ".png"));
                } catch (IOException e) {
                    System.out.println("IOException");
                }
            }
            for (int i = 0; i < 4; i++) {
                try {
                    knightDownImage[i] = ImageIO.read(new File("src/sprites/knight/" + (i + 9) + ".png"));
                } catch (IOException e) {
                    System.out.println("IOException");
                }
            }
            for (int i = 0; i < 4; i++) {
                try {
                    knightLeftImage[i] = ImageIO.read(new File("src/sprites/knight/" + (i + 13) + ".png"));
                } catch (IOException e) {
                    System.out.println("IOException");
                }
            }

        } else if (isDuncan) {

            for (int i = 0; i < 6; i++) {
                try {
                    duncanRightImage[i] = ImageIO.read(new File("src/sprites/duncanright" + (i + 1) + ".png"));
                } catch (IOException e) {
                    System.out.println("IOException");
                }
            }
            for (int i = 0; i < 6; i++) {
                try {
                    duncanUpImage[i] = ImageIO.read(new File("src/sprites/duncanback" + (i + 1) + ".png"));
                } catch (IOException e) {
                    System.out.println("IOException");
                }
            }
            for (int i = 0; i < 6; i++) {
                try {
                    duncanDownImage[i] = ImageIO.read(new File("src/sprites/duncanfront" + (i + 1) + ".png"));
                } catch (IOException e) {
                    System.out.println("IOException");
                }
            }
            for (int i = 0; i < 6; i++) {
                try {
                    duncanLeftImage[i] = ImageIO.read(new File("src/sprites/duncanleft" + (i + 1) + ".png"));
                } catch (IOException e) {
                    System.out.println("IOException");
                }
            }
        } else if (isBanqou) {
            for (int i = 0; i < 6; i++) {
                try {
                    banquoRightImage[i] = ImageIO.read(new File("src/sprites/banquoright" + (i + 1) + ".png"));
                } catch (IOException e) {
                    System.out.println("IOException");
                }
            }
            for (int i = 0; i < 6; i++) {
                try {
                    banquoUpImage[i] = ImageIO.read(new File("src/sprites/banquoback" + (i + 1) + ".png"));
                } catch (IOException e) {
                    System.out.println("IOException");
                }
            }
            for (int i = 0; i < 6; i++) {
                try {
                    banquoDownImage[i] = ImageIO.read(new File("src/sprites/banquofront" + (i + 1) + ".png"));
                } catch (IOException e) {
                    System.out.println("IOException");
                }
            }
            for (int i = 0; i < 6; i++) {
                try {
                    banquoLeftImage[i] = ImageIO.read(new File("src/sprites/banquoleft" + (i + 1) + ".png"));
                } catch (IOException e) {
                    System.out.println("IOException");
                }
            }
        } else if (isBanqouQhost) {

            try {
                banquoGhost = ImageIO.read(new File("src/sprites/banquoghostfront1.png"));
            } catch (IOException e) {
                System.out.println("IOException");

            }
        } else if (isLadyMacduff) {
            for (int i = 0; i < 6; i++) {
                try {
                    ladyMacduffRightImage[i] = ImageIO.read(new File("src/sprites/ladymacduffright" + (i + 1) + ".png"));
                } catch (IOException e) {
                    System.out.println("IOException");
                }
            }
            for (int i = 0; i < 6; i++) {
                try {
                    ladyMacduffUpImage[i] = ImageIO.read(new File("src/sprites/ladymacduffback" + (i + 1) + ".png"));
                } catch (IOException e) {
                    System.out.println("IOException");
                }
            }
            for (int i = 0; i < 6; i++) {
                try {
                    ladyMacduffDownImage[i] = ImageIO.read(new File("src/sprites/ladymacdufffront" + (i + 1) + ".png"));
                } catch (IOException e) {
                    System.out.println("IOException");
                }
            }
            for (int i = 0; i < 6; i++) {
                try {
                    ladyMacduffLeftImage[i] = ImageIO.read(new File("src/sprites/ladymacduffleft" + (i + 1) + ".png"));
                } catch (IOException e) {
                    System.out.println("IOException");
                }
            }
        } else if (isMacduffSon) {
            for (int i = 0; i < 6; i++) {
                try {
                    macduffSonRightImage[i] = ImageIO.read(new File("src/sprites/macduffsonright" + (i + 1) + ".png"));
                } catch (IOException e) {
                    System.out.println("IOException");
                }
            }
            for (int i = 0; i < 6; i++) {
                try {
                    macduffSonUpImage[i] = ImageIO.read(new File("src/sprites/macduffsonback" + (i + 1) + ".png"));
                } catch (IOException e) {
                    System.out.println("IOException");
                }
            }
            for (int i = 0; i < 6; i++) {
                try {
                    macduffSonDownImage[i] = ImageIO.read(new File("src/sprites/macduffsonfront" + (i + 1) + ".png"));
                } catch (IOException e) {
                    System.out.println("IOException");
                }
            }
            for (int i = 0; i < 6; i++) {
                try {
                    macduffSonLeftImage[i] = ImageIO.read(new File("src/sprites/macduffsonleft" + (i + 1) + ".png"));
                } catch (IOException e) {
                    System.out.println("IOException");
                }
            }
        } else if (isYoungSiward) {
            for (int i = 0; i < 6; i++) {
                try {
                    youngSiwardRightImage[i] = ImageIO.read(new File("src/sprites/youngsiwardright" + (i + 1) + ".png"));
                } catch (IOException e) {
                    System.out.println("IOException");
                }
            }
            for (int i = 0; i < 6; i++) {
                try {
                    youngSiwardUpImage[i] = ImageIO.read(new File("src/sprites/youngsiwardback" + (i + 1) + ".png"));
                } catch (IOException e) {
                    System.out.println("IOException");
                }
            }
            for (int i = 0; i < 6; i++) {
                try {
                    youngSiwardDownImage[i] = ImageIO.read(new File("src/sprites/youngsiwardfront" + (i + 1) + ".png"));
                } catch (IOException e) {
                    System.out.println("IOException");
                }
            }
            for (int i = 0; i < 6; i++) {
                try {
                    youngSiwardLeftImage[i] = ImageIO.read(new File("src/sprites/youngsiwardleft" + (i + 1) + ".png"));
                } catch (IOException e) {
                    System.out.println("IOException");
                }
            }
        } else if (isMacduff) {
            for (int i = 0; i < 6; i++) {
                try {
                    macduffRightImage[i] = ImageIO.read(new File("src/sprites/macduffright" + (i + 1) + ".png"));
                } catch (IOException e) {
                    System.out.println("IOException");
                }
            }
            for (int i = 0; i < 6; i++) {
                try {
                    macduffUpImage[i] = ImageIO.read(new File("src/sprites/macduffback" + (i + 1) + ".png"));
                } catch (IOException e) {
                    System.out.println("IOException");
                }
            }
            for (int i = 0; i < 6; i++) {
                try {
                    macduffDownImage[i] = ImageIO.read(new File("src/sprites/macdufffront" + (i + 1) + ".png"));
                } catch (IOException e) {
                    System.out.println("IOException");
                }
            }
            for (int i = 0; i < 6; i++) {
                try {
                    macduffLeftImage[i] = ImageIO.read(new File("src/sprites/macduffleft" + (i + 1) + ".png"));
                } catch (IOException e) {
                    System.out.println("IOException");
                }
            }

        } else if (isLadyMacbeth) {
            for (int i = 0; i < 6; i++) {
                try {
                    ladyMacbethRightImage[i] = ImageIO.read(new File("src/sprites/ladymacbethright" + (i + 1) + ".png"));
                } catch (IOException e) {
                    System.out.println("IOException");
                }
            }
            for (int i = 0; i < 6; i++) {
                try {
                    ladyMacbethUpImage[i] = ImageIO.read(new File("src/sprites/ladymacbethback" + (i + 1) + ".png"));
                } catch (IOException e) {
                    System.out.println("IOException");
                }
            }
            for (int i = 0; i < 6; i++) {
                try {
                    ladyMacbethDownImage[i] = ImageIO.read(new File("src/sprites/ladymacbethfront" + (i + 1) + ".png"));
                } catch (IOException e) {
                    System.out.println("IOException");
                }
            }
            for (int i = 0; i < 6; i++) {
                try {
                    ladyMacbethLeftImage[i] = ImageIO.read(new File("src/sprites/ladymacbethleft" + (i + 1) + ".png"));
                } catch (IOException e) {
                    System.out.println("IOException");
                }
            }
        } else if (isWitches){
            try {
                witch1 = ImageIO.read(new File("src/sprites/witch1.png"));
            } catch (IOException e) {
                System.out.println("IOException");

            }
            try {
                witch2 = ImageIO.read(new File("src/sprites/witch2.png"));
            } catch (IOException e) {
                System.out.println("IOException");

            }
            try {
                witch3 = ImageIO.read(new File("src/sprites/witch3.png"));
            } catch (IOException e) {
                System.out.println("IOException");

            }
        }


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


        /*
        for (int i = 0; i < closedSet.size(); i++) {
            g.setColor(Color.red);
            closedSet.get(i).draw(g);
        }

        for (int i = 0; i < openSet.size(); i++) {
            g.setColor(Color.green);
            openSet.get(i).draw(g);
        }
        for (int i = 0; i < lastPath.size(); i++) {
            g.setColor(Color.orange);
            lastPath.get(i).draw(g);
        }

        for (int i = 0; i < path.size(); i++) {
            g.setColor(Color.blue);
            path.get(i).draw(g);
            g.setColor(Color.red);
        }


        /*

        g.setColor(Color.black);
        if (next != null)
            next.draw(g);

        g.setColor(Color.cyan);
        startNode.draw(g);

        g.setColor(Color.WHITE);
        endNode.draw(g);

        */

        if (health > 0) {
            if (isKnight) {

                if (this.dx < 0) {
                    g.drawImage(knightLeftImage[currentFrame], (int) (tileX + x - this.width / 2), (int) (tileY + y - this.height / 2), null);
                } else if (this.dx > 0){
                    g.drawImage(knightRightImage[currentFrame], (int) (tileX + x - this.width / 2), (int) (tileY + y - this.height / 2), null);
                } else if (this.dy < 0){
                    g.drawImage(knightUpImage[currentFrame], (int) (tileX + x - this.width / 2), (int) (tileY + y - this.height / 2), null);

                } else if (this.dy > 0){
                    g.drawImage(knightDownImage[currentFrame], (int) (tileX + x - this.width / 2), (int) (tileY + y - this.height / 2), null);
                } else {
                    g.drawImage(knightDownImage[0], (int) (tileX + x - this.width / 2), (int) (tileY + y - this.height / 2), null);
                }

            } else if (isDuncan) {
                if (this.dx < 0) {
                    g.drawImage(duncanLeftImage[currentFrame], (int) (tileX + x - this.width / 2), (int) (tileY + y - this.height / 2), null);
                } else if (this.dx > 0){
                    g.drawImage(duncanRightImage[currentFrame], (int) (tileX + x - this.width / 2), (int) (tileY + y - this.height / 2), null);
                } else if (this.dy < 0){
                    g.drawImage(duncanUpImage[currentFrame], (int) (tileX + x - this.width / 2), (int) (tileY + y - this.height / 2), null);

                } else if (this.dy > 0){
                    g.drawImage(duncanDownImage[currentFrame], (int) (tileX + x - this.width / 2), (int) (tileY + y - this.height / 2), null);
                } else {
                    g.drawImage(duncanDownImage[0], (int) (tileX + x - this.width / 2), (int) (tileY + y - this.height / 2), null);
                }

            } else if (isBanqou) {
                if (this.dx < 0) {
                    g.drawImage(banquoLeftImage[currentFrame], (int) (tileX + x - this.width / 2), (int) (tileY + y - this.height / 2), null);
                } else if (this.dx > 0){
                    g.drawImage(banquoRightImage[currentFrame], (int) (tileX + x - this.width / 2), (int) (tileY + y - this.height / 2), null);
                } else if (this.dy < 0){
                    g.drawImage(banquoUpImage[currentFrame], (int) (tileX + x - this.width / 2), (int) (tileY + y - this.height / 2), null);

                } else if (this.dy > 0){
                    g.drawImage(banquoDownImage[currentFrame], (int) (tileX + x - this.width / 2), (int) (tileY + y - this.height / 2), null);
                } else {
                    g.drawImage(banquoDownImage[0], (int) (tileX + x - this.width / 2), (int) (tileY + y - this.height / 2), null);
                }

            } else if (isBanqouQhost) {

                g.drawImage(banquoGhost, (int) (tileX + x - this.width / 2), (int) (tileY + y - this.height / 2), null);


            } else if (isLadyMacduff) {
                if (this.dx < 0) {
                    g.drawImage(ladyMacduffLeftImage[currentFrame], (int) (tileX + x - this.width / 2), (int) (tileY + y - this.height / 2), null);
                } else if (this.dx > 0){
                    g.drawImage(ladyMacduffRightImage[currentFrame], (int) (tileX + x - this.width / 2), (int) (tileY + y - this.height / 2), null);
                } else if (this.dy < 0){
                    g.drawImage(ladyMacduffUpImage[currentFrame], (int) (tileX + x - this.width / 2), (int) (tileY + y - this.height / 2), null);

                } else if (this.dy > 0){
                    g.drawImage(ladyMacduffDownImage[currentFrame], (int) (tileX + x - this.width / 2), (int) (tileY + y - this.height / 2), null);
                } else {
                    g.drawImage(ladyMacduffDownImage[0], (int) (tileX + x - this.width / 2), (int) (tileY + y - this.height / 2), null);
                }
            } else if (isMacduffSon) {
                if (this.dx < 0) {
                    g.drawImage(macduffSonLeftImage[currentFrame], (int) (tileX + x - this.width / 2), (int) (tileY + y - this.height / 2), null);
                } else if (this.dx > 0){
                    g.drawImage(macduffSonRightImage[currentFrame], (int) (tileX + x - this.width / 2), (int) (tileY + y - this.height / 2), null);
                } else if (this.dy < 0){
                    g.drawImage(macduffSonUpImage[currentFrame], (int) (tileX + x - this.width / 2), (int) (tileY + y - this.height / 2), null);

                } else if (this.dy > 0){
                    g.drawImage(macduffSonDownImage[currentFrame], (int) (tileX + x - this.width / 2), (int) (tileY + y - this.height / 2), null);
                } else {
                    g.drawImage(macduffSonDownImage[0], (int) (tileX + x - this.width / 2), (int) (tileY + y - this.height / 2), null);
                }

            } else if (isYoungSiward) {

                if (this.dx < 0) {
                    g.drawImage(youngSiwardLeftImage[currentFrame], (int) (tileX + x - this.width / 2), (int) (tileY + y - this.height / 2), null);
                } else if (this.dx > 0){
                    g.drawImage(youngSiwardRightImage[currentFrame], (int) (tileX + x - this.width / 2), (int) (tileY + y - this.height / 2), null);
                } else if (this.dy < 0){
                    g.drawImage(youngSiwardUpImage[currentFrame], (int) (tileX + x - this.width / 2), (int) (tileY + y - this.height / 2), null);

                } else if (this.dy > 0){
                    g.drawImage(youngSiwardDownImage[currentFrame], (int) (tileX + x - this.width / 2), (int) (tileY + y - this.height / 2), null);
                } else {
                    g.drawImage(youngSiwardDownImage[0], (int) (tileX + x - this.width / 2), (int) (tileY + y - this.height / 2), null);
                }

            } else if (isMacduff) {

                if (this.dx < 0) {
                    g.drawImage(macduffLeftImage[currentFrame], (int) (tileX + x - this.width / 2), (int) (tileY + y - this.height / 2), null);
                } else if (this.dx > 0){
                    g.drawImage(macduffRightImage[currentFrame], (int) (tileX + x - this.width / 2), (int) (tileY + y - this.height / 2), null);
                } else if (this.dy < 0){
                    g.drawImage(macduffUpImage[currentFrame], (int) (tileX + x - this.width / 2), (int) (tileY + y - this.height / 2), null);

                } else if (this.dy > 0){
                    g.drawImage(macduffDownImage[currentFrame], (int) (tileX + x - this.width / 2), (int) (tileY + y - this.height / 2), null);
                } else {
                    g.drawImage(macduffDownImage[0], (int) (tileX + x - this.width / 2), (int) (tileY + y - this.height / 2), null);
                }

            } else if (isLadyMacbeth) {
                if (this.dx < 0) {
                    g.drawImage(ladyMacbethLeftImage[currentFrame], (int) (tileX + x - this.width / 2), (int) (tileY + y - this.height / 2), null);
                } else if (this.dx > 0){
                    g.drawImage(ladyMacbethRightImage[currentFrame], (int) (tileX + x - this.width / 2), (int) (tileY + y - this.height / 2), null);
                } else if (this.dy < 0){
                    g.drawImage(ladyMacbethUpImage[currentFrame], (int) (tileX + x - this.width / 2), (int) (tileY + y - this.height / 2), null);

                } else if (this.dy > 0){
                    g.drawImage(ladyMacbethDownImage[currentFrame], (int) (tileX + x - this.width / 2), (int) (tileY + y - this.height / 2), null);
                } else {
                    g.drawImage(ladyMacbethDownImage[0], (int) (tileX + x - this.width / 2), (int) (tileY + y - this.height / 2), null);
                }
            } else if (isWitches){

                    g.drawImage(witch1, (int) (tileX + x - this.width / 2 - 100), (int) (tileY + y - this.height / 2), null);
                    g.drawImage(witch2, (int) (tileX + x - this.width / 2 ), (int) (tileY + y - this.height / 2), null);
                    g.drawImage(witch3, (int) (tileX + x - this.width / 2 + 100), (int) (tileY + y - this.height / 2), null);
            }

        }

    } // draw

    public int heuristic(Node neighbor, Node endNode) {
        return calculateDistance(neighbor.getxIndex(), endNode.getxIndex(), neighbor.getyIndex(), endNode.getyIndex());
    }

    public int calculateDistance(int x1, int x2, int y1, int y2) {

        return (int) Math.sqrt(Math.pow((x1 - x2), 2) + Math.pow((y1 - y2), 2));
    }

    public void run() {
        if (frame / 10 >= 1) {
            currentFrame++;
            if (currentFrame >= 4) {
                currentFrame = 0;
            } // if
            frame = 0;
        } // if

    } //run

    public double getDx() {
        return dx;
    }

    public boolean isLadyMacbeth() {
        return isLadyMacbeth;
    }

    public boolean isWitches() {
        return isWitches;
    }

    public void loseHealth() {
        this.health--;
        if (this.health < 1) {
            GamePanel.enemies.remove(this);
            if (GamePanel.enemies.isEmpty()) {
                this.tileMap.setCleared(true);
            }
            for (int i = 0; i < GamePanel.shotsEnemy.size(); i++) {
                GamePanel.shotsEnemy.remove(i);

            }
        }
    }
    public void sound(String str) {
        System.out.println(str);
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

} // Enemy


