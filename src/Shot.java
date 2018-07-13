import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;


public class Shot {


    private double x; // x
    private double y; // y
    private int radius; // radius

    private double dx; // speed
    private double dy; // speed

    // directions
    private boolean facingLeft;
    private boolean facingRight;
    private boolean facingUp;
    private boolean facingDown;
    private boolean left;
    private boolean right;
    private boolean up;
    private boolean down;
    BufferedImage swordUp;
    BufferedImage swordDown;
    BufferedImage swordRight;
    BufferedImage swordLeft;
    Enemy enemy;

    public Shot(Player player, double x, double y) {

        this.x = x;
        this.y = y;
        this.facingUp = player.isFacingUp();
        this.facingDown = player.isFacingDown();
        this.facingLeft = player.isFacingLeft();
        this.facingRight = player.isFacingRight();
        this.left = player.isLeft();
        this.right = player.isRight();
        this.up = player.isUp();
        this.down = player.isDown();
        this.radius = 4;
        try {
            this.swordUp = ImageIO.read(new File("src/sprites/shot/swordup.png"));
        } catch (IOException e) {
            System.out.println("IOException");
        } // catch
        try {
            this.swordDown= ImageIO.read(new File("src/sprites/shot/sworddown.png"));
        } catch (IOException e) {
            System.out.println("IOException");
        } // catch
        try {
            this.swordRight = ImageIO.read(new File("src/sprites/shot/swordright.png"));
        } catch (IOException e) {
            System.out.println("IOException");
        } // catch
        try {
            this.swordLeft= ImageIO.read(new File("src/sprites/shot/swordleft.png"));
        } catch (IOException e) {
            System.out.println("IOException");
        } // catch
    } // Shot

    public Shot(Enemy enemy, double x, double y, double dx, double dy) {
        this.x = x;
        this.y = y;
        this.dx = dx;
        this.dy = dy;
        this.radius = 4;
        this.enemy = enemy;

        try {
            this.swordUp = ImageIO.read(new File("src/sprites/shot/swordup.png"));
        } catch (IOException e) {
            System.out.println("IOException");
        } // catch
        try {
            this.swordDown= ImageIO.read(new File("src/sprites/shot/sworddown.png"));
        } catch (IOException e) {
            System.out.println("IOException");
        } // catch
        try {
            this.swordRight = ImageIO.read(new File("src/sprites/shot/swordright.png"));
        } catch (IOException e) {
            System.out.println("IOException");
        } // catch
        try {
            this.swordLeft= ImageIO.read(new File("src/sprites/shot/swordleft.png"));
        } catch (IOException e) {
            System.out.println("IOException");
        } // catch
        
    } // Shot


    public double getDx() {
        return dx;
    }

    public double getDy() {
        return dy;
    }

    public int getRadius() {
        return radius;
    }

    public boolean update() {

        if (this.facingUp) {
            this.dx = 0;
            this.dy = -10;
        } else if (this.facingDown) {
            this.dx = 0;
            this.dy = 10;
        } else if (this.facingLeft) {
            this.dx = -10;
            this.dy = 0;
        } else if (this.facingRight) {
            this.dx = 10;
            this.dy = 0;
        }



        this.x += this.dx;
        this.y += this.dy;

        if (x > GamePanel.WIDTH || x < 0 || y > GamePanel.HEIGHT || y < 0) {
            return true;
        }

        return false;
    }


    public void draw(Graphics2D g) {

        if (this.dx < 0 && Math.abs(this.dx) - Math.abs(this.dy) > 0) {
            g.drawImage(swordLeft, (int) (x), (int) (y), null);
        } else if (this.dx > 0 && Math.abs(this.dx) - Math.abs(this.dy) > 0){
            g.drawImage(swordRight, (int) (x), (int) (y), null);
        }

        if (this.dy > 0 && Math.abs(this.dx) - Math.abs(this.dy) < 0){
            g.drawImage(swordDown, (int) (x ), (int) (y ), null);
        } else if (this.dy < 0 && Math.abs(this.dx) - Math.abs(this.dy) < 0){
            g.drawImage(swordUp, (int) (x ), (int) (y ), null);
        }
    } // draw

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }
} // Shot