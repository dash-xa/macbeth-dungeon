import java.awt.*;
import java.util.ArrayList;

public class Node implements Comparable {

    private int x; // x
    private int y; // y
    private int yIndex; // index
    private int xIndex; // index


    private int type; //type
    private int f; // f cost
    private int g; // g cost
    private int h; // h cost
    private Node parent; // parent
    private int size; // size
    private boolean wall; // walls


    private ArrayList<Node> neighbors;

    public int getType() {
        return type;
    }

    public ArrayList<Node> getNeighbors() {
        return neighbors;
    }

    public boolean isWall() {
        return wall;
    }

    public Node(int value, int locationY, int locationX) {

        // set attributes
        this.type = value;
        this.yIndex = locationY;
        this.size = 50;
        this.xIndex = locationX;

        this.y = yIndex * 50;
        this.x = xIndex * 50;

        this.neighbors = new ArrayList<>();
        this.parent = null;

        if (this.type == 1 || this.type == 5 || this.type == 12 || this.type == 14) {
            this.wall = true;
        } else {
            this.wall = false;
        }

    } // Node

    public int getSize() {
        return size;
    }


    public void addNeighbors(Node[][] nodeMap) {

        if (this.xIndex < nodeMap.length - 2) {

            this.neighbors.add(nodeMap[this.yIndex][this.xIndex + 1]);
        }

        if (this.xIndex > 1) {
            this.neighbors.add(nodeMap[this.yIndex][this.xIndex - 1]);
        }

        if (this.yIndex > 1) {
            this.neighbors.add(nodeMap[this.yIndex - 1][this.xIndex]);
        }

        if (this.yIndex < nodeMap.length - 2) {
            this.neighbors.add(nodeMap[this.yIndex + 1][this.xIndex]);
        }
    } // addNeighbors

    public void setPrevious(Node previous) {
        this.parent = previous;
    }

    public Node getPrevious() {
        return parent;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getyIndex() {
        return yIndex;
    }

    public int getxIndex() {
        return xIndex;
    }

    public void draw(Graphics2D g) {
        g.fillRect(this.x, this.y, size, size);
    } // draw

    public int getF() {
        return f;
    }

    public int getG() {
        return g;
    }

    public int getH() {
        return h;
    }

    public void setF(int f) {
        this.f = f;
    }

    public void setG(int g) {
        this.g = g;
    }

    public void setH(int h) {
        this.h = h;
    }

    @Override
    public int compareTo(Object e) {
        Node compared = (Node) e;

        if (this.xIndex == compared.xIndex && this.yIndex == compared.yIndex) {
            return 0;
        } else if (this.xIndex < compared.xIndex && this.yIndex < compared.yIndex) {
            return -1;
        } else if (this.xIndex > compared.xIndex && this.yIndex > compared.yIndex) {
            return 1;

        } else {
            return -1;
        }
    }

    public String toString() {
        return ("x: " + this.xIndex + ", y: " + this.yIndex);
    }

    public void setType(int type) {
        this.type = type;
    }
} // Node
