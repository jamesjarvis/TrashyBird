import java.awt.Rectangle;
import java.awt.Color;
import java.awt.Graphics;
import java.util.Random;

public class Column {

    private static int WIDTH = 60;
    private int GAP_SIZE = 100;
    private int MIN_FROM_EDGE = 30;
    private int X;
    private boolean check;
    private int GAP_START;

    private Color COLOR;

    private Rectangle top;
    private Rectangle bottom;

    /**
     *
     * @param X - the X coordinate of the column to be added
     */
    public Column(int X) {
        this.X = X;
        this.COLOR = Color.GREEN.darker();
        this.check = false;

        Random rand = new Random();
        GAP_START = MIN_FROM_EDGE+rand.nextInt((TrashyBird.getHEIGHT()-Background.getFLOOR_HEIGHT()-(2*MIN_FROM_EDGE)-GAP_SIZE));

        this.top = new Rectangle(X, 0, this.WIDTH, GAP_START);
        this.bottom = new Rectangle(X, GAP_START+GAP_SIZE, this.WIDTH, TrashyBird.getHEIGHT()-Background.getFLOOR_HEIGHT()-(GAP_START+GAP_SIZE));
    }

    public static int getWIDTH() {
        return WIDTH;
    }
    public int getX() {
        return X;
    }
    public boolean isCheck() {
        return check;
    }
    public void setCheck(boolean check) {
        this.check = check;
    }

    public int getGAP_SIZE() {
        return GAP_SIZE;
    }

    public int getGAP_START() {
        return GAP_START;
    }

    public void incrementPosition(){
        X -= TrashyBird.getSpeed();
        top.setLocation(X, top.y);
        bottom.setLocation(X, bottom.y);
    }

    public void paintColumn(Graphics g){
        g.setColor(COLOR);

        g.fillRect(top.x, top.y, top.width, top.height);

        g.fillRect(bottom.x, bottom.y, bottom.width, bottom.height);
    }
}